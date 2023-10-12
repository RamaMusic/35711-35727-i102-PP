package bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BankTest {
    @Test
    public void test01NewAccountStartsEmpty() { assertEquals(0, new Account().getBalance()); }

    @Test
    public void test02DepositIncreasesBalance() {
        assertEquals(10, new Account().deposit(10).getBalance());
    }

    @Test
    public void test03DepositZeroDoesNotChangeBalance() {
        assertEquals(0, new Account().deposit(0).getBalance());
    }

    @Test
    public void test04DepositTwoAmountsIncreasesBalance() {
        assertEquals(25, new Account().deposit(10).deposit(15).getBalance());
    }

    @Test
    public void test05WithdrawDecreasesBalance() {
        assertEquals(10, new Account().deposit(20).withdraw(10).getBalance());
    }

    @Test
    public void test06WithdrawFailsNoResidue() {
        Account account = new Account();
        assertThrows(RuntimeException.class, () -> account.withdraw(10));
        assertEquals(0, account.getBalance());
    }

    @Test
    public void test07ReportAfterADeposit() {
        String report = "Deposited 10\nBalance is 10";
        assertEquals(report, new Account().deposit(10).report());
    }

    @Test
    public void test09ReportAfterOneDepositAndOneWithdrawal() {
        String report = "Deposited 10\nWithdrew 5\nBalance is 5";
        assertEquals(report, new Account().deposit(10).withdraw(5).report());
    }
}
