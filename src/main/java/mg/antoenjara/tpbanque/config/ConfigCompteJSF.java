/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.antoenjara.tpbanque.config;

import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.servlet.ServletContext;
import jakarta.transaction.Transactional;
import mg.antoenjara.tpbanque.entity.Compte;
import mg.antoenjara.tpbanque.service.GestionnaireCompte;


import jakarta.enterprise.context.ApplicationScoped;

/**
 * Configuration JSF
 * @author PC
 */

public class ConfigCompteJSF {

    public ConfigCompteJSF(){}
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
        // Créer 4 Compte
        compte.creerCompte(new Compte("John Lennon", 150000));
        compte.creerCompte(new Compte("Paul McCartney", 950000));
        compte.creerCompte(new Compte("Ringo Starr,", 20000));
        compte.creerCompte(new Compte("Georges Harrisson", 100000));
    }
    
    
}
