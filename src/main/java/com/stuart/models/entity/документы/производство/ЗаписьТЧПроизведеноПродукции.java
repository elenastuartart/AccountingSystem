package com.stuart.models.entity.документы.производство;

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
@Table(name = "table_part_produced_of_products", schema = "study_db")
public class ЗаписьТЧПроизведеноПродукции extends ЗаписьБД {
    @Id
    @GeneratedValue
    private UUID id;
    private Integer lineNumber;

    @ManyToOne
    @JoinColumn(name = "nomenclature_id", referencedColumnName = "id")
    private ЗаписьНоменклатура nomenclature_; //  ТЧ_ПРОИЗВЕДЕНО_ПРОДУКЦИИ-ЗАПИСЬ_НОМЕНКЛАТУРА связь с классом "ЗаписьНоменклатура" (таблица БД "nomenclature")
                                            //(таблица БД "table_part_produced_of_products"-"nomenclature") list productsOfGoodsTableParts
    private Double amount;

    public ЗаписьТЧПроизведеноПродукции(ЗаписьНоменклатура nomenclature_, Double amount) {
        this.nomenclature_ = nomenclature_;
        this.amount = amount;
    }
}
