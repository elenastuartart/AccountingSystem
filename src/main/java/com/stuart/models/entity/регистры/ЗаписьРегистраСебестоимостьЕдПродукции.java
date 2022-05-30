package com.stuart.models.entity.регистры;

import com.stuart.dao.DataAccessObject;
import com.stuart.models.entity.ЗаписьБД;
import com.stuart.models.entity.документы.Документ;
import lombok.*;
import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "register_costprice", schema = "study_db")
public class ЗаписьРегистраСебестоимостьЕдПродукции extends ЗаписьРегистра {
    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID id = UUID.randomUUID();
    private Date date;
    private Double sumSale; //сумма реализации по строке
    private Double sumCostprice; //сумма себестоимости по строке
    private Double profit;//прибыль со строки
    private Double profitByUnit;//прибыль с единицы
    private Double amount;
    @Column(columnDefinition = "BINARY(16)")
    private UUID idDoc;
    private String typeDoc;
    @Column(columnDefinition = "BINARY(16)")
    private UUID idNom;
    @Transient
    private Документ registrarDoc;

    public static ЗаписьРегистраСебестоимостьЕдПродукции findObjectByValue(String fieldName, Object fieldValue) {
        return (ЗаписьРегистраСебестоимостьЕдПродукции) DataAccessObject.findObjectByValue(getType(), fieldName, fieldValue);
    }

    public static List<ЗаписьРегистраСебестоимостьЕдПродукции> findObjectsByValue(String fieldName, Object fieldValue){
        List<ЗаписьРегистраСебестоимостьЕдПродукции> Result = new ArrayList<ЗаписьРегистраСебестоимостьЕдПродукции>();
        List<ЗаписьБД> Записи = DataAccessObject.findObjectsByValue(getType(), fieldName, fieldValue);
        for(var Запись:Записи) Result.add((ЗаписьРегистраСебестоимостьЕдПродукции)Запись);
        return Result;
    }

    public static String getType() {
        return "ЗаписьРегистраСебестоимостьЕдПродукции";
    }

    @Override
    public boolean ПередЗаписью() {
        if (this.getDate() == null
                || this.sumSale == null
                || this.idDoc == null
                || this.typeDoc == null
                || this.idNom == null
                || this.profit == null
                || this.profitByUnit == null
                || this.sumCostprice == null
                || this.sumSale == null)
            return false;
        else
            return true;
    }

    @Override
    public String toString() {
        return "ЗаписьРегистраВзаиморасчеты{" +
                "Дата: " + date.toString() +

                "; Сумма: " + sumSale +
                '}';
    }
}
