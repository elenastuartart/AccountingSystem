package com.stuart.models.entity.справочники;

import com.stuart.dao.записьБД.DataAccessObject;
import com.stuart.models.entity.ЗаписьБД;
import com.stuart.models.entity.документы.производство.ЗаписьТЧРасходМатериалов;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "production_stages", schema = "study_db")
public class ЗаписьЭтапыПроизводства extends ЭлементСправочника {

    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID id = UUID.randomUUID();
    private Integer code;
    private String name;
    private String description_stage;
    //ЗаписьЭтапыПроизводства-ЗаписьТЧРасходМатериалов
    //(таблица БД "production_stages"-table_part_material_consuption")
    @OneToMany(mappedBy = "stage_", fetch = FetchType.LAZY)
    private List<ЗаписьТЧРасходМатериалов> table_part_material_consuption_1;

    public ЗаписьЭтапыПроизводства(String name, String description_stage) {
        this.code = ЭлементСправочника.GetRandomCode();
        this.name = name;
        this.description_stage = description_stage;
    }

    public void setCode() {
        this.code = ЭлементСправочника.GetRandomCode();
    }

    public static ЗаписьЭтапыПроизводства findObjectByValue(String fieldName, Object fieldValue) {
        return (ЗаписьЭтапыПроизводства) DataAccessObject.findObjectByValue(getType(), fieldName, fieldValue);
    }

    public static List<ЗаписьЭтапыПроизводства> findObjectsByValue(String fieldName, Object fieldValue){
        List<ЗаписьЭтапыПроизводства> Result = new ArrayList<ЗаписьЭтапыПроизводства>();
        List<ЗаписьБД> Записи = DataAccessObject.findObjectsByValue(getType(), fieldName, fieldValue);
        for(var Запись:Записи) Result.add((ЗаписьЭтапыПроизводства)Запись);
        return Result;
    }

    public static String getType() {
        return "ЗаписьЭтапыПроизводства";
    }

    @Override
    public boolean ПередЗаписью() {
        if ( this.getCode() == null
                    || this.getName() == null)
            return false;
        else
            return true;
    }
}

