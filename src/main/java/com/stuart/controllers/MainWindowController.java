package com.stuart.controllers;

import com.stuart.controllers.документЗакупка.TableDocPurchaseController;
import com.stuart.controllers.документПроизводство.TableDocManufactureController;
import com.stuart.controllers.документРеализация.TableDocSaleController;
import com.stuart.controllers.контрагенты.TableKAController;
import com.stuart.controllers.номенклатура.TableNomController;
import com.stuart.controllers.этапыПроизводства.TablePrStagesController;
import javafx.beans.property.ObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.Getter;
import org.controlsfx.control.textfield.CustomTextField;
import org.controlsfx.control.textfield.TextFields;
import org.hibernate.annotations.SqlFragmentAlias;

import java.io.IOException;
import java.lang.reflect.Method;

public class MainWindowController {
    @FXML
    private Button btnSprNom;
    @FXML
    private Button btnSprKa;
    @FXML
    private Button btnPrStages;
    @FXML
    private Button btnDocPurchase;
    @FXML
    private Button btnDocManufacture;
    @FXML
    private Button btnDocSale;
    @FXML
    private Tab tabMain;
    @FXML
    private Button btnOk;
    @FXML
    private Tab tabReport1;
    @FXML
    private Tab tabReport2;
    @FXML
    private Label labelHello;
    @FXML
    private Label labelHello1;

    private static final String fxmlSprNom = "/fxml/tableNom.fxml";
    private static final String fxmlSprKa = "/fxml/tableKA.fxml";
    private static final String fxmlSprPrSt = "/fxml/tablePrStages.fxml";
    private static final String fxmlDocPurchase = "/fxml/tableDocPurchase.fxml";
    private static final String fxmlDocManufacture = "/fxml/tableDocManufacture.fxml";
    private static final String fxmlDocSale = "/fxml/tableDocSale.fxml";

    private Parent fxmlMain;
    private FXMLLoader fxmlLoaderSprNom = new FXMLLoader();
    private FXMLLoader fxmlLoaderSprKa = new FXMLLoader();
    private FXMLLoader fxmlLoaderSprPrSt = new FXMLLoader();
    private FXMLLoader fxmlLoaderDocPurchase = new FXMLLoader();
    private FXMLLoader fxmlLoaderDocManufacture = new FXMLLoader();
    private FXMLLoader fxmlLoaderDocSale = new FXMLLoader();

    private TableNomController tableNomController;
    private TableKAController tableKAController;
    private TablePrStagesController tablePrStagesController;
    private TableDocPurchaseController tableDocPurchaseController;
    private TableDocManufactureController tableDocManufactureController;
    private TableDocSaleController tableDocSaleController;

    private Stage mainStage;
    private Stage stageSprNom;
    private Stage stageSprPrSt;
    private Stage stageSpkKa;
    private Stage stageDocPurchase;
    private Stage stageDocManufacture;
    private Stage stageDocSale;

    private boolean initSprNomController = false;
    private boolean initSprPrStController = false;
    private boolean initSprKaController = false;
    private boolean initDocPurchaseController = false;
    private boolean initDocManufactureController = false;
    private boolean initDocSaleController = false;


    @FXML
    private void showStageSprNom () {
        if(stageSprNom == null) {
            stageSprNom = new Stage();
            stageSprNom.setTitle("Справочник Номенклатура");
            stageSprNom.setMinWidth(800);
            stageSprNom.setMinHeight(600);
            stageSprNom.setResizable(false);
            stageSprNom.setScene(new Scene(fxmlMain));
            stageSprNom.initModality(Modality.WINDOW_MODAL);
            stageSprNom.initOwner(mainStage); //получаем родительское окно - источник события
        }
        stageSprNom.showAndWait();
//        stageSprNom.show();
    }

    @FXML
    private void showStageSprPrSt() {
        if(stageSprPrSt == null) {
            stageSprPrSt = new Stage();
            stageSprPrSt.setTitle("Справочник Этапы производства");
            stageSprPrSt.setMinWidth(800);
            stageSprPrSt.setMinHeight(600);
            stageSprPrSt.setResizable(true);
            stageSprPrSt.setScene(new Scene(fxmlMain));
            stageSprPrSt.initModality(Modality.WINDOW_MODAL);
            stageSprPrSt.initOwner(mainStage); //получаем родительское окно - источник события
        }
        stageSprPrSt.showAndWait();
    }

    @FXML
    private void showStageSprKa() {
        if(stageSpkKa == null) {
            stageSpkKa = new Stage();
            stageSpkKa.setTitle("Справочник Контрагенты");
            stageSpkKa.setMinWidth(800);
            stageSpkKa.setMinHeight(600);
            stageSpkKa.setResizable(false);
            stageSpkKa.setScene(new Scene(fxmlMain));
            stageSpkKa.initModality(Modality.WINDOW_MODAL);
            stageSpkKa.initOwner(mainStage); //получаем родительское окно - источник события
        }
        stageSpkKa.showAndWait();
    }

