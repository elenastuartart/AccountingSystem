package com.stuart.controllers;

import com.stuart.models.entity.справочники.ЗаписьКонтрагент;
import com.stuart.models.entity.справочники.ТестСпрКА;
import com.stuart.utils.DialogManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditDialogKAController {

    @FXML
    private TextField txtCode;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtTypeCA;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContacts;

    @FXML
    private Button btnSaveOrUpdate;

    @FXML
    private Button btnCansel;

    private ТестСпрКА записьКонтрагент;

    @FXML
    void actionClose(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.hide();
    }

    @FXML
    void actionSave(ActionEvent event) {
        if(!checkValues()) {
            return;
        }
        записьКонтрагент.setCode(Integer.valueOf(txtCode.getText()));
        записьКонтрагент.setName(txtName.getText());
        записьКонтрагент.setType_KA(txtTypeCA.getText());
        записьКонтрагент.setAddress(txtAddress.getText());
        записьКонтрагент.setContact_person(txtContacts.getText());
        actionClose(event);
    }

    public void setЗаписьКонтрагент(ТестСпрКА записьКонтрагент) {
        this.записьКонтрагент = записьКонтрагент;
        txtCode.setText(String.valueOf(записьКонтрагент.getCode()));
        txtName.setText(записьКонтрагент.getName());
        txtTypeCA.setText(записьКонтрагент.getType_KA());
        txtAddress.setText(записьКонтрагент.getAddress());
        txtContacts.setText(записьКонтрагент.getContact_person());
    }

    public ТестСпрКА getЗаписьКонтрагент() {
        return записьКонтрагент;
    }

    private boolean checkValues() {
        if(txtCode.getText().trim().length() == 0
                || txtName.getText().trim().length() == 0
                || txtTypeCA.getText().trim().length() == 0
                || txtAddress.getText().trim().length() == 0
                || txtContacts.getText().trim().length() == 0) {
            DialogManager.showInfoDialog("Ошибка", "Заполните все поля!");
            return false;
        }
        return true;
    }
}
