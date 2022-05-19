package com.stuart.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class TableContragentController {

    @FXML
    private Button btnAddRecord;

    @FXML
    private Button btnEditRecord;

    @FXML
    private TextField txtSearch;

    @FXML
    private Button btnSearch;

    @FXML
    private TableView<?> tableSprContragent;

    @FXML
    private Label labelCount;

    @FXML
    void showDialogEdit(ActionEvent event) {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/createFormContragent.fxml"));
            stage.setTitle("Создать/редактировать запись Контрагент");
            stage.setMinWidth(600);
            stage.setMinHeight(400);
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node)event.getSource()).getScene().getWindow()); //получаем родительское окно - источник события
            stage.show();

            btnAddRecord.setText("Запись добавлена!");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
