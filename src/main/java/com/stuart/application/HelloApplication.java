package com.stuart.application;

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


            final ЗаписьКонтрагент КА1 = new ЗаписьКонтрагент();
//            КА1.setCode(10103);
//            КА1.setAddress("Москва");
//            КА1.setContact_person("aaa2@mail.ru");
//            КА1.setName("Fashion house");
//            КА1.setType_KA("покупатель");
//            session.beginTransaction();
//            session.save(КА1);
//            session.getTransaction().commit();
//            session.close();

//            Query<ЗаписьКонтрагент> query = session.createQuery("from ЗаписьКонтрагент node1 where node1.code = :param");
//            query.setParameter("param", 10103);
//            ЗаписьКонтрагент записьКонтрагент = query.uniqueResult();
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


            //ЗаписьНоменклатура Ном2 = session.get(ЗаписьНоменклатура.class,);
//            Ном1.ЗаписатьЭлементСправочника(factory);

            //-+++Ном1.ИзменитьЗаписьСправочникаНоменклатура(factory, "Карина");

//            Ном1.ЗаписатьСправочник(factory);
//            int res = Ном1.УдалитьЗапись_Из_БД(factory, Ном1.getКод());
//            System.out.println(res);

//            Реализация док1 = new Реализация(new Date(), 12231234, КА1);
//            ЗаписьТЧСписокТоваров запись1 = new ЗаписьТЧСписокТоваров(Ном1, (double)2, (double)200);
//            ЗаписьТЧСписокТоваров запись2 = new ЗаписьТЧСписокТоваров( Ном1, (double)4, (double)50);
//            ЗаписьТЧСписокТоваров запись3 = new ЗаписьТЧСписокТоваров( Ном1, (double)3, (double)30);
////
//            док1.ЗаполнитьТЧ(запись1);
//            док1.ЗаполнитьТЧ(запись2);
//            док1.ЗаполнитьТЧ(запись3);
//            док1.ПосчитатьИтоговуюСумму();

//            System.out.println(док1.toString());
//            док1.toStringTable();
////
//            ЗаписьРегистраВзаиморасчеты взаиморасчеты1 = new ЗаписьРегистраВзаиморасчеты();
//            System.out.println(взаиморасчеты1.toString());
//
//            ЭлементDAO.create(док1);
//            boolean проверка = взаиморасчеты1.ПередЗаписью();
//            System.out.println(проверка);
       }
        finally {
            if (factory != null) {
                factory.close();
            }
        }
    }
}
