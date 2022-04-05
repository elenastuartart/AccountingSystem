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
    @GeneratedValue
    private UUID ID;
    private int Код;
    private String Наименование;
    private int Артикул;
    private String Категория;
    private String Подкатегория;

//(optional = false, cascade = CascadeType.)
    @ManyToOne
    @JoinColumn(name= "Производитель_ID")
    private ЗаписьКонтрагент Производитель;

    private boolean ПризнакУдаления;

    public void setПроизводитель(ЗаписьКонтрагент производитель) {
        Производитель = производитель;
    }

    @Override
    public void ПередЗаписью() {
        super.ПередЗаписью();
    }
}
