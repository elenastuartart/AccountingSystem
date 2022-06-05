package com.stuart.interfaces.impls.документы.производство;

import com.stuart.dao.DataAccessObject;
import com.stuart.interfaces.ISpravochnik;
import com.stuart.models.entity.документы.закупка.ЗаписьТЧ_Закупка;
import com.stuart.models.entity.документы.производство.ЗаписьТЧРасходМатериалов;
import com.stuart.models.entity.справочники.ЗаписьКонтрагент;
import com.stuart.objectsFX.ObjectFX;
import com.stuart.objectsFX.документы.ДокументЗакупкаFX;
import com.stuart.objectsFX.документы.ДокументПроизводствоFX;
import com.stuart.objectsFX.документы.ТабЧастьЗакупкаFX;
import com.stuart.objectsFX.документы.ТабЧастьИзрасходованоПроизводствоFX;
import com.stuart.objectsFX.справочники.ЗаписьКонтрагентFX;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class HibernateTabConsumedManufacture extends DataAccessObject implements ISpravochnik {

    private ObservableList<ТабЧастьИзрасходованоПроизводствоFX> tabPartsConsumedList = FXCollections.observableArrayList();

    public ObservableList<ТабЧастьИзрасходованоПроизводствоFX> getTabPartsConsumedList() {
        return tabPartsConsumedList;
    }

    @Override
    public ObservableList<ТабЧастьИзрасходованоПроизводствоFX> findAll(ObjectFX objectFX ) {
        ДокументПроизводствоFX документПроизводствоFX = (ДокументПроизводствоFX) objectFX;
        Session newSession = DataAccessObject.get_session();
        Query<ЗаписьТЧРасходМатериалов> query = newSession.createQuery("select tch from ЗаписьТЧРасходМатериалов tch where tch.idDoc =: param");
        query.setParameter("param", документПроизводствоFX.getId());/////////////передать айди документа, которому принадлежит таб часть
        var resultList = query.getResultList();
        this.tabPartsConsumedList.clear();
        for (int i = 0; i < resultList.size(); i++) {
            ЗаписьТЧРасходМатериалов res = resultList.get(i);
            ТабЧастьИзрасходованоПроизводствоFX табЧастьИзрасходованоПроизводствоFX = new ТабЧастьИзрасходованоПроизводствоFX();
            табЧастьИзрасходованоПроизводствоFX.setId(res.getId());
            табЧастьИзрасходованоПроизводствоFX.setNumberStr(res.getLineNumber());
            табЧастьИзрасходованоПроизводствоFX.setIdDocManufactureFX_(res.getIdDoc());
            табЧастьИзрасходованоПроизводствоFX.setAmount(res.getAmount());
            табЧастьИзрасходованоПроизводствоFX.setNomenclatureFX_(табЧастьИзрасходованоПроизводствоFX.getNomenclatureFX_(res));
            табЧастьИзрасходованоПроизводствоFX.setPrStageFX_(табЧастьИзрасходованоПроизводствоFX.getPrStageFX_(res));

            this.tabPartsConsumedList.add(табЧастьИзрасходованоПроизводствоFX);
        }
        newSession.close();
        return this.tabPartsConsumedList;
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
