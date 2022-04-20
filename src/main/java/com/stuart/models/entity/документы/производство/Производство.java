package com.stuart.models.entity.документы.производство;

import com.stuart.models.entity.документы.Документ;
import com.stuart.models.entity.регистры.ЗаписьРегистраТоварыНаСкладах;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "doc_manufacture", schema = "study_db")
public class Производство extends Документ {
    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID id = UUID.randomUUID();
    private Date date;
    private Integer number;
    private boolean pometkaProvedeniya;
    @OneToMany(mappedBy = "doc_manufacture_", fetch = FetchType.LAZY)
    private List<ЗаписьТЧРасходМатериалов> table_part_material_consuption_
            = new ArrayList<>(); //Производство-ЗаписьТЧРасходМатериалов
                                 //таблица БД "doc_manufacture"-"table_part_material_consuption"

    @OneToMany (mappedBy = "doc_manufacture_", fetch = FetchType.LAZY)
    private List<ЗаписьТЧПроизведеноПродукции> table_part_produced_of_products_
            = new ArrayList<>(); //Производство-ЗаписьТЧПроизведеноПродукции
                                  //таблица БД "doc_manufacture"-"table_part_produced_of_products"


    public Производство(Date date, Integer number) {
        this.date = date;
        this.number = number;
        pometkaProvedeniya = false;
    }

    public void ЗаполнитьТЧ_Расход(ЗаписьТЧРасходМатериалов запись) {
//        if(РасходМатериалов.isEmpty()) {
//            запись.НомерСтроки = 1;
//        }
//        else {
//            запись.НомерСтроки = РасходМатериалов.size() + 1;
//        }
        this.table_part_material_consuption_.add(запись); //по мере создания записей добавляем их в табличную часть документа
    }

    public void ЗаполнитьТЧ_Произведено(ЗаписьТЧПроизведеноПродукции запись) {
//        if(РасходМатериалов.isEmpty()) {
//            запись.НомерСтроки = 1;
//        }
//        else {
//            запись.НомерСтроки = РасходМатериалов.size() + 1;
//        }
        this.table_part_produced_of_products_.add(запись); //по мере создания записей добавляем их в табличную часть документа
    }

    @Override
    public boolean ПередЗаписью() {
        if ((this.getDate() == null || this.getNumber() == null))
            return false;
        else
            return true;
    }

    @Override
    public boolean ЗаписатьТабЧасти() {

        boolean result1 = true;
        boolean result2 = true;

        for (int i = 0; i < table_part_material_consuption_.size(); i ++) {
            var СтрТЧ = table_part_material_consuption_.get(i);
            СтрТЧ.setLineNumber(i+1);
            if(СтрТЧ.save() == false) {
                result1 = false;
            }
        }

        for (int i = 0; i < table_part_produced_of_products_.size(); i ++) {
            var СтрТЧ = table_part_produced_of_products_.get(i);
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
        for (int i = 0; i < table_part_material_consuption_.size(); i ++) {
            ЗаписьТЧРасходМатериалов записьТЧРасходМатериалов =
                    table_part_material_consuption_.get(i);
            ЗаписьРегистраТоварыНаСкладах СтрРегистраРасход = new ЗаписьРегистраТоварыНаСкладах();
            СтрРегистраРасход.setРегистратор(this);
            СтрРегистраРасход.setНоменклатура(записьТЧРасходМатериалов.getNomenclature_());
            СтрРегистраРасход.setКоличество(записьТЧРасходМатериалов.getAmount());
            СтрРегистраРасход.setСумма((double)0);
            if(СтрРегистраРасход.save() == false) {
                result1 = false;
            }
        }

        for (int i = 0; i < table_part_produced_of_products_.size(); i ++) {
            ЗаписьТЧПроизведеноПродукции записьТЧПроизведеноПродукции =
                    table_part_produced_of_products_.get(i);
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
        return "Производство № " + number + " от "
                + date.toString() ;
    }
}
