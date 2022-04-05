package com.stuart.models.entity.регистры;

import com.stuart.models.entity.ЗаписьБД;
import com.stuart.models.entity.документы.Документ;
import com.stuart.models.entity.справочники.ЗаписьНоменклатура;

import java.util.Date;

public class ЗаписьРегистраТоварыНаСкладах extends ЗаписьБД {
    public Документ Регистратор;
    public Date Дата;
    public String ВидДвижения;
    public ЗаписьНоменклатура Номенклатура;
    public int Количество;
    public int Сумма;

}
