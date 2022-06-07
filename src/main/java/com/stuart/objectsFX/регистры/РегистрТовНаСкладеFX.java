package com.stuart.objectsFX.регистры;

import com.stuart.objectsFX.ObjectFX;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class РегистрТовНаСкладеFX extends ObjectFX {

    private SimpleStringProperty nomenclature = new SimpleStringProperty("");
    private SimpleDoubleProperty amount = new SimpleDoubleProperty();
    private SimpleDoubleProperty price = new SimpleDoubleProperty();

    public String getNomenclature() {
        return nomenclature.get();
    }

    public Double getAmount() {
        return amount.get();
    }

    public Double getPrice() {
        return price.get();
    }

    public void setNomenclature(String nomenclature) {
        this.nomenclature.set(nomenclature);
    }

    public void setAmount(double amount) {
        this.amount.set(amount);
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public SimpleStringProperty nomenclatureProperty() {
        return nomenclature;
    }

    public SimpleDoubleProperty amountProperty() {
        return amount;
    }

    public SimpleDoubleProperty priceProperty() {
        return price;
    }
}
