package com.stuart.objectsFX;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

public class ЗаписьНоменклатураFX extends ObjectFX {
    @Getter
    @Setter
    private UUID id;
    private SimpleIntegerProperty code  = new SimpleIntegerProperty();
    private SimpleIntegerProperty articleNumber  = new SimpleIntegerProperty();
    private SimpleStringProperty name = new SimpleStringProperty("");
    private SimpleStringProperty category = new SimpleStringProperty(""); ;
    private SimpleStringProperty subcategory = new SimpleStringProperty("");
    private ЗаписьКонтрагентFX записьКонтрагентFX;

    public ЗаписьНоменклатураFX() {

    }

    public ЗаписьНоменклатураFX(Integer code, String name, String category,
                                String subcategory, String type_KA ) {
        this.code = new SimpleIntegerProperty(code);
        this.name = new SimpleStringProperty(name);
        this.category = new SimpleStringProperty(category);
        this.subcategory = new SimpleStringProperty(subcategory);
        this.записьКонтрагентFX = new ЗаписьКонтрагентFX();
    }

    public String getName() {
        return name.get();
    }

    public Integer getCode() {
        return code.get();
    }

    public Integer getArticleNumber() {
        return articleNumber.get();
    }

    public String getSubcategory() {
        return subcategory.get();
    }

    public String getCategory() {
        return category.get();
    }

    public void setCode(Integer  code) {
        this.code.set(code);
    }

    public void setArticleNumber(int articleNumber) {
        this.articleNumber.set(articleNumber);
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setSubcategory(String subcategory) {
        this.subcategory.set(subcategory);
    }

    public void setCategory(String category) {
        this.category.set(category);
    }

    public SimpleIntegerProperty  codeProperty() {
        return code;
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public SimpleStringProperty categoryProperty() {
        return category;
    }

    public SimpleStringProperty subcategoryProperty() {
        return subcategory;
    }

}
