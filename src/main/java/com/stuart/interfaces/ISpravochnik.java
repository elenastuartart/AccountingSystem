package com.stuart.interfaces;

import com.stuart.models.entity.ЗаписьБД;
import com.stuart.models.entity.справочники.ЗаписьКонтрагент;
import com.stuart.objects.ЗаписьКонтрагентFX;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface ISpravochnik {

    boolean add(ЗаписьКонтрагентFX записьКонтрагентFX);

    boolean update (ЗаписьКонтрагентFX записьКонтрагентFX);

    ObservableList<ЗаписьКонтрагентFX> findAll() throws SQLException;

    ObservableList<ЗаписьБД> find(String text);


}
