package com.inventor.promaxcheckque.view;

import com.inventor.promaxcheckque.controllers.mainCtrl;
import com.inventor.promaxcheckque.dto.impls.subjectDAOimpls;
import com.inventor.promaxcheckque.dto.impls.teacherDAOImpls;
import com.inventor.promaxcheckque.entities.SubjectsEntity;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXCheckbox;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;

import static com.inventor.promaxcheckque.controllers.mainCtrl.subEditOption;

public class subjectNode {

    private GridPane subjectsGridPane;
    private styles bkgStyle = new styles();
    private AnchorPane popupBkg;
    private AnchorPane subjectChoicePane;
    private VBox subChoiceHbox;

    public subjectNode(GridPane subjects) {
        this.subjectsGridPane = subjects;
    }

    public subjectNode(AnchorPane subjectChoicePane, VBox subChoiceHbox) {
        this.subjectChoicePane = subjectChoicePane;
        this.subChoiceHbox = subChoiceHbox;
    }

    public void initSubjectNode(List<SubjectsEntity> subjects) {
        subjectsGridPane.getChildren().clear();
        int size = subjects.size();
        AnchorPane[] panes = new AnchorPane[size];
        int iterator = 0;
        for (SubjectsEntity o : subjects) {
            try {
                panes[iterator]= FXMLLoader.load(getClass().getResource("/subjectNode.fxml"));
                String bkg = bkgStyle.getBackground();
                panes[iterator].setStyle("-fx-background-color: " + bkg);
                for (Node p : panes[iterator].getChildren()) {
                    if (p instanceof Label) {
                        String id = p.getId();
                        if ("name".equals(id)) {
                            ((Label) p).setText(o.getName());
                        } else if ("countTeachers".equals(id)) {
                            long cntTeachers = teacherDAOImpls.getInstance().getTeachersCountOnSubject(o.getId());
                            ((Label) p).setText("o'qituvchilar soni:" + String.valueOf(cntTeachers));
                        }
                    } else if (p instanceof MFXButton) {
                        String id = p.getId();
                        if ("edit".equals(id)) {
                            ((MFXButton) p).setText(String.valueOf(o.getId()));
                            p.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent event) {
                                    long id  = Long.parseLong(((MFXButton) p).getText());
                                    SubjectsEntity obj = subjectDAOimpls.getInstance().get(id);
                                    mainCtrl.subObj = obj;
                                    subEditOption.initEditingSubject(obj);
                                }
                            });
                        } else if ("delete".equals(id)) {
                            ((MFXButton) p).setText(String.valueOf(o.getId()));
                            p.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent event) {
                                    subjectDAOimpls.getInstance().remove(Long.parseLong(((MFXButton) p).getText()));
                                    mainCtrl.subNode.initSubjectNode(subjectDAOimpls.getInstance().getAll());
                                }
                            });
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            iterator++;
        }
        fillScanGridPane(panes, size);
    }

    public void initSubjectChoiceBox(List<SubjectsEntity> subs, AnchorPane rootPane) {
        subjectChoicePane.setVisible(true);
        rootPane.setVisible(false);
        subChoiceHbox.getChildren().clear();
        for (SubjectsEntity p : subjectDAOimpls.getInstance().getAll()) {
            if (subs.contains(p)) {
                subChoiceHbox.getChildren().add(createCheckBox(p, true));
            } else {
                subChoiceHbox.getChildren().add(createCheckBox(p, false));
            }
        }

    }

    private MFXCheckbox createCheckBox(SubjectsEntity obj, boolean selection) {
        MFXCheckbox chBox = new MFXCheckbox();
        chBox.setText(obj.getName());
        chBox.setPrefSize(190,35);
        chBox.setSelected(selection);
        chBox.setStyle("-fx-font-family: Poppins_regular;-fx-font-size: 15px;-fx-font-weight: bold");
        chBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                mainCtrl.selectedSubjectsTeachers.add(obj);
            } else {
                mainCtrl.selectedSubjectsTeachers.remove(obj);
            }
        });
        return chBox;
    }

    public void fillScanGridPane(AnchorPane panes[], int sizeNode) {
        subjectsGridPane.getChildren().clear();

        double rows = sizeNode/2 + 1;
        for (int i = 1; i <= rows;i++) {
            subjectsGridPane.addRow(i);
        }
        int k = 0;
         try {
            for (int i = 0; i < rows;i++) {
                for (int j = 0;j < 2;j++) {
                    subjectsGridPane.add(panes[k], j, i);
                    k++;
                }
            }
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {

        }
    }
}
