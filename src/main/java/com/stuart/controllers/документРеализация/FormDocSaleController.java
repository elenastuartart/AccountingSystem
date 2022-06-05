package com.stuart.controllers.документРеализация;

import com.stuart.interfaces.impls.документы.реализация.HibernateTabSale;
import com.stuart.interfaces.impls.справочники.HibernateSprKA;
import com.stuart.models.entity.справочники.ЗаписьКонтрагент;
import com.stuart.objectsFX.документы.ДокументРеализацияFX;
import com.stuart.objectsFX.документы.ТабЧастьРеализацияFX;
import com.stuart.objectsFX.справочники.ЗаписьКонтрагентFX;
import com.stuart.utils.DialogManager;
import javafx.collections.FXCollections;
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
import java.time.ZoneId;
import java.util.Date;

public class FormDocSaleController {

    @FXML
    private Button btnCreateRegisterAndClose;
    @FXML
    private Button btnWriteAndClose;
    @FXML
    private Button btnClose;
    @FXML
    private TextField txtNumber;
    @FXML
    private TextField txtSum;
    @FXML
    private DatePicker datePickerDate;
    @FXML
    private ChoiceBox<ЗаписьКонтрагентFX> choiseBoxContragent;
    @FXML
    private Button btnEditTabRecords;
    @FXML
    private Button btnAddRecords;
    @FXML
    private Button btnDeleteRecords;
    @FXML
    private TableView tableTabSale;
    @FXML
    private TableColumn<ТабЧастьРеализацияFX, String> columnNumber;
    @FXML
    private TableColumn<ТабЧастьРеализацияFX, String> columnNomenclature;
    @FXML
    private TableColumn<ТабЧастьРеализацияFX, String> columnAmount;
    @FXML
    private TableColumn<ТабЧастьРеализацияFX, String> columnPrice;
    @FXML
    private TableColumn<ТабЧастьРеализацияFX, String> columnSum;

    private static final String FXML_EDIT = "/fxml/editTabSale.fxml";
    private HibernateTabSale listTabSaleImpl = new HibernateTabSale();
    private Parent fxmlEdit;
    private FXMLLoader fxmlLoader = new FXMLLoader();
    private EditTabSaleController editTabSaleController;
    private Stage editDialogStage;
    private Stage mainStage;

    private ДокументРеализацияFX документРеализацияFX;

    private boolean saveClicked = false;
    private HibernateSprKA hibernateSprKA = new HibernateSprKA();
    ЗаписьКонтрагент записьКонтрагент = new ЗаписьКонтрагент();

    @FXML
    private void showDialog() {
        if(editDialogStage == null) {
            editDialogStage = new Stage();
            editDialogStage.setTitle("Редактировать табличную часть документа Реализация");
            editDialogStage.setMinWidth(600);
            editDialogStage.setMinHeight(400);
            editDialogStage.setResizable(false);
            editDialogStage.setScene(new Scene(fxmlEdit));
            editDialogStage.initModality(Modality.WINDOW_MODAL);
            editDialogStage.initOwner(mainStage); //получаем родительское окно - источник события
        }
        editDialogStage.showAndWait(); //ожидание закрытия окна
    }

    private boolean recordTabSaleIsSelected(ТабЧастьРеализацияFX selectedRecord) {
        if(selectedRecord == null) {
            DialogManager.showInfoDialog("Ошибка", "Выберите запись!");
            return  false;
        }
        return true;
    }

    @FXML
    private void initialize() throws SQLException {
        columnNumber.setCellValueFactory(new PropertyValueFactory<ТабЧастьРеализацияFX, String>("numberStr"));
        columnNomenclature.setCellValueFactory(new PropertyValueFactory<ТабЧастьРеализацияFX, String>("nomenclature"));
        columnPrice.setCellValueFactory(new PropertyValueFactory<ТабЧастьРеализацияFX, String>("price"));
        columnAmount.setCellValueFactory(new PropertyValueFactory<ТабЧастьРеализацияFX, String>("amount"));
        columnSum.setCellValueFactory(new PropertyValueFactory<ТабЧастьРеализацияFX, String>("sum"));

        initLoader();
        initListeners();
    }

