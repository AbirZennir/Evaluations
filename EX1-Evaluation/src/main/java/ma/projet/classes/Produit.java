package ma.projet.classes;

import javax.persistence.*;

@Entity
@NamedQuery(
        name = "Produit.findPrixSup100",
        query = "select p from Produit p where p.prix > 100"
)
public class Produit {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique=true)
    private String reference;

    @Column(nullable=false)
    private float prix;

    @ManyToOne(optional=false)
    private Categorie categorie;

    public Produit() {}
    public Produit(String reference, float prix, Categorie categorie) {
        this.reference=reference; this.prix=prix; this.categorie=categorie;
    }
    // getters/setters/toString
    public Long getId() { return id; }
    public String getReference() { return reference; }
    public void setReference(String reference) { this.reference = reference; }
    public float getPrix() { return prix; }
    public void setPrix(float prix) { this.prix = prix; }
    public Categorie getCategorie() { return categorie; }
    public void setCategorie(Categorie categorie) { this.categorie = categorie; }
    @Override
    public String toString() {
        return "Produit{" +
                "id=" + id +
                ", reference='" + reference + '\'' +
                ", prix=" + prix +
                ", categorie=" + (categorie != null ? categorie.getCode() : "null") +
                '}';
    }
    }

