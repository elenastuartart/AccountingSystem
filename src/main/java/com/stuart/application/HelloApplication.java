package com.stuart.application;

import com.stuart.dao.записьБД.DataAccessObject;
import com.stuart.models.entity.ЗаписьБД;
import com.stuart.models.entity.документы.закупка.Закупка;
import com.stuart.models.entity.документы.закупка.ЗаписьТЧ_Закупка;
import com.stuart.models.entity.документы.продажа.ЗаписьТЧСписокТоваров;
import com.stuart.models.entity.документы.продажа.Реализация;
import com.stuart.models.entity.документы.производство.ЗаписьТЧПроизведеноПродукции;
import com.stuart.models.entity.документы.производство.ЗаписьТЧРасходМатериалов;
import com.stuart.models.entity.документы.производство.Производство;
import com.stuart.models.entity.справочники.ЗаписьКонтрагент;
import com.stuart.models.entity.справочники.ЗаписьНоменклатура;
import com.stuart.models.entity.справочники.ЗаписьЭтапыПроизводства;
import lombok.extern.log4j.Log4j2;
import org.hibernate.*;
//import javax.persistence.Query;
import org.hibernate.query.Query;

//import javax.persistence.Query;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

@Log4j2
public class HelloApplication {

    public static void main(String[] args) {

        try (final Session newSession = DataAccessObject.openSessionBeginTransaction()) {

//            Закупка закупка = Закупка.findObjectByValue("number", 69038659);
//            Реализация реализация = Реализация.findObjectByValue("number", 20220101);
//            закупка.Проведение();
//            реализация.Проведение();

//            Query<Double> query = newSession.createQuery("select " +
//                    "sum (zrv.sum) from ЗаписьРегистраВзаиморасчеты zrv ");
//            Double sum = query.uniqueResult();
//            log.info(sum);

//            ЗаписьНоменклатура запись = ЗаписьНоменклатура.findObjectByValue("code", 75613925);
//            Query<Double> query = newSession.createQuery("select " + "sum (zrts .amount ) " +
//                    "from ЗаписьРегистраТоварыНаСкладах zrts "
//                    + "where zrts.idNom =: param");
//            query.setParameter("param", запись.getId());
//            Double amountNom = query.uniqueResult();
//            log.info(amountNom);

//              СОЗДАНИЕ СПРАВОЧНИКА КОНТРАГЕНТ
//            ЗаписьКонтрагент контрагент1 = new ЗаписьКонтрагент();
//            контрагент1.setName("My company BJD DOLL HOUSE");
//            контрагент1.setCode();
//            контрагент1.setType_KA(" ");
//            контрагент1.setAddress("Москва, пр. Вернадского, д 14");
//            контрагент1.setContact_person("bjd_doll_house@mail.ru");
//            контрагент1.save();
//
//            ЗаписьКонтрагент поискКА = ЗаписьКонтрагент.findObjectByValue("name", "Куклодом");
//            System.out.println(поискКА.toString());
//            поискКА.delete();

//            СОЗДАНИЕ СПРАВОЧНИКА НОМЕНКЛАТУРА
//            ЗаписьНоменклатура номенклатура1 = new ЗаписьНоменклатура();
//            номенклатура1.setCode();
//            номенклатура1.setArticle_number();
//            номенклатура1.setName("ПЕРВОНАЧАЛЬНЫЙ ПРИХОД Д/С");
//            номенклатура1.setCategory(" ");
//            номенклатура1.setSubcategory(" ");
//            номенклатура1.setContragent_(ЗаписьКонтрагент.findObjectByValue("code", 37236088));
//            номенклатура1.save();
//            Integer code = 201001;
//            for (int i = 0; i < 11; i++) {
//                ЗаписьНоменклатура поискНом = ЗаписьНоменклатура.findObjectByValue("code", code + i);
//                поискНом.setCode();
//                поискНом.setArticle_number();
////            }
//            ЗаписьНоменклатура поискНом = ЗаписьНоменклатура.findObjectByValue("name", "Батист синий тонкий");
//            поискНом.delete();

//             ЗАПИСЬ СПРАВОЧНИКА ЭТАПЫ ПРОИЗВОДСТВА
//            ЗаписьЭтапыПроизводства записьЭтапыПроизводства1 = new ЗаписьЭтапыПроизводства();
//            записьЭтапыПроизводства1.setCode();
//            записьЭтапыПроизводства1.setName("блалалал");
//            записьЭтапыПроизводства1.setDescription_stage(" ");
//            записьЭтапыПроизводства1.save();

//            Integer code = 300101;
//            for (int i = 0; i < 8; i++) {
//                ЗаписьЭтапыПроизводства поискЭтапы = ЗаписьЭтапыПроизводства.findObjectByValue("code", code + i);
//                поискЭтапы.setCode();
//            }
//            ЗаписьЭтапыПроизводства поискЭтапы = ЗаписьЭтапыПроизводства.findObjectByValue("code", 27586997);
//            поискЭтапы.delete();


//      СОЗДАНИЕ ДОКУМЕНТА ЗАКУПКА + ЗАПОЛНЕНИЕ ТАБ ЧАСТЕЙ + ПРОВЕДЕНИЕ
//            Закупка закупка = new Закупка();
//            закупка.setDate(new Date());
//            закупка.setNumber();
//            закупка.setPometkaProvedeniya();
//            закупка.setContragent_(ЗаписьКонтрагент.findObjectByValue("code", 72796914));
////
//            ЗаписьТЧ_Закупка запись1 = закупка.ДобавитьЗаписьВТЧ();
//            запись1.setNomenclature_(ЗаписьНоменклатура
//                    .findObjectByValue("code", 75613925));
//            запись1.setAmount(5D);
//            запись1.setPrice(3.550D);
//            запись1.setSum();

//            закупка.Проведение();

//            ЗаписьТЧ_Закупка запись2 = закупка.ДобавитьЗаписьВТЧ();
//            запись2.setNomenclature_(ЗаписьНоменклатура
//                    .findObjectByValue("code", 50728878));
//            запись2.setAmount(10D);
//            запись2.setPrice(450D);
//            запись2.setSum();
//
//            ЗаписьТЧ_Закупка запись3 = закупка.ДобавитьЗаписьВТЧ();
//            запись3.setNomenclature_(ЗаписьНоменклатура
//                    .findObjectByValue("code", 31433907));
//            запись3.setAmount(4D);
//            запись3.setPrice(800D);
//            запись3.setSum();
//
//            ЗаписьТЧ_Закупка запись4 = закупка.ДобавитьЗаписьВТЧ();
//            запись4.setNomenclature_(ЗаписьНоменклатура
//                    .findObjectByValue("code", 81905731));
//            запись4.setAmount(20D);
//            запись4.setPrice(95D);
//            запись4.setSum();
//
//            ЗаписьТЧ_Закупка запись5 = закупка.ДобавитьЗаписьВТЧ();
//            запись5.setNomenclature_(ЗаписьНоменклатура
//                    .findObjectByValue("code", 86694250));
//            запись5.setAmount(10D);
//            запись5.setPrice(350D);
//            запись5.setSum();
//            закупка.Проведение();

//            УДАЛЕНИЕ ЗАПИСИ ИЗ ТАБЧАСТИ ДОКУМЕНТА + ПРОВЕДЕНИЕ
//            Закупка поискЗакупка = Закупка.findObjectByValue("number", 57962313);
//            var табчасти = ЗаписьТЧ_Закупка.findObjectsByValue("idDoc", поискЗакупка.getId());
//            ЗаписьТЧ_Закупка запись1 = ЗаписьТЧ_Закупка.findObjectByValue("price", 830D);
//            табчасти.remove(запись1);
//            поискЗакупка.setTable_part_purchase_(табчасти);
//            поискЗакупка.Проведение();

            //ДОБАВЛЕНИЕ ЗАПИСИ В ТАБЧАСТЬ ДОКУМЕНТА + ПРОВЕДЕНИЕ
//            Закупка поискЗакупка = Закупка.findObjectByValue("number", 57962313);
//            var табчасти = ЗаписьТЧ_Закупка.findObjectsByValue("idDoc", поискЗакупка.getId());
//
//            ЗаписьТЧ_Закупка записьТЧ_закупка = поискЗакупка.ДобавитьЗаписьВТЧ();
//            записьТЧ_закупка.setAmount(3D);
//            записьТЧ_закупка.setPrice(830D);
//            записьТЧ_закупка.setSum();
//            записьТЧ_закупка.setNomenclature_(ЗаписьНоменклатура
//                    .findObjectByValue("code", 97950039));
//            табчасти.add(записьТЧ_закупка);
//            поискЗакупка.setTable_part_purchase_(табчасти);
//            поискЗакупка.Проведение();

//          ОТМЕНА ПРОВЕДЕНИЯ + РЕДАКТИРОВАНИЕ ПОЛЕЙ ДОКУМЕНТА
//            Закупка поискЗакупка = Закупка.findObjectByValue("number", 1001001);
//            поискЗакупка.setNumber();
//            поискЗакупка.ОтменаПроведения();


//          СОЗДАНИЕ ДОКУМЕНТА ПРОИЗВОДСТВО + ЗАПОЛНЕНИЕ ТАБ ЧАСТЕЙ + ПРОВЕДЕНИЕ
//            Производство производство = new Производство();
//            производство.setNumber();
//            производство.setPometkaProvedeniya();
//            производство.setDate(new Date());
////
//            ЗаписьТЧРасходМатериалов расход1 = производство.ДобавитьЗаписьВ_ТЧ_Расход();
//            расход1.setStage_(ЗаписьЭтапыПроизводства.findObjectByValue("code", 64239158));
//            расход1.setAmount(4D);
//            расход1.setNomenclature_(ЗаписьНоменклатура.findObjectByValue("code", 75613925));
//
//            ЗаписьТЧРасходМатериалов расход2 = производство.ДобавитьЗаписьВ_ТЧ_Расход();
//            расход2.setStage_(ЗаписьЭтапыПроизводства.findObjectByValue("code", 70581890));
//            расход2.setAmount(2.1D);
//            расход2.setNomenclature_(ЗаписьНоменклатура.findObjectByValue("code", 50728878));
//
//            ЗаписьТЧРасходМатериалов расход3 = производство.ДобавитьЗаписьВ_ТЧ_Расход();
//            расход3.setStage_(ЗаписьЭтапыПроизводства.findObjectByValue("code", 44320909));
//            расход3.setAmount(0.5D);
//            расход3.setNomenclature_(ЗаписьНоменклатура.findObjectByValue("code", 31433907));
//
//            ЗаписьТЧРасходМатериалов расход4 = производство.ДобавитьЗаписьВ_ТЧ_Расход();
//            расход4.setStage_(ЗаписьЭтапыПроизводства.findObjectByValue("code", 44320909));
//            расход4.setAmount(8D);
//            расход4.setNomenclature_(ЗаписьНоменклатура.findObjectByValue("code", 81905731));
//
//            ЗаписьТЧРасходМатериалов расход5 = производство.ДобавитьЗаписьВ_ТЧ_Расход();
//            расход5.setStage_(ЗаписьЭтапыПроизводства.findObjectByValue("code", 34706467));
//            расход5.setAmount(6D);
//            расход5.setNomenclature_(ЗаписьНоменклатура.findObjectByValue("code", 86694250));
//
//            ЗаписьТЧПроизведеноПродукции произведено1 = производство.ДобавитьЗаписьВ_ТЧ_Произведено();
//            произведено1.setAmount(4D);
//            произведено1.setNomenclature_(ЗаписьНоменклатура.findObjectByValue("code", 14024916));
//////
////            ЗаписьТЧПроизведеноПродукции произведено2 = производство.ДобавитьЗаписьВ_ТЧ_Произведено();
////            произведено2.setAmount(4D);
////            произведено2.setNomenclature_(ЗаписьНоменклатура.findObjectByValue("code", 24882154));
////
////            ЗаписьТЧПроизведеноПродукции произведено3 = производство.ДобавитьЗаписьВ_ТЧ_Произведено();
////            произведено3.setAmount(6D);
////            произведено3.setNomenclature_(ЗаписьНоменклатура.findObjectByValue("code", 44577570));
////
//            производство.Проведение();


//      СОЗДАНИЕ ДОКУМЕНТА РЕАЛИЗАЦИЯ + ЗАПОЛНЕНИЕ ТАБ ЧАСТЕЙ + ПРОВЕДЕНИЕ
            Реализация реализация = new Реализация();
            реализация.setDate(new Date());
            реализация.setNumber();
            реализация.setPometkaProvedeniya();
            реализация.setContragent_(ЗаписьКонтрагент.findObjectByValue("code", 66432253));
//
            ЗаписьТЧСписокТоваров запись1 = реализация.ДобавитьЗаписьВТЧ();
            запись1.setAmount(3D);
            запись1.setPrice(60000D);
            запись1.setSum();
            запись1.setNomenclature_(ЗаписьНоменклатура
                    .findObjectByValue("code", 14024916));
//
//            ЗаписьТЧСписокТоваров запись2 = реализация.ДобавитьЗаписьВТЧ();
//            запись2.setAmount(1D);
//            запись2.setPrice(5000D);
//            запись2.setSum();
//            запись2.setNomenclature_(ЗаписьНоменклатура
//                    .findObjectByValue("code", 24882154));
//
//            ЗаписьТЧСписокТоваров запись3 = реализация.ДобавитьЗаписьВТЧ();
//            запись3.setAmount(2D);
//            запись3.setPrice(3800D);
//            запись3.setSum();
//            запись3.setNomenclature_(ЗаписьНоменклатура
//                    .findObjectByValue("code", 44577570));
//
            реализация.Проведение();
//

            DataAccessObject.commitTransactionCloseSession();

        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
