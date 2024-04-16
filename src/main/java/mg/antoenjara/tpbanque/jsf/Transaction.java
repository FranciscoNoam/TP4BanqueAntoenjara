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
import mg.antoenjara.tpbanque.service.GestionnaireCompte;
import mg.antoenjara.tpbanque.jsf.util.Util;
/**
 *
 * @author francisco
 */
@Named(value = "transaction")
@ViewScoped
public class Transaction implements Serializable{

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
                Util.messageErreur("Type transaction non definit !", "Type transaction non definit !", "form:mouv");
                return null;
        }
        gc.modifieCompte(compte);
        Util.addFlashInfoMessage(transaction + " de " + solde + " effectué du compte de " + this.compte.getNom());
        return "listeComptes?faces-redirect=true";
    }

    public boolean checkTransaction() {
        boolean error = false;
        if(transaction==null){
            Util.messageErreur("Type transaction non definit !", "Type mouvement non definit !", "form:transaction");
            error = true;
        }else if (solde <= 0) {
            Util.messageErreur("Transaction de la transaction doit être superieur à 0 !", "Solde incorect !", "form:solde");
            error = true;
        } else if (this.compte.getSolde() < solde) {
            if (transaction.equals("retrait")) {
                Util.messageErreur("Solde à retirer insuffisant !", "Solde insuffisant !", "form:solde");
                error = true;
            }
        }
        return error;
    }
}
