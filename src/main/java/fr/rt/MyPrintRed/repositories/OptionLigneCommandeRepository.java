package fr.rt.MyPrintRed.repositories;

import fr.rt.MyPrintRed.entities.OptionLigneCommande;
import fr.rt.MyPrintRed.entities.OptionLigneCommandePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OptionLigneCommandeRepository extends JpaRepository<OptionLigneCommande, OptionLigneCommandePK> {

    @Query(value = "select olc from OptionLigneCommande  olc where olc.optionLigneCommandePK.numeroCommande = :#{#numeroCommande} " +
            "and olc.optionLigneCommandePK.numeroLigneCommande = :#{#numeroLigneCommande}")
    List<OptionLigneCommande> getAllByNumeros(@Param("numeroCommande")Integer numeroCommande,
                                              @Param("numeroLigneCommande")Integer numeroLigneCommande);

    @Query(value ="delete from OptionLigneCommande olc where olc.optionLigneCommandePK.numeroCommande = :#{#numeroCommande} " +
            "and olc.optionLigneCommandePK.numeroLigneCommande = :#{#numeroLigneCommande}")
    void deleteAllByNumeros(@Param("numeroCommande")Integer numeroCommande,
                            @Param("numeroLigneCommande")Integer numeroLigneCommande);

    void deleteByOptionLigneCommandePK_NumeroCommandeAndAndOptionLigneCommandePK_NumeroLigneCommande(Integer numeroCommande,Integer numeroLigneCommande);
}
