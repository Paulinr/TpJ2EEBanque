/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Groupe;
import java.util.List;
import javax.ejb.Stateful;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Ing paulin
 */
@Stateful
@LocalBean
public class GestionGroupe {
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
     @PersistenceContext(unitName = "TPFLEURIMEMICHELPAULIN-ejbPU")
    private EntityManager em;

    public void creerGroupe(Groupe group) {
        em.persist(group);
    }
   public List<Groupe> getAllGroupe() {
        Query query = em.createNamedQuery("Groupe.findAll");
        return query.getResultList();
    }
   public Groupe update(Groupe group) {
        return em.merge(group);
    }

    public void persit(Groupe group) {
        em.persist(group);
    }

    public Groupe getGroupe(Long id) {
        return em.find(Groupe.class, id);
    }
    
    public Groupe getGroupeByName(String groupname) {
        Query query = em.createNamedQuery("Groupe.findBygroupname").setParameter("groupname", groupname);
        return (Groupe)query.getSingleResult();
    }
}
