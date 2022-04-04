package com.stuart.models.entity.регистры;

import models.ЗаписьБД;
import models.документ.Документ;
import models.справочники.ЗаписьНоменклатура;

import java.util.Date;

public class ЗаписьРегистраТоварыНаСкладах extends ЗаписьБД {
    public Документ Регистратор;
    public Date Дата;
    public String ВидДвижения;
    public ЗаписьНоменклатура Номенклатура;
    public int Количество;
    public int Сумма;

}
