package fr.rt.MyPrintRed.repositories;

import fr.rt.MyPrintRed.entities.Utilisateur;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur,Integer> {

    Optional<Utilisateur> findUserByEmail(String email);
    Optional<Utilisateur> findById(Integer id);

    @Transactional
    @Modifying
    @Query("UPDATE Utilisateur a " +
            "SET a.active = TRUE WHERE a.email = ?1")
    int enableAppUser(String email);

    boolean existsByEmail(String email);
}
