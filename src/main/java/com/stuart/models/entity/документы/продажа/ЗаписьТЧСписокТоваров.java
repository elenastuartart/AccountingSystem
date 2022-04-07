package com.stuart.models.entity.документы.продажа;

import com.stuart.models.entity.ЗаписьБД;
import com.stuart.models.entity.справочники.ЗаписьНоменклатура;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ЗаписьТЧСписокТоваров extends ЗаписьБД {
    //реквизиты ТЧ
    public Integer НомерСтроки;
    public ЗаписьНоменклатура Номенклатура;
    public Integer Количество;
    public Integer Цена;
    public Integer Сумма;


    public ЗаписьТЧСписокТоваров(ЗаписьНоменклатура номенклатура, Integer количество, Integer цена) {
        Номенклатура = номенклатура;
        Количество = количество;
        Цена = цена;
        Сумма = цена * количество;
    }

    @Override
    public boolean ПередЗаписью() {
        if (this.getНомерСтроки() == null || this.getНоменклатура() == null
                || this.getКоличество() == null || this.getЦена() == null
                || this.getСумма() == null)
            return false;
        else
            return true;
    }
}
