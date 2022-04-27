package com.stuart.models.entity.справочники;

import com.stuart.dao.записьБД.DataAccessObject;
import com.stuart.models.entity.ЗаписьБД;
import com.stuart.models.entity.документы.закупка.ЗаписьТЧ_Закупка;
import com.stuart.models.entity.документы.продажа.ЗаписьТЧСписокТоваров;
import com.stuart.models.entity.документы.производство.ЗаписьТЧПроизведеноПродукции;
import com.stuart.models.entity.документы.производство.ЗаписьТЧРасходМатериалов;
import com.stuart.models.entity.регистры.ЗаписьРегистраТоварыНаСкладах;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "nomenclature", schema = "study_db")
public class ЗаписьНоменклатура extends ЭлементСправочника {

    public static ЗаписьНоменклатура findObjectByValue(String fieldName, Object fieldValue) {
        return (ЗаписьНоменклатура) DataAccessObject.findObjectByValue(getType(), fieldName, fieldValue);
    }

    public static List<ЗаписьНоменклатура> findObjectsByValue(String fieldName, Object fieldValue){
        List<ЗаписьНоменклатура> Result = new ArrayList<ЗаписьНоменклатура>();
        List<ЗаписьБД> Записи = DataAccessObject.findObjectsByValue(getType(), fieldName, fieldValue);
        for(var Запись:Записи) Result.add((ЗаписьНоменклатура)Запись);
        return Result;
    }

    public static String getType() {
        return "ЗаписьНоменклатура";
    }

    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID id = UUID.randomUUID();
    private Integer code;
    private String name;
    private Integer article_number;
    private String category;
    private String subcategory;
    @ManyToOne
    @JoinColumn(name = "contragent_id", referencedColumnName = "id")
    private ЗаписьКонтрагент contragent_;//  ЗАПИСЬ_НОМЕНКЛАТУРА-ЗАПИСЬ_КОНТРАГЕНТ связь с классом "ЗаписьКонтрагент" (таблица БД "contragent")
                                     //(таблица БД " nomenclature "-"contragent") list nomenclaturies_

    @OneToMany(mappedBy = "nomenclature_", fetch = FetchType.LAZY)
    private List<ЗаписьТЧ_Закупка> table_part_purchase_; //НОМЕНКЛАТУРА-ТЧ_ЗАКУПКА связь с классом ЗаписьТЧ_Закупка
                                                        // (таблица БД "nomenclature"-"table_part_purchase")
    @OneToMany(mappedBy = "nomenclature_", fetch = FetchType.LAZY)
    private List<ЗаписьТЧСписокТоваров> table_part_ListOfProducts_; //НОМЕНКЛАТУРА-ТЧ_СПИСОКТОВАРОВ  связь с классом ЗаписьТЧ_списокТоваров
                                                                    //(таблица БД "nomenclature"-table_part_list_of_products")
    @OneToMany(mappedBy = "nomenclature_", fetch = FetchType.LAZY)
    private List<ЗаписьТЧПроизведеноПродукции> table_part_produced_of_products_; //НОМЕНКЛАТУРА-ТЧ_ПРОИЗВЕДЕНОПРОДУЦИИ  связь с классом ЗаписьТЧ_ПроизведеноПродукции
                                                                                //(таблица БД "nomenclature"-table_part_produced_of_products")
    @OneToMany(mappedBy = "nomenclature_", fetch = FetchType.LAZY)
    private List<ЗаписьТЧРасходМатериалов> table_part_material_consuption_;//НОМЕНКЛАТУРА-ТЧ_РАСХОД_МАТЕРИАЛОВ  связь с классом ЗаписьТЧРасходМатериалов
                                                                            //(таблица БД "nomenclature"-table_part_material_consuption")
    @OneToMany(mappedBy = "nomenclature_", fetch = FetchType.LAZY)
    private List<ЗаписьРегистраТоварыНаСкладах> register_products_in_stock_;

    @Override
    public boolean ПередЗаписью() {
        if ( this.getCode() == null
                || this.getName() == null || this.getArticle_number() == 0
                || this.getCategory() == null || this.getSubcategory() == null)
            return false;
        else
            return true;
    }
}
