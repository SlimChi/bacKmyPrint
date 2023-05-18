package fr.rt.MyPrintRed.services.impl;

import fr.rt.MyPrintRed.repositories.ConfirmationTokenRepository;
import fr.rt.MyPrintRed.entities.ConfirmationToken;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author slimane
 * @Project EmailApi
 */
@Service
@AllArgsConstructor
public class ConfirmationTokenServiceImpl {

    private final ConfirmationTokenRepository confirmationTokenRepository;

    public void saveConfirmationToken(ConfirmationToken token) {
        confirmationTokenRepository.save(token);
    }

    public Optional<ConfirmationToken> getToken(String token) {
        return confirmationTokenRepository.findByToken(token);
    }

    public int setConfirmedAt(String token) {
        return confirmationTokenRepository.updateConfirmedAt(
                token, LocalDateTime.now());
    }

    public void deleteToken(String token) {
        confirmationTokenRepository.deleteByToken(token);
    }
}
