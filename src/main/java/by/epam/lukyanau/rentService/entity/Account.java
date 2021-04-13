package by.epam.lukyanau.rentService.entity;

import java.math.BigDecimal;

public class Account extends Entity {
    private BigDecimal balance;
    private int userId;
    private boolean status;


    public Account() {
    }

    public Account(int userId) {
        this.userId = userId;
    }

    public Account(int userId, boolean status) {
        this.userId = userId;
        this.status = status;
    }

    public Account(boolean status) {
        this.status = status;
    }

    public Account(BigDecimal balance, int userId) {
        this.balance = balance;
        this.userId = userId;
    }

    public Account(BigDecimal balance, boolean status) {
        this.balance = balance;
        this.status = status;
    }

    public Account(BigDecimal balance, int userId, boolean status) {
        this.balance = balance;
        this.userId = userId;
        this.status = status;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        if (userId != account.userId) return false;
        if (status != account.status) return false;
        return balance != null ? balance.equals(account.balance) : account.balance == null;
    }

    @Override
    public int hashCode() {
        int result = balance != null ? balance.hashCode() : 0;
        result = 31 * result + userId;
        result = 31 * result + (status ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Account{" +
                "balance=" + balance +
                ", userId=" + userId +
                ", status=" + status +
                '}';
    }
}
