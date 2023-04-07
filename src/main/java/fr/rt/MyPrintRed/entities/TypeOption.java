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
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "type_option", schema = "public", catalog = "MYPRINTTEST")
public class TypeOption {

    @EmbeddedId
    private TypeOptionPK typeOptionPK;

    @Basic
    @Column(name = "libelle")
    private String libelle;
    @Basic
    @Column(name = "prix")
    private BigDecimal prix;

}
