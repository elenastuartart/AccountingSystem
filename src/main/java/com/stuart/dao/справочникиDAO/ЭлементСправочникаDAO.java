package com.stuart.dao.справочникиDAO;


import com.stuart.dao.DAO;
import com.stuart.models.entity.справочники.ЭлементСправочника;
import com.sun.istack.NotNull;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ЭлементСправочникаDAO implements DAO {
    private final SessionFactory factory;

    public ЭлементСправочникаDAO(final SessionFactory factory) {
        this.factory = factory;
    }


    @Override
    public void create(ЭлементСправочника ЭлементСправочника) {
        try (final Session session = factory.openSession()) {
            session.beginTransaction();
            session.save(ЭлементСправочника);
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
    public void save(ЭлементСправочника ЭлементСправочника) {

    }

    @Override
    public void update(ЭлементСправочника ЭлементСправочника) {
        try (final Session session = factory.openSession()) {
            session.beginTransaction();
            session.update(ЭлементСправочника);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(ЭлементСправочника ЭлементСправочника) {

    }


}
