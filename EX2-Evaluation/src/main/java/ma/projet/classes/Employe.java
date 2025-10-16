package ma.projet.classes;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Employe {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false) private String nom;
    @Column(nullable=false) private String prenom;
    private String telephone;

    // Chef de projet : 1 employé gère plusieurs projets
    @OneToMany(mappedBy = "chefProjet")
    private List<Projet> projetsGeres = new ArrayList<>();

    @OneToMany(mappedBy = "employe")
    private List<EmployeTache> employeTaches = new ArrayList<>();

    public Employe() {}
    public Employe(String nom, String prenom, String telephone) {
        this.nom = nom; this.prenom = prenom; this.telephone = telephone;
    }
    // getters/setters
    public Long getId() { return id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }
    public List<Projet> getProjetsGeres() { return projetsGeres; }
    public void setProjetsGeres(List<Projet> projetsGeres) { this.projetsGeres = projetsGeres; }
    public List<EmployeTache> getEmployeTaches() { return employeTaches; }
    public void setEmployeTaches(List<EmployeTache> employeTaches) { this.employeTaches = employeTaches; }
}
