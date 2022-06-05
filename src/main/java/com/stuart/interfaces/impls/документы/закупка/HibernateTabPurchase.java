package com.stuart.interfaces.impls.документы.закупка;

import com.stuart.dao.DataAccessObject;
import com.stuart.interfaces.ISpravochnik;
import com.stuart.models.entity.документы.закупка.Закупка;
import com.stuart.models.entity.документы.закупка.ЗаписьТЧ_Закупка;
import com.stuart.models.entity.справочники.ЗаписьКонтрагент;
import com.stuart.objectsFX.ObjectFX;
import com.stuart.objectsFX.документы.ДокументЗакупкаFX;
import com.stuart.objectsFX.справочники.ЗаписьКонтрагентFX;
import com.stuart.objectsFX.справочники.ЗаписьНоменклатураFX;
import com.stuart.objectsFX.документы.ТабЧастьЗакупкаFX;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.SQLException;

public class HibernateTabPurchase extends DataAccessObject implements ISpravochnik {

    private ObservableList<ТабЧастьЗакупкаFX> tabPartPurchaseList = FXCollections.observableArrayList();

    public ObservableList<ТабЧастьЗакупкаFX> getTabPartPurchaseList() {
        return tabPartPurchaseList;
    }

    @Override
    public ObservableList<ТабЧастьЗакупкаFX> findAll(ObjectFX objectFX ) {
        ДокументЗакупкаFX документЗакупкаFX = (ДокументЗакупкаFX) objectFX;
        Session newSession = DataAccessObject.get_session();
        Query<ЗаписьТЧ_Закупка> query = newSession.createQuery("select tch from ЗаписьТЧ_Закупка tch where tch.idDoc =: param");
        query.setParameter("param", документЗакупкаFX.getId());/////////////передать айди документа, которому принадлежит таб часть
        var resultList = query.getResultList();
        this.tabPartPurchaseList.clear();
        for (int i = 0; i < resultList.size(); i++) {
            ЗаписьТЧ_Закупка res = resultList.get(i);
            ТабЧастьЗакупкаFX табЧастьЗакупкаFX = new ТабЧастьЗакупкаFX();
            табЧастьЗакупкаFX.setNumberStr(res.getLineNumber());
            табЧастьЗакупкаFX.setAmount(res.getAmount());
            табЧастьЗакупкаFX.setPrice(res.getPrice());
            табЧастьЗакупкаFX.setSum(res.getSum());
            табЧастьЗакупкаFX.setId(res.getId());
            табЧастьЗакупкаFX.setNomenclatureFX_(табЧастьЗакупкаFX.getNomenclatureFX_(res));
            табЧастьЗакупкаFX.setIdDocPurchaseFX_(res.getIdDoc());

            this.tabPartPurchaseList.add(табЧастьЗакупкаFX);
        }
        newSession.close();
        return this.tabPartPurchaseList;
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
