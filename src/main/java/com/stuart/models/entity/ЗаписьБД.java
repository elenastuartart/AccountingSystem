package com.stuart.models.entity;

import com.stuart.dao.DataAccessObject;

public class ЗаписьБД {

    protected boolean ПередЗаписью() {
        return false;
    }

    public boolean save() {
        return DataAccessObject.save(this);
    }

    public boolean delete() {
        return  DataAccessObject.delete(this);
    }

}

