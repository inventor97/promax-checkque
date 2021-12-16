package com.inventor.promaxcheckque.dto.interfaces;

import com.inventor.promaxcheckque.entities.TeachersEntity;

import java.util.List;

public interface teachers extends commonDAO<TeachersEntity>{


    List<TeachersEntity> getTeachersList();

    long getTeachersCountOnSubject(int subjectId);
}
