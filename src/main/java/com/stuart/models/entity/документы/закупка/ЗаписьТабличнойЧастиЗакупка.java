package com.stuart.models.entity.документы.закупка;

import com.stuart.models.entity.ЗаписьБД;
import com.stuart.models.entity.справочники.ЗаписьНоменклатура;


public class ЗаписьТабличнойЧастиЗакупка extends ЗаписьБД {
    public Закупка Документ;
    public int НомерСтроки;
    public ЗаписьНоменклатура Номенклатура;
    public int Количество;
    public int Цена;
    public int Сумма;
}
