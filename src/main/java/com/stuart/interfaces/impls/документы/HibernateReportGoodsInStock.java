package com.stuart.interfaces.impls.документы;

import com.stuart.dao.DataAccessObject;
import com.stuart.interfaces.ISpravochnik;
import com.stuart.models.entity.регистры.ЗаписьРегистраТоварыНаСкладах;
import com.stuart.models.entity.справочники.ЗаписьНоменклатура;
import com.stuart.objectsFX.ObjectFX;
import com.stuart.objectsFX.регистры.РегистрТовНаСкладеFX;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;

public class HibernateReportGoodsInStock extends DataAccessObject implements ISpravochnik {

    private ObservableList<РегистрТовНаСкладеFX> registerList = FXCollections.observableArrayList();
    private Object res;

    public ObservableList<РегистрТовНаСкладеFX> getRegisterList() {
        return registerList;
    }

    @Override
    public ObservableList<РегистрТовНаСкладеFX> findAll() {
        Session newSession = DataAccessObject.get_session();
        Query<Object[]> query = newSession.createQuery(
                "  select Nom.name AS Nomenclature," +
                        " SUM(Registr.amount) AS Amount," +
                        " SUM(Registr.sum) AS Sum  from  ЗаписьРегистраТоварыНаСкладах " +
                        "AS Registr JOIN ЗаписьНоменклатура " +
                        "AS Nom ON Registr.idNom = Nom.id GROUP BY Nom.name");
        var resultList = query.getResultList();
        this.registerList.clear();
        for (int i = 0; i < resultList.size(); i++) {
            Object[] res = resultList.get(i);
            РегистрТовНаСкладеFX регистрТовНаСкладеFX = new РегистрТовНаСкладеFX();
            регистрТовНаСкладеFX.setNomenclature((String) res[0]);
            регистрТовНаСкладеFX.setAmount((Double) res[1]);
            регистрТовНаСкладеFX.setPrice((Double) res[2]);

            this.registerList.add(регистрТовНаСкладеFX);
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
