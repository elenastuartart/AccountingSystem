package com.stuart.interfaces.impls;

import com.stuart.dao.DataAccessObject;
import com.stuart.interfaces.ISpravochnik;
import com.stuart.models.entity.ЗаписьБД;
import com.stuart.models.entity.справочники.ЗаписьКонтрагент;
import com.stuart.objects.ЗаписьКонтрагентFX;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class DBSpravochnikKA extends DataAccessObject implements ISpravochnik {

    //реализация для контрагента
    private ObservableList<ЗаписьКонтрагентFX> contragentList = FXCollections.observableArrayList();

    public ObservableList<ЗаписьКонтрагентFX> getContragentList() {
        return contragentList;
    }

    @Override
    public ObservableList<ЗаписьКонтрагентFX> findAll() {
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
    public boolean add(ЗаписьКонтрагентFX записьКонтрагентFX) {
        try (final Session newSession = DataAccessObject.openSessionBeginTransaction()) {
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
    public boolean update(ЗаписьКонтрагентFX записьКонтрагентFX) {
        try (final Session newSession = DataAccessObject.openSessionBeginTransaction()) {
            ЗаписьКонтрагент записьКонтрагент = ЗаписьКонтрагент.findObjectByValue("id", записьКонтрагентFX.getId());
//            ЗаписьКонтрагент записьКонтрагент = ЗаписьКонтрагент.findObjectByValue("code", записьКонтрагентFX.getCode());
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
            return false;///////
        }
    }

    @Override
    public ObservableList<ЗаписьБД> find(String text) {
        String s = null;
        return null;
        /////
    }
}
