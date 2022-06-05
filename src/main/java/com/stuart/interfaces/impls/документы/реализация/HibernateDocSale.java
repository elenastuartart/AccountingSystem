package com.stuart.interfaces.impls.документы.реализация;

import com.stuart.dao.DataAccessObject;
import com.stuart.interfaces.ISpravochnik;
import com.stuart.models.entity.документы.закупка.Закупка;
import com.stuart.models.entity.документы.продажа.Реализация;
import com.stuart.objectsFX.ObjectFX;
import com.stuart.objectsFX.документы.ДокументЗакупкаFX;
import com.stuart.objectsFX.документы.ДокументРеализацияFX;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class HibernateDocSale extends DataAccessObject implements ISpravochnik {

    private ObservableList<ДокументРеализацияFX> docSaleList = FXCollections.observableArrayList();

    public ObservableList<ДокументРеализацияFX> getDocSaleList() {
        return docSaleList;
    }

    @Override
    public ObservableList<ДокументРеализацияFX> findAll() throws SQLException {
        Session newSession = DataAccessObject.get_session();
        Query<Реализация> query = newSession.createQuery("select doc from Реализация doc");
        var resultList = query.getResultList();
        this.docSaleList.clear();
        for (int i = 0; i < resultList.size(); i++) {
            Реализация res = resultList.get(i);
            ДокументРеализацияFX документРеализацияFX = new ДокументРеализацияFX();
            документРеализацияFX.setId(res.getId());
            документРеализацияFX.setNumber(res.getNumber());
            документРеализацияFX.setDate(res.getDate());
            if(res.getPometkaProvedeniya()==true)  документРеализацияFX.setProvedenie("Проведен"); else документРеализацияFX.setProvedenie("Не проведен");
            документРеализацияFX.setFinalSum(res.getFinalSum());
            документРеализацияFX.setContragent(res.getContragent_());
            документРеализацияFX.setДокументРеализация_(res);
            документРеализацияFX.setContragentFX_(документРеализацияFX.getContragentFX_(res));
//            документЗакупкаFX.setTabParts_(res.getTable_part_purchase_());
            документРеализацияFX.setTabParts_(документРеализацияFX.getTabPartsFX_(res.getTable_part_list_of_products_()));
            this.docSaleList.add(документРеализацияFX);
        }
        newSession.close();
        return this.docSaleList;
    }

    @Override
    public boolean add(ObjectFX objectFX) {
        try (final Session newSession = DataAccessObject.openSessionBeginTransaction()) {
            ДокументРеализацияFX документРеализацияFX = (ДокументРеализацияFX) objectFX;
            Реализация документРеализация = new Реализация();
            документРеализация.setNumber();
            документРеализация.setDate(документРеализацияFX.getDate());
            документРеализация.setTable_part_list_of_products_(документРеализацияFX.getTabParts_());
            документРеализация.setFinalSum();
            документРеализация.setContragent_(документРеализацияFX.getContragentFX_().getЗаписьКонтрагент_());
            документРеализация.setPometkaProvedeniya(документРеализацияFX.pometkaProvedenya);

            boolean result = документРеализация.ЗаписатьДокумент();

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
            ДокументРеализацияFX документРеализацияFX = (ДокументРеализацияFX) objectFX;
            Реализация документРеализация = Реализация.findObjectByValue("id", документРеализацияFX.getId());
            документРеализация.setNumber(документРеализацияFX.getNumber());
            документРеализация.setDate(документРеализацияFX.getDate());
            документРеализация.setFinalSum(документРеализацияFX.getFinalSum());
            документРеализация.setContragent_(документРеализацияFX.getContragentFX_().getЗаписьКонтрагент_());
            документРеализация.setTable_part_list_of_products_(документРеализацияFX.getTabParts_(документРеализацияFX.getTabPartsFX_()));
            документРеализация.setPometkaProvedeniya(документРеализацияFX.pometkaProvedenya);

            boolean result = документРеализация.ЗаписатьДокумент();

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
    public ObservableList<ДокументРеализацияFX> findInt(Integer value) {
        Session newSession = DataAccessObject.get_session();
        this.docSaleList.clear();
        Query<Реализация> query = newSession.createQuery
                ("select zn from Реализация zn where zn.number =: param ");
        query.setParameter("param", value);
        List<Реализация> resultList = query.getResultList();

        for (int i = 0; i < resultList.size(); i++) {
            Реализация res = resultList.get(i);
            ДокументРеализацияFX документРеализацияFX = new ДокументРеализацияFX();
            документРеализацияFX.setId(res.getId());
            документРеализацияFX.setNumber(res.getNumber());
            документРеализацияFX.setDate(res.getDate());
            if(res.getPometkaProvedeniya()==true)  документРеализацияFX.setProvedenie("Проведен"); else документРеализацияFX.setProvedenie("Не проведен");
            документРеализацияFX.setFinalSum(res.getFinalSum());
            документРеализацияFX.setContragent(res.getContragent_());
            документРеализацияFX.setДокументРеализация_(res);
            документРеализацияFX.setContragentFX_(документРеализацияFX.getContragentFX_(res));
//            документЗакупкаFX.setTabParts_(res.getTable_part_purchase_());
            документРеализацияFX.setTabParts_(документРеализацияFX.getTabPartsFX_(res.getTable_part_list_of_products_()));
            this.docSaleList.add(документРеализацияFX);
        }
        newSession.close();
        return docSaleList;
    }
}
