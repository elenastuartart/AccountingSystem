package com.stuart.controllers.документРеализация;

import com.stuart.interfaces.impls.документы.реализация.HibernateDocSale;
import com.stuart.objectsFX.документы.ДокументРеализацияFX;
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

public class TableDocSaleController {
    @FXML
    private Button btnCreateDoc;
    @FXML
    private Button btnEditDoc;
    @FXML
    private CustomTextField txtSearch;
    @FXML
    private Button btnSearch;
    @FXML
    private TableView tableListDocSale;
    @FXML
    private TableColumn<ДокументРеализацияFX, String> columnNumber;
    @FXML
    private TableColumn<ДокументРеализацияFX, String> columnDate;
    @FXML
    private TableColumn<ДокументРеализацияFX, String> columnContragent;
    @FXML
    private TableColumn<ДокументРеализацияFX, String> columnSum;
    @FXML
    private TableColumn<ДокументРеализацияFX, String> columnProvedenie;
    @FXML
    private Label labelCount;

    private static final String FXML_EDIT = "/fxml/formDocSale.fxml";
    private HibernateDocSale listDocSaleImpl = new HibernateDocSale();
    private Parent fxmlEdit;
    private FXMLLoader fxmlLoader = new FXMLLoader();
    private FormDocSaleController formDocSaleController;
    private Stage editDialogStage;
    private Stage mainStage;

    @FXML
    private void initialize() throws SQLException {
        columnNumber.setCellValueFactory(new PropertyValueFactory<ДокументРеализацияFX, String>("number"));
        columnDate.setCellValueFactory(new PropertyValueFactory<ДокументРеализацияFX, String>("date"));
        columnContragent.setCellValueFactory(new PropertyValueFactory<ДокументРеализацияFX, String>("contragent"));
        columnSum.setCellValueFactory(new PropertyValueFactory<ДокументРеализацияFX, String>("finalSum"));
        columnProvedenie.setCellValueFactory(new PropertyValueFactory<ДокументРеализацияFX, String>("provedenie"));

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
        ДокументРеализацияFX selectedRecord = (ДокументРеализацияFX) tableListDocSale.getSelectionModel().getSelectedItem();
        Button clickedButton = (Button) source;

        boolean research = false;
        switch (clickedButton.getId()) {
            case "btnEditDoc" :
                editDoc(selectedRecord, false);
                break;
            case "btnCreateDoc":
                editDoc(new ДокументРеализацияFX(), true);
                break;
        }
        fillTable();
    }

    @FXML
    void actionSearch(ActionEvent event) throws SQLException {
        if ( txtSearch.getText().trim().length() == 0) {
            listDocSaleImpl.findAll();
        }
        else {
            listDocSaleImpl.findInt(Integer.valueOf(txtSearch.getText()));
        }

    }

    @FXML
    private void showDialog() {
        if(editDialogStage == null) {
            editDialogStage = new Stage();
            editDialogStage.setTitle("Создать/редактировать документ Реализация");
            editDialogStage.setMinWidth(600);
            editDialogStage.setMinHeight(400);
            editDialogStage.setResizable(false);
            editDialogStage.setScene(new Scene(fxmlEdit));
            editDialogStage.initModality(Modality.WINDOW_MODAL);
            editDialogStage.initOwner(mainStage); //получаем родительское окно - источник события
        }
        editDialogStage.showAndWait(); //ожидание закрытия окна
    }

    public void editDoc(ДокументРеализацияFX selectedDoc, boolean add) {
        if (!recordDocSaleIsSelected(selectedDoc)) {
            return;
        }
        try {
            initLoader();
            formDocSaleController.setДокументРеализацияFX(selectedDoc);
        } catch (Exception e) {
            e.printStackTrace();
        }

        showDialog();

        if(formDocSaleController.isSaveClicked()) {
            if (add)
                listDocSaleImpl.add(selectedDoc);
            else
                listDocSaleImpl.update(selectedDoc);
        }
    }

    private boolean recordDocSaleIsSelected(ДокументРеализацияFX selectedRecord) {
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
        ObservableList<ДокументРеализацияFX> list = listDocSaleImpl.findAll();
        tableListDocSale.setItems(list);
        tableListDocSale.refresh();
        updateCountLabel();
    }

    private void initListeners() {
        tableListDocSale.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount()==2) {
                    editDoc((ДокументРеализацияFX) tableListDocSale.getSelectionModel().getSelectedItem(), false);
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
            this.fxmlLoader.setLocation(getClass().getResource("/fxml/formDocSale.fxml"));
            fxmlEdit = fxmlLoader.load();
            formDocSaleController = fxmlLoader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    private void updateCountLabel() {
        labelCount.setText("Количество документов: "+ listDocSaleImpl.getDocSaleList().size());
    }

}
