package com.stuart.controllers;

import com.stuart.models.entity.справочники.ЗаписьКонтрагент;
import com.stuart.models.entity.справочники.ТестСпрКА;
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
        записьКонтрагент.setCode(txtCode.getText());
        записьКонтрагент.setName(txtName.getText());
        записьКонтрагент.setType_KA(txtTypeCA.getText());
        записьКонтрагент.setAddress(txtAddress.getText());
        записьКонтрагент.setContact_person(txtContacts.getText());
        actionClose(event);
    }

    public void setЗаписьКонтрагент(ТестСпрКА записьКонтрагент) {
        this.записьКонтрагент = записьКонтрагент;
        txtCode.setText(записьКонтрагент.getCode());
        txtName.setText(записьКонтрагент.getName());
        txtTypeCA.setText(записьКонтрагент.getType_KA());
        txtAddress.setText(записьКонтрагент.getAddress());
        txtContacts.setText(записьКонтрагент.getContact_person());
    }

    public ТестСпрКА getЗаписьКонтрагент() {
        return записьКонтрагент;
    }
}
