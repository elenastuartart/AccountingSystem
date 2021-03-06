package com.stuart.objectsFX.справочники;

import com.stuart.models.entity.справочники.ЗаписьЭтапыПроизводства;
import com.stuart.objectsFX.ObjectFX;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
public class ЗаписьЭтапыПроизводстваFX extends ObjectFX {
    @Getter
    @Setter
    private UUID id;
    private SimpleIntegerProperty code = new SimpleIntegerProperty();
    private SimpleStringProperty name = new SimpleStringProperty("");
    private SimpleStringProperty description_stage =  new SimpleStringProperty("");
    private ЗаписьЭтапыПроизводства записьЭтапыПроизводства_;

    @Override
    public String toString() {
        return name.getValue();
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

    public ЗаписьЭтапыПроизводства getЗаписьЭтапыПроизводства_() {
        return записьЭтапыПроизводства_;
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

    public void setЗаписьЭтапыПроизводства_(ЗаписьЭтапыПроизводства записьЭтапыПроизводства_) {
        this.записьЭтапыПроизводства_ = записьЭтапыПроизводства_;
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
