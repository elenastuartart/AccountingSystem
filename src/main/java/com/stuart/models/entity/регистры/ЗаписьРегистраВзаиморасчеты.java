package com.stuart.models.entity.регистры;

import com.stuart.models.entity.ЗаписьБД;
import com.stuart.models.entity.документы.Документ;
import com.stuart.models.entity.документы.закупка.Закупка;
import com.stuart.models.entity.документы.продажа.Реализация;
import com.stuart.models.entity.справочники.ЗаписьКонтрагент;
import com.stuart.models.entity.справочники.ЗаписьНоменклатура;
import lombok.Getter;
import lombok.Setter;


import java.util.Date;
@Getter
@Setter
public class ЗаписьРегистраВзаиморасчеты extends ЗаписьРегистра {

    public Документ Регистратор;
    public Date Дата;
    public String ВидДвижения;
    public ЗаписьКонтрагент Контрагент;
    //public ЗаписьНоменклатура Номенклатура;
    public Integer Сумма;

    //взаиморасчеты с покупателем - приход
    public ЗаписьРегистраВзаиморасчеты(Реализация регистратор, String видДвижения ) {
        Регистратор = регистратор;
        Дата = регистратор.getДата();
        ВидДвижения = видДвижения;
        Контрагент = регистратор.getКонтрагент();
        Сумма = регистратор.getИтоговаяСумма();
    }

    @Override
    public boolean ПередЗаписью() {
        if (this.getРегистратор() == null || this.getДата() == null
                || this.getВидДвижения() == null || this.getКонтрагент() == null
                || this.getСумма() == null)
            return false;
        else
            return true;
    }

    //    public ЗаписьРегистраВзаиморасчеты(Закупка регистратор, String видДвижения ) {
//        super(регистратор);
//        ВидДвижения = видДвижения;
//        Контрагент = регистратор.getКонтрагент();
//        Сумма = регистратор.getИтоговаяСумма();
//    }


    @Override
    public String toString() {
        return "ЗаписьРегистраВзаиморасчеты{" +
                "Дата: " + Дата.toString() +
                "; Вид движения: " + ВидДвижения +
                "; Контрагент: " + Контрагент.getНаименование() +
                "; Сумма: " + Сумма +
                '}';
    }
}
