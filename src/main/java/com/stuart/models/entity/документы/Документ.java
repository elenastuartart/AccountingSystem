package com.stuart.models.entity.документы;

import com.stuart.models.entity.ОбъектБД;

import java.util.Date;

public abstract class Документ extends ОбъектБД {

    public Date Дата;
    public int Номер;

    public abstract void ОбработкаПроведения();

    @Override
    public void ПередЗаписью() {

    }

}
