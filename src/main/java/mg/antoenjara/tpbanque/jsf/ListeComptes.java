/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.antoenjara.tpbanque.jsf;

import jakarta.inject.Named;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import java.util.List;
import java.util.Locale;
import mg.antoenjara.tpbanque.entity.Compte;
import mg.antoenjara.tpbanque.service.GestionnaireCompte;

/**
 *
 * @author francisco
 */
@Named(value = "listeComptes")
@Dependent
public class ListeComptes {

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

}
