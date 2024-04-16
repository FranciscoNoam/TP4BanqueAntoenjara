/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.antoenjara.tpbanque.config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.servlet.ServletContext;
import jakarta.transaction.Transactional;
import mg.antoenjara.tpbanque.entity.CompteBancaire;
import mg.antoenjara.tpbanque.service.GestionnaireCompte;


/**
 *
 * @author francisco
 */
public class InitBean {

    @Inject
    private GestionnaireCompte compte;

    @Transactional
    public void init(@Observes 
    @Initialized(ApplicationScoped.class) ServletContext context) {
        
        long count = compte.nbComptes();

        if (count <= 0) {
            createAccounts();
        }
    }
    
    private void createAccounts() {
        // Créer 4 CompteBancaire
        compte.creerCompte(new CompteBancaire("John Lennon", 150000));
        compte.creerCompte(new CompteBancaire("Paul McCartney", 950000));
        compte.creerCompte(new CompteBancaire("Ringo Starr,", 20000));
        compte.creerCompte(new CompteBancaire("Georges Harrisson", 100000));
    }
    
  
    
}
