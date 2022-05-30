package com.stuart.interfaces;

import com.stuart.models.entity.ЗаписьБД;
import com.stuart.objects.ЗаписьКонтрагентFX;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface ISpravochnik {

    boolean add(ЗаписьБД записьБД);

    boolean update (ЗаписьБД записьБД);

    ObservableList<ЗаписьКонтрагентFX> findAll() throws SQLException;

    ObservableList<ЗаписьБД> find(String text);


}
