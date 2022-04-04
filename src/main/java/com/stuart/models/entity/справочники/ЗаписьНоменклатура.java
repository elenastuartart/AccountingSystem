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
    private UUID ПервичныйКлюч;
    private int Код;
    private String Наименование;
    private int Артикул;
    private String Категория;
    private String Подкатегория;
    //private ЗаписьКонтрагент Производитель;
    private boolean ПризнакУдаления;


    @Override
    public void ПередЗаписью() {
        super.ПередЗаписью();
    }
}
