package fr.rt.MyPrintRed.services.password;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author slimane
 * @Project EmailApi
 */
@Service
@AllArgsConstructor
public class PasswordTokenService {

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
