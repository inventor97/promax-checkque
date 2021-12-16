package com.inventor.promaxcheckque.view;

import com.inventor.promaxcheckque.controllers.mainCtrl;
import com.inventor.promaxcheckque.dto.impls.checksDataDAOimpls;
import com.inventor.promaxcheckque.dto.impls.subjectDAOimpls;
import com.inventor.promaxcheckque.dto.impls.teacherDAOImpls;
import com.inventor.promaxcheckque.entities.ChecksDataEntity;
import com.inventor.promaxcheckque.entities.SubjectsEntity;
import com.inventor.promaxcheckque.entities.TeachersEntity;
import com.inventor.promaxcheckque.utils.dateUtils;
import com.inventor.promaxcheckque.utils.generateXlSXprinter;
import com.inventor.promaxcheckque.utils.windowCtrl;
import io.github.palexdev.materialfx.controls.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class paymentView {

    private MFXTextField fio;
    private MFXTextField amount;
    private AnchorPane subjectBox;
    private MFXRadioButton cash;
    private MFXRadioButton card;
    private MFXTextField cardHOlderTxt;
    private HBox cardHolderPane;
    private MFXTextField comment;
    private AnchorPane monthBox;
    private AnchorPane teacherBox;
    private AnchorPane popBkg;
    private AnchorPane subjectChoiceNode;
    private Label choiceLb;
    private MFXProgressSpinner spinner;
    private VBox subChoiceHbox;

    public paymentView(MFXTextField fio, MFXTextField amount, AnchorPane subjectBox, MFXRadioButton cash, MFXRadioButton card, MFXTextField cardHOlderTxt, HBox cardHolderPane, MFXTextField comment, AnchorPane monthBox, AnchorPane teacherBox, AnchorPane popBkg, AnchorPane subjectChoiceNode, Label choiceLb, MFXProgressSpinner spinner, VBox subChoiceHbox) {
        this.fio = fio;
        this.amount = amount;
        this.subjectBox = subjectBox;
        this.cash = cash;
        this.card = card;
        this.cardHOlderTxt = cardHOlderTxt;
        this.cardHolderPane = cardHolderPane;
        this.comment = comment;
        this.monthBox = monthBox;
        this.teacherBox = teacherBox;
        this.popBkg = popBkg;
        this.subjectChoiceNode = subjectChoiceNode;
        this.choiceLb = choiceLb;
        this.spinner = spinner;
        this.subChoiceHbox = subChoiceHbox;
        initTxtRestr();
        initRadioBtnListener();
    }

    public Task<Void> initRecordTask() {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                recordCheck();
                return null;
            }
        };
        task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                windowCtrl.makeToast("Malumot saqlandi");
                fio.setText("");
                cash.setSelected(false);
                card.setSelected(false);
                teacherBox.getChildren().clear();
                monthBox.getChildren().clear();
                subjectBox.getChildren().clear();
                amount.setText("");
                comment.setText("");
                popBkg.setVisible(false);
                spinner.setVisible(false);
            }
        });
        return task;
    }

    private void initTxtRestr() {
        amount.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*(\\.\\d*)?")) {
                    amount.setText(oldValue);
                }
            }
        });
    }

    private void initRadioBtnListener() {
        card.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                cardHolderPane.setVisible(true);
            } else {
                cardHolderPane.setVisible(false);
            }
        });
    }

    public void addTeacherBox(List<TeachersEntity> teachers) {
        teacherBox.getChildren().clear();
        int size = teachers.size();
        AnchorPane[] nodes = new AnchorPane[size];
        int iterator = 0;
        for (TeachersEntity o : teachers) {
            try {
                nodes[iterator] = FXMLLoader.load(getClass().getResource("/userNode.fxml"));
                for (Node p : nodes[iterator].getChildren()) {
                    if (p instanceof Label) {
                        String id = p.getId();
                        if ("name".equals(id)) {
                            ((Label) p).setText(o.getName());
                        }
                    } else if (p instanceof  MFXButton) {
                        String id = p.getId();
                        if ("cancel".equals(id)) {
                            ((MFXButton) p).setText(String.valueOf(o.getId()));
                            p.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent event) {
                                    TeachersEntity obj = teacherDAOImpls
                                            .getInstance()
                                            .get(Long
                                                    .parseLong(((MFXButton) p)
                                                            .getText()));
                                    mainCtrl.selectedTeacherForPay.removeIf(e -> e.equals(obj));
                                    mainCtrl.payView.addTeacherBox(mainCtrl.selectedTeacherForPay);
                                    System.out.println("falnasdfjknsd");
                                }
                            });
                        }
                    } else if (p instanceof Circle) {
                        try {
                            ((Circle) p).setFill(new ImagePattern(ImageUtils.byteArray2Image(o.getImg())));
                        } catch (Exception e) {
                            ((Circle) p).setFill(Paint.valueOf("#e5e5e5"));
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            iterator++;
        }
        for (int i = 0; i < nodes.length; i++) {
            nodes[i].setLayoutX(i*10);
            teacherBox.getChildren().add(nodes[i]);
        }
    }

    public void addSubjectBox(List<SubjectsEntity> subs) {
        subjectBox.getChildren().clear();
        List<AnchorPane> nodes = new ArrayList<>();
        int itr = 0;
        for (SubjectsEntity o : subs) {
            try {
                nodes.add(FXMLLoader.load(getClass().getResource("/monthNode.fxml")));
                nodes.get(itr).setStyle("-fx-background-color: linear-gradient(to bottom right, #FF5F6D, #FFC371)");
                for (Node p : nodes.get(itr).getChildren()) {
                    if (p instanceof Label) {
                        String id = p.getId();
                        if ("name".equals(id)) {
                            ((Label) p).setText(o.getName());
                        }
                    } else if (p instanceof MFXButton) {
                        if ("cancel".equals(p.getId())) {
                            ((MFXButton) p).setText(String.valueOf(o.getId()));
                            p.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent event) {
                                    SubjectsEntity obj = subjectDAOimpls.getInstance()
                                            .get(Long.parseLong(((MFXButton) p).getText()));
                                    mainCtrl.selecedSubjects.removeIf(e -> e.equals(obj));
                                    mainCtrl.payView.addSubjectBox(mainCtrl.selecedSubjects);
                                }
                            });
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            itr++;
        }
        for (int i = 0; i < nodes.size(); i++) {
            nodes.get(i).setLayoutX(i*10);
            subjectBox.getChildren().add(nodes.get(i));
        }
    }

    public void addMonthBox(List<String> months) {
        monthBox.getChildren().clear();
        List<AnchorPane> nodes = new ArrayList<>();
        int itr = 0;
        for (String o : months) {
            try {
                nodes.add(FXMLLoader.load(getClass().getResource("/monthNode.fxml")));
                for (Node p : nodes.get(itr).getChildren()) {
                    if (p instanceof Label) {
                        String id = p.getId();
                        if ("name".equals(id)) {
                            ((Label) p).setText(o);
                        }
                    } else if (p instanceof MFXButton) {
                        if ("cancel".equals(p.getId())) {
                            ((MFXButton) p).setText(o);
                            p.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent event) {
                                    mainCtrl.selecedMonths.removeIf(e -> e.equals(o));
                                    mainCtrl.payView.addMonthBox(mainCtrl.selecedMonths);
                                }
                            });
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            itr++;
        }
        for (int i = 0; i < nodes.size(); i++) {
            nodes.get(i).setLayoutX(i*10);
            monthBox.getChildren().add(nodes.get(i));
        }
    }

    public boolean checkForCorrection() {
        try {
            if (!fio.getText().equals("")) {
                if (!amount.getText().equals("")) {
                    if (cash.isSelected() || card.isSelected()) {
                        if (mainCtrl.selecedMonths.size() > 0 && mainCtrl.selectedTeacherForPay.size() > 0) {
                            if (!comment.getText().equals("")) {
                                if (mainCtrl.selecedSubjects.size() > 0 ) {
                                    if (card.isSelected()) {
                                        if (!cardHOlderTxt.getText().equals("")) {
                                            return true;
                                        } else {
                                            windowCtrl.makeToast("Karta egasini kiriting.");
                                            return false;
                                        }
                                    } else {
                                        return true;
                                    }
                                } else {
                                    windowCtrl.makeToast("O'quv fanini tanlang");
                                    return false;
                                }
                            } else {
                                windowCtrl.makeToast("Izoh kiritilamadi");
                                return false;
                            }
                        } else {
                            windowCtrl.makeToast("To'lov oyi yoki O'qituvchi tanlanmadi");
                            return false;
                        }
                    } else {
                        windowCtrl.makeToast("To'lov turini tanlang");
                        return false;
                    }
                } else  {
                    windowCtrl.makeToast("To'lovni kiriting");
                    return false;
                }
            } else {
                windowCtrl.makeToast("O'quvchi nomini kiriting");
                return false;
            }
        } catch (NullPointerException e) {
            windowCtrl.makeToast("Ma'lumotlar yetarli emas");
            return false;
        }
    }

    public void recordCheck() {
        StringBuilder subIds = new StringBuilder();
        for (SubjectsEntity o : mainCtrl.selecedSubjects) {
            subIds.append(o.getName()).append(",");
        }
        final String[] teachers = {""};
        mainCtrl.selectedTeacherForPay.forEach(e -> {
            teachers[0] += e.getName() + ",";
        });
        final String[] months = {""};
        mainCtrl.selecedMonths.forEach(e -> {
            months[0] += e + ",";
        });
        Date dateCreated = new Date(new java.util.Date().getTime());
        try {
            ChecksDataEntity obj = new ChecksDataEntity(fio.getText(),
                    Long.parseLong(amount.getText()), cash.isSelected(),
                    mainCtrl.activeUser.getId(), teachers[0], subIds.toString(),
                    comment.getText(), dateCreated, months[0], cash.isSelected() ? "" : cardHOlderTxt.getText());
            checksDataDAOimpls.getInstance().add(obj);
            generateXlSXprinter.saveSoldCheck(obj);
            mainCtrl.selecedMonths.clear();
            mainCtrl.selectedTeacherForPay.clear();
            mainCtrl.selecedSubjects.clear();
        } catch (Exception e) {
            e.printStackTrace();
            windowCtrl.makeToast("Ma'lumotlar yuklanishida xatolik");
        }
    }

    public void initSubjectChoice() {
        popBkg.setVisible(true);
        subjectChoiceNode.setVisible(true);
        choiceLb.setText("Fanni tanlang");
        subChoiceHbox.getChildren().clear();
        for (SubjectsEntity o : subjectDAOimpls.getInstance().getAll()) {
            if (mainCtrl.selecedSubjects.contains(o)) {
                subChoiceHbox.getChildren().add(createSubjectCheckBox(o, true));
            } else {
                subChoiceHbox.getChildren().add(createSubjectCheckBox(o, false));
            }
        }
    }

    public void initTeacherChoice() {
        popBkg.setVisible(true);
        subjectChoiceNode.setVisible(true);
        choiceLb.setText("O'qituvchini tanlang");
        subChoiceHbox.getChildren().clear();
        for (TeachersEntity o : teacherDAOImpls.getInstance().getAll()) {
            if (mainCtrl.selectedTeacherForPay.contains(o)) {
                subChoiceHbox.getChildren().add(createTeacherCheckBox(o, true));
            } else {
                subChoiceHbox.getChildren().add(createTeacherCheckBox(o, false));
            }
        }
    }

    public void initMonthChoice() {
        popBkg.setVisible(true);
        subjectChoiceNode.setVisible(true);
        choiceLb.setText("Oyni tanlang");
        subChoiceHbox.getChildren().clear();
        for (String o : dateUtils.getMonths()) {
            if (mainCtrl.selecedMonths.contains(o)) {
                subChoiceHbox.getChildren().add(createMonthCheckBox(o, true));
            } else {
                subChoiceHbox.getChildren().add(createMonthCheckBox(o, false));
            }
        }
    }

    private MFXCheckbox createSubjectCheckBox(SubjectsEntity obj, boolean selection) {
        MFXCheckbox chBox = new MFXCheckbox();
        chBox.setText(obj.getName());
        chBox.setPrefSize(Region.USE_COMPUTED_SIZE,35);
        chBox.setSelected(selection);
        chBox.setStyle("-fx-font-family: Poppins_regular;-fx-font-size: 15px;-fx-font-weight: bold");
        chBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                mainCtrl.selecedSubjects.add(obj);
            }else {
                mainCtrl.selecedSubjects.remove(obj);
            }
        });
        return chBox;
    }

    private MFXCheckbox createTeacherCheckBox(TeachersEntity obj, boolean selection) {
        MFXCheckbox chBox = new MFXCheckbox();
        chBox.setText(obj.getName());
        chBox.setPrefSize(Region.USE_COMPUTED_SIZE,35);
        chBox.setSelected(selection);
        chBox.setStyle("-fx-font-family: Poppins_regular;-fx-font-size: 15px;-fx-font-weight: bold");
        chBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                mainCtrl.selectedTeacherForPay.add(obj);
            } else {
                mainCtrl.selectedTeacherForPay.remove(obj);
            }
        });
        return chBox;
    }

    private MFXCheckbox createMonthCheckBox(String obj, boolean selection) {
        MFXCheckbox chBox = new MFXCheckbox();
        chBox.setText(obj);
        chBox.setPrefSize(190,35);
        chBox.setSelected(selection);
        chBox.setStyle("-fx-font-family: Poppins_regular;-fx-font-size: 15px;-fx-font-weight: bold");
        chBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                mainCtrl.selecedMonths.add(obj);
            } else {
                mainCtrl.selecedMonths.remove(obj);
            }
        });
        return chBox;
    }

}
