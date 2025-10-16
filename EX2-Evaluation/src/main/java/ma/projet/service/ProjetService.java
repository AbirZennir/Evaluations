package ma.projet.service;

import ma.projet.classes.Projet;
import ma.projet.dao.IDao;

import java.util.List;

public class ProjetService extends BaseService implements IDao<Projet> {
    @Override public Projet add(Projet o){ return tx(s->{s.save(o); return o;}); }
    @Override public Projet update(Projet o){ return tx(s->{s.update(o); return o;}); }
    @Override public boolean delete(Long id){ return tx(s->{var p=s.get(Projet.class,id); if(p!=null){s.delete(p); return true;} return false;}); }
    @Override public Projet findById(Long id){ return tx(s->s.get(Projet.class,id)); }
    @Override public List<Projet> findAll(){ return tx(s->s.createQuery("from Projet", Projet.class).getResultList()); }

    /** Tâches planifiées d’un projet (dates prévues de Tache) */
    public List<Object[]> tachesPlanifiees(Long projetId){
        return tx(s -> s.createQuery(
                        "select t.id, t.nom, t.dateDebut, t.dateFin, t.prix " +
                                "from Tache t where t.projet.id = :pid order by t.dateDebut", Object[].class)
                .setParameter("pid", projetId)
                .getResultList());
    }

    /** Tâches réalisées d’un projet (avec dates réelles venant de EmployeTache) */
    public List<Object[]> tachesRealisees(Long projetId){
        return tx(s -> s.createQuery(
                        "select t.id, t.nom, et.dateDebutReelle, et.dateFinReelle " +
                                "from EmployeTache et join et.tache t " +
                                "where t.projet.id = :pid " +
                                "order by et.dateDebutReelle", Object[].class)
                .setParameter("pid", projetId)
                .getResultList());
    }
}
