package com.stuart.models.entity.справочники;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@ToString
public class ЗаписьЭтапыПроизводства extends ЭлементСправочника {
    @Id
    @GeneratedValue
    private UUID ПервичныйКлюч;
    private int Код;
    private String Наименование;
    private String ОписаниеЭтапа;
    private boolean ПризнакУдаления;


    @Override
    public void ПередЗаписью() {
        super.ПередЗаписью();
    }
}

