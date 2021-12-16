package com.inventor.promaxcheckque.entities;


import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "expenses", schema = "promax_billing")
public class ExpensesEntity {
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "user")
    private String user;
    @Basic
    @Column(name = "date_created")
    private Date dateCreated;
    @Basic
    @Column(name = "amount")
    private long amount;
    @Basic
    @Column(name = "comment")
    private String comment;

    public ExpensesEntity(int id, String user, Date dateCreated, long amount, String comment) {
        this.id = id;
        this.user = user;
        this.dateCreated = dateCreated;
        this.amount = amount;
        this.comment = comment;
    }

    public ExpensesEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExpensesEntity)) return false;
        ExpensesEntity that = (ExpensesEntity) o;
        return id == that.id && amount == that.amount && user.equals(that.user) && dateCreated.equals(that.dateCreated) && comment.equals(that.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, dateCreated, amount, comment);
    }
}
