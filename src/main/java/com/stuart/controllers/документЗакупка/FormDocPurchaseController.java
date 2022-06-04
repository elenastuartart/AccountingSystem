package com.stuart.controllers.документЗакупка;

import com.stuart.interfaces.impls.документы.HibernateTabPurchase;
import com.stuart.interfaces.impls.справочники.HibernateSprKA;
import com.stuart.models.entity.документы.закупка.Закупка;
import com.stuart.models.entity.справочники.ЗаписьКонтрагент;
import com.stuart.models.entity.справочники.ЗаписьНоменклатура;
import com.stuart.objectsFX.документы.ДокументЗакупкаFX;
import com.stuart.objectsFX.документы.ТабЧастьЗакупкаFX;
import com.stuart.objectsFX.справочники.ЗаписьКонтрагентFX;
import com.stuart.objectsFX.справочники.ЗаписьНоменклатураFX;
import com.stuart.utils.DialogManager;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Struct;
import java.time.ZoneId;
import java.util.Date;

public class FormDocPurchaseController {

    @FXML
    private Button btnCreateRegisterAndClose;
    @FXML
    private Button btnWriteAndClose;
    @FXML
    private Button btnClose;
    @FXML
    private Button btnEditTabRecords;
    @FXML
    private Button btnAddRecords;
    @FXML
    private Button btnDeleteRecords;
    @FXML
    private DatePicker datePickerDate;
    @FXML
    private TextField txtNumber;
    @FXML
    private ChoiceBox<ЗаписьКонтрагентFX> choiseBoxContragent;
    @FXML
    private TextField txtSum;
    @FXML
    private TableView tableTabPurchase;
    @FXML
    private TableColumn<ТабЧастьЗакупкаFX, String> columnNumber;
    @FXML
    private TableColumn<ТабЧастьЗакупкаFX, String> columnNomenclature;
    @FXML
    private TableColumn<ТабЧастьЗакупкаFX, String> columnPrice;
    @FXML
    private TableColumn<ТабЧастьЗакупкаFX, String> columnAmount;
    @FXML
    private TableColumn<ТабЧастьЗакупкаFX, String> columnSum;

    private static final String FXML_EDIT = "/fxml/editTabPurchase.fxml";
    private HibernateTabPurchase listTabPurchaseImpl = new HibernateTabPurchase();
    private Parent fxmlEdit;
    private FXMLLoader fxmlLoader = new FXMLLoader();
    private EditTabPurchaseController editTabPurchaseController;
    private Stage editDialogStage;
    private Stage mainStage;

    private ДокументЗакупкаFX документЗакупкаFX;

    private boolean saveClicked = false;
    private HibernateSprKA hibernateSprKA = new HibernateSprKA();
    ЗаписьКонтрагент записьКонтрагент = new ЗаписьКонтрагент();


    @FXML
    private void showDialog() {
        if(editDialogStage == null) {
            editDialogStage = new Stage();
            editDialogStage.setTitle("Редактировать табличную часть документа Закупка");
            editDialogStage.setMinWidth(600);
            editDialogStage.setMinHeight(400);
            editDialogStage.setResizable(false);
            editDialogStage.setScene(new Scene(fxmlEdit));
            editDialogStage.initModality(Modality.WINDOW_MODAL);
            editDialogStage.initOwner(mainStage); //получаем родительское окно - источник события
        }
        editDialogStage.showAndWait(); //ожидание закрытия окна
    }

    private boolean recordTabPurchaseIsSelected(ТабЧастьЗакупкаFX selectedRecord) {
        if(selectedRecord == null) {
            DialogManager.showInfoDialog("Ошибка", "Выберите запись!");
            return  false;
        }
        return true;
    }

    @FXML
    private void initialize() throws SQLException {
        columnNumber.setCellValueFactory(new PropertyValueFactory<ТабЧастьЗакупкаFX, String>("numberStr"));
        columnNomenclature.setCellValueFactory(new PropertyValueFactory<ТабЧастьЗакупкаFX, String>("nomenclature"));
        columnPrice.setCellValueFactory(new PropertyValueFactory<ТабЧастьЗакупкаFX, String>("price"));
        columnAmount.setCellValueFactory(new PropertyValueFactory<ТабЧастьЗакупкаFX, String>("amount"));
        columnSum.setCellValueFactory(new PropertyValueFactory<ТабЧастьЗакупкаFX, String>("sum"));

        initLoader();
        initListeners();
    }

    public void fillTable() throws SQLException {
        ObservableList<ТабЧастьЗакупкаFX> list = listTabPurchaseImpl.findAll(документЗакупкаFX);
        tableTabPurchase.setItems(list);
        tableTabPurchase.refresh();
    }

    public void fillTableFromFX() {
        ObservableList <ТабЧастьЗакупкаFX> list = listTabPurchaseImpl.getTabPartPurchaseList();
        tableTabPurchase.setItems(list);
        tableTabPurchase.refresh();
    }

