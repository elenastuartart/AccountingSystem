package com.stuart.controllers.документПроизводство;

import com.stuart.interfaces.impls.справочники.HibernateSprNom;
import com.stuart.interfaces.impls.справочники.HibernateSprPrStages;
import com.stuart.models.entity.справочники.ЗаписьНоменклатура;
import com.stuart.models.entity.справочники.ЗаписьЭтапыПроизводства;
import com.stuart.objectsFX.документы.ТабЧастьЗакупкаFX;
import com.stuart.objectsFX.документы.ТабЧастьИзрасходованоПроизводствоFX;
import com.stuart.objectsFX.справочники.ЗаписьНоменклатураFX;
import com.stuart.objectsFX.справочники.ЗаписьЭтапыПроизводстваFX;
import com.stuart.utils.DialogManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.sql.SQLException;

public class EditTabConsumedManufactureController {

    @FXML
    private TextField txtNumberStr;
    @FXML
    private ChoiceBox<ЗаписьНоменклатураFX> choiseBoxNomenclature;
    @FXML
    private TextField txtAmount;
    @FXML
    private ChoiceBox<ЗаписьЭтапыПроизводстваFX> choiseBoxPrStage;
    @FXML
    private Button btnSaveOrUpdate;
    @FXML
    private Button btnCansel;

    private ТабЧастьИзрасходованоПроизводствоFX табЧастьИзрасходованоПроизводствоFX;
    private boolean saveClicked = false;
    HibernateSprNom hibernateSprNom  = new HibernateSprNom();
    ЗаписьНоменклатура записьНоменклатура = new ЗаписьНоменклатура();
    HibernateSprPrStages hibernateSprPrStages = new HibernateSprPrStages();
    ЗаписьЭтапыПроизводства записьЭтапыПроизводства = new ЗаписьЭтапыПроизводства();

    @FXML
    void actionClose(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.hide();
    }

    @FXML
    void actionSaveOrUpdate(ActionEvent event) {
        if(!checkValues()) {
            return;
        }
        табЧастьИзрасходованоПроизводствоFX.setNumberStr(Integer.valueOf(txtNumberStr.getText()));
        табЧастьИзрасходованоПроизводствоFX.setAmount(Double.valueOf(txtAmount.getText()));
        табЧастьИзрасходованоПроизводствоFX.setNomenclatureFX_(choiseBoxNomenclature.getValue());
        табЧастьИзрасходованоПроизводствоFX.setPrStageFX_(choiseBoxPrStage.getValue());

        saveClicked = true;
        actionClose(event);
    }

    public void setТабЧастьИзрасходованоПроизводствоFX(ТабЧастьИзрасходованоПроизводствоFX табЧастьИзрасходованоПроизводствоFX) throws SQLException {
        if (табЧастьИзрасходованоПроизводствоFX == null) {
            return;
        }
        saveClicked = false;
        this.табЧастьИзрасходованоПроизводствоFX = табЧастьИзрасходованоПроизводствоFX;
        txtNumberStr.setText(String.valueOf(табЧастьИзрасходованоПроизводствоFX.getNumberStr()));
        txtAmount.setText(String.valueOf(табЧастьИзрасходованоПроизводствоFX.getAmount()));
        this.initChoiseBoxNomenclature();
        this.initChoiseBoxPrStage();
    }

    public ТабЧастьИзрасходованоПроизводствоFX getТабЧастьИзрасходованоПроизводствоFX() {
        return табЧастьИзрасходованоПроизводствоFX;
    }

    private boolean checkValues() {
        if(txtAmount.getText().trim().length() == 0
                || choiseBoxPrStage.getValue() == null
                || choiseBoxNomenclature.getValue() == null) {
            DialogManager.showInfoDialog("Ошибка", "Заполните все поля!");
            return false;
        }
        return true;
    }

    public boolean isSaveClicked() {
        return saveClicked;
    }

    private void initChoiseBoxNomenclature() throws SQLException {
        ObservableList<ЗаписьНоменклатураFX> nomenclatureList = hibernateSprNom.findAll();

        ObservableList<ЗаписьНоменклатураFX> list = FXCollections.observableArrayList();
        for (int i = 0; i < nomenclatureList.size(); i++) {
            list.add(nomenclatureList.get(i));
        }

        this.choiseBoxNomenclature.setValue(this.табЧастьИзрасходованоПроизводствоFX.getNomenclatureFX_());

        this.choiseBoxNomenclature.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    try {
                        choiseBoxNomenclature.setItems(list);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    if (choiseBoxNomenclature.getValue() != null) {

                        choiseBoxNomenclature.setOnAction(event1 ->
                        {
                            choiseBoxNomenclature.setValue(choiseBoxNomenclature.getValue());
                            табЧастьИзрасходованоПроизводствоFX.setNomenclatureFX_(choiseBoxNomenclature.getValue());
                        });
                    }
                }
            }
        });

    }

    private void initChoiseBoxPrStage() throws SQLException {
        ObservableList<ЗаписьЭтапыПроизводстваFX> prStagesList = hibernateSprPrStages.findAll();

        ObservableList<ЗаписьЭтапыПроизводстваFX> list = FXCollections.observableArrayList();
        for (int i = 0; i < prStagesList.size(); i++) {
            list.add(prStagesList.get(i));
        }

        this.choiseBoxPrStage.setValue(this.табЧастьИзрасходованоПроизводствоFX.getPrStageFX_());

        this.choiseBoxPrStage.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    try {
                        choiseBoxPrStage.setItems(list);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    if (choiseBoxPrStage.getValue() != null) {

                        choiseBoxPrStage.setOnAction(event1 ->
                        {
                            choiseBoxPrStage.setValue(choiseBoxPrStage.getValue());
                            табЧастьИзрасходованоПроизводствоFX.setPrStageFX_(choiseBoxPrStage.getValue());
                        });
                    }
                }
            }
        });


    }
}
