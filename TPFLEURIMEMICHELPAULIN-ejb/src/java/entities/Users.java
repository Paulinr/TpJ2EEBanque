/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ing paulin
 */
@Table(name ="users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT ut FROM Users ut"),
    @NamedQuery(name = "Users.findByUserId", query = "SELECT ut FROM Users ut WHERE ut.id = :userId"),
    @NamedQuery(name = "Users.login", query = "SELECT ut FROM Users ut WHERE ut.username = :username and ut.password = :password "),
    @NamedQuery(name = "Users.findByUsername", query = "SELECT ut FROM Users ut WHERE ut.username = :username"),
    @NamedQuery(name = "Users.nbUtilisateurs", query = "SELECT COUNT(ut) FROM Users ut")})
@Entity
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(unique = true,nullable = false)
    private String username;
    
    @Column(nullable = false)
    private String password;
    private String nom;
    private String prenom;
    
    @ManyToOne
    private Groupe groupe;
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users() {
    }

    public Users(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Users(Long id, String username, String password, String nom, String prenom, Groupe groupe) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.groupe = groupe;
    }

    public Users(String nom, String prenom, Groupe groupe) {
        this.nom = nom;
        this.prenom = prenom;
        this.groupe = groupe;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "entities.users[ id=" + id + " ]";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Groupe getGroupe() {
        return groupe;
    }

    public void setGroupe(Groupe groupe) {
        this.groupe = groupe;
    }
    
}
