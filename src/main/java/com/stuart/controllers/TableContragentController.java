package com.stuart.controllers;

import com.stuart.interfaces.impls.CollectionISpravochnikKA;
import com.stuart.models.entity.справочники.ЗаписьКонтрагент;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private TableView tableSprContragent;
    @FXML
    private TableColumn<ЗаписьКонтрагент, String> columnCode;
    @FXML
    private TableColumn<ЗаписьКонтрагент, String> columnName;
    @FXML
    private TableColumn<ЗаписьКонтрагент, String> columnAddress;
    @FXML
    private TableColumn<ЗаписьКонтрагент, String> columnTypeKA;
    @FXML
    private TableColumn<ЗаписьКонтрагент, String> columnContacts;
    @FXML
    private Label labelCount;

    private CollectionISpravochnikKA sprKAimpl = new CollectionISpravochnikKA();
    //    private Parent fxmlEdit;
//    private FXMLLoader fxmlLoader = new FXMLLoader();
//    private CreateFormContragentController editDialogController;
//    private Stage editDialogStage;

    @FXML
    private void initialize() {
        columnCode.setCellValueFactory(new PropertyValueFactory<ЗаписьКонтрагент, String>("code"));
        columnName.setCellValueFactory(new PropertyValueFactory<ЗаписьКонтрагент, String>("name"));
        columnTypeKA.setCellValueFactory(new PropertyValueFactory<ЗаписьКонтрагент, String>("type_KA"));
        columnAddress.setCellValueFactory(new PropertyValueFactory<ЗаписьКонтрагент, String>("address"));
        columnContacts.setCellValueFactory(new PropertyValueFactory<ЗаписьКонтрагент, String>("contact_person"));

        this.sprKAimpl.getСписокКонтрагентов().addListener(new ListChangeListener<ЗаписьКонтрагент>() {
            @Override
            public void onChanged(Change<? extends ЗаписьКонтрагент> c) {
                updateCountLabel();
            }
        });

        sprKAimpl.fillTestData();

        tableSprContragent.setItems(sprKAimpl.getСписокКонтрагентов());

//        try {
//            this.fxmlLoader.setLocation(getClass().getResource("/fxml/editDialogKA.fxml"));
//            fxmlEdit = fxmlLoader.load();
//            editDialogController = fxmlLoader.getController();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
//    @FXML
//    private void showDialog(Window parentWindow) {
//        if(editDialogStage == null) {
//            editDialogStage = new Stage();
//            editDialogStage.setTitle("Создать/редактировать запись Контрагент");
//            editDialogStage.setMinWidth(600);
//            editDialogStage.setMinHeight(400);
//            editDialogStage.setResizable(false);
//            editDialogStage.setScene(new Scene(fxmlEdit));
//            editDialogStage.initModality(Modality.WINDOW_MODAL);
//            editDialogStage.initOwner(parentWindow); //получаем родительское окно - источник события
//        }
//        editDialogStage.show();
//    }
    @FXML
    private void showDialog(ActionEvent actionEvent) {
//        получаем источник события
        Object source = actionEvent.getSource();
        //если нажата не кнопка выходим из метода
        if(!(source instanceof Button)) {
            return;
        }

        Button clickedButton = (Button) source;

        ЗаписьКонтрагент selectedKA = (ЗаписьКонтрагент) tableSprContragent.getSelectionModel().getSelectedItem();

        switch (clickedButton.getId()) {
            case "btnAddRecord":
                break;
            case  "btnEditRecord":
                break;
        }
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/editDialogKA.fxml"));
            stage.setTitle("Создать/редактировать запись Контрагент");
            stage.setMinWidth(600);
            stage.setMinHeight(400);
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateCountLabel() {
        labelCount.setText("Количество записей: "+ sprKAimpl.getСписокКонтрагентов().size());
    }

//    public void actionButtonPressed(ActionEvent actionEvent) {
//        //получаем источник события
//        Object source = actionEvent.getSource();
//        //если нажата не кнопка выходим из метода
//        if(!(source instanceof Button)) {
//            return;
//        }
//
//        Button clickedButton = (Button) source;
//
//        ЗаписьКонтрагент selectedKA = (ЗаписьКонтрагент) tableSprContragent.getSelectionModel().getSelectedItem();
//
//        Window parentWindow = ((Node) actionEvent.getSource()).getScene().getWindow();
//
//        editDialogController.setЗаписьКонтрагент(selectedKA);
//
//        switch (clickedButton.getId()) {
//            case "btnAddRecord":
//                break;
//            case  "btnEditRecord":
//                showDialog(parentWindow);
//                break;
//        }
//    }
}
