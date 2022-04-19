package com.stuart.models.entity.документы.производство;

import com.stuart.models.entity.ЗаписьБД;
import com.stuart.models.entity.справочники.ЗаписьНоменклатура;
import com.stuart.models.entity.справочники.ЗаписьЭтапыПроизводства;
import lombok.*;

import javax.persistence.*;
import javax.print.attribute.standard.MediaSize;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "table_part_material_consuption", schema = "study_db")
public class ЗаписьТЧРасходМатериалов extends ЗаписьБД {
    @Id
    @GeneratedValue
    private UUID id;
    private Integer lineNumber;

    @ManyToOne
    @JoinColumn (name = "production_stages_id", referencedColumnName = "id")
    private ЗаписьЭтапыПроизводства stage_; //ТЧ_РАСХОД_МАТЕРИАЛОВ_ЭТАПЫ_ПРОИЗВОДСТВА- связь с классом ЗаписьЭтапыПроизводства
                                //(таблица БД -table_part_material_consuption"-"production_stages")


    @ManyToOne
    @JoinColumn(name = "nomenclature_id", referencedColumnName = "id")
    private ЗаписьНоменклатура nomenclature_;//  ТЧ_РАСХОД_МАТЕРИАЛОВ-НОМЕНКЛАТУРА связь с классом "ЗаписьНоменклатура" (таблица БД "nomenclature")
                                            //(таблица БД "table_part_material_consuption"-"nomenclature") list table_part_material_consuption_
    private Double amount;


    public ЗаписьТЧРасходМатериалов(ЗаписьЭтапыПроизводства stage,
                                    ЗаписьНоменклатура nomenclature, Double amount) {
        this.stage_ = stage;
        this.nomenclature_ = nomenclature;
        this.amount = amount;
    }

}
