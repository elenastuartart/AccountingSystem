package com.stuart.models.entity.документы.продажа;

import com.stuart.models.entity.ЗаписьБД;
import com.stuart.models.entity.документы.Документ;
import com.stuart.models.entity.справочники.ЗаписьКонтрагент;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;

@Getter
@Setter
public class Реализация extends Документ {

    //основные реквизиты:
    //дата
    //номер
    //пометка проведения
    public Date Дата;
    public Integer Номер;
    public boolean ПометкаПроведения;
    //Допольнительные реквизиты
    public ЗаписьКонтрагент Контрагент;
    public Integer ИтоговаяСумма;

   //табличная часть документа
    @Getter
    @Setter
    public ArrayList<ЗаписьТЧСписокТоваров> ТабличнаяЧасть = new ArrayList<>();

    //записываем основные реквизиты
    public Реализация(Date дата, Integer номер, ЗаписьКонтрагент контрагент) {
        Дата = дата;
        Номер = номер;
        this.ПометкаПроведения = false;
        Контрагент = контрагент;
    }

    //пользователь передает данные, заполняем табличную часть
    public void ДобавитьЗаписьВТабличнуюЧасть(ЗаписьТЧСписокТоваров Запись) {
        if(ТабличнаяЧасть.isEmpty()) {
            Запись.НомерСтроки = 1;
        }
        else {
            Запись.НомерСтроки = ТабличнаяЧасть.size() + 1;
        }
        this.ТабличнаяЧасть.add(Запись); //по мере создания записей добавляем их в табличную часть документа
    }

    public void ПосчитатьИтоговуюСумму() {
        int sum = 0;
        for (int i = 0; i < ТабличнаяЧасть.size(); i++) {
            ЗаписьТЧСписокТоваров запись = ТабличнаяЧасть.get(i);
            sum = sum + запись.Сумма;
        }
        this.ИтоговаяСумма = sum;
    }

    @Override
    public boolean ПередЗаписью() {

        if ((this.getДата() == null || this.getНомер() == null
                || this.getКонтрагент() == null || this.getИтоговаяСумма() == null
                || this.getТабличнаяЧасть() == null))
            return false;
        else
            return true;
    }

    @Override
    public String toString() {
        return "Реализация " + Номер + " от "
                + Дата.toString() + " на сумму " + ИтоговаяСумма
                + " контрагент " + Контрагент.getНаименование() + " проведение " + ПометкаПроведения ;
    }

    public void toStringTable () {
        for (int i = 0; i < this.ТабличнаяЧасть.size(); i++) {
            System.out.println( ТабличнаяЧасть.get(i).НомерСтроки + " " +
                    ТабличнаяЧасть.get(i).Номенклатура.getНаименование() +" " +
                    ТабличнаяЧасть.get(i).Цена + " руб. " +
                    ТабличнаяЧасть.get(i).Количество + " " +
                    ТабличнаяЧасть.get(i).Сумма )  ;
            System.out.println( );
        }
    }

}
