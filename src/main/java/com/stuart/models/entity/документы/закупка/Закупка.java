package com.stuart.models.entity.документы.закупка;

import com.stuart.models.entity.документы.Документ;
import com.stuart.models.entity.справочники.ЗаписьКонтрагент;


public class Закупка extends Документ {

    public ЗаписьКонтрагент Контрагент;

    public Закупка() {

    }

    @Override
    public void ОбработкаПроведения() {

    }

    @Override
    public String toString() {
        return "Закупка " + Номер + " от " + Дата.toString();
    }
}
