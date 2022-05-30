package com.stuart.models.entity.справочники;

import com.stuart.dao.DataAccessObject;
import com.stuart.models.entity.ЗаписьБД;
import com.stuart.models.entity.документы.закупка.Закупка;
import com.stuart.models.entity.документы.продажа.Реализация;
import com.stuart.models.entity.регистры.ЗаписьРегистраВзаиморасчеты;
import lombok.*;

import javax.persistence.*;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "contragent", schema = "study_db")
public class ЗаписьКонтрагент extends ЭлементСправочника {

    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID id = UUID.randomUUID();
    private Integer code;
    private String name;
    private String contact_person;
    private String address;
    private String type_KA; //поставщик/покупатель
    //ЗаписьКонтрагент-Закупка
    //таблица БД "contragent"-"doc_purchase"
    @OneToMany(mappedBy = "contragent_", fetch = FetchType.LAZY)
    private List<Закупка> doc_purchase_;
    //  ЗАПИСЬ_КОНТРАГЕНТ-ЗАПИСЬ_НОМЕНКЛАТУРА связь с классом "ЗаписьНоменклатура" (таблица БД "nomenclature")
    //(таблица БД "contragent"-"nomenclature") list nomenclaturies_
    @OneToMany(mappedBy = "contragent_", fetch = FetchType.LAZY)
    private List<ЗаписьНоменклатура> nomenclaturies_;
    //ЗаписьКонтрагент-Реализация
    //таблица БД БД "contragent"-"doc_sale"
    @OneToMany(mappedBy = "contragent_", fetch = FetchType.LAZY)
    private List<Реализация> doc_sale_;
    //ЗаписьКонтрагент-ЗаписьРегистраВзаиморасчеты
    //таблица БД "contragent"-"register_calculation"
    @OneToMany(mappedBy = "contragent_", fetch = FetchType.LAZY)
    private List<ЗаписьРегистраВзаиморасчеты> register_calculation_;

    public ЗаписьКонтрагент(String name, String contact_person, String address, String type_KA) {
        this.code = ЭлементСправочника.GetRandomCode();
        this.name = name;
        this.contact_person = contact_person;
        this.address = address;
        this.type_KA = type_KA;
    }

    public void setCode() {
        this.code = ЭлементСправочника.GetRandomCode();
    }

    public Integer getCode() {
        return code;
    }

    public static ЗаписьКонтрагент findObjectByValue(String fieldName, Object fieldValue) {
        return (ЗаписьКонтрагент)DataAccessObject.findObjectByValue(getType(), fieldName, fieldValue);
    }

    public static List<ЗаписьКонтрагент> findObjectsByValue(String fieldName, Object fieldValue){
        List<ЗаписьКонтрагент> Result = new ArrayList<ЗаписьКонтрагент>();
        List<ЗаписьБД> Записи = DataAccessObject.findObjectsByValue(getType(), fieldName, fieldValue);
        for(var Запись:Записи) Result.add((ЗаписьКонтрагент)Запись);
        return Result;
    }

    public static String getType() {
        return "ЗаписьКонтрагент";
    }

    @Override
    public boolean ПередЗаписью() {
        if (this.getCode() == null
                || this.getName() == null || this.getContact_person() == null
                || this.getType_KA()==null)
            return false;
        else
            return true;
    }

    @Override
    public String toString() {
        return "ЗаписьКонтрагент{" +
                "Код=" + code +
                ", Наименование='" + name + '\'' +
                ", КонтактноеЛицоКА='" + contact_person + '\'' +
                ", АдресКА='" + address + '\'' +
                ", ТипКонтрагента='" + type_KA + '\'' +
                '}';
    }





}
