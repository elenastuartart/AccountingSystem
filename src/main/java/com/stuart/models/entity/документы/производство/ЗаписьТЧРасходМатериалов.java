package com.stuart.models.entity.документы.производство;

import com.stuart.dao.записьБД.DataAccessObject;
import com.stuart.models.entity.ЗаписьБД;
import com.stuart.models.entity.справочники.ЗаписьНоменклатура;
import com.stuart.models.entity.справочники.ЗаписьЭтапыПроизводства;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "table_part_material_consuption", schema = "study_db")
public class ЗаписьТЧРасходМатериалов extends ЗаписьБД {

    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID id = UUID.randomUUID();
    private Integer lineNumber;
    private Double amount;
    //ЗаписьТЧРасходМатериалов-ЗаписьЭтапыПроизводства
    //(таблица БД -table_part_material_consuption"-"production_stages")
    @ManyToOne
    @JoinColumn (name = "production_stages_id", referencedColumnName = "id")
    private ЗаписьЭтапыПроизводства stage_;
    //  ЗаписьТЧРасходМатериалов-ЗаписьНоменклатура
    //(таблица БД "table_part_material_consuption"-"nomenclature") list table_part_material_consuption_
    @ManyToOne
    @JoinColumn(name = "nomenclature_id", referencedColumnName = "id")
    private ЗаписьНоменклатура nomenclature_;
    //ЗаписьТЧРасходМатериалов-Производство
    //таблица БД "table_part_material_consuption"-"doc_manufacture№
    @ManyToOne
    @JoinColumn(name = "doc_manufacture_id", referencedColumnName = "id")
    private Производство doc_manufacture_;
    @Column(columnDefinition = "BINARY(16)")
    private UUID idDoc;

    public ЗаписьТЧРасходМатериалов(ЗаписьЭтапыПроизводства stage,
                                    ЗаписьНоменклатура nomenclature,
                                    Double amount, Производство производство) {
        this.stage_ = stage;
        this.nomenclature_ = nomenclature;
        this.amount = amount;
        this.doc_manufacture_ = производство;
        this.idDoc = производство.getId();
    }

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

    @Override
    public boolean ПередЗаписью() {
        if (this.getLineNumber() == null || this.getNomenclature_() == null
                || this.getAmount() == null || this.getStage_() == null
                || this.doc_manufacture_==null || this.idDoc == null)
            return false;
        else
            return true;
    }
}
