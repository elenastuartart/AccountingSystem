package com.stuart.objectsFX.регистры;

import com.stuart.objectsFX.ObjectFX;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class РегистрСебестоимостьFX extends ObjectFX {

    private SimpleStringProperty nomenclature = new SimpleStringProperty("");
    private SimpleDoubleProperty amount = new SimpleDoubleProperty();
    private SimpleDoubleProperty profit = new SimpleDoubleProperty();
    private SimpleDoubleProperty profitByUnit = new SimpleDoubleProperty();
    private SimpleDoubleProperty costprice = new SimpleDoubleProperty();

    public String getNomenclature() {
        return nomenclature.get();
    }

    public Double getAmount() {
        return amount.get();
    }

    public Double getProfit() {
        return profit.get();
    }

    public Double getProfitByUnit() {
        return profitByUnit.get();
    }

    public double getCostprice() {
        return costprice.get();
    }

    public void setNomenclature(String nomenclature) {
        this.nomenclature.set(nomenclature);
    }

    public void setAmount(double amount) {
        this.amount.set(amount);
    }

    public void setProfit(double profit) {
        this.profit.set(profit);
    }

    public void setProfitByUnit(double profitByUnit) {
        this.profitByUnit.set(profitByUnit);
    }

    public void setCostprice(double price) {
        this.costprice.set(price);
    }

    public SimpleStringProperty nomenclatureProperty() {
        return nomenclature;
    }

    public SimpleDoubleProperty amountProperty() {
        return amount;
    }

    public SimpleDoubleProperty profitProperty() {
        return profit;
    }

    public SimpleDoubleProperty profitByUnitProperty() {
        return profitByUnit;
    }

    public SimpleDoubleProperty costpriceProperty() {
        return costprice;
    }
}
