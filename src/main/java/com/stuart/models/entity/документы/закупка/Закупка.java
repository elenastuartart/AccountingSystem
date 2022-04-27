package com.stuart.models.entity.документы.закупка;

import com.stuart.dao.записьБД.DataAccessObject;
import com.stuart.models.entity.ЗаписьБД;
import com.stuart.models.entity.документы.Документ;
import com.stuart.models.entity.регистры.ЗаписьРегистраВзаиморасчеты;
import com.stuart.models.entity.регистры.ЗаписьРегистраТоварыНаСкладах;
import com.stuart.models.entity.справочники.ЗаписьКонтрагент;
import lombok.*;
import org.hibernate.Session;
import org.hibernate.query.Query;

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
@Entity
@Table(name = "doc_purchase", schema = "study_db")
public class Закупка extends Документ {

    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID id = UUID.randomUUID();
    private Date date;
    private Integer number;
    private boolean pometkaProvedeniya;
    private Double finalSum;
    //Закупка-ЗаписьКонтраген / таблица БД "doc_purchase"-"contragent"
    @ManyToOne
    @JoinColumn(name = "contragent_id", referencedColumnName = "id")
    private ЗаписьКонтрагент contragent_;
    //  Закупка-ЗаписьТЧЗакупка / (таблица БД "doc_purchase"-"table_part_purchase"
    @OneToMany(mappedBy = "doc_purchase_", fetch = FetchType.LAZY)
    private List<ЗаписьТЧ_Закупка> table_part_purchase_ = new ArrayList<>();
    @Transient
    public List<ЗаписьРегистраТоварыНаСкладах> registerProductsInStock;
    @Transient
    public List<ЗаписьРегистраВзаиморасчеты> registerCalculations;

    public Закупка(Date date, Integer number, ЗаписьКонтрагент contragent_) {
        this.date = date;
        this.number = number;
        this.pometkaProvedeniya = false;
        this.contragent_ = contragent_;
    }

    public void setPometkaProvedeniya() {
        this.pometkaProvedeniya = false;
    }

    public void setFinalSum() {
        //если табличная часть не заполнена обработать исключение
        double sum1 = 0;
        for (int i = 0; i < table_part_purchase_.size(); i++) {
            ЗаписьТЧ_Закупка запись = table_part_purchase_.get(i);
            sum1 = sum1 + запись.getSum();
        }
        this.finalSum = sum1;
    }

    public static Закупка findObjectByValue(String fieldName, Object fieldValue) {
        return (Закупка) DataAccessObject.findObjectByValue(getType(), fieldName, fieldValue);
    }

    public static List<Закупка> findObjectsByValue(String fieldName, Object fieldValue){
        List<Закупка> Result = new ArrayList<Закупка>();
        List<ЗаписьБД> Записи = DataAccessObject.findObjectsByValue(getType(), fieldName, fieldValue);
        for(var Запись:Записи) Result.add((Закупка)Запись);
        return Result;
    }

    public static String getType() {
        return "Закупка";
    }

    public void initRegisterCalculation () {
        this.registerCalculations = ЗаписьРегистраВзаиморасчеты.findObjectsByValue("idDoc", this.id);
        this.registerProductsInStock = ЗаписьРегистраТоварыНаСкладах.findObjectsByValue("idDoc", this.id);
    }

    public void ДобавитьЗаписьВТЧ(ЗаписьТЧ_Закупка запись) {
        this.table_part_purchase_.add(запись); //по мере создания записей добавляем их в табличную часть документа
    }

    @Override
    public boolean ЗаписатьТабЧасти() {
        boolean result = true;

        List<ЗаписьТЧ_Закупка> записиТЧ =
                ЗаписьТЧ_Закупка.findObjectsByValue("idDoc", this.id);

        for (int i = 0; i < записиТЧ.size(); i++) {
            var строка = записиТЧ.get(i);
            if(строка.delete() == false) {
                result = false;
                break;
            }
        }

        if(result!=false) {
            for (int i = 0; i < table_part_purchase_.size(); i ++) {
                var СтрТЧ = table_part_purchase_.get(i);
                СтрТЧ.setLineNumber(i+1);
                if(СтрТЧ.save() == false) {
                    result = false;
                }
            }
        }
//
        return result;
    }

    @Override
    public boolean ЗаписатьРегистры() {
        boolean result = true;

        List<ЗаписьРегистраВзаиморасчеты> записиРегистраДокумента =
                ЗаписьРегистраВзаиморасчеты.findObjectsByValue(
                "idDoc", this.id);

        for (int i = 0; i < записиРегистраДокумента.size(); i++) {
            var строка = записиРегистраДокумента.get(i);
            if(строка.delete() == false) {
                 result = false;
                 break;
            }
        }

        if(result!=false) {
            var СтрРегистра = new ЗаписьРегистраВзаиморасчеты();

            СтрРегистра.setDate(this.getDate());
            СтрРегистра.setContragent_(this.getContragent_());
            СтрРегистра.setSum(this.getFinalSum());
            СтрРегистра.setTypeDoc(getType());
            СтрРегистра.setIdDoc(this.getId());

            if(СтрРегистра.save() == false) {
                result = false;
            }
        }
        return result;
    }

    @Override
    public boolean ЗаписатьДокумент() {
        boolean result = true;

        if(this.ПередЗаписью() == false) {
            System.out.println("Не прошло проверку перед записью");
            result = false;
        }
        if(result!=false) {
            if (this.save() == false) {
                System.out.println("Ошибка при записи документа");
                result = false;
            }
        }
        if(result!=false) {
            if(this.ЗаписатьТабЧасти()==false) {
                System.out.println("Не удалось записать табличную часть");
                result = false;
            }
        }
        if (result!=false && pometkaProvedeniya==true) {
            if(this.ЗаписатьРегистры()==false) {
                System.out.println("Не удалось записать регистры");
                result = false;
            }
        }
        if(result==false)
            System.out.println("Не удалось записать документ");

        return  result;
    }

    @Override
    public boolean Проведение() {
        boolean result = true;
        pometkaProvedeniya = true;
        if (ЗаписатьДокумент()==false) {
            result = false;
        }
        return result;
    }

    @Override
    public boolean ПередЗаписью() {
        this.setFinalSum();
        if (this.getDate() == null || this.getNumber() == null
                || this.getContragent_() == null || this.getFinalSum() == null
                || this.getTable_part_purchase_() == null)
            return false;
        else
            return true;
    }


    public static ЗаписьТЧ_Закупка ПолучитьСтрокуТЧ(Закупка закупка) {

        return null;
    }

    @Override
    public String toString() {
        return "Закупка " + number + " от "
                + date.toString() + " на сумму " + finalSum
                + " контрагент " + contragent_.getName() + " проведение " + pometkaProvedeniya;
    }

    public void toStringTablePart () {
        for (int i = 0; i < this.table_part_purchase_.size(); i++) {
            System.out.println(
                    table_part_purchase_.get(i).getNomenclature_().getName() +" " +
                            table_part_purchase_.get(i).getPrice() + " руб. " +
                            table_part_purchase_.get(i).getAmount() + " " +
                            table_part_purchase_.get(i).getSum() + " ");
            System.out.println( );
        }
    }

}
