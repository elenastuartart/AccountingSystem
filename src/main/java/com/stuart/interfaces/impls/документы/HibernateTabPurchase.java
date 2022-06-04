package com.stuart.interfaces.impls.документы;

import com.stuart.dao.DataAccessObject;
import com.stuart.interfaces.ISpravochnik;
import com.stuart.models.entity.документы.закупка.Закупка;
import com.stuart.models.entity.документы.закупка.ЗаписьТЧ_Закупка;
import com.stuart.models.entity.справочники.ЗаписьКонтрагент;
import com.stuart.objectsFX.ObjectFX;
import com.stuart.objectsFX.документы.ДокументЗакупкаFX;
import com.stuart.objectsFX.справочники.ЗаписьКонтрагентFX;
import com.stuart.objectsFX.справочники.ЗаписьНоменклатураFX;
import com.stuart.objectsFX.документы.ТабЧастьЗакупкаFX;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.SQLException;

public class HibernateTabPurchase extends DataAccessObject implements ISpravochnik {

    private ObservableList<ТабЧастьЗакупкаFX> tabPartPurchaseList = FXCollections.observableArrayList();

    public ObservableList<ТабЧастьЗакупкаFX> getTabPartPurchaseList() {
        return tabPartPurchaseList;
    }

    @Override
    public ObservableList<ТабЧастьЗакупкаFX> findAll(ObjectFX objectFX ) {
        ДокументЗакупкаFX документЗакупкаFX = (ДокументЗакупкаFX) objectFX;
        Session newSession = DataAccessObject.get_session();
        Query<ЗаписьТЧ_Закупка> query = newSession.createQuery("select tch from ЗаписьТЧ_Закупка tch where tch.idDoc =: param");
        query.setParameter("param", документЗакупкаFX.getId());/////////////передать айди документа, которому принадлежит таб часть
        var resultList = query.getResultList();
        this.tabPartPurchaseList.clear();
        for (int i = 0; i < resultList.size(); i++) {
            ЗаписьТЧ_Закупка res = resultList.get(i);
            ТабЧастьЗакупкаFX табЧастьЗакупкаFX = new ТабЧастьЗакупкаFX();
            табЧастьЗакупкаFX.setNumberStr(res.getLineNumber());
            табЧастьЗакупкаFX.setAmount(res.getAmount());
            табЧастьЗакупкаFX.setPrice(res.getPrice());
            табЧастьЗакупкаFX.setSum(res.getSum());
            табЧастьЗакупкаFX.setId(res.getId());
            табЧастьЗакупкаFX.setNomenclatureFX_(табЧастьЗакупкаFX.getNomenclatureFX_(res));
            табЧастьЗакупкаFX.setIdDocPurchaseFX_(res.getIdDoc());

            this.tabPartPurchaseList.add(табЧастьЗакупкаFX);
        }
        newSession.close();
        return this.tabPartPurchaseList;
    }

//    public ObservableList<ТабЧастьЗакупкаFX> findAll(ДокументЗакупкаFX документЗакупкаFX) throws SQLException {
////        Session newSession = DataAccessObject.get_session();
////        Query<ЗаписьТЧ_Закупка> query = newSession.createQuery("select tch from ЗаписьТЧ_Закупка tch where tch.idDoc =: param");
////        query.setParameter("param", документЗакупкаFX.getId());/////////////передать айди документа, которому принадлежит таб часть
////        var resultList = query.getResultList();
////        for (int i = 0; i < resultList.size(); i++) {
////            ЗаписьТЧ_Закупка res = resultList.get(i);
////            ТабЧастьЗакупкаFX табЧастьЗакупкаFX = new ТабЧастьЗакупкаFX();
////            табЧастьЗакупкаFX.setNumberStr(res.getLineNumber());
////            табЧастьЗакупкаFX.setAmount(res.getAmount());
////            табЧастьЗакупкаFX.setPrice(res.getPrice());
////            табЧастьЗакупкаFX.setSum(res.getSum());
////            табЧастьЗакупкаFX.setId(res.getId());
////
////                ЗаписьНоменклатураFX записьНоменклатураFX = табЧастьЗакупкаFX.getNomenclatureFX_();
////                записьНоменклатураFX.setId(res.getNomenclature_().getId());
////                записьНоменклатураFX.setName(res.getNomenclature_().getName());
////                записьНоменклатураFX.setArticleNumber(res.getNomenclature_().getArticle_number());
////                записьНоменклатураFX.setCode(res.getNomenclature_().getCode());
////                записьНоменклатураFX.setCategory(res.getNomenclature_().getCategory());
////                записьНоменклатураFX.setSubcategory(res.getNomenclature_().getSubcategory());
////
////                    ЗаписьКонтрагентFX записьКонтрагентFX = записьНоменклатураFX.getContragentFX_();
////                    записьКонтрагентFX.setCode(res.getNomenclature_().getContragent_().getCode());
////                    записьКонтрагентFX.setId(res.getNomenclature_().getContragent_().getId());
////                    записьКонтрагентFX.setName(res.getNomenclature_().getContragent_().getName());
////                    записьКонтрагентFX.setType_KA(res.getNomenclature_().getContragent_().getType_KA());
////                    записьКонтрагентFX.setAddress(res.getNomenclature_().getContragent_().getAddress());
////                    записьКонтрагентFX.setContact_person(res.getNomenclature_().getContragent_().getContact_person());
////                    записьКонтрагентFX.setЗаписьКонтрагент_(res.getNomenclature_().getContragent_());
////
////                записьНоменклатураFX.setContragentFX_(записьКонтрагентFX);
////                записьНоменклатураFX.setContragent(res.getNomenclature_().getContragent_());
////
////            табЧастьЗакупкаFX.setNomenclatureFX_(записьНоменклатураFX);
////            табЧастьЗакупкаFX.setNomenclature(res.getNomenclature_());
////            табЧастьЗакупкаFX.setIdDocPurchaseFX_(res.getIdDoc());
////
////            this.tabPartPurchaseList.add(табЧастьЗакупкаFX);
////        }
////        newSession.close();
//        return this.tabPartPurchaseList;
//    }

