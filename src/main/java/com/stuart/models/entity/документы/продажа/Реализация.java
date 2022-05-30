package com.stuart.models.entity.документы.продажа;

import com.stuart.dao.DataAccessObject;
import com.stuart.models.entity.ЗаписьБД;
import com.stuart.models.entity.документы.Документ;
import com.stuart.models.entity.регистры.ЗаписьРегистраВзаиморасчеты;
import com.stuart.models.entity.регистры.ЗаписьРегистраСебестоимостьЕдПродукции;
import com.stuart.models.entity.регистры.ЗаписьРегистраТоварыНаСкладах;
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
@Entity
@ToString
@Table(name = "doc_sale",schema = "study_db")
public class Реализация extends Документ {
    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID id = UUID.randomUUID();
    private Date date;
    private Integer number;
    private boolean pometkaProvedeniya;
    private Double finalSum;
    //Реализация-ЗаписьКонтрагент
    // таблица БД "doc_sale"-"contragent"
    @ManyToOne
    @JoinColumn(name = "contragent_id", referencedColumnName = "id")
    private ЗаписьКонтрагент contragent_;
    //Реализация-ЗаписьТЧСписокТоваров
    @OneToMany (mappedBy = "doc_sale_", fetch = FetchType.LAZY)
    private List<ЗаписьТЧСписокТоваров> table_part_list_of_products_ = new ArrayList<>();
    @Transient
    private List<ЗаписьРегистраТоварыНаСкладах> registerProductsInStock;
    @Transient
    private List<ЗаписьРегистраВзаиморасчеты> registerCalculations;

    public Реализация(Date date, ЗаписьКонтрагент contragent) {
        this.date = date;
        this.number = Документ.GetRandomNum();
        this.pometkaProvedeniya = false;
        this.contragent_ = contragent;
    }

    private void initRegisterCalculation () {
        this.registerCalculations = ЗаписьРегистраВзаиморасчеты.findObjectsByValue("idDoc", this.id);
        this.registerProductsInStock = ЗаписьРегистраТоварыНаСкладах.findObjectsByValue("idDoc", this.id);
    }

    public void setPometkaProvedeniya() {
        this.pometkaProvedeniya = false;
    }

    public void setFinalSum() {
        double sum = 0;
        for (int i = 0; i < table_part_list_of_products_.size(); i++) {
            ЗаписьТЧСписокТоваров запись = table_part_list_of_products_.get(i);
            sum = sum + запись.getSum();
        }
        this.finalSum = sum;
    }

    public void setNumber() {
        this.number = Документ.GetRandomNum();
    }

    public static Реализация findObjectByValue(String fieldName, Object fieldValue) {
        return (Реализация) DataAccessObject.findObjectByValue(getType(), fieldName, fieldValue);
    }

    public static List<Реализация> findObjectsByValue(String fieldName, Object fieldValue){
        List<Реализация> Result = new ArrayList<Реализация>();
        List<ЗаписьБД> Записи = DataAccessObject.findObjectsByValue(getType(), fieldName, fieldValue);
        for(var Запись:Записи) Result.add((Реализация)Запись);
        return Result;
    }

    private static String getType() {
        return "Реализация";
    }

    public  ЗаписьТЧСписокТоваров ДобавитьЗаписьВТЧ() {
        var ЗаписьТЧ = new ЗаписьТЧСписокТоваров();
        ЗаписьТЧ.setDoc_sale_(this);
        ЗаписьТЧ.setIdDoc(this.getId());
        this.table_part_list_of_products_.add(ЗаписьТЧ); //по мере создания записей добавляем их в табличную часть документа
        return ЗаписьТЧ;
    }

