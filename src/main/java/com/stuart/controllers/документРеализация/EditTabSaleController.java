package com.stuart.controllers.документРеализация;

import com.stuart.interfaces.impls.справочники.HibernateSprNom;
import com.stuart.models.entity.справочники.ЗаписьНоменклатура;
import com.stuart.objectsFX.документы.ТабЧастьЗакупкаFX;
import com.stuart.objectsFX.документы.ТабЧастьРеализацияFX;
import com.stuart.objectsFX.справочники.ЗаписьНоменклатураFX;
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

public class EditTabSaleController {

    @FXML
    private TextField txtNumberStr;
    @FXML
    private ChoiceBox<ЗаписьНоменклатураFX> choiseBoxNomenclature;
    @FXML
    private TextField txtAmount;
    @FXML
    private TextField txtPrice;
    @FXML
    private TextField txtSum;
    @FXML
    private Button btnSaveOrUpdate;
    @FXML
    private Button btnCansel;

    private ТабЧастьРеализацияFX табЧастьРеализацияFX;
    private boolean saveClicked = false;
    HibernateSprNom hibernateSprNom  = new HibernateSprNom();
    ЗаписьНоменклатура записьНоменклатура = new ЗаписьНоменклатура();

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
        табЧастьРеализацияFX.setNumberStr(Integer.valueOf(txtNumberStr.getText()));
        табЧастьРеализацияFX.setAmount(Double.valueOf(txtAmount.getText()));
        табЧастьРеализацияFX.setPrice(Double.valueOf(txtPrice.getText()));
        табЧастьРеализацияFX.setSum(Double.valueOf(txtSum.getText()));
        табЧастьРеализацияFX.setNomenclatureFX_(choiseBoxNomenclature.getValue());

        saveClicked = true;
        actionClose(event);

    }

    public void setТабЧастьРеализацияFX(ТабЧастьРеализацияFX табЧастьРеализацияFX) throws SQLException {
        if (табЧастьРеализацияFX == null){
            return;
        }
        saveClicked = false;
        this.табЧастьРеализацияFX = табЧастьРеализацияFX;
        txtNumberStr.setText(String.valueOf(табЧастьРеализацияFX.getNumberStr()));
        txtAmount.setText(String.valueOf(табЧастьРеализацияFX.getAmount()));
        txtPrice.setText(String.valueOf(табЧастьРеализацияFX.getPrice()));
        txtSum.setText(String.valueOf(табЧастьРеализацияFX.getSum()));
        this.initChoiseBox();
    }

    public ТабЧастьРеализацияFX getТабЧастьРеализацияFX() {
        return табЧастьРеализацияFX;
    }

    private boolean checkValues() {
        if(txtAmount.getText().trim().length() == 0
                || txtPrice.getText().trim().length() == 0
                || txtSum.getText().trim().length() == 0
                || choiseBoxNomenclature.getValue() == null) {
            DialogManager.showInfoDialog("Ошибка", "Заполните все поля!");
            return false;
        }
        return true;
    }

    public boolean isSaveClicked() {
        return saveClicked;
    }

    private void initChoiseBox() throws SQLException {

        ObservableList<ЗаписьНоменклатураFX> nomenclatureList = hibernateSprNom.findAll();

        ObservableList<ЗаписьНоменклатураFX> list = FXCollections.observableArrayList();
        for (int i = 0; i < nomenclatureList.size(); i++) {
            list.add(nomenclatureList.get(i));
        }

        this.choiseBoxNomenclature.setValue(this.табЧастьРеализацияFX.getNomenclatureFX_());

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
                            табЧастьРеализацияFX.setNomenclatureFX_(choiseBoxNomenclature.getValue());
                        });
                    }
                }
            }
        });

    }
}
