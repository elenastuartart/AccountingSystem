package com.stuart.application;

import com.stuart.controllers.MainWindowController;
import com.stuart.controllers.документЗакупка.TableDocPurchaseController;
import com.stuart.controllers.документПроизводство.TableDocManufactureController;
import com.stuart.controllers.документРеализация.TableDocSaleController;
import com.stuart.controllers.контрагенты.TableKAController;
import com.stuart.controllers.номенклатура.TableNomController;
import com.stuart.dao.DataAccessObject;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;

import java.io.IOException;

@Log4j2
public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader();
//        fxmlLoader.setLocation(getClass().getResource("/fxml/tableKA.fxml"));
//        Parent fxmlMain = fxmlLoader.load();
//        TableKAController mainController = fxmlLoader.getController();
//        mainController.setMainStage(primaryStage);
//
//        primaryStage.setTitle("Справочник Контрагенты");
//        primaryStage.setMinHeight(800);
//        primaryStage.setMinWidth(600);
//        primaryStage.setScene(new Scene(fxmlMain, 300, 275));
//        primaryStage.show();

//        FXMLLoader fxmlLoader = new FXMLLoader();
//        fxmlLoader.setLocation(getClass().getResource("/fxml/tablePrStages.fxml"));
//        Parent fxmlMain = fxmlLoader.load();
//        TablePrStagesController mainController = fxmlLoader.getController();
//        mainController.setMainStage(primaryStage);
//
//        primaryStage.setTitle("Справочник Этапы производства");
//        primaryStage.setMinHeight(800);
//        primaryStage.setMinWidth(600);
//        primaryStage.setScene(new Scene(fxmlMain, 300, 275));
//        primaryStage.show();

//        FXMLLoader fxmlLoader = new FXMLLoader();
//        fxmlLoader.setLocation(getClass().getResource("/fxml/tableNom.fxml"));
//        Parent fxmlMain = fxmlLoader.load();
//        TableNomController mainController = fxmlLoader.getController();
//        mainController.setMainStage(primaryStage);
//
//        primaryStage.setTitle("Справочник Номенклатура");
//        primaryStage.setMinHeight(800);
//        primaryStage.setMinWidth(600);
//        primaryStage.setScene(new Scene(fxmlMain, 300, 275));
//        primaryStage.show();
//
//        FXMLLoader fxmlLoader = new FXMLLoader();
//        fxmlLoader.setLocation(getClass().getResource("/fxml/tableDocPurchase.fxml"));
//        Parent fxmlMain = fxmlLoader.load();
//        TableDocPurchaseController mainController = fxmlLoader.getController();
//        mainController.setMainStage(primaryStage);
//
//        primaryStage.setTitle("Документы по закупкам");
//        primaryStage.setMinHeight(800);
//        primaryStage.setMinWidth(600);
//        primaryStage.setScene(new Scene(fxmlMain, 300, 275));
//        primaryStage.show();

//        FXMLLoader fxmlLoader = new FXMLLoader();
//        fxmlLoader.setLocation(getClass().getResource("/fxml/tableDocManufacture.fxml"));
//        Parent fxmlMain = fxmlLoader.load();
//        TableDocManufactureController mainController = fxmlLoader.getController();
//        mainController.setMainStage(primaryStage);
//
//        primaryStage.setTitle("Документы по производству");
//        primaryStage.setMinHeight(800);
//        primaryStage.setMinWidth(600);
//        primaryStage.setScene(new Scene(fxmlMain, 300, 275));
//        primaryStage.show();

