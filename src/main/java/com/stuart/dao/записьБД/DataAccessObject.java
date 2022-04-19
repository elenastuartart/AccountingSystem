package com.stuart.dao.записьБД;


import com.stuart.models.entity.ЗаписьБД;
import com.stuart.models.entity.справочники.ЗаписьЭтапыПроизводства;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class DataAccessObject {

    public static boolean save(ЗаписьБД записьБД) {

        final SessionFactory factory = new Configuration().configure().buildSessionFactory();
        try (final Session session = factory.openSession()) {

            boolean ПроверкаЗаписи = записьБД.ПередЗаписью();

            if (ПроверкаЗаписи==true) {
                var tran = session.beginTransaction();

                session.save(записьБД);

                tran.commit();
                session.close();
            }
            return true;
        }
        catch(Exception e){
            System.out.println("Не удалось создать запись!"); //обработать исключение БД и не дать записать в базу
            return false;
        }
    }

    public static ЗаписьБД findByValue(String tableName, String fieldName, Object fieldValue) {

        ЗаписьБД result = null;

        final SessionFactory factory = new Configuration().configure().buildSessionFactory();
        try (final Session session = factory.openSession()){

            Query<ЗаписьБД> query = session.createQuery("from " + tableName + " table where table." + fieldName + " = :param");
            query.setParameter("param", fieldValue);
            result = query.uniqueResult();

            session.close();
        }

        return result;
    }
}
