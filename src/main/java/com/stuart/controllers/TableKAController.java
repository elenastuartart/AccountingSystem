package com.stuart.controllers;

import com.stuart.interfaces.impls.DBSpravochnikKA;
import com.stuart.objects.ЗаписьКонтрагентFX;
import com.stuart.utils.DialogManager;
import javafx.beans.property.ObjectProperty;
import javafx.collections.ListChangeListener;
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

public class TableKAController {

    @FXML
    private Button btnAddRecord;
    @FXML
    private Button btnEditRecord;
    @FXML
    private CustomTextField txtSearch;
    @FXML
    private Button btnSearch;
    @FXML
    private TableView tableSprContragent;
    @FXML
    private TableColumn<ЗаписьКонтрагентFX, String> columnCode;
    @FXML
    private TableColumn<ЗаписьКонтрагентFX, String> columnName;
    @FXML
    private TableColumn<ЗаписьКонтрагентFX, String> columnAddress;
    @FXML
    private TableColumn<ЗаписьКонтрагентFX, String> columnTypeKA;
    @FXML
    private TableColumn<ЗаписьКонтрагентFX, String> columnContacts;
    @FXML
    private Label labelCount;

    private static final String FXML_EDIT = "/fxml/editDialogKA.fxml";
    private DBSpravochnikKA sprKAImpl = new  DBSpravochnikKA();
    private Parent fxmlEdit;
    private FXMLLoader fxmlLoader = new FXMLLoader();
    private EditDialogKAController editDialogController;
    private Stage editDialogStage;
    private Stage mainStage;

    @FXML
    private void initialize() throws SQLException {
        columnCode.setCellValueFactory(new PropertyValueFactory<ЗаписьКонтрагентFX, String>("code"));
        columnName.setCellValueFactory(new PropertyValueFactory<ЗаписьКонтрагентFX, String>("name"));
        columnTypeKA.setCellValueFactory(new PropertyValueFactory<ЗаписьКонтрагентFX, String>("type_KA"));
        columnAddress.setCellValueFactory(new PropertyValueFactory<ЗаписьКонтрагентFX, String>("address"));
        columnContacts.setCellValueFactory(new PropertyValueFactory<ЗаписьКонтрагентFX, String>("contact_person"));
        setupClearButtonField(txtSearch);
        initListeners();
        fillTable();
        initLoader();
    }

    @FXML
    public void actionButtonPressed(ActionEvent actionEvent) {
        //получаем источник события
        Object source = actionEvent.getSource();
        //если нажата не кнопка выходим из метода
        if(!(source instanceof Button)) {
            return;
        }
        ЗаписьКонтрагентFX selectedRecord = (ЗаписьКонтрагентFX) tableSprContragent.getSelectionModel().getSelectedItem();

        Button clickedButton = (Button) source;

        boolean research = false;
        switch (clickedButton.getId()) {
            case "btnAddRecord":
                editDialogController.setЗаписьКонтрагентFX(new ЗаписьКонтрагентFX());
                showDialog();
//                sprKAImpl.add(editDialogController.getЗаписьКонтрагентFX());
                if (editDialogController.isSaveClicked()) {
                    sprKAImpl.add(editDialogController.getЗаписьКонтрагентFX());
                    research = true;
                }
                break;
            case  "btnEditRecord":
                if(!recordKAisSelected(selectedRecord)) {
                    return;
                }
                editDialogController.setЗаписьКонтрагентFX(selectedRecord);
                showDialog();
                if (editDialogController.isSaveClicked()) {
                    // коллекция в addressBookImpl и так обновляется, т.к. мы ее редактируем в диалоговом окне и сохраняем при нажатии на ОК
                    sprKAImpl.update(selectedRecord);
                    research = true;
                }
                break;
        }
    }

    @FXML
    private void showDialog() {
        if(editDialogStage == null) {
            editDialogStage = new Stage();
            editDialogStage.setTitle("Создать/редактировать запись справочника Контрагент");
            editDialogStage.setMinWidth(600);
            editDialogStage.setMinHeight(400);
            editDialogStage.setResizable(false);
            editDialogStage.setScene(new Scene(fxmlEdit));
            editDialogStage.initModality(Modality.WINDOW_MODAL);
            editDialogStage.initOwner(mainStage); //получаем родительское окно - источник события
        }
        editDialogStage.showAndWait(); //ожидание закрытия окна
    }

    private boolean recordKAisSelected(ЗаписьКонтрагентFX selectedRecord) {
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
        ObservableList<ЗаписьКонтрагентFX> list = sprKAImpl.findAll();
        tableSprContragent.setItems(list);
    }

    private void initLoader() {
        try {
            this.fxmlLoader.setLocation(getClass().getResource("/fxml/editDialogKA.fxml"));
            fxmlEdit = fxmlLoader.load();
            editDialogController = fxmlLoader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initListeners() {
        this.sprKAImpl.getContragentList().addListener(new ListChangeListener<ЗаписьКонтрагентFX>() {
            @Override
            public void onChanged(Change<? extends ЗаписьКонтрагентFX> c) {
                updateCountLabel();
            }
        });

        tableSprContragent.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getClickCount()==2) {
                    editDialogController.setЗаписьКонтрагентFX(
                            (ЗаписьКонтрагентFX) tableSprContragent.getSelectionModel().getSelectedItem());
                    showDialog();
                    if (editDialogController.isSaveClicked()) {
                        sprKAImpl.update((ЗаписьКонтрагентFX) tableSprContragent.getSelectionModel().getSelectedItem());
                    }

                }
            }
        });
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    private void updateCountLabel() {
        labelCount.setText("Количество записей: "+sprKAImpl.getContragentList().size());
    }

    public void actionSearch(ActionEvent actionEvent) {

        if (txtSearch.getText().trim().length() == 0) {
            sprKAImpl.findAll();
        }
        else {
            sprKAImpl.findText(txtSearch.getText());

//            if(sprKAImpl.findText(txtSearch.getText()) == null) {
//                sprKAImpl.findInt(Integer.valueOf(txtSearch.getText()));
//            }

//
//            if (Integer.valueOf(txtSearch.getText())>0) {
//                 sprKAImpl.findInt(Integer.valueOf(txtSearch.getText()));
//            }
//            else {
//                sprKAImpl.findText(txtSearch.getText());
//            }
            //делаем доп метод с поиском по коду или номеру, и в зависимости от введенного значения вызываем нужнгый метод
        }
    }

}