    private void initListeners() {
        tableTabPurchase.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getClickCount()==2) {
                    editStrTab((ТабЧастьЗакупкаFX) tableTabPurchase.getSelectionModel().getSelectedItem(), false);
                    fillTableFromFX();
                }
            }
        });
    }

    private void initLoader() {
        try {
            this.fxmlLoader.setLocation(getClass().getResource("/fxml/editTabPurchase.fxml"));
            fxmlEdit = fxmlLoader.load();
            editTabPurchaseController  = fxmlLoader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void editStrTab(ТабЧастьЗакупкаFX selectedRecord, boolean add) {
        if(!recordTabPurchaseIsSelected(selectedRecord)) {
            return;
        }
        try {
            editTabPurchaseController.setТабЧастьЗакупкаFX(selectedRecord);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        showDialog();

        if (editTabPurchaseController.isSaveClicked()) {
            if (add)
                listTabPurchaseImpl.getTabPartPurchaseList().add(selectedRecord);
        }
    }

    void deleteStrTab(ТабЧастьЗакупкаFX selectedRecord) {
        if(!recordTabPurchaseIsSelected(selectedRecord)) {
            return;
        }
        listTabPurchaseImpl.getTabPartPurchaseList().remove(selectedRecord);
    }

    @FXML
    void actionEditTabRecords(ActionEvent actionEvent) throws SQLException {
        Object source = actionEvent.getSource();
        if(!(source instanceof Button)) {
            return;
        }
        ТабЧастьЗакупкаFX selectedRecord = (ТабЧастьЗакупкаFX) tableTabPurchase.getSelectionModel().getSelectedItem();
        Button clickedButton = (Button) source;

        boolean research = false;
        switch (clickedButton.getId()) {
            case "btnEditTabRecords":
                editStrTab(selectedRecord,false);
                break;
            case  "btnAddRecords":
                editStrTab(new ТабЧастьЗакупкаFX(), true);
                break;
            case "btnDeleteRecords":
                deleteStrTab(selectedRecord);
                break;
        }
        fillTableFromFX();
    }

    @FXML
    void actionSaveAndClose(ActionEvent event) {
        if(!checkValues()) {
            return;
        }
        документЗакупкаFX.setNumber(Integer.valueOf(txtNumber.getText()));
        документЗакупкаFX.setDate(Date.from(datePickerDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        документЗакупкаFX.setFinalSum(Double.valueOf(txtSum.getText()));
        документЗакупкаFX.setContragent(choiseBoxContragent.getValue().getЗаписьКонтрагент_());
        документЗакупкаFX.setContragentFX_(choiseBoxContragent.getValue());
        документЗакупкаFX.setTabPartsFX_(listTabPurchaseImpl.getTabPartPurchaseList());
        saveClicked = true;

        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.hide();
    }

    @FXML
    void actionExit(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.hide();
    }

    @FXML
    void actionCreateRegister(ActionEvent event) {
        документЗакупкаFX.pometkaProvedenya = true;
        actionSaveAndClose(event);
    }

    @FXML
    void actionDeleteRegister(ActionEvent event) {
        документЗакупкаFX.pometkaProvedenya = false;
        actionSaveAndClose(event);
    }

    public void setДокументЗакупкаFX(ДокументЗакупкаFX документЗакупкаFX) throws SQLException {
        if (документЗакупкаFX == null){
            return;
        }

        saveClicked = false;
        this.документЗакупкаFX = документЗакупкаFX;
        txtNumber.setText(String.valueOf(документЗакупкаFX.getNumber()));
        datePickerDate.setValue(документЗакупкаFX.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        txtSum.setText(String.valueOf(документЗакупкаFX.getFinalSum()));
        tableTabPurchase.setItems(документЗакупкаFX.getTabPartsFX_());
        this.initChoiseBox();

        fillTable();

    }

    public ДокументЗакупкаFX getДокументЗакупкаFX() {
        return документЗакупкаFX;
    }

    private boolean checkValues() {
        if(txtNumber.getText().trim().length() == 0
                || txtSum.getText().trim().length() == 0
                || datePickerDate.getValue() == null
                || choiseBoxContragent.getValue() == null) {
            DialogManager.showInfoDialog("Ошибка", "Заполните все поля!");
            return false;
        }
        return true;
    }

    public boolean isSaveClicked() {
        return saveClicked;
    }

    private void initChoiseBox() throws SQLException {
        ObservableList<ЗаписьКонтрагентFX> contragentList = hibernateSprKA.findAll();

        ObservableList<ЗаписьКонтрагентFX> list = FXCollections.observableArrayList();
        for (int i = 0; i < contragentList.size(); i++) {
            list.add(contragentList.get(i));
        }

        this.choiseBoxContragent.setValue(this.документЗакупкаFX.getContragentFX_());

        this.choiseBoxContragent.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    try {
                        choiseBoxContragent.setItems(list);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    if (choiseBoxContragent.getValue() != null) {

                        Label label = new Label();
                        choiseBoxContragent.setOnAction(event1 ->
                        {
                            choiseBoxContragent.setValue(choiseBoxContragent.getValue());
                            документЗакупкаFX.setContragentFX_(choiseBoxContragent.getValue());
                        });
                    }
                }
            }
        });

    }


}
