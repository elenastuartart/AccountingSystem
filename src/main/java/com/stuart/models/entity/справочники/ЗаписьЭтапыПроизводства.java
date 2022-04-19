package com.stuart.models.entity.справочники;

import com.stuart.models.entity.документы.производство.ЗаписьТЧРасходМатериалов;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@ToString
@Table(name = "production_stages", schema = "study_db")
public class ЗаписьЭтапыПроизводства extends ЭлементСправочника {
    @Id
    @GeneratedValue
    private UUID id;
    private Integer code;
    private String name;
    private String descriprion_stage;

    @OneToMany(mappedBy = "stage_", fetch = FetchType.LAZY)
    private List<ЗаписьТЧРасходМатериалов>
            table_part_material_consuption_1;//ЭТАПЫ_ПРОИЗВОДСТВА-ТЧ_РАСХОД_МАТЕРИАЛОВ  связь с классом ЗаписьТЧРасходМатериалов
                                            //(таблица БД "production_stages"-table_part_material_consuption")

    @Override
    public boolean ПередЗаписью() {
        if ( this.getCode() == null
                    || this.getName() == null
                    || this.getDescriprion_stage() == null)
            return false;
        else
            return true;
    }
}

