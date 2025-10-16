package ma.projet.service;

import ma.projet.classes.*;
import ma.projet.dao.IDao;

import java.util.List;

public class EmployeTacheService extends BaseService implements IDao<EmployeTache> {
    @Override public EmployeTache add(EmployeTache o){ return tx(s->{s.save(o); return o;}); }
    @Override public EmployeTache update(EmployeTache o){ return tx(s->{s.update(o); return o;}); }
    @Override public boolean delete(Long id){ throw new UnsupportedOperationException("use deleteByKey"); }
    public boolean deleteByKey(Long employeId, Long tacheId){
        return tx(s -> { var et = s.get(EmployeTache.class, new EmployeTacheId(employeId, tacheId));
            if(et!=null){ s.delete(et); return true; } return false; });
    }
    @Override public EmployeTache findById(Long id){ throw new UnsupportedOperationException("use findByKey"); }
    public EmployeTache findByKey(Long employeId, Long tacheId){
        return tx(s -> s.get(EmployeTache.class, new EmployeTacheId(employeId, tacheId)));
    }
    @Override public List<EmployeTache> findAll(){ return tx(s->s.createQuery("from EmployeTache", EmployeTache.class).getResultList()); }
}
