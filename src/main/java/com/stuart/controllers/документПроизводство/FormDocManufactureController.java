package com.stuart.controllers.документПроизводство;

import com.stuart.interfaces.impls.документы.производство.HibernateTabConsumedManufacture;
import com.stuart.interfaces.impls.документы.производство.HibernateTabProducedManufacture;
import com.stuart.objectsFX.документы.*;
import com.stuart.utils.DialogManager;
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

public class FormDocManufactureController {

    @FXML
    private Button btnCreateRegisterAndClose;
    @FXML
    private Button btnWriteAndClose;
    @FXML
    private Button btnClose;
    @FXML
    private TextField txtNumber;
    @FXML
    private DatePicker datePickerDate;
    @FXML
    private Button btnEditTabRecords;
    @FXML
    private Button btnAddRecords;
    @FXML
    private Button btnDeleteRecords;

    @FXML
    private Tab tabPaneConsumed;
    @FXML
    private TableView tableViewConsumed;
    @FXML
    private TableColumn<ТабЧастьИзрасходованоПроизводствоFX, String> columnLineNumberConsumed;
    @FXML
    private TableColumn<ТабЧастьИзрасходованоПроизводствоFX, String> columnNomenclatureConsumed;
    @FXML
    private TableColumn<ТабЧастьИзрасходованоПроизводствоFX, String> columnAmountConsumed;
    @FXML
    private TableColumn<ТабЧастьИзрасходованоПроизводствоFX, String> columnPrStageConsumed;
    @FXML
    private TabPane tabePane;

    @FXML
    private Tab tabPaneProduced;
    @FXML
    private TableView tableViewProduced;
    @FXML
    private TableColumn<ТабЧастьПроизведеноПроизводствоFX, String> columnLineNumberProduced;
    @FXML
    private TableColumn<ТабЧастьПроизведеноПроизводствоFX, String> columnNomenclatureProduced;
    @FXML
    private TableColumn<ТабЧастьПроизведеноПроизводствоFX, String> columnAmountProduced;

    private static final String FXML_EDIT_Consumed = "/fxml/editTabConsumedManufacture.fxml";
    private static final String FXML_EDIT_Produced = "/fxml/editTabProducedManufacture.fxml";
    private HibernateTabConsumedManufacture listTabConsumedImpl = new HibernateTabConsumedManufacture();
    private HibernateTabProducedManufacture listTabProducedImpl = new HibernateTabProducedManufacture();
    private Parent fxmlEdit;
    private FXMLLoader fxmlLoaderConsumed = new FXMLLoader();
    private FXMLLoader fxmlLoaderProduced = new FXMLLoader();
    private EditTabConsumedManufactureController editTabConsumedManufactureController;
    private EditTabProducedManufactureController editTabProducedManufactureController;
    private Stage editDialogStageConsumed;
    private Stage editDialogStageProduced;
    private Stage mainStage;
    private ДокументПроизводствоFX документПроизводствоFX;
    private boolean saveClicked = false;

    @FXML
    private void showDialogStageConsumed() {
        if(editDialogStageConsumed == null) {
            editDialogStageConsumed = new Stage();
            editDialogStageConsumed.setTitle("Редактировать табличную часть Расход материалов");
            editDialogStageConsumed.setMinWidth(600);
            editDialogStageConsumed.setMinHeight(300);
            editDialogStageConsumed.setResizable(false);
            editDialogStageConsumed.setScene(new Scene(fxmlEdit));
            editDialogStageConsumed.initModality(Modality.WINDOW_MODAL);
            editDialogStageConsumed.initOwner(mainStage); //получаем родительское окно - источник события
        }
        editDialogStageConsumed.showAndWait(); //ожидание закрытия окна
    }

    @FXML
    private void showDialogStageProduced() {
        if(editDialogStageProduced == null) {
            editDialogStageProduced = new Stage();
            editDialogStageProduced.setTitle("Редактировать табличную часть Произведено продукции");
            editDialogStageProduced.setMinWidth(600);
            editDialogStageProduced.setMinHeight(300);
            editDialogStageProduced.setResizable(false);
            editDialogStageProduced.setScene(new Scene(fxmlEdit));
            editDialogStageProduced.initModality(Modality.WINDOW_MODAL);
            editDialogStageProduced.initOwner(mainStage); //получаем родительское окно - источник события
        }
        editDialogStageProduced.showAndWait(); //ожидание закрытия окна
    }

    private boolean recordTabConsumedIsSelected(ТабЧастьИзрасходованоПроизводствоFX selectedRecord) {
        if(selectedRecord == null) {
            DialogManager.showInfoDialog("Ошибка", "Выберите запись!");
            return  false;
        }
        return true;
    }

