package fr.rt.MyPrintRed.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "option_ligne_commande", schema = "public", catalog = "MYPRINTTEST")
public class OptionLigneCommande {

    @EmbeddedId
    private OptionLigneCommandePK optionLigneCommandePK;

}
