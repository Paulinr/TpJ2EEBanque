/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Client;
import entities.CompteBancaire;
import entities.TransactionBancaire;
import java.io.Serializable;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


/**
 *
 * @author dmichel
 */
//@TransactionManagement(TransactionManagementType.BEAN) 
@Stateless
@LocalBean
public class GestionnaireDeCompteBancaire implements Serializable {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    //@Resource 
   // UserTransaction ut; 
    
    @PersistenceContext //(unitName = "TPFLEURIMEMICHELPAULIN-ejbPU")
    private EntityManager em;

    public void creerCompte(CompteBancaire compteBancaire) {
        
       // try {
           //  ut.begin();
             em.persist(compteBancaire);
            // ut.commit();
            
//        } catch (IllegalStateException | SecurityException | HeuristicMixedException | HeuristicRollbackException | NotSupportedException | RollbackException | SystemException ex) {
//            try {
//                ut.rollback();
//            } catch (IllegalStateException | SecurityException | SystemException e) {
//                throw new RuntimeException(ex);
//            }
   //     }
       
    }

    public List<CompteBancaire> getAllComptes() {
        Query query = em.createNamedQuery("CompteBancaire.findAll");
        return query.getResultList();
    }
    
     public List<CompteBancaire> getComptesByClient(Long clientId) {
        Query query = em.createNamedQuery("CompteBancaire.findCompteByClientId");
        query.setParameter("clientId", clientId);
        return query.getResultList();
    }
     
     public List<CompteBancaire> getLazyCptes(int start, int nbComptes){
        
        Query query =em.createNamedQuery("CompteBancaire.findAll");
        query.setFirstResult(start);
        query.setMaxResults(nbComptes);
        
        return query.getResultList();
    }
    
  public int getNbComptes(){
        Query query = em.createNamedQuery("CompteBancaire.nbComptes");
        
        return ((Long) query.getSingleResult()).intValue();
    }
    
    /**
     * Supprimer un compte bancaire
     * @param compte 
     */
    public void delete(CompteBancaire compte) {
        // try {
            
          //   ut.begin();
             em.remove(em.merge(compte));
         //    ut.commit();
//        } catch (IllegalStateException | SecurityException | HeuristicMixedException | HeuristicRollbackException | NotSupportedException | RollbackException | SystemException ex) {
//             try {
//                   ut.rollback();
//             } catch (IllegalStateException | SecurityException | SystemException e) {
//                  throw new RuntimeException(ex);
//             }
//            
//        }
        
        
    }
    
    public void creerOperation(CompteBancaire compte, String descrption, float montant){
        TransactionBancaire tb = new TransactionBancaire(montant, descrption);
        compte.getTransactions().add(tb);
    }
    
    public CompteBancaire Consulter(String noCompte) { 
        Query query= em.createNamedQuery("CompteBancaire.findByCompteNoCpte");
        return (CompteBancaire) query.getResultList();
    }

  
    public CompteBancaire crediterCompte(CompteBancaire compte, float montant){
      // try {
          //  ut.begin();
            compte.deposer(montant);
            creerOperation(compte, "Dépot", montant);
            em.merge(compte);
           // ut.commit();
//            System.out.println("DEPOT REUSSI");
//        } catch (IllegalStateException | SecurityException | HeuristicMixedException | HeuristicRollbackException | NotSupportedException | RollbackException | SystemException ex) {
//            try {
//                ut.rollback();
//            } catch (IllegalStateException | SecurityException | SystemException e) {
//               throw new RuntimeException(ex); 
//            }
//        }
        return update (compte);
        
    }
    
    public CompteBancaire debiterCompte(CompteBancaire compte, float montant){
        compte.retirer(montant);
        creerOperation(compte, "Retrait", montant);
        em.merge(compte);
        System.out.println("RETRAIT REUSSI");
        return update (compte);
    }
    
    
    public void transaction(int tb, CompteBancaire compte, float montant){
               
        if (tb == 0){
            compte = this.debiterCompte(compte, montant);
        }
        else 
            if (tb == 1) {
            compte= this.crediterCompte(compte, montant);      
            }
           
        
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Opération réussie !","La transaction a été effectuée");  
          
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
 

    public CompteBancaire update(CompteBancaire comptebancaire) {
        return em.merge(comptebancaire);
    }

    public void persist(CompteBancaire compteBancaire) {
        em.persist(compteBancaire);
    }

    public CompteBancaire getCompteBancaire(Long idCompte) {
        return em.find(CompteBancaire.class,idCompte );
    }
    
     public void virement(Long idDebiteur, Long idCrediteur, float montant){
//         try {
             //ut.begin();
             System.out.println("==========================");
             System.out.println("idDebiteur: "+idDebiteur);
             System.out.println("idCrediteur: "+idCrediteur);
             System.out.println("Montant: "+montant);
             System.out.println("==========================");
        CompteBancaire compteDebiteur = getCompteBancaire(idDebiteur);// getCompteBancaire(idDebiteur);
        CompteBancaire compteCrediteur= getCompteBancaire(idCrediteur);
        
       //compteDebiteur.retirer(montant);
       //compteCrediteur.deposer(montant);
        
        debiterCompte(compteDebiteur, montant);
        crediterCompte(compteCrediteur, montant);
        
//        creerOperation(compteDebiteur, "Virement effectué à "+compteCrediteur.getClient().getNom()+"-"+compteCrediteur.getClient().getPrenom(), montant);
//        creerOperation(compteCrediteur, "Virement reçu de "+ compteDebiteur.getClient().getNom()+"-"+compteDebiteur.getClient().getPrenom(), montant);
//        
//       em.merge(compteDebiteur);
//        em.merge(compteCrediteur); 
//            ut.commit();
//         } catch (IllegalStateException | SecurityException | HeuristicMixedException | HeuristicRollbackException | NotSupportedException | RollbackException | SystemException ex) {
//             try {
//                 ut.rollback();
//                 
//             } catch (IllegalStateException | SecurityException | SystemException e) {
//                 throw new RuntimeException(ex); 
//             }
//         }
        
       
     }
     
     public List<CompteBancaire> getCompteByCliId(Long clientId) {
        Query query = em.createNamedQuery("CompteBancaire.findCompteByClientId").setParameter("clientId", clientId);
        return query.getResultList();
    }
     public void creerComptesTest() {  
         Client client= new Client();
         CompteBancaire cpte= new CompteBancaire();
     }
    
}
