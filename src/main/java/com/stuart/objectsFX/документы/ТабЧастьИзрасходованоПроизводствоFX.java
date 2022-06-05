package com.stuart.objectsFX.документы;

import com.stuart.models.entity.документы.закупка.ЗаписьТЧ_Закупка;
import com.stuart.models.entity.документы.производство.ЗаписьТЧРасходМатериалов;
import com.stuart.objectsFX.ObjectFX;
import com.stuart.objectsFX.справочники.ЗаписьНоменклатураFX;
import com.stuart.objectsFX.справочники.ЗаписьЭтапыПроизводстваFX;
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
public class ТабЧастьИзрасходованоПроизводствоFX extends ObjectFX {
    @Getter
    @Setter
    private UUID id;
    private SimpleIntegerProperty numberStr  = new SimpleIntegerProperty();
    private SimpleDoubleProperty amount = new SimpleDoubleProperty();
    private SimpleStringProperty nomenclature = new SimpleStringProperty("");
    private SimpleStringProperty prStage = new SimpleStringProperty("");

    private ЗаписьНоменклатураFX nomenclatureFX_ = new ЗаписьНоменклатураFX();
    private ЗаписьЭтапыПроизводстваFX prStageFX_ = new ЗаписьЭтапыПроизводстваFX();

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

    public ЗаписьНоменклатураFX getNomenclatureFX_(ЗаписьТЧРасходМатериалов записьТЧРасходМатериалов) {
        nomenclatureFX_.setId(записьТЧРасходМатериалов.getNomenclature_().getId());
        nomenclatureFX_.setCode(записьТЧРасходМатериалов.getNomenclature_().getCode());
        nomenclatureFX_.setArticleNumber(записьТЧРасходМатериалов.getNomenclature_().getArticle_number());
        nomenclatureFX_.setName(записьТЧРасходМатериалов.getNomenclature_().getName());
        nomenclatureFX_.setCategory(записьТЧРасходМатериалов.getNomenclature_().getCategory());
        nomenclatureFX_.setSubcategory(записьТЧРасходМатериалов.getNomenclature_().getSubcategory());
        nomenclatureFX_.setЗаписьНоменклатура_(записьТЧРасходМатериалов.getNomenclature_());
        nomenclatureFX_.setContragent(записьТЧРасходМатериалов.getNomenclature_().getContragent_());
        return nomenclatureFX_;
    }

    public ЗаписьНоменклатураFX getNomenclatureFX_() {
        return nomenclatureFX_;
    }

    public ЗаписьЭтапыПроизводстваFX getPrStageFX_(ЗаписьТЧРасходМатериалов записьТЧРасходМатериалов) {
        prStageFX_.setId(записьТЧРасходМатериалов.getStage_().getId());
        prStageFX_.setCode(записьТЧРасходМатериалов.getStage_().getCode());
        prStageFX_.setName(записьТЧРасходМатериалов.getStage_().getName());
        prStageFX_.setDescription_stage(записьТЧРасходМатериалов.getStage_().getDescription_stage());
        prStageFX_.setЗаписьЭтапыПроизводства_(записьТЧРасходМатериалов.getStage_());
        return prStageFX_;
    }

    public ЗаписьЭтапыПроизводстваFX getPrStageFX_() {
        return prStageFX_;
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

    public void setPrStageFX_(ЗаписьЭтапыПроизводстваFX prStageFX_) {
        this.prStage = prStageFX_.nameProperty();
        this.prStageFX_ = prStageFX_;
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

    public SimpleStringProperty prStageProperty() {
        return prStage;
    }
}
