package info.kgeorgiy.ja.bondarev.bank;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Implementation of {@link Bank} interface. Works remotely, connected to the port.
 * @author Nikita bondarev
 */
public class RemoteBank implements Bank {

    private final int port;
    private final ConcurrentMap<String, Account> accounts = new ConcurrentHashMap<>();
    private final ConcurrentMap<Long, Person> people = new ConcurrentHashMap<>();
    private final ConcurrentMap<Long, Set<Account>> accountsOfPerson = new ConcurrentHashMap<>();

    /**
     * Creates instance of {@link RemoteBank} connected to the given port.
     * @param port port for connection.
     */
    public RemoteBank(final int port) {
        this.port = port;
    }

    @Override
    public Account createAccount(final String id) throws RemoteException {
        final String[] data = id.split(":");
        if (data.length != 2) {
            throw new RemoteException("Wrong id. Example - passport:id");
        }
        final Person person = getPerson(Long.parseLong(data[0]), PersonType.REMOTE);
        if (person == null) {
            throw new RemoteException("There is no person with passport: " + data[0] + ".");
        }
        final long passport = person.getPassport();
        final Account account = new RemoteAccount(id);
        if (accounts.putIfAbsent(id, account) == null) {
            System.out.println("Creating the account " + id + " for person with passport " + passport + ".");
            UnicastRemoteObject.exportObject(account, port);
            accountsOfPerson.get(passport).add(account);
            return account;
        } else {
            System.out.println("Account " + id + " already exists.");
            return getAccount(id);
        }
    }

    @Override
    public Account getAccount(final String id) {
        System.out.println("Retrieving account " + id);
        return accounts.get(id);
    }

    @Override
    public Person createPerson(final String name, final String surname, final long passport) throws RemoteException {
        final RemotePerson person = new RemotePerson(name, surname, passport);
        if (people.putIfAbsent(passport, person) == null) {
            System.out.println("Registering the person with passport " + passport + ".");
            UnicastRemoteObject.exportObject(person, port);
            accountsOfPerson.put(passport, ConcurrentHashMap.newKeySet());
            return person;
        } else {
            System.out.println("This person with passport " + passport + " already exists.");
            return getPerson(passport, PersonType.REMOTE);
        }
    }

    @Override
    public Person getPerson(final long passport, final PersonType type) throws RemoteException {
        System.out.println("Retrieving the person with passport " + passport + ", type " + type + ".");
        switch (type) {
            case LOCAL -> {
                return getLocalPerson(passport);
            }
            case REMOTE -> {
                return getRemotePerson(passport);
            }
            default -> {
                System.out.println("Wrong type of person.");
                return null;
            }
        }
    }

    @Override
    public Map<Long, Person> getPeople() throws RemoteException {
        return people;
    }

    @Override
    public Map<String, Account> getAccounts() throws RemoteException {
        return accounts;
    }

    private LocalPerson getLocalPerson(final long passport) throws RemoteException {
        final RemotePerson person = getRemotePerson(passport);
        if (person == null) {
            return null;
        }
        final Set<Account> accountsOfLocalPerson = accountsOfPerson.get(person.getPassport());
        final Map<String, Account> map = new HashMap<>();
        for (Account account : accountsOfLocalPerson) {
            map.put(account.getId(), cloneAccount(account));
        }
        return new LocalPerson(person.getName(), person.getSurname(), person.getPassport(), map);
    }

    private RemotePerson getRemotePerson(final long passport) {
        final RemotePerson person = (RemotePerson) people.get(passport);
        if (person == null) {
            System.out.println("There is no person with given passport.");
        }
        return person;
    }

    private Account cloneAccount(final Account original) throws RemoteException {
        final Account clone = new RemoteAccount(original.getId());
        clone.setAmount(original.getAmount());
        return clone;
    }
}
