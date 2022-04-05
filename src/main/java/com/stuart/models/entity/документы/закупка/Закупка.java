package com.stuart.models.entity.документы.закупка;

import com.stuart.models.entity.документы.Документ;
import com.stuart.models.entity.справочники.ЗаписьКонтрагент;
import lombok.AllArgsConstructor;

import java.util.Date;


public class Закупка extends Документ {

    public ЗаписьКонтрагент Контрагент;

    public Закупка(Date Дата, int Номер, ЗаписьКонтрагент контрагент) {
        super(Дата, Номер);
        Контрагент = контрагент;
    }


    @Override
    public void Проведение() {

    }

    @Override
    public void ОтменаПроведения() {

    }

    @Override
    public String toString() {
        return "Закупка " + Номер + " от " + Дата.toString();
    }
}
