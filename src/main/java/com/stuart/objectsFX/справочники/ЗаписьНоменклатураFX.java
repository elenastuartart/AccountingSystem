package com.stuart.objectsFX.справочники;

import com.stuart.models.entity.справочники.ЗаписьКонтрагент;
import com.stuart.models.entity.справочники.ЗаписьНоменклатура;
import com.stuart.objectsFX.ObjectFX;
import com.stuart.objectsFX.справочники.ЗаписьКонтрагентFX;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
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

    private ЗаписьНоменклатура записьНоменклатура_;
    private ЗаписьКонтрагентFX contragentFX_ = new ЗаписьКонтрагентFX();

    @Override
    public String toString() {
        return name.getValue();
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

    public ЗаписьКонтрагентFX getContragentFX_() {
        return contragentFX_;
    }

    public ЗаписьНоменклатура getЗаписьНоменклатура_() {
        return записьНоменклатура_;
    }

    public void setContragentFX_(ЗаписьКонтрагентFX contragentFX_) {
        this.contragentFX_ = contragentFX_;
        this.contragent = contragentFX_.nameProperty();
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

    public void setContragent(ЗаписьКонтрагент записьКонтрагент) {
        this.contragent =  this.contragentFX_.nameProperty();
    }

    public void setЗаписьНоменклатура_(ЗаписьНоменклатура записьНоменклатура_) {
        this.записьНоменклатура_ = записьНоменклатура_;
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
