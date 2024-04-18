/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.antoenjara.tpbanque.jsf;

import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.List;
import mg.antoenjara.tpbanque.entity.Compte;
import mg.antoenjara.tpbanque.entity.OperationBancaire;
import mg.antoenjara.tpbanque.service.GestionnaireCompte;

/**
 *
 * @author francisco
 */
@Named
@ViewScoped
public class Operation implements Serializable{

    private Long idCompte;
    private Compte compte;
    private List<OperationBancaire> operations;

    
     @Inject
    private GestionnaireCompte comptebancaireManager;
    
    public Long getIdCompte() {
        return idCompte;
    }

    public void setIdCompte(Long idCompte) {
        this.idCompte = idCompte;
    }

    public Compte getCompte() {
        return compte;
    }
     public void setCompte(Compte compte) {
         this.compte=compte;
    }
     public List<OperationBancaire> getOperations() {
        return operations;
    }
     public void setOperations(List<OperationBancaire> opt) {
         this.operations=opt;
    }
    
     public void load() {
        this.compte = comptebancaireManager.findById(idCompte);
       this.operations = compte.getOperations();
    }
     
    public Operation() {
    }
    
}
