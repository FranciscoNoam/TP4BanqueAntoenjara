/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mg.antoenjara.tpbanque.service;
import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import java.util.List;
import mg.antoenjara.tpbanque.entity.CompteBancaire;
/**
 *
 * @author francisco
 */
@DataSourceDefinition (
    className="com.mysql.cj.jdbc.MysqlDataSource",
    name="java:app/jdbc/banque",
    serverName="localhost",
    portNumber=3306,
    user="banque",              // nom et
    password="banque", // mot de passe que vous avez donnés lors de la création de la base de données
    databaseName="banque",
    properties = {
      "useSSL=false",
      "allowPublicKeyRetrieval=true",
      "driverClass=com.mysql.cj.jdbc.Driver"
    }
)

@RequestScoped
public class GestionnaireCompte {
    
    @PersistenceContext(unitName = "banquePU")
    private EntityManager em;

    /**
     * Methode pour faire l'insertion d'une customer
     *
     * @param CompteBancaire
     */
    @Transactional
    public void persist(CompteBancaire compte) {
        em.persist(compte);
    }
    
    public List<CompteBancaire> getAllComptes() {
      Query query = em.createNamedQuery("CompteBancaire.findAll");
       return query.getResultList();
    }

    @Transactional
    public void creerCompte(CompteBancaire c) {
      em.persist(c);
      
    }
    

}
