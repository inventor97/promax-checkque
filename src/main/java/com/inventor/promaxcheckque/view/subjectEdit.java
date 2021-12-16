package com.inventor.promaxcheckque.view;

import com.inventor.promaxcheckque.entities.SubjectsEntity;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.scene.layout.AnchorPane;

public class subjectEdit {

    private AnchorPane mainBkg;
    private AnchorPane subjectEditNode;
    private MFXTextField editedSubject;
    private MFXButton addSubject;

    public subjectEdit(AnchorPane mainBkg, AnchorPane subjectEditNode, MFXTextField editedSubject, MFXButton addSubject) {
        this.mainBkg = mainBkg;
        this.subjectEditNode = subjectEditNode;
        this.editedSubject = editedSubject;
        this.addSubject = addSubject;
    }

    public SubjectsEntity initEditingSubject(SubjectsEntity obj) {
        mainBkg.setVisible(true);
        subjectEditNode.setVisible(true);
        if (obj != null) {
            editedSubject.setText(obj.getName());
            addSubject.setText("O'zgartirish");
        }
        return obj;
    }
}
