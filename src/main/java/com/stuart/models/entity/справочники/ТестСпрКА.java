package com.stuart.models.entity.справочники;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "test_KA", schema = "study_db")
public class ТестСпрКА extends ЭлементСправочника {
    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID id = UUID.randomUUID();

    private SimpleIntegerProperty  code = new SimpleIntegerProperty();
    private SimpleStringProperty name = new SimpleStringProperty("");
    private SimpleStringProperty contact_person = new SimpleStringProperty(""); ;
    private SimpleStringProperty address = new SimpleStringProperty("");
    private SimpleStringProperty type_KA = new SimpleStringProperty(""); //поставщик/покупатель

    public ТестСпрКА() {

    }

    public ТестСпрКА(Integer code, String name, String contact_person,
                     String address, String type_KA ) {
        this.code = new SimpleIntegerProperty(code);
        this.name = new SimpleStringProperty(name);
        this.contact_person = new SimpleStringProperty(contact_person);
        this.address = new SimpleStringProperty(address);
        this.type_KA = new SimpleStringProperty(type_KA);
    }

    @Column(name = "name")
    public String getName() {
        return name.get();
    }

    @Column(name = "code")
    public Integer getCode() {
        return code.get();
    }

    @Column(name = "type_ka")
    public String getType_KA() {
        return type_KA.get();
    }

    @Column(name = "address")
    public String getAddress() {
        return address.get();
    }

    @Column(name = "contacts")
    public String getContact_person() {
        return contact_person.get();
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
