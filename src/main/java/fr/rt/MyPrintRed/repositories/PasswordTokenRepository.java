package fr.rt.MyPrintRed.repositories;

import fr.rt.MyPrintRed.entities.PasswordToken;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author slim
 * @Project
 */
@Repository
@Transactional
public interface PasswordTokenRepository extends JpaRepository<PasswordToken, Long> {
    Optional<PasswordToken> findByToken(String token);
    void delete(PasswordToken token);
}
