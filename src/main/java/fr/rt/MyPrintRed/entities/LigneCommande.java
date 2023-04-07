package fr.rt.MyPrintRed.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Objects;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ligne_commande", schema = "public", catalog = "MYPRINTTEST")
public class LigneCommande {

    @EmbeddedId
    private LigneCommandePK ligneCommandePK;


    @Basic
    @Column(name = "recto_verso")
    private boolean rectoVerso;
    @Basic
    @Column(name = "couleur")
    private boolean couleur;
    @Basic
    @Column(name = "nombre_exemplaire")
    private int nombreExemplaire;
    @Basic
    @Column(name = "nombre_feuille")
    private int nombreFeuille;
    @Basic
    @Column(name = "prix_ligne_commande")
    private BigDecimal prixLigneCommande;
    @ManyToOne
    @JoinColumn(name = "id_status")
    private Status status;
    @ManyToOne
    @JoinColumn(name = "id_fichier")
    private Fichier fichier;


}
