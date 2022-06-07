package com.stuart.objectsFX.регистры;

import com.stuart.objectsFX.ObjectFX;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
public class РегистрВзаиморасчетыFX extends ObjectFX {

    private Date date = new Date();
    private SimpleStringProperty contragent = new SimpleStringProperty("");
    private SimpleDoubleProperty sum = new SimpleDoubleProperty();

    public Date getDate() {
        return date;
    }

    public String getContragent() {
        return contragent.get();
    }

    public double getSum() {
        return sum.get();
    }

    public void setContragent(String contragent) {
        this.contragent.set(contragent);
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setSum(double sum) {
        this.sum.set(sum);
    }

    public SimpleStringProperty contragentProperty() {
        return contragent;
    }

    public SimpleDoubleProperty sumProperty() {
        return sum;
    }
}
