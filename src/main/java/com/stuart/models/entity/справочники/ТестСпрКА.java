package com.stuart.models.entity.справочники;

import javafx.beans.property.SimpleStringProperty;

import javax.persistence.Table;


@Table(name = "test_KA", schema = "study_db")
public class ТестСпрКА extends ЭлементСправочника {

    private SimpleStringProperty code = new SimpleStringProperty("");
    private SimpleStringProperty name = new SimpleStringProperty("");
    private SimpleStringProperty contact_person = new SimpleStringProperty(""); ;
    private SimpleStringProperty address = new SimpleStringProperty("");
    private SimpleStringProperty type_KA =new SimpleStringProperty(""); //поставщик/покупатель

    public ТестСпрКА() {

    }

    public ТестСпрКА(String code, String name, String contact_person,
                     String address, String type_KA ) {
        this.code = new SimpleStringProperty(code);
        this.name = new SimpleStringProperty(name);
        this.contact_person = new SimpleStringProperty(contact_person);
        this.address = new SimpleStringProperty(address);
        this.type_KA = new SimpleStringProperty(type_KA);
    }

    public String getName() {
        return name.get();
    }

    public String getCode() {
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

    public void setCode(String  code) {
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

    public SimpleStringProperty codeProperty() {
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
