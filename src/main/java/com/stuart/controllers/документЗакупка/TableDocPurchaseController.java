package com.stuart.controllers.документЗакупка;

import com.stuart.interfaces.impls.документы.закупка.HibernateDocPurchase;
import com.stuart.objectsFX.документы.ДокументЗакупкаFX;
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

public class TableDocPurchaseController {
    @FXML
    private Button btnCreateDoc;
    @FXML
    private Button btnEditDoc;
    @FXML
    private CustomTextField txtSearch;
    @FXML
    private Button btnSearch;
    @FXML
    private TableView tableListDocPurchase;
    @FXML
    private TableColumn<ДокументЗакупкаFX, String> columnNumber;
    @FXML
    private TableColumn<ДокументЗакупкаFX, String> columnDate;
    @FXML
    private TableColumn<ДокументЗакупкаFX, String> columnContragent;
    @FXML
    private TableColumn<ДокументЗакупкаFX, String> columnSum;
    @FXML
    private TableColumn<ДокументЗакупкаFX, String> columnProvedenie;
    @FXML
    private Label labelCount;

    private static final String FXML_EDIT = "/fxml/formDocPurchase.fxml";
    private HibernateDocPurchase listDocPurchaseImpl = new HibernateDocPurchase();
    private Parent fxmlEdit;
    private FXMLLoader fxmlLoader = new FXMLLoader();
    private FormDocPurchaseController formDocPurchaseController;
    private Stage editDialogStage;
    private Stage mainStage;

    @FXML
    private void initialize() throws SQLException {
        columnNumber.setCellValueFactory(new PropertyValueFactory<ДокументЗакупкаFX, String>("number"));
        columnDate.setCellValueFactory(new PropertyValueFactory<ДокументЗакупкаFX, String>("date"));
        columnContragent.setCellValueFactory(new PropertyValueFactory<ДокументЗакупкаFX, String>("contragent"));
        columnSum.setCellValueFactory(new PropertyValueFactory<ДокументЗакупкаFX, String>("finalSum"));
        columnProvedenie.setCellValueFactory(new PropertyValueFactory<ДокументЗакупкаFX, String>("provedenie"));

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
        ДокументЗакупкаFX selectedRecord = (ДокументЗакупкаFX) tableListDocPurchase.getSelectionModel().getSelectedItem();
        Button clickedButton = (Button) source;

        boolean research = false;
        switch (clickedButton.getId()) {
            case "btnEditDoc" :
                editDoc(selectedRecord, false);
                break;
            case "btnCreateDoc":
                editDoc(new ДокументЗакупкаFX(), true);
                break;
        }
        fillTable();
    }

    @FXML
    void actionSearch(ActionEvent event) throws SQLException {
        if ( txtSearch.getText().trim().length() == 0) {
            listDocPurchaseImpl.findAll();
        }
        else {
            listDocPurchaseImpl.findInt(Integer.valueOf(txtSearch.getText()));
        }
    }

    @FXML
    private void showDialog() {
        if(editDialogStage == null) {
            editDialogStage = new Stage();
            editDialogStage.setTitle("Создать/редактировать документ Закупка");
            editDialogStage.setMinWidth(520);
            editDialogStage.setMinHeight(688);
            editDialogStage.setResizable(false);
            editDialogStage.setScene(new Scene(fxmlEdit));
            editDialogStage.initModality(Modality.WINDOW_MODAL);
            editDialogStage.initOwner(mainStage); //получаем родительское окно - источник события
        }
        editDialogStage.showAndWait(); //ожидание закрытия окна
    }

    public void editDoc(ДокументЗакупкаFX selectedDoc, boolean add) {
        if (!recordDocPurchaseIsSelected(selectedDoc)) {
            return;
        }
        try {
            initLoader();
            formDocPurchaseController.setДокументЗакупкаFX(selectedDoc);
        } catch (Exception e) {
            e.printStackTrace();
        }

        showDialog();

        if(formDocPurchaseController.isSaveClicked()) {
            if (add)
                listDocPurchaseImpl.add(selectedDoc);
            else
                listDocPurchaseImpl.update(selectedDoc);
        }
    }

    private boolean recordDocPurchaseIsSelected(ДокументЗакупкаFX selectedRecord) {
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
        ObservableList<ДокументЗакупкаFX> list = listDocPurchaseImpl.findAll();
        tableListDocPurchase.setItems(list);
        tableListDocPurchase.refresh();
        updateCountLabel();
    }

    private void initListeners() {
        tableListDocPurchase.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount()==2) {
                    editDoc((ДокументЗакупкаFX) tableListDocPurchase.getSelectionModel().getSelectedItem(), false);
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
            this.fxmlLoader.setLocation(getClass().getResource("/fxml/formDocPurchase.fxml"));
            fxmlEdit = fxmlLoader.load();
            formDocPurchaseController = fxmlLoader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    private void updateCountLabel() {
        labelCount.setText("Количество документов: "+listDocPurchaseImpl.getDocPurchaseList().size());
    }

}
