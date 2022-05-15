package com.stuart.models.entity.документы.производство;

import com.stuart.dao.записьБД.DataAccessObject;
import com.stuart.models.entity.ЗаписьБД;
import com.stuart.models.entity.документы.Документ;
import com.stuart.models.entity.регистры.ЗаписьРегистраТоварыНаСкладах;
import lombok.*;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.*;
import javax.print.Doc;
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
@Table(name = "doc_manufacture", schema = "study_db")
public class Производство extends Документ {
    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID id = UUID.randomUUID();
    private Date date;
    private Integer number;
    private boolean pometkaProvedeniya;
    //Производство-ЗаписьТЧРасходМатериалов
    //таблица БД "doc_manufacture"-"table_part_material_consuption"
    @OneToMany(mappedBy = "doc_manufacture_", fetch = FetchType.LAZY)
    private List<ЗаписьТЧРасходМатериалов> table_part_material_consuption_
            = new ArrayList<>();
    //Производство-ЗаписьТЧПроизведеноПродукции
    //таблица БД "doc_manufacture"-"table_part_produced_of_products"
    @OneToMany (mappedBy = "doc_manufacture_", fetch = FetchType.LAZY)
    private List<ЗаписьТЧПроизведеноПродукции> table_part_produced_of_products_
            = new ArrayList<>();
    @Transient
    private List<ЗаписьРегистраТоварыНаСкладах> registerProductsInStock;

    public Производство(Date date) {
        this.date = date;
        this.number = Документ.GetRandomNum();
        this.pometkaProvedeniya = false;
    }

    private void initRegisterCalculation () {
        this.registerProductsInStock = ЗаписьРегистраТоварыНаСкладах.findObjectsByValue("idDoc", this.id);
    }

    public void setPometkaProvedeniya() {
        this.pometkaProvedeniya = false;
    }

    public void setNumber() {
        this.number = Документ.GetRandomNum();
    }

    public static Производство findObjectByValue(String fieldName, Object fieldValue) {
        return (Производство) DataAccessObject.findObjectByValue(getType(), fieldName, fieldValue);
    }

    public static List<Производство> findObjectsByValue(String fieldName, Object fieldValue){
        List<Производство> Result = new ArrayList<Производство>();
        List<ЗаписьБД> Записи = DataAccessObject.findObjectsByValue(getType(), fieldName, fieldValue);
        for(var Запись:Записи) Result.add((Производство)Запись);
        return Result;
    }

    private static String getType() {
        return "Производство";
    }

    private Double ПолучитьСтоимостьЕдиницыПроизведеннойПродукции() {
        var проводкиДокумента =
                ЗаписьРегистраТоварыНаСкладах.findObjectsByValue("idDoc", this.getId());
        Double sum = 0D;
        for (int i = 0; i < проводкиДокумента.size(); i++) {
            sum = sum + (проводкиДокумента.get(i).getSum() * -1);
        }
        Double amount = 0D;
        for (int i = 0; i < this.table_part_produced_of_products_.size(); i++) {
            amount = amount + this.table_part_produced_of_products_.get(i).getAmount();
        }
        Double стоимостьЕдиницы = sum/amount;
        return стоимостьЕдиницы;
    }

    public ЗаписьТЧРасходМатериалов ДобавитьЗаписьВ_ТЧ_Расход() {
        var ЗаписьТЧ = new ЗаписьТЧРасходМатериалов();
        ЗаписьТЧ.setDoc_manufacture_(this);
        ЗаписьТЧ.setIdDoc(this.getId());
        this.table_part_material_consuption_.add(ЗаписьТЧ); //по мере создания записей добавляем их в табличную часть документа
        return ЗаписьТЧ;
    }

    public ЗаписьТЧПроизведеноПродукции ДобавитьЗаписьВ_ТЧ_Произведено () {
        var ЗаписьТЧ = new ЗаписьТЧПроизведеноПродукции();
        ЗаписьТЧ.setDoc_manufacture_(this);
        ЗаписьТЧ.setIdDoc(this.getId());
        this.table_part_produced_of_products_.add(ЗаписьТЧ); //по мере создания записей добавляем их в табличную часть документа
        return ЗаписьТЧ;
    }

    private boolean ЗаписатьТабЧасти_Расход() {

        List<ЗаписьТЧРасходМатериалов> записиТЧ =
                ЗаписьТЧРасходМатериалов.findObjectsByValue("idDoc", this.id);

        for (int i = 0; i < записиТЧ.size(); i++) {
            var строка = записиТЧ.get(i);
            if (!строка.delete())
                return false;
        }

        for (int i = 0; i < table_part_material_consuption_.size(); i++) {
            var СтрТЧ = table_part_material_consuption_.get(i);
            СтрТЧ.setLineNumber(i + 1);
            if (!СтрТЧ.save())
                return false;
        }

        return true;
    }

