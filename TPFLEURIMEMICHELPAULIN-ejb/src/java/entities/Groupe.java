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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ing paulin
 */
@Table(name="groupe")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Groupe.findAll", query = "SELECT g FROM Groupe g"),
    @NamedQuery(name = "Groupe.findBygroupname", query = "SELECT g FROM Groupe g WHERE g.groupname = :groupname")
    })
@Entity
public class Groupe implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true, nullable = false)
    private String groupname;
    
    @OneToOne
    private Users user;
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Groupe() {
    }

    public Groupe(String groupname, Users user) {
        this.groupname = groupname;
        this.user = user;
    }

    public Groupe(Long id, String groupname, Users user) {
        this.id = id;
        this.groupname = groupname;
        this.user = user;
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
        if (!(object instanceof Groupe)) {
            return false;
        }
        Groupe other = (Groupe) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "entities.Groupe[ id=" + id + " ]";
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
    
}
