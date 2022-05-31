package com.stuart.interfaces.impls;

import com.stuart.dao.DataAccessObject;
import com.stuart.interfaces.ISpravochnik;
import com.stuart.models.entity.справочники.ЗаписьКонтрагент;
import com.stuart.objectsFX.ObjectFX;
import com.stuart.objectsFX.ЗаписьКонтрагентFX;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class HibernateSprKA extends DataAccessObject implements ISpravochnik {

    private ObservableList<ЗаписьКонтрагентFX> contragentList = FXCollections.observableArrayList();

    public ObservableList<ЗаписьКонтрагентFX> getContragentList() {
        return contragentList;
    }

    @Override
    public ObservableList<ЗаписьКонтрагентFX> findAll() throws SQLException {
        Session newSession = DataAccessObject.get_session();
        Query<ЗаписьКонтрагент> query = newSession.createQuery("select k from ЗаписьКонтрагент k");
        var resultList = query.getResultList();
        for (int i = 0; i < resultList.size(); i++) {
            var res = resultList.get(i);
            ЗаписьКонтрагентFX записьКонтрагентFX = new ЗаписьКонтрагентFX();
            записьКонтрагентFX.setCode(res.getCode());
            записьКонтрагентFX.setName(res.getName());
            записьКонтрагентFX.setType_KA(res.getType_KA());
            записьКонтрагентFX.setAddress(res.getAddress());
            записьКонтрагентFX.setContact_person(res.getContact_person());
            записьКонтрагентFX.setId(res.getId());
            this.contragentList.add(записьКонтрагентFX);
        }
        newSession.close();
        return this.contragentList;
    }

    @Override
    public boolean add(ObjectFX objectFX) {
        try (final Session newSession = DataAccessObject.openSessionBeginTransaction()) {
            ЗаписьКонтрагентFX записьКонтрагентFX = (ЗаписьКонтрагентFX) objectFX;
            ЗаписьКонтрагент записьКонтрагент = new ЗаписьКонтрагент();
            записьКонтрагент.setCode();
            записьКонтрагент.setName(записьКонтрагентFX.getName());
            записьКонтрагент.setType_KA(записьКонтрагентFX.getType_KA());
            записьКонтрагент.setAddress(записьКонтрагентFX.getAddress());
            записьКонтрагент.setContact_person(записьКонтрагентFX.getContact_person());

            boolean result = DataAccessObject.save(записьКонтрагент);

            DataAccessObject.commitTransactionCloseSession();

            if (result) {
                записьКонтрагентFX.setCode(записьКонтрагент.getCode());
                записьКонтрагентFX.setId(записьКонтрагент.getId());
                contragentList.add(записьКонтрагентFX);
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
    public boolean update(ObjectFX objectFX) {
        try (final Session newSession = DataAccessObject.openSessionBeginTransaction()) {
            ЗаписьКонтрагентFX записьКонтрагентFX = (ЗаписьКонтрагентFX) objectFX;
            ЗаписьКонтрагент записьКонтрагент = ЗаписьКонтрагент.findObjectByValue("id", записьКонтрагентFX.getId());
            записьКонтрагент.setCode(записьКонтрагентFX.getCode());
            записьКонтрагент.setName(записьКонтрагентFX.getName());
            записьКонтрагент.setType_KA(записьКонтрагентFX.getType_KA());
            записьКонтрагент.setAddress(записьКонтрагентFX.getAddress());
            записьКонтрагент.setContact_person(записьКонтрагентFX.getContact_person());

            boolean result = DataAccessObject.save(записьКонтрагент);
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
    public ObservableList<ЗаписьКонтрагентFX> findText(String text) {

        Session newSession = DataAccessObject.get_session();
        this.contragentList.clear();
        Query<ЗаписьКонтрагент> query = newSession.createQuery
                ("select zk from ЗаписьКонтрагент zk where zk.name =: param " +
                        "or zk.type_KA =: param or zk.address =:param " +
                        "or zk.contact_person =:param");
        query.setParameter("param", text);
        List<ЗаписьКонтрагент> resultList = query.getResultList();

        for (int i = 0; i < resultList.size(); i++) {
            var res = resultList.get(i);
            ЗаписьКонтрагентFX записьКонтрагентFX = new ЗаписьКонтрагентFX();
            записьКонтрагентFX.setCode(res.getCode());
            записьКонтрагентFX.setName(res.getName());
            записьКонтрагентFX.setType_KA(res.getType_KA());
            записьКонтрагентFX.setAddress(res.getAddress());
            записьКонтрагентFX.setContact_person(res.getContact_person());
            записьКонтрагентFX.setId(res.getId());
            this.contragentList.add(записьКонтрагентFX);
        }
        newSession.close();
        return contragentList;
    }

    @Override
    public ObservableList<ЗаписьКонтрагентFX> findInt(Integer value) {
        this.contragentList.clear();
        ЗаписьКонтрагент search = ЗаписьКонтрагент.findObjectByValue("code", value);
        ЗаписьКонтрагентFX result = new ЗаписьКонтрагентFX();
        result.setId(search.getId());
        result.setCode(search.getCode());
        result.setName(search.getName());
        result.setType_KA(search.getType_KA());
        result.setAddress(search.getAddress());
        result.setContact_person(search.getContact_person());
        this.contragentList.add(result);
        return contragentList;
    }
}
