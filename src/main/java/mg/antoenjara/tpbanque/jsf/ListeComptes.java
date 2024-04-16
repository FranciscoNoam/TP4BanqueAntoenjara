/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.antoenjara.tpbanque.jsf;

import jakarta.inject.Named;
import jakarta.enterprise.context.Dependent;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import mg.antoenjara.tpbanque.entity.Compte;
import mg.antoenjara.tpbanque.service.GestionnaireCompte;
import org.primefaces.PrimeFaces;

/**
 *
 * @author francisco
 */
@Named(value = "listeComptes")
@Dependent
public class ListeComptes {

    private Compte selectedCompte;
    private List<Compte> customerList;

    @Inject
    private GestionnaireCompte comptebancaireManager;

    /**
     * Retourne la liste des clients pour affichage dans une DataTable.
     */
    public List<Compte> getAllComptes() {
        if (customerList == null) {
            customerList = comptebancaireManager.getAllComptes();
        }
        return customerList;
    }

    public Compte getSelectedCompte() {
        return selectedCompte;
    }

    public void setSelectedCompte(Compte selectedCompte) {
        this.selectedCompte = selectedCompte;
    }

    public boolean filterBySolde(Object value, Object filter, Locale locale) {

        if (filter == null || filter.toString().isEmpty()) {
            return true; // Pas de filtre, donc tous les éléments sont affichés
        }

        if (value == null) {
            return false; // Élément vide, donc on ne l'affiche pas
        }

        double soldeValue = ((Number) value).longValue();
        double filterValue = Long.parseLong(filter.toString());

        // Filtre pour les soldes supérieurs ou égaux à la valeur saisie
        return soldeValue >= filterValue;
    }

    public ListeComptes() {
    }

    public void show() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Action de suppression", "Le compte a été marqué pour suppression."));
    }

    public void redirectToDetail(String idCompte) {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        try {
            externalContext.redirect(externalContext.getRequestContextPath() + "/detailCompte.xhtml?idCompte=" + idCompte);
        } catch (IOException e) {
            // Gérer les erreurs d'entrée/sortie
            e.printStackTrace();
        }
    }
    
     public void redirectToDelete(String idCompte){
         ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        try {
            externalContext.redirect(externalContext.getRequestContextPath() + "/deleteCompte.xhtml?selectedItemId=" + idCompte);
        } catch (IOException e) {
            // Gérer les erreurs d'entrée/sortie
            e.printStackTrace();
        }
     }
    
   
}
