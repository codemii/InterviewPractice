package org.example;

import com.sun.media.sound.InvalidDataException;
import org.example.model.Expense;
import org.example.model.Group;
import org.example.model.SplitType;
import org.example.model.User;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InvalidDataException {
        //Creating users
        User user1 = new User(1, "shivi", "shivi@gmail.com", "12345");
        User user2 = new User(2, "jai", "jai@gmail.com", "43453");
        User user3 = new User(3, "bhanu", "bhanu@gmail.com", "85654");
        User user4 = new User(4, "tinni", "tinni@gmail.com", "93445");

        //Creating groups
        Group group1 = new Group(1, "travel");
        Group group2 = new Group(2, "office");

        group1.addUser(user1);
        group1.addUser(user2);
        group1.addUser(user3);

        group2.addUser(user1);
        group2.addUser(user2);
        group2.addUser(user3);
        group2.addUser(user4);

        List<User> users = new ArrayList<>();
        users.add(user2);
        users.add(user3);
        users.add(user4);
        Expense expense = new Expense(1, "auto", 45, user1, users, SplitType.EQUAL, null);
        group2.addExpense(expense);
        group2.showSimplifiedExpense();

        users.clear();
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        List<Double> subamounts = new ArrayList<>();
        subamounts.add(0.2);
        subamounts.add(0.3);
        subamounts.add(0.4);
        subamounts.add(0.1);
        expense = new Expense(2, "cab", 240, user2, users, SplitType.PERCENTAGE, subamounts);
        group2.addExpense(expense);
        group2.showSimplifiedExpense();

        group2.showExpenses();
        group2.showNetGroupExpend();
        group2.simplyfyExpenses();
        group2.showSimplifiedExpense();
        group2.showNetGroupBalanceToBeSettled();

        user1.showExpenses();
        user1.showNetBalance();
        user2.showNetBalance();
        user3.showNetBalance();
        user4.showNetBalance();
    }
}