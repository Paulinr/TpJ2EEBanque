/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Groupe;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Ing paulin
 */
@Stateless
@LocalBean
public class geestionGroupe {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
     @PersistenceContext(unitName = "TPFLEURIMEMICHELPAULIN-ejbPU")
     private EntityManager em;

     public void creerGroupe(Groupe groupe) {
        em.persist(groupe);
    }
  public List<Groupe> getAllGroupes() {
        Query query = em.createNamedQuery("Groupe.findAll");
        return query.getResultList();
    }
}
