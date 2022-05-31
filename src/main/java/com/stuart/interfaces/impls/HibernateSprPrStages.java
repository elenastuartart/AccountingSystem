package com.stuart.interfaces.impls;

import com.stuart.dao.DataAccessObject;
import com.stuart.interfaces.ISpravochnik;
import com.stuart.models.entity.справочники.ЗаписьКонтрагент;
import com.stuart.models.entity.справочники.ЗаписьЭтапыПроизводства;
import com.stuart.objectsFX.ObjectFX;
import com.stuart.objectsFX.ЗаписьКонтрагентFX;
import com.stuart.objectsFX.ЗаписьЭтапыПроизводстваFX;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class HibernateSprPrStages extends DataAccessObject implements ISpravochnik {

    private ObservableList<ЗаписьЭтапыПроизводстваFX> prStagesList = FXCollections.observableArrayList();

    public ObservableList<ЗаписьЭтапыПроизводстваFX> getPrStagesList() {
        return prStagesList;
    }

    @Override
    public ObservableList<ЗаписьЭтапыПроизводстваFX> findAll() throws SQLException {
        Session newSession = DataAccessObject.get_session();

        Query<ЗаписьЭтапыПроизводства> query = newSession.createQuery("select st from ЗаписьЭтапыПроизводства st");
        var resultList = query.getResultList();
        for (int i = 0; i < resultList.size(); i++) {
            var res = resultList.get(i);
            ЗаписьЭтапыПроизводстваFX записьЭтапыПроизводстваFX = new ЗаписьЭтапыПроизводстваFX();
            записьЭтапыПроизводстваFX.setCode(res.getCode());
            записьЭтапыПроизводстваFX.setName(res.getName());
            записьЭтапыПроизводстваFX.setDescription_stage(res.getDescription_stage());
            this.prStagesList.add(записьЭтапыПроизводстваFX);
        }
        newSession.close();
        return this.prStagesList;
    }

    @Override
    public boolean add(ObjectFX objectFX) {
        try (final Session newSession = DataAccessObject.openSessionBeginTransaction()) {
            ЗаписьЭтапыПроизводстваFX записьЭтапыПроизводстваFX = (ЗаписьЭтапыПроизводстваFX) objectFX;
            ЗаписьЭтапыПроизводства записьЭтапыПроизводства = new ЗаписьЭтапыПроизводства();
            записьЭтапыПроизводства.setCode();
            записьЭтапыПроизводства.setName(записьЭтапыПроизводстваFX.getName());
            записьЭтапыПроизводства.setDescription_stage(записьЭтапыПроизводстваFX.getDescription_stage());

            boolean result = DataAccessObject.save(записьЭтапыПроизводства);

            DataAccessObject.commitTransactionCloseSession();

            if (result) {
                записьЭтапыПроизводстваFX.setCode(записьЭтапыПроизводства.getCode());
                записьЭтапыПроизводстваFX.setId(записьЭтапыПроизводства.getId());
                prStagesList.add(записьЭтапыПроизводстваFX);
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
            ЗаписьЭтапыПроизводстваFX записьЭтапыПроизводстваFX = (ЗаписьЭтапыПроизводстваFX) objectFX;
            ЗаписьЭтапыПроизводства записьЭтапыПроизводства = new ЗаписьЭтапыПроизводства();
            записьЭтапыПроизводства.setCode();
            записьЭтапыПроизводства.setName(записьЭтапыПроизводстваFX.getName());
            записьЭтапыПроизводства.setDescription_stage(записьЭтапыПроизводстваFX.getDescription_stage());

            boolean result = DataAccessObject.save(записьЭтапыПроизводства);
            DataAccessObject.commitTransactionCloseSession();
            if (result) {
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
    public ObservableList<ЗаписьЭтапыПроизводстваFX> findText(String text) {
        Session newSession = DataAccessObject.get_session();
        this.prStagesList.clear();
        Query<ЗаписьЭтапыПроизводства> query = newSession.createQuery
                ("select st from ЗаписьЭтапыПроизводства st where st.name =: param " +
                        "or st.description_stage =: param");
        query.setParameter("param", text);
        List<ЗаписьЭтапыПроизводства> resultList = query.getResultList();

        for (int i = 0; i < resultList.size(); i++) {
            var res = resultList.get(i);
            ЗаписьЭтапыПроизводстваFX записьЭтапыПроизводстваFX = new ЗаписьЭтапыПроизводстваFX();
            записьЭтапыПроизводстваFX.setCode(res.getCode());
            записьЭтапыПроизводстваFX.setName(res.getName());
            записьЭтапыПроизводстваFX.setDescription_stage(res.getDescription_stage());
            записьЭтапыПроизводстваFX.setId(res.getId());

            this.prStagesList.add(записьЭтапыПроизводстваFX);
        }
        newSession.close();
        return prStagesList;
    }

    @Override
    public ObservableList<ЗаписьЭтапыПроизводстваFX> findInt(Integer value) {
        this.prStagesList.clear();
        ЗаписьЭтапыПроизводства search = ЗаписьЭтапыПроизводства.findObjectByValue("code", value);
        ЗаписьЭтапыПроизводстваFX result = new ЗаписьЭтапыПроизводстваFX();
        result.setId(search.getId());
        result.setCode(search.getCode());
        result.setName(search.getName());
        result.setDescription_stage(search.getDescription_stage());

        this.prStagesList.add(result);
        return prStagesList;
    }
}
