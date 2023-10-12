package bank;

import java.util.ArrayList;

public class Account {

    private ArrayList<Integer> transactions = new ArrayList<Integer>();

    public Integer getBalance() { return transactions.stream().reduce(0, Integer::sum); }

    public Account deposit(Integer amount) {
        if (amount < 0) throw new RuntimeException("Cannot deposit a negative amount");
        transactions.add(amount);
        return this;
    }

    public Account withdraw(Integer amount) {
        if (amount > this.getBalance() || amount < 0) throw new RuntimeException("Cannot withdraw more than balance or a negative amount");
        transactions.add(-amount);
        return this;
    }

    public String report() {
        String report = "";
        for (Integer transaction : transactions) {
            if (transaction > 0) report += "Deposited " + transaction + "\n";
            else report += "Withdrew " + -transaction + "\n";
        }
        report += "Balance is " + this.getBalance();
        return report;
    }
}
