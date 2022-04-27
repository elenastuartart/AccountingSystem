package com.stuart.models.entity.регистры;

import com.stuart.dao.записьБД.DataAccessObject;
import com.stuart.models.entity.ЗаписьБД;
import com.stuart.models.entity.документы.Документ;
import com.stuart.models.entity.документы.закупка.Закупка;
import com.stuart.models.entity.документы.продажа.Реализация;
import com.stuart.models.entity.документы.производство.Производство;
import com.stuart.models.entity.справочники.ЗаписьНоменклатура;
import lombok.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "register_products_in_stock", schema = "study_db")
public class ЗаписьРегистраТоварыНаСкладах extends ЗаписьРегистра {

    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID id = UUID.randomUUID();
    private UUID idDoc;
    private String typeDoc;
    //ЗаписьРегистраТоварыНаСкладах-ЗаписьНоменклатура
    //таблица БД "registrar_products_in_stock"-"nomenclature"
    @ManyToOne
    @JoinColumn(name = "nomenclature_id", referencedColumnName = "id")
    private ЗаписьНоменклатура nomenclature_;

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

    public Документ getДокумент() {

        final SessionFactory factory = new Configuration().configure().buildSessionFactory();
        try (final Session session = factory.openSession()) {

            if (registrarDoc ==null)
                switch (typeDoc) {
                    case "Закупка": {
                        org.hibernate.query.Query<Закупка> query1 = session.createQuery("from Закупка node where node.id = :param");
                        query1.setParameter("param", idDoc);
                        Закупка закупка  = query1.uniqueResult();
                        registrarDoc = закупка;
                    }
                    case "Реализация": {
                        org.hibernate.query.Query<Реализация> query2 = session.createQuery("from Реализация node where node.id = :param");
                        query2.setParameter("param", idDoc);
                        Реализация реализация  = query2.uniqueResult();
                        registrarDoc = реализация;
                    }
                    case "Производство": {
                        Query<Производство> query3 = session.createQuery("from Производство node where node.id = :param");
                        query3.setParameter("param", idDoc);
                        Производство производство  = query3.uniqueResult();
                        registrarDoc = производство;
                    }
                }
            session.close();

        } catch (Exception e) {
            System.out.println("Не удалось создать запись: " + e.getMessage()); //обработать исключение БД и не дать записать в базу
        }

        return registrarDoc;
    }

    @Override
    public boolean ПередЗаписью() {
        if (this.getRegistrarDoc() == null || this.getNomenclature_() == null
                || this.getAmount() == null
                || this.getSum() == null)
            return false;
        else
            return true;
    }

    @Override
    public String toString() {
        return "ЗаписьРегистраТоварыНаСкладах{" +
                "Дата: " + nomenclature_.getName().toString() +
                "; Контрагент: " + amount +
                "; Сумма: " + sum +
                '}';
    }

}
