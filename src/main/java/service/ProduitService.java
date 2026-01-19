package service;

import entity.Categorie;
import entity.Produit;
import repository.CategorieRepository;
import repository.ProduitRepository;

import java.util.Optional;

public class ProduitService {

    private final ProduitRepository produitRepository;

    private final CategorieRepository categorieRepository;

    public ProduitService(ProduitRepository produitRepository,
                          CategorieRepository categorieRepository){

        this.produitRepository = produitRepository;
        this.categorieRepository = categorieRepository;
    }

    public Produit ajouterProduit(Produit produit, Long categorieId){
        Optional<Categorie> categorie = categorieRepository.findById(categorieId);

        if(categorie.isPresent()){
            produit.setCategorie(categorie.get());
            return produitRepository.save(produit);
        } else {
            return null;
        }
    }

    public Produit updateProduit(Long produitId,
                                 Produit nouveauProduit,
                                 Long categorieId){

        Optional<Produit> produitOptional = produitRepository.findById(produitId);

        if(produitOptional.isEmpty()) {
            return null;
        }

        Produit produit = produitOptional.get();
        produit.setNom(nouveauProduit.getNom());
        produit.setPrix(nouveauProduit.getPrix());
        produit.setQuantityEnStock(nouveauProduit.getQuantityEnStock());

        if (categorieId != null) {
            categorieRepository.findById(categorieId).ifPresent(produit::setCategorie);
        }

        return produitRepository.save(produit);
    }

    public Produit addProductToCategorie(Long produitId, Long categorieId){
        Optional<Produit> produitOptional = produitRepository.findById(produitId);
        Optional<Categorie> categorieOptional = categorieRepository.findById(categorieId);

        if( produitOptional.isEmpty() || categorieOptional.isEmpty()){
            return null;
        }

        Produit produit = produitOptional.get();
        Categorie categorie = categorieOptional.get();

        produit.setCategorie(categorie);

        return produitRepository.save(produit);
    }

    public Produit removeProductFromCategorie(Long produitId){
        Optional<Produit> produitOptional = produitRepository.findById(produitId);
        if( produitOptional.isEmpty() ){
            return null;
        }

        Produit produit = produitOptional.get();
        produit.setCategorie(null);

        return produitRepository.save(produit);
    }

    public void supprimerProduit(Long produitId){
        produitRepository.deleteById(produitId);

    }
}
