/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.service;

import java.util.List;
import ma.projet.dao.IDao;
import ma.projet.entities.Employee;
import ma.projet.entities.Service;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author lenovo
 */
public class EmployeeService extends AbstractFacade<Employee>{
    @Override
    public boolean create(Employee o) {
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(o);
        session.getTransaction().commit();
        return true;
    }
    @Override
    public boolean update(Employee o) {
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(o);
        session.getTransaction().commit();
        return true;
    }
    @Override
    public boolean delete(Employee o) {
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(o);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public Employee getById(int id) {
        Employee employee  = null;
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        employee  = (Employee) session.get(Employee.class, id);
        session.getTransaction().commit();
        return employee;
    }

    @Override
    public List<Employee> getAll() {
        
         List <Employee> employes = null;
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        employes  = session.createQuery("from Employee").list();
        session.getTransaction().commit();
        return employes;
    }
  
 public List<Employee> getChefs() {
    List<Employee> chefs = null;
     Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
     try {
        
    chefs = session.createQuery(" FROM Employee WHERE Chef is Null").list();
    
    session.getTransaction().commit();
     } catch (Exception e) {
      if (session != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return chefs;
    }

    public Employee chefByService(Service service) {
    Employee chef = null;
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    try {
        chef = (Employee) session.createQuery("FROM Employee WHERE chef is NULL AND service = :service")
                .setParameter("service", service.getId()).uniqueResult();
                
        session.getTransaction().commit();
    } catch (Exception e) {
        if (session != null) {
            session.getTransaction().rollback();
        }
        e.printStackTrace();
    } finally {
        if (session != null) {
            session.close();
        }
    }
    return chef;
}

    @Override
    protected Class<Employee> getEntityClass() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
 public List<Object[]> nbEmployesByService(){
        List<Object[]> employes = null;
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        employes  = session.createQuery("select count(e.nom),service from employee e group by e.service").list();
        session.getTransaction().commit();
        return employes;
    }
 public List<Object[]> nbEmployesByChef(){
        List<Object[]> employes = null;
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        employes  = session.createQuery("select count(e.nom) from employee e where e.chef is not null group by e.chef").list();
        session.getTransaction().commit();
        return employes;
    }
        
}
