package com.stuart.controllers.этапыПроизводства;

import com.stuart.interfaces.impls.справочники.HibernateSprPrStages;
import com.stuart.objectsFX.справочники.ЗаписьЭтапыПроизводстваFX;
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

public class TablePrStagesController {
    @FXML
    private Button btnAddRecord;
    @FXML
    private Button btnEditRecord;
    @FXML
    private CustomTextField txtSearch;
    @FXML
    private Button btnSearch;
    @FXML
    private TableView tablePrStages;
    @FXML
    private TableColumn<ЗаписьЭтапыПроизводстваFX, String> columnCode;
    @FXML
    private TableColumn<ЗаписьЭтапыПроизводстваFX, String> columnName;
    @FXML
    private TableColumn<ЗаписьЭтапыПроизводстваFX, String> columnDescription;
    @FXML
    private Label labelCount;

    private static final String FXML_EDIT = "/fxml/editDialogPrStages.fxml";
    private HibernateSprPrStages sprPrStagesImpl = new HibernateSprPrStages();
    private Parent fxmlEdit;
    private FXMLLoader fxmlLoader = new FXMLLoader();
    private EditPrStagesController editDialogController;
    private Stage editDialogStage;
    private Stage mainStage;

    @FXML
    private void initialize() throws SQLException {
        columnCode.setCellValueFactory(new PropertyValueFactory<ЗаписьЭтапыПроизводстваFX, String>("code"));
        columnName.setCellValueFactory(new PropertyValueFactory<ЗаписьЭтапыПроизводстваFX, String>("name"));
        columnDescription.setCellValueFactory(new PropertyValueFactory<ЗаписьЭтапыПроизводстваFX, String>("description_stage"));
        setupClearButtonField(txtSearch);
        initListeners();
        fillTable();
        initLoader();
    }

    @FXML
    void actionButtonPressed(ActionEvent actionEvent) {
        //получаем источник события
        Object source = actionEvent.getSource();
        //если нажата не кнопка выходим из метода
        if(!(source instanceof Button)) {
            return;
        }
        ЗаписьЭтапыПроизводстваFX selectedRecord = (ЗаписьЭтапыПроизводстваFX) tablePrStages.getSelectionModel().getSelectedItem();

        Button clickedButton = (Button) source;

        boolean research = false;
        switch (clickedButton.getId()) {
            case "btnAddRecord":
                editDialogController.setЗаписьЭтапыПроизводстваFX(new ЗаписьЭтапыПроизводстваFX());
                showDialog();
//                sprKAImpl.add(editDialogController.getЗаписьКонтрагентFX());
                if (editDialogController.isSaveClicked()) {
                    sprPrStagesImpl.add(editDialogController.getЗаписьЭтапыПроизводстваFX());
                    research = true;
                }
                break;
            case  "btnEditRecord":
                if(!recordPrStIsSelected(selectedRecord)) {
                    return;
                }
                editDialogController.setЗаписьЭтапыПроизводстваFX(selectedRecord);
                showDialog();
                if (editDialogController.isSaveClicked()) {
                    // коллекция в addressBookImpl и так обновляется, т.к. мы ее редактируем в диалоговом окне и сохраняем при нажатии на ОК
                    sprPrStagesImpl.update(selectedRecord);
                    research = true;
                }
                break;
        }
    }

    @FXML
    private void showDialog() {
        if(editDialogStage == null) {
            editDialogStage = new Stage();
            editDialogStage.setTitle("Создать/редактировать запись справочника Этапы производства");
            editDialogStage.setMinWidth(600);
            editDialogStage.setMinHeight(400);
            editDialogStage.setResizable(false);
            editDialogStage.setScene(new Scene(fxmlEdit));
            editDialogStage.initModality(Modality.WINDOW_MODAL);
            editDialogStage.initOwner(mainStage); //получаем родительское окно - источник события
        }
        editDialogStage.showAndWait(); //ожидание закрытия окна
    }

    private boolean recordPrStIsSelected(ЗаписьЭтапыПроизводстваFX selectedRecord) {
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
        ObservableList<ЗаписьЭтапыПроизводстваFX> list = sprPrStagesImpl.findAll();
        tablePrStages.setItems(list);
    }

    private void initLoader() {
        try {
            this.fxmlLoader.setLocation(getClass().getResource("/fxml/editDialogPrStages.fxml"));
            fxmlEdit = fxmlLoader.load();
            editDialogController = fxmlLoader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initListeners() {
        this.sprPrStagesImpl.getPrStagesList().addListener(new ListChangeListener<ЗаписьЭтапыПроизводстваFX>() {
            @Override
            public void onChanged(Change<? extends ЗаписьЭтапыПроизводстваFX> c) {
                updateCountLabel();
            }
        });

        tablePrStages.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getClickCount()==2) {
                    try {
                        editDialogController.setЗаписьЭтапыПроизводстваFX(
                                (ЗаписьЭтапыПроизводстваFX) tablePrStages.getSelectionModel().getSelectedItem());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    showDialog();
                    if (editDialogController.isSaveClicked()) {
                        sprPrStagesImpl.update((ЗаписьЭтапыПроизводстваFX) tablePrStages.getSelectionModel().getSelectedItem());
                    }
                    try {
                        sprPrStagesImpl.findAll();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    private void updateCountLabel() {
        labelCount.setText("Количество записей: "+sprPrStagesImpl.getPrStagesList().size());
    }

    @FXML
    void actionSearch(ActionEvent event) throws SQLException {
        if (txtSearch.getText().trim().length() == 0) {
            sprPrStagesImpl.findAll();
        }
        else {
            sprPrStagesImpl.findText(txtSearch.getText());
            //делаем доп метод с поиском по коду или номеру, и в зависимости от введенного значения вызываем нужнгый метод
        }
    }
}
