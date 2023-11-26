/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 *
 * @author lenovo
 */
@Entity
public class Employee implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    private String prenom;
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;
    private String photo;
    @ManyToOne
    @JoinColumn(name = "Chef")
    private Employee em;
    @ManyToOne
    @JoinColumn(name = "service")
    private Service service ;
    public Employee() {
      service=new Service();
     
    }

    public Employee(String nom, String prenom, Date dateNaissance, String photo, Service service) {
        this();
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.photo = photo;
        this.service = service;
    }

    public Employee(String nom, String prenom, Date dateNaissance, String photo, Employee em, Service service) {
        this(nom, prenom, dateNaissance, photo, service);
        this.em = em;
    }


    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

//    @Override
//    public String toString() {
//        if(em!=null)
//          return "Employee{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", dateNaissance=" + dateNaissance + ", photo=" + photo + ", em=" + em.nom + ", service=" + service.getNom() + '}';
//        else
//            return "Chef{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", dateNaissance=" + dateNaissance + ", photo=" + photo +  ", service=" + service.getNom() + '}';
//    }

    

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Employee getEm() {
        return em;
    }

    public void setEm(Employee em) {
        this.em = em;
    }
    
    
          
    
}
