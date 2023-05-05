package fr.rt.MyPrintRed.repositories;

import fr.rt.MyPrintRed.entities.LigneCommande;
import fr.rt.MyPrintRed.entities.LigneCommandePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LigneCommandeRepository extends JpaRepository<LigneCommande, LigneCommandePK> {


    @Query(value = "select max(lc.ligneCommandePK.numeroLigneCommande) from LigneCommande lc where lc.ligneCommandePK.numeroCommande = :#{#numeroCommande}")
    Optional<Integer> getMaxId(@Param("numeroCommande") Integer numeroCommande);

    @Query(value = "select lc from LigneCommande lc where lc.ligneCommandePK.numeroCommande = :#{#numeroCommande}")
    List<LigneCommande> getAllByNumeroCommande(@Param("numeroCommande") Integer numeroCommande);

}
