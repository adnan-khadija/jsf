/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.*;

/**
 *
 * @author Lachgar
 */
@Entity
public class Service  implements Serializable {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int id ;
    private String nom;
    @OneToMany(mappedBy = "service",fetch = FetchType.EAGER)
    private List<Employee> employes;

    public Service() {
    }

    public List<Employee> getEmployes() {
        return employes;
    }

    public void setEmployes(List<Employee> employes) {
        this.employes = employes;
    }

    public Service(String nom) {
        this.nom = nom;
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
//
//    @Override
//    public String toString() {
//        return "Service{" + "id=" + id + ", nom=" + nom + '}';
//    }

    
    
}