    @Override
    protected boolean ЗаписатьТабЧасти() {

        List<ЗаписьТЧСписокТоваров> записиТЧ =
                ЗаписьТЧСписокТоваров.findObjectsByValue("idDoc", this.id);

        for (int i = 0; i < записиТЧ.size(); i++) {
            var строка = записиТЧ.get(i);

            if(!строка.delete())
                return false;
        }

        for (int i = 0; i < table_part_list_of_products_.size(); i++) {
            var СтрТЧ = table_part_list_of_products_.get(i);
            СтрТЧ.setLineNumber(i+1);

            if (!СтрТЧ.save())
                return false;
        }

        return true;
    }

    @Override
    protected boolean ЗаписатьРегистрыВзаиморасчетов() {

        var СтрРегистра = new ЗаписьРегистраВзаиморасчеты();
        СтрРегистра.setDate(this.getDate());
        СтрРегистра.setContragent_(this.getContragent_());
        СтрРегистра.setSum(this.getFinalSum());
        СтрРегистра.setTypeDoc(getType());
        СтрРегистра.setIdDoc(this.getId());

        if(!СтрРегистра.save())
            return false;

        return true;
    }

    @Override
    protected boolean ЗаписатьРегистрыТоварыНаСкладе() {
        for (int i = 0; i < this.getTable_part_list_of_products_().size(); i++) {

            var СтрРегистра = new ЗаписьРегистраТоварыНаСкладах();
            СтрРегистра.setDate(this.getDate());
            СтрРегистра.setIdNom(this.getTable_part_list_of_products_().get(i).getNomenclature_().getId());
            СтрРегистра.setAmount(-(this.getTable_part_list_of_products_().get(i).getAmount()));
            СтрРегистра.setTypeDoc(this.getType());
            СтрРегистра.setIdDoc(this.getId());

            Double sum = DataAccessObject.ПолучитьСреднееПоРегиструТовары(this.getId(), СтрРегистра.getIdNom(),
                    this.getTable_part_list_of_products_().get(i).getAmount());
            СтрРегистра.setSum(-sum);

            if(!СтрРегистра.save())
                return false;
        }
        return true;
    }

    @Override
    protected boolean ЗаписатьРегистрыСебестоимостьЕдПродукции() {
        for (int i = 0; i < this.getTable_part_list_of_products_().size(); i++) {

            var СтрРегистра = new ЗаписьРегистраСебестоимостьЕдПродукции();
            СтрРегистра.setDate(this.getDate());
            СтрРегистра.setIdNom(this.getTable_part_list_of_products_().get(i).getNomenclature_().getId());
            СтрРегистра.setTypeDoc(this.getType());
            СтрРегистра.setIdDoc(this.getId());
            Double sum = DataAccessObject.ПолучитьСреднееПоРегиструТовары(this.getId(), СтрРегистра.getIdNom(),
                    this.getTable_part_list_of_products_().get(i).getAmount());
            СтрРегистра.setSumCostprice(+sum);
            СтрРегистра.setSumSale(this.getTable_part_list_of_products_().get(i).getPrice()
                    * this.getTable_part_list_of_products_().get(i).getAmount());
            СтрРегистра.setAmount(+this.getTable_part_list_of_products_().get(i).getAmount());
            СтрРегистра.setProfitByUnit(this.getTable_part_list_of_products_().get(i).getPrice()
                    - (sum/this.getTable_part_list_of_products_().get(i).getAmount()));
            СтрРегистра.setProfit(this.getTable_part_list_of_products_().get(i).getPrice()
                    * this.getTable_part_list_of_products_().get(i).getAmount()
                    - sum);

            if(!СтрРегистра.save())
                return false;
        }
        return true;
    }

    @Override
    protected boolean ПроверкаНаличия() {

        boolean result = false;
        for (int i = 0; i < table_part_list_of_products_.size(); i++) {

            var idDoc = this.getId();
            var idNom = this.table_part_list_of_products_.get(i).getNomenclature_().getId();
            var amountNom = DataAccessObject.ПолучитьОстатокПоРегиструТовары(idNom, "amount", idDoc);

            if(amountNom >= table_part_list_of_products_.get(i).getAmount())
                result = true;
            else
                System.out.println("Номенклатуры "
                        + this.table_part_list_of_products_.get(i).getNomenclature_().getName().toString()
                        + " недостаточно для реализации! "
                        + "В наличии только " + amountNom + " единиц");
        }
        return result;
    }

