package com.stuart.models.entity.регистры;

import com.stuart.dao.записьБД.DataAccessObject;
import com.stuart.models.entity.ЗаписьБД;
import com.stuart.models.entity.документы.Документ;
import com.stuart.models.entity.справочники.ЗаписьКонтрагент;
import com.stuart.models.entity.справочники.ЗаписьНоменклатура;
import lombok.*;
import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "register_calculation", schema = "study_db")
public class ЗаписьРегистраВзаиморасчеты extends ЗаписьРегистра {
    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID id = UUID.randomUUID();
    private Date date;
    private Double sum;
    //ЗаписьРегистраВзаиморасчеты-ЗаписьКонтрагент
    //таблица БД "register_calculation"-"contragent"
    @ManyToOne
    @JoinColumn(name = "contragent_id", referencedColumnName = "id")
    private ЗаписьКонтрагент contragent_;
    @Column(columnDefinition = "BINARY(16)")
    private UUID idDoc;
    private String typeDoc;
    @Transient
    private Документ registrarDoc;

    public static ЗаписьРегистраВзаиморасчеты findObjectByValue(String fieldName, Object fieldValue) {
        return (ЗаписьРегистраВзаиморасчеты) DataAccessObject.findObjectByValue(getType(), fieldName, fieldValue);
    }

    public static List<ЗаписьРегистраВзаиморасчеты> findObjectsByValue(String fieldName, Object fieldValue){
        List<ЗаписьРегистраВзаиморасчеты> Result = new ArrayList<ЗаписьРегистраВзаиморасчеты>();
        List<ЗаписьБД> Записи = DataAccessObject.findObjectsByValue(getType(), fieldName, fieldValue);
        for(var Запись:Записи) Result.add((ЗаписьРегистраВзаиморасчеты)Запись);
        return Result;
    }

    public static String getType() {
        return "ЗаписьРегистраВзаиморасчеты";
    }

    @Override
    public boolean ПередЗаписью() {
        if (
//                this.getRegistrarDoc() == null ||
                this.getDate() == null
                | this.getContragent_()  == null
                || this.getSum() == null)
            return false;
        else
            return true;
    }

    @Override
    public String toString() {
        return "ЗаписьРегистраВзаиморасчеты{" +
                "Дата: " + date.toString() +
                "; Контрагент: " + contragent_.getName() +
                "; Сумма: " + sum +
                '}';
    }
}

//каждый юзер(регистр) имеет один или несколько типов ролей (документов) -
//роль(документ) - типы документов, доступные для юзера(регистра)

//юзер-роль (регистр-документ) - связующая таблица между юзером и ролью (регистром и документом) - много ко многим
//юзер-роль (регистр-документ) ссыдается на таблицу юзер(регистр)по айди (????) и на роль(документ) по айди по внешним ключам

//один юзер(регистр) - несколько ролей(документов) юзер-роль (регистр-док) один ко многим, но один роль(док) может быть у
//нескольких юзеров(регистров)

//создать промежуточную таблицу юзер-роль(регистр-документ) и там каждого юзера(регистра) связывали с его ролями(документами)

//по умолчанию присвается роль2 (создать некий шаблон документа, чтобы заполнялся автоматом)

//когда получаем юзера(регистр) нужно получить и все его роли(документы)

//таблица юзер связана с табл роль (регистр-документ) по принципу многие ко многим через промежуточную таблицу юзер-роль(регистр-док)

//в юзере(регистре) указываем @ManyToMany  и ссылку на коллекцию ролей (документов) 2.21мин
//роль(документ) 3.08мин делаем аннотацию @ManyToMany с листом регистров

//для таблицы юзер-роль (регистр-документ) ентити класс не создаем!!! таблица нужна только для связи

//для каждого юзера(регистра) получить список ролей(документов)

//в роль(документ) связь @ManyToMany пока заккоментируем чтобы работало корректно