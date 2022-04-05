package com.stuart.dao.справочникиDAO;


import com.stuart.dao.DAO;
import com.stuart.models.entity.ЗаписьБД;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ЗаписьБД_DAO implements DAO {
    private final SessionFactory factory;

    public ЗаписьБД_DAO(final SessionFactory factory) {
        this.factory = factory;
    }


    @Override
    public void create(ЗаписьБД записьБД) {
        try (final Session session = factory.openSession()) {
            session.beginTransaction();
            session.save(записьБД);
            session.getTransaction().commit();
        }
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
