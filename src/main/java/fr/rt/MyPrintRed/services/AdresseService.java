package fr.rt.MyPrintRed.services;


import fr.rt.MyPrintRed.dto.AdresseDto;
import jakarta.transaction.Transactional;

/**
 * @author slimane
 * @Project auth
 */
public interface AdresseService extends AbstractService<AdresseDto>{


    Integer save(AdresseDto dto);

    @Transactional
    void addAdresseToUser(AdresseDto dto);

    @Transactional
    void updateAdress(Integer id, String rue, String complement, String codePostal, String ville);
}
