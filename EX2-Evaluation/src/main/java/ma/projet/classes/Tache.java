package ma.projet.classes;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQuery(
        name = "Tache.prixSup1000",
        query = "select t from Tache t where t.prix > 1000"
)
public class Tache {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false) private String nom;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private double prix;

    @ManyToOne(optional=false)
    private Projet projet;

    @OneToMany(mappedBy = "tache")
    private List<EmployeTache> affectations = new ArrayList<>();

    public Tache() {}
    public Tache(String nom, LocalDate dateDebut, LocalDate dateFin, double prix, Projet projet) {
        this.nom = nom; this.dateDebut = dateDebut; this.dateFin = dateFin; this.prix = prix; this.projet = projet;
    }
    // getters/setters
    public Long getId() { return id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public LocalDate getDateDebut() { return dateDebut; }
    public void setDateDebut(LocalDate dateDebut) { this.dateDebut = dateDebut; }
    public LocalDate getDateFin() { return dateFin; }
    public void setDateFin(LocalDate dateFin) { this.dateFin = dateFin; }
    public double getPrix() { return prix; }
    public void setPrix(double prix) { this.prix = prix; }
    public Projet getProjet() { return projet; }
    public void setProjet(Projet projet) { this.projet = projet; }
    public List<EmployeTache> getAffectations() { return affectations;}
    public void setAffectations(List<EmployeTache> affectations) { this.affectations = affectations; }

}
