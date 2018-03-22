/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.CompteBancaire;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author dmichel
 */
@Stateless
@LocalBean
public class GestionnaireDeCompteBancaire {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext(unitName = "TPFLEURIMEMICHELPAULIN-ejbPU")
    private EntityManager em;

    public void creerCompte(CompteBancaire compteBancaire) {
        em.persist(compteBancaire);
    }

    public List<CompteBancaire> getAllComptes() {
        Query query = em.createNamedQuery("CompteBancaire.findAll");
        return query.getResultList();
    }

    public void creerComptesTest() {
        creerCompte(new CompteBancaire("Paulin", 150000));
        creerCompte(new CompteBancaire("Fleurime", 950000));
        creerCompte(new CompteBancaire("Michel", 20000));
    }

    public CompteBancaire update(CompteBancaire comptebancaire) {
        return em.merge(comptebancaire);
    }

    public void persist(CompteBancaire compteBancaire) {
        em.persist(compteBancaire);
    }

    public CompteBancaire getCompteBancaire(Long idCompteBancaire) {
        return em.find(CompteBancaire.class, idCompteBancaire);
    }
}
