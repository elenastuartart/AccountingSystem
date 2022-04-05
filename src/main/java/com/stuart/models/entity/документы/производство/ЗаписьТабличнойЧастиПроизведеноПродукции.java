package com.stuart.models.entity.документы.производство;

import com.stuart.models.entity.ЗаписьБД;
import com.stuart.models.entity.справочники.ЗаписьНоменклатура;


public class ЗаписьТабличнойЧастиПроизведеноПродукции extends ЗаписьБД {
    public Производство Документ;
    public int НомерСтроки;
    public ЗаписьНоменклатура Номенклатура;
    public int Количество;
}
