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
public class OptionLigneCommandePK implements Serializable {
    @Column(name = "numero_commande")
    private int numeroCommande;
    @Column(name = "numero_ligne_commande")
    private int numeroLigneCommande;
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "id_option"),
            @JoinColumn(name = "id_type_option")
    })
    private transient TypeOption typeOption;

}
