package com.stuart.models.entity.документы.закупка;

import com.stuart.models.entity.ЗаписьБД;
import com.stuart.models.entity.справочники.ЗаписьНоменклатура;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "table_part_purchase", schema = "study_db")
public class ЗаписьТЧ_Закупка extends ЗаписьБД {

    @Id
    @GeneratedValue
    public UUID id;
    public Integer lineNumber;


    @ManyToOne
    @JoinColumn(name = "nomenclature_id", referencedColumnName = "id")
    public ЗаписьНоменклатура nomenclature_; //ТЧ_ЗАКУПКА-ЗАПИСЬ_НОМЕНКЛАТУРА связь с классом ЗаписьНоменклатура
                                            // (таблица БД "table_part_purchase"-"nomenclature"), list purchasesTableParts

    public Double amount; //количество
    public Double price; //цена
    public Double sum; //сумма
//    @ManyToOne
//    @JoinColumn(name = "docPurchase_id", referencedColumnName = "id")
//    private Закупка purchaseDoc;

    public void setNomenclature_(ЗаписьНоменклатура nomenclature) {
        this.nomenclature_ = nomenclature;
    }

    public ЗаписьТЧ_Закупка(ЗаписьНоменклатура nomenclature_,
                            Double amount, Double price) {
        nomenclature_ = nomenclature_;
        this.amount = amount;
        this.price = price;
        sum = price * amount;
    }

    @Override
    public boolean ПередЗаписью() {
        if (this.getNomenclature_() == null
                || this.getAmount() == null || this.getPrice() == null
                || this.getSum() == null )
            return false;
        else
            return true;
    }

}
