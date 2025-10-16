package ma.projet.service;

import ma.projet.classes.Tache;
import ma.projet.dao.IDao;

import java.time.LocalDate;
import java.util.List;

public class TacheService extends BaseService implements IDao<Tache> {
    @Override public Tache add(Tache o){ return tx(s->{s.save(o); return o;}); }
    @Override public Tache update(Tache o){ return tx(s->{s.update(o); return o;}); }
    @Override public boolean delete(Long id){ return tx(s->{var t=s.get(Tache.class,id); if(t!=null){s.delete(t); return true;} return false;}); }
    @Override public Tache findById(Long id){ return tx(s->s.get(Tache.class,id)); }
    @Override public List<Tache> findAll(){ return tx(s->s.createQuery("from Tache", Tache.class).getResultList()); }

    /** NamedQuery : prix > 1000 DH */
    public List<Tache> prixSup1000(){
        return tx(s -> s.createNamedQuery("Tache.prixSup1000", Tache.class).getResultList());
    }

    /** Tâches réalisées entre deux dates (réelles) */
    public List<Object[]> realiseesEntre(LocalDate d1, LocalDate d2){
        return tx(s -> s.createQuery(
                        "select t.id, t.nom, et.dateDebutReelle, et.dateFinReelle " +
                                "from EmployeTache et join et.tache t " +
                                "where et.dateDebutReelle >= :d1 and et.dateFinReelle <= :d2 " +
                                "order by et.dateDebutReelle", Object[].class)
                .setParameter("d1", d1)
                .setParameter("d2", d2)
                .getResultList());
    }
}
