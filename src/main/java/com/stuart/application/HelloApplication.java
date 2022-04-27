package com.stuart.application;

import com.stuart.dao.записьБД.DataAccessObject;
import com.stuart.models.entity.документы.закупка.Закупка;
import com.stuart.models.entity.документы.закупка.ЗаписьТЧ_Закупка;
import com.stuart.models.entity.регистры.ЗаписьРегистраВзаиморасчеты;
import com.stuart.models.entity.справочники.ЗаписьКонтрагент;
import com.stuart.models.entity.справочники.ЗаписьНоменклатура;
import lombok.extern.log4j.Log4j2;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Log4j2
public class HelloApplication {

    public static void main(String[] args) {

        try (final Session newSession = DataAccessObject.openSessionBeginTransaction()) {

//            Закупка закупка = new Закупка();
//            закупка.setDate(new Date());
//            закупка.setNumber(1001001);
//            закупка.setPometkaProvedeniya();
//            закупка.setContragent_(ЗаписьКонтрагент.findObjectByValue("code", 101010));
//
//            ЗаписьТЧ_Закупка запись1 = new ЗаписьТЧ_Закупка(ЗаписьНоменклатура
//                    .findObjectByValue("code", 201008), 3D, 30D, закупка);
//            закупка.ДобавитьЗаписьВТЧ(запись1);
//
//            ЗаписьТЧ_Закупка запись2 = new ЗаписьТЧ_Закупка(ЗаписьНоменклатура
//                    .findObjectByValue("code", 201009), 5D, 60D, закупка);
//            закупка.ДобавитьЗаписьВТЧ(запись2);
//
//            ЗаписьТЧ_Закупка запись3 = new ЗаписьТЧ_Закупка(ЗаписьНоменклатура
//                    .findObjectByValue("code", 201007), 4D, 40D, закупка);
//            закупка.ДобавитьЗаписьВТЧ(запись3);
//
//            ЗаписьТЧ_Закупка запись4 = new ЗаписьТЧ_Закупка(ЗаписьНоменклатура
//                    .findObjectByValue("code", 201006), 2D, 50D, закупка);
//            закупка.ДобавитьЗаписьВТЧ(запись4);
////
//            закупка.ЗаписатьДокумент();

            Закупка закупка1 = Закупка.findObjectByValue("number", 1001001); //получаем документ из базы / юзер ищет по какому-то полю докумета
            List <ЗаписьТЧ_Закупка> табчасти = ЗаписьТЧ_Закупка.findObjectsByValue("idDoc", закупка1.getId()); //ищем все записи таб частей, которые принаддлежат этому документу


            ЗаписьТЧ_Закупка запись = ЗаписьТЧ_Закупка.findObjectByValue("id", UUID.fromString("c885c10f-3b5b-4bf4-ae4e-b0600211a411")); //получили строку табчасти конкретного документа
//
////            запись.setPrice(53D); //обновляем записьТЧ - работает
////            запись.setSum();
//
////            табчасти.remove(запись); //удаляем из одну запись ТЧ - работает
//
//            ЗаписьТЧ_Закупка записьТЧ1 = new ЗаписьТЧ_Закупка(ЗаписьНоменклатура
//                    .findObjectByValue("code", 201005), 9D, 18D, закупка1);
//            List <ЗаписьТЧ_Закупка> табчасти = ЗаписьТЧ_Закупка.findObjectsByValue("idDoc", закупка1.getId());
//
//            табчасти.add(записьТЧ1); //добавляем новую запись в табличную часть - работает
////
//            закупка1.setTable_part_purchase_(табчасти); //обновленные табчасти прицепляем документу в базу

            закупка1.Проведение();

            DataAccessObject.commitTransactionCloseSession();
//            DataAccessObject.rollbackTransactionCloseSession();
        }

    }
}
