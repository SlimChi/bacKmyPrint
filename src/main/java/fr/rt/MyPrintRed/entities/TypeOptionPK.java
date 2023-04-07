package fr.rt.MyPrintRed.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class TypeOptionPK implements Serializable {

    @Column(name = "id_option")
    private int idOption;

    @Column(name = "id_type_option")
    private int idTypeOption;


}
