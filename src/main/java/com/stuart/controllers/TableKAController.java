package com.stuart.controllers;

import com.stuart.interfaces.impls.CollectionISpravochnikKA;
import com.stuart.models.entity.справочники.ЗаписьКонтрагент;
import com.stuart.models.entity.справочники.ТестСпрКА;
import javafx.beans.property.ObjectProperty;
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
import javafx.stage.Window;
import org.controlsfx.control.textfield.CustomTextField;
import org.controlsfx.control.textfield.TextFields;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Locale;

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
    private TableColumn<ТестСпрКА, String> columnCode;
    @FXML
    private TableColumn<ТестСпрКА, String> columnName;
    @FXML
    private TableColumn<ТестСпрКА, String> columnAddress;
    @FXML
    private TableColumn<ТестСпрКА, String> columnTypeKA;
    @FXML
    private TableColumn<ТестСпрКА, String> columnContacts;
    @FXML
    private Label labelCount;

    private CollectionISpravochnikKA sprKAimpl = new CollectionISpravochnikKA();
    private ObservableList<ТестСпрКА> backupList;
    private Parent fxmlEdit;
    private FXMLLoader fxmlLoader = new FXMLLoader();
    private EditDialogKAController editDialogController;
    private Stage editDialogStage;
    private Stage mainStage;

    @FXML
    private void initialize() {
        columnCode.setCellValueFactory(new PropertyValueFactory<ТестСпрКА, String>("code"));
        columnName.setCellValueFactory(new PropertyValueFactory<ТестСпрКА, String>("name"));
        columnTypeKA.setCellValueFactory(new PropertyValueFactory<ТестСпрКА, String>("type_KA"));
        columnAddress.setCellValueFactory(new PropertyValueFactory<ТестСпрКА, String>("address"));
        columnContacts.setCellValueFactory(new PropertyValueFactory<ТестСпрКА, String>("contact_person"));
        setupClearButtonField(txtSearch);
        initListeners();
        fillData();
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
        Button clickedButton = (Button) source;

        switch (clickedButton.getId()) {
            case "btnAddRecord":
                editDialogController.setЗаписьКонтрагент(new ТестСпрКА());
                showDialog();
                sprKAimpl.add(editDialogController.getЗаписьКонтрагент());
                break;
            case  "btnEditRecord":
                editDialogController.setЗаписьКонтрагент((ТестСпрКА) tableSprContragent.getSelectionModel().getSelectedItem());
                showDialog();
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

    private void setupClearButtonField(CustomTextField customTextField) {
        try {
            Method m = TextFields.class.getDeclaredMethod("setupClearButtonField", TextField.class, ObjectProperty.class);
            m.setAccessible(true);
            m.invoke(null, customTextField, customTextField.rightProperty());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void fillData() {
        sprKAimpl.fillTestData();
        backupList = FXCollections.observableArrayList();
        backupList.addAll(sprKAimpl.getСписокКонтрагентов());
        tableSprContragent.setItems(sprKAimpl.getСписокКонтрагентов());
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
        this.sprKAimpl.getСписокКонтрагентов().addListener(new ListChangeListener<ТестСпрКА>() {
            @Override
            public void onChanged(Change<? extends ТестСпрКА> c) {
                updateCountLabel();
            }
        });

        tableSprContragent.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getClickCount()==2) {
                    editDialogController.setЗаписьКонтрагент(
                            (ТестСпрКА) tableSprContragent.getSelectionModel().getSelectedItem());
                    showDialog();
                }
            }
        });
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    private void updateCountLabel() {
        labelCount.setText("Количество записей: "+ sprKAimpl.getСписокКонтрагентов().size());
    }

    public void actionSearch(ActionEvent actionEvent) {
        sprKAimpl.getСписокКонтрагентов().clear();
        for (ТестСпрКА записьКонтрагент : backupList) {
            if (записьКонтрагент.getCode().toLowerCase().contains(txtSearch.getText().toLowerCase()) ||
                    записьКонтрагент.getName().toLowerCase().contains(txtSearch.getText().toLowerCase()) ||
                    записьКонтрагент.getType_KA().toLowerCase().contains(txtSearch.getText().toLowerCase()) ||
                    записьКонтрагент.getAddress().toLowerCase().contains(txtSearch.getText().toLowerCase()) ||
                    записьКонтрагент.getContact_person().toLowerCase().contains(txtSearch.getText().toLowerCase())) {
                sprKAimpl.getСписокКонтрагентов().add(записьКонтрагент);
            }
        }
    }

}
