package com.stuart.interfaces;

import com.stuart.objectsFX.ObjectFX;
import com.stuart.objectsFX.документы.ДокументЗакупкаFX;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface ISpravochnik {

    boolean add(ObjectFX objectFX);

    boolean update (ObjectFX objectFX);

    default ObservableList<?> findAll() throws SQLException {
        return null;
    }

//    default ObservableList<?> findAll(ДокументЗакупкаFX документЗакупкаFX) {
//        return null;
//    }

    default ObservableList<?> findAll(ObjectFX objectFX) {
        return null;
    }

    ObservableList<?> findText(String text);

    ObservableList<?>  findInt(Integer value);
}