    @FXML
    private void showStageDocPurchase() {
        if(stageDocPurchase == null) {
            stageDocPurchase = new Stage();
            stageDocPurchase.setTitle("Документы по закупкам");
            stageDocPurchase.setMinWidth(800);
            stageDocPurchase.setMinHeight(600);
            stageDocPurchase.setResizable(false);
            stageDocPurchase.setScene(new Scene(fxmlMain));
            stageDocPurchase.initModality(Modality.WINDOW_MODAL);
            stageDocPurchase.initOwner(mainStage); //получаем родительское окно - источник события
        }
        stageDocPurchase.showAndWait();
    }

    @FXML
    private void showStageDocManufacture() {
        if(stageDocManufacture == null) {
            stageDocManufacture = new Stage();
            stageDocManufacture.setTitle("Документы по производству");
            stageDocManufacture.setMinWidth(800);
            stageDocManufacture.setMinHeight(600);
            stageDocManufacture.setResizable(false);
            stageDocManufacture.setScene(new Scene(fxmlMain));
            stageDocManufacture.initModality(Modality.WINDOW_MODAL);
            stageDocManufacture.initOwner(mainStage); //получаем родительское окно - источник события
        }
        stageDocManufacture.showAndWait();
    }

    @FXML
    private void showStageDocSale() {
        if(stageDocSale == null) {
            stageDocSale = new Stage();
            stageDocSale.setTitle("Документы по продажам");
            stageDocSale.setMinWidth(800);
            stageDocSale.setMinHeight(600);
            stageDocSale.setResizable(false);
            stageDocSale.setScene(new Scene(fxmlMain));
            stageDocSale.initModality(Modality.WINDOW_MODAL);
            stageDocSale.initOwner(mainStage); //получаем родительское окно - источник события
        }
        stageDocSale.showAndWait();
    }

    private void initFxmlLoaderSprNom() {
        try {
            this.fxmlLoaderSprNom.setLocation(getClass().getResource("/fxml/tableNom.fxml"));
            fxmlMain = fxmlLoaderSprNom.load();
            tableNomController  = fxmlLoaderSprNom.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initFxmlLoaderSprPrSt() {
        try {
            this.fxmlLoaderSprPrSt.setLocation(getClass().getResource("/fxml/tablePrStages.fxml"));
            fxmlMain = fxmlLoaderSprPrSt.load();
            tablePrStagesController  = fxmlLoaderSprPrSt.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initFxmlLoaderSprKa() {
        try {
            this.fxmlLoaderSprKa.setLocation(getClass().getResource("/fxml/tableKA.fxml"));
            fxmlMain = fxmlLoaderSprKa.load();
            tableKAController  = fxmlLoaderSprKa.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initFxmlLoaderDocPurchase() {
        try {
            this.fxmlLoaderDocPurchase.setLocation(getClass().getResource("/fxml/tableDocPurchase.fxml"));
            fxmlMain = fxmlLoaderDocPurchase.load();
            tableDocPurchaseController  = fxmlLoaderDocPurchase.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initFxmlLoaderDocManufacture() {
        try {
            this.fxmlLoaderDocManufacture.setLocation(getClass().getResource("/fxml/tableDocManufacture.fxml"));
            fxmlMain = fxmlLoaderDocManufacture.load();
            tableDocManufactureController  = fxmlLoaderDocManufacture.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initFxmlLoaderDocSale() {
        try {
            this.fxmlLoaderDocSale.setLocation(getClass().getResource("/fxml/tableDocSale.fxml"));
            fxmlMain = fxmlLoaderDocSale.load();
            tableDocSaleController  = fxmlLoaderDocSale.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    @FXML
    void actionButtonPressed(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();
        if(!(source instanceof Button)) {
            return;
        }

        Button clickedButton = (Button) source;

        switch (clickedButton.getId()) {
            case "btnSprNom" :
                if (initSprNomController == false) {
                    initFxmlLoaderSprNom();
                    initSprNomController = true;
                }
                showStageSprNom();
                break;
            case "btnSprKa":
                if (initSprKaController == false) {
                    initFxmlLoaderSprKa();
                    initSprKaController = true;
                }
                showStageSprKa();
                break;
            case "btnPrStages":
                if (initSprPrStController  == false) {
                    initFxmlLoaderSprPrSt();
                    initSprPrStController = true;
                }
                showStageSprPrSt();
                break;
            case "btnDocPurchase":
                if (initDocPurchaseController   == false) {
                    initFxmlLoaderDocPurchase();
                    initDocPurchaseController = true;
                }
                showStageDocPurchase();
                break;
            case "btnDocManufacture":
                if (initDocManufactureController    == false) {
                    initFxmlLoaderDocManufacture();
                    initDocManufactureController = true;
                }
                showStageDocManufacture();
                break;
            case "btnDocSale":
                if (initDocSaleController    == false) {
                    initFxmlLoaderDocSale();
                    initDocSaleController = true;
                }
                showStageDocSale();
                break;
            case "btnOk" :
                labelHello.setText("Добро пожаловать в систему");
                labelHello1.setText("Учет хозяйственной деятельности художественной мастерской");
                break;
        }

    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    public boolean isInitSprNomController() {
        return initSprNomController;
    }
}
