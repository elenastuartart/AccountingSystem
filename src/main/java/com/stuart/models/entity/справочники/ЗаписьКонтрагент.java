package com.stuart.models.entity.справочники;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@ToString
public class ЗаписьКонтрагент extends ЭлементСправочника {

    @Id
    @GeneratedValue
    private UUID ПервичныйКлюч;
    private int Код;
    private String Наименование;
    private String КонтактноеЛицоКА;
    private String АдресКА;
    private String ТипКонтрагента; //поставщик/покупатель
    private boolean ПризнакУдаления;

    @Override
    public void ПередЗаписью() {
        super.ПередЗаписью();
    }
}
