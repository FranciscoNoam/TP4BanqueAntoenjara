/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.antoenjara.tpbanque.jsf;

import jakarta.inject.Named;
import jakarta.enterprise.context.Dependent;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
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
@Named(value = "virement")
@ViewScoped
public class Virement implements Serializable {

    @Inject
    private GestionnaireCompte comptebancaireManager;

    /**
     * Creates a new instance of Virement
     */
    public Virement() {
    }

    private Long debiter;
    private Long crediter;
    private int montant;

    private Compte compteDebiter;
    private Compte compteCrediter;

    public void setDebiter(Long id) {
        this.debiter = id;
    }

    public void setCrediter(Long id) {
        this.crediter = id;
    }

    public void setMontant(int solde) {
        this.montant = solde;
    }

    public void setCompteDebiter(Compte crd) {
        this.compteDebiter = crd;
    }

    public void setCompteCrediter(Compte dbt) {
        this.compteCrediter = dbt;
    }

    public Long getDebiter() {
        return this.debiter;
    }

    public Long getCrediter() {
        return this.crediter;
    }

    public int getMontant() {
        return this.montant;
    }

    public Compte getCompteDebiter() {
        return this.compteDebiter;
    }

    public Compte getCompteCrediter() {
        return this.compteCrediter;
    }

    public boolean validationTransfer(Compte compteDebiter,Compte compteCrediter) {

        /*return debiter != null && debiter > 0
                && crediter != null && crediter > 0 && montant > 0; */
        boolean erreur = false;
        if (compteDebiter == null || compteCrediter == null) {

            if (compteDebiter == null) {
                Util.messageErreur("Aucun compte avec cet id !", "Aucun compte avec cet id !", "form:debiter");
            }

            if (compteCrediter == null) {
                Util.messageErreur("Aucun compte avec cet id !", "Aucun compte avec cet id !", "form:crediter");
            }
            erreur = true;
        } else {
            if (compteDebiter.getSolde() < montant) {
                Util.messageErreur("Solde insufisant pour le transfert !", "Solde source insuficant !", "form:debiter");
                erreur = true;
            }
        }
        return erreur;
    }

    public String virementSolde() {
        Compte compteCrediter = comptebancaireManager.findById(crediter);
        Compte compteDebiter = comptebancaireManager.findById(debiter);

        boolean error = this.validationTransfer(compteDebiter,compteCrediter);

        if (error) {
            return null;
        }

        comptebancaireManager.transfert(compteDebiter, compteCrediter, montant);

        Util.addFlashInfoMessage("Transfert de " + montant + " effectué de " + compteDebiter.getNom() + " vers " + compteCrediter.getNom());
        return "listeComptes?faces-redirect=true";
    }

    /*public String virementSolde() {
        if (validationTransfer()) {
            Compte compteCrediter = comptebancaireManager.findById(crediter);
            Compte compteDebiter = comptebancaireManager.findById(debiter);
            comptebancaireManager.transfert(compteDebiter,compteCrediter, montant);
            return "listeComptes?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur", "Virement invalide"));
            return null;
        }
        
        
    }*/
}
