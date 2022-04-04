package com.stuart.models.entity.документы.продажа;

import com.stuart.models.entity.ЗаписьБД;
import com.stuart.models.entity.справочники.ЗаписьНоменклатура;


public class ЗаписьТабличнойЧастиРеализация extends ЗаписьБД {
    public Реализация Документ;
    public int НомерСтроки;
    public ЗаписьНоменклатура Номенклатура;
    public int Количество;
    public int Цена;
    public int Сумма;
}
