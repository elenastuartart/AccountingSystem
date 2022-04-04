package com.stuart;


import com.stuart.models.entity.справочники.ЗаписьКонтрагент;
import com.stuart.models.entity.справочники.ЗаписьНоменклатура;
import com.stuart.models.entity.справочники.ЗаписьЭтапыПроизводства;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HelloApplication {

    public static void main(String[] args) {

        Configuration configuration = new Configuration();
        configuration.configure();

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {

            session.beginTransaction();

//            ЗаписьКонтрагент КА2 = ЗаписьКонтрагент.builder()
//                    .Код(10101)
//                    .Наименование("Литейная №2")
//                    .АдресКА("Москва, Нахимовский пр-д, д3")
//                    .ТипКонтрагента("поставщик")
//                    .КонтактноеЛицоКА("liteinaya1@mail.ru")
//                    .ПризнакУдаления(false)
//                    .build();
//
//            session.save(КА2);
//            session.getTransaction().commit();

//            ЗаписьНоменклатура Ном1 = ЗаписьНоменклатура.builder()
//                    .Артикул(55101)
//                    .Подкатегория("Кукла бланк")
//                    .Категория("Товары на реализацию")
//                    .Код(10201)
//                    .Наименование("Молд Карина")
//                    .ПризнакУдаления(false)
//                    .build();
//            session.save(Ном1);
//            session.getTransaction().commit();

            ЗаписьЭтапыПроизводства Запись1 = ЗаписьЭтапыПроизводства.builder()
                    .ОписаниеЭтапа("изготовление парика в красном цвете с укладкой из термоволокна")
                    .Код(10301)
                    .Наименование("Создание парика")
                    .ПризнакУдаления(false)
                    .build();
            session.save(Запись1);
            session.getTransaction().commit();

            //session.close();

        }
    }
}
