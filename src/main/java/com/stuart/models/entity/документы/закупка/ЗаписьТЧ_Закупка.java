package com.stuart.models.entity.документы.закупка;

import com.stuart.models.entity.ЗаписьБД;
import com.stuart.models.entity.документы.продажа.Реализация;
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
    @Column(columnDefinition = "BINARY(16)")
    private UUID id = UUID.randomUUID();
    private Integer lineNumber;

    @ManyToOne
    @JoinColumn(name = "nomenclature_id", referencedColumnName = "id")
    private ЗаписьНоменклатура nomenclature_; //ТЧ_ЗАКУПКА-ЗАПИСЬ_НОМЕНКЛАТУРА связь с классом ЗаписьНоменклатура
                                            // (таблица БД "table_part_purchase"-"nomenclature"), list purchasesTableParts
    private Double amount; //количество
    private Double price; //цена
    private Double sum; //сумма
    @ManyToOne
    @JoinColumn(name = "doc_purchase_id", referencedColumnName = "id")
    private Закупка doc_purchase_; //ЗаписьТЧЗакупка-Закупка
                                    // (таблица БД "table_part_purchase"-"doc_purchase"), list table_part_purchase_



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
