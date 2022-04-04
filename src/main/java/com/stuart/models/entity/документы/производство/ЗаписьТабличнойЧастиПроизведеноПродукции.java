package com.stuart.models.entity.документы.производство;

import models.ЗаписьБД;
import models.справочники.ЗаписьНоменклатура;

public class ЗаписьТабличнойЧастиПроизведеноПродукции extends ЗаписьБД {
    public Производство Документ;
    public int НомерСтроки;
    public ЗаписьНоменклатура Номенклатура;
    public int Количество;
}
