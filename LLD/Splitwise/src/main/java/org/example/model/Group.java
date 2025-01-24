package org.example.model;

import com.sun.media.sound.InvalidDataException;
import javafx.util.Pair;

import java.util.*;

public class Group {
    int groupId;
    String groupName;
    HashMap<User, Double> userToBalanceMap;
    List<Expense> expenseList;
    double totalExpend;
    double netSettleRemaining;
    HashMap<User, List<Pair<User, Double>>> simplifiedMap;

    public Group(int groupId, String groupName) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.userToBalanceMap = new HashMap<>();
        this.expenseList = new ArrayList<>();
        this.totalExpend = 0;
        this.netSettleRemaining = 0;
        this.simplifiedMap = new HashMap<>();
    }

    public void addUser(User user) {
        if(userToBalanceMap.containsKey(user)) {
            System.out.println("User already exists");
        } else {
            this.userToBalanceMap.put(user, (double) 0);
            user.addGroup(this);
        }
    }

    public List<Expense> getExpenseList() {
        return expenseList;
    }

    public void addExpense(Expense expense) throws InvalidDataException {
        this.expenseList.add(expense);
        this.totalExpend += expense.getAmount();
        updateUserData(expense);
        simplyfyExpenses();
    }

    private void updateUserData(Expense expense) throws InvalidDataException {
        boolean flag = false;
        for(Split split : expense.getSplitList()) {
            User user = split.getUser();
            user.addExpense(expense);
            if(userToBalanceMap.containsKey(user)) {
                double amount = split.getAmount();
                double userBalance = userToBalanceMap.get(user);
                userBalance = userBalance - amount;
                user.updateAmount(-1 * amount);

                if(user == expense.paidBy) {
                    userBalance = userBalance + expense.getAmount();
                    user.updateAmount(expense.getAmount());
                    flag = true;
                }

                userToBalanceMap.put(user, userBalance);
            } else {
                throw new InvalidDataException("User data is not present in group, please validate");
            }
        }

        //if user who paid is not part of split
        if(!flag) {
            userToBalanceMap.put(expense.getPaidBy(), expense.getAmount());
            expense.getPaidBy().addExpense(expense);
            expense.getPaidBy().updateAmount(expense.getAmount());
        }
    }

    public void simplyfyExpenses() {
        this.simplifiedMap = new HashMap<>();

        PriorityQueue<Pair<User, Double>> plusAmount = new PriorityQueue<>(Comparator.comparing(Pair<User, Double>::getValue).reversed());
        PriorityQueue<Pair<User, Double>> minusAmount = new PriorityQueue<>(Comparator.comparing(Pair<User, Double>::getValue).reversed());

        for (Map.Entry<User, Double> mapElement : userToBalanceMap.entrySet()) {
            if(mapElement.getValue() < 0) {
                minusAmount.add(new Pair<>(mapElement.getKey(), Math.abs(mapElement.getValue())));
            } else if(mapElement.getValue() > 0) {
                plusAmount.add(new Pair<>(mapElement.getKey(), mapElement.getValue()));
            }
        }

        while(!plusAmount.isEmpty() && !minusAmount.isEmpty()) {
            Pair<User, Double> plusPair = plusAmount.poll();
            Pair<User, Double> minusPair = minusAmount.poll();
            Double minVal = Math.min(plusPair.getValue(), minusPair.getValue());

            if(plusPair.getValue() - minVal != 0) {
                plusAmount.add(new Pair<>(plusPair.getKey(), plusPair.getValue()-minVal));
            } else if(minusPair.getValue() - minVal != 0) {
                minusAmount.add(new Pair<>(minusPair.getKey(), minusPair.getValue()-minVal));
            }

            simplifiedMap.computeIfAbsent(plusPair.getKey(), k -> new ArrayList<>()).add(new Pair<>(minusPair.getKey(), minVal));
        }

        if(!plusAmount.isEmpty() || !minusAmount.isEmpty()) {
            System.out.println("Error occured in simplifying expenses");
        } else {
            System.out.println("Expenses simplified");
        }
    }

    public void showExpenses() {
        for(Expense expense : expenseList) {
            System.out.println("-------------------------------------------------");
            System.out.println(expense.getExpenseName() + " expense is paid by " + expense.getPaidBy().getUserName() + " and owed by " );
            for(Split split : expense.getSplitList()) {
                System.out.println(split.getUser().getUserName() + " borrowed " + split.getAmount() + " rs");
            }
        }
    }

    public void showNetGroupExpend() {
        System.out.println("-------------------------------------------------");
        System.out.println("Total group expend is : " + this.totalExpend);
    }

    public void showNetGroupBalanceToBeSettled() {
        userToBalanceMap.forEach((k, v)
                -> this.netSettleRemaining+=v);
        System.out.println("-------------------------------------------------");
        System.out.println("Total settlement remaining is : " + this.netSettleRemaining);
    }

    public void showSimplifiedExpense() {
        System.out.println("-------------------------------------------------");
        for (Map.Entry<User, List<Pair<User, Double>>> entry : simplifiedMap.entrySet()) {
            User receiver = entry.getKey();
            List<Pair<User, Double>> payerList = entry.getValue();

            for(Pair<User, Double> payer : payerList) {
                User borrower = payer.getKey();
                Double amount = payer.getValue();
                System.out.println(borrower.getUserName() + " owes " + receiver.getUserName() + " Rs " + String.format("%.2f", amount));
            }

        }
    }
}
