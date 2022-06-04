package com.stuart.controllers.документЗакупка;

import com.stuart.interfaces.impls.документы.HibernateTabPurchase;
import com.stuart.interfaces.impls.справочники.HibernateSprNom;
import com.stuart.models.entity.справочники.ЗаписьНоменклатура;
import com.stuart.objectsFX.документы.ТабЧастьЗакупкаFX;
import com.stuart.objectsFX.справочники.ЗаписьКонтрагентFX;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.sql.SQLException;

public class EditTabPurchaseController {

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

    private ТабЧастьЗакупкаFX табЧастьЗакупкаFX;
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
        табЧастьЗакупкаFX.setNumberStr(Integer.valueOf(txtNumberStr.getText()));
        табЧастьЗакупкаFX.setAmount(Double.valueOf(txtAmount.getText()));
        табЧастьЗакупкаFX.setPrice(Double.valueOf(txtPrice.getText()));
        табЧастьЗакупкаFX.setSum(Double.valueOf(txtSum.getText()));
        табЧастьЗакупкаFX.setNomenclatureFX_(choiseBoxNomenclature.getValue());

        saveClicked = true;
        actionClose(event);

    }

    public void setТабЧастьЗакупкаFX(ТабЧастьЗакупкаFX табЧастьЗакупкаFX) throws SQLException {
        if (табЧастьЗакупкаFX == null){
            return;
        }
        saveClicked = false;
        this.табЧастьЗакупкаFX = табЧастьЗакупкаFX;
        txtNumberStr.setText(String.valueOf(табЧастьЗакупкаFX.getNumberStr()));
        txtAmount.setText(String.valueOf(табЧастьЗакупкаFX.getAmount()));
        txtPrice.setText(String.valueOf(табЧастьЗакупкаFX.getPrice()));
        txtSum.setText(String.valueOf(табЧастьЗакупкаFX.getSum()));
        this.initChoiseBox();
    }

    public ТабЧастьЗакупкаFX getТабЧастьЗакупкаFX() {
        return табЧастьЗакупкаFX;
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

        this.choiseBoxNomenclature.setValue(this.табЧастьЗакупкаFX.getNomenclatureFX_());

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
                            табЧастьЗакупкаFX.setNomenclatureFX_(choiseBoxNomenclature.getValue());
                        });
                    }
                }
            }
        });

    }
}
