package com.stuart.objectsFX.документы;

import com.stuart.models.entity.документы.закупка.ЗаписьТЧ_Закупка;
import com.stuart.models.entity.документы.производство.ЗаписьТЧПроизведеноПродукции;
import com.stuart.models.entity.документы.производство.ЗаписьТЧРасходМатериалов;
import com.stuart.models.entity.документы.производство.Производство;
import com.stuart.objectsFX.ObjectFX;
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
public class ДокументПроизводствоFX extends ObjectFX {
    @Getter
    @Setter
    private UUID id;
    private Date date = new Date();
    private SimpleStringProperty provedenie = new SimpleStringProperty("");
    private SimpleIntegerProperty number = new SimpleIntegerProperty();
    public boolean pometkaProvedenya;

    private Производство документПроизводство_;
    private ObservableList<ТабЧастьИзрасходованоПроизводствоFX> tabPartsConsumedFX_ = FXCollections.observableArrayList();
    private List<ЗаписьТЧРасходМатериалов> tabPartsConsumed_ = new ArrayList<>();
    private ObservableList<ТабЧастьПроизведеноПроизводствоFX> tabPartsProducedFX_ = FXCollections.observableArrayList();
    private List<ЗаписьТЧПроизведеноПродукции> tabPartsProduced_ = new ArrayList<>();


    public String getProvedenie() {
        return provedenie.get();
    }

    public Integer getNumber() {
        return number.get();
    }

    public Date getDate() {
        return date;
    }

    public Производство getДокументПроизводство_() {
        return документПроизводство_;
    }

    public List<ЗаписьТЧРасходМатериалов> getTabPartsConsumed_(ObservableList<ТабЧастьИзрасходованоПроизводствоFX> listTabFX_) {
        tabPartsConsumed_.clear();
        for (int i = 0; i < listTabFX_.size(); i++) {
            var resFX = listTabFX_.get(i);
            ЗаписьТЧРасходМатериалов записьТЧРасходМатериалов = new ЗаписьТЧРасходМатериалов();
            записьТЧРасходМатериалов.setId(resFX.getId());
            записьТЧРасходМатериалов.setIdDoc(getДокументПроизводство_().getId());
            записьТЧРасходМатериалов.setDoc_manufacture_(getДокументПроизводство_());
            записьТЧРасходМатериалов.setAmount(resFX.getAmount());
            записьТЧРасходМатериалов.setNomenclature_(resFX.getNomenclatureFX_().getЗаписьНоменклатура_());
            записьТЧРасходМатериалов.setStage_(resFX.getPrStageFX_().getЗаписьЭтапыПроизводства_());
            tabPartsConsumed_.add(записьТЧРасходМатериалов);
        }
        return tabPartsConsumed_;
    }

    public List<ЗаписьТЧРасходМатериалов> getTabPartsConsumed_() {
        for (int i = 0; i < tabPartsConsumedFX_.size(); i++) {
            var res = tabPartsConsumedFX_.get(i);
            ЗаписьТЧРасходМатериалов записьТЧРасходМатериалов = new ЗаписьТЧРасходМатериалов();
            записьТЧРасходМатериалов.setId(res.getId());
            записьТЧРасходМатериалов.setAmount(res.getAmount());
            записьТЧРасходМатериалов.setNomenclature_(res.getNomenclatureFX_().getЗаписьНоменклатура_());
            записьТЧРасходМатериалов.setStage_(res.getPrStageFX_().getЗаписьЭтапыПроизводства_());
            записьТЧРасходМатериалов.setIdDoc(res.getIdDocManufactureFX_());
            записьТЧРасходМатериалов.setDoc_manufacture_(getДокументПроизводство_());
            tabPartsConsumed_.add(записьТЧРасходМатериалов);
        }
        return tabPartsConsumed_;
    }

