package com.stuart.models.entity.справочники;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class ЗаписьКонтрагент extends ЭлементСправочника {

    @Id
    @GeneratedValue
    private UUID ID;
    private int Код;
    private String Наименование;
    private String КонтактноеЛицоКА;
    private String АдресКА;
    private String ТипКонтрагента; //поставщик/покупатель
    private boolean ПризнакУдаления;
    @OneToMany(mappedBy = "Производитель")
    private Collection<ЗаписьНоменклатура> Номенклатуры;

    @Override
    public void ПередЗаписью() {
        super.ПередЗаписью();
    }

    @Override
    public String toString() {
        return "ЗаписьКонтрагент{" +
                "Код=" + Код +
                ", Наименование='" + Наименование + '\'' +
                ", КонтактноеЛицоКА='" + КонтактноеЛицоКА + '\'' +
                ", АдресКА='" + АдресКА + '\'' +
                ", ТипКонтрагента='" + ТипКонтрагента + '\'' +
                ", ПризнакУдаления=" + ПризнакУдаления +
                '}';
    }




}
