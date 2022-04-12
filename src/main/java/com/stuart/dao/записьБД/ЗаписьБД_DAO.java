package com.stuart.dao.записьБД;


import com.stuart.dao.DAO;
import com.stuart.models.entity.ЗаписьБД;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import java.util.List;
import java.util.UUID;

public class ЗаписьБД_DAO implements DAO{
    private final SessionFactory factory;

    public ЗаписьБД_DAO(final SessionFactory factory) {
        this.factory = factory;
    }


    @Override
    public boolean create(ЗаписьБД записьБД) { // запись в БД
        try (final Session session = factory.openSession()) {
            boolean ПроверкаЗаписи = записьБД.ПередЗаписью();
            if (ПроверкаЗаписи==true) {
                session.beginTransaction();
                session.save(записьБД);
                session.getTransaction().commit();
            }
            return true;
        }
        catch(Exception e){
                System.out.println("Заполните все поля!");
                return false;
        }
    }


    public List findByCode(Integer code) {
        try (final Session session = factory.openSession()) {
            Query query = session.createQuery("from ЗаписьКонтрагент where Код = :paramCode");
            query.setParameter("paramCode", code);
            List list = query.getResultList();
            return list;
        }
    }


    @Override
    public void update(UUID записьБД) {
        try (final Session session = factory.openSession()) {
            session.beginTransaction();
            session.update(записьБД);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(ЗаписьБД записьБД) {
        try (final Session session = factory.openSession()) {
            session.beginTransaction();
            session.delete(записьБД);
            session.getTransaction().commit();
        }
    }

}
