package com.stuart.models.entity.регистры;

import com.stuart.models.entity.документы.Документ;
import com.stuart.models.entity.справочники.ЗаписьКонтрагент;
import lombok.*;

import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ЗаписьРегистраВзаиморасчеты extends ЗаписьРегистра {

    private UUID ID;
    @ManyToOne // в качестве регистратора могут быть два типа документов продажа и закупка
    private Документ Регистратор;
    private Date Дата;
    @ManyToOne
    private ЗаписьКонтрагент Контрагент;
    private Double Сумма;


    @Override
    public boolean ПередЗаписью() {
        if (this.getРегистратор() == null || this.getДата() == null
                | this.getКонтрагент() == null
                || this.getСумма() == null)
            return false;
        else
            return true;
    }


    @Override
    public String toString() {
        return "ЗаписьРегистраВзаиморасчеты{" +
                "Дата: " + Дата.toString() +
                "; Контрагент: " + Контрагент.getName() +
                "; Сумма: " + Сумма +
                '}';
    }
}
