package com.stuart.dao.записьВБД;


import com.stuart.dao.DAO;
import com.stuart.models.entity.ЗаписьБД;
import com.stuart.models.entity.документы.Документ;
import com.stuart.models.entity.регистры.ЗаписьРегистра;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ЗаписьБД_DAO implements DAO{
    private final SessionFactory factory;

    public ЗаписьБД_DAO(final SessionFactory factory) {
        this.factory = factory;
    }


    @Override
    public void create(ЗаписьБД записьБД) { // запись в БД
        try (final Session session = factory.openSession()) {
            boolean ПроверкаЗаписи = записьБД.ПередЗаписью();
            if (ПроверкаЗаписи==true) {
                session.beginTransaction();
                session.save(записьБД);
                session.getTransaction().commit();
            }
        }
        catch(Exception e){
                System.out.println("Заполните все поля!");
        }
            //обработать исключение БД
    }


    @Override
    public void Проведение(Документ документ, ЗаписьРегистра регистр) {
        try (final Session session = factory.openSession()) {
            boolean ПроверкаДокумента = документ.ПередЗаписью();
            boolean ПроверкаРегистра = регистр.ПередЗаписью();
            if (ПроверкаДокумента || ПроверкаРегистра) {
                документ.setПометкаПроведения(true);
                session.beginTransaction();
                session.save(документ);
                session.save(регистр);
                session.getTransaction().commit();
            }
        }
        catch (Exception e) {
            System.out.println("Заполните все поля!");
        }
    }


    @Override
    public void ОтменаПроведения() {

    }

    //    @Override
//    public ЭлементСправочника read(final int code) {
//        try (final Session session = factory.openSession()) {
//            final ЭлементСправочника result = session.get(ЭлементСправочника.class, code);
//            return result != null ? result : new ЭлементСправочника();
//        }
//    }

    @Override
    public void save() {

    }

    @Override
    public void update() {
//        try (final Session session = factory.openSession()) {
//            session.beginTransaction();
//            session.update(ЭлементСправочника);
//            session.getTransaction().commit();
//        }
    }

    @Override
    public void delete() {

    }

}
