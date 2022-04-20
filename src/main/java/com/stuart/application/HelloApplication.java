package com.stuart.application;

import com.stuart.models.entity.справочники.test;
import com.stuart.models.entity.справочники.ЗаписьКонтрагент;
import com.stuart.models.entity.справочники.ЗаписьНоменклатура;
import com.stuart.models.entity.справочники.ЗаписьЭтапыПроизводства;
import lombok.extern.log4j.Log4j2;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

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
//            final test test1 = new test();
//            test1.setName("test1");
//            test1.setNumber(1231);
//            session.beginTransaction();
//            session.save(test1);
//            session.getTransaction().commit();
//            session.close();




            //final ЗаписьКонтрагент КА1 = new ЗаписьКонтрагент();
//            КА1.setCode(10103);
//            КА1.setAddress("Москва");
//            КА1.setContact_person("aaa2@mail.ru");
//            КА1.setName("Fashion house");
//            КА1.setType_KA("покупатель");
//            session.beginTransaction();
//            session.save(КА1);
//            session.getTransaction().commit();
//            session.close();
//
//            Query<test> query = session.createQuery("from test node1 where node1.number = :param");
//            query.setParameter("param", 1231);
//            test testX = query.uniqueResult();
//            System.out.println(testX.getId().toString());

//
//
//            final ЗаписьНоменклатура Ном1 = new ЗаписьНоменклатура();
//            Ном1.setName("Молд Агнесс");
//            Ном1.setCode(10201);
//            Ном1.setArticle_number(500102);
//            Ном1.setCategory("Товары на реализацию");
//            Ном1.setSubcategory("Кукла бланк");
//            Ном1.setProducer(записьКонтрагент);
//            session.beginTransaction();
//            session.save(Ном1);
//            session.getTransaction().commit();
//            session.close();

//            session.beginTransaction();
//            session.delete(testX);
//            session.getTransaction().commit();
//            session.close();





//            CriteriaBuilder cb = session.getCriteriaBuilder();
//            CriteriaUpdate<ЗаписьКонтрагент> criteriaUpdate = cb.createCriteriaUpdate(ЗаписьКонтрагент.class);
//            Root<ЗаписьКонтрагент> root = criteriaUpdate.from(ЗаписьКонтрагент.class);
//
//            criteriaUpdate.set("Наименование", "Поле изменено!");
//
//            criteriaUpdate.where(cb.equal(root.get("ID"), записьКонтрагент.getID()));
//
//            Transaction transaction = session.beginTransaction();
//            session.createQuery(criteriaUpdate).executeUpdate();
//            transaction.commit();
//            session.close();

       }
        finally {
            if (factory != null) {
                factory.close();
            }
        }
    }
}
