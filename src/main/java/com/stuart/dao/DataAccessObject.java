package com.stuart.dao;

import com.stuart.models.entity.ЗаписьБД;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;
import java.util.UUID;

public class DataAccessObject
//        implements ISpravochnik
{

    private static SessionFactory _factory;
    private static Session _session;

    private static SessionFactory getFactory(){
        if (_factory == null){
            _factory = new Configuration().configure().buildSessionFactory();
        }
        return _factory;
    }

    public static Session get_session() {
        return getFactory().openSession();
    }

    public static Session openSessionBeginTransaction() {
        _session = getFactory().openSession();
        _session.beginTransaction();
        return  _session;
    }

    public static Double ПолучитьОстатокПоРегиструТовары(UUID idNom, String ИмяРесурса, UUID idDoc) {

        Query<Double> query = _session.createQuery("select " + "sum (zrts ." + ИмяРесурса + " ) " +
                "from ЗаписьРегистраТоварыНаСкладах zrts "
                + "where zrts.idNom =: param and zrts.idDoc !=: param2");
        query.setParameter("param", idNom);
        query.setParameter("param2", idDoc);

        return query.uniqueResult();
    }

    public static Double ПолучитьОстатокПоРегиструВзаиморасчеты(UUID idDoc) {

        Query<Double> query = _session.createQuery("select " +
                "sum (zrv.sum) from ЗаписьРегистраВзаиморасчеты zrv "
                + "where zrv.idDoc !=: param");

        query.setParameter("param", idDoc);

        return query.uniqueResult();
    }

    public static Double ПолучитьСреднееПоРегиструТовары (UUID idDoc, UUID idNom, Double amountConsuption) {
        Double amount = DataAccessObject.ПолучитьОстатокПоРегиструТовары(idNom, "amount", idDoc);
        Double sum = DataAccessObject.ПолучитьОстатокПоРегиструТовары(idNom, "sum", idDoc);
        Double price = sum / amount;
        Double averagePrice = price * amountConsuption;
        return averagePrice;
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


    //проблемы с двойным кликом при редактировании

}
