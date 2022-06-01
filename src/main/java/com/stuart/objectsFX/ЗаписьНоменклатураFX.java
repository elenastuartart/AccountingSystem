package com.stuart.objectsFX;

import com.stuart.models.entity.справочники.ЗаписьКонтрагент;
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
    private SimpleStringProperty contragent = new SimpleStringProperty("");

    private ЗаписьКонтрагентFX contragent_ = new ЗаписьКонтрагентFX();

    public ЗаписьНоменклатураFX() {

    }

    public ЗаписьНоменклатураFX(Integer code, String name, String category,
                                String subcategory, ЗаписьКонтрагент contragent) {
        this.code = new SimpleIntegerProperty(code);
        this.name = new SimpleStringProperty(name);
        this.category = new SimpleStringProperty(category);
        this.subcategory = new SimpleStringProperty(subcategory);
        this.contragent = new SimpleStringProperty(contragent.getName());
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

    public String getContragent() {
        return contragent.get();
    } //поле для вывода в таблицу

    public ЗаписьКонтрагентFX getContragent_() {
        return contragent_;
    }

    public void setContragent_(ЗаписьКонтрагентFX contragent_) {
        this.contragent_ = contragent_;
        this.contragent = contragent_.nameProperty();
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

//    public void setProducer(ЗаписьКонтрагент producer) {
////        this.producer.set(String.valueOf(producer));
//        this.producer = new SimpleStringProperty(producer.getName());
//    }

    public void setContragent(ЗаписьКонтрагент записьКонтрагент) {
        this.contragent =  this.contragent_.nameProperty();
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

    public SimpleStringProperty contragentProperty() {
        return contragent;
    }


}
