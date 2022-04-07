package com.stuart.application;


import com.stuart.dao.записьВБД.ЗаписьБД_DAO;
import com.stuart.models.entity.документы.продажа.ЗаписьТЧСписокТоваров;
import com.stuart.models.entity.документы.продажа.Реализация;
import com.stuart.models.entity.регистры.ЗаписьРегистраВзаиморасчеты;
import com.stuart.models.entity.справочники.ЗаписьКонтрагент;
import com.stuart.models.entity.справочники.ЗаписьНоменклатура;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.GeneratedValue;
import java.util.Date;
import java.util.Random;


public class HelloApplication {

    public static void main(String[] args) {

        SessionFactory factory = null;

        try {
            factory = new Configuration().configure().buildSessionFactory();
            ЗаписьБД_DAO ЭлементDAO = new ЗаписьБД_DAO(factory);

            final ЗаписьКонтрагент КА1 = new ЗаписьКонтрагент();
            КА1.setКод(10101);
            КА1.setАдресКА("Реутов");
            КА1.setКонтактноеЛицоКА("aaa@mail.ru");
            КА1.setНаименование("Литейная №5");
            КА1.setТипКонтрагента("продавец");
            //ЭлементDAO.create(КА1);

            final ЗаписьНоменклатура Ном1 = new ЗаписьНоменклатура();
            Ном1.setНаименование("Молд Карина");
            Ном1.setКод(10200);
            Ном1.setАртикул(500101);
            Ном1.setКатегория("Товары на реализацию");
            Ном1.setПодкатегория("Кукла с росписью");
            Ном1.setПроизводитель(КА1);
            //ЭлементDAO.create(Ном1);


            Реализация док1 = new Реализация( new Date(), 1245545, КА1);

            ЗаписьТЧСписокТоваров запись1 = new ЗаписьТЧСписокТоваров(Ном1, 2, 200);
            ЗаписьТЧСписокТоваров запись2 = new ЗаписьТЧСписокТоваров( Ном1, 4, 50);
            ЗаписьТЧСписокТоваров запись3 = new ЗаписьТЧСписокТоваров( Ном1, 3, 30);
//
            док1.ДобавитьЗаписьВТабличнуюЧасть(запись1);
            док1.ДобавитьЗаписьВТабличнуюЧасть(запись2);
            док1.ДобавитьЗаписьВТабличнуюЧасть(запись3);
            док1.ПосчитатьИтоговуюСумму();

            System.out.println(док1.toString());
            док1.toStringTable();

            ЗаписьРегистраВзаиморасчеты взаиморасчеты1 = new ЗаписьРегистраВзаиморасчеты(док1, "приход");
            System.out.println(взаиморасчеты1.toString());

            //ЭлементDAO.create(док1);
            boolean проверка = взаиморасчеты1.ПередЗаписью();
            System.out.println(проверка);
        }
        finally {
            if (factory != null) {
                factory.close();
            }
        }
    }
}