    private boolean recordTabProducedIsSelected(ТабЧастьПроизведеноПроизводствоFX selectedRecord) {
        if(selectedRecord == null) {
            DialogManager.showInfoDialog("Ошибка", "Выберите запись!");
            return  false;
        }
        return true;
    }

    @FXML
    private void initialize() throws SQLException {
        columnLineNumberConsumed.setCellValueFactory(new PropertyValueFactory<ТабЧастьИзрасходованоПроизводствоFX, String>("numberStr"));
        columnAmountConsumed.setCellValueFactory(new PropertyValueFactory<ТабЧастьИзрасходованоПроизводствоFX, String>("amount"));
        columnNomenclatureConsumed.setCellValueFactory(new PropertyValueFactory<ТабЧастьИзрасходованоПроизводствоFX, String>("nomenclature"));
        columnPrStageConsumed.setCellValueFactory(new PropertyValueFactory<ТабЧастьИзрасходованоПроизводствоFX, String>("prStage"));

        columnLineNumberProduced.setCellValueFactory(new PropertyValueFactory<ТабЧастьПроизведеноПроизводствоFX, String>("numberStr"));
        columnAmountProduced.setCellValueFactory(new PropertyValueFactory<ТабЧастьПроизведеноПроизводствоFX, String>("amount"));
        columnNomenclatureProduced.setCellValueFactory(new PropertyValueFactory<ТабЧастьПроизведеноПроизводствоFX, String>("nomenclature"));

//        initLoaderConsumed();
        initListenersConsumed();
        initListenersProduced();
    }

    public void fillTable() throws SQLException {
        ObservableList<ТабЧастьИзрасходованоПроизводствоFX> list1 = listTabConsumedImpl.findAll(документПроизводствоFX);
        tableViewConsumed.setItems(list1);
        tableViewConsumed.refresh();

        ObservableList<ТабЧастьПроизведеноПроизводствоFX> list2 = listTabProducedImpl.findAll(документПроизводствоFX);
        tableViewProduced.setItems(list2);
        tableViewProduced.refresh();
    }

    public void fillTableFromFX() {
        ObservableList <ТабЧастьИзрасходованоПроизводствоFX> list1 = listTabConsumedImpl.getTabPartsConsumedList();
        tableViewConsumed.setItems(list1);
        tableViewConsumed.refresh();

        ObservableList <ТабЧастьПроизведеноПроизводствоFX> list2 = listTabProducedImpl.getTabPartsProducedList();
        tableViewProduced.setItems(list2);
        tableViewProduced.refresh();
    }

