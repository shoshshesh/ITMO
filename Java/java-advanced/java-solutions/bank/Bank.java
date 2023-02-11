package info.kgeorgiy.ja.bondarev.bank;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

/**
 * Remote interface for banks.
 */
public interface Bank extends Remote {
    /**
     * Creates a new account with specified identifier if it does not already exist.
     * @param id account id
     * @return created or existing account.
     */
    Account createAccount(String id) throws RemoteException;

    /**
     * Returns an account by identifier.
     * @param id account id
     * @return account with specified identifier or {@code null} if such account does not exist.
     */
    Account getAccount(String id) throws RemoteException;

    /**
     * Register a new person with given name, surname and passport if this person is not already registered.
     * @param name name of the person.
     * @param surname surname of the person.
     * @param passport unique passport number.
     * @return created or existing person.
     */
    Person createPerson(String name, String surname, long passport) throws RemoteException;

    /**
     * Returns a person of given {@link PersonType type} by passport if this person is registered.
     * @param passport passport number of the person to be found.
     * @param type type of the person.
     * @return person of given type with specified passport or {@code null} if such person is not registered.
     */
    Person getPerson(long passport, PersonType type) throws RemoteException;

    /**
     * Returns a {@link java.util.concurrent.ConcurrentMap map} of people by passport.
     * @return map of people by passport.
     */
    Map<Long, Person> getPeople() throws RemoteException;

    /**
     * Returns a {@link java.util.concurrent.ConcurrentMap map} of accounts by id.
     * @return map of accounts by id.
     */
    Map<String, Account> getAccounts() throws RemoteException;
}
