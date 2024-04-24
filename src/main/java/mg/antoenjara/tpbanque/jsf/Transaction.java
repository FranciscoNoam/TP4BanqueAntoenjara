/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.antoenjara.tpbanque.jsf;

import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.persistence.OptimisticLockException;
import java.io.Serializable;
import mg.antoenjara.tpbanque.entity.Compte;
import mg.antoenjara.tpbanque.service.GestionnaireCompte;
import mg.antoenjara.tpbanque.jsf.util.Util;

/**
 *
 * @author francisco
 */
@Named(value = "transaction")
@ViewScoped
public class Transaction implements Serializable {

    /**
     * Creates a new instance of Transaction
     */
    public Transaction() {
    }

    private long idCompte;
    private int solde;
    private Compte compte;
    private String transaction;

    @Inject
    GestionnaireCompte gc;

    public long getIdCompte() {
        return idCompte;
    }

    public void setIdCompte(long idCompte) {
        this.idCompte = idCompte;
    }

    public void loadCompte() {
        compte = gc.findById(idCompte);
    }

    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }

    public String getTransaction() {
        return transaction;
    }

    public void setTransaction(String type) {
        this.transaction = type;
    }

    public int getSolde() {
        return solde;
    }

    public void setSolde(int solde) {
        this.solde = solde;
    }

    public String mouvement() {
        try {
            
            if (solde <= 0) {
                Util.messageErreur("Le Montant doit être superieur à 0 !", "Montant est invalid !", "form:solde");
                return null;
            }
            
            if (compte.getSolde() < solde) {
                if (transaction.equals("retrait")) {
                    Util.messageErreur("Montant à retirer insuffisant !", "Montant insuffisant !", "form:solde");
                    return null;
                }
            }

            switch (transaction) {
                case "retrait":
                    compte.retirer(solde);
                    break;
                case "versement":
                    compte.deposer(solde);
                    break;
                default:
                    Util.messageErreur("Type transaction non definit !", "Type transaction non definit !", "form:transaction");
                    return null;
            }
            
            gc.modifieCompte(compte);
            Util.addFlashInfoMessage(transaction + " de " + solde + " effectué du compte de " + compte.getNom());
            
            return "listeComptes?faces-redirect=true";
            
        } catch (OptimisticLockException ex) {
            
            Util.messageErreur("Le compte de " + compte.getNom()
                    + " a été modifié ou supprimé par un autre utilisateur !");
            return null; // pour rester sur la page s'il y a une exception
        }
    }


    /* public String mouvement() {
        try {
            boolean isError = this.checkTransaction();
            if (isError) {
                return null;
            }

            switch (transaction) {
                case "retrait":
                    compte.retirer(solde);
                    break;
                case "versement":
                    compte.deposer(solde);
                    break;
                default:
                    Util.messageErreur("Type transaction non definit !", "Type transaction non definit !", "form:transaction");
                    return null;
            }
            gc.modifieCompte(compte);
            Util.addFlashInfoMessage(transaction + " de " + solde + " effectué du compte de " + this.compte.getNom());
            return "listeComptes?faces-redirect=true";
        } catch (OptimisticLockException ex) {
        Util.messageErreur("Le compte de " + compte.getNom()
                  + " a été modifié ou supprimé par un autre utilisateur !");
        return null; // pour rester sur la page s'il y a une exception
    }
        }

    

    public boolean checkTransaction() {
        boolean error = false;
        if (solde <= 0) {
            Util.messageErreur("Le Montant doit être superieur à 0 !", "Montant est invalid !", "form:solde");
            error = true;
        } else if (transaction == null) {
            Util.messageErreur("Type transaction non definit !", "Type transaction non definit !", "form:transaction");
            error = true;
        } else if (this.compte.getSolde() < solde) {
            if (transaction.equals("retrait")) {
                Util.messageErreur("Montant à retirer insuffisant !", "Montant insuffisant !", "form:solde");
                error = true;
            }
        }
        return error;
    }
     */
 /*
    public String mouvement() {
        boolean isError = this.checkTransaction();
        if (isError) {
            return null;
        }
        if (transaction == "retrait") {
            compte.retirer(solde);
        } else if (transaction == "versement") {
            compte.deposer(solde);
        } else {
            Util.messageErreur("Type transaction non definit !", "Type transaction non definit !", "form:transaction");
            return null;
        }
        
        gc.modifieCompte(compte);
        Util.addFlashInfoMessage(transaction + " de " + solde + " effectué du compte de " + this.compte.getNom());
        return "listeComptes?faces-redirect=true";
    }
     */
}
