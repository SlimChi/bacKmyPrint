package fr.rt.MyPrintRed.services.impl;

import fr.rt.MyPrintRed.repositories.PasswordTokenRepository;
import fr.rt.MyPrintRed.entities.PasswordToken;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author slimane
 * @Project EmailApi
 */
@Service
@AllArgsConstructor
public class PasswordTokenServiceImpl {

    private final PasswordTokenRepository passwordTokenRepository;

    public void savePasswordToken(PasswordToken token) {
        passwordTokenRepository.save(token);
    }

    public Optional<PasswordToken> getToken(String token) {
        return passwordTokenRepository.findByToken(token);
    }

    public void deleteToken(PasswordToken token) {
        passwordTokenRepository.delete(token);
    }

}
