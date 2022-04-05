package com.stuart.models.entity.документы;

import com.stuart.models.entity.ЗаписьБД;
import com.stuart.models.entity.ОбъектБД;
import lombok.AllArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
public abstract class Документ extends ОбъектБД {

    public Date Дата;
    public int Номер;

    public Документ() {
        super();
    }


    //public List <ЗаписьТЧ> КоллекцияЗаписей;

    public abstract void Проведение();

    public abstract void ОтменаПроведения();

    @Override
    public void ПередЗаписью() {

    }

    public void ЗаписатьТабЧастиВКоллекцию(List <ЗаписьБД> КоллекцияЗаписей) {

    }


}
