package com.stuart.objectsFX;

import com.stuart.models.entity.справочники.ЗаписьКонтрагент;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;
@NoArgsConstructor
@AllArgsConstructor
public class ДокументЗакупкаFX extends ObjectFX {
    @Getter
    @Setter
    private UUID id;
    private SimpleStringProperty dateView = new SimpleStringProperty("");
    private SimpleStringProperty provedenie = new SimpleStringProperty("");
    private SimpleIntegerProperty number = new SimpleIntegerProperty();
    private SimpleStringProperty contragent = new SimpleStringProperty("");
    private SimpleDoubleProperty finalSum = new SimpleDoubleProperty();

    @Getter
    @Setter
    private Date date = new Date();

    private ЗаписьКонтрагентFX contragent_ = new ЗаписьКонтрагентFX();
    private ObservableList<ТабЧастьЗакупкаFX> tabParts_ = FXCollections.observableArrayList();

    public ДокументЗакупкаFX(Date dateView, ЗаписьКонтрагент записьКонтрагент) {

    }

    public String getDateView() {
        return dateView.get();
    }

    public String getProvedenie() {
        return provedenie.get();
    }

    public Integer getNumber() {
        return number.get();
    }

    public String getContragent() {
        return contragent.get();
    }

    public Double getFinalSum() {
        return finalSum.get();
    }

    public ЗаписьКонтрагентFX getContragent_() {
        return contragent_;
    }

    public ObservableList<ТабЧастьЗакупкаFX> getTabParts_() {
        return tabParts_;
    }

    public void setDateView(String dateView) {
        this.dateView.set(dateView);
    }

    public void setProvedenie(String provedenie) {
        this.provedenie.set(provedenie);
    }

    public void setNumber(Integer number) {
        this.number.set(number);
    }

    public void setFinalSum(Double finalSum) {
        this.finalSum.set(finalSum);
    }

//    public void setContragent(ЗаписьКонтрагент записьКонтрагент) {
//        this.contragent =  this.contragent_.nameProperty();
//    }

    public void setContragent(ЗаписьКонтрагент записьКонтрагент) {
        this.contragent = new SimpleStringProperty(записьКонтрагент.getName());
    }

    public void setContragent_(ЗаписьКонтрагентFX contragent_) {
        this.contragent_ = contragent_;
    }

    public void setTabParts_(ObservableList<ТабЧастьЗакупкаFX> tabParts_) {
        this.tabParts_ = tabParts_;
    }

    public SimpleStringProperty dateViewProperty() {
        return dateView;
    }

    public SimpleIntegerProperty numberProperty() {
        return number;
    }

    public SimpleDoubleProperty finalSumProperty() {
        return finalSum;
    }

    public SimpleStringProperty provedenieProperty() {
        return provedenie;
    }

    public SimpleStringProperty contragentProperty() {
        return contragent;
    }

}
