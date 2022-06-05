package com.stuart.objectsFX.документы;

import com.stuart.models.entity.документы.закупка.Закупка;
import com.stuart.models.entity.документы.закупка.ЗаписьТЧ_Закупка;
import com.stuart.models.entity.справочники.ЗаписьКонтрагент;
import com.stuart.objectsFX.ObjectFX;
import com.stuart.objectsFX.справочники.ЗаписьКонтрагентFX;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
@NoArgsConstructor
@AllArgsConstructor
public class ДокументЗакупкаFX extends ObjectFX {
    @Getter
    @Setter
    private UUID id;
    private Date date = new Date();
    private SimpleStringProperty provedenie = new SimpleStringProperty("");
    private SimpleIntegerProperty number = new SimpleIntegerProperty();
    private SimpleStringProperty contragent = new SimpleStringProperty("");
    private SimpleDoubleProperty finalSum = new SimpleDoubleProperty();
    public boolean pometkaProvedenya;

    private Закупка документЗакупка_;
    private ЗаписьКонтрагентFX contragentFX_ = new ЗаписьКонтрагентFX();
    private ObservableList<ТабЧастьЗакупкаFX> tabPartsFX_ = FXCollections.observableArrayList();
    private List<ЗаписьТЧ_Закупка> tabParts_ = new ArrayList<>();

    public ДокументЗакупкаFX(Date dateView, ЗаписьКонтрагент записьКонтрагент) {

    }

    public String getProvedenie() {
        return provedenie.get();
    }

    public Integer getNumber() {
        return number.get();
    }

    public String getContragent() {
        return contragent.get();
    }

    public Double getFinalSum() {
        return finalSum.get();
    }

    public Date getDate() {
        return date;
    }

    public ЗаписьКонтрагентFX getContragentFX_(Закупка закупка) {
        contragentFX_.setId(закупка.getContragent_().getId());
        contragentFX_.setCode(закупка.getContragent_().getCode());
        contragentFX_.setName(закупка.getContragent_().getName());
        contragentFX_.setType_KA(закупка.getContragent_().getType_KA());
        contragentFX_.setAddress(закупка.getContragent_().getAddress());
        contragentFX_.setContact_person(закупка.getContragent_().getContact_person());
        contragentFX_.setЗаписьКонтрагент_(закупка.getContragent_());
        return contragentFX_;
    }

    public ЗаписьКонтрагентFX getContragentFX_() {
        return contragentFX_;
    }

    public Закупка getДокументЗакупка_() {
        return документЗакупка_;
    }

    public List<ЗаписьТЧ_Закупка> getTabParts_(ObservableList<ТабЧастьЗакупкаFX> listTabFX_) {
        tabParts_.clear();
        for (int i = 0; i < listTabFX_.size(); i++) {
            var resFX = listTabFX_.get(i);

            ЗаписьТЧ_Закупка записьТЧ_закупка = new ЗаписьТЧ_Закупка();
            записьТЧ_закупка.setId(resFX.getId());
            записьТЧ_закупка.setIdDoc(getДокументЗакупка_().getId());
            записьТЧ_закупка.setDoc_purchase_(getДокументЗакупка_());
            записьТЧ_закупка.setPrice(resFX.getPrice());
            записьТЧ_закупка.setAmount(resFX.getAmount());
            записьТЧ_закупка.setSum();
            записьТЧ_закупка.setNomenclature_(resFX.getNomenclatureFX_().getЗаписьНоменклатура_());
            tabParts_.add(записьТЧ_закупка);
        }

        return tabParts_;
    }

