package com.stuart.interfaces.impls.документы.производство;

import com.stuart.dao.DataAccessObject;
import com.stuart.interfaces.ISpravochnik;
import com.stuart.models.entity.документы.закупка.ЗаписьТЧ_Закупка;
import com.stuart.models.entity.документы.производство.ЗаписьТЧПроизведеноПродукции;
import com.stuart.objectsFX.ObjectFX;
import com.stuart.objectsFX.документы.ДокументЗакупкаFX;
import com.stuart.objectsFX.документы.ДокументПроизводствоFX;
import com.stuart.objectsFX.документы.ТабЧастьЗакупкаFX;
import com.stuart.objectsFX.документы.ТабЧастьПроизведеноПроизводствоFX;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class HibernateTabProducedManufacture extends DataAccessObject implements ISpravochnik {

    private ObservableList<ТабЧастьПроизведеноПроизводствоFX> tabPartsProducedList = FXCollections.observableArrayList();

    public ObservableList<ТабЧастьПроизведеноПроизводствоFX> getTabPartsProducedList() {
        return tabPartsProducedList;
    }

    @Override
    public ObservableList<ТабЧастьПроизведеноПроизводствоFX> findAll(ObjectFX objectFX ) {
        ДокументПроизводствоFX документПроизводствоFX = (ДокументПроизводствоFX) objectFX;
        Session newSession = DataAccessObject.get_session();
        Query<ЗаписьТЧПроизведеноПродукции> query = newSession.createQuery("select tch from ЗаписьТЧПроизведеноПродукции tch where tch.idDoc =: param");
        query.setParameter("param", документПроизводствоFX.getId());/////////////передать айди документа, которому принадлежит таб часть
        var resultList = query.getResultList();
        this.tabPartsProducedList.clear();
        for (int i = 0; i < resultList.size(); i++) {
            ЗаписьТЧПроизведеноПродукции res = resultList.get(i);
            ТабЧастьПроизведеноПроизводствоFX табЧастьПроизведеноПроизводствоFX = new ТабЧастьПроизведеноПроизводствоFX();
            табЧастьПроизведеноПроизводствоFX.setId(res.getId());
            табЧастьПроизведеноПроизводствоFX.setNumberStr(res.getLineNumber());
            табЧастьПроизведеноПроизводствоFX.setIdDocManufactureFX_(res.getIdDoc());
            табЧастьПроизведеноПроизводствоFX.setAmount(res.getAmount());
            табЧастьПроизведеноПроизводствоFX.setNomenclatureFX_(табЧастьПроизведеноПроизводствоFX.getNomenclatureFX_(res));

            this.tabPartsProducedList.add(табЧастьПроизведеноПроизводствоFX);
        }
        newSession.close();
        return this.tabPartsProducedList;
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
