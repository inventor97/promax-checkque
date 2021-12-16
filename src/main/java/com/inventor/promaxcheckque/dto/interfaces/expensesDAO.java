package com.inventor.promaxcheckque.dto.interfaces;

import com.inventor.promaxcheckque.entities.ExpensesEntity;

import java.sql.Date;
import java.util.List;

public interface expensesDAO extends commonDAO<ExpensesEntity> {

    List<ExpensesEntity> getByList(Date date,boolean byMonth);

}
