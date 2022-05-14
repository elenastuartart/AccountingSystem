package com.stuart.models.entity.документы;

import com.stuart.models.entity.ЗаписьБД;
import lombok.*;
import org.hibernate.Session;

import java.util.Random;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Документ extends ЗаписьБД {

    private static Random _rnd;
    public static Integer GetRandomNum(){
        if (_rnd == null)
            _rnd = new Random();

        return _rnd.nextInt(99999999);
    }

    private boolean ПометкаПроведения;

    public Документ getДокумент (Session session) {
        return null;
    }

    @Override
    protected boolean ПередЗаписью() {
        return super.ПередЗаписью();
    }

    protected boolean ЗаписатьРегистрыВзаиморасчетов() {
        return true;
    }

    protected boolean ЗаписатьРегистрыТоварыНаСкладе() {
        return true;
    }

    protected boolean ЗаписатьТабЧасти() {
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
            if(this.ЗаписатьРегистрыВзаиморасчетов()==false) {
                System.out.println("Не удалось записать регистры");
                result = false;
            }
        }
        if(result==false)
            System.out.println("Не удалось записать документ");

        return  result;
    }

    public boolean Проведение() {
        return true;
    }

    public boolean ОтменаПроведения() {
        return true;
    }

    protected boolean ОчисткаРегистров() {
        return true;
    }

    protected boolean ПроверкаНаличия() { return true; }
}
