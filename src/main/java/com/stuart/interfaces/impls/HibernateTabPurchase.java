package com.stuart.interfaces.impls;

import com.stuart.dao.DataAccessObject;
import com.stuart.interfaces.ISpravochnik;
import com.stuart.models.entity.документы.закупка.ЗаписьТЧ_Закупка;
import com.stuart.models.entity.справочники.ЗаписьКонтрагент;
import com.stuart.objectsFX.ObjectFX;
import com.stuart.objectsFX.ЗаписьКонтрагентFX;
import com.stuart.objectsFX.ЗаписьНоменклатураFX;
import com.stuart.objectsFX.ТабЧастьЗакупкаFX;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class HibernateTabPurchase extends DataAccessObject implements ISpravochnik {

    private ObservableList<ТабЧастьЗакупкаFX> tabPartPurchaseList = FXCollections.observableArrayList();

    public ObservableList<ТабЧастьЗакупкаFX> getTabPartPurchaseList() {
        return tabPartPurchaseList;
    }

    @Override
    public ObservableList<ТабЧастьЗакупкаFX> findAll() throws SQLException {
        Session newSession = DataAccessObject.get_session();
        Query<ЗаписьТЧ_Закупка> query = newSession.createQuery("select tch from ЗаписьТЧ_Закупка tch where tch.idDoc=:param");
        query.setParameter("param", 123);/////////////передать айди документа, которому принадлежит таб часть
        var resultList = query.getResultList();
        for (int i = 0; i < resultList.size(); i++) {
            ЗаписьТЧ_Закупка res = resultList.get(i);
            ТабЧастьЗакупкаFX табЧастьЗакупкаFX = new ТабЧастьЗакупкаFX();
            табЧастьЗакупкаFX.setNumberStr(res.getLineNumber());
            табЧастьЗакупкаFX.setAmount(res.getAmount());
            табЧастьЗакупкаFX.setPrice(res.getPrice());
            табЧастьЗакупкаFX.setSum(res.getSum());
            табЧастьЗакупкаFX.setId(res.getId());

                ЗаписьНоменклатураFX записьНоменклатураFX = табЧастьЗакупкаFX.getNomenclature_();
                записьНоменклатураFX.setId(res.getNomenclature_().getId());
                записьНоменклатураFX.setName(res.getNomenclature_().getName());
                записьНоменклатураFX.setArticleNumber(res.getNomenclature_().getArticle_number());
                записьНоменклатураFX.setCode(res.getNomenclature_().getCode());
                записьНоменклатураFX.setCategory(res.getNomenclature_().getCategory());
                записьНоменклатураFX.setSubcategory(res.getNomenclature_().getSubcategory());

                    ЗаписьКонтрагентFX записьКонтрагентFX = записьНоменклатураFX.getContragent_();
                    записьКонтрагентFX.setCode(res.getNomenclature_().getContragent_().getCode());
                    записьКонтрагентFX.setId(res.getNomenclature_().getContragent_().getId());
                    записьКонтрагентFX.setName(res.getNomenclature_().getContragent_().getName());
                    записьКонтрагентFX.setType_KA(res.getNomenclature_().getContragent_().getType_KA());
                    записьКонтрагентFX.setAddress(res.getNomenclature_().getContragent_().getAddress());
                    записьКонтрагентFX.setContact_person(res.getNomenclature_().getContragent_().getContact_person());

                записьНоменклатураFX.setContragent_(записьКонтрагентFX);
                записьНоменклатураFX.setContragent(res.getNomenclature_().getContragent_());

            табЧастьЗакупкаFX.setNomenclature_(записьНоменклатураFX);
            табЧастьЗакупкаFX.setNomenclature(res.getNomenclature_());
            табЧастьЗакупкаFX.setIdDocPurchaseFX_(res.getIdDoc());

            this.tabPartPurchaseList.add(табЧастьЗакупкаFX);
        }
        newSession.close();
        return this.tabPartPurchaseList;
    }

    @Override
    public boolean add(ObjectFX objectFX) {
//        try (final Session newSession = DataAccessObject.openSessionBeginTransaction()) {
//            ЗаписьКонтрагентFX записьКонтрагентFX = (ЗаписьКонтрагентFX) objectFX;
//            ЗаписьКонтрагент записьКонтрагент = new ЗаписьКонтрагент();
//            записьКонтрагент.setCode();
//            записьКонтрагент.setName(записьКонтрагентFX.getName());
//            записьКонтрагент.setType_KA(записьКонтрагентFX.getType_KA());
//            записьКонтрагент.setAddress(записьКонтрагентFX.getAddress());
//            записьКонтрагент.setContact_person(записьКонтрагентFX.getContact_person());
//
//            boolean result = DataAccessObject.save(записьКонтрагент);
//
//            DataAccessObject.commitTransactionCloseSession();
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
    public ObservableList<ТабЧастьЗакупкаFX> findText(String text) {

//        Session newSession = DataAccessObject.get_session();
//        this.tabPartPurchaseList.clear();
//        Query<ЗаписьКонтрагент> query = newSession.createQuery
//                ("select zk from ЗаписьКонтрагент zk where zk.name =: param " +
//                        "or zk.type_KA =: param or zk.address =:param " +
//                        "or zk.contact_person =:param");
//        query.setParameter("param", text);
//        List<ЗаписьКонтрагент> resultList = query.getResultList();
//
//        for (int i = 0; i < resultList.size(); i++) {
//            var res = resultList.get(i);
//            ЗаписьКонтрагентFX записьКонтрагентFX = new ЗаписьКонтрагентFX();
//            записьКонтрагентFX.setCode(res.getCode());
//            записьКонтрагентFX.setName(res.getName());
//            записьКонтрагентFX.setType_KA(res.getType_KA());
//            записьКонтрагентFX.setAddress(res.getAddress());
//            записьКонтрагентFX.setContact_person(res.getContact_person());
//            записьКонтрагентFX.setId(res.getId());
//            this.tabPartPurchaseList.add(записьКонтрагентFX);
//        }
//        newSession.close();
        return tabPartPurchaseList;
    }

    @Override
    public ObservableList<ТабЧастьЗакупкаFX> findInt(Integer value) {
//        this.tabPartPurchaseList.clear();
//        ЗаписьКонтрагент search = ЗаписьКонтрагент.findObjectByValue("code", value);
//        ЗаписьКонтрагентFX result = new ЗаписьКонтрагентFX();
//        result.setId(search.getId());
//        result.setCode(search.getCode());
//        result.setName(search.getName());
//        result.setType_KA(search.getType_KA());
//        result.setAddress(search.getAddress());
//        result.setContact_person(search.getContact_person());
//        this.tabPartPurchaseList.add(result);
        return tabPartPurchaseList;
    }
}
