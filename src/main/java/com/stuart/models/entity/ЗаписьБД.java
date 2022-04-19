package com.stuart.models.entity;

import com.stuart.dao.записьБД.DataAccessObject;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ЗаписьБД {

    public boolean ПередЗаписью() {
        return false;
    }

    public boolean save() {
        return DataAccessObject.save(this);
    }

    public ЗаписьБД findByValueDAO(String fieldName, Object fieldValue) {
        return null;
    }
}

