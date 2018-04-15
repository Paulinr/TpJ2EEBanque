/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author paulin
 */
@Entity
@Table(name = "tbl_compte")
@XmlRootElement
@NamedQueries({ 
    @NamedQuery(name = "CompteBancaire.findAll", query = "SELECT c FROM CompteBancaire c"),
    @NamedQuery(name = "CompteBancaire.findByCompteId", query = "SELECT c FROM CompteBancaire c WHERE c.id = :compteId"),
     @NamedQuery(name = "CompteBancaire.findByCompteNoCpte", query = "SELECT c FROM CompteBancaire c WHERE c.numeroCompte =:numeroCompte"),
      @NamedQuery(name = "CompteBancaire.findByTypeCompte", query = "SELECT c FROM CompteBancaire c WHERE c.typeCompte = :typeCompte"),
     @NamedQuery(name = "CompteBancaire.nbComptes", query = "SELECT COUNT(c) FROM CompteBancaire c"),
    @NamedQuery(name = "CompteBancaire.findCompteByClientId", query = "SELECT c FROM CompteBancaire c WHERE c.client.id = :clientId")
  })
public class CompteBancaire implements Serializable {

    private static final long serialVersionUID = 1L;
    

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Size(max = 16)
    @Column(unique = true,nullable = false)
    private String numeroCompte;
    private String typeCompte;
    private float solde;   
    @ManyToOne
    private Client client;

    public CompteBancaire() { 
        client= new Client();  
    }

    public CompteBancaire(String numeroCompte, float solde, Client client) {
        this.numeroCompte = numeroCompte;
        this.solde = solde;
        this.client = client;  }

    public CompteBancaire(float solde, Client client) {
        this.solde = solde;
        this.client = client;    
        
    }

    public CompteBancaire(String typeCompte, float solde) {
        this.typeCompte = typeCompte;
        this.solde = solde;
    }

    public CompteBancaire(String numeroCompte, String typeCompte, float solde) {
        this.numeroCompte = numeroCompte;
        this.typeCompte = typeCompte;
        this.solde = solde;
    }

    public CompteBancaire(String numeroCompte, String typeCompte, float solde, Client client) {
        this.numeroCompte = numeroCompte;
        this.typeCompte = typeCompte;
        this.solde = solde;
        this.client = client; }
    
    public Client getClient() {
        return client;
    }

    @OneToMany(mappedBy = "comptebancaire")
    private List<TransactionBancaire> transactions;
   

    public void setClient(Client client) {
        this.client = client;
    }
    
    

    public List<TransactionBancaire> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionBancaire> transactions) {
        this.transactions = transactions;
    }

    public void deposer(float montant) {
        if(montant < 0) { 
              throw new IllegalArgumentException("somme négative " + montant); 
        } 
 
        this.solde = this.solde + montant;
    }

    public float retirer(float montant) {
      /*   if(montant < 0) { 
       throw new IllegalArgumentException("montant négatif " + montant); 
        } 
       if(montant > solde) { 
       throw new RuntimeException("montant supérieur au solde" + montant); 
        }  */
        if (montant <= solde) {
            solde -= montant;
            return solde;
        } 
       else {
         
            return solde;
           
        }
    }

    public String getNumeroCompte() {
        return numeroCompte;
    }

    public void setNumeroCompte(String numeroCompte) {
        this.numeroCompte = numeroCompte;
    }


    public String getTypeCompte() {
        return typeCompte;
    }

    public void setTypeCompte(String typeCompte) {
        this.typeCompte = typeCompte;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CompteBancaire other = (CompteBancaire) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

   
    

    @Override
    public String toString() {
        return "CompteBancaire{" + "id=" + id + ", numeroCompte=" + numeroCompte + ", typeCompte=" + typeCompte + ", solde=" + solde + ", client=" + client + '}';
    }

    public float getSolde() {
        return solde;
    }

    public void setSolde(float solde) {
        this.solde = solde;
    }
    
    }
