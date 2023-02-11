package info.kgeorgiy.ja.bondarev.bank;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Remote interface for people.
 */
public interface Person extends Remote {

    /** Returns name of person */
    String getName() throws RemoteException;

    /** Returns surname of person */
    String getSurname() throws RemoteException;

    /** Returns passport of person */
    long getPassport() throws RemoteException;

}
