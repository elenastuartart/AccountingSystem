package com.stuart.controllers.документПроизводство;

import com.stuart.interfaces.impls.документы.производство.HibernateDocManufacture;
import com.stuart.objectsFX.документы.ДокументЗакупкаFX;
import com.stuart.objectsFX.документы.ДокументПроизводствоFX;
import com.stuart.utils.DialogManager;
import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.CustomTextField;
import org.controlsfx.control.textfield.TextFields;

import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.SQLException;

public class TableDocManufactureController {

    @FXML
    private Button btnCreateDoc;
    @FXML
    private Button btnEditDoc;
    @FXML
    private CustomTextField txtSearch;
    @FXML
    private Button btnSearch;
    @FXML
    private TableView tableListDocManufacture;
    @FXML
    private TableColumn<ДокументПроизводствоFX, String> columnNumber;
    @FXML
    private TableColumn<ДокументПроизводствоFX, String> columnDate;
    @FXML
    private TableColumn<ДокументПроизводствоFX, String> columnProvedenie;
    @FXML
    private Label labelCount;

    private static final String FXML_EDIT = "/fxml/formDocManufacture.fxml";
    private HibernateDocManufacture listDocManufactureImpl = new HibernateDocManufacture();
    private Parent fxmlEdit;
    private FXMLLoader fxmlLoader = new FXMLLoader();
    private FormDocManufactureController formDocManufactureController;
    private Stage editDialogStage;
    private Stage mainStage;

    @FXML
    private void initialize() throws SQLException {
        columnNumber.setCellValueFactory(new PropertyValueFactory<ДокументПроизводствоFX, String>("number"));
        columnDate.setCellValueFactory(new PropertyValueFactory<ДокументПроизводствоFX, String>("date"));
        columnProvedenie.setCellValueFactory(new PropertyValueFactory<ДокументПроизводствоFX, String>("provedenie"));

        setupClearButtonField(txtSearch);
        initListeners();
        fillTable();
    }

    @FXML
    void actionButtonPressed(ActionEvent actionEvent) throws SQLException {
        Object source = actionEvent.getSource();
        if(!(source instanceof Button)) {
            return;
        }
        ДокументПроизводствоFX selectedRecord = (ДокументПроизводствоFX) tableListDocManufacture.getSelectionModel().getSelectedItem();
        Button clickedButton = (Button) source;

        boolean research = false;
        switch (clickedButton.getId()) {
            case "btnEditDoc" :
                editDoc(selectedRecord, false);
                break;
            case "btnCreateDoc":
                editDoc(new ДокументПроизводствоFX(), true);
                break;
        }
        fillTable();
    }

    @FXML
    void actionSearch(ActionEvent event) throws SQLException {
        if ( txtSearch.getText().trim().length() == 0) {
            listDocManufactureImpl.findAll();
        }
        else {
            listDocManufactureImpl.findInt(Integer.valueOf(txtSearch.getText()));
        }
    }

    @FXML
    private void showDialog() {
        if(editDialogStage == null) {
            editDialogStage = new Stage();
            editDialogStage.setTitle("Создать/редактировать документ Производство");
            editDialogStage.setMinWidth(600);
            editDialogStage.setMinHeight(400);
            editDialogStage.setResizable(false);
            editDialogStage.setScene(new Scene(fxmlEdit));
            editDialogStage.initModality(Modality.WINDOW_MODAL);
            editDialogStage.initOwner(mainStage); //получаем родительское окно - источник события
        }
        editDialogStage.showAndWait(); //ожидание закрытия окна
    }

    public void editDoc(ДокументПроизводствоFX selectedDoc, boolean add) {
        if (!recordDocIsSelected(selectedDoc)) {
            return;
        }
        try {
            initLoader();
            formDocManufactureController.setДокументПроизводствоFX(selectedDoc);
        } catch (Exception e) {
            e.printStackTrace();
        }

        showDialog();

        if(formDocManufactureController.isSaveClicked()) {
            if (add)
                listDocManufactureImpl.add(selectedDoc);
            else
                listDocManufactureImpl.update(selectedDoc);
        }
    }

    private boolean recordDocIsSelected(ДокументПроизводствоFX selectedRecord) {
        if(selectedRecord == null) {
            DialogManager.showInfoDialog("Ошибка", "Выберите запись!");
            return  false;
        }
        return true;
    }

    private void setupClearButtonField(CustomTextField customTextField) {
        try {
            Method m = TextFields.class.getDeclaredMethod("setupClearButtonField", TextField.class, ObjectProperty.class);
            m.setAccessible(true);
            m.invoke(null, customTextField, customTextField.rightProperty());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void fillTable() throws SQLException {
        ObservableList<ДокументПроизводствоFX> list = listDocManufactureImpl.findAll();
        tableListDocManufacture.setItems(list);
        tableListDocManufacture.refresh();
    }

    private void initListeners() {
        tableListDocManufacture.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount()==2) {
                    editDoc((ДокументПроизводствоFX) tableListDocManufacture.getSelectionModel().getSelectedItem(), false);
                    try {
                        fillTable();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void initLoader() {
        try {
            this.fxmlLoader.setLocation(getClass().getResource("/fxml/formDocManufacture.fxml"));
            fxmlEdit = fxmlLoader.load();
            formDocManufactureController = fxmlLoader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    private void updateCountLabel() {
        labelCount.setText("Количество записей: "+listDocManufactureImpl.getDocManufactureList().size());
    }

}
