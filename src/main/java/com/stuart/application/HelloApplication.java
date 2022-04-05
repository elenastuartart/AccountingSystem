package com.stuart.application;


import com.stuart.dao.справочникиDAO.ЭлементСправочникаDAO;
import com.stuart.models.entity.документы.продажа.ЗаписьБДСписокТоваров;
import com.stuart.models.entity.документы.продажа.Реализация;
import com.stuart.models.entity.справочники.ЗаписьКонтрагент;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Date;


public class HelloApplication {

    public static void main(String[] args) {

        SessionFactory factory = null;

        try {
            factory = new Configuration().configure().buildSessionFactory();
            ЭлементСправочникаDAO ЭлементDAO = new ЭлементСправочникаDAO(factory);

            final ЗаписьКонтрагент КА1 = new ЗаписьКонтрагент();
            КА1.setАдресКА("Реутов");
            КА1.setКод(10103);
            КА1.setКонтактноеЛицоКА("aaa@mail.ru");
            КА1.setНаименование("Литейная №3");
            КА1.setПризнакУдаления(false);
            КА1.setТипКонтрагента("продавец");
            ЭлементDAO.create(КА1);
//
//            final ЗаписьНоменклатура Ном1 = new ЗаписьНоменклатура();
//            Ном1.setКод(20101);
//            Ном1.setНаименование("Молд Карина");
//            Ном1.setПризнакУдаления(false);
//            Ном1.setАртикул(500101);
//            Ном1.setКатегория("Товары на реализацию");
//            Ном1.setПодкатегория("Кукла с росписью");
//            Ном1.setПроизводитель(КА1);
//            ЭлементDAO.create(Ном1);
//            final ЗаписьКонтрагент result = (ЗаписьКонтрагент) ЭлементСпр.read(10100);
//            System.out.println("created : " + result);
//            System.out.println();

            //System.out.println(КА1.toString());

            Реализация док1 = new Реализация( new Date(), 10101, КА1);

            ЗаписьБДСписокТоваров запись1 = new ЗаписьБДСписокТоваров("Кукла бланк", 1, 200);
            ЗаписьБДСписокТоваров запись2 = new ЗаписьБДСписокТоваров( "парик розовый", 2, 50);
            ЗаписьБДСписокТоваров запись3 = new ЗаписьБДСписокТоваров( "обувь базовая", 3, 30);

            док1.ДобавитьЗаписьВТабличнуюЧасть(запись1);
            док1.ДобавитьЗаписьВТабличнуюЧасть(запись2);
            док1.ДобавитьЗаписьВТабличнуюЧасть(запись3);
            док1.ПосчитатьИтоговуюСумму();

            System.out.println(док1.toString());
            док1.toStringTable();

        }
        finally {
            if (factory != null) {
                factory.close();
            }
        }
    }
}
