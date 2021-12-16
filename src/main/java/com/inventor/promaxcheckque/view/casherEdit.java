package com.inventor.promaxcheckque.view;

import com.inventor.promaxcheckque.entities.CashersEntity;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.IOException;

public class casherEdit {

    private AnchorPane popupBkg;
    private AnchorPane casherEditPane;
    private MFXTextField name;
    private PasswordField pass;
    private MFXButton addBtn;
    private Circle img;


    public casherEdit(AnchorPane popupBkg, AnchorPane casherEditPane, MFXTextField name, PasswordField pass, MFXButton addBtn, Circle img) {
        this.popupBkg = popupBkg;
        this.casherEditPane = casherEditPane;
        this.name = name;
        this.pass = pass;
        this.addBtn = addBtn;
        this.img = img;
    }

    public void initEditingCasher(CashersEntity obj) {
        popupBkg.setVisible(true);
        casherEditPane.setVisible(true);
        if (obj != null) {
            name.setText(obj.getName());
            pass.setText(obj.getPassword());
            if (obj.getImg() != null && obj.getImg().length > 0) {
                try {
                    img.setFill(new ImagePattern(ImageUtils.byteArray2Image(obj.getImg())));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            addBtn.setText("O'zgartirish");
        }
    }
}
