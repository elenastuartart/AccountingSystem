package com.stuart.models.entity.справочники;

import com.stuart.models.entity.ЗаписьБД;
import org.hibernate.SessionFactory;


public class ЭлементСправочника extends ЗаписьБД {

    @Override
    public boolean ПередЗаписью() {
        return super.ПередЗаписью();
    }

    public boolean ЗаписатьСправочник(SessionFactory factory) {
        boolean result = true;
        if(this.ПередЗаписью() == false || this.ДобавитьЗапись_в_БД(factory)==false) {
            result = false;
        }
        return  result;
    }
}