    @Override
    public boolean ЗаписатьДокумент() {

        if(!this.ПередЗаписью()) {
            System.out.println("Не удалось записать документ: Не прошло проверку перед записью");
            return false;
        }

        if (!this.save()) {
            System.out.println("Ошибка при записи документа");
            return false;
        }

        if(!this.ЗаписатьТабЧасти()) {
            System.out.println("Не удалось записать документ: Ошибка записи табличной части СписокТоваров");
            return false;
        }

        if(!this.ОчисткаРегистров()) {
            System.out.println("Не удалось записать документ: Ошибка очистки регистров");
            return false;
        }

        if (pometkaProvedeniya) {

            if (!this.ЗаписатьРегистрыВзаиморасчетов()) {
                System.out.println("Не удалось записать документ: Ошибка записи регистра Взаиморасчеты");
                return false;
            }

            if(!this.ЗаписатьРегистрыТоварыНаСкладе()) {
                System.out.println("Не удалось записать документ: Ошибка записи регистра ТоварыНаСкладе");
                return false;
            }

            if(!this.ЗаписатьРегистрыСебестоимостьЕдПродукции()) {
                System.out.println("Не удалось записать документ: Ошибка записи регистра СебестоимостьЕдПродукции");
                return false;
            }

        }

        return  true;
    }

    @Override
    public boolean Проведение() {
        pometkaProvedeniya = true;

        if (!ЗаписатьДокумент())
            return false;

        return true;
    }

    @Override
    public boolean ОтменаПроведения() {
        this.pometkaProvedeniya = false;

        if (!ЗаписатьДокумент())
            return false;

        return true;
    }

    @Override
    protected boolean ОчисткаРегистров() {
        List<ЗаписьРегистраВзаиморасчеты> записиРегистраДокумента1 =
                ЗаписьРегистраВзаиморасчеты.findObjectsByValue(
                        "idDoc", this.id);

        for (int i = 0; i < записиРегистраДокумента1.size(); i++) {
            var строка = записиРегистраДокумента1.get(i);

            if(!строка.delete())
                return false;
        }

        List<ЗаписьРегистраТоварыНаСкладах> записиРегистраДокумента2 =
                ЗаписьРегистраТоварыНаСкладах.findObjectsByValue(
                        "idDoc", this.id);

        for (int i = 0; i < записиРегистраДокумента2.size(); i++) {
            var строка = записиРегистраДокумента2.get(i);

            if(!строка.delete())
                return false;
        }

        List<ЗаписьРегистраСебестоимостьЕдПродукции> записиРегистраДокумента3 =
                ЗаписьРегистраСебестоимостьЕдПродукции.findObjectsByValue(
                        "idDoc", this.id);

        for (int i = 0; i < записиРегистраДокумента3.size(); i++) {
            var строка = записиРегистраДокумента3.get(i);

            if(!строка.delete())
                return false;
        }

        return true;
    }

    @Override
    protected boolean ПередЗаписью() {
        this.setFinalSum();
        if ((this.getDate() == null || this.getNumber() == null
                || this.getContragent_() == null || this.getFinalSum() == null
                || this.getTable_part_list_of_products_() == null) || !this.ПроверкаНаличия())
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

    public void toStringTablePart () {
        for (int i = 0; i < this.table_part_list_of_products_.size(); i++) {
            System.out.println( table_part_list_of_products_.get(i).getLineNumber() + " " +
                    table_part_list_of_products_.get(i).getNomenclature_().getName() +" " +
                    table_part_list_of_products_.get(i).getPrice() + " руб. " +
                    table_part_list_of_products_.get(i).getAmount() + " " +
                    table_part_list_of_products_.get(i).getSum())  ;
            System.out.println( );
        }
    }

}
