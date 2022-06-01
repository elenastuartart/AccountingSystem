package com.stuart.controllers;

import com.stuart.interfaces.impls.HibernateDocPurchase;
import com.stuart.objectsFX.ДокументЗакупкаFX;
import com.stuart.objectsFX.ЗаписьНоменклатураFX;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.CustomTextField;

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
    private EditNomController editDialogController;
    private Stage editDialogStage;
    private Stage mainStage;

    @FXML
    void actionButtonPressed(ActionEvent event) {

    }

    @FXML
    private void initialize() throws SQLException {
        columnNumber.setCellValueFactory(new PropertyValueFactory<ДокументЗакупкаFX, String>("number"));
        columnDate.setCellValueFactory(new PropertyValueFactory<ДокументЗакупкаFX, String>("date"));
        columnContragent.setCellValueFactory(new PropertyValueFactory<ДокументЗакупкаFX, String>("contragent"));
        columnSum.setCellValueFactory(new PropertyValueFactory<ДокументЗакупкаFX, String>("finalSum"));
        columnProvedenie.setCellValueFactory(new PropertyValueFactory<ДокументЗакупкаFX, String>("provedenie"));

//        setupClearButtonField(txtSearch);
//        initListeners();
        fillTable();
//        initLoader();
    }

    private void fillTable() throws SQLException {
        ObservableList<ДокументЗакупкаFX> list = listDocPurchaseImpl.findAll();
        tableListDocPurchase.setItems(list);
    }

//    private void initListeners() {
//        this.listDocPurchaseImpl.getDocPurchaseList().addListener(new ListChangeListener<ДокументЗакупкаFX>() {
//            @Override
//            public void onChanged(Change<? extends ДокументЗакупкаFX> c) {
//                updateCountLabel();
//            }
//        });
//
//        tableListDocPurchase.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                if(event.getClickCount()==2) {
//                    try {
//                        editDialogController.setЗаписьНоменклатураFX(
//                                (ЗаписьНоменклатураFX) tableListDocPurchase.getSelectionModel().getSelectedItem());
//                    } catch (SQLException e) {
//                        e.printStackTrace();
//                    }
//                    showDialog();
//                    if (editDialogController.isSaveClicked()) {
//                        listDocPurchaseImpl.update((ЗаписьНоменклатураFX) tableListDocPurchase.getSelectionModel().getSelectedItem());
//                    }
//
//                }
//            }
//        });
//    }



    @FXML
    void actionSearch(ActionEvent event) {

    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

}
