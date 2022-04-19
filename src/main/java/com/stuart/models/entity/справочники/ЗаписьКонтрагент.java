package com.stuart.models.entity.справочники;

import com.stuart.models.entity.ЗаписьБД;
import lombok.*;

import javax.persistence.*;
import javax.persistence.Table;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity

@Table(name = "contragent", schema = "study_db")
public class ЗаписьКонтрагент extends ЭлементСправочника {

    @Id
    @GeneratedValue
    private UUID id;
    private Integer code;
    private String name;
    private String contact_person;
    private String address;
    private String type_KA; //поставщик/покупатель
//    @OneToMany(mappedBy = "contragent", fetch = FetchType.LAZY)
//    private List<Закупка> purchasesDoc;
    @OneToMany(mappedBy = "contragent_", fetch = FetchType.LAZY)
    private List<ЗаписьНоменклатура> nomenclaturies_; //  ЗАПИСЬ_КОНТРАГЕНТ-ЗАПИСЬ_НОМЕНКЛАТУРА связь с классом "ЗаписьНоменклатура" (таблица БД "nomenclature")
                                                    //(таблица БД "contragent"-"nomenclature") list nomenclaturies_

    @Override
    public boolean ПередЗаписью() {
        if (this.getCode() == null
                || this.getName() == null || this.getContact_person() == null
                || this.getType_KA()==null)
            return false;
        else
            return true;
    }

    @Override
    public String toString() {
        return "ЗаписьКонтрагент{" +
                "Код=" + code +
                ", Наименование='" + name + '\'' +
                ", КонтактноеЛицоКА='" + contact_person + '\'' +
                ", АдресКА='" + address + '\'' +
                ", ТипКонтрагента='" + type_KA + '\'' +
                '}';
    }

    @Override
    public ЗаписьБД findByValueDAO(String fieldName, Object fieldValue) {
        return super.findByValueDAO(fieldName, fieldValue);
    }


}
