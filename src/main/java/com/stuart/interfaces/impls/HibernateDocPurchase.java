package com.stuart.interfaces.impls;

import com.stuart.dao.DataAccessObject;
import com.stuart.interfaces.ISpravochnik;
import com.stuart.models.entity.документы.закупка.Закупка;
import com.stuart.models.entity.справочники.ЗаписьКонтрагент;
import com.stuart.objectsFX.ObjectFX;
import com.stuart.objectsFX.ДокументЗакупкаFX;
import com.stuart.objectsFX.ЗаписьКонтрагентFX;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class HibernateDocPurchase extends DataAccessObject implements ISpravochnik {

    private ObservableList<ДокументЗакупкаFX> docPurchaseList = FXCollections.observableArrayList();

    public ObservableList<ДокументЗакупкаFX> getDocPurchaseList() {
        return docPurchaseList;
    }

    @Override
    public ObservableList<ДокументЗакупкаFX> findAll() throws SQLException {
        Session newSession = DataAccessObject.get_session();
        Query<Закупка> query = newSession.createQuery("select doc from Закупка doc");
        var resultList = query.getResultList();
        for (int i = 0; i < resultList.size(); i++) {
            Закупка res = resultList.get(i);
            ДокументЗакупкаFX документЗакупкаFX = new ДокументЗакупкаFX();
            документЗакупкаFX.setId(res.getId());
            документЗакупкаFX.setNumber(res.getNumber());
            документЗакупкаFX.setDate(res.getDate());
            if(res.getPometkaProvedeniya()==true)  документЗакупкаFX.setProvedenie("Проведен"); else документЗакупкаFX.setProvedenie("Не проведен");
            документЗакупкаFX.setFinalSum(res.getFinalSum());
            документЗакупкаFX.setContragent(res.getContragent_());
//            документЗакупкаFX.setContragent_();

            this.docPurchaseList.add(документЗакупкаFX);
        }
        newSession.close();
        return this.docPurchaseList;
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
//                docPurchaseList.add(записьКонтрагентFX);
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
//        try (final Session newSession = DataAccessObject.openSessionBeginTransaction()) {
//            ЗаписьКонтрагентFX записьКонтрагентFX = (ЗаписьКонтрагентFX) objectFX;
//            ЗаписьКонтрагент записьКонтрагент = ЗаписьКонтрагент.findObjectByValue("id", записьКонтрагентFX.getId());
//            записьКонтрагент.setCode(записьКонтрагентFX.getCode());
//            записьКонтрагент.setName(записьКонтрагентFX.getName());
//            записьКонтрагент.setType_KA(записьКонтрагентFX.getType_KA());
//            записьКонтрагент.setAddress(записьКонтрагентFX.getAddress());
//            записьКонтрагент.setContact_person(записьКонтрагентFX.getContact_person());
//
//            boolean result = DataAccessObject.save(записьКонтрагент);
//            DataAccessObject.commitTransactionCloseSession();
//            if(result) {
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
    public ObservableList<ЗаписьКонтрагентFX> findText(String text) {

//        Session newSession = DataAccessObject.get_session();
//        this.docPurchaseList.clear();
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
//            this.docPurchaseList.add(записьКонтрагентFX);
//        }
//        newSession.close();
//        return docPurchaseList;
        return null;
    }

    @Override
    public ObservableList<ЗаписьКонтрагентFX> findInt(Integer value) {
//        this.docPurchaseList.clear();
//        ЗаписьКонтрагент search = ЗаписьКонтрагент.findObjectByValue("code", value);
//        ЗаписьКонтрагентFX result = new ЗаписьКонтрагентFX();
//        result.setId(search.getId());
//        result.setCode(search.getCode());
//        result.setName(search.getName());
//        result.setType_KA(search.getType_KA());
//        result.setAddress(search.getAddress());
//        result.setContact_person(search.getContact_person());
//        this.docPurchaseList.add(result);
//        return docPurchaseList;
        return null;
    }

}
