import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;
import ma.projet.entities.Service;
import ma.projet.service.ServiceService;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartModel;

@ManagedBean(name = "chartBean")
@ViewScoped
public class ChartBean implements Serializable {

    private BarChartModel barModel;
    private ServiceService serviceService;

    public ChartBean() {
        serviceService = new ServiceService();
    }

    @PostConstruct
    public void init() {
        createBarModel();
    }
     public ChartModel initBarModel() {
        CartesianChartModel model = new CartesianChartModel();
        ChartSeries salles = new ChartSeries();
        salles.setLabel("Services");
        model.setAnimate(true);
        for (Service s : serviceService.getAll()) {
            salles.set(s.getNom(), s.getEmployes().size());
        }
        model.addSeries(salles);
        
                
        return model;
    }

    public BarChartModel getBarModel() {
        return barModel;
    }

    private void createBarModel() {
        barModel = new BarChartModel();

        List<Service> services = serviceService.getAll(); // Récupérer les services depuis la base de données

        ChartSeries employesParService = new ChartSeries();
        employesParService.setLabel("Employés par Service");

        for (Service service : services) {
            int nombreEmployes = service.getEmployes().size(); // Nombre d'employés pour chaque service
            employesParService.set(service.getNom(), nombreEmployes);
        }

        barModel.addSeries(employesParService);

        barModel.setTitle("Nombre d'employés par service");
        barModel.setLegendPosition("ne");

        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Services");

        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Nombre d'employés");
        yAxis.setMin(0);
    }
}
