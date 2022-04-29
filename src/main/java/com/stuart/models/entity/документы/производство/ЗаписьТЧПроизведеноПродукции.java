package com.stuart.models.entity.документы.производство;

import com.stuart.dao.записьБД.DataAccessObject;
import com.stuart.models.entity.ЗаписьБД;
import com.stuart.models.entity.документы.продажа.Реализация;
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
@Entity
@Table(name = "table_part_produced_of_products", schema = "study_db")
public class ЗаписьТЧПроизведеноПродукции extends ЗаписьБД {

    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID id = UUID.randomUUID();
    private Integer lineNumber;
    private Double amount;
    //  ТЧ_ПРОИЗВЕДЕНО_ПРОДУКЦИИ-ЗАПИСЬ_НОМЕНКЛАТУРА связь с классом "ЗаписьНоменклатура" (таблица БД "nomenclature")
    //(таблица БД "table_part_produced_of_products"-"nomenclature") list productsOfGoodsTableParts
    @ManyToOne
    @JoinColumn(name = "nomenclature_id", referencedColumnName = "id")
    private ЗаписьНоменклатура nomenclature_;
    //ЗаписьТЧПроизведеноПродукции-Производство
    //таблица БД "table_part_produced_of_products"-"doc_manufacture"
    @ManyToOne
    @JoinColumn(name = "doc_manufacture_id", referencedColumnName = "id")
    private Производство doc_manufacture_;
    @Column(columnDefinition = "BINARY(16)")
    private UUID idDoc;

    public ЗаписьТЧПроизведеноПродукции(ЗаписьНоменклатура nomenclature_, Double amount,
                                        Производство производство) {
        this.nomenclature_ = nomenclature_;
        this.amount = amount;
        this.doc_manufacture_ = производство;
        this.idDoc = производство.getId();
    }

    public static ЗаписьТЧПроизведеноПродукции findObjectByValue(String fieldName, Object fieldValue) {
        return (ЗаписьТЧПроизведеноПродукции) DataAccessObject.findObjectByValue(getType(), fieldName, fieldValue);
    }

    public static List<ЗаписьТЧПроизведеноПродукции> findObjectsByValue(String fieldName, Object fieldValue){
        List<ЗаписьТЧПроизведеноПродукции> Result = new ArrayList<ЗаписьТЧПроизведеноПродукции>();
        List<ЗаписьБД> Записи = DataAccessObject.findObjectsByValue(getType(), fieldName, fieldValue);
        for(var Запись:Записи) Result.add((ЗаписьТЧПроизведеноПродукции)Запись);
        return Result;
    }

    public static String getType() {
        return "ЗаписьТЧПроизведеноПродукции";
    }

    @Override
    public boolean ПередЗаписью() {
        if (this.getLineNumber() == null || this.getNomenclature_() == null
                || this.getAmount() == null
                || this.doc_manufacture_==null || this.idDoc == null)
            return false;
        else
            return true;
    }
}
