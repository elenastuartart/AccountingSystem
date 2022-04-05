package com.stuart.models.entity.документы.производство;

import com.stuart.models.entity.документы.Документ;
import com.stuart.models.entity.справочники.ЗаписьЭтапыПроизводства;
import lombok.AllArgsConstructor;

import java.util.Date;

public class Производство extends Документ {

    public ЗаписьЭтапыПроизводства Этап;

    public Производство(Date Дата, int Номер) {
        super(Дата, Номер);
    }


    @Override
    public void ОтменаПроведения() {

    }

    @Override
    public void Проведение() {

    }

    @Override
    public String toString() {
        return "Производство " + Номер + " от " + Дата.toString();
    }
}
