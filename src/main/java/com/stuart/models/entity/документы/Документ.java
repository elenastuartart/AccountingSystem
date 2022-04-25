package com.stuart.models.entity.документы;

import com.stuart.models.entity.ЗаписьБД;
import com.stuart.models.entity.документы.закупка.Закупка;
import com.stuart.models.entity.документы.продажа.Реализация;
import com.stuart.models.entity.регистры.ЗаписьРегистраВзаиморасчеты;
import jdk.jfr.MetadataDefinition;
import lombok.*;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.Any;
import org.hibernate.annotations.AnyMetaDef;
import org.hibernate.annotations.MetaValue;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "document",schema = "study_db")
public class Документ extends ЗаписьБД {

    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID id = UUID.randomUUID();

    @ManyToMany(mappedBy = "documents_", fetch = FetchType.LAZY)
    private Set<ЗаписьРегистраВзаиморасчеты>  registersCalculations_;

    @Transient
    private boolean ПометкаПроведения;

    @Override
    public boolean ПередЗаписью() {
        return super.ПередЗаписью();
    }

    public boolean ЗаписатьРегистры() {
        return true;
    }

    public boolean ЗаписатьТабЧасти() {
        return true;
    }

    public boolean Проведение() {

        boolean result = true;
        ПометкаПроведения = true;
        if (ЗаписатьДокумент()==false) {
            result = false;
        }
        return result;
    }

    public boolean ЗаписатьДокумент() {

        boolean result = true;
        if(this.ПередЗаписью() == false || this.save()==false) {
            result = false;
        }
        if(result!=false) {
            if(this.ЗаписатьТабЧасти()==false) {
                result = false;
            }
        }
        if (result!=false && ПометкаПроведения==true) {
            if(this.ЗаписатьРегистры()==false) {
                result = false;
            }
        }
        return  result;
    }
}
