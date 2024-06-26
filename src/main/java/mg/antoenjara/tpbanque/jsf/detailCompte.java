/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.antoenjara.tpbanque.jsf;

import jakarta.inject.Named;
import jakarta.enterprise.context.Dependent;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import static java.lang.Long.parseLong;
import java.util.List;
import mg.antoenjara.tpbanque.entity.Compte;
import mg.antoenjara.tpbanque.service.GestionnaireCompte;

/**
 *
 * @author francisco
 */
@Named
@ViewScoped
public class detailCompte implements Serializable {

    private Long selectedItemId;
    private Long idCompte;
    private Compte compte;
    @Inject
    private GestionnaireCompte comptebancaireManager;

    public Long getSelectedItemId() {
        return selectedItemId;
    }

    public void setSelectedItemId(Long selectedItemId) {
        this.selectedItemId = selectedItemId;
    }

    public Long getIdCompte() {
        return idCompte;
    }

    public void setIdCompte(Long idCompte) {
        this.idCompte = idCompte;
    }

    /**
     * Retourne les détails du client courant (contenu dans l'attribut customer
     * de cette classe).
     */
    public Compte getCompte() {
        return compte;
    }

    public String update() {
        comptebancaireManager.modifieCompte(compte);
        return "listeComptes?faces-redirect=true";
    }

    public String delete() {
        comptebancaireManager.delete(compte);
        return "listeComptes?faces-redirect=true";
    }

    public void loadCompte() {
        this.compte = comptebancaireManager.findById(idCompte);
    }
    public void loadCompteDelete() {
        this.compte = comptebancaireManager.findById(selectedItemId);
    }

    /**
     * Creates a new instance of detailCompte
     */
    public detailCompte() {
    }
}
