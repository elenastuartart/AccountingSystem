package com.stuart.models.entity.справочники;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@ToString
public class ЗаписьНоменклатура extends ЭлементСправочника {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private UUID ID;
    private Integer Код;
    private String Наименование;
    private Integer Артикул;
    private String Категория;
    private String Подкатегория;
    //(optional = false, cascade = CascadeType.)
    @ManyToOne
    private ЗаписьКонтрагент Производитель;

    public void setПроизводитель(ЗаписьКонтрагент производитель) {
        Производитель = производитель;
    }

    @Override
    public boolean ПередЗаписью() {
        if ( this.getКод() == null
                || this.getНаименование() == null || this.getАртикул() == 0
                || this.getКатегория() == null || this.getПодкатегория()==null)
//                || this.getПроизводитель() == null)
            return false;
        else
            return true;
    }
}
