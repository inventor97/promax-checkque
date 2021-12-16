package com.inventor.promaxcheckque.entities;


import java.util.Objects;


public class expTableClass {
    private int no;
    private long amount;
    private String by;
    private String comment;
    private String date;

    public expTableClass(int no, long amount, String by, String comment, String date) {
        this.no = no;
        this.amount = amount;
        this.by = by;
        this.comment = comment;
        this.date = date;
    }

    public expTableClass() {
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof expTableClass)) return false;
        expTableClass that = (expTableClass) o;
        return no == that.no && amount == that.amount && by.equals(that.by) && comment.equals(that.comment) && date.equals(that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(no, amount, by, comment, date);
    }

    public expTableClass(int order, ExpensesEntity obj) {
        this.no = order;
        this.amount = obj.getAmount();
        this.by = obj.getUser();
        this.date = String.valueOf(obj.getDateCreated());
        this.comment = obj.getComment();
    }
}
