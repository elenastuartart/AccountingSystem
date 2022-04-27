package com.stuart.models.entity.документы;

import com.stuart.models.entity.ЗаписьБД;
import com.stuart.models.entity.документы.закупка.Закупка;
import com.stuart.models.entity.документы.продажа.Реализация;
import com.stuart.models.entity.регистры.ЗаписьРегистраВзаиморасчеты;
import jdk.jfr.MetadataDefinition;
import lombok.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.Any;
import org.hibernate.annotations.AnyMetaDef;
import org.hibernate.annotations.MetaValue;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Документ extends ЗаписьБД {

    private boolean ПометкаПроведения;

    public Документ getДокумент (Session session) {
        return null;
    }

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

    public boolean ЗаписатьДокумент() {

        boolean result = true;

        if(this.ПередЗаписью() == false) {
            System.out.println("Не прошло проверку перед записью");
            result = false;
        }
        if(result!=false) {
            if (this.save() == false) {
                System.out.println("Ошибка при записи документа");
                result = false;
            }
        }
        if(result!=false) {
            if(this.ЗаписатьТабЧасти()==false) {
                System.out.println("Не удалось записать табличную часть");
                result = false;
            }
        }
        if (result!=false && ПометкаПроведения==true) {
            if(this.ЗаписатьРегистры()==false) {
                System.out.println("Не удалось записать регистры");
                result = false;
            }
        }
        if(result==false)
            System.out.println("Не удалось записать документ");

        return  result;
    }

    public static ЗаписьБД ПолучитьСтрокуТЧ() {
        return null;
    }

    public boolean Проведение() {

        return true;
    }
}
