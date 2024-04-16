/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mg.antoenjara.tpbanque.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;

/**
 *
 * @author francisco
 */
@Entity
public class Compte implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private int solde;

    public Long getId() {
        return id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setSolde(int solde) {
        this.solde = solde;
    }

    public String getNom() {
        return this.nom;
    }

    public int getSolde() {
        return this.solde;
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
        if (!(object instanceof Compte)) {
            return false;
        }
        Compte other = (Compte) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mg.antoenjara.tpbanque.entity.CompteBancaire[ id=" + id + " ]";
    }

    public Compte() {

    }

    public Compte(String nom, int solde) {
        this.nom = nom;
        this.solde = solde;
    }

   
    public void deposer(int montant) {
        solde += montant;
    }

    public void retirer(int montant) {
        if (montant < solde) {
            solde -= montant;
        } else {
            solde = 0;
        }
    }

}
