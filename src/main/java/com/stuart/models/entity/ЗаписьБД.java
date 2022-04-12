package com.stuart.models.entity;

import com.stuart.dao.записьБД.ЗаписьБД_DAO;
import org.hibernate.SessionFactory;

public class ЗаписьБД {

    public boolean ПередЗаписью() {
        return false;
    }

    public boolean ДобавитьЗапись_в_БД(SessionFactory factory) {
        ЗаписьБД_DAO ЭлементDAO = new ЗаписьБД_DAO(factory);
        return ЭлементDAO.create(this);
    }

}
