/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mg.antoenjara.tpbanque.service;

import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.util.List;
import mg.antoenjara.tpbanque.entity.Compte;

/**
 *
 * @author francisco
 */
@DataSourceDefinition(
        className = "com.mysql.cj.jdbc.MysqlDataSource",
        name = "java:app/jdbc/banque",
        serverName = "localhost",
        portNumber = 3306,
        user = "banque", // nom et
        password = "banque", // mot de passe que vous avez donnés lors de la création de la base de données
        databaseName = "banque",
        properties = {
            "useSSL=false",
            "allowPublicKeyRetrieval=true",
            "driverClass=com.mysql.cj.jdbc.Driver"
        }
)

//@RequestScoped
@ApplicationScoped
public class GestionnaireCompte {

    @PersistenceContext(unitName = "banquePU")
    private EntityManager em;

    /**
     * Methode pour faire l'insertion d'une customer
     *
     * @param Compte
     */
    @Transactional
    public void persist(Compte compte) {
        em.persist(compte);
    }

    public List<Compte> getAllComptes() {
        String requete = "SELECT c FROM Compte c ";
        TypedQuery<Compte> query = em.createQuery(requete, Compte.class);
        List<Compte> liste = query.getResultList();
        return liste;
    }

    public Compte findById(Long idCompte) {
    String requete = "SELECT c FROM Compte c" +" WHERE c.id= :compte";
    TypedQuery<Compte> query = em.createQuery(requete, Compte.class);
    query.setParameter("compte", idCompte);
    try {
        Compte data =  query.getSingleResult();
        return data;
    } catch (NoResultException e) {
        return null;
    }
}

    @Transactional
    public void creerCompte(Compte c) {
        em.persist(c);

    }

    @Transactional
    public void modifieCompte(Compte c) {
        em.merge(c);

    }

    public long nbComptes() {
        Object data = em.createQuery("SELECT COUNT(c) FROM Compte c").getSingleResult();
        long result = (long) data;
        return result;
    }
    
      @Transactional
    public void delete(Compte c){
        //em.remove(c);
         em.remove(em.merge(c));
    }

}
