package org.example.model;

public class Split {
    User user;
    double amount;
    SplitType splitType;

    public Split(User user, double amount, SplitType splitType) {
        this.user = user;
        this.amount = amount;
        this.splitType = splitType;
    }

    public User getUser() {
        return user;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public SplitType getSplitType() {
        return splitType;
    }
}
