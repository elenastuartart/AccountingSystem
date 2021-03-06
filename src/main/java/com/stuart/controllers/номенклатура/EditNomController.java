package com.stuart.controllers.номенклатура;

import com.stuart.interfaces.impls.справочники.HibernateSprKA;
import com.stuart.models.entity.справочники.ЗаписьКонтрагент;
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

public class EditNomController {
    @FXML
    private TextField txtCode;
    @FXML
    private TextField txtArticleNumber;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtCategory;
    @FXML
    private TextField txtSubcategory;
    @FXML
    private ChoiceBox<ЗаписьКонтрагентFX> choiseBoxProducer;
    @FXML
    private Button btnSaveOrUpdate;
    @FXML
    private Button btnCansel;

    private ЗаписьНоменклатураFX записьНоменклатураFX;
    private boolean saveClicked = false;
    private HibernateSprKA hibernateSprKA = new HibernateSprKA();
    ЗаписьКонтрагент записьКонтрагент = new ЗаписьКонтрагент();

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
        записьНоменклатураFX.setCode(Integer.valueOf(txtCode.getText()));
        записьНоменклатураFX.setArticleNumber(Integer.valueOf(txtArticleNumber.getText()));
        записьНоменклатураFX.setName(txtName.getText());
        записьНоменклатураFX.setCategory(txtCategory.getText());
        записьНоменклатураFX.setSubcategory(txtSubcategory.getText());
        записьНоменклатураFX.setContragent(choiseBoxProducer.getValue().getЗаписьКонтрагент_());
        записьНоменклатураFX.setContragentFX_(choiseBoxProducer.getValue());

        saveClicked = true;
        actionClose(event);
    }

    public void setЗаписьНоменклатураFX(ЗаписьНоменклатураFX записьНоменклатураFX) throws SQLException {
        if (записьНоменклатураFX == null){
            return;
        }
        saveClicked = false;
        this.записьНоменклатураFX = записьНоменклатураFX;
        txtCode.setText(записьНоменклатураFX.getCode().toString());
        txtArticleNumber.setText(записьНоменклатураFX.getArticleNumber().toString());
        txtName.setText(записьНоменклатураFX.getName());
        txtCategory.setText(записьНоменклатураFX.getCategory());
        txtSubcategory.setText(записьНоменклатураFX.getSubcategory());
        this.initChoiseBox();
    }

    public ЗаписьНоменклатураFX getЗаписьНоменклатураFX() {
        return записьНоменклатураFX;
    }

    private boolean checkValues() {
        if(txtCode.getText().trim().length() == 0
                || txtArticleNumber.getText().trim().length() == 0
                || txtName.getText().trim().length() == 0
                || txtCategory.getText().trim().length() == 0) {
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

        this.choiseBoxProducer.setValue(this.записьНоменклатураFX.getContragentFX_());

        this.choiseBoxProducer.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    try {
                        choiseBoxProducer.setItems(list);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    if (choiseBoxProducer.getValue() != null) {

//                        Label label = new Label();
                        choiseBoxProducer.setOnAction(event1 ->
                        {
                            choiseBoxProducer.setValue(choiseBoxProducer.getValue());
                            записьНоменклатураFX.setContragentFX_(choiseBoxProducer.getValue());
                        });
                    }
                }
            }
        });

    }
}
