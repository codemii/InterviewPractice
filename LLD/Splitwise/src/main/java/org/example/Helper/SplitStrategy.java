package org.example.Helper;

import org.example.model.Split;
import org.example.model.User;

import java.util.List;

public abstract class SplitStrategy {
    public List<Split> splitExpense(User paidBy, List<User> userList, double amount, List<Double> subamounts) {
        //overridden in inherited classes
        return null;
    }
    public boolean validateExpense(User paidBy, List<Split> splitList, double amount) {
        for(Split split : splitList) {
            amount -= split.getAmount();
            //making amount 0 for the user who is paying the expense
//            if(paidBy == split.getUser()) {
//                split.setAmount(0);
//            }
//            //else setting it as -ve or borrowed amount
//            else {
//                split.setAmount(-1 * split.getAmount());
//            }
        }
        if(amount == 0) {
            return true;
        }
        return false;
    }
}
