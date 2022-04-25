package com.stuart.models.entity.регистры;

import com.stuart.models.entity.документы.Документ;
import com.stuart.models.entity.справочники.ЗаписьНоменклатура;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "registrar_products_in_stock")
public class ЗаписьРегистраТоварыНаСкладах extends ЗаписьРегистра {
    @Id
    private UUID id;
    @ManyToOne//manytoany
    private Документ registrarDoc;// в качестве регистратора могут выступать 3 типа документов



    @ManyToOne
    @JoinColumn(name = "nomenclature_id", referencedColumnName = "id")
    private ЗаписьНоменклатура nomenclature_;//ЗаписьРегистраТоварыНаСкладах-ЗаписьНоменклатура
                                            //таблица БД "registrar_products_in_stock"-"nomenclature"
    private Double amount;
    private Double sum;

    @Override
    public boolean ПередЗаписью() {
        if (this.getRegistrarDoc() == null || this.getNomenclature_() == null
                || this.getAmount() == null
                || this.getSum() == null)
            return false;
        else
            return true;
    }

    @Override
    public String toString() {
        return "ЗаписьРегистраТоварыНаСкладах{" +
                "Дата: " + nomenclature_.getName().toString() +
                "; Контрагент: " + amount +
                "; Сумма: " + sum +
                '}';
    }

}
