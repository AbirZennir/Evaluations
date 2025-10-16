package ma.projet.classes;

import javax.persistence.*;

@Entity
public class LigneCommandeProduit {
    @EmbeddedId
    private LigneCommandeId id = new LigneCommandeId();

    @ManyToOne(optional=false)
    @MapsId("produitId")
    private Produit produit;

    @ManyToOne(optional=false)
    @MapsId("commandeId")
    private Commande commande;

    @Column(nullable=false)
    private int quantite;

    public LigneCommandeProduit() {}
    public LigneCommandeProduit(Produit p, Commande c, int qte) {
        this.produit = p; this.commande = c; this.quantite = qte;
        this.id = new LigneCommandeId(p.getId(), c.getId());
    }
    // getters/setters
    public LigneCommandeId getId() { return id; }
    public Produit getProduit() { return produit; }
    public void setProduit(Produit produit) { this.produit = produit; }
    public Commande getCommande() { return commande; }
    public void setCommande(Commande commande) { this.commande = commande; }
    public int getQuantite() { return quantite; }
    public void setQuantite(int quantite) { this.quantite = quantite; }

}