    public List<ЗаписьТЧПроизведеноПродукции> getTabPartsProduced_(ObservableList<ТабЧастьПроизведеноПроизводствоFX> listTabFX) {
        tabPartsProduced_.clear();
        for (int i = 0; i < listTabFX.size(); i++) {
            var resFX = listTabFX.get(i);
            ЗаписьТЧПроизведеноПродукции записьТЧПроизведеноПродукции = new ЗаписьТЧПроизведеноПродукции();
            записьТЧПроизведеноПродукции.setId(resFX.getId());
            записьТЧПроизведеноПродукции.setIdDoc(getДокументПроизводство_().getId());
            записьТЧПроизведеноПродукции.setDoc_manufacture_(getДокументПроизводство_());
            записьТЧПроизведеноПродукции.setAmount(resFX.getAmount());
            записьТЧПроизведеноПродукции.setNomenclature_(resFX.getNomenclatureFX_().getЗаписьНоменклатура_());
            tabPartsProduced_.add(записьТЧПроизведеноПродукции);
        }
        return tabPartsProduced_;
    }

    public List<ЗаписьТЧПроизведеноПродукции> getTabPartsProduced_() {
        for (int i = 0; i < tabPartsProducedFX_.size(); i++) {
            var resFX = tabPartsProducedFX_.get(i);
            ЗаписьТЧПроизведеноПродукции записьТЧПроизведеноПродукции = new ЗаписьТЧПроизведеноПродукции();
            записьТЧПроизведеноПродукции.setId(resFX.getId());
            записьТЧПроизведеноПродукции.setIdDoc(getДокументПроизводство_().getId());
            записьТЧПроизведеноПродукции.setDoc_manufacture_(getДокументПроизводство_());
            записьТЧПроизведеноПродукции.setAmount(resFX.getAmount());
            записьТЧПроизведеноПродукции.setNomenclature_(resFX.getNomenclatureFX_().getЗаписьНоменклатура_());
            tabPartsProduced_.add(записьТЧПроизведеноПродукции);
        }
        return tabPartsProduced_;
    }

    public ObservableList<ТабЧастьИзрасходованоПроизводствоFX> getTabPartsConsumedFX_() {
        return tabPartsConsumedFX_;
    }

    public ObservableList<ТабЧастьИзрасходованоПроизводствоFX> getTabPartsConsumedFX_(List<ЗаписьТЧРасходМатериалов> записьТЧРасходМатериалов) {
        for (int i = 0; i < записьТЧРасходМатериалов.size(); i++) {
            var res = записьТЧРасходМатериалов.get(i);
            ТабЧастьИзрасходованоПроизводствоFX табЧастьИзрасходованоПроизводствоFX = new ТабЧастьИзрасходованоПроизводствоFX();
            табЧастьИзрасходованоПроизводствоFX.setId(res.getId());
            табЧастьИзрасходованоПроизводствоFX.setAmount(res.getAmount());
            табЧастьИзрасходованоПроизводствоFX.setNumberStr(res.getLineNumber());
            табЧастьИзрасходованоПроизводствоFX.setNomenclatureFX_(табЧастьИзрасходованоПроизводствоFX.getNomenclatureFX_());
            табЧастьИзрасходованоПроизводствоFX.setPrStageFX_(табЧастьИзрасходованоПроизводствоFX.getPrStageFX_());
            табЧастьИзрасходованоПроизводствоFX.setIdDocManufactureFX_(res.getIdDoc());
            tabPartsConsumedFX_.add(табЧастьИзрасходованоПроизводствоFX);
        }
        return tabPartsConsumedFX_;
    }

    public ObservableList<ТабЧастьПроизведеноПроизводствоFX> getTabPartsProducedFX_() {
        return tabPartsProducedFX_;
    }

