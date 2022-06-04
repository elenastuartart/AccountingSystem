package com.stuart.interfaces.impls.документы;

import com.stuart.dao.DataAccessObject;
import com.stuart.interfaces.ISpravochnik;
import com.stuart.models.entity.документы.закупка.Закупка;
import com.stuart.models.entity.документы.закупка.ЗаписьТЧ_Закупка;
import com.stuart.models.entity.справочники.ЗаписьКонтрагент;
import com.stuart.models.entity.справочники.ЗаписьНоменклатура;
import com.stuart.objectsFX.ObjectFX;
import com.stuart.objectsFX.документы.ДокументЗакупкаFX;
import com.stuart.objectsFX.документы.ТабЧастьЗакупкаFX;
import com.stuart.objectsFX.справочники.ЗаписьКонтрагентFX;
import com.stuart.objectsFX.справочники.ЗаписьНоменклатураFX;
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
        this.docPurchaseList.clear();
        for (int i = 0; i < resultList.size(); i++) {
            Закупка res = resultList.get(i);
            ДокументЗакупкаFX документЗакупкаFX = new ДокументЗакупкаFX();
            документЗакупкаFX.setId(res.getId());
            документЗакупкаFX.setNumber(res.getNumber());
            документЗакупкаFX.setDate(res.getDate());
            if(res.getPometkaProvedeniya()==true)  документЗакупкаFX.setProvedenie("Проведен"); else документЗакупкаFX.setProvedenie("Не проведен");
            документЗакупкаFX.setFinalSum(res.getFinalSum());
            документЗакупкаFX.setContragent(res.getContragent_());
            документЗакупкаFX.setДокументЗакупка_(res);
            документЗакупкаFX.setContragentFX_(документЗакупкаFX.getContragentFX_(res));
//            документЗакупкаFX.setTabParts_(res.getTable_part_purchase_());
            документЗакупкаFX.setTabParts_(документЗакупкаFX.getTabPartsFX_(res.getTable_part_purchase_()));
            this.docPurchaseList.add(документЗакупкаFX);
        }
        newSession.close();
        return this.docPurchaseList;
    }

    @Override
    public boolean add(ObjectFX objectFX) {
        try (final Session newSession = DataAccessObject.openSessionBeginTransaction()) {
            ДокументЗакупкаFX документЗакупкаFX = (ДокументЗакупкаFX) objectFX;
            Закупка документЗакупка = new Закупка();

            документЗакупка.setNumber();
            документЗакупка.setDate(документЗакупкаFX.getDate());
            документЗакупка.setTable_part_purchase_(документЗакупкаFX.getTabParts_());
//            документЗакупка.setTable_part_purchase_(документЗакупкаFX.getTabParts_(документЗакупкаFX.getTabPartsFX_()));

            документЗакупка.setFinalSum();
            документЗакупка.setContragent_(документЗакупкаFX.getContragentFX_().getЗаписьКонтрагент_());
            документЗакупка.setPometkaProvedeniya(документЗакупкаFX.pometkaProvedenya);

            boolean result = документЗакупка.ЗаписатьДокумент();

            DataAccessObject.commitTransactionCloseSession();
            if (result)
                return true;

            return false;
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(ObjectFX objectFX) {
        try (final Session newSession = DataAccessObject.openSessionBeginTransaction()) {
            ДокументЗакупкаFX документЗакупкаFX = (ДокументЗакупкаFX) objectFX;
            Закупка документЗакупка = Закупка.findObjectByValue("id", документЗакупкаFX.getId());
            документЗакупка.setNumber(документЗакупкаFX.getNumber());
            документЗакупка.setDate(документЗакупкаFX.getDate());
            документЗакупка.setFinalSum(документЗакупкаFX.getFinalSum());
            документЗакупка.setContragent_(документЗакупкаFX.getContragentFX_().getЗаписьКонтрагент_());
            документЗакупка.setTable_part_purchase_(документЗакупкаFX.getTabParts_(документЗакупкаFX.getTabPartsFX_()));
            документЗакупка.setPometkaProvedeniya(документЗакупкаFX.pometkaProvedenya);

            boolean result = документЗакупка.ЗаписатьДокумент();

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
    public ObservableList<ДокументЗакупкаFX> findText(String text) {
        Session newSession = DataAccessObject.get_session();
        this.docPurchaseList.clear();
        Query<Закупка> query = newSession.createQuery
                ("select z from Закупка z where z.contragent_.name =: param ");
        query.setParameter("param", text);
        List<Закупка> resultList = query.getResultList();

        for (int i = 0; i < resultList.size(); i++) {
            Закупка res = resultList.get(i);
            ДокументЗакупкаFX документЗакупкаFX = new ДокументЗакупкаFX();
            документЗакупкаFX.setId(res.getId());
            документЗакупкаFX.setNumber(res.getNumber());
            документЗакупкаFX.setDate(res.getDate());
            if(res.getPometkaProvedeniya()==true)  документЗакупкаFX.setProvedenie("Проведен"); else документЗакупкаFX.setProvedenie("Не проведен");
            документЗакупкаFX.setFinalSum(res.getFinalSum());
            документЗакупкаFX.setContragent(res.getContragent_());
            документЗакупкаFX.setДокументЗакупка_(res);
            документЗакупкаFX.setContragentFX_(документЗакупкаFX.getContragentFX_(res));
            документЗакупкаFX.setTabParts_(документЗакупкаFX.getTabPartsFX_(res.getTable_part_purchase_()));
            this.docPurchaseList.add(документЗакупкаFX);
        }
        newSession.close();
        return docPurchaseList;
    }

    @Override
    public ObservableList<ДокументЗакупкаFX> findInt(Integer value) {
        Session newSession = DataAccessObject.get_session();
        this.docPurchaseList.clear();
        Query<Закупка> query = newSession.createQuery
                ("select zn from Закупка zn where zn.number =: param " +
                        "or zn.finalSum =: param ");
        query.setParameter("param", value);
        List<Закупка> resultList = query.getResultList();

        for (int i = 0; i < resultList.size(); i++) {
            var res = resultList.get(i);
            ДокументЗакупкаFX документЗакупкаFX = new ДокументЗакупкаFX();
            документЗакупкаFX.setId(res.getId());
            документЗакупкаFX.setNumber(res.getNumber());
            документЗакупкаFX.setDate(res.getDate());
            if(res.getPometkaProvedeniya()==true)  документЗакупкаFX.setProvedenie("Проведен"); else документЗакупкаFX.setProvedenie("Не проведен");
            документЗакупкаFX.setFinalSum(res.getFinalSum());
            документЗакупкаFX.setContragent(res.getContragent_());
            документЗакупкаFX.setДокументЗакупка_(res);
            документЗакупкаFX.setContragentFX_(документЗакупкаFX.getContragentFX_(res));
            документЗакупкаFX.setTabParts_(документЗакупкаFX.getTabPartsFX_(res.getTable_part_purchase_()));
            this.docPurchaseList.add(документЗакупкаFX);
        }
        newSession.close();
        return docPurchaseList;
    }
}
