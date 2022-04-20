package com.stuart.models.entity.справочники;

import com.stuart.models.entity.документы.производство.ЗаписьТЧРасходМатериалов;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

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
    @Column(columnDefinition = "BINARY(16)")
    private UUID id = UUID.randomUUID();
    private Integer code;
    private String name;
    private String descriprion_stage;

    @OneToMany(mappedBy = "stage_", fetch = FetchType.LAZY)
    private List<ЗаписьТЧРасходМатериалов>
            table_part_material_consuption_1;//ЗаписьЭтапыПроизводства-ТЧ_РАСХОД_МАТЕРИАЛОВ  связь с классом ЗаписьТЧРасходМатериалов
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

