package com.stuart.objectsFX.справочники;

import com.stuart.models.entity.справочники.ЗаписьКонтрагент;
import com.stuart.objectsFX.ObjectFX;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

public class ЗаписьКонтрагентFX extends ObjectFX {
    @Getter
    @Setter
    private UUID id;
    private SimpleIntegerProperty code  = new SimpleIntegerProperty();
    private SimpleStringProperty name = new SimpleStringProperty("");
    private SimpleStringProperty contact_person = new SimpleStringProperty(""); ;
    private SimpleStringProperty address = new SimpleStringProperty("");
    private SimpleStringProperty type_KA = new SimpleStringProperty(""); //поставщик/покупатель
    private ЗаписьКонтрагент записьКонтрагент_;

    @Override
    public String toString() {
        return name.getValue();
    }

    public ЗаписьКонтрагентFX() {

    }

    public ЗаписьКонтрагентFX(Integer code, String name, String contact_person,
                              String address, String type_KA ) {
        this.code = new SimpleIntegerProperty(code);
        this.name = new SimpleStringProperty(name);
        this.contact_person = new SimpleStringProperty(contact_person);
        this.address = new SimpleStringProperty(address);
        this.type_KA = new SimpleStringProperty(type_KA);
    }

    public ЗаписьКонтрагент getЗаписьКонтрагент_() {
        return записьКонтрагент_;
    }

    public String getName() {
        return name.get();
    }

    public Integer getCode() {
        return code.get();
    }

    public String getType_KA() {
        return type_KA.get();
    }

    public String getAddress() {
        return address.get();
    }

    public String getContact_person() {
        return contact_person.get();
    }

    public void setЗаписьКонтрагент_(ЗаписьКонтрагент записьКонтрагент_) {
        this.записьКонтрагент_ = записьКонтрагент_;
    }

    public void setCode(Integer  code) {
        this.code.set(code);
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public void setContact_person(String contact_person) {
        this.contact_person.set(contact_person);
    }

    public void setType_KA(String type_KA) {
        this.type_KA.set(type_KA);
    }

    public SimpleIntegerProperty  codeProperty() {
        return code;
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public SimpleStringProperty contact_personProperty() {
        return contact_person;
    }

    public SimpleStringProperty addressProperty() {
        return address;
    }

    public SimpleStringProperty type_KAProperty() {
        return type_KA;
    }

}
