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
public class OptionCategoriePK implements Serializable {

    @Column(name = "id_categorie")
    private int idCategorie;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "id_option"),
            @JoinColumn(name = "id_type_option")
    })
    private TypeOption typeOption;


}
