package com.stuart.interfaces;

import com.stuart.models.entity.ЗаписьБД;
import javafx.collections.ObservableList;

public interface ISpravochnik {

    boolean add(ЗаписьБД записьБД);

    boolean update (ЗаписьБД записьБД);

    ObservableList<ЗаписьБД> findAll();

    ObservableList<ЗаписьБД> find(String text);


}
