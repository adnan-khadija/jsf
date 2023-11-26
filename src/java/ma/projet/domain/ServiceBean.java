package ma.projet.domain;

import java.io.Serializable;
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

@ManagedBean(name = "serviceBean")
@SessionScoped
public class ServiceBean implements Serializable {

    private ServiceService serviceService;
    private EmployeeService employeeService;
    private Service service;
    private List<Service> services;
    private Employee employee;
    private List<Employee> employees;

    public ServiceBean() {
        serviceService = new ServiceService();
        employeeService = new EmployeeService();
        service = new Service();
        employee = new Employee();
        services = serviceService.getAll();
        loadServices();
    }

    public Employee getEmployee() {
        if (employees == null) {
            employees = service.getEmployes();
        }
        return employee;
    }

    public EmployeeService getEmployeeService() {
        return employeeService;
    }

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
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

    public String createService() {
        if (isValidService(service)) {
            serviceService.create(service);
            service = new Service();
            services = serviceService.getAll();
    }
         return null;
    }

    private boolean isValidService(Service service) {
        return service != null && service.getNom() != null && !service.getNom().trim().isEmpty();
    }

    public void loadServices() {
        // Charger la liste des services
        services = serviceService.getAll();
    }
//    public void deleteService() {
//        service.setEmployes(null);
//        serviceService.delete(service);
//    }
    public String deleteService() {
        serviceService.delete(serviceService.getById(service.getId()));
        loadServices();
        return null;
    }

    public String editService() {
        serviceService.update(service);
        services = serviceService.getAll();
        return null;
    }

    public void onEdit(RowEditEvent event) {
        employee = (Employee) event.getObject();
        Service room = serviceService.getById(this.employee.getService().getId());
        employee.setService(room);
        employee.getService().setNom(service.getNom());
        employeeService.update(employee);
    }

    public ServiceService getServiceService() {
        return serviceService;
    }

    public void setServiceService(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public void onCancel(RowEditEvent event) {
    }
    public ChartModel initBarModel() {
    CartesianChartModel model = new CartesianChartModel();
    model.setAnimate(true);

    for (Service s : serviceService.getAll()) {
        ChartSeries serviceSeries = new ChartSeries();
        serviceSeries.setLabel(s.getNom()); // Label pour chaque service

        // Ajout d'une série pour chaque service avec le nombre d'employés
        serviceSeries.set(s.getNom(), s.getEmployes().size());
        
        // Ajout de la série au modèle
        model.addSeries(serviceSeries);
    }

    return model;
}
public Employee chef(){
    return employeeService.chefByService(service);
}
      
}
