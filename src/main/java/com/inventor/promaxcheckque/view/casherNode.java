package com.inventor.promaxcheckque.view;

import com.inventor.promaxcheckque.controllers.mainCtrl;
import com.inventor.promaxcheckque.dto.impls.cashersDAOImpls;
import com.inventor.promaxcheckque.entities.CashersEntity;
import com.inventor.promaxcheckque.utils.windowCtrl;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.util.List;

public class casherNode {

    private VBox casherVbox;

    public casherNode(VBox casherVbox) {
        this.casherVbox = casherVbox;
    }

    public void initCashersNode(List<CashersEntity> cashers) {
        casherVbox.getChildren().clear();
        int size = cashers.size();
        cashers.removeIf(e -> !e.equals(mainCtrl.activeUser));
         if (cashers.size() == 0) {
            cashers.addAll(cashersDAOImpls.getInstance().getAll());
        }
        AnchorPane[] panes = new AnchorPane[size];
        int iterator = 0;
        for (CashersEntity o : cashers) {
            try {
                panes[iterator] = FXMLLoader.load(getClass().getResource("/userNode.fxml"));
                for (Node p: panes[iterator].getChildren()) {
                    if (p instanceof Label) {
                        String id = p.getId();
                        if ("name".equals(id)) {
                            ((Label) p).setText(o.getName());
                        }
                    } else if (p instanceof Circle) {
                        String id = p.getId();
                        if ("accountImg".equals(id)) {
                            if (o.getImg() != null && o.getImg().length > 0) {
                                ((Circle) p).setFill(new ImagePattern(ImageUtils.byteArray2Image(o.getImg())));
                            }
                        }
                    } else if (p instanceof MFXButton) {
                        String id = p.getId();
                        if ("edit".equals(id)) {
                            ((MFXButton) p).setText(String.valueOf(o.getId()));
                            p.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent event) {
                                    mainCtrl.cashObj = cashersDAOImpls.getInstance().get(Long.parseLong(((MFXButton) p).getText()));
                                    mainCtrl.cashEdit.initEditingCasher(mainCtrl.cashObj);
                                }
                            });
                        } else if ("cancel".equals(id)) {
                            ((MFXButton) p).setText(String.valueOf(o.getId()));
                            p.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent event) {
                                    if (mainCtrl.activeUser.getId() == 9) {
                                        cashersDAOImpls.getInstance().remove(Long.parseLong(((MFXButton) p).getText()));
                                        mainCtrl.cashNode.initCashersNode(cashersDAOImpls.getInstance().getAll());
                                    } else {
                                        windowCtrl.makeToast("Bu amaliyot bajarilishi mumkin emas");
                                    }
                                }
                            });
                        }
                    }
                }
            } catch (IOException e) {

            }
            casherVbox.getChildren().add(panes[iterator]);
            iterator++;
        }
    }
}
