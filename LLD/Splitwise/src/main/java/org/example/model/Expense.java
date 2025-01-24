package org.example.model;

import org.example.Helper.SplitStrategy;
import org.example.Helper.SplitStrategyFactory;

import java.util.List;

public class Expense {
    int expenseId;
    String expenseName;
    double amount;
    User paidBy;
    List<User> userList;
    List<Split> splitList;

    public Expense(int expenseId, String expenseName, double amount, User paidBy, List<User> userList, SplitType splitType, List<Double> subamount) {
        this.expenseId = expenseId;
        this.expenseName = expenseName;
        this.amount = amount;
        this.paidBy = paidBy;
        this.userList = userList;
        this.splitList = getUserSplits(paidBy, userList, amount, splitType, subamount);
    }

    private List<Split> getUserSplits(User paidBy, List<User> userList, double amount, SplitType splitType, List<Double> subamount) {
        SplitStrategyFactory factory = new SplitStrategyFactory();
        SplitStrategy splitStrategy = factory.createSplitStrategy(splitType);
        return splitStrategy.splitExpense(paidBy, userList, amount, subamount);
    }

    public int getExpenseId() {
        return expenseId;
    }

    public String getExpenseName() {
        return expenseName;
    }

    public double getAmount() {
        return amount;
    }

    public User getPaidBy() {
        return paidBy;
    }

    public List<User> getUserList() {
        return userList;
    }

    public List<Split> getSplitList() {
        return splitList;
    }
}
