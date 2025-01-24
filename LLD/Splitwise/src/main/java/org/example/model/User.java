package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    int userId;

    String userName;
    String emailId;
    String phoneNo;
    List<Group> groupList;
    List<Expense> expenseList;
    double totalBalance; //basically total amount user will get or need to pay

    public User(int userId, String userName, String emailId, String phoneNo) {
        this.userId = userId;
        this.userName = userName;
        this.emailId = emailId;
        this.phoneNo = phoneNo;
        this.groupList = new ArrayList<>();
        this.expenseList = new ArrayList<>();
        this.totalBalance = 0;
    }

    public List<Group> getGroupList() {
        return groupList;
    }

    public List<Expense> getExpenseList() {
        return expenseList;
    }

    public void addGroup(Group group) {
        this.groupList.add(group);
    }

    public String getUserName() {
        return userName;
    }

    public void addExpense(Expense expense) {
        this.expenseList.add(expense);
    }

    public void showExpenses() {
        for(Expense expense : expenseList) {
            System.out.println("-------------------------------------------------\n");
            System.out.print(expense.getExpenseName() + " expense is paid by " + expense.getPaidBy().getUserName() + " of amount : " + expense.getAmount());
            for(Split split : expense.getSplitList()) {
                if(split.getUser() == this) {
                    System.out.println(" and " + getUserName() + " owes : " + split.getAmount() + " rs");
                }
            }
        }
    }

    public void showNetBalance() {
        System.out.println("-------------------------------------------------");
        System.out.println("Total balance for user : " + this.userName + ", is : " + this.totalBalance);
    }

    public void updateAmount(double amount) {
        this.totalBalance += amount;
    }
}
