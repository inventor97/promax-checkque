package com.inventor.promaxcheckque.entities;


public class tableClass {

    private int no;
    private String name;
    private String sub;
    private String teacher;
    private String month;
    private long amount;
    private ChecksDataEntity obj;
    private ExpensesEntity expObj;

    public tableClass() {
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public ChecksDataEntity getObj() {
        return obj;
    }

    public void setObj(ChecksDataEntity obj) {
        this.obj = obj;
    }

    public ExpensesEntity getExpObj() {
        return expObj;
    }

    public void setExpObj(ExpensesEntity expObj) {
        this.expObj = expObj;
    }

    public tableClass(int order, ChecksDataEntity obj) {
        this.obj = obj;
        this.no = order;
        this.name = obj.getName();
        this.month = obj.getPayedMonth();
        this.sub = obj.getSubjects();
        this.teacher = obj.getTeachers();
        this.amount = obj.getAmountBill();
    }

}
