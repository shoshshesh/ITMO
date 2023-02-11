package info.kgeorgiy.ja.bondarev.bank;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Map;

/**
 * Implementation of {@link Person} interface. This type of person is not connected to the bank.
 * All changes do not affect on the bank states.
 * @author Nikita Bondarev
 */
public class LocalPerson implements Person, Serializable {

    private final String name;
    private final String surname;
    private final long passport;
    private final Map<String, Account> accounts;

    /**
     * Creates instance of {@link LocalPerson}.
     * @param name name of the person.
     * @param surname surname of the person.
     * @param passport passport of the person.
     * @param accounts all accounts of the person.
     */
    public LocalPerson(final String name, final String surname, final long passport,
                       final Map<String, Account> accounts) {
        this.name = name;
        this.surname = surname;
        this.passport = passport;
        this.accounts = accounts;
    }

    // :NOTE: AbstractPerson
    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getSurname() {
        return surname;
    }

    @Override
    public long getPassport() {
        return passport;
    }

    /**
     * Returns map of all {@link Account accounts} of this person by id.
     */
    public Map<String, Account> getAccounts() throws RemoteException {
        return accounts;
    }

    /**
     * Add given {@link Account account} to the {@code accounts}.
     * @param account account to be added.
     */
    public void addAccount(final Account account) throws RemoteException {
        if (accounts.putIfAbsent(account.getId(), account) != null) {
            System.out.println("This person has already got an account with given id.");
        }
    }

    /**
     * Returns account from {@code accounts} by id.
     * @param id id of account.
     */
    public Account getAccount(final String id) {
        return accounts.get(id);
    }
}
