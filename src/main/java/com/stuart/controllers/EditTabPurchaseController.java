package com.stuart.controllers;

import com.stuart.objectsFX.ЗаписьКонтрагентFX;
import com.stuart.utils.DialogManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditTabPurchaseController {

    @FXML
    private TextField txtNumberStr;

    @FXML
    private ChoiceBox<?> choiseNomenclature;

    @FXML
    private TextField txtAmount;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtSum;

    @FXML
    private Button btnSaveOrUpdate;

    @FXML
    private Button btnCansel;

    @FXML
    void actionClose(ActionEvent event) {

    }

    @FXML
    void actionSaveOrUpdate(ActionEvent event) {

    }
}
