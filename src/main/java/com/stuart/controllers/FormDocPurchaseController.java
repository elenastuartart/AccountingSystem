package com.stuart.controllers;

import com.stuart.objectsFX.ЗаписьКонтрагентFX;
import com.stuart.utils.DialogManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class FormDocPurchaseController {

    @FXML
    private Button btnCreateRegisterAndClose;

    @FXML
    private Button btnWriteAndClose;

    @FXML
    private Button btnEditTabRecords;

    @FXML
    private DatePicker datePickerDate;

    @FXML
    private TextField txtNumber;

    @FXML
    private TextField txtContragent;

    @FXML
    private TextField txtSum;

    @FXML
    private TableView<?> tableTabPurchase;

    @FXML
    private TableColumn<?, ?> columnNumber;

    @FXML
    private TableColumn<?, ?> columnNomenclature;

    @FXML
    private TableColumn<?, ?> columnPrice;

    @FXML
    private TableColumn<?, ?> columnAmount;

    @FXML
    private TableColumn<?, ?> columnSum;

    @FXML
    void actionButtonPressed(ActionEvent event) {

    }

}
