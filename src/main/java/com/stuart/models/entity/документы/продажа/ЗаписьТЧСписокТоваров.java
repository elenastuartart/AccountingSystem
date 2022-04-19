package com.stuart.models.entity.документы.продажа;

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
@Table(name = "table_part_list_of_products", schema = "study_db")
public class ЗаписьТЧСписокТоваров extends ЗаписьБД {
    @Id
    @GeneratedValue
    public UUID id;
    public Integer lineNumber; //номер строки

    @ManyToOne
    @JoinColumn(name = "nomenclature_id", referencedColumnName = "id")
    public ЗаписьНоменклатура nomenclature_; //ТЧ_СПИСОК_ТОВАРОВ-ЗАПИСЬ_НОМЕНКЛАТУРА связь с классом ЗаписьНоменклатура
                                            // (таблица БД "table_part_ListOfProducts"-"nomenclature"), list listsOfProductstTableParts
    public Double amount; //количество
    public Double price; //цена
    public Double sum; //сумма


    public ЗаписьТЧСписокТоваров(ЗаписьНоменклатура nomenclature_, Double amount, Double price) {
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
