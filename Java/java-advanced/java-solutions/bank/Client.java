package info.kgeorgiy.ja.bondarev.bank;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Client for checking how {@link RemoteBank} works.
 * @author Nikita Bondarev
 */
public final class Client {
    /** Utility class. */
    public Client() {}

    private static final int PORT = 8888;

    /**
     * Main method of {@link Client}.
     * <p>Register a person if they are not registered yet otherwise checks their name and surname.
     * <p>Creates an account for the person if it is not already created and set a new value.
     * @param args name, surname, passport, accountId, new amount.
     */
    public static void main(final String[] args) throws RemoteException {
        if (args == null || args.length != 5) {
            System.out.println("Wrong arguments. Expected: name surname passport accountId money");
            return;
        }
        final Registry registry = LocateRegistry.getRegistry(PORT);
        final Bank bank;
        try {
            bank = (Bank) registry.lookup("//localhost/bank");
        } catch (final NotBoundException e) {
            System.out.println("Bank is not bound");
            return;
        }
        final String name = args[0];
        final String surname = args[1];
        final long passport = Long.parseLong(args[2]);
        final String accountId = args[3];
        final int money = Integer.parseInt(args[4]);
        Person person = bank.getPerson(passport, PersonType.REMOTE);
        if (person == null) {
            System.out.println("Person with passport " + passport + " doesn't exist.");
            System.out.println("Registering the person in the bank.");
            person = bank.createPerson(name, surname, passport);
        } else {
            if (!name.equals(person.getName())) {
                System.out.println("Wrong name.");
                return;
            }
            if (!surname.equals(person.getSurname())) {
                System.out.println("Wrong surname.");
                return;
            }
        }
        final String accountIdInBank = passport + ":" + accountId;
        Account account = bank.getAccount(accountIdInBank);
        if (account == null) {
            System.out.println("Account " + accountId + " of person with passport " + passport + " doesn't exist.");
            System.out.println("Creating the account.");
            account = bank.createAccount(accountIdInBank);
        }
        account.setAmount(money);
        System.out.println("New amount of money on account " + accountId + " of person with passport " +
                passport + ": " + account.getAmount());
    }
}
