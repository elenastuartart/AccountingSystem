package com.stuart.interfaces;

import com.stuart.objects.ЗаписьКонтрагентFX;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface ISpravochnik {

    boolean add(ЗаписьКонтрагентFX записьКонтрагентFX);

    boolean update (ЗаписьКонтрагентFX записьКонтрагентFX);

    ObservableList<ЗаписьКонтрагентFX> findAll() throws SQLException;

    ObservableList<ЗаписьКонтрагентFX> findText(String text);

    ObservableList<ЗаписьКонтрагентFX>  findInt(Integer value);
}
