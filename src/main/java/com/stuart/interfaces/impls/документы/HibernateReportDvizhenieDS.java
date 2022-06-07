package com.stuart.interfaces.impls.документы;

import com.stuart.dao.DataAccessObject;
import com.stuart.interfaces.ISpravochnik;
import com.stuart.models.entity.регистры.ЗаписьРегистраВзаиморасчеты;
import com.stuart.models.entity.регистры.ЗаписьРегистраТоварыНаСкладах;
import com.stuart.models.entity.справочники.ЗаписьКонтрагент;
import com.stuart.models.entity.справочники.ЗаписьНоменклатура;
import com.stuart.objectsFX.ObjectFX;
import com.stuart.objectsFX.регистры.РегистрВзаиморасчетыFX;
import com.stuart.objectsFX.регистры.РегистрТовНаСкладеFX;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.Date;

public class HibernateReportDvizhenieDS extends DataAccessObject implements ISpravochnik {

    private ObservableList<РегистрВзаиморасчетыFX> registerList = FXCollections.observableArrayList();

    public ObservableList<РегистрВзаиморасчетыFX> getRegisterList() {
        return registerList;
    }

    @Override
    public ObservableList<РегистрВзаиморасчетыFX> findAll() {
        Session newSession = DataAccessObject.get_session();
        Query<Object[]> query = newSession.createQuery(
                "select Registr.date AS date, contragent.name AS Contragent, " +
                        "Registr.sum AS Oborot from ЗаписьРегистраВзаиморасчеты AS Registr " +
                        "JOIN ЗаписьКонтрагент AS contragent ON Registr.idKontragent = contragent.id");
        var resultList = query.getResultList();
        this.registerList.clear();
        for (int i = 0; i < resultList.size(); i++) {
            Object[] res = resultList.get(i);
            РегистрВзаиморасчетыFX регистрВзаиморасчетыFX  = new РегистрВзаиморасчетыFX();

            регистрВзаиморасчетыFX.setDate((Date) res[0]);
            регистрВзаиморасчетыFX.setContragent((String) res[1]);
            регистрВзаиморасчетыFX.setSum((Double) res[2]);

            this.registerList.add(регистрВзаиморасчетыFX);
        }
        newSession.close();
        return this.registerList;

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
