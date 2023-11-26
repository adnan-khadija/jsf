package ma.projet.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import ma.projet.entities.Employee;
import ma.projet.entities.Service;
import ma.projet.service.EmployeeService;
import ma.projet.service.ServiceService;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartModel;
import org.primefaces.model.chart.ChartSeries;

@ManagedBean(name ="employeeBean")
@SessionScoped
public class EmployeeBean implements Serializable {

    private ServiceService ss;
    private EmployeeService es;
    private Service service;
    private Employee employee;
    private List<Employee> employees;
    private List<Service> services;
    private List<Employee> chefs;
    private List<Employee> chefByService;

    public EmployeeBean() {
        ss = new ServiceService();
        es = new EmployeeService();
        employee = new Employee();
        employees = new ArrayList<>(); // Initialiser avec une liste vide
        services = new ArrayList<>(); // Initialiser avec une liste vide
        chefs = new ArrayList<>();
        chefByService = new ArrayList<>();
        loadEmployees();
        loadServices();
        loadChefs();
        loadChefsByService();
    }

//    public void handleFileUpload(FileUploadEvent event) {
//    UploadedFile uploadedFile = event.getFile();
//    String fileName = uploadedFile.getFileName(); // Obtenez le nom du fichier
//    
//    employee.setPhoto(fileName);
//
//}
    public void onCancel(RowEditEvent event) {
    }
    public void loadChefsByService() {
        es.chefByService(service);
    }

    public void loadChefs() {
        // Charger la liste des chefs
        chefs = es.getChefs();
    }

    public void loadServices() {
        // Charger la liste des services
        
        services = ss.getAll();
    }

    public List<Employee> getChefs() {
        return chefs;
    }

    public void setChefs(List<Employee> chefs) {
        this.chefs = chefs;
    }

 
    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public ServiceService getSs() {
        return ss;
    }

    public void setSs(ServiceService ss) {
        this.ss = ss;
    }

    public EmployeeService getEs() {
        return es;
    }

    public void setEs(EmployeeService es) {
        this.es = es;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
// public String createEmployee() {
//        if (isValidEmployee()) {
//            employee.setService(service);
//            es.create(employee);
//            employee = new Employee();
//            loadEmployees();
//            FacesContext.getCurrentInstance().addMessage(null,
//                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Succès", "Employé créé avec succès."));
//        } else {
//            FacesContext.getCurrentInstance().addMessage(null,
//                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur", "Veuillez remplir tous les champs."));
//        }
//        return null;
//    }
    public String creatEmployee() {
        es.create(employee);
        employee = new Employee();
        loadEmployees();
        return null;
    }


    public String deleteEmployee() {
        es.delete(es.getById(employee.getId()));
        loadEmployees();
        return null;
    }

    public void loadEmployees() {
        employees = es.getAll();
    }

    public String editEmployee() {
        es.update(employee);
        return "pageSucces";
    }

    private boolean isValidEmployee() {
        return employee != null
                && employee.getNom() != null && !employee.getNom().trim().isEmpty()
                && employee.getPrenom() != null && !employee.getPrenom().trim().isEmpty()
                && employee.getDateNaissance() != null // Vérifier si la date de naissance est renseignée
                && employee.getPhoto() != null && !employee.getPhoto().trim().isEmpty()
                && employee.getService() != null; // Vérifier si le service est renseigné
                
    }
  

}
