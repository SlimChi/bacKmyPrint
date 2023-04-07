package fr.rt.MyPrintRed.repositories;



import fr.rt.MyPrintRed.entities.Adresse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdresseRepository extends JpaRepository<Adresse,Integer> {

}
