package com.stuart.interfaces;

import com.stuart.objectsFX.ObjectFX;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface ISpravochnik {

    boolean add(ObjectFX objectFX);

    boolean update (ObjectFX objectFX);

    default ObservableList<?> findAll() throws SQLException {
        return null;
    }

    ObservableList<?> findText(String text);

    ObservableList<?>  findInt(Integer value);
}
