package com.stuart.models.entity.документы.продажа;

import models.документ.Документ;
import models.справочники.ЗаписьКонтрагент;

public class Реализация extends Документ {

    public ЗаписьКонтрагент Контрагент;

    public Реализация() {

    }

    @Override
    public void ОбработкаПроведения() {

    }

    @Override
    public String toString() {
        return "Реализация " + Номер + " от " + Дата.toString();
    }
}
