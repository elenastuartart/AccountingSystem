package com.stuart.models.entity.справочники;

import com.stuart.models.entity.ЗаписьБД;

import java.util.Random;


public class ЭлементСправочника extends ЗаписьБД {

    private static Random _rnd;
    public static Integer GetRandomCode(){
        if (_rnd == null)
            _rnd = new Random();

        return _rnd.nextInt(99999999);
    }

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
