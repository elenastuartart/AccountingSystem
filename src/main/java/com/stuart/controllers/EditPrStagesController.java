package com.stuart.controllers;

import com.stuart.objectsFX.ЗаписьЭтапыПроизводстваFX;
import com.stuart.utils.DialogManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditPrStagesController {
    @FXML
    private TextField txtCode;

    @FXML
    private TextField txtName;

    @FXML
    private TextArea txtDescription;

    @FXML
    private Button btnSaveOrUpdate;

    @FXML
    private Button btnCansel;

    private ЗаписьЭтапыПроизводстваFX записьЭтапыПроизводстваFX;

    private boolean saveClicked = false;

    @FXML
    void actionClose(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.hide();
    }

    @FXML
    void actionSaveOrUpdate(ActionEvent event) {
        if(!checkValues()) {
            return;
        }
        записьЭтапыПроизводстваFX.setCode(Integer.valueOf(txtCode.getText()));
        записьЭтапыПроизводстваFX.setName(txtName.getText());
        записьЭтапыПроизводстваFX.setDescription_stage(txtDescription.getText());
        saveClicked = true;
        actionClose(event);
    }

    public void setЗаписьЭтапыПроизводстваFX(ЗаписьЭтапыПроизводстваFX записьЭтапыПроизводстваFX) {
        if (записьЭтапыПроизводстваFX==null) {
            return;
        }
        saveClicked = false;
        this.записьЭтапыПроизводстваFX = записьЭтапыПроизводстваFX;
        txtCode.setText(записьЭтапыПроизводстваFX.getCode().toString());
        txtName.setText(записьЭтапыПроизводстваFX.getName());
        txtDescription.setText(записьЭтапыПроизводстваFX.getDescription_stage());
    }

    public ЗаписьЭтапыПроизводстваFX getЗаписьЭтапыПроизводстваFX() {
        return записьЭтапыПроизводстваFX;
    }

    private boolean checkValues() {
        if(txtCode.getText().trim().length() == 0
                || txtName.getText().trim().length() == 0
                || txtDescription.getText().trim().length() == 0) {
            DialogManager.showInfoDialog("Ошибка", "Заполните все поля!");
            return false;
        }
        return true;
    }

    public boolean isSaveClicked() {
        return saveClicked;
    }
}
