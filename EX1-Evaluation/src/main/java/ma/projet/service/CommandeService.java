package ma.projet.service;

import ma.projet.classes.Commande;
import ma.projet.dao.IDao;

import java.util.List;

public class CommandeService extends BaseService implements IDao<Commande> {
    @Override public Commande add(Commande o){ return tx(s->{ s.save(o); return o;});}
    @Override public Commande update(Commande o){ return tx(s->{ s.update(o); return o;});}
    @Override public boolean delete(Long id){ return tx(s->{ var c=s.get(Commande.class,id); if(c!=null){s.delete(c); return true;} return false;});}
    @Override public Commande findById(Long id){ return tx(s-> s.get(Commande.class,id));}
    @Override public List<Commande> findAll(){ return tx(s-> s.createQuery("from Commande", Commande.class).getResultList());}
}
