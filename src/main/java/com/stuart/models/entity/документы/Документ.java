package com.stuart.models.entity.документы;

import com.stuart.models.entity.ЗаписьБД;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.SessionFactory;

@Getter
@Setter
public class Документ extends ЗаписьБД {

    public boolean ПометкаПроведения;

    @Override
    public boolean ПередЗаписью() {
        return super.ПередЗаписью();
    }

    public boolean ЗаписатьРегистры() {
        return true;
    }

    public boolean ЗаписатьТабЧасти() {
        return true;
    }

    public boolean Проведение() {

        boolean result = true;
        ПометкаПроведения = true;
        if (ЗаписатьДокумент()==false) {
            result = false;
        }
        return result;
    }

    public boolean ЗаписатьДокумент() {

        boolean result = true;
        if(this.ПередЗаписью() == false || this.save()==false) {
            result = false;
        }
        if(result!=false) {
            if(this.ЗаписатьТабЧасти()==false) {
                result = false;
            }
        }
        if (result!=false && ПометкаПроведения==true) {
            if(this.ЗаписатьРегистры()==false) {
                result = false;
            }
        }
        return  result;
    }
}