    private boolean ЗаписатьТабЧасти_Произведено() {

        List<ЗаписьТЧПроизведеноПродукции> записиТЧ =
                ЗаписьТЧПроизведеноПродукции.findObjectsByValue("idDoc", this.id);

        for (int i = 0; i < записиТЧ.size(); i++) {
            var строка = записиТЧ.get(i);
            if (!строка.delete())
                return false;
        }

        for (int i = 0; i < table_part_produced_of_products_.size(); i ++) {
            var СтрТЧ = table_part_produced_of_products_.get(i);
            СтрТЧ.setLineNumber(i+1);
            if(!СтрТЧ.save())
                return false;
        }
        return true;
    }

    @Override
    protected boolean ЗаписатьРегистрыТоварыНаСкладе() {

        //заполнение строк регистра на расход материалов по таблице Table_part_material_consuption_
        for (int i = 0; i < this.getTable_part_material_consuption_().size(); i++) {
            var стрРегистраРасход = new ЗаписьРегистраТоварыНаСкладах();
            стрРегистраРасход.setDate(this.getDate());
            стрРегистраРасход.setAmount(-(this.getTable_part_material_consuption_().get(i).getAmount()));
            стрРегистраРасход.setIdNom(this.getTable_part_material_consuption_().get(i).getNomenclature_().getId());
            стрРегистраРасход.setIdDoc(this.getId());
            стрРегистраРасход.setTypeDoc(this.getType());

            Double sum = DataAccessObject.ПолучитьСреднееПоРегиструТовары(this.getId(), стрРегистраРасход.getIdNom(),
                    this.getTable_part_material_consuption_().get(i).getAmount());
            стрРегистраРасход.setSum(-sum);

            if (!стрРегистраРасход.save()) return false;
        }

        //заполнение строк регистра на Произведено продукции по таблице Table_part_produced_of_products_
        for (int i = 0; i < this.getTable_part_produced_of_products_().size(); i++) {
            var стрРегистраПриход = new ЗаписьРегистраТоварыНаСкладах();
            стрРегистраПриход.setDate(this.getDate());
            стрРегистраПриход.setAmount(+(this.getTable_part_produced_of_products_().get(i).getAmount()));
            стрРегистраПриход.setIdNom(this.getTable_part_produced_of_products_().get(i).getNomenclature_().getId());
            стрРегистраПриход.setIdDoc(this.getId());
            стрРегистраПриход.setTypeDoc(this.getType());

            Double sum = this.ПолучитьСтоимостьЕдиницыПроизведеннойПродукции() * стрРегистраПриход.getAmount();
            стрРегистраПриход.setSum(+sum);

            if(!стрРегистраПриход.save()) return false;
        }
        return true;
    }

    @Override
    protected boolean ОчисткаРегистров() {
        List<ЗаписьРегистраТоварыНаСкладах> записиРегистраДокумента =
                ЗаписьРегистраТоварыНаСкладах.findObjectsByValue(
                        "idDoc", this.id);

        for (int i = 0; i < записиРегистраДокумента.size(); i++) {
            var строка = записиРегистраДокумента.get(i);

            if(!строка.delete()) return false;
        }
        return true;
    }

    @Override
    public boolean ЗаписатьДокумент() {

        if(!this.ПередЗаписью()) {
            System.out.println("Не удалось записать документ: Не прошло проверку перед записью.");
            return false;
        }

        if (!this.save()) {
            System.out.println("Ошибка при записи документа");
            return false;
        }

        if(!this.ЗаписатьТабЧасти_Расход()) {
            System.out.println("Не удалось записать документ: Ошибка записи записи табличной части Расход");
            return false;
        }

        if(!this.ЗаписатьТабЧасти_Произведено()) {
            System.out.println("Не удалось записать документ: Ошибка записи табличной части Произведено");
            return false;
        }

        if(!this.ОчисткаРегистров()) {
            System.out.println("Не удалось записать документ: Ошибка очистки регистров");
            return false;
        }

        if (this.pometkaProvedeniya && !this.ЗаписатьРегистрыТоварыНаСкладе()) {
            System.out.println("Не удалось записать документ: Ошибка записи регистра ТоварыНаСкладе");
            return false;
        }

        return true;
    }

    @Override
    public boolean Проведение() {

        this.pometkaProvedeniya = true;

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
    protected boolean ПроверкаНаличия() {

        boolean result = false;
        for (int i = 0; i < table_part_material_consuption_.size(); i++) {

            var idDoc = this.getId();
            var idNom = this.table_part_material_consuption_.get(i).getNomenclature_().getId();
            var amountNom = DataAccessObject.ПолучитьОстатокПоРегиструТовары(idNom, "amount", idDoc);

            if(amountNom >= table_part_material_consuption_.get(i).getAmount())
                result = true;
            else
                System.out.println("Номенклатуры "
                        + this.table_part_material_consuption_.get(i).getNomenclature_().getName().toString()
                        + " недостаточно для списания! "
                        + "Для реализации доступно " + amountNom + " единиц");
        }
        return result;
    }

    @Override
    protected boolean ПередЗаписью() {

        if (this.getDate() == null || this.getNumber() == null
                || this.getTable_part_produced_of_products_()==null
                || this.getTable_part_material_consuption_()==null || !this.ПроверкаНаличия())
            return false;
        else
            return true;
    }


    @Override
    public String toString() {
        return "Производство № " + number + " от "
                + date.toString() ;
    }
}
