package com.stuart.controllers.номенклатура;

import com.stuart.interfaces.impls.справочники.HibernateSprNom;
import com.stuart.objectsFX.справочники.ЗаписьНоменклатураFX;
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

public class TableNomController {
    @FXML
    private Button btnAddRecord;
    @FXML
    private Button btnEditRecord;
    @FXML
    private CustomTextField txtSearch;
    @FXML
    private Button btnSearch;
    @FXML
    private TableView tableSprNomenclature;
    @FXML
    private TableColumn<ЗаписьНоменклатураFX, String> columnCode;
    @FXML
    private TableColumn<ЗаписьНоменклатураFX, String> columnArticleNumber;
    @FXML
    private TableColumn<ЗаписьНоменклатураFX, String> columnName;
    @FXML
    private TableColumn<ЗаписьНоменклатураFX, String> columnCategory;
    @FXML
    private TableColumn<ЗаписьНоменклатураFX, String> columnSubcategory;
    @FXML
    private TableColumn<ЗаписьНоменклатураFX, String> columnProducer;
    @FXML
    private Label labelCount;

    private static final String FXML_EDIT = "/fxml/editDialogNom.fxml";
    private HibernateSprNom sprNomImpl = new HibernateSprNom();
    private Parent fxmlEdit;
    private FXMLLoader fxmlLoader = new FXMLLoader();
    private EditNomController editDialogController;
    private Stage editDialogStage;
    private Stage mainStage;

    @FXML
    private void initialize() throws SQLException {
        columnCode.setCellValueFactory(new PropertyValueFactory<ЗаписьНоменклатураFX, String>("code"));
        columnArticleNumber.setCellValueFactory(new PropertyValueFactory<ЗаписьНоменклатураFX, String>("articleNumber"));
        columnName.setCellValueFactory(new PropertyValueFactory<ЗаписьНоменклатураFX, String>("name"));
        columnCategory.setCellValueFactory(new PropertyValueFactory<ЗаписьНоменклатураFX, String>("category"));
        columnSubcategory.setCellValueFactory(new PropertyValueFactory<ЗаписьНоменклатураFX, String>("subcategory"));
        columnProducer.setCellValueFactory(new PropertyValueFactory<ЗаписьНоменклатураFX, String>("contragent"));
        setupClearButtonField(txtSearch);
        initListeners();
        fillTable();
        initLoader();
    }

    @FXML
    void actionButtonPressed(ActionEvent actionEvent) throws SQLException {
        //получаем источник события
        Object source = actionEvent.getSource();
        //если нажата не кнопка выходим из метода
        if(!(source instanceof Button)) {
            return;
        }
        ЗаписьНоменклатураFX selectedRecord = (ЗаписьНоменклатураFX) tableSprNomenclature.getSelectionModel().getSelectedItem();

        Button clickedButton = (Button) source;

        boolean research = false;
        switch (clickedButton.getId()) {
            case "btnAddRecord":
                editDialogController.setЗаписьНоменклатураFX(new ЗаписьНоменклатураFX());
                showDialog();
                if (editDialogController.isSaveClicked()) {
                    sprNomImpl.add(editDialogController.getЗаписьНоменклатураFX());
                    research = true;
                }
                break;
            case  "btnEditRecord":
                if(!recordNomIsSelected(selectedRecord)) {
                    return;
                }
                try {
                    editDialogController.setЗаписьНоменклатураFX(selectedRecord);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                showDialog();
                if (editDialogController.isSaveClicked()) {
                    sprNomImpl.update(selectedRecord);
                    research = true;
                }
                break;
        }

    }

    @FXML
    private void showDialog() {
        if(editDialogStage == null) {
            editDialogStage = new Stage();
            editDialogStage.setTitle("Создать/редактировать запись справочника Номенклатура");
            editDialogStage.setMinWidth(600);
            editDialogStage.setMinHeight(400);
            editDialogStage.setResizable(false);
            editDialogStage.setScene(new Scene(fxmlEdit));
            editDialogStage.initModality(Modality.WINDOW_MODAL);
            editDialogStage.initOwner(mainStage); //получаем родительское окно - источник события
        }
        editDialogStage.showAndWait(); //ожидание закрытия окна
    }

    private boolean recordNomIsSelected(ЗаписьНоменклатураFX selectedRecord) {
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
        ObservableList<ЗаписьНоменклатураFX> list = sprNomImpl.findAll();
        tableSprNomenclature.setItems(list);
    }

    private void initLoader() {
        try {
            this.fxmlLoader.setLocation(getClass().getResource("/fxml/editDialogNom.fxml"));
            fxmlEdit = fxmlLoader.load();
            editDialogController = fxmlLoader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initListeners() {
        this.sprNomImpl.getNomenclaturiesList().addListener(new ListChangeListener<ЗаписьНоменклатураFX>() {
            @Override
            public void onChanged(Change<? extends ЗаписьНоменклатураFX> c) {
                updateCountLabel();
            }
        });

        tableSprNomenclature.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getClickCount()==2) {
                    try {
                        editDialogController.setЗаписьНоменклатураFX(
                                (ЗаписьНоменклатураFX) tableSprNomenclature.getSelectionModel().getSelectedItem());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    showDialog();
                    if (editDialogController.isSaveClicked()) {
                        sprNomImpl.update((ЗаписьНоменклатураFX) tableSprNomenclature.getSelectionModel().getSelectedItem());
                        try {
                            sprNomImpl.findAll();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }
            }
        });
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    private void updateCountLabel() {
        labelCount.setText("Количество записей: "+sprNomImpl.getNomenclaturiesList().size());
    }

    @FXML
    void actionSearch(ActionEvent event) throws SQLException {
        if ( txtSearch.getText().trim().length() == 0) {
            sprNomImpl.findAll();
        }
        else {
            sprNomImpl.findText(txtSearch.getText());
//            sprNomImpl.findInt(Integer.valueOf(txtSearch.getText()));
            //делаем доп метод с поиском по коду или номеру, и в зависимости от введенного значения вызываем нужнгый метод
        }
    }
}
