package com.stuart.interfaces.impls;

import com.stuart.dao.DataAccessObject;
import com.stuart.interfaces.ISpravochnik;
import com.stuart.models.entity.справочники.ЗаписьКонтрагент;
import com.stuart.models.entity.справочники.ЗаписьНоменклатура;
import com.stuart.objectsFX.ObjectFX;
import com.stuart.objectsFX.ЗаписьКонтрагентFX;
import com.stuart.objectsFX.ЗаписьНоменклатураFX;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class HibernateSprNom extends DataAccessObject implements ISpravochnik {

    private ObservableList<ЗаписьНоменклатураFX> nomenclaturiesList = FXCollections.observableArrayList();

    public ObservableList<ЗаписьНоменклатураFX> getNomenclaturiesList() {
        return nomenclaturiesList;
    }

    @Override
    public ObservableList<ЗаписьНоменклатураFX> findAll() throws SQLException {
        Session newSession = DataAccessObject.get_session();
        Query<ЗаписьНоменклатура> query = newSession.createQuery("select n from ЗаписьНоменклатура n");
        var resultList = query.getResultList();
        this.nomenclaturiesList.clear();
        for (int i = 0; i < resultList.size(); i++) {
            ЗаписьНоменклатура res = resultList.get(i);
            ЗаписьНоменклатураFX записьНоменклатураFX = new ЗаписьНоменклатураFX();
            записьНоменклатураFX.setCode(res.getCode());
            записьНоменклатураFX.setArticleNumber(res.getArticle_number());
            записьНоменклатураFX.setName(res.getName());
            записьНоменклатураFX.setCategory(res.getCategory());
            записьНоменклатураFX.setSubcategory(res.getSubcategory());
            записьНоменклатураFX.setId(res.getId());
            //заполняем поле объекта ЗаписьКонтрагентFX contragent_ (создаем объект контрагентFX и заполняем его данными
            //контрагента, который привязан к номенклатуре, из базы)
                ЗаписьКонтрагентFX записьКонтрагентFX = записьНоменклатураFX.getContragent_();
                записьКонтрагентFX.setId(res.getContragent_().getId());
                записьКонтрагентFX.setCode(res.getContragent_().getCode());
                записьКонтрагентFX.setName(res.getContragent_().getName());
                записьКонтрагентFX.setType_KA(res.getContragent_().getType_KA());
                записьКонтрагентFX.setAddress(res.getContragent_().getAddress());
                записьКонтрагентFX.setContact_person(res.getContragent_().getContact_person());
                записьКонтрагентFX.setЗаписьКонтрагент(res.getContragent_());


            записьНоменклатураFX.setContragent_(записьКонтрагентFX);
            //для вывода в таблице заполняем отдельное поле Producer наименованием контрагента, который привязан к номенклатуре
            записьНоменклатураFX.setContragent(res.getContragent_());
            //контрагент
            this.nomenclaturiesList.add(записьНоменклатураFX);
        }
        newSession.close();
        return this.nomenclaturiesList;
    }

    @Override
    public boolean add(ObjectFX objectFX) {
        try (final Session newSession = DataAccessObject.openSessionBeginTransaction()) {
            ЗаписьНоменклатураFX записьНоменклатураFX = (ЗаписьНоменклатураFX) objectFX;
            ЗаписьНоменклатура записьНоменклатура = new ЗаписьНоменклатура();
            записьНоменклатура.setCode();
            записьНоменклатура.setArticle_number();
            записьНоменклатура.setName(записьНоменклатураFX.getName());
            записьНоменклатура.setCategory(записьНоменклатураFX.getCategory());
            записьНоменклатура.setSubcategory(записьНоменклатураFX.getSubcategory());
            //контрагент

            boolean result = DataAccessObject.save(записьНоменклатура);

            DataAccessObject.commitTransactionCloseSession();

            if (result) {
                записьНоменклатураFX.setCode(записьНоменклатура.getCode());
                записьНоменклатураFX.setId(записьНоменклатура.getId());
                nomenclaturiesList.add(записьНоменклатураFX);
                return true;
            }
            return false;
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(ObjectFX objectFX) {
        try (final Session newSession = DataAccessObject.openSessionBeginTransaction()) {
            ЗаписьНоменклатураFX записьНоменклатураFX = (ЗаписьНоменклатураFX) objectFX;
            ЗаписьНоменклатура записьНоменклатура = ЗаписьНоменклатура.findObjectByValue("id", записьНоменклатураFX.getId());
            записьНоменклатура.setCode(записьНоменклатураFX.getCode());
            записьНоменклатура.setArticle_number(записьНоменклатураFX.getArticleNumber());
            записьНоменклатура.setName(записьНоменклатураFX.getName());
            записьНоменклатура.setCategory(записьНоменклатураFX.getCategory());
            записьНоменклатура.setSubcategory(записьНоменклатураFX.getSubcategory());
            записьНоменклатура.setContragent_(записьНоменклатураFX.getContragent_().getЗаписьКонтрагент());
            //контрагент

            boolean result = DataAccessObject.save(записьНоменклатура);
            DataAccessObject.commitTransactionCloseSession();
            if(result) {
                return true;
            }
            return false;
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public ObservableList<ЗаписьНоменклатураFX> findText(String text) {

        Session newSession = DataAccessObject.get_session();
        this.nomenclaturiesList.clear();
        Query<ЗаписьНоменклатура> query = newSession.createQuery
                ("select zn from ЗаписьНоменклатура zn where zn.name =: param " +
                        "or zn.category =: param or zn.subcategory =:param ");
        query.setParameter("param", text);
        List<ЗаписьНоменклатура> resultList = query.getResultList();

        for (int i = 0; i < resultList.size(); i++) {
            var res = resultList.get(i);
            ЗаписьНоменклатураFX записьНоменклатураFX = new ЗаписьНоменклатураFX();
            записьНоменклатураFX.setCode(res.getCode());
            записьНоменклатураFX.setArticleNumber(res.getArticle_number());
            записьНоменклатураFX.setName(res.getName());
            записьНоменклатураFX.setCategory(res.getCategory());
            записьНоменклатураFX.setSubcategory(res.getSubcategory());
            записьНоменклатураFX.setId(res.getId());
            //контрагент
            this.nomenclaturiesList.add(записьНоменклатураFX);
        }
        newSession.close();
        return nomenclaturiesList;
    }

    @Override
    public ObservableList<ЗаписьНоменклатураFX> findInt(Integer value) {
        Session newSession = DataAccessObject.get_session();
        this.nomenclaturiesList.clear();
        Query<ЗаписьНоменклатура> query = newSession.createQuery
                ("select zn from ЗаписьНоменклатура zn where zn.code =: param " +
                        "or zn.article_number =: param ");
        query.setParameter("param", value);
        List<ЗаписьНоменклатура> resultList = query.getResultList();

        for (int i = 0; i < resultList.size(); i++) {
            var res = resultList.get(i);
            ЗаписьНоменклатураFX записьНоменклатураFX =  new ЗаписьНоменклатураFX();
            записьНоменклатураFX.setCode(res.getCode());
            записьНоменклатураFX.setArticleNumber(res.getArticle_number());
            записьНоменклатураFX.setName(res.getName());
            записьНоменклатураFX.setCategory(res.getCategory());
            записьНоменклатураFX.setSubcategory(res.getSubcategory());
            записьНоменклатураFX.setId(res.getId());
            //контрагент
            this.nomenclaturiesList.add(записьНоменклатураFX);
        }
        newSession.close();
        return nomenclaturiesList;
    }
}
