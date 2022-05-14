package com.stuart.dao.записьБД;

import com.stuart.models.entity.ЗаписьБД;
import com.stuart.models.entity.документы.закупка.ЗаписьТЧ_Закупка;
import com.stuart.models.entity.справочники.ЗаписьЭтапыПроизводства;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class DataAccessObject {

    private static SessionFactory _factory;
    private static Session _session;

    private static SessionFactory getFactory(){
        if (_factory == null){
            _factory = new Configuration().configure().buildSessionFactory();
        }
        return _factory;
    }

    public static Session getCurrentSession() {
        return _session;
    }

    public static Session openSessionBeginTransaction() {
        _session = getFactory().openSession();
        _session.beginTransaction();
        return  _session;
    }

    public static void commitTransactionCloseSession()  {
        _session.getTransaction().commit();
        _session.close();
        _session = null;
    }

    public static void rollbackTransactionCloseSession(){
        _session.getTransaction().rollback();
        _session.close();
        _session = null;
    }

    public static boolean save(ЗаписьБД записьБД) {

        if (_session == null) {
            try (final Session newSession = getFactory().openSession()) {

                var tran = newSession.beginTransaction();
                newSession.saveOrUpdate(записьБД);
                tran.commit();
                newSession.close();

                return true;
            }
            catch (Exception e) {
                System.out.println("Не удалось создать запись: " + e.getMessage()); //обработать исключение БД и не дать записать в базу
                return false;
            }
        }
        else {
            try {
                _session.save(записьБД);
                return true;
            }
            catch (Exception e) {
                System.out.println("Не удалось создать запись: " + e.getMessage()); //обработать исключение БД и не дать записать в базу
                return false;
            }
        }
    }

    public static boolean delete(ЗаписьБД записьБД) {
        if (_session == null) {
            try (final Session newSession = getFactory().openSession()) {

                var tran = newSession.beginTransaction();
                newSession.delete(записьБД);
                tran.commit();
                newSession.close();

                return true;
            }
            catch (Exception e) {
                System.out.println("Не удалось удалить запись: " + e.getMessage()); //обработать исключение БД и не дать записать в базу
                return false;
            }
        }
        else {
            try {
                _session.delete(записьБД);
                return true;
            }
            catch (Exception e) {
                System.out.println("Не удалось удалить запись: " + e.getMessage()); //обработать исключение БД и не дать записать в базу
                return false;
            }
        }
    }

    public static ЗаписьБД findObjectByValue(String tableName, String fieldName, Object fieldValue) {

        ЗаписьБД result = null;

        if (_session == null) {
            try (final Session newSession = getFactory().openSession()) {

                Query<ЗаписьБД> query = newSession.createQuery("from " + tableName + " table where table." + fieldName + " = :param");
                query.setParameter("param", fieldValue);
                result = query.uniqueResult();

                newSession.close();
            }
            catch (Exception e) {
                System.out.println("Не удалось найти запись: " + e.getMessage());
            }
        }
        else {
            try {
                Query<ЗаписьБД> query = _session.createQuery("from " + tableName + " table where table." + fieldName + " = :param");
                query.setParameter("param", fieldValue);
                result = query.uniqueResult();
            }
            catch (Exception e) {
                System.out.println("Не удалось найти запись: " + e.getMessage());
            }
        }
        return result;
    }

    public static List<ЗаписьБД> findObjectsByValue(String tableName, String fieldName, Object fieldValue) {

        List<ЗаписьБД> result = null;

        if (_session == null) {
            try (final Session newSession = getFactory().openSession()){

                Query<ЗаписьБД> query = newSession.createQuery("from " + tableName + " table where table." + fieldName + " = :param");
                query.setParameter("param", fieldValue);
                result = query.getResultList();

                newSession.close();
            }
            catch (Exception e) {
                System.out.println("Не удалось найти записи: " + e.getMessage());
            }
        }
        else {
            try {
                Query<ЗаписьБД> query = _session.createQuery("from " + tableName + " table where table." + fieldName + " = :param");
                query.setParameter("param", fieldValue);
                result = query.getResultList();
            }
            catch (Exception e) {
                System.out.println("Не удалось найти записи: " + e.getMessage());
            }
        }
        return result;
    }

//    public static int getSizeTable(String tableName) {
//        Integer result = null;
//
//        if (_session == null) {
//            try (final Session newSession = getFactory().openSession()){
//
//                Query<Integer> query = newSession.createQuery("select " + "count(*) " +
//                        "from " + tableName + " table where table." + " = :param");
//                query.setParameter("param", tableName);
//                result = query.getSingleResult();
//
//                newSession.close();
//            }
//            catch (Exception e) {
//                System.out.println("Не удалось найти записи: " + e.getMessage());
//            }
//        }
//        else {
//            try {
//                Query<ЗаписьБД> query = _session.createQuery("from " + tableName + " table where table." + fieldName + " = :param");
//                query.setParameter("param", fieldValue);
//                result = query.getResultList();
//            }
//            catch (Exception e) {
//                System.out.println("Не удалось найти записи: " + e.getMessage());
//            }
//        }
//        return result;
//    }
}
