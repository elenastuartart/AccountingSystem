package com.stuart.controllers;

import com.stuart.models.entity.справочники.ЗаписьКонтрагент;
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
    private Label txtTypeCA;
    @FXML
    private Label txtAddress;
    @FXML
    private Label txtContacts;
    @FXML
    private Button btnSaveOrUpdate;
    @FXML
    private Button btnCansel;

    private ЗаписьКонтрагент записьКонтрагент;

    @FXML
    void actionClose(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    void actionSave(ActionEvent event) {
//        записьКонтрагент.setCode(Integer.valueOf(new String(String.valueOf(txtCode.getText()))));
//        записьКонтрагент.setName(txtName.getText());
//        записьКонтрагент.setType_KA(txtTypeCA.getText());
//        записьКонтрагент.setAddress(txtAddress.getText());
//        записьКонтрагент.setContact_person(txtContacts.getText());
//        actionClose(event);
    }

    public void setЗаписьКонтрагент(ЗаписьКонтрагент записьКонтрагент) {
        this.записьКонтрагент = записьКонтрагент;
        txtContacts.setText(new String(String.valueOf(записьКонтрагент.getCode())));
        txtName.setText(записьКонтрагент.getName());
        txtTypeCA.setText(записьКонтрагент.getType_KA());
        txtAddress.setText(записьКонтрагент.getAddress());
        txtContacts.setText(записьКонтрагент.getContact_person());
    }

}
