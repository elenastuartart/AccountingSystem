package com.stuart.models.entity.документы.производство;

import com.stuart.models.entity.документы.Документ;
import com.stuart.models.entity.справочники.ЗаписьЭтапыПроизводства;

public class Производство extends Документ {

    public ЗаписьЭтапыПроизводства Этап;


    public Производство() {

    }

    @Override
    public void ОбработкаПроведения() {

    }

    @Override
    public String toString() {
        return "Производство " + Номер + " от " + Дата.toString();
    }
}