    @Override
    public boolean add(ObjectFX objectFX) {
        try (final Session newSession = DataAccessObject.openSessionBeginTransaction()) {
            ТабЧастьЗакупкаFX табЧастьЗакупкаFX = (ТабЧастьЗакупкаFX) objectFX;
            ЗаписьТЧ_Закупка записьТЧ_закупка = new ЗаписьТЧ_Закупка();
            записьТЧ_закупка.setId(табЧастьЗакупкаFX.getId());

        }
            DataAccessObject.commitTransactionCloseSession();
//
//            if (result) {
//                записьКонтрагентFX.setCode(записьКонтрагент.getCode());
//                записьКонтрагентFX.setId(записьКонтрагент.getId());
//                tabPartPurchaseList.add(записьКонтрагентFX);
//                return true;
//            }
//            return false;
//        }
//        catch (Exception ex) {
//            System.out.println(ex.getMessage());
//            return false;
//        }
        return false;
    }

    @Override
    public boolean update(ObjectFX objectFX) {
        try (final Session newSession = DataAccessObject.openSessionBeginTransaction()) {
            ЗаписьКонтрагентFX записьКонтрагентFX = (ЗаписьКонтрагентFX) objectFX;
            ЗаписьКонтрагент записьКонтрагент = ЗаписьКонтрагент.findObjectByValue("id", записьКонтрагентFX.getId());
            записьКонтрагент.setCode(записьКонтрагентFX.getCode());
            записьКонтрагент.setName(записьКонтрагентFX.getName());
            записьКонтрагент.setType_KA(записьКонтрагентFX.getType_KA());
            записьКонтрагент.setAddress(записьКонтрагентFX.getAddress());
            записьКонтрагент.setContact_person(записьКонтрагентFX.getContact_person());

            boolean result = DataAccessObject.save(записьКонтрагент);
            DataAccessObject.commitTransactionCloseSession();
            if(result) {
                return true;
            }
            return false;
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public ObservableList<?> findText(String text) {
        return null;
    }

    @Override
    public ObservableList<?> findInt(Integer value) {
        return null;
    }
}
