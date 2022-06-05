package com.stuart.objectsFX.документы;

import com.stuart.models.entity.документы.закупка.Закупка;
import com.stuart.models.entity.документы.закупка.ЗаписьТЧ_Закупка;
import com.stuart.models.entity.документы.продажа.ЗаписьТЧСписокТоваров;
import com.stuart.models.entity.документы.продажа.Реализация;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
public class ДокументРеализацияFX extends ObjectFX {
    @Getter
    @Setter
    private UUID id;
    private Date date = new Date();
    private SimpleStringProperty provedenie = new SimpleStringProperty("");
    private SimpleIntegerProperty number = new SimpleIntegerProperty();
    private SimpleStringProperty contragent = new SimpleStringProperty("");
    private SimpleDoubleProperty finalSum = new SimpleDoubleProperty();
    public boolean pometkaProvedenya;

    private Реализация документРеализация_;
    private ЗаписьКонтрагентFX contragentFX_ = new ЗаписьКонтрагентFX();
    private ObservableList<ТабЧастьРеализацияFX> tabPartsFX_ = FXCollections.observableArrayList();
    private List<ЗаписьТЧСписокТоваров> tabParts_ = new ArrayList<>();

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

    public ЗаписьКонтрагентFX getContragentFX_(Реализация реализация) {
        contragentFX_.setId(реализация.getContragent_().getId());
        contragentFX_.setCode(реализация.getContragent_().getCode());
        contragentFX_.setName(реализация.getContragent_().getName());
        contragentFX_.setType_KA(реализация.getContragent_().getType_KA());
        contragentFX_.setAddress(реализация.getContragent_().getAddress());
        contragentFX_.setContact_person(реализация.getContragent_().getContact_person());
        contragentFX_.setЗаписьКонтрагент_(реализация.getContragent_());
        return contragentFX_;
    }

    public ЗаписьКонтрагентFX getContragentFX_() {
        return contragentFX_;
    }

    public Реализация getДокументРеализация_() {
        return документРеализация_;
    }

    public List<ЗаписьТЧСписокТоваров> getTabParts_(ObservableList<ТабЧастьРеализацияFX> listTabFX_) {
        tabParts_.clear();
        for (int i = 0; i < listTabFX_.size(); i++) {
            var resFX = listTabFX_.get(i);

            ЗаписьТЧСписокТоваров записьТЧ_реализация = new ЗаписьТЧСписокТоваров();
            записьТЧ_реализация.setId(resFX.getId());
            записьТЧ_реализация.setIdDoc(getДокументРеализация_().getId());
            записьТЧ_реализация.setDoc_sale_(getДокументРеализация_()) ;
            записьТЧ_реализация.setPrice(resFX.getPrice());
            записьТЧ_реализация.setAmount(resFX.getAmount());
            записьТЧ_реализация.setSum();
            записьТЧ_реализация.setNomenclature_(resFX.getNomenclatureFX_().getЗаписьНоменклатура_());
            tabParts_.add(записьТЧ_реализация);
        }
        return tabParts_;
    }

    public List<ЗаписьТЧСписокТоваров> getTabParts_() {
        for (int i = 0; i < tabPartsFX_.size(); i++) {
            var res = tabPartsFX_.get(i);
            ЗаписьТЧСписокТоваров записьТЧ_реализация = new ЗаписьТЧСписокТоваров();
            записьТЧ_реализация.setAmount(res.getAmount());
            записьТЧ_реализация.setPrice(res.getPrice());
            записьТЧ_реализация.setSum();
            записьТЧ_реализация.setNomenclature_(res.getNomenclatureFX_().getЗаписьНоменклатура_());
            записьТЧ_реализация.setIdDoc(res.getIdDocSaleFX_());
            записьТЧ_реализация.setDoc_sale_(getДокументРеализация_());
            tabParts_.add(записьТЧ_реализация);
        }
        return tabParts_;
    }

    public ObservableList<ТабЧастьРеализацияFX> getTabPartsFX_() {
        return tabPartsFX_;
    }

    public ObservableList<ТабЧастьРеализацияFX> getTabPartsFX_(List<ЗаписьТЧСписокТоваров> табчасти) {
        for (int i = 0; i < табчасти.size(); i++) {
            var res = табчасти.get(i);

            ТабЧастьРеализацияFX табЧастьРеализацияFX = new ТабЧастьРеализацияFX();
            табЧастьРеализацияFX.setId(res.getId());
            табЧастьРеализацияFX.setAmount(res.getAmount());
            табЧастьРеализацияFX.setPrice(res.getPrice());
            табЧастьРеализацияFX.setSum(res.getSum());
            табЧастьРеализацияFX.setNumberStr(res.getLineNumber());
            табЧастьРеализацияFX.setNomenclatureFX_(табЧастьРеализацияFX.getNomenclatureFX_());
            табЧастьРеализацияFX.setIdDocSaleFX_(res.getIdDoc());
            tabPartsFX_.add(табЧастьРеализацияFX);
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

    public void setДокументРеализация_(Реализация документРеализация_) {
        this.документРеализация_ = документРеализация_;
    }

    public void setTabParts_(ObservableList<ТабЧастьРеализацияFX> табЧастиFX) {
        for (int i = 0; i < табЧастиFX.size(); i++) {
            var res = табЧастиFX.get(i);
            ЗаписьТЧСписокТоваров записьТЧ_реализация = new ЗаписьТЧСписокТоваров();
            записьТЧ_реализация.setId(res.getId());
            записьТЧ_реализация.setPrice(res.getPrice());
            записьТЧ_реализация.setAmount(res.getAmount());
            записьТЧ_реализация.setSum();
            записьТЧ_реализация.setIdDoc(res.getIdDocSaleFX_());
            записьТЧ_реализация.setNomenclature_(res.getNomenclatureFX_().getЗаписьНоменклатура_());
            записьТЧ_реализация.setLineNumber(res.getNumberStr());
            записьТЧ_реализация.setDoc_sale_(getДокументРеализация_());
            this.tabParts_.add(записьТЧ_реализация);
        }
        this.tabParts_ = tabParts_;
    }

    public void setContragentFX_(ЗаписьКонтрагентFX contragentFX_) {
        this.contragentFX_ = contragentFX_;
        this.contragent = contragentFX_.nameProperty();
    }

    public void setTabPartsFX_(ObservableList<ТабЧастьРеализацияFX> tabPartsFX_) {
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
