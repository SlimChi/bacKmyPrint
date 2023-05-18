package fr.rt.MyPrintRed.services;

import fr.rt.MyPrintRed.dto.PasswordDto;
import fr.rt.MyPrintRed.dto.UtilisateurDto;
import fr.rt.MyPrintRed.dto.InsertUtilisateurDto;
import fr.rt.MyPrintRed.entities.Utilisateur;
import fr.rt.MyPrintRed.request.AccountResponse;
import fr.rt.MyPrintRed.entities.NewPassword;
import fr.rt.MyPrintRed.entities.ResetPassword;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface UtilisateurService {


    List<UtilisateurDto> getUtilisateurs();

    @Transactional
    List<UtilisateurDto> findAll();

    UtilisateurDto getUtilisateurById(Integer idUtilisateur);

    UtilisateurDto updateUtilisateur(Integer idUtilisateur, InsertUtilisateurDto utilisateurDto);

    UtilisateurDto updateUtilisateurPassword(Integer idUtilisateur, PasswordDto passwordDto);

    Optional<Utilisateur> getUserByEmail(String email);

    AccountResponse checkEmail(ResetPassword resetPassword);

    AccountResponse resetPassword(NewPassword newPassword, String token);

    @Transactional
    void updateUser(Integer id, String firstName, String lastName, String email, String telephone);

    @Transactional
    void deleteUser(Integer userId);

    @Transactional
    void deleteUserAndAddresses(Integer userId);
}
