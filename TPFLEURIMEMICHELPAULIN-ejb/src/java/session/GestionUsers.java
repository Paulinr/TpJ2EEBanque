/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Users;
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
public class GestionUsers {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
     @PersistenceContext(unitName = "TPFLEURIMEMICHELPAULIN-ejbPU")
     private EntityManager em;

    public void creerUser(Users user) {
        em.persist(user);
    }
  public List<Users> getAllUsers() {
        Query query = em.createNamedQuery("Users.findAll");
        return query.getResultList();
    }
     public int getNbUsers(){
        Query query = em.createNamedQuery("Users.nbUtilisateurs");
        
        return ((Long) query.getSingleResult()).intValue();
    }
    
    public Users update(Users user) {
        return em.merge(user);
    }

    public void persit(Users user) {
        em.persist(user);
    }

    public Users getUser(Long id) {
        return em.find(Users.class, id);
    }

    public Users getUserByUsername(String username) {
        Query query = em.createNamedQuery("Users.findByUsername").setParameter("username", username);
        return (Users)query.getSingleResult();
    }
    
    public Users connectUser(String username,String password) {
           Query query = em.createNamedQuery("Users.login");
                 query.setParameter("username", username);
                 query.setParameter("password", password);
         return (Users)query.getSingleResult();
    }
    
}
