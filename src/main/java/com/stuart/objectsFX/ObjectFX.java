package com.stuart.objectsFX;

import com.stuart.interfaces.ISpravochnik;
import javafx.collections.ObservableList;
import java.sql.SQLException;


public class ObjectFX implements ISpravochnik {

    @Override
    public boolean add(ObjectFX objectFX) {
        return false;
    }

    @Override
    public boolean update(ObjectFX objectFX) {
        return false;
    }

    @Override
    public ObservableList<?> findAll() throws SQLException {
        return null;
    }

    @Override
    public ObservableList<?> findText(String text) {
        return null;
    }

    @Override
    public ObservableList<?> findInt(Integer value) {
        return null;
    }
}
