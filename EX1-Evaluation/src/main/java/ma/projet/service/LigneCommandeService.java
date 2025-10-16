package ma.projet.service;

import ma.projet.classes.*;
import ma.projet.dao.IDao;

import java.util.List;

public class LigneCommandeService extends BaseService implements IDao<LigneCommandeProduit> {
    @Override public LigneCommandeProduit add(LigneCommandeProduit o){ return tx(s->{ s.save(o); return o;});}
    @Override public LigneCommandeProduit update(LigneCommandeProduit o){ return tx(s->{ s.update(o); return o;});}
    @Override public boolean delete(Long id){ throw new UnsupportedOperationException("Utilise deleteByKey(produitId, commandeId)"); }

    public boolean deleteByKey(Long produitId, Long commandeId){
        return tx(s -> {
            LigneCommandeProduit l = s.get(LigneCommandeProduit.class, new LigneCommandeId(produitId, commandeId));
            if(l!=null){ s.delete(l); return true; }
            return false;
        });
    }
    @Override public LigneCommandeProduit findById(Long id){ throw new UnsupportedOperationException("Utilise findByKey"); }

    public LigneCommandeProduit findByKey(Long produitId, Long commandeId){
        return tx(s -> s.get(LigneCommandeProduit.class, new LigneCommandeId(produitId, commandeId)));
    }
    @Override public List<LigneCommandeProduit> findAll(){
        return tx(s -> s.createQuery("from LigneCommandeProduit", LigneCommandeProduit.class).getResultList());
    }
}