    public void fillTable() throws SQLException {
        ObservableList<ТабЧастьРеализацияFX> list = listTabSaleImpl.findAll(документРеализацияFX);
        tableTabSale.setItems(list);
        tableTabSale.refresh();
    }

    public void fillTableFromFX() {
        ObservableList <ТабЧастьРеализацияFX> list = listTabSaleImpl.getTabPartSaleList();
        tableTabSale.setItems(list);
        tableTabSale.refresh();
    }

    private void initListeners() {
        tableTabSale.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getClickCount()==2) {
                    editStrTab((ТабЧастьРеализацияFX) tableTabSale.getSelectionModel().getSelectedItem(), false);
                    fillTableFromFX();
                }
            }
        });
    }

    private void initLoader() {
        try {
            this.fxmlLoader.setLocation(getClass().getResource("/fxml/editTabSale.fxml"));
            fxmlEdit = fxmlLoader.load();
            editTabSaleController = fxmlLoader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void editStrTab(ТабЧастьРеализацияFX selectedRecord, boolean add) {
        if(!recordTabSaleIsSelected(selectedRecord)) {
            return;
        }
        try {
            editTabSaleController.setТабЧастьРеализацияFX(selectedRecord);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        showDialog();

        if (editTabSaleController.isSaveClicked()) {
            if (add)
                listTabSaleImpl.getTabPartSaleList().add(selectedRecord);
        }
    }

    void deleteStrTab(ТабЧастьРеализацияFX selectedRecord) {
        if(!recordTabSaleIsSelected(selectedRecord)) {
            return;
        }
        listTabSaleImpl.getTabPartSaleList().remove(selectedRecord);
    }

    @FXML
    void actionEditTabRecords(ActionEvent actionEvent) throws SQLException {
        Object source = actionEvent.getSource();
        if(!(source instanceof Button)) {
            return;
        }
        ТабЧастьРеализацияFX selectedRecord = (ТабЧастьРеализацияFX) tableTabSale.getSelectionModel().getSelectedItem();
        Button clickedButton = (Button) source;

        boolean research = false;
        switch (clickedButton.getId()) {
            case "btnEditTabRecords":
                editStrTab(selectedRecord,false);
                break;
            case  "btnAddRecords":
                editStrTab(new ТабЧастьРеализацияFX(), true);
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
        документРеализацияFX.setNumber(Integer.valueOf(txtNumber.getText()));
        документРеализацияFX.setDate(Date.from(datePickerDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        документРеализацияFX.setFinalSum(Double.valueOf(txtSum.getText()));
        документРеализацияFX.setContragent(choiseBoxContragent.getValue().getЗаписьКонтрагент_());
        документРеализацияFX.setContragentFX_(choiseBoxContragent.getValue());
        документРеализацияFX.setTabPartsFX_(listTabSaleImpl.getTabPartSaleList());
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
        документРеализацияFX.pometkaProvedenya = true;
        actionSaveAndClose(event);
    }

    @FXML
    void actionDeleteRegister(ActionEvent event) {
        документРеализацияFX.pometkaProvedenya = false;
        actionSaveAndClose(event);
    }

    public void setДокументРеализацияFX(ДокументРеализацияFX документРеализацияFX) throws SQLException {
        if (документРеализацияFX == null){
            return;
        }
        saveClicked = false;
        this.документРеализацияFX = документРеализацияFX;
        txtNumber.setText(String.valueOf(документРеализацияFX.getNumber()));
        datePickerDate.setValue(документРеализацияFX.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        txtSum.setText(String.valueOf(документРеализацияFX.getFinalSum()));
        tableTabSale.setItems(документРеализацияFX.getTabPartsFX_());
        this.initChoiseBox();

        fillTable();
    }

    public ДокументРеализацияFX getДокументРеализацияFX() {
        return документРеализацияFX;
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

        this.choiseBoxContragent.setValue(this.документРеализацияFX.getContragentFX_());

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
                            документРеализацияFX.setContragentFX_(choiseBoxContragent.getValue());
                        });
                    }
                }
            }
        });

    }

}
