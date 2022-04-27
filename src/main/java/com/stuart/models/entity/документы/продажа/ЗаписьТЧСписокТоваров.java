package com.stuart.models.entity.документы.продажа;

import com.stuart.dao.записьБД.DataAccessObject;
import com.stuart.models.entity.ЗаписьБД;
import com.stuart.models.entity.документы.закупка.Закупка;
import com.stuart.models.entity.документы.закупка.ЗаписьТЧ_Закупка;
import com.stuart.models.entity.справочники.ЗаписьНоменклатура;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "table_part_list_of_products", schema = "study_db")
public class ЗаписьТЧСписокТоваров extends ЗаписьБД {

    public static ЗаписьТЧСписокТоваров findObjectByValue(String fieldName, Object fieldValue) {
        return (ЗаписьТЧСписокТоваров) DataAccessObject.findObjectByValue(getType(), fieldName, fieldValue);
    }

    public static List<ЗаписьТЧСписокТоваров> findObjectsByValue(String fieldName, Object fieldValue){
        List<ЗаписьТЧСписокТоваров> Result = new ArrayList<ЗаписьТЧСписокТоваров>();
        List<ЗаписьБД> Записи = DataAccessObject.findObjectsByValue(getType(), fieldName, fieldValue);
        for(var Запись:Записи) Result.add((ЗаписьТЧСписокТоваров)Запись);
        return Result;
    }
    public static String getType() {
        return "ЗаписьТЧСписокТоваров";
    }

    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID id = UUID.randomUUID();
    private Integer lineNumber; //номер строки

    @ManyToOne
    @JoinColumn(name = "nomenclature_id", referencedColumnName = "id")
    private ЗаписьНоменклатура nomenclature_; //ТЧ_СПИСОК_ТОВАРОВ-ЗАПИСЬ_НОМЕНКЛАТУРА связь с классом ЗаписьНоменклатура
                                            // (таблица БД "table_part_ListOfProducts"-"nomenclature"), list listsOfProductstTableParts
    @ManyToOne
    @JoinColumn(name = "doc_sale_id", referencedColumnName = "id")
    private Реализация doc_sale_; //ЗаписьТЧСписокТоваров-Реализация
                                // (таблица БД "table_part_list_of_products"-"doc_sale")
    private Double amount; //количество
    private Double price; //цена
    private Double sum; //сумма

    private ЗаписьТЧСписокТоваров(ЗаписьНоменклатура nomenclature_, Double amount, Double price) {
        nomenclature_ = nomenclature_;
        this.amount = amount;
        price = price;
        sum = price * amount;
    }

    public void setNomenclature_(ЗаписьНоменклатура nomenclature) {
        this.nomenclature_ = nomenclature;
    }

    @Override
    public boolean ПередЗаписью() {
        if (this.getLineNumber() == null || this.getNomenclature_() == null
                || this.getAmount() == null || this.getPrice() == null
                || this.getSum() == null)
            return false;
        else
            return true;
    }

}
