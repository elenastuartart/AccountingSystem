package com.stuart.models.entity.документы.закупка;

import com.stuart.dao.записьБД.DataAccessObject;
import com.stuart.models.entity.ЗаписьБД;
import com.stuart.models.entity.документы.продажа.Реализация;
import com.stuart.models.entity.справочники.ЗаписьНоменклатура;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "table_part_purchase", schema = "study_db")
public class ЗаписьТЧ_Закупка extends ЗаписьБД {

    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID id = UUID.randomUUID();
    private Integer lineNumber;
    private Double amount; //количество
    private Double price; //цена
    private Double sum; //сумма
    //ТЧ_ЗАКУПКА-ЗАПИСЬ_НОМЕНКЛАТУРА связь с классом ЗаписьНоменклатура
    // (таблица БД "table_part_purchase"-"nomenclature"), list purchasesTableParts
    @ManyToOne
    @JoinColumn(name = "nomenclature_id", referencedColumnName = "id")
    private ЗаписьНоменклатура nomenclature_;
    //ЗаписьТЧЗакупка-Закупка
    // (таблица БД "table_part_purchase"-"doc_purchase"), list table_part_purchase_
    @ManyToOne
    @JoinColumn(name = "doc_purchase_id", referencedColumnName = "id")
    private Закупка doc_purchase_;
    @Column(columnDefinition = "BINARY(16)")
    private UUID idDoc;

    public ЗаписьТЧ_Закупка(ЗаписьНоменклатура nomenclature_,
                            Double amount, Double price, Закупка закупка) {
        this.nomenclature_ = nomenclature_;
        this.amount = amount;
        this.price = price;
        this.sum = price * amount;
        this.doc_purchase_ = закупка;
        this.idDoc = закупка.getId();
    }

    public void setSum() {
        this.sum = price * amount;
    }

    public static String getType() {
        return "ЗаписьТЧ_Закупка";
    }

    public static ЗаписьТЧ_Закупка findObjectByValue(String fieldName, Object fieldValue) {
        return (ЗаписьТЧ_Закупка) DataAccessObject.findObjectByValue(getType(), fieldName, fieldValue);
    }

    public static List<ЗаписьТЧ_Закупка> findObjectsByValue(String fieldName, Object fieldValue){
        List<ЗаписьТЧ_Закупка> Result = new ArrayList<ЗаписьТЧ_Закупка>();
        List<ЗаписьБД> Записи = DataAccessObject.findObjectsByValue(getType(), fieldName, fieldValue);
        for(var Запись:Записи) {
            Result.add((ЗаписьТЧ_Закупка)Запись);
        }
        return Result;
    }

    @Override
    public boolean ПередЗаписью() {
        if (this.getNomenclature_() == null
                || this.getAmount() == null || this.getPrice() == null
                || this.getSum() == null || this.doc_purchase_== null
                || this.idDoc == null)
            return false;
        else
            return true;
    }

}
