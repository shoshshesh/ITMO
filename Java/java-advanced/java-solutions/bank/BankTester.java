package info.kgeorgiy.ja.bondarev.bank;

import org.junit.Test;
import org.junit.Assert;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * Class for testing {@link RemoteBank}.
 * @author Nikita Bondarev
 */
public class BankTester {
    private static final int PORT = 8888;
    private static Bank bank;
    private static Registry registry;

    static {
        try {
            registry = LocateRegistry.createRegistry(PORT);
        } catch (RemoteException e) {
            System.err.println("Cannot export registry." + e.getMessage());
        }
    }

    /**
     * Recreate {@code bank} for a new test.
     * @param message message describing the test.
     */
    public void beforeEach(String message) throws RemoteException {
        System.out.println(message);
        bank = new RemoteBank(PORT);
        UnicastRemoteObject.exportObject(bank, PORT);
        registry.rebind("//localhost/bank", bank);
    }

    @Test
    public void test1() throws RemoteException {
        beforeEach("Creating 5 people and 5 accounts. Setting money for each account.");
        for (int i = 1; i <= 5; i++) {
            bank.createPerson("Name" + i, "Surname" + i, i);
            bank.createAccount(i + ":" + i);
            bank.getAccount(i + ":" + i).setAmount(i);
        }
        check();
        System.out.println();
    }

    private void check() throws RemoteException {
        final Map<Long, Person> people = bank.getPeople();
        final Map<String, Account> accounts = bank.getAccounts();
        Assert.assertEquals(5, people.size());
        Assert.assertEquals(5, accounts.size());
        for (Map.Entry<Long, Person> entry : people.entrySet()) {
            final long curPassport = entry.getKey();
            final Person curPerson = entry.getValue();
            Assert.assertEquals(curPassport, curPerson.getPassport());
            Assert.assertEquals("Name" + curPassport, curPerson.getName());
            Assert.assertEquals("Surname" + curPassport, curPerson.getSurname());
            final Account accountOfCurPerson = accounts.get(curPassport + ":" + curPassport);
            Assert.assertEquals(curPassport, accountOfCurPerson.getAmount());
        }
        System.out.println();
    }

    @Test
    public void test2() throws RemoteException {
        beforeEach("Creating 2 people with the same passport.");
        bank.createPerson("Ivan", "Ivanov", 12345);
        bank.createPerson("Masha", "Popova", 12345);
        Assert.assertEquals(1, bank.getPeople().size());
        System.out.println();
    }

    @Test
    public void test3() throws RemoteException {
        beforeEach("Creating 2 accounts with the same id.");
        bank.createPerson("Ivan", "Ivanov", 12345);
        for (int i = 0; i < 2; i++) {
            bank.createAccount("12345:1");
        }
        Assert.assertEquals(1, bank.getAccounts().size());
        System.out.println();
    }

    @Test
    public void test4() throws RemoteException {
        beforeEach("Checking LocalPerson does not connected to the bank.");
        final long passport = 12345;
        bank.createPerson("Ivan", "Ivanov", passport);
        final String accountId = passport + ":" + 1;
        bank.createAccount(accountId);

        final Account remoteAccount = bank.getAccount(accountId);
        remoteAccount.setAmount(100);

        final LocalPerson localPerson = (LocalPerson) bank.getPerson(passport, PersonType.LOCAL);
        final Account localAccount = localPerson.getAccount(accountId);
        localAccount.setAmount(200);

        Assert.assertNotEquals(remoteAccount.getAmount(), localAccount.getAmount());


        remoteAccount.setAmount(500);
        Assert.assertEquals(500, bank.getAccount(accountId).getAmount());

        localPerson.addAccount(new RemoteAccount(passport + ":" + 2));
        Assert.assertNotEquals(bank.getAccounts().size(), localPerson.getAccounts().size());
    }

    @Test
    public void test5() throws RemoteException {
        beforeEach("Like test1() but concurrently.");
        final ExecutorService service = Executors.newFixedThreadPool(5);
        final Phaser phaser = new Phaser(1);
        for (int i = 1; i <= 5; i++) {
            final int finalI = i;
            service.submit(() -> {
                try {
                    phaser.register();
                    bank.createPerson("Name" + finalI, "Surname" + finalI, finalI);
                    bank.createAccount(finalI + ":" + finalI);
                    bank.getAccount(finalI + ":" + finalI).setAmount(finalI);
                } catch (RemoteException e) {
                    System.err.println("RemoteException occurred." + e.getMessage());
                } finally {
                    phaser.arrive();
                }
            });
        }
        phaser.arriveAndAwaitAdvance();
        check();
        closeService(service);
    }

    @Test
    public void test6() throws RemoteException {
        beforeEach("5 thread are trying to create the same person and the same account.");
        final ExecutorService service = Executors.newFixedThreadPool(5);
        final Phaser phaser = new Phaser(1);
        final long passport = 100;
        for (int i = 1; i <= 5; i++) {
            final int finalI = i;
            service.submit(() -> {
                try {
                    phaser.register();
                    bank.createPerson("Name", "Surname", passport);
                    bank.createAccount(passport + ":" + passport);
                    bank.getAccount(passport + ":" + passport).setAmount(finalI);
                } catch (RemoteException e) {
                    System.err.println("RemoteException occurred." + e.getMessage());
                } finally {
                    phaser.arrive();
                }
            });
        }
        phaser.arriveAndAwaitAdvance();
        Assert.assertEquals(1, bank.getPeople().size());
        Assert.assertEquals(1, bank.getAccounts().size());
        System.out.println();
        closeService(service);
    }

    private static void closeService(ExecutorService executorService) {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(2, TimeUnit.SECONDS)) {
                System.out.println("Service termination is taking too much time. It's forcibly stopped.");
            }
        } catch (InterruptedException e) {
            System.err.println("Interrupted while waiting termination of this service: " + e.getMessage());
        }
        executorService.shutdownNow();
    }

}