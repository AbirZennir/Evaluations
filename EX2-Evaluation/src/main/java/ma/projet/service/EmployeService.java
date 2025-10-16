package ma.projet.service;

import ma.projet.classes.Employe;
import ma.projet.classes.Tache;
import ma.projet.dao.IDao;

import java.util.List;

public class EmployeService extends BaseService implements IDao<Employe> {
    @Override public Employe add(Employe o){ return tx(s->{s.save(o); return o;}); }
    @Override public Employe update(Employe o){ return tx(s->{s.update(o); return o;}); }
    @Override public boolean delete(Long id){ return tx(s->{var e=s.get(Employe.class,id); if(e!=null){s.delete(e); return true;} return false;}); }
    @Override public Employe findById(Long id){ return tx(s->s.get(Employe.class,id)); }
    @Override public List<Employe> findAll(){ return tx(s->s.createQuery("from Employe", Employe.class).getResultList()); }

    /** Tâches réalisées par un employé (avec dates réelles existantes) */
    public List<Object[]> tachesRealisees(Long empId){
        return tx(s -> s.createQuery(
                        "select t.id, t.nom, et.dateDebutReelle, et.dateFinReelle " +
                                "from EmployeTache et join et.tache t " +
                                "where et.employe.id = :id " +
                                "order by et.dateDebutReelle", Object[].class)
                .setParameter("id", empId)
                .getResultList());
    }

    /** Projets gérés par un employé (chef de projet) */
    public List<Object[]> projetsGeres(Long empId){
        return tx(s -> s.createQuery(
                        "select p.id, p.nom, p.dateDebut, p.dateFin " +
                                "from Projet p where p.chefProjet.id = :id " +
                                "order by p.dateDebut", Object[].class)
                .setParameter("id", empId)
                .getResultList());
    }
}
