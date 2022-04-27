package com.stuart.models.entity.документы.продажа;

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
@Table(name = "doc_sale",schema = "study_db")
public class Реализация extends Документ {

    public static Реализация findObjectByValue(String fieldName, Object fieldValue) {
        return (Реализация) DataAccessObject.findObjectByValue(getType(), fieldName, fieldValue);
    }

    public static List<Реализация> findObjectsByValue(String fieldName, Object fieldValue){
        List<Реализация> Result = new ArrayList<Реализация>();
        List<ЗаписьБД> Записи = DataAccessObject.findObjectsByValue(getType(), fieldName, fieldValue);
        for(var Запись:Записи) Result.add((Реализация)Запись);
        return Result;
    }
    public static String getType() {
        return "Реализация";
    }

    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID id = UUID.randomUUID();
    private Date date;
    private Integer number;
    private boolean pometkaProvedeniya;
    @ManyToOne
    @JoinColumn(name = "contragent_id", referencedColumnName = "id")
    private ЗаписьКонтрагент contragent_; //Реализация-ЗаписьКонтрагент
                                        // таблица БД "doc_sale"-"contragent"
    private Double finalSum;
    @OneToMany (mappedBy = "doc_sale_", fetch = FetchType.LAZY)
    private List<ЗаписьТЧСписокТоваров> table_part_list_of_products_ = new ArrayList<>(); //Реализация-ЗаписьТЧСписокТоваров

    @Transient
    public List<ЗаписьРегистраТоварыНаСкладах> registerProductsInStock;
    @Transient
    public List<ЗаписьРегистраВзаиморасчеты> registerCalculations;

    public void initRegisterCalculation () {
        this.registerCalculations = ЗаписьРегистраВзаиморасчеты.findObjectsByValue("idDoc", this.id);
        this.registerProductsInStock = ЗаписьРегистраТоварыНаСкладах.findObjectsByValue("idDoc", this.id);
    }


//    @OneToMany(mappedBy = doc_sale_, fetch = FetchType.LAZY)
//    private List<ЗаписьРегистраВзаиморасчеты> register_calculation_ = new ArrayList<>();

//    @Any(metaDef = "type", metaColumn = @Column(name = "type_id"), fetch = FetchType.LAZY, optional = true)
//    @JoinColumn(name = "register_calculation_id", referencedColumnName = "id")
//    private List<ЗаписьРегистраВзаиморасчеты> DocMetaDef = new ArrayList<>();

    public void setPometkaProvedeniya() {
        this.pometkaProvedeniya = false;
    }

    public Реализация(Date date, Integer number, ЗаписьКонтрагент contragent) {
        this.date = date;
        this.number = number;
        this.pometkaProvedeniya = false;
        contragent_ = contragent;
    }

    public void initTableParts(ЗаписьТЧСписокТоваров Запись) {
        this.table_part_list_of_products_.add(Запись); //по мере создания записей добавляем их в табличную часть документа
    }

    public void setFinalSum() {
        double sum = 0;
        for (int i = 0; i < table_part_list_of_products_.size(); i++) {
            ЗаписьТЧСписокТоваров запись = table_part_list_of_products_.get(i);
            sum = sum + запись.getSum();
        }
        this.finalSum = sum;
    }

    @Override
    public boolean ПередЗаписью() {

        if ((this.getDate() == null || this.getNumber() == null
                || this.getContragent_() == null || this.getFinalSum() == null
                || this.getTable_part_list_of_products_() == null))
            return false;
        else
            return true;
    }

    @Override
    public String toString() {
        return "Реализация " + number + " от "
                + date.toString() + " на сумму " + finalSum
                + " контрагент " + contragent_.getName() + " проведение " + pometkaProvedeniya;
    }

    public void toStringTable () {
        for (int i = 0; i < this.table_part_list_of_products_.size(); i++) {
            System.out.println( table_part_list_of_products_.get(i).getLineNumber() + " " +
                    table_part_list_of_products_.get(i).getNomenclature_().getName() +" " +
                    table_part_list_of_products_.get(i).getPrice() + " руб. " +
                    table_part_list_of_products_.get(i).getAmount() + " " +
                    table_part_list_of_products_.get(i).getSum())  ;
            System.out.println( );
        }
    }

    @Override
    public boolean ЗаписатьТабЧасти() {
        boolean result = true;
        for (int i = 0; i < table_part_list_of_products_.size(); i ++) {
            var СтрТЧ = table_part_list_of_products_.get(i);
            if(СтрТЧ.save() == false) {
                result = false;
            }
        }
        return result;
    }

    @Override
    public boolean ЗаписатьРегистры() {
        boolean result = true;
        var СтрРегистра = new ЗаписьРегистраВзаиморасчеты() ;

        СтрРегистра.setDate(this.getDate());
        СтрРегистра.setContragent_(this.getContragent_());
        СтрРегистра.setSum(this.getFinalSum());

        СтрРегистра.setTypeDoc(getType());
        СтрРегистра.setIdDoc(this.getId());

        if(СтрРегистра.save() == false) {
            result = false;
        }
        return result;
    }
}
