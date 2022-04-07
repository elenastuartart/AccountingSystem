package com.stuart.models.entity.документы;

import com.stuart.models.entity.ЗаписьБД;
import com.stuart.models.entity.документы.продажа.ЗаписьТЧСписокТоваров;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Документ extends ЗаписьБД {

    public boolean ПометкаПроведения;

    @Override
    public boolean ПередЗаписью() {
        return super.ПередЗаписью();
    }

}
