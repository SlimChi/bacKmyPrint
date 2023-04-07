package fr.rt.MyPrintRed.services.impl;

import fr.rt.MyPrintRed.dto.PasswordDto;
import fr.rt.MyPrintRed.dto.UtilisateurDto;
import fr.rt.MyPrintRed.dto.InsertUtilisateurDto;
import fr.rt.MyPrintRed.entities.Adresse;
import fr.rt.MyPrintRed.entities.Utilisateur;
import fr.rt.MyPrintRed.mapper.UtilisateurMapper;
import fr.rt.MyPrintRed.repositories.AdresseRepository;
import fr.rt.MyPrintRed.repositories.UtilisateurRepository;
import fr.rt.MyPrintRed.request.AccountResponse;
import fr.rt.MyPrintRed.services.UtilisateurService;
import fr.rt.MyPrintRed.services.emailService.EmailSendService;
import fr.rt.MyPrintRed.services.password.NewPassword;
import fr.rt.MyPrintRed.services.password.PasswordToken;
import fr.rt.MyPrintRed.services.password.PasswordTokenService;
import fr.rt.MyPrintRed.services.password.ResetPassword;
import fr.rt.MyPrintRed.validators.ObjectsValidator;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UtilisateurServiceImpl implements UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;


    @Autowired
    private Validator validator;
    @Autowired
    private ObjectsValidator validate;
    @Autowired
    private EmailSendService emailSendService;
    @Autowired
    private PasswordTokenService passwordTokenService;

    @Autowired
    private final UtilisateurMapper utilisateurMapper;

    private final PasswordEncoder passwordEncoder;
    private final AdresseRepository adresseRepository;

    @Override
    public List<UtilisateurDto> getUtilisateurs() {
        return utilisateurMapper.toListDto(utilisateurRepository.findAll());
    }

    @Override
    @Transactional
    public List<UtilisateurDto> findAll() {
        return utilisateurRepository.findAll()
                .stream().map(utilisateurMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UtilisateurDto getUtilisateurById(Integer idUtilisateur) {

        Optional<Utilisateur> utilisateur = utilisateurRepository.findById(idUtilisateur);

        return utilisateurMapper.toDto(utilisateur.orElseThrow());
    }

    @Override
    public UtilisateurDto updateUtilisateur(Integer idUtilisateur, InsertUtilisateurDto utilisateurDto) {

        Utilisateur utilisateur = utilisateurRepository.findById(idUtilisateur).orElseThrow();

        utilisateur.setNom(utilisateurDto.getNom());
        utilisateur.setPrenom(utilisateurDto.getPrenom());
        utilisateur.setEmail(utilisateurDto.getEmail());
        utilisateur.setTelephone(utilisateurDto.getTelephone());



        return utilisateurMapper.toDto(utilisateurRepository.save(utilisateur));

    }

    @Override
    public UtilisateurDto updateUtilisateurPassword(Integer idUtilisateur, PasswordDto passwordDto) {
        Utilisateur utilisateur = utilisateurRepository.findById(idUtilisateur).orElseThrow();

        utilisateur.setPassword(passwordEncoder.encode(passwordDto.getPassword()));



        return utilisateurMapper.toDto(utilisateurRepository.save(utilisateur));
    }


    public int enableAppUser(String email) {
        return utilisateurRepository.enableAppUser(email);
    }

    @Override
    public Optional<Utilisateur> getUserByEmail(String email) {
        return this.utilisateurRepository.findUserByEmail(email);
    }

    @Override
    public AccountResponse checkEmail(ResetPassword resetPassword) {
        validate.validate(resetPassword);
        var user = utilisateurRepository.findUserByEmail(resetPassword.getEmail())
                .orElseThrow();
        AccountResponse accountResponse = new AccountResponse();
        Set<ConstraintViolation<ResetPassword>> violations = validator.validate(resetPassword);
        List<String> errors = new ArrayList<>();
        for (ConstraintViolation<ResetPassword> violation : violations) {
            errors.add(violation.getMessage());
        }

        if (user != null && errors.isEmpty()) {
            String token = UUID.randomUUID().toString();
            PasswordToken passwordToken = new PasswordToken(token, user);
            String link = "http://localhost:4200/reset-password?token=" + token;
            emailSendService.sendEmail(resetPassword.getEmail(), buildEmail(link), "Reset you password");
            passwordTokenService.savePasswordToken(passwordToken);
            accountResponse.setResult("Email envoyé");
        } else {
            accountResponse.setResult("Échec d'envoi");
        }
        return accountResponse;
    }

    private String buildEmail(String link) {
        return "<div style=\"background-color:transparent;\">\n" +
                "\t\t\t\t\t\t<div class=\"block-grid \" style=\"min-width: 320px; max-width: 640px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; Margin: 0 auto; background-color: #fff;\">\n" +
                "\t\t\t\t\t\t\t<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:#fff;\">\n" +
                "\t\t\t\t\t\t\t\t<!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"background-color:transparent;\"><tr><td align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:640px\"><tr class=\"layout-full-width\" style=\"background-color:#fff\"><![endif]-->\n" +
                "\t\t\t\t\t\t\t\t<!--[if (mso)|(IE)]><td align=\"center\" width=\"640\" style=\"background-color:#fff;width:640px; border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent;\" valign=\"top\"><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 0px; padding-left: 0px; padding-top:0px; padding-bottom:0px;\"><![endif]-->\n" +
                "\t\t\t\t\t\t\t\t<div class=\"col num12\" style=\"min-width: 320px; max-width: 640px; display: table-cell; vertical-align: top; width: 640px;\">\n" +
                "\t\t\t\t\t\t\t\t\t<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                "\t\t\t\t\t\t\t\t\t\t<!--[if (!mso)&(!IE)]><!-->\n" +
                "\t\t\t\t\t\t\t\t\t\t<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:0px; padding-bottom:0px; padding-right: 0px; padding-left: 0px;\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<!--<![endif]-->\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<div class=\"img-container center autowidth\" align=\"center\" style=\"padding-right: 0px;padding-left: 0px;\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t<!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr style=\"line-height:0px\"><td style=\"padding-right: 0px;padding-left: 0px;\" align=\"center\"><![endif]--><a href=\""+ link +" \" target=\"_blank\" style=\"outline:none\" tabindex=\"-1\"><img class=\"center autowidth\" align=\"center\" border=\"0\" src=\"https://d1oco4z2z1fhwp.cloudfront.net/templates/default/4036/___passwordreset.gif\" alt=\"Image of lock &amp; key.\" title=\"Image of lock &amp; key.\" style=\"text-decoration: none; -ms-interpolation-mode: bicubic; height: auto; border: 0; width: 100%; max-width: 640px; display: block;\" width=\"640\"></a>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t<!--[if mso]></td></tr></table><![endif]-->\n" +
                "\t\t\t\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<table class=\"divider\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" role=\"presentation\" valign=\"top\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t<tbody>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t<td class=\"divider_inner\" style=\"word-break: break-word; vertical-align: top; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; padding-top: 30px; padding-right: 0px; padding-bottom: 0px; padding-left: 0px;\" valign=\"top\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<table class=\"divider_content\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-top: 0px solid #BBBBBB; width: 100%;\" align=\"center\" role=\"presentation\" valign=\"top\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<tbody>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<td style=\"word-break: break-word; vertical-align: top; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\"><span></span></td>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</tbody>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</table>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t</tbody>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t</table>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 40px; padding-left: 40px; padding-top: 10px; padding-bottom: 10px; font-family: Arial, sans-serif\"><![endif]-->\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<div style=\"color:#555555;font-family:'Helvetica Neue', Helvetica, Arial, sans-serif;line-height:1.2;padding-top:10px;padding-right:40px;padding-bottom:10px;padding-left:40px;\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t<div class=\"txtTinyMce-wrapper\" style=\"line-height: 1.2; font-size: 12px; font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; color: #555555; mso-line-height-alt: 14px;\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t<p style=\"margin: 0; font-size: 30px; line-height: 1.2; text-align: center; word-break: break-word; mso-line-height-alt: 36px; margin-top: 0; margin-bottom: 0;\"><span style=\"font-size: 30px; color: #2b303a;\"><strong>Forgot Your Password?</strong></span></p>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<!--[if mso]></td></tr></table><![endif]-->\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 40px; padding-left: 40px; padding-top: 10px; padding-bottom: 10px; font-family: Tahoma, sans-serif\"><![endif]-->\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<div style=\"color:#555555;font-family:Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;line-height:1.5;padding-top:10px;padding-right:40px;padding-bottom:10px;padding-left:40px;\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t<div class=\"txtTinyMce-wrapper\" style=\"line-height: 1.5; font-size: 12px; font-family: Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif; color: #555555; mso-line-height-alt: 18px;\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t<p style=\"margin: 0; font-size: 15px; line-height: 1.5; text-align: center; word-break: break-word; font-family: Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif; mso-line-height-alt: 23px; margin-top: 0; margin-bottom: 0;\"><span style=\"color: #808389; font-size: 15px;\">You recently requested to reset your password for your account. Use the button below to reset it. This password reset is only valid for the next 24 hours..</span></p>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<!--[if mso]></td></tr></table><![endif]-->\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<div class=\"button-container\" align=\"center\" style=\"padding-top:15px;padding-right:10px;padding-bottom:0px;padding-left:10px;\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t<!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-spacing: 0; border-collapse: collapse; mso-table-lspace:0pt; mso-table-rspace:0pt;\"><tr><td style=\"padding-top: 15px; padding-right: 10px; padding-bottom: 0px; padding-left: 10px\" align=\"center\"><v:roundrect xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:w=\"urn:schemas-microsoft-com:office:word\"style=\"height:46.5pt;width:201.75pt;v-text-anchor:middle;\" arcsize=\"57%\" stroke=\"false\" fillcolor=\"#f7a50c\"><w:anchorlock/><v:textbox inset=\"0,0,0,0\"><center style=\"color:#ffffff; font-family:Arial, sans-serif; font-size:16px\"><![endif]--><a href=\""+ link +"\" target=\"_blank\" style=\"-webkit-text-size-adjust: none; text-decoration: none; display: inline-block; color: #ffffff; background-color: #f7a50c; border-radius: 35px; -webkit-border-radius: 35px; -moz-border-radius: 35px; width: auto; width: auto; border-top: 1px solid #f7a50c; border-right: 1px solid #f7a50c; border-bottom: 1px solid #f7a50c; border-left: 1px solid #f7a50c; padding-top: 15px; padding-bottom: 15px; font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; text-align: center; mso-border-alt: none; word-break: keep-all;\"><span style=\"padding-left:30px;padding-right:30px;font-size:16px;display:inline-block;letter-spacing:undefined;\"><span style=\"font-size: 16px; margin: 0; line-height: 2; word-break: break-word; mso-line-height-alt: 32px;\"><strong>RESET PASSWORD</strong></span></span></a>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t<!--[if mso]></center></v:textbox></v:roundrect></td></tr></table><![endif]-->\n" +
                "\t\t\t\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<table class=\"divider\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" role=\"presentation\" valign=\"top\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t<tbody>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t<td class=\"divider_inner\" style=\"word-break: break-word; vertical-align: top; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; padding-top: 60px; padding-right: 0px; padding-bottom: 12px; padding-left: 0px;\" valign=\"top\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<table class=\"divider_content\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-top: 0px solid #BBBBBB; width: 100%;\" align=\"center\" role=\"presentation\" valign=\"top\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<tbody>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<td style=\"word-break: break-word; vertical-align: top; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\"><span></span></td>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</tbody>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</table>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t</tbody>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t</table>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<!--[if (!mso)&(!IE)]><!-->\n" +
                "\t\t\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t\t\t<!--<![endif]-->\n" +
                "\t\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t<!--[if (mso)|(IE)]></td></tr></table><![endif]-->\n" +
                "\t\t\t\t\t\t\t\t<!--[if (mso)|(IE)]></td></tr></table></td></tr></table><![endif]-->\n" +
                "\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t</div>";
    }

    @Override
    public AccountResponse resetPassword(NewPassword newPassword, String token) {
        var user = utilisateurRepository.findUserByEmail(newPassword.getEmail())
                .orElseThrow();

        AccountResponse accountResponse = new AccountResponse();

        if (passwordTokenService.getToken(token).isPresent() && passwordTokenService.getToken(token).get().getToken().equals(newPassword.getTokenPassword())) {
            user.setPassword(passwordEncoder.encode(newPassword.getPassword()));
            utilisateurRepository.save(user);
            passwordTokenService.deleteToken(passwordTokenService.getToken(token).get());
            accountResponse.setResult("Mot de passe modifié avec succès");
        } else {
            accountResponse.setResult("Token non valide");
        }

        return accountResponse;
    }

    @Override
    @Transactional
    public void updateUser(Integer id, String firstName, String lastName, String email, String telephone) {

        Utilisateur updateUser = utilisateurRepository.findById(id).orElseThrow();

        updateUser.setIdUtilisateur(id);
        updateUser.setEmail(email);
        updateUser.setNom(firstName);
        updateUser.setPrenom(lastName);
        updateUser.setTelephone(telephone);

    }

    @Override
    @Transactional
    public void deleteUser(Integer userId) {
        // Find the user by ID
        Utilisateur user = utilisateurRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        // Delete the user
        utilisateurRepository.delete(user);
    }

    @Override
    @Transactional
    public void deleteUserAndAddresses(Integer userId) {
        // Find the user by ID
        Utilisateur user = utilisateurRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        // Delete all the user's addresses
        for (Adresse adresse : user.getAdresse()) {
            adresseRepository.delete(adresse);
        }

        // Delete the user
        utilisateurRepository.delete(user);
    }
}
