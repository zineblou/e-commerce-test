package service;

import entity.Categorie;
import org.springframework.stereotype.Service;
import repository.CategorieRepository;

import java.util.Optional;

@Service
public class CategorieService {

    private final CategorieRepository categorieRepository;

    public  CategorieService(CategorieRepository categorieRepository){
        this.categorieRepository = categorieRepository;
    }

    public Categorie ajouterCategorie(Categorie categorie){
        return this.categorieRepository.save(categorie);
    }

    public Categorie modifierCategorie(Long id, Categorie updatedCategorie){

       Optional<Categorie> categorieOptional = categorieRepository.findById(id);
       if(categorieOptional.isPresent()){
          Categorie categorie = categorieOptional.get();
          categorie.setNom(updatedCategorie.getNom());
          categorie.setDescription(updatedCategorie.getDescription());
          return categorieRepository.save(categorie);
       }

       return null;
    }

    public  void supprimerCategorie(Long id){
        categorieRepository.deleteById(id);
    }
}
