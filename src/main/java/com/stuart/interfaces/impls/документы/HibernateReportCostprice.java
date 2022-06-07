package com.stuart.interfaces.impls.документы;

import com.stuart.dao.DataAccessObject;
import com.stuart.interfaces.ISpravochnik;
import com.stuart.models.entity.регистры.ЗаписьРегистраСебестоимостьЕдПродукции;
import com.stuart.models.entity.регистры.ЗаписьРегистраТоварыНаСкладах;
import com.stuart.models.entity.справочники.ЗаписьНоменклатура;
import com.stuart.objectsFX.ObjectFX;
import com.stuart.objectsFX.регистры.РегистрСебестоимостьFX;
import com.stuart.objectsFX.регистры.РегистрТовНаСкладеFX;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class HibernateReportCostprice extends DataAccessObject implements ISpravochnik {

    private ObservableList<РегистрСебестоимостьFX> registerList = FXCollections.observableArrayList();

    public ObservableList<РегистрСебестоимостьFX> getRegisterList() {
        return registerList;
    }

    @Override
    public ObservableList<РегистрСебестоимостьFX> findAll() {
        Session newSession = DataAccessObject.get_session();
        Query<Object[]> query = newSession.createQuery(
                "select Nom.name AS Nomenclature, SUM(Registr.amount) AS Amount, " +
                        "SUM(Registr.profit) AS Profit, " +
                        "AVG(Registr.profitByUnit) AS profitByUnit, " +
                        "SUM(Registr.sumCostprice) AS sumCostprice from " +
                        "ЗаписьРегистраСебестоимостьЕдПродукции AS Registr JOIN ЗаписьНоменклатура " +
                        "AS Nom ON Registr.idNom = Nom.id GROUP BY Nom.name");
        var resultList = query.getResultList();
        this.registerList.clear();
        for (int i = 0; i < resultList.size(); i++) {
            Object[] res = resultList.get(i);
            РегистрСебестоимостьFX регистрСебестоимостьFX = new РегистрСебестоимостьFX();
            регистрСебестоимостьFX.setNomenclature((String) res[0]);
            регистрСебестоимостьFX.setAmount((Double) res[1]);
            регистрСебестоимостьFX.setProfit((Double) res[2]);
            регистрСебестоимостьFX.setProfitByUnit((Double) res[3]);
            регистрСебестоимостьFX.setCostprice((Double) res[4]);
            this.registerList.add(регистрСебестоимостьFX);
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
