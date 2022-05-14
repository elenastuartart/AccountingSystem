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
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    private List<ЗаписьРегистраТоварыНаСкладах> registerProductsInStock;
    @Transient
    private List<ЗаписьРегистраВзаиморасчеты> registerCalculations;

    public Закупка(Date date, ЗаписьКонтрагент contragent_) {
        this.date = date;
        this.number = Документ.GetRandomNum();
        this.pometkaProvedeniya = false;
        this.contragent_ = contragent_;
    }

    public void initRegisterCalculation () {
        this.registerCalculations = ЗаписьРегистраВзаиморасчеты.findObjectsByValue("idDoc", this.id);
        this.registerProductsInStock = ЗаписьРегистраТоварыНаСкладах.findObjectsByValue("idDoc", this.id);
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

    public void setNumber() {
        this.number = Документ.GetRandomNum();
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

    public ЗаписьТЧ_Закупка ДобавитьЗаписьВТЧ() {
        var ЗаписьТЧ = new ЗаписьТЧ_Закупка();
        ЗаписьТЧ.setDoc_purchase_(this);
        ЗаписьТЧ.setIdDoc(this.getId());
        this.table_part_purchase_.add(ЗаписьТЧ); //по мере создания записей добавляем их в табличную часть документа

        return ЗаписьТЧ;
    }

    @Override
    protected boolean ЗаписатьТабЧасти() {

        List<ЗаписьТЧ_Закупка> записиТЧ =
                ЗаписьТЧ_Закупка.findObjectsByValue("idDoc", this.id);

        for (int i = 0; i < записиТЧ.size(); i++) {
            var строка = записиТЧ.get(i);

            if(!строка.delete())
                return false;
        }

        for (int i = 0; i < table_part_purchase_.size(); i ++) {
            var СтрТЧ = table_part_purchase_.get(i);
            СтрТЧ.setLineNumber(i+1);

            if(!СтрТЧ.save())
                    return false;
        }

        return true;
    }

    @Override
    protected boolean ЗаписатьРегистрыВзаиморасчетов() {

        var СтрРегистра = new ЗаписьРегистраВзаиморасчеты();
        СтрРегистра.setDate(this.getDate());
        СтрРегистра.setContragent_(this.getContragent_());
        СтрРегистра.setSum(-(this.getFinalSum()));
        СтрРегистра.setTypeDoc(getType());
        СтрРегистра.setIdDoc(this.getId());

        if(!СтрРегистра.save())
            return  false;

        return true;
    }

    @Override
    protected boolean ЗаписатьРегистрыТоварыНаСкладе() {

        for (int i = 0; i < this.getTable_part_purchase_().size(); i++) {

            var СтрРегистра = new ЗаписьРегистраТоварыНаСкладах();
            СтрРегистра.setDate(this.getDate());
            СтрРегистра.setIdNom(this.getTable_part_purchase_().get(i).getNomenclature_().getId());
            СтрРегистра.setAmount(this.getTable_part_purchase_().get(i).getAmount());
            СтрРегистра.setSum(0D);
            СтрРегистра.setTypeDoc(this.getType());
            СтрРегистра.setIdDoc(this.getId());

            if(!СтрРегистра.save())
                return  false;
        }

        return true;
    }

    @Override
    protected boolean ОчисткаРегистров() {
       List<ЗаписьРегистраВзаиморасчеты> записиРегистраДокумента1 =
                ЗаписьРегистраВзаиморасчеты.findObjectsByValue("idDoc", this.id);

        for (int i = 0; i < записиРегистраДокумента1.size(); i++) {
            var строка = записиРегистраДокумента1.get(i);

            if(!строка.delete())
                return false;
        }

        List<ЗаписьРегистраТоварыНаСкладах> записиРегистраДокумента =
                ЗаписьРегистраТоварыНаСкладах.findObjectsByValue(
                        "idDoc", this.id);

        for (int i = 0; i < записиРегистраДокумента.size(); i++) {
            var строка = записиРегистраДокумента.get(i);

            if(!строка.delete())
                return false;
        }
        return true;
    }

    @Override
    protected boolean ПередЗаписью() {
        this.setFinalSum();
        if (this.getDate() == null || this.getNumber() == null
                || this.getContragent_() == null || this.getFinalSum() == null
                || this.getTable_part_purchase_() == null  || !this.ПроверкаНаличия())
            return false;
        else
            return true;
    }
    //проверка наличия денежных средств для осуществления Закупки
    @Override
    protected boolean ПроверкаНаличия() {
        Session session = DataAccessObject.getCurrentSession();

        Query<Double> query = session.createQuery("select " +
                "sum (zrv.sum) from ЗаписьРегистраВзаиморасчеты zrv ");
        Double sum = query.uniqueResult();

        if(sum>=this.finalSum)
            return true;
        else
            System.out.println("Недостаточно средств для осуществления закупки! Проверьте баланс и повторите попытку");
            return false;
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
            System.out.println("Не удалось записать документ: Ошибка записи табличной части Закупка");
            return false;
        }

        if(!this.ОчисткаРегистров()) {
            System.out.println("Не удалось записать документ: Ошибка очистки регистров");
            return false;
        }

        if (pometkaProvedeniya) {

            if(!this.ЗаписатьРегистрыВзаиморасчетов()) {
                System.out.println("Не удалось записать документ: Ошибка записи регистра Взаиморасчеты");
                return false;
            }

            if(!this.ЗаписатьРегистрыТоварыНаСкладе()) {
                System.out.println("Не удалось записать документ: Ошибка записи регистра ТоварыНаСкладе");
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
