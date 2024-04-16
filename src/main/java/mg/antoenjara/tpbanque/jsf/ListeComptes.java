/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.antoenjara.tpbanque.jsf;

import jakarta.inject.Named;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import java.util.List;
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
    public ListeComptes() {
    }
    
}
