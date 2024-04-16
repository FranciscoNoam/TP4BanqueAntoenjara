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

    public boolean validationTransfer() {

        return debiter != null && debiter > 0
                && crediter != null && crediter > 0 && montant > 0;
    }

    public String virementSolde() {
        if (validationTransfer()) {
            Compte compteCrediter = comptebancaireManager.findById(crediter);
            Compte compteDebiter = comptebancaireManager.findById(debiter);
            comptebancaireManager.transfert(compteDebiter,compteCrediter, montant);
            return "listeComptes?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur", "Virement invalide"));
            return null;
        }
    }

}
