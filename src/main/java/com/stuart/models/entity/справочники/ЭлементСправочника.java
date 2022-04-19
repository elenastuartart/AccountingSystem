package com.stuart.models.entity.справочники;

import com.stuart.models.entity.ЗаписьБД;


public class ЭлементСправочника extends ЗаписьБД {

    @Override
    public boolean ПередЗаписью() {
        return super.ПередЗаписью();
    }

    public boolean Записать() {
        boolean result = true;
        if(this.ПередЗаписью() == false || this.save()==false) {
            result = false;
        }
        return  result;
    }

}
