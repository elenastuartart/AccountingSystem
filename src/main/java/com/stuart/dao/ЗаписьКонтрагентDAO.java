package com.stuart.dao;


import com.sun.istack.NotNull;
import org.hibernate.SessionFactory;

public class ЗаписьКонтрагентDAO {
    private final SessionFactory factory;

    public ЗаписьКонтрагентDAO(@NotNull final SessionFactory factory) {
        this.factory = factory;
    }


}
