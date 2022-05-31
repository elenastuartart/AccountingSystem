package com.stuart.objectsFX;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

public class ЗаписьЭтапыПроизводстваFX extends ObjectFX {
    @Getter
    @Setter
    private UUID id;
    private SimpleIntegerProperty code = new SimpleIntegerProperty();
    private SimpleStringProperty name = new SimpleStringProperty("");
    private SimpleStringProperty description_stage =  new SimpleStringProperty("");

    public ЗаписьЭтапыПроизводстваFX(Integer code, String name, String description_stage) {
        this.code = new SimpleIntegerProperty(code);
        this.name = new SimpleStringProperty(name);
        this.description_stage = new SimpleStringProperty(description_stage);
    }

    public ЗаписьЭтапыПроизводстваFX() {

    }

    public Integer getCode() {
        return code.get();
    }

    public String getName() {
        return name.get();
    }

    public String getDescription_stage() {
        return description_stage.get();
    }

    public void setCode(int code) {
        this.code.set(code);
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setDescription_stage(String description_stage) {
        this.description_stage.set(description_stage);
    }

    public SimpleIntegerProperty codeProperty() {
        return code;
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public SimpleStringProperty description_stageProperty() {
        return description_stage;
    }
}
