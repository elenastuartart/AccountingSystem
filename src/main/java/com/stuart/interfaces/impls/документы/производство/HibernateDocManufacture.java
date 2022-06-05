package com.stuart.interfaces.impls.документы.производство;

import com.stuart.dao.DataAccessObject;
import com.stuart.interfaces.ISpravochnik;
import com.stuart.models.entity.документы.закупка.Закупка;
import com.stuart.models.entity.документы.производство.Производство;
import com.stuart.objectsFX.ObjectFX;
import com.stuart.objectsFX.документы.ДокументЗакупкаFX;
import com.stuart.objectsFX.документы.ДокументПроизводствоFX;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class HibernateDocManufacture extends DataAccessObject implements ISpravochnik {

    private ObservableList<ДокументПроизводствоFX> docManufactureList = FXCollections.observableArrayList();

    public ObservableList<ДокументПроизводствоFX> getDocManufactureList() {
        return docManufactureList;
    }

    @Override
    public ObservableList<ДокументПроизводствоFX> findAll() throws SQLException {
        Session newSession = DataAccessObject.get_session();
        Query<Производство> query = newSession.createQuery("select doc from Производство doc");
        var resultList = query.getResultList();
        this.docManufactureList.clear();
        for (int i = 0; i < resultList.size(); i++) {
            Производство res = resultList.get(i);
            ДокументПроизводствоFX документПроизводствоFX = new ДокументПроизводствоFX();
            документПроизводствоFX.setId(res.getId());
            документПроизводствоFX.setNumber(res.getNumber());
            документПроизводствоFX.setDate(res.getDate());
            if(res.getPometkaProvedeniya()==true)  документПроизводствоFX.setProvedenie("Проведен"); else документПроизводствоFX.setProvedenie("Не проведен");
            документПроизводствоFX.setДокументПроизводство_(res);
            документПроизводствоFX.setTabPartsConsumed_(документПроизводствоFX.getTabPartsConsumedFX_(res.getTable_part_material_consuption_()));
            документПроизводствоFX.setTabPartsProduced_(документПроизводствоFX.getTabPartsProducedFX_(res.getTable_part_produced_of_products_()));
            this.docManufactureList.add(документПроизводствоFX);
        }
        newSession.close();
        return this.docManufactureList;
    }

    @Override
    public boolean add(ObjectFX objectFX) {
        try (final Session newSession = DataAccessObject.openSessionBeginTransaction()) {
            ДокументПроизводствоFX документПроизводствоFX  = (ДокументПроизводствоFX) objectFX;
            Производство документПроизводство = new Производство();

            документПроизводство.setNumber();
            документПроизводство.setDate(документПроизводствоFX.getDate());
            документПроизводство.setPometkaProvedeniya(документПроизводствоFX.pometkaProvedenya);
            документПроизводство.setTable_part_material_consuption_(документПроизводствоFX.getTabPartsConsumed_());
            документПроизводство.setTable_part_produced_of_products_(документПроизводствоFX.getTabPartsProduced_());

            boolean result = документПроизводство.ЗаписатьДокумент();

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
            ДокументПроизводствоFX документПроизводствоFX  = (ДокументПроизводствоFX) objectFX;
            Производство документПроизводство = Производство.findObjectByValue("id", документПроизводствоFX.getId());

            документПроизводство.setNumber(документПроизводствоFX.getNumber());
            документПроизводство.setDate(документПроизводствоFX.getDate());
            документПроизводство.setPometkaProvedeniya(документПроизводствоFX.pometkaProvedenya);
            документПроизводство.setTable_part_material_consuption_(документПроизводствоFX.getTabPartsConsumed_(документПроизводствоFX.getTabPartsConsumedFX_()));
            документПроизводство.setTable_part_produced_of_products_(документПроизводствоFX.getTabPartsProduced_(документПроизводствоFX.getTabPartsProducedFX_()));

            boolean result = документПроизводство.ЗаписатьДокумент();

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
    public ObservableList<ДокументПроизводствоFX> findInt(Integer value) {
        Session newSession = DataAccessObject.get_session();
        this.docManufactureList.clear();
        Query<Производство> query = newSession.createQuery
                ("select zn from Производство zn where zn.number =: param ");
        query.setParameter("param", value);
        List<Производство> resultList = query.getResultList();

        for (int i = 0; i < resultList.size(); i++) {
            var res = resultList.get(i);
            ДокументПроизводствоFX документПроизводствоFX = new ДокументПроизводствоFX();
            документПроизводствоFX.setId(res.getId());
            документПроизводствоFX.setNumber(res.getNumber());
            документПроизводствоFX.setDate(res.getDate());
            if(res.getPometkaProvedeniya()==true)  документПроизводствоFX.setProvedenie("Проведен"); else документПроизводствоFX.setProvedenie("Не проведен");
            документПроизводствоFX.setДокументПроизводство_(res);
            документПроизводствоFX.setTabPartsConsumed_(документПроизводствоFX.getTabPartsConsumedFX_(res.getTable_part_material_consuption_()));
            документПроизводствоFX.setTabPartsProduced_(документПроизводствоFX.getTabPartsProducedFX_(res.getTable_part_produced_of_products_()));
            this.docManufactureList.add(документПроизводствоFX);
        }
        newSession.close();
        return docManufactureList;
    }
}
