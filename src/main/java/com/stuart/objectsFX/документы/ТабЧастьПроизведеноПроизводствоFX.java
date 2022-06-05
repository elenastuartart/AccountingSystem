package com.stuart.objectsFX.документы;

import com.stuart.models.entity.документы.производство.ЗаписьТЧПроизведеноПродукции;
import com.stuart.objectsFX.ObjectFX;
import com.stuart.objectsFX.справочники.ЗаписьНоменклатураFX;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
public class ТабЧастьПроизведеноПроизводствоFX extends ObjectFX {
    @Getter
    @Setter
    private UUID id;
    private SimpleIntegerProperty numberStr  = new SimpleIntegerProperty();
    private SimpleDoubleProperty amount = new SimpleDoubleProperty();
    private SimpleStringProperty nomenclature = new SimpleStringProperty("");

    private ЗаписьНоменклатураFX nomenclatureFX_ = new ЗаписьНоменклатураFX();

    private UUID idDocManufactureFX_;

    public Integer getNumberStr() {
        return numberStr.get();
    }

    public Double getAmount() {
        return amount.get();
    }

    public String getNomenclature() {
        return nomenclature.get();
    }

    public ЗаписьНоменклатураFX getNomenclatureFX_(ЗаписьТЧПроизведеноПродукции записьТЧПроизведеноПродукции) {
        nomenclatureFX_.setId(записьТЧПроизведеноПродукции.getNomenclature_().getId());
        nomenclatureFX_.setCode(записьТЧПроизведеноПродукции.getNomenclature_().getCode());
        nomenclatureFX_.setArticleNumber(записьТЧПроизведеноПродукции.getNomenclature_().getArticle_number());
        nomenclatureFX_.setName(записьТЧПроизведеноПродукции.getNomenclature_().getName());
        nomenclatureFX_.setCategory(записьТЧПроизведеноПродукции.getNomenclature_().getCategory());
        nomenclatureFX_.setSubcategory(записьТЧПроизведеноПродукции.getNomenclature_().getSubcategory());
        nomenclatureFX_.setЗаписьНоменклатура_(записьТЧПроизведеноПродукции.getNomenclature_());
        nomenclatureFX_.setContragent(записьТЧПроизведеноПродукции.getNomenclature_().getContragent_());
        return nomenclatureFX_;
    }

    public ЗаписьНоменклатураFX getNomenclatureFX_() {
        return nomenclatureFX_;
    }

    public UUID getIdDocManufactureFX_() {
        return idDocManufactureFX_;
    }

    public void setNumberStr(Integer numberStr) {
        this.numberStr.set(numberStr);
    }

    public void setAmount(Double amount) {
        this.amount.set(amount);
    }

    public void setNomenclatureFX_(ЗаписьНоменклатураFX nomenclatureFX_) {
        this.nomenclature = nomenclatureFX_.nameProperty();
        this.nomenclatureFX_ = nomenclatureFX_;
    }

    public void setIdDocManufactureFX_(UUID idDocManufactureFX_) {
        this.idDocManufactureFX_ = idDocManufactureFX_;
    }

    public SimpleIntegerProperty numberStrProperty() {
        return numberStr;
    }

    public SimpleDoubleProperty amountProperty() {
        return amount;
    }

    public SimpleStringProperty nomenclatureProperty() {
        return nomenclature;
    }

}
