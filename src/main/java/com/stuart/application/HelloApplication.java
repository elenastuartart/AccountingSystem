package com.stuart.application;

import com.stuart.models.entity.документы.Документ;
import com.stuart.models.entity.документы.закупка.Закупка;
import com.stuart.models.entity.документы.закупка.ЗаписьТЧ_Закупка;
import com.stuart.models.entity.документы.продажа.Реализация;
import com.stuart.models.entity.документы.производство.Производство;
import com.stuart.models.entity.регистры.ЗаписьРегистраВзаиморасчеты;
import com.stuart.models.entity.справочники.test;
import com.stuart.models.entity.справочники.ЗаписьКонтрагент;
import com.stuart.models.entity.справочники.ЗаписьНоменклатура;
import com.stuart.models.entity.справочники.ЗаписьЭтапыПроизводства;
import lombok.extern.log4j.Log4j2;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.Date;

@Log4j2
public class HelloApplication {

    public static void main(String[] args) {
        SessionFactory factory = null;

        log.info("Hibernate tutorial started");

        try {
            factory = new Configuration().configure().buildSessionFactory();
            final Session session = factory.openSession();

            //ЗаписьБД_DAO записьБД_dao = new ЗаписьБД_DAO(factory);
//
//            final ЗаписьКонтрагент КА1 = new ЗаписьКонтрагент();
//            КА1.setCode(101011);
//            КА1.setAddress("Москва");
//            КА1.setContact_person("contragent11@mail.ru");
//            КА1.setName("More dolls");
//            КА1.setType_KA("продавец");
//            session.beginTransaction();
//            session.save(КА1);
//            session.getTransaction().commit();
//            session.close();

            Query<ЗаписьКонтрагент> query = session.createQuery("from ЗаписьКонтрагент node1 where node1.code = :param");
            query.setParameter("param", 101003);
            ЗаписьКонтрагент записьКонтрагент = query.uniqueResult();
//            System.out.println(записьКонтрагент.getId().toString());
//            session.beginTransaction();
//            session.delete(записьКонтрагент);
//            session.getTransaction().commit();
//            session.close();

//            final ЗаписьНоменклатура Ном1 = new ЗаписьНоменклатура();
//            Ном1.setName("Батист синий тонкий");
//            Ном1.setCode(201010);
//            Ном1.setArticle_number(500120);
//            Ном1.setCategory("Материалы для производства");
//            Ном1.setSubcategory("Изготовление одежды и аксессуаров");
//            Ном1.setContragent_(записьКонтрагент);
//            session.beginTransaction();
//            session.save(Ном1);
//            session.getTransaction().commit();
//            session.close();

            Query<ЗаписьНоменклатура> query3 = session.createQuery("from ЗаписьНоменклатура node2 where node2.code = :param");
            query3.setParameter("param", 201010);
            ЗаписьНоменклатура записьНоменклатура3 = query3.uniqueResult();
            System.out.println(записьНоменклатура3.getId().toString());
//
//            session.beginTransaction();
//            записьНоменклатура.setContragent_(записьКонтрагент);
//            session.update(записьНоменклатура);
//            session.getTransaction().commit();
//            session.close();

//            final ЗаписьЭтапыПроизводства этап1 = new ЗаписьЭтапыПроизводства();
//            этап1.setCode(300107);
//            этап1.setName("укладка парика");
//            этап1.setDescriprion_stage(" ");
//            session.beginTransaction();
//            session.save(этап1);
//            session.getTransaction().commit();
////            session.close();
//
//
            Закупка закупка = new Закупка();
            закупка.setDate(new Date());
            закупка.setPometkaProvedeniya();
            закупка.setNumber(4140012);
            закупка.setContragent_(записьКонтрагент);
            session.beginTransaction();
            session.save(закупка);
            session.getTransaction().commit();

//            Query<Закупка> query4 = session.createQuery("from Закупка node2 where node2.number = :param");
//            query4.setParameter("param", 4140001);
//            Закупка закупка  = query4.uniqueResult();
//            System.out.println(закупка.getId().toString());
//            session.close();
////
//            ЗаписьТЧ_Закупка тч_закупка = new ЗаписьТЧ_Закупка();
//            тч_закупка.setNomenclature_(записьНоменклатура);
//            тч_закупка.setAmount(5.7D);
//            тч_закупка.setPrice(1000D);
//            тч_закупка.setSum();
//            тч_закупка.setDoc_purchase_(закупка);
//            закупка.ЗаполнитьТЧ(тч_закупка);
//
//            ЗаписьТЧ_Закупка тч_закупка1 = new ЗаписьТЧ_Закупка();
//            тч_закупка1.setNomenclature_(записьНоменклатура2);
//            тч_закупка1.setAmount(2D);
//            тч_закупка1.setPrice(2500D);
//            тч_закупка1.setSum();
//            тч_закупка1.setDoc_purchase_(закупка);
//            закупка.ЗаполнитьТЧ(тч_закупка1);
//
//            ЗаписьТЧ_Закупка тч_закупка2 = new ЗаписьТЧ_Закупка();
//            тч_закупка2.setNomenclature_(записьНоменклатура3);
//            тч_закупка2.setAmount(5.5D);
//            тч_закупка2.setPrice(860D);
//            тч_закупка2.setSum();
//            тч_закупка2.setDoc_purchase_(закупка);
//            закупка.ЗаполнитьТЧ(тч_закупка2);
//            System.out.println(закупка.getTable_part_purchase_().size());

//            session.beginTransaction();
////            for (int i = 0; i < закупка.getTable_part_purchase_().size(); i++) {
////                var СтрТЧ = закупка.getTable_part_purchase_().get(i);
////                СтрТЧ.setLineNumber(i+1);
////                session.save(СтрТЧ);
////            }
////            закупка.setFinalSum();
//            закупка.ЗаписатьТабЧасти();
//            session.getTransaction().commit();
//            session.close();

//            session.beginTransaction();
//            session.save(тч_закупка1);
//            session.getTransaction().commit();
//            session.close();


//            CriteriaBuilder cb = session.getCriteriaBuilder();
//            CriteriaUpdate<ЗаписьКонтрагент> criteriaUpdate = cb.createCriteriaUpdate(ЗаписьКонтрагент.class);
//            Root<ЗаписьКонтрагент> root = criteriaUpdate.from(ЗаписьКонтрагент.class);
//            criteriaUpdate.set("Наименование", "Поле изменено!");
//            criteriaUpdate.where(cb.equal(root.get("ID"), записьКонтрагент.getID()));

//            ЗаписьРегистраВзаиморасчеты регистр = new ЗаписьРегистраВзаиморасчеты();
//            session.beginTransaction();
//            регистр.setDate(закупка.getDate());
//            регистр.setContragent_(закупка.getContragent_());
//            регистр.setSum(закупка.getFinalSum());
//            session.save(регистр);
//            session.getTransaction().commit();
//            session.close();


//        session.close();

       }
        finally {
            if (factory != null) {
                factory.close();
            }
        }
    }
}
