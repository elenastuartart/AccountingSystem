package com.stuart.models.entity.регистры;

import com.stuart.dao.записьБД.DataAccessObject;
import com.stuart.models.entity.ЗаписьБД;
import com.stuart.models.entity.документы.Документ;
import com.stuart.models.entity.справочники.ЗаписьКонтрагент;
import com.stuart.models.entity.справочники.ЗаписьНоменклатура;
import lombok.*;
import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "register_calculation", schema = "study_db")
public class ЗаписьРегистраВзаиморасчеты extends ЗаписьРегистра {
    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID id = UUID.randomUUID();
    private Date date;
    private Double sum;
    //ЗаписьРегистраВзаиморасчеты-ЗаписьКонтрагент
    //таблица БД "register_calculation"-"contragent"
    @ManyToOne
    @JoinColumn(name = "contragent_id", referencedColumnName = "id")
    private ЗаписьКонтрагент contragent_;
    @Column(columnDefinition = "BINARY(16)")
    private UUID idDoc;
    private String typeDoc;
    @Transient
    private Документ registrarDoc;

    public static ЗаписьРегистраВзаиморасчеты findObjectByValue(String fieldName, Object fieldValue) {
        return (ЗаписьРегистраВзаиморасчеты) DataAccessObject.findObjectByValue(getType(), fieldName, fieldValue);
    }

    public static List<ЗаписьРегистраВзаиморасчеты> findObjectsByValue(String fieldName, Object fieldValue){
        List<ЗаписьРегистраВзаиморасчеты> Result = new ArrayList<ЗаписьРегистраВзаиморасчеты>();
        List<ЗаписьБД> Записи = DataAccessObject.findObjectsByValue(getType(), fieldName, fieldValue);
        for(var Запись:Записи) Result.add((ЗаписьРегистраВзаиморасчеты)Запись);
        return Result;
    }

    public static String getType() {
        return "ЗаписьРегистраВзаиморасчеты";
    }

    @Override
    public boolean ПередЗаписью() {
        if (this.getDate() == null
                || this.getContragent_()  == null
                || this.getSum() == null
                || this.idDoc == null
                || this.typeDoc == null)
            return false;
        else
            return true;
    }

    @Override
    public String toString() {
        return "ЗаписьРегистраВзаиморасчеты{" +
                "Дата: " + date.toString() +
                "; Контрагент: " + contragent_.getName() +
                "; Сумма: " + sum +
                '}';
    }
}

