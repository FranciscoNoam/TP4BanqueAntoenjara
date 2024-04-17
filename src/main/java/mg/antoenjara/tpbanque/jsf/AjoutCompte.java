/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.antoenjara.tpbanque.jsf;

import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import mg.antoenjara.tpbanque.entity.Compte;
import mg.antoenjara.tpbanque.jsf.util.Util;
import mg.antoenjara.tpbanque.service.GestionnaireCompte;

/**
 *
 * @author francisco
 */
@Named(value = "ajoutCompte")
@ViewScoped
public class AjoutCompte implements Serializable{
    
    @Inject
    GestionnaireCompte compteManager;
    
    private Compte compte;
    private long idCompte;
    
    private String nom;
    private int solde;
    
    /**
     * Creates a new instance of Compte
     */
    public AjoutCompte() {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nomTitulaire) {
        this.nom = nomTitulaire;
    }

    public int getSolde() {
        return solde;
    }

    public void setSolde(int solde) {
        this.solde = solde;
    }

    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compteBancaire) {
        this.compte = compteBancaire;
    }


    public long getIdCompte() {
        return idCompte;
    }

    public void setIdCompte(long idCompte) {
        this.idCompte = idCompte;
    }
    
    
    public String create(){
        
         if(nom==null  || nom==""){
            Util.messageErreur("Le nom doit imperativement contenir au moins une caractère valide !", "Nom est incorect ! ", "form:nom");
            return null;
        }
         
        if(solde<=0){
            Util.messageErreur("Le solde doit imperativement être superieur à 1 !", "Solde bancaire incorect ! ", "form:solde");
            return null;
        }
        
        Compte c = compteManager.findByName(nom);
        
        if(c!=null){
            Util.messageErreur("Le compte bancaire avec le Nom '"+nom+"' existe dejà !", "Compte bancaire existant !", "form:nom");
            return null;
        }
        c = new Compte(nom , solde);
        compteManager.creerCompte(c);
        
        Util.addFlashInfoMessage("La création du compte de "+c.getNom()+" effectué avec une Solde de "+c.getSolde());
        return "listeComptes?faces-redirect=true";
    }
    
    
    
}