    private void initListenersConsumed() {
        tableViewConsumed.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getClickCount()==2) {
                    editStrTabConsumed((ТабЧастьИзрасходованоПроизводствоFX) tableViewConsumed.getSelectionModel().getSelectedItem(), false);
                    fillTableFromFX();/////
                }
            }
        });
    }

    private void initListenersProduced() {
        tableViewProduced.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getClickCount()==2) {
                    editStrTabProduced((ТабЧастьПроизведеноПроизводствоFX) tableViewProduced.getSelectionModel().getSelectedItem(), false);
                    fillTableFromFX();/////
                }
            }
        });
    }

    private void initLoaderConsumed() {
        try {
            this.fxmlLoaderConsumed.setLocation(getClass().getResource("/fxml/editTabConsumedManufacture.fxml"));
            fxmlEdit = fxmlLoaderConsumed.load();
            editTabConsumedManufactureController  = fxmlLoaderConsumed.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initLoaderProduced() {
        try {
            this.fxmlLoaderProduced.setLocation(getClass().getResource("/fxml/editTabProducedManufacture.fxml"));
            fxmlEdit = fxmlLoaderProduced.load();
            editTabProducedManufactureController = fxmlLoaderProduced.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void editStrTabConsumed(ТабЧастьИзрасходованоПроизводствоFX selectedRecord, boolean add) {
        initLoaderConsumed();
        if(!recordTabConsumedIsSelected(selectedRecord)) {
            return;
        }
        try {
            editTabConsumedManufactureController.setТабЧастьИзрасходованоПроизводствоFX(selectedRecord);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        showDialogStageConsumed();
        //вот тут вызвем инитлоадер

        if (editTabConsumedManufactureController.isSaveClicked()) {
            if (add)
                listTabConsumedImpl.getTabPartsConsumedList().add(selectedRecord);
        }
    }

    void editStrTabProduced(ТабЧастьПроизведеноПроизводствоFX selectedRecord, boolean add) {
        initLoaderProduced();
        if(!recordTabProducedIsSelected(selectedRecord)) {
            return;
        }
        try {
            editTabProducedManufactureController.setТабЧастьПроизведеноПроизводствоFX(selectedRecord);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        showDialogStageProduced();
        //вот тут вызвем инитлоадер

        if (editTabProducedManufactureController.isSaveClicked()) {
            if (add)
                listTabProducedImpl.getTabPartsProducedList().add(selectedRecord);
        }
    }

    void deleteStrTabConsumed(ТабЧастьИзрасходованоПроизводствоFX selectedRecord) {
        if(!recordTabConsumedIsSelected(selectedRecord)) {
            return;
        }
        listTabConsumedImpl.getTabPartsConsumedList().remove(selectedRecord);
    }

    void deleteStrTabProduced(ТабЧастьПроизведеноПроизводствоFX selectedRecord) {
        if(!recordTabProducedIsSelected(selectedRecord)) {
            return;
        }
        listTabProducedImpl.getTabPartsProducedList().remove(selectedRecord);
    }

    @FXML
    void actionEditTabRecords(ActionEvent actionEvent) {

        Object source = actionEvent.getSource();
        if(!(source instanceof Button)) {
            return;
        }

        Button clickedButton = (Button) source;
        if(tabPaneConsumed.isSelected()) {

            ТабЧастьИзрасходованоПроизводствоFX selectedRecord =
                    (ТабЧастьИзрасходованоПроизводствоFX) tableViewConsumed.getSelectionModel().getSelectedItem();

            switch (clickedButton.getId()) {
                case "btnEditTabRecords":
                    editStrTabConsumed(selectedRecord, false);
                    break;
                case  "btnAddRecords":
                    editStrTabConsumed(new ТабЧастьИзрасходованоПроизводствоFX(), true);
                    break;
                case "btnDeleteRecords":
                    deleteStrTabConsumed(selectedRecord);
                    break;
            }

        } else if(tabPaneProduced.isSelected()) {

            ТабЧастьПроизведеноПроизводствоFX selectedRecord =
                    (ТабЧастьПроизведеноПроизводствоFX) tableViewProduced.getSelectionModel().getSelectedItem();

            switch (clickedButton.getId()) {
                case "btnEditTabRecords":
                    editStrTabProduced(selectedRecord,false);
                    break;
                case  "btnAddRecords":
                    editStrTabProduced(new ТабЧастьПроизведеноПроизводствоFX(), true);
                    break;
                case "btnDeleteRecords":
                    deleteStrTabProduced(selectedRecord);
                    break;
            }
        }
        fillTableFromFX();
    }

    @FXML
    void actionSaveAndClose(ActionEvent event) {
        if(!checkValues()) {
            return;
        }
        документПроизводствоFX.setNumber(Integer.valueOf(txtNumber.getText()));
        документПроизводствоFX.setDate(Date.from(datePickerDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        документПроизводствоFX.setTabPartsConsumedFX_(listTabConsumedImpl.getTabPartsConsumedList());
        документПроизводствоFX.setTabPartsProducedFX_(listTabProducedImpl.getTabPartsProducedList());
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
        документПроизводствоFX.pometkaProvedenya = true;
        actionSaveAndClose(event);
    }

    @FXML
    void actionDeleteRegister(ActionEvent event) {
        документПроизводствоFX.pometkaProvedenya = false;
        actionSaveAndClose(event);
    }

    public void setДокументПроизводствоFX(ДокументПроизводствоFX документПроизводствоFX) throws SQLException {
        if (документПроизводствоFX == null){
            return;
        }
        saveClicked = false;
        this.документПроизводствоFX = документПроизводствоFX;
        txtNumber.setText(String.valueOf(документПроизводствоFX.getNumber()));
        datePickerDate.setValue(документПроизводствоFX.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        tableViewConsumed.setItems(документПроизводствоFX.getTabPartsConsumedFX_());
        tableViewProduced.setItems(документПроизводствоFX.getTabPartsProducedFX_());

        fillTable();
    }

    public ДокументПроизводствоFX getДокументПроизводствоFX() {
        return документПроизводствоFX;
    }

    private boolean checkValues() {
        if(txtNumber.getText().trim().length() == 0
                || datePickerDate.getValue() == null) {
            DialogManager.showInfoDialog("Ошибка", "Заполните все поля!");
            return false;
        }
        return true;
    }

    public boolean isSaveClicked() {
        return saveClicked;
    }

}
