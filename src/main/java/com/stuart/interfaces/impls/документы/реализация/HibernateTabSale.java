package com.stuart.interfaces.impls.документы.реализация;

import com.stuart.dao.DataAccessObject;
import com.stuart.interfaces.ISpravochnik;
import com.stuart.models.entity.документы.закупка.ЗаписьТЧ_Закупка;
import com.stuart.models.entity.документы.продажа.ЗаписьТЧСписокТоваров;
import com.stuart.objectsFX.ObjectFX;
import com.stuart.objectsFX.документы.ДокументЗакупкаFX;
import com.stuart.objectsFX.документы.ДокументРеализацияFX;
import com.stuart.objectsFX.документы.ТабЧастьЗакупкаFX;
import com.stuart.objectsFX.документы.ТабЧастьРеализацияFX;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class HibernateTabSale extends DataAccessObject implements ISpravochnik {

    private ObservableList<ТабЧастьРеализацияFX> tabPartSaleList = FXCollections.observableArrayList();

    public ObservableList<ТабЧастьРеализацияFX> getTabPartSaleList() {
        return tabPartSaleList;
    }

    @Override
    public ObservableList<ТабЧастьРеализацияFX> findAll(ObjectFX objectFX ) {
        ДокументРеализацияFX документРеализацияFX = (ДокументРеализацияFX) objectFX;
        Session newSession = DataAccessObject.get_session();
        Query<ЗаписьТЧСписокТоваров> query = newSession.createQuery("select tch from ЗаписьТЧСписокТоваров tch where tch.idDoc =: param");
        query.setParameter("param", документРеализацияFX.getId());/////////////передать айди документа, которому принадлежит таб часть
        var resultList = query.getResultList();
        this.tabPartSaleList.clear();
        for (int i = 0; i < resultList.size(); i++) {
            ЗаписьТЧСписокТоваров res = resultList.get(i);
            ТабЧастьРеализацияFX табЧастьРеализацияFX = new ТабЧастьРеализацияFX();
            табЧастьРеализацияFX.setNumberStr(res.getLineNumber());
            табЧастьРеализацияFX.setAmount(res.getAmount());
            табЧастьРеализацияFX.setPrice(res.getPrice());
            табЧастьРеализацияFX.setSum(res.getSum());
            табЧастьРеализацияFX.setId(res.getId());
            табЧастьРеализацияFX.setNomenclatureFX_(табЧастьРеализацияFX.getNomenclatureFX_(res));
            табЧастьРеализацияFX.setIdDocSaleFX_(res.getIdDoc());

            this.tabPartSaleList.add(табЧастьРеализацияFX);
        }
        newSession.close();
        return this.tabPartSaleList;
    }

    @Override
    public boolean add(ObjectFX objectFX) {
        return false;
    }

    @Override
    public boolean update(ObjectFX objectFX) {
        return false;
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
