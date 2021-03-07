package by.epam.lukyanau.rentService.entity;

import java.math.BigDecimal;

public class Account extends Entity {
    private BigDecimal balance;
    private int userId;
    private byte status;

    public Account() {
    }

    public Account(BigDecimal balance, byte status) {
        this.balance = balance;
        this.status = status;
    }

    public Account(BigDecimal balance, int userId, byte status) {
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


    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
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
        result = 31 * result + (int) status;
        return result;
    }

    @Override
    public String toString() {
        return "Account" +
                " balance=" + balance +
                ", userId=" + userId +
                ", status=" + status;
    }
}
