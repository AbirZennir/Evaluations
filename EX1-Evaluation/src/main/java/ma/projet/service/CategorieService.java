package ma.projet.service;

import ma.projet.classes.Categorie;
import ma.projet.dao.IDao;
import org.hibernate.Session;

import java.util.List;

public class CategorieService extends BaseService implements IDao<Categorie> {
    @Override public Categorie add(Categorie o) { return tx(s -> { s.save(o); return o; }); }
    @Override public Categorie update(Categorie o) { return tx(s -> { s.update(o); return o; }); }
    @Override public boolean delete(Long id) { return tx(s -> { Categorie c=s.get(Categorie.class,id); if(c!=null){s.delete(c); return true;} return false;});}
    @Override public Categorie findById(Long id) { return tx(s -> s.get(Categorie.class,id)); }
    @Override public List<Categorie> findAll() {
        return tx(s -> s.createQuery("from Categorie", Categorie.class).getResultList());
    }
}
