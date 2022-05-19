package com.stuart.controllers;

import com.stuart.models.entity.справочники.ЗаписьКонтрагент;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class HelloController{

    @FXML
    private TextField txtCode;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtTypeKA;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtContacts;

    @FXML
    private TableColumn<?, ?> tableColumnCode;

    @FXML
    private Tab txtSetFieldsContragent;

    @FXML
    void setCode(ActionEvent event) {

    }

    @FXML
    void onCreateNodeContragent(ActionEvent event) {
        ЗаписьКонтрагент записьКонтрагент = new ЗаписьКонтрагент();
        записьКонтрагент.setCode();
        String code = txtCode.getText();
        String resultCode = String.format(code);

    }


}