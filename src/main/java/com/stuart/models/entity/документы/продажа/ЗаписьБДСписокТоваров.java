package com.stuart.models.entity.документы.продажа;

import com.stuart.models.entity.ЗаписьБД;


public class ЗаписьБДСписокТоваров extends ЗаписьБД {
    //реквизиты ТЧ
    //public int НомерСтроки;
    public String Номенклатура;
    public int Количество;
    public int Цена;
    public int Сумма;


    public ЗаписьБДСписокТоваров(String номенклатура, int количество, int цена) {
        super();
        Номенклатура = номенклатура;
        Количество = количество;
        Цена = цена;
        Сумма = цена * количество;
    }


}
