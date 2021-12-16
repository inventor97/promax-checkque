package com.inventor.promaxcheckque.view;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class NavButtons {

    private MFXButton teacherBtn;
    private MFXButton paymentBtn;
    private MFXButton hisBtn;
    private MFXButton expensesBtn;
    private ImageView teacherIcon;
    private ImageView paymentIcon;
    private ImageView hisIcon;
    private ImageView expensesIcon;

    public NavButtons(MFXButton teacherBtn, MFXButton paymentBtn, MFXButton hisBtn, MFXButton expensesBtn, ImageView teacherIcon, ImageView paymentIcon, ImageView hisIcon, ImageView expensesIcon) {
        this.teacherBtn = teacherBtn;
        this.paymentBtn = paymentBtn;
        this.hisBtn = hisBtn;
        this.expensesBtn = expensesBtn;
        this.teacherIcon = teacherIcon;
        this.paymentIcon = paymentIcon;
        this.hisIcon = hisIcon;
        this.expensesIcon = expensesIcon;
    }

    public void setFocus(ActionEvent event) {
        if (event.getSource() == teacherBtn) {
            setTransparentBkg();
            setDefaultNavBtnIcons();
            teacherBtn.setStyle(styles.NAV_BUTTON);
            teacherIcon.setImage(new Image("data_recovery_96px_fill.png", 30, 30, false, false));
        } else if (event.getSource() == paymentBtn) {
            setTransparentBkg();
            setDefaultNavBtnIcons();
            paymentBtn.setStyle(styles.NAV_BUTTON);
            paymentIcon.setImage(new Image("check_fill.png", 30, 30, false, false));
        } else if (event.getSource() == hisBtn) {
            setTransparentBkg();
            setDefaultNavBtnIcons();
            hisBtn.setStyle(styles.NAV_BUTTON);
            hisIcon.setImage(new Image("order_history_96px _fill.png", 30, 30, false, false));
        } else if (event.getSource() == expensesBtn) {
            setTransparentBkg();
            setDefaultNavBtnIcons();
            expensesBtn.setStyle(styles.NAV_BUTTON);
            expensesIcon.setImage(new Image("ledger_fill.png", 30, 30, false, false));
        }
    }

    public void setTransparentBkg() {
        teacherBtn.setStyle("-fx-background-color: transparent");
        paymentBtn.setStyle("-fx-background-color: transparent");
        hisBtn.setStyle("-fx-background-color: transparent");
        expensesBtn.setStyle("-fx-background-color: transparent");
    }

    public void setDefaultNavBtnIcons() {
        teacherIcon.setImage(new Image("data_recovery_96px.png", 30,30,false, false));
        paymentIcon.setImage(new Image("check.png", 30,30,false, false));
        hisIcon.setImage(new Image("order_history_96px.png", 30,30,false, false));
        expensesIcon.setImage(new Image("ledger_96px.png", 30, 30, false, false));
    }
}
