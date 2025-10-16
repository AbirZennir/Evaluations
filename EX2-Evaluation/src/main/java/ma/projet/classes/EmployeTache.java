package ma.projet.classes;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class EmployeTache {
    @EmbeddedId
    private EmployeTacheId id = new EmployeTacheId();

    @ManyToOne @MapsId("employeId")
    private Employe employe;

    @ManyToOne @MapsId("tacheId")
    private Tache tache;

    private LocalDate dateDebutReelle;
    private LocalDate dateFinReelle;

    public EmployeTache() {}
    public EmployeTache(Employe e, Tache t, LocalDate ddr, LocalDate dfr){
        this.employe = e; this.tache = t; this.dateDebutReelle = ddr; this.dateFinReelle = dfr;
        this.id = new EmployeTacheId(e.getId(), t.getId());
    }
    // getters/setters
    public EmployeTacheId getId() { return id; }
    public void setId(EmployeTacheId id) { this.id = id;}
    public Employe getEmploye() { return employe; }
    public void setEmploye(Employe employe) { this.employe = employe; }
    public Tache getTache() { return tache; }
    public void setTache(Tache tache) { this.tache = tache; }
    public LocalDate getDateDebutReelle() { return dateDebutReelle; }
    public void setDateDebutReelle(LocalDate dateDebutReelle) { this.dateDebutReelle = dateDebutReelle; }
    public LocalDate getDateFinReelle() { return dateFinReelle; }
    public void setDateFinReelle(LocalDate dateFinReelle) { this.dateFinReelle = dateFinReelle; }

}