    public List<ЗаписьТЧ_Закупка> getTabParts_() {
        for (int i = 0; i < tabPartsFX_.size(); i++) {
            var res = tabPartsFX_.get(i);
            ЗаписьТЧ_Закупка записьТЧ_закупка = new ЗаписьТЧ_Закупка();
            записьТЧ_закупка.setAmount(res.getAmount());
            записьТЧ_закупка.setPrice(res.getPrice());
            записьТЧ_закупка.setSum();
            записьТЧ_закупка.setNomenclature_(res.getNomenclatureFX_().getЗаписьНоменклатура_());
            записьТЧ_закупка.setIdDoc(res.getIdDocPurchaseFX_());
            записьТЧ_закупка.setDoc_purchase_(getДокументЗакупка_());
            tabParts_.add(записьТЧ_закупка);
        }
        return tabParts_;
    }

    public ObservableList<ТабЧастьЗакупкаFX> getTabPartsFX_() {
        return tabPartsFX_;
    }

    public ObservableList<ТабЧастьЗакупкаFX> getTabPartsFX_(List<ЗаписьТЧ_Закупка> табчасти) {
        for (int i = 0; i < табчасти.size(); i++) {
            var res = табчасти.get(i);

            ТабЧастьЗакупкаFX табЧастьЗакупкаFX = new ТабЧастьЗакупкаFX();
            табЧастьЗакупкаFX.setId(res.getId());
            табЧастьЗакупкаFX.setAmount(res.getAmount());
            табЧастьЗакупкаFX.setPrice(res.getPrice());
            табЧастьЗакупкаFX.setSum(res.getSum());
            табЧастьЗакупкаFX.setNumberStr(res.getLineNumber());
            табЧастьЗакупкаFX.setNomenclatureFX_(табЧастьЗакупкаFX.getNomenclatureFX_());
            табЧастьЗакупкаFX.setIdDocPurchaseFX_(res.getIdDoc());
            tabPartsFX_.add(табЧастьЗакупкаFX);
        }


        return tabPartsFX_;
    }

    public void setProvedenie(String provedenie) {
        this.provedenie.set(provedenie);
    }

    public void setNumber(Integer number) {
        this.number.set(number);
    }

    public void setFinalSum(Double finalSum) {
        this.finalSum.set(finalSum);
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setContragent(ЗаписьКонтрагент записьКонтрагент) {
//        this.contragent = new SimpleStringProperty(записьКонтрагент.getName());
        this.contragent =  this.contragentFX_.nameProperty();
    }

    public void setДокументЗакупка_(Закупка документЗакупка_) {
        this.документЗакупка_ = документЗакупка_;
    }

    public void setTabParts_(ObservableList<ТабЧастьЗакупкаFX> табЧастиFX) {
        for (int i = 0; i < табЧастиFX.size(); i++) {
            var res = табЧастиFX.get(i);
            ЗаписьТЧ_Закупка записьТЧ_закупка = new ЗаписьТЧ_Закупка();
            записьТЧ_закупка.setId(res.getId());
            записьТЧ_закупка.setPrice(res.getPrice());
            записьТЧ_закупка.setAmount(res.getAmount());
            записьТЧ_закупка.setSum();
            записьТЧ_закупка.setIdDoc(res.getIdDocPurchaseFX_());
            записьТЧ_закупка.setNomenclature_(res.getNomenclatureFX_().getЗаписьНоменклатура_());
            записьТЧ_закупка.setLineNumber(res.getNumberStr());
            записьТЧ_закупка.setDoc_purchase_(getДокументЗакупка_());
            this.tabParts_.add(записьТЧ_закупка);
        }

        this.tabParts_ = tabParts_;
    }

    public void setContragentFX_(ЗаписьКонтрагентFX contragentFX_) {
        this.contragentFX_ = contragentFX_;
        this.contragent = contragentFX_.nameProperty();
    }

    public void setTabPartsFX_(ObservableList<ТабЧастьЗакупкаFX> tabPartsFX_) {
        this.tabPartsFX_ = tabPartsFX_;
    }

    public SimpleIntegerProperty numberProperty() {
        return number;
    }

    public SimpleDoubleProperty finalSumProperty() {
        return finalSum;
    }

    public SimpleStringProperty provedenieProperty() {
        return provedenie;
    }

    public SimpleStringProperty contragentProperty() {
        return contragent;
    }

}
