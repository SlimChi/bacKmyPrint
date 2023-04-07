package fr.rt.MyPrintRed.repositories;


import fr.rt.MyPrintRed.entities.Fichier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FichierRepository extends JpaRepository<Fichier,Integer> {
}
