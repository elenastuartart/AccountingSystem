package com.stuart.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ЭлементСправочника {
    @Id
    @GeneratedValue
    private UUID ПервичныйКлюч;
    private Integer Код;
    private String Наименование;
    private boolean ПризнакУдаления;


}
