package com.stuart.models.entity.документы.производство;

import com.stuart.dao.записьБД.DataAccessObject;
import com.stuart.models.entity.ЗаписьБД;
import com.stuart.models.entity.справочники.ЗаписьНоменклатура;
import com.stuart.models.entity.справочники.ЗаписьЭтапыПроизводства;
import lombok.*;

import javax.persistence.*;
import javax.print.attribute.standard.MediaSize;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "table_part_material_consuption", schema = "study_db")
public class ЗаписьТЧРасходМатериалов extends ЗаписьБД {

    public static ЗаписьТЧРасходМатериалов findObjectByValue(String fieldName, Object fieldValue) {
        return (ЗаписьТЧРасходМатериалов) DataAccessObject.findObjectByValue(getType(), fieldName, fieldValue);
    }

    public static List<ЗаписьТЧРасходМатериалов> findObjectsByValue(String fieldName, Object fieldValue){
        List<ЗаписьТЧРасходМатериалов> Result = new ArrayList<ЗаписьТЧРасходМатериалов>();
        List<ЗаписьБД> Записи = DataAccessObject.findObjectsByValue(getType(), fieldName, fieldValue);
        for(var Запись:Записи) Result.add((ЗаписьТЧРасходМатериалов)Запись);
        return Result;
    }

    public static String getType() {
        return "ЗаписьТЧРасходМатериалов";
    }

    @Id
    @GeneratedValue
    private UUID id;
    private Integer lineNumber;

    @ManyToOne
    @JoinColumn (name = "production_stages_id", referencedColumnName = "id")
    private ЗаписьЭтапыПроизводства stage_; //ТЧ_РАСХОД_МАТЕРИАЛОВ_ЭТАПЫ_ПРОИЗВОДСТВА- связь с классом ЗаписьЭтапыПроизводства
                                        //(таблица БД -table_part_material_consuption"-"production_stages")
    @ManyToOne
    @JoinColumn(name = "doc_manufacture_id", referencedColumnName = "id")
    private Производство doc_manufacture_; //ЗаписьТЧРасходМатериалов-Производство
                                            //таблица БД "table_part_material_consuption"-"doc_manufacture№

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
