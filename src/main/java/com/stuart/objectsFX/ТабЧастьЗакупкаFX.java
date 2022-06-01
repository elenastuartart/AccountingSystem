package com.stuart.objectsFX;

import com.stuart.models.entity.справочники.ЗаписьНоменклатура;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
@NoArgsConstructor
@AllArgsConstructor
public class ТабЧастьЗакупкаFX extends ObjectFX {
    @Getter
    @Setter
    private UUID id;
    private SimpleIntegerProperty numberStr  = new SimpleIntegerProperty();
    private SimpleDoubleProperty amount = new SimpleDoubleProperty();
    private SimpleDoubleProperty price = new SimpleDoubleProperty();
    private SimpleDoubleProperty sum = new SimpleDoubleProperty();
    private SimpleStringProperty nomenclature = new SimpleStringProperty();

    private ЗаписьНоменклатураFX nomenclature_ = new ЗаписьНоменклатураFX();
    private UUID idDocPurchaseFX_;

    public Integer getNumberStr() {
        return numberStr.get();
    }

    public Double getAmount() {
        return amount.get();
    }

    public Double getPrice() {
        return price.get();
    }

    public Double getSum() {
        return sum.get();
    }

    public String getNomenclature() {
        return nomenclature.get();
    }

    public ЗаписьНоменклатураFX getNomenclature_() {
        return nomenclature_;
    }

    public UUID getIdDocPurchaseFX_() {
        return idDocPurchaseFX_;
    }

    public void setNumberStr(Integer numberStr) {
        this.numberStr.set(numberStr);
    }

    public void setAmount(Double amount) {
        this.amount.set(amount);
    }

    public void setPrice(Double price) {
        this.price.set(price);
    }

    public void setSum(double sum) {
        this.sum.set(sum);
    }

    public void setNomenclature(ЗаписьНоменклатура записьНоменклатура) {
        this.nomenclature = this.nomenclature_.nameProperty();
    }

    public void setNomenclature_(ЗаписьНоменклатураFX nomenclature_) {
        this.nomenclature_ = nomenclature_;
    }

    public void setIdDocPurchaseFX_(UUID idDocPurchaseFX_) {
        this.idDocPurchaseFX_ = idDocPurchaseFX_;
    }

    public SimpleIntegerProperty numberStrProperty() {
        return numberStr;
    }

    public SimpleDoubleProperty amountProperty() {
        return amount;
    }

    public SimpleDoubleProperty priceProperty() {
        return price;
    }

    public SimpleDoubleProperty sumProperty() {
        return sum;
    }

    public SimpleStringProperty nomenclatureProperty() {
        return nomenclature;
    }

}
