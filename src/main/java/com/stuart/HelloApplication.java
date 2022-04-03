package com.stuart;

import com.stuart.models.ЭлементСправочника;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HelloApplication {

    public static void main(String[] args) {

        Configuration configuration = new Configuration();
        //configuration.addAnnotatedClass(ЭлементСправочника.class);
        configuration.configure();

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {

            session.beginTransaction();


            ЭлементСправочника элем1 = ЭлементСправочника.builder()
                    .Код(1)
                    .Наименование("Элемент_1")
                    .ПризнакУдаления(false)
                    .build();

            ЭлементСправочника элем2 = ЭлементСправочника.builder()
                    .Код(2)
                    .Наименование("Элемент_2")
                    .ПризнакУдаления(false)
                    .build();



            session.save(элем1);

            session.save(элем2);

            session.getTransaction().commit();

            //session.close();

        }
    }
}
