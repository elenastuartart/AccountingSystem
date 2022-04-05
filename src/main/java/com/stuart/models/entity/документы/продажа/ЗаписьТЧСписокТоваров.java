package com.stuart.models.entity.документы.продажа;

import com.stuart.models.entity.ЗаписьБД;
import com.stuart.models.entity.справочники.ЗаписьНоменклатура;


public class ЗаписьТЧСписокТоваров extends ЗаписьБД {
    //реквизиты ТЧ
    public int НомерСтроки;
    public ЗаписьНоменклатура Номенклатура;
    public int Количество;
    public int Цена;
    public int Сумма;


    public ЗаписьТЧСписокТоваров(ЗаписьНоменклатура номенклатура, int количество, int цена) {
        Номенклатура = номенклатура;
        Количество = количество;
        Цена = цена;
        Сумма = цена * количество;
    }


}
