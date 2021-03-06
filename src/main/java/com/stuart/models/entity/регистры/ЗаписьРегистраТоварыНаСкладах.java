package com.stuart.models.entity.регистры;

import com.stuart.dao.DataAccessObject;
import com.stuart.models.entity.ЗаписьБД;
import com.stuart.models.entity.документы.Документ;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "register_products_in_stock", schema = "study_db")
public class ЗаписьРегистраТоварыНаСкладах extends ЗаписьРегистра {

    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID id = UUID.randomUUID();
    @Column(columnDefinition = "BINARY(16)")
    private UUID idDoc;
    private String typeDoc;
    private Date date;
    @Column(columnDefinition = "BINARY(16)")
    private UUID idNom;
    private Double amount;
    private Double sum;
    @Transient
    private Документ registrarDoc;

    public static String getType() {
        return "ЗаписьРегистраТоварыНаСкладах";
    }

    public static ЗаписьРегистраТоварыНаСкладах findObjectByValue(String fieldName, Object fieldValue) {
        return (ЗаписьРегистраТоварыНаСкладах) DataAccessObject.findObjectByValue(getType(), fieldName, fieldValue);
    }

    public static List<ЗаписьРегистраТоварыНаСкладах> findObjectsByValue(String fieldName, Object fieldValue){
        List<ЗаписьРегистраТоварыНаСкладах> Result = new ArrayList<ЗаписьРегистраТоварыНаСкладах>();
        List<ЗаписьБД> Записи = DataAccessObject.findObjectsByValue(getType(), fieldName, fieldValue);
        for(var Запись:Записи) Result.add((ЗаписьРегистраТоварыНаСкладах)Запись);
        return Result;
    }

    @Override
    public boolean ПередЗаписью() {
        if (this.getRegistrarDoc() == null
                || this.getAmount() == null
                || this.getDate() == null
                || this.getIdDoc() == null
                || this.getIdNom() == null
                || this.getSum() == null
                || this.getTypeDoc() == null)
            return false;
        else
            return true;
    }

    @Override
    public String toString() {
        return "ЗаписьРегистраТоварыНаСкладах{" +
                "Дата: "  +
                "; Контрагент: " + amount +
                '}';
    }

}
//    public Документ getДокумент() {
//
//        !!! ЭТОТ МЕТОД НАДО ПЕРЕДАЛАТЬ !!!
//            старый кусок кода, который для строки регистра ищет свой документ
//
//        final SessionFactory factory = new Configuration().configure().buildSessionFactory();
//        try (final Session session = factory.openSession()) {
//
//            if (registrarDoc ==null)
//                switch (typeDoc) {
//                    case "Закупка": {
//                        org.hibernate.query.Query<Закупка> query1 = session.createQuery("from Закупка node where node.id = :param");
//                        query1.setParameter("param", idDoc);
//                        Закупка закупка  = query1.uniqueResult();
//                        registrarDoc = закупка;
//                    }
//                    case "Реализация": {
//                        org.hibernate.query.Query<Реализация> query2 = session.createQuery("from Реализация node where node.id = :param");
//                        query2.setParameter("param", idDoc);
//                        Реализация реализация  = query2.uniqueResult();
//                        registrarDoc = реализация;
//                    }
//                    case "Производство": {
//                        Query<Производство> query3 = session.createQuery("from Производство node where node.id = :param");
//                        query3.setParameter("param", idDoc);
//                        Производство производство  = query3.uniqueResult();
//                        registrarDoc = производство;
//                    }
//                }
//            session.close();
//
//        } catch (Exception e) {
//            System.out.println("Не удалось создать запись: " + e.getMessage()); //обработать исключение БД и не дать записать в базу
//        }
//
//        return registrarDoc;
//    }


