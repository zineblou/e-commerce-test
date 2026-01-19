package controller;

import entity.Categorie;
import org.springframework.web.bind.annotation.*;
import service.CategorieService;

@RestController
@RequestMapping("api/categories")
public class CategorieController {

    private final CategorieService categorieService;

    public CategorieController(CategorieService categorieService){
        this.categorieService = categorieService;
    }

    @PostMapping
    public Categorie addCategorie(@RequestBody Categorie categorie){
        return categorieService.ajouterCategorie(categorie);
    }

    @PostMapping("/{categoryId}")
    public Categorie updateCategorie(@PathVariable Long categoryId, @RequestBody Categorie categorie){
        return categorieService.modifierCategorie(categoryId, categorie);
    }

    @DeleteMapping("/id")
    public void delete(@PathVariable Long id){
        categorieService.supprimerCategorie(id);
    }
}

