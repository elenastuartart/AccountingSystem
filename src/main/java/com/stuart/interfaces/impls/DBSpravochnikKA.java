package com.stuart.interfaces.impls;

import com.stuart.dao.записьБД.DataAccessObject;
import com.stuart.interfaces.ISpravochnik;
import com.stuart.models.entity.ЗаписьБД;
import com.stuart.models.entity.справочники.ЗаписьКонтрагент;
import javafx.collections.ObservableList;
import org.hibernate.Session;

import javax.management.Query;

public class DBSpravochnikKA extends DataAccessObject implements ISpravochnik  {


    @Override
    public boolean add(ЗаписьБД записьБД) {
        return false;
    }

    @Override
    public boolean update(ЗаписьБД записьБД) {
        return false;
    }

    @Override
    public ObservableList<ЗаписьБД> findAll() {
        return null;
    }

    @Override
    public ObservableList<ЗаписьБД> find(String text) {
        return null;
    }
}
