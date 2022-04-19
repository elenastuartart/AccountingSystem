package com.stuart.models.entity.документы.закупка;

import com.stuart.models.entity.документы.Документ;
import com.stuart.models.entity.регистры.ЗаписьРегистраВзаиморасчеты;
import com.stuart.models.entity.справочники.ЗаписьКонтрагент;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@Builder
//@Entity
//@Table(name = "docPurchase", schema = "study_db")
public class Закупка extends Документ {
    @Id
    @GeneratedValue
    public UUID code;
    public Date date;
    public Integer number;
    public boolean pometkaProvedeniya;
    @ManyToOne
    @JoinColumn(name = "contragent_id", referencedColumnName = "id")
    public ЗаписьКонтрагент contragent;
    public Double finalSum;
    @OneToMany(mappedBy = "purchaseDoc", fetch = FetchType.LAZY) //к одному документу много строк табличной части
    public List<ЗаписьТЧ_Закупка> tableParte = new ArrayList<>();


    public Закупка(Date date, Integer number, ЗаписьКонтрагент contragent) {
        this.date = date;
        this.number = number;
        this.pometkaProvedeniya = false;
        this.contragent = contragent;
    }

    public void ЗаполнитьТЧ(ЗаписьТЧ_Закупка запись) {
//        if(ТабличнаяЧасть.isEmpty()) {
//            запись.НомерСтроки = 1;
//        }
//        else {
//            запись.НомерСтроки = ТабличнаяЧасть.size() + 1;
//        }
        this.tableParte.add(запись); //по мере создания записей добавляем их в табличную часть документа
    }

    public void ПосчитатьИтоговуюСумму() {
        double sum1 = 0;
        for (int i = 0; i < tableParte.size(); i++) {
            ЗаписьТЧ_Закупка запись = tableParte.get(i);
            sum1 = sum1 + запись.sum;
        }
        this.finalSum = sum1;
    }

    @Override
    public boolean ПередЗаписью() {

        if ((this.getDate() == null || this.getNumber() == null
                || this.getContragent() == null || this.getFinalSum() == null
                || this.getTableParte() == null))
            return false;
        else
            return true;
    }

    @Override
    public String toString() {
        return "Закупка " + number + " от "
                + date.toString() + " на сумму " + finalSum
                + " контрагент " + contragent.getName() + " проведение " + pometkaProvedeniya;
    }

    public void toStringTable () {
        for (int i = 0; i < this.tableParte.size(); i++) {
            System.out.println(
                    tableParte.get(i).nomenclature_.getName() +" " +
                    tableParte.get(i).price + " руб. " +
                    tableParte.get(i).amount + " " +
                    tableParte.get(i).sum + " ");
            System.out.println( );
        }
    }

    @Override
    public boolean ЗаписатьТабЧасти() {
        boolean result = true;
        for (int i = 0; i < tableParte.size(); i ++) {
            var СтрТЧ = tableParte.get(i);
            СтрТЧ.setLineNumber(i+1);
            if(СтрТЧ.save() == false) {
                result = false;
            }
        }
        return result;
    }

    @Override
    public boolean ЗаписатьРегистры() {
        boolean result = true;
        var СтрРегистра = new ЗаписьРегистраВзаиморасчеты();
        СтрРегистра.setРегистратор(this);
        СтрРегистра.setДата(this.getDate());
        СтрРегистра.setКонтрагент(this.getContragent());
        СтрРегистра.setСумма(this.getFinalSum());

        if(СтрРегистра.save() == false) {
            result = false;
        }
        return result;
    }



}
