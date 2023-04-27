package fr.rt.MyPrintRed.services.impl;

import fr.rt.MyPrintRed.dto.CategorieDto;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CategorieServiceImplTest {

    @Autowired
    private CategorieServiceImpl categorieService;

    private static  Integer id;
    @Test
    @Order(1)
    void insert() {

        CategorieDto categorieDto = CategorieDto.builder()
                .idCategorie(0)
                .libelle("TEST CATEGORIE")
                .build();

        CategorieDto savedCategorie = categorieService.insert(categorieDto);
        this.id = savedCategorie.getIdCategorie();
        assertNotNull(savedCategorie);
        assertEquals("TEST CATEGORIE",savedCategorie.getLibelle());
    }

    @Test
    @Order(3)
    void update() {

        CategorieDto categorieDto = categorieService.getById(this.id);
        categorieDto.setLibelle("TEST UPDATE");
        CategorieDto updateCategorie = categorieService.update(this.id,categorieDto);

        assertNotNull(updateCategorie);
        assertEquals("TEST UPDATE",updateCategorie.getLibelle());
    }

    @Test
    void getCategories() {
    }

    @Test
    @Order(2)
    void getById() {

        assertNotNull(categorieService.getById(this.id));
        assertEquals("TEST CATEGORIE",categorieService.getById(this.id).getLibelle());
    }


}