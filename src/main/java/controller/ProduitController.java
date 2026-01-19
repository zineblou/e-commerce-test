package controller;

import entity.Categorie;
import entity.Produit;
import org.springframework.web.bind.annotation.*;
import service.ProduitService;

@RestController
@RequestMapping("api/produits")
public class ProduitController {

    private final ProduitService produitService;

    ProduitController(ProduitService produitService){
        this.produitService = produitService;
    }

    public Produit addProduit(@RequestBody Produit produit,  @RequestParam Long categorieId){
        return produitService.ajouterProduit(produit,categorieId);
    }

    @PostMapping("/id}")
    public Produit updateProduit(@PathVariable Long produitId,
                                     @RequestBody Produit newProduct,
                                     @RequestParam Long categorieId){
        return produitService.updateProduit(produitId, newProduct,categorieId);
    }

    @DeleteMapping("/id")
    public void delete(@PathVariable Long id){
        produitService.supprimerProduit(id);
    }


}
