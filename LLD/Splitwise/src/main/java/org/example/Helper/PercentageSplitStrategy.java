package org.example.Helper;

import org.example.model.Split;
import org.example.model.SplitType;
import org.example.model.User;

import java.util.ArrayList;
import java.util.List;

public class PercentageSplitStrategy extends SplitStrategy {
    @Override
    public List<Split> splitExpense(User paidBy, List<User> userList, double amount, List<Double> subamounts) {
        List<Split> splits = new ArrayList<>();
        for(int i = 0; i < userList.size(); i++) {
            User user = userList.get(i);
            double share = amount * subamounts.get(i);
            splits.add(new Split(user, share, SplitType.PERCENTAGE));
        }

        if(validateExpense(paidBy, splits, amount)) {
            System.out.println("Validation succeed");
            return splits;
        }

        System.out.println("Validation failed, please check amount/split entered");
        return null;
    }
}