//        FXMLLoader fxmlLoader = new FXMLLoader();
//        fxmlLoader.setLocation(getClass().getResource("/fxml/tableDocSale.fxml"));
//        Parent fxmlMain = fxmlLoader.load();
//        TableDocSaleController mainController = fxmlLoader.getController();
//        mainController.setMainStage(primaryStage);
//
//        primaryStage.setTitle("Документы по продажам");
//        primaryStage.setMinHeight(800);
//        primaryStage.setMinWidth(600);
//        primaryStage.setScene(new Scene(fxmlMain, 300, 275));
//        primaryStage.show();

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/fxml/mainWindow.fxml"));
        Parent fxmlMain = fxmlLoader.load();
        MainWindowController mainController = fxmlLoader.getController();
        mainController.setMainStage(primaryStage);

        primaryStage.setTitle("Учет хозяйственной деятельности художественной мастерской");
        primaryStage.setMinHeight(540);
        primaryStage.setMinWidth(768);
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(fxmlMain, 768, 540));
        primaryStage.show();
    }



    public static void main(String[] args) {

        launch();

        try (final Session newSession = DataAccessObject.openSessionBeginTransaction()) {

            // ЗАПРОС ОСТАТКИ
//            select
//            Nom.name AS Nomenclature, // Номенклатура
//            SUM(Registr.amount) AS Amount, // Количество (остаток)
//            SUM(Registr.sum) AS Sum // Стоимость остатков
//            from
//            register_products_in_stock AS Registr
//            JOIN nomenclature AS Nom
//            ON Registr.idNom = Nom.id
//            GROUP BY Nom.name

            // ЗАПРОС СЕБЕСТОИМОСТЬ
//            select
//            Nom.name AS Nomenclature, // Номенклатура
//            SUM(Registr.amount) AS Amount, // Количество (реализовано)
//            SUM(Registr.profit) AS Profit, // Сумма (прибыль)
//            AVG(Registr.profitByUnit) AS profitByUnit, // Прибыль за единицу товара
//            SUM(Registr.sumCostprice) AS sumCostprice // Себестоимость
//            from
//            register_costprice AS Registr
//            JOIN nomenclature AS Nom
//            ON Registr.idNom = Nom.id
//            GROUP BY Nom.name

            // ИСТОРИЯ ДВИЖЕНИЯ ДенСсретв
//            select
//            Registr.date AS date, // Дата
//            contragent.name AS Contragent, // Контаргент
//            Registr.sum AS Oborot // Оборот
//                    from
//            register_calculation AS Registr
//            JOIN contragent AS contragent
//            ON Registr.contragent_id = contragent.id














//            Закупка закупка = Закупка.findObjectByValue("number", 69038659);
//            Реализация реализация = Реализация.findObjectByValue("number", 18116225);
//////            закупка.Проведение();
//            реализация.Проведение();
//            Производство производство = Производство.findObjectByValue("number", 66832553);
//            производство.Проведение();
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


////      СОЗДАНИЕ ДОКУМЕНТА ЗАКУПКА + ЗАПОЛНЕНИЕ ТАБ ЧАСТЕЙ + ПРОВЕДЕНИЕ
//            Закупка закупка = new Закупка();
//            закупка.setDate(new Date());
//            закупка.setNumber();
//            закупка.setPometkaProvedeniya();
//            закупка.setContragent_(ЗаписьКонтрагент.findObjectByValue("code", 72796914));
////
//            ЗаписьТЧ_Закупка запись1 = закупка.ДобавитьЗаписьВТЧ();
//            запись1.setNomenclature_(ЗаписьНоменклатура
//                    .findObjectByValue("code", 48255858));
//            запись1.setAmount(15D);
//            запись1.setPrice(4000D);
//            запись1.setSum();
//
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


////          СОЗДАНИЕ ДОКУМЕНТА ПРОИЗВОДСТВО + ЗАПОЛНЕНИЕ ТАБ ЧАСТЕЙ + ПРОВЕДЕНИЕ
//            Производство производство = new Производство();
//            производство.setNumber();
//            производство.setPometkaProvedeniya();
//            производство.setDate(new Date());
////
//            ЗаписьТЧРасходМатериалов расход1 = производство.ДобавитьЗаписьВ_ТЧ_Расход();
//            расход1.setStage_(ЗаписьЭтапыПроизводства.findObjectByValue("code", 64239158));
//            расход1.setAmount(10D);
//            расход1.setNomenclature_(ЗаписьНоменклатура.findObjectByValue("code", 48255858));
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
//            произведено1.setAmount(10D);
//            произведено1.setNomenclature_(ЗаписьНоменклатура.findObjectByValue("code", 29443114));
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


////      СОЗДАНИЕ ДОКУМЕНТА РЕАЛИЗАЦИЯ + ЗАПОЛНЕНИЕ ТАБ ЧАСТЕЙ + ПРОВЕДЕНИЕ
//            Реализация реализация = new Реализация();
//            реализация.setDate(new Date());
//            реализация.setNumber();
//            реализация.setPometkaProvedeniya();
//            реализация.setContragent_(ЗаписьКонтрагент.findObjectByValue("code", 66432253));
//////
//            ЗаписьТЧСписокТоваров запись1 = реализация.ДобавитьЗаписьВТЧ();
//            запись1.setAmount(8D);
//            запись1.setPrice(60000D);
//            запись1.setSum();
//            запись1.setNomenclature_(ЗаписьНоменклатура
//                    .findObjectByValue("code", 29443114));
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
//            реализация.Проведение();
//

            DataAccessObject.commitTransactionCloseSession();

        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
