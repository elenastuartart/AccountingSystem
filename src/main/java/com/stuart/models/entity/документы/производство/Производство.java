package com.stuart.models.entity.документы.производство;

import com.stuart.models.entity.документы.Документ;
import com.stuart.models.entity.регистры.ЗаписьРегистраТоварыНаСкладах;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class Производство extends Документ {

    public UUID Код;
    public Date Дата;
    public Integer Номер;
    public boolean ПометкаПроведения;
    @OneToMany
    public ArrayList<ЗаписьТЧРасходМатериалов> РасходМатериалов = new ArrayList<>();
    @OneToMany
    public ArrayList<ЗаписьТЧПроизведеноПродукции> ПроизведеноПродукции = new ArrayList<>();

    public Производство(Date дата, Integer номер) {
        Дата = дата;
        Номер = номер;
        ПометкаПроведения = false;
    }


    public void ЗаполнитьТЧ_Расход(ЗаписьТЧРасходМатериалов запись) {
//        if(РасходМатериалов.isEmpty()) {
//            запись.НомерСтроки = 1;
//        }
//        else {
//            запись.НомерСтроки = РасходМатериалов.size() + 1;
//        }
        this.РасходМатериалов.add(запись); //по мере создания записей добавляем их в табличную часть документа
    }

    public void ЗаполнитьТЧ_Произведено(ЗаписьТЧПроизведеноПродукции запись) {
//        if(РасходМатериалов.isEmpty()) {
//            запись.НомерСтроки = 1;
//        }
//        else {
//            запись.НомерСтроки = РасходМатериалов.size() + 1;
//        }
        this.ПроизведеноПродукции.add(запись); //по мере создания записей добавляем их в табличную часть документа
    }

    @Override
    public boolean ПередЗаписью() {
        if ((this.getДата() == null || this.getНомер() == null))
            return false;
        else
            return true;
    }

    @Override
    public boolean ЗаписатьТабЧасти() {

        boolean result1 = true;
        boolean result2 = true;

        for (int i = 0; i < РасходМатериалов.size(); i ++) {
            var СтрТЧ = РасходМатериалов.get(i);
            СтрТЧ.setLineNumber(i+1);
            if(СтрТЧ.save() == false) {
                result1 = false;
            }
        }

        for (int i = 0; i < ПроизведеноПродукции.size(); i ++) {
            var СтрТЧ = ПроизведеноПродукции.get(i);
            СтрТЧ.setLineNumber(i+1);
            if(СтрТЧ.save() == false) {
                result2 = false;
            }
        }

        if(result1 == true && result2 == true)
            return true;
        else
            return false;
    }

    @Override
    public boolean ЗаписатьРегистры() {
        boolean result1 = true;
        boolean result2 = true;
        for (int i = 0; i < РасходМатериалов.size(); i ++) {
            ЗаписьТЧРасходМатериалов записьТЧРасходМатериалов =
                    РасходМатериалов.get(i);
            ЗаписьРегистраТоварыНаСкладах СтрРегистраРасход = new ЗаписьРегистраТоварыНаСкладах();
            СтрРегистраРасход.setРегистратор(this);
            СтрРегистраРасход.setНоменклатура(записьТЧРасходМатериалов.getNomenclature_());
            СтрРегистраРасход.setКоличество(записьТЧРасходМатериалов.getAmount());
            СтрРегистраРасход.setСумма((double)0);
            if(СтрРегистраРасход.save() == false) {
                result1 = false;
            }
        }

        for (int i = 0; i < ПроизведеноПродукции.size(); i ++) {
            ЗаписьТЧПроизведеноПродукции записьТЧПроизведеноПродукции =
                    ПроизведеноПродукции.get(i);
            ЗаписьРегистраТоварыНаСкладах СтрРегистраПроизведено = new ЗаписьРегистраТоварыНаСкладах();
            СтрРегистраПроизведено.setРегистратор(this);
            СтрРегистраПроизведено.setНоменклатура(записьТЧПроизведеноПродукции.getNomenclature_());
            СтрРегистраПроизведено.setКоличество(записьТЧПроизведеноПродукции.getAmount());
            СтрРегистраПроизведено.setСумма((double) 0);
            if(СтрРегистраПроизведено.save() == false) {
                result2 = false;
            }
        }

        if(result1 == true && result2 == true)
            return true;
        else
            return false;

    }

    @Override
    public String toString() {
        return "Производство № " + Номер + " от "
                + Дата.toString() ;
    }
}
