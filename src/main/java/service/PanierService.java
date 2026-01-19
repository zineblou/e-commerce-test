package service;

import entity.Article;
import entity.Panier;
import entity.Produit;
import repository.ArticleRepository;
import repository.PanierRepository;
import repository.ProduitRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class PanierService {

    public final PanierRepository panierRepository;
    public final ProduitRepository produitRepository;
    public final ArticleRepository articleRepository;

    public PanierService(PanierRepository panierRepository, ProduitRepository produitRepository,
                         ArticleRepository articleRepository ){
        this.panierRepository = panierRepository;
        this.produitRepository= produitRepository;
        this.articleRepository=articleRepository;
    }

    public Panier creerPanier(HashMap<Long, Integer> productAndQuantity){
        if(!productAndQuantity.isEmpty()){
            Panier panier = new Panier();
            for(Map.Entry<Long, Integer> entry :  productAndQuantity.entrySet()){
                Long productId = entry.getKey();
                Integer quantity = entry.getValue();
                Optional<Produit> produitOptional = produitRepository.findById(productId);

                if(produitOptional.isEmpty()) {
                    return null;
                }

                Article article = this.articleRepository.save(new Article(quantity, produitOptional.get()));
                panier = new Panier();
                panier.getArticles().add(article);

            }
            return  this.panierRepository.save(panier);

        }

        return null;
    }

}
