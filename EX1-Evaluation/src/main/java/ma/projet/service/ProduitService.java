package ma.projet.service;

import ma.projet.classes.Categorie;
import ma.projet.classes.Produit;
import ma.projet.dao.IDao;
import org.hibernate.Session;

import java.time.LocalDate;
import java.util.List;

public class ProduitService extends BaseService implements IDao<Produit> {
    @Override public Produit add(Produit o){ return tx(s->{ s.save(o); return o;});}
    @Override public Produit update(Produit o){ return tx(s->{ s.update(o); return o;});}
    @Override public boolean delete(Long id){ return tx(s->{ Produit p=s.get(Produit.class,id); if(p!=null){s.delete(p); return true;} return false;});}
    @Override public Produit findById(Long id){ return tx(s-> s.get(Produit.class,id));}
    @Override public List<Produit> findAll(){ return tx(s-> s.createQuery("from Produit", Produit.class).getResultList());}

    /** 1) Produits par catégorie */
    public List<Produit> findByCategorie(Long categorieId) {
        return tx(s -> s.createQuery(
                        "select p from Produit p where p.categorie.id = :cid", Produit.class)
                .setParameter("cid", categorieId)
                .getResultList());
    }

    /** 2) Produits commandés entre deux dates (distinct + quantité si besoin via DTO) */
    public List<Produit> findCommandesBetween(LocalDate d1, LocalDate d2) {
        return tx(s -> s.createQuery(
                        "select distinct l.produit from LigneCommandeProduit l " +
                                "where l.commande.date between :d1 and :d2", Produit.class)
                .setParameter("d1", d1)
                .setParameter("d2", d2)
                .getResultList());
    }

    /** 3) Produits d’une commande donnée */
    public List<Object[]> findProduitsInCommande(Long commandeId) {
        // Retourne [reference, prix, quantite]
        return tx(s -> s.createQuery(
                        "select p.reference, p.prix, l.quantite " +
                                "from LigneCommandeProduit l join l.produit p " +
                                "where l.commande.id = :cid", Object[].class)
                .setParameter("cid", commandeId)
                .getResultList());
    }

    /** 4) Nommed query : produits prix > 100 DH */
    public List<Produit> findPrixSup100() {
        return tx((Session s) -> s.createNamedQuery("Produit.findPrixSup100", Produit.class)
                .getResultList());
    }
}
