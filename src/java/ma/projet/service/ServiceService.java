/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.service;

import ma.projet.entities.Service;
import java.util.List;
import ma.projet.entities.Employee;
import org.hibernate.Session;
import ma.projet.util.HibernateUtil;

/**
 *
 * @author lenovo
 */


public class ServiceService  extends AbstractFacade<Service>{
    @Override
    public boolean create(Service o) {
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(o);
        session.getTransaction().commit();
        return true;
    }
    @Override
    public boolean update(Service o) {
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(o);
        session.getTransaction().commit();
        return true;
    }
    @Override
    public boolean delete(Service o) {
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(o);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public Service getById(int id) {
        Service salle  = null;
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        salle  = (Service) session.get(Service.class, id);
        session.getTransaction().commit();
        return salle;
    }
    

    @Override
    public List<Service> getAll() {
        
         List <Service> service = null;
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        service  = session.createQuery("from Service").list();
        session.getTransaction().commit();
        return service;
    }
    

    @Override
    protected Class<Service> getEntityClass() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     public List<Object[]> nbServic(){
        List<Object[]> services = null;
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        services  = session.createQuery("select count(s.nom), s.nom from Service s group by s.nom").list();
        session.getTransaction().commit();
        return services;
    }
    
}
