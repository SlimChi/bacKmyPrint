package fr.rt.MyPrintRed.repositories;

import fr.rt.MyPrintRed.entities.OptionCategorie;
import fr.rt.MyPrintRed.entities.OptionCategoriePK;
import fr.rt.MyPrintRed.entities.TypeOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OptionCategorieRepository extends JpaRepository<OptionCategorie, OptionCategoriePK> {
    @Query(value = "select oc from OptionCategorie oc where oc.optionCategoriePK.idCategorie = :#{#idCategorie} ")
    List<OptionCategorie> getAllByIdCategorie(@Param("idCategorie")Integer idCategorie);

    void deleteAllByOptionCategoriePK_IdCategorie(Integer idCategorie);

}
