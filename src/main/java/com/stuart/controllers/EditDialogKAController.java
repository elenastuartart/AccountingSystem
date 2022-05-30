package com.stuart.controllers;

import com.stuart.objects.ЗаписьКонтрагентFX;
import com.stuart.utils.DialogManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
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

    private ЗаписьКонтрагентFX записьКонтрагентFX;

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
        записьКонтрагентFX.setCode(Integer.valueOf(txtCode.getText()));
        записьКонтрагентFX.setName(txtName.getText());
        записьКонтрагентFX.setType_KA(txtTypeCA.getText());
        записьКонтрагентFX.setAddress(txtAddress.getText());
        записьКонтрагентFX.setContact_person(txtContacts.getText());
        saveClicked = true;
        actionClose(event);
    }

    public void setЗаписьКонтрагентFX(ЗаписьКонтрагентFX записьКонтрагентFX) {
        if (записьКонтрагентFX == null){
            return;
        }
        saveClicked = false;
        this.записьКонтрагентFX = записьКонтрагентFX;
        txtCode.setText(записьКонтрагентFX.getCode().toString());
        txtName.setText(записьКонтрагентFX.getName());
        txtTypeCA.setText(записьКонтрагентFX.getType_KA());
        txtAddress.setText(записьКонтрагентFX.getAddress());
        txtContacts.setText(записьКонтрагентFX.getContact_person());
    }

    public ЗаписьКонтрагентFX getЗаписьКонтрагентFX() {
        return записьКонтрагентFX;
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

    public boolean isSaveClicked() {
        return saveClicked;
    }
}
