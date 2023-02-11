package info.kgeorgiy.ja.bondarev.bank;

import java.io.Serializable;
import java.rmi.RemoteException;

/**
 * Implementation of {@link Person} interface. This type of person is connected to the bank.
 * @author Nikita Bondarev
 */
public class RemotePerson implements Person, Serializable {

    private final String name;
    private final String surname;
    private final long passport;

    /**
     * Creates instance of {@link RemotePerson}.
     * @param name name of the person.
     * @param surname surname of the person.
     * @param passport passport of the person.
     */
    public RemotePerson(String name, String surname, long passport) {
        this.name = name;
        this.surname = surname;
        this.passport = passport;
    }

    @Override
    public String getName() throws RemoteException {
        return name;
    }

    @Override
    public String getSurname() throws RemoteException {
        return surname;
    }

    @Override
    public long getPassport() throws RemoteException {
        return passport;
    }
}
