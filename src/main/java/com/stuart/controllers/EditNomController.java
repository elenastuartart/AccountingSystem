package com.stuart.controllers;

import com.stuart.objectsFX.ЗаписьКонтрагентFX;
import com.stuart.objectsFX.ЗаписьНоменклатураFX;
import com.stuart.utils.DialogManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditNomController {
    @FXML
    private TextField txtCode;
    @FXML
    private TextField txtArticleNumber;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtCategory;
    @FXML
    private TextField txtSubcategory;
    @FXML
    private ChoiceBox<ЗаписьКонтрагентFX> txtProducer;
    @FXML
    private Button btnSaveOrUpdate;
    @FXML
    private Button btnCansel;

    private ЗаписьНоменклатураFX записьНоменклатураFX;

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
        записьНоменклатураFX.setCode(Integer.valueOf(txtCode.getText()));
        записьНоменклатураFX.setArticleNumber(Integer.valueOf(txtArticleNumber.getText()));
        записьНоменклатураFX.setName(txtName.getText());
        записьНоменклатураFX.setCategory(txtCategory.getText());
        записьНоменклатураFX.setSubcategory(txtSubcategory.getText());
//        контрагент
        saveClicked = true;
        actionClose(event);
    }

    public void setЗаписьНоменклатураFX(ЗаписьНоменклатураFX записьНоменклатураFX) {
        if (записьНоменклатураFX == null){
            return;
        }
        saveClicked = false;
        this.записьНоменклатураFX = записьНоменклатураFX;
        txtCode.setText(записьНоменклатураFX.getCode().toString());
        txtArticleNumber.setText(записьНоменклатураFX.getArticleNumber().toString());
        txtName.setText(записьНоменклатураFX.getName());
        txtCategory.setText(записьНоменклатураFX.getCategory());
        txtSubcategory.setText(записьНоменклатураFX.getSubcategory());
        //контрагент
    }

    public ЗаписьНоменклатураFX getЗаписьНоменклатураFX() {
        return записьНоменклатураFX;
    }

    private boolean checkValues() {
        if(txtCode.getText().trim().length() == 0
                || txtArticleNumber.getText().trim().length() == 0
                || txtName.getText().trim().length() == 0
                || txtCategory.getText().trim().length() == 0) {
            DialogManager.showInfoDialog("Ошибка", "Заполните все поля!");
            return false;
        }
        return true;
    }

    public boolean isSaveClicked() {
        return saveClicked;
    }
}
