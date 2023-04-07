package fr.rt.MyPrintRed.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import java.util.Arrays;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "fichier", schema = "public", catalog = "MYPRINTTEST")

public class Fichier {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_fichier")
    private int idFichier;
    @Basic
    @Column(name = "nom")
    private String nom;
    @Basic
    @Column(name = "type_fichier")
    private String typeFichier;


    @Column(name = "data_fichier")
    private byte[] dataFichier;


    public Fichier(Integer idFichier) {
        this.idFichier = idFichier;
    }
}
