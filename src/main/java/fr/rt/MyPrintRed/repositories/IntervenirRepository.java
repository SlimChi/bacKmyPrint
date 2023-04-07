package fr.rt.MyPrintRed.repositories;

import fr.rt.MyPrintRed.entities.Intervenir;
import fr.rt.MyPrintRed.entities.IntervenirPK;
import fr.rt.MyPrintRed.entities.LigneCommande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IntervenirRepository extends JpaRepository<Intervenir, IntervenirPK> {

    //@Query(value = "select lc from LigneCommande lc where lc.ligneCommandePK.numeroCommande = :#{#numeroCommande}")
   // List<LigneCommande> getAllByNumeroCommande(@Param("numeroCommande") Integer numeroCommande);

    @Query(value = "select  i from Intervenir i where i.intervenirPK.numeroCommande = :#{#numeroCommande}")
    List<Intervenir> getAllByNumeroCommande(@Param("numeroCommande")Integer numeroCommande);
}
