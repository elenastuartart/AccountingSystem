package com.stuart.interfaces.impls;

import com.stuart.interfaces.ISpravochnik;
import com.stuart.models.entity.ЗаписьБД;
import com.stuart.models.entity.справочники.ЗаписьКонтрагент;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CollectionISpravochnikKA implements ISpravochnik {

    private ObservableList<ЗаписьКонтрагент> списокКонтрагентов = FXCollections.observableArrayList();

    @Override
    public void add(ЗаписьБД записьБД) {
        списокКонтрагентов.add((ЗаписьКонтрагент) записьБД);
    }

    @Override
    public void update(ЗаписьБД записьБД) {

    }

    public ObservableList<ЗаписьКонтрагент> getСписокКонтрагентов() {
        return списокКонтрагентов;
    }

    public void print() {
        int number = 0;
        System.out.println();
        for(ЗаписьКонтрагент записьКонтрагент: списокКонтрагентов) {
            number++;
            System.out.println(number + записьКонтрагент.getCode() + записьКонтрагент.getName()+
                    записьКонтрагент.getAddress() + записьКонтрагент.getContact_person() + записьКонтрагент.getType_KA());
        }
    }

    public void fillTestData() {
        списокКонтрагентов.add
                (new ЗаписьКонтрагент("ТестКА1", "test1@mail.ru", "Москва1", "продавец1"));
        списокКонтрагентов.add
                (new ЗаписьКонтрагент("ТестКА2", "test2@mail.ru", "Москва2", "продавец2"));
        списокКонтрагентов.add
                (new ЗаписьКонтрагент("ТестКА3", "test3@mail.ru", "Москва3", "продавец3"));
        списокКонтрагентов.add
                (new ЗаписьКонтрагент("ТестКА4", "test4@mail.ru", "Москва4", "продавец4"));
        списокКонтрагентов.add
                (new ЗаписьКонтрагент("ТестКА5", "test5@mail.ru", "Москва5", "продавец5"));
        списокКонтрагентов.add
                (new ЗаписьКонтрагент("ТестКА6", "test6@mail.ru", "Москва6", "продавец6"));
        списокКонтрагентов.add
                (new ЗаписьКонтрагент("ТестКА7", "test7@mail.ru", "Москва7", "продавец7"));

    }
}
