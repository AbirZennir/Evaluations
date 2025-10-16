package ma.projet.classes;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Projet {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique=true)
    private String nom;

    private LocalDate dateDebut;
    private LocalDate dateFin;

    // Chef de projet (ManyToOne -> Employe)
    @ManyToOne(optional=false)
    private Employe chefProjet;

    @OneToMany(mappedBy = "projet", cascade = CascadeType.ALL)
    private List<Tache> taches = new ArrayList<>();

    public Projet() {}
    public Projet(String nom, LocalDate dateDebut, LocalDate dateFin, Employe chefProjet) {
        this.nom = nom; this.dateDebut = dateDebut; this.dateFin = dateFin; this.chefProjet = chefProjet;
    }
    // getters/setters
    public Long getId() { return id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public LocalDate getDateDebut() { return dateDebut; }
    public void setDateDebut(LocalDate dateDebut) { this.dateDebut = dateDebut; }
    public LocalDate getDateFin() { return dateFin; }
    public void setDateFin(LocalDate dateFin) { this.dateFin = dateFin; }
    public Employe getChefProjet() { return chefProjet; }
    public void setChefProjet(Employe chefProjet) { this.chefProjet = chefProjet; }
    public List<Tache> getTaches() { return taches; }
    public void setTaches(List<Tache> taches) { this.taches = taches;  }

}
