package com.stuart.objectsFX.документы;

import com.stuart.models.entity.документы.закупка.ЗаписьТЧ_Закупка;
import com.stuart.models.entity.справочники.ЗаписьНоменклатура;
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
public class ТабЧастьЗакупкаFX extends ObjectFX {
    @Getter
    @Setter
    private UUID id;
    private SimpleIntegerProperty numberStr  = new SimpleIntegerProperty();
    private SimpleDoubleProperty amount = new SimpleDoubleProperty();
    private SimpleDoubleProperty price = new SimpleDoubleProperty();
    private SimpleDoubleProperty sum = new SimpleDoubleProperty();
    private SimpleStringProperty nomenclature = new SimpleStringProperty("");

    private ЗаписьНоменклатураFX nomenclatureFX_ = new ЗаписьНоменклатураFX();

    private UUID idDocPurchaseFX_;

    public Integer getNumberStr() {
        return numberStr.get();
    }

    public Double getAmount() {
        return amount.get();
    }

    public Double getPrice() {
        return price.get();
    }

    public Double getSum() {
        return sum.get();
    }

    public String getNomenclature() {
        return nomenclature.get();
    }

    public ЗаписьНоменклатураFX getNomenclatureFX_(ЗаписьТЧ_Закупка записьТЧ_закупка) {
        nomenclatureFX_.setId(записьТЧ_закупка.getNomenclature_().getId());
        nomenclatureFX_.setCode(записьТЧ_закупка.getNomenclature_().getCode());
        nomenclatureFX_.setArticleNumber(записьТЧ_закупка.getNomenclature_().getArticle_number());
        nomenclatureFX_.setName(записьТЧ_закупка.getNomenclature_().getName());
        nomenclatureFX_.setCategory(записьТЧ_закупка.getNomenclature_().getCategory());
        nomenclatureFX_.setSubcategory(записьТЧ_закупка.getNomenclature_().getSubcategory());
        nomenclatureFX_.setЗаписьНоменклатура_(записьТЧ_закупка.getNomenclature_());
        nomenclatureFX_.setContragent(записьТЧ_закупка.getNomenclature_().getContragent_());
        return nomenclatureFX_;
    }

    public ЗаписьНоменклатураFX getNomenclatureFX_() {
        return nomenclatureFX_;
    }

    public UUID getIdDocPurchaseFX_() {
        return idDocPurchaseFX_;
    }

    public void setNumberStr(Integer numberStr) {
        this.numberStr.set(numberStr);
    }

    public void setAmount(Double amount) {
        this.amount.set(amount);
    }

    public void setPrice(Double price) {
        this.price.set(price);
    }

    public void setSum(double sum) {
        this.sum.set(sum);
    }

    public void setNomenclatureFX_(ЗаписьНоменклатураFX nomenclatureFX_) {
        this.nomenclature = nomenclatureFX_.nameProperty();
        this.nomenclatureFX_ = nomenclatureFX_;
    }

    public void setIdDocPurchaseFX_(UUID idDocPurchaseFX_) {
        this.idDocPurchaseFX_ = idDocPurchaseFX_;
    }

    public SimpleIntegerProperty numberStrProperty() {
        return numberStr;
    }

    public SimpleDoubleProperty amountProperty() {
        return amount;
    }

    public SimpleDoubleProperty priceProperty() {
        return price;
    }

    public SimpleDoubleProperty sumProperty() {
        return sum;
    }

    public SimpleStringProperty nomenclatureProperty() {
        return nomenclature;
    }

}
