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
@Builder
@Entity
@Table(name = "doc_purchase", schema = "study_db")
public class Закупка extends Документ {
    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID id = UUID.randomUUID();
    public Date date;
    private Integer number;
    private boolean pometkaProvedeniya;
    @ManyToOne
    @JoinColumn(name = "contragent_id", referencedColumnName = "id")
    private ЗаписьКонтрагент contragent_; //Закупка-ЗаписьКонтрагент
                                        //таблица БД "doc_purchase"-"contragent"
    private Double finalSum;
    @OneToMany(mappedBy = "doc_purchase_", fetch = FetchType.LAZY)
    private List<ЗаписьТЧ_Закупка> table_part_purchase_ = new ArrayList<>(); //  Закупка-ЗаписьТЧЗакупка
                                                                            // (таблица БД "doc_purchase"-"table_part_purchase"

    public Закупка(Date date, Integer number, ЗаписьКонтрагент contragent_) {
        this.date = date;
        this.number = number;
        this.pometkaProvedeniya = false;
        this.contragent_ = contragent_;
    }

    public void ЗаполнитьТЧ(ЗаписьТЧ_Закупка запись) {
//        if(ТабличнаяЧасть.isEmpty()) {
//            запись.НомерСтроки = 1;
//        }
//        else {
//            запись.НомерСтроки = ТабличнаяЧасть.size() + 1;
//        }
        this.table_part_purchase_.add(запись); //по мере создания записей добавляем их в табличную часть документа
    }

    public void ПосчитатьИтоговуюСумму() {
        double sum1 = 0;
        for (int i = 0; i < table_part_purchase_.size(); i++) {
            ЗаписьТЧ_Закупка запись = table_part_purchase_.get(i);
            sum1 = sum1 + запись.getSum();
        }
        this.finalSum = sum1;
    }

    @Override
    public boolean ПередЗаписью() {

        if ((this.getDate() == null || this.getNumber() == null
                || this.getContragent_() == null || this.getFinalSum() == null
                || this.getTable_part_purchase_() == null))
            return false;
        else
            return true;
    }

    @Override
    public String toString() {
        return "Закупка " + number + " от "
                + date.toString() + " на сумму " + finalSum
                + " контрагент " + contragent_.getName() + " проведение " + pometkaProvedeniya;
    }

    public void toStringTable () {
        for (int i = 0; i < this.table_part_purchase_.size(); i++) {
            System.out.println(
                    table_part_purchase_.get(i).getNomenclature_().getName() +" " +
                            table_part_purchase_.get(i).getPrice() + " руб. " +
                            table_part_purchase_.get(i).getAmount() + " " +
                            table_part_purchase_.get(i).getSum() + " ");
            System.out.println( );
        }
    }

    @Override
    public boolean ЗаписатьТабЧасти() {
        boolean result = true;
        for (int i = 0; i < table_part_purchase_.size(); i ++) {
            var СтрТЧ = table_part_purchase_.get(i);
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
        СтрРегистра.setКонтрагент(this.getContragent_());
        СтрРегистра.setСумма(this.getFinalSum());

        if(СтрРегистра.save() == false) {
            result = false;
        }
        return result;
    }



}