    public ObservableList<ТабЧастьПроизведеноПроизводствоFX> getTabPartsProducedFX_(List<ЗаписьТЧПроизведеноПродукции> записьТЧПроизведеноПродукции) {
        for (int i = 0; i < записьТЧПроизведеноПродукции.size(); i++) {
            var res = записьТЧПроизведеноПродукции.get(i);
            ТабЧастьПроизведеноПроизводствоFX табЧастьПроизведеноПроизводствоFX = new ТабЧастьПроизведеноПроизводствоFX();
            табЧастьПроизведеноПроизводствоFX.setId(res.getId());
            табЧастьПроизведеноПроизводствоFX.setAmount(res.getAmount());
            табЧастьПроизведеноПроизводствоFX.setNumberStr(res.getLineNumber());
            табЧастьПроизведеноПроизводствоFX.setIdDocManufactureFX_(res.getIdDoc());
            табЧастьПроизведеноПроизводствоFX.setNomenclatureFX_(табЧастьПроизведеноПроизводствоFX.getNomenclatureFX_());
            tabPartsProducedFX_.add(табЧастьПроизведеноПроизводствоFX);
        }
        return tabPartsProducedFX_;
    }

    public void setProvedenie(String provedenie) {
        this.provedenie.set(provedenie);
    }

    public void setNumber(Integer number) {
        this.number.set(number);
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setДокументПроизводство_(Производство документПроизводство_) {
        this.документПроизводство_ = документПроизводство_;
    }

    public void setTabPartsConsumed_(ObservableList<ТабЧастьИзрасходованоПроизводствоFX> табЧастиFX) {
        for (int i = 0; i < табЧастиFX.size(); i++) {
            var res = табЧастиFX.get(i);
            ЗаписьТЧРасходМатериалов записьТЧРасходМатериалов = new ЗаписьТЧРасходМатериалов();
            записьТЧРасходМатериалов.setId(res.getId());
            записьТЧРасходМатериалов.setAmount(res.getAmount());
            записьТЧРасходМатериалов.setIdDoc(res.getIdDocManufactureFX_());
            записьТЧРасходМатериалов.setNomenclature_(res.getNomenclatureFX_().getЗаписьНоменклатура_());
            записьТЧРасходМатериалов.setStage_(res.getPrStageFX_().getЗаписьЭтапыПроизводства_());
            записьТЧРасходМатериалов.setLineNumber(res.getNumberStr());
            записьТЧРасходМатериалов.setDoc_manufacture_(getДокументПроизводство_());
            this.tabPartsConsumed_.add(записьТЧРасходМатериалов);
        }
        this.tabPartsConsumed_ = tabPartsConsumed_;
    }

    public void setTabPartsConsumedFX_(ObservableList<ТабЧастьИзрасходованоПроизводствоFX> tabPartsConsumedFX_) {
        this.tabPartsConsumedFX_ = tabPartsConsumedFX_;
    }

    public void setTabPartsProduced_(List<ТабЧастьПроизведеноПроизводствоFX> табЧастиFX) {
        for (int i = 0; i < табЧастиFX.size(); i++) {
            var res = табЧастиFX.get(i);
            ЗаписьТЧПроизведеноПродукции записьТЧПроизведеноПродукции = new ЗаписьТЧПроизведеноПродукции();
            записьТЧПроизведеноПродукции.setId(res.getId());
            записьТЧПроизведеноПродукции.setAmount(res.getAmount());
            записьТЧПроизведеноПродукции.setIdDoc(res.getIdDocManufactureFX_());
            записьТЧПроизведеноПродукции.setNomenclature_(res.getNomenclatureFX_().getЗаписьНоменклатура_());
            записьТЧПроизведеноПродукции.setLineNumber(res.getNumberStr());
            записьТЧПроизведеноПродукции.setDoc_manufacture_(getДокументПроизводство_());
            this.tabPartsProduced_.add(записьТЧПроизведеноПродукции);
        }
        this.tabPartsProduced_ = tabPartsProduced_;
    }

    public void setTabPartsProducedFX_(ObservableList<ТабЧастьПроизведеноПроизводствоFX> tabPartsProducedFX_) {
        this.tabPartsProducedFX_ = tabPartsProducedFX_;
    }

    public SimpleIntegerProperty numberProperty() {
        return number;
    }

    public SimpleStringProperty provedenieProperty() {
        return provedenie;
    }

}
