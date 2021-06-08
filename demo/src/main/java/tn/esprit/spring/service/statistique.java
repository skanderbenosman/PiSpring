package tn.esprit.spring.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.donut.DonutChartDataSet;
import org.primefaces.model.charts.donut.DonutChartModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.repository.AppointmentRepository;

@Service
@Transactional
public class statistique {
	@Autowired
	AppointmentRepository rep;
	
	private DonutChartModel donutModel;
	

	
	
	

	public DonutChartModel getDonutModel() {
		return donutModel;
	}

	public void setDonutModel(DonutChartModel donutModel) {
		this.donutModel = donutModel;
	}

	@PostConstruct
    public void init() {
        
        createDonutModel();
     
        
    }
	
	

	public void createDonutModel() {
        donutModel = new DonutChartModel();
        ChartData data = new ChartData();

        DonutChartDataSet dataSet = new DonutChartDataSet();
        List<Number> values = new ArrayList<>();
        float s=0;
 
     s=rep.nbAppointment();
     System.out.println("rani nbAppointment ");

     System.out.println(s);

     //   values.add(s); //on obtient la somme des coasts des projets de cat info
        float r=0;
     r=rep.nbAppointmenttypeFACETOFACE("FACE_TO_FACE");
    
     System.out.println("rani nbAppointmenttypeFACETOFACE ");
     System.out.println(r);
        values.add(r);//on obtient la somme des coasts des projets de cat agriculture
        float rr=0;
      rr=rep.nbAppointmenttypeONLINE("ONLINE");
      System.out.println("rani nbAppointmenttypeONLINE ");

      System.out.println(rr);
        values.add(rr);//on obtient la somme des coasts des projets de cat agriculture

        dataSet.setData(values);

        List<String> bgColors = new ArrayList<>();
        bgColors.add("rgb(255, 99, 132)");
        bgColors.add("rgb(54, 162, 235)");
 
        dataSet.setBackgroundColor(bgColors);

        data.addChartDataSet(dataSet);
        List<String> labels = new ArrayList<>();
        labels.add("FACE_TO_FACE");
        labels.add("ONLINE");
   
       
        data.setLabels(labels);

        donutModel.setData(data);
    }
	
	

	
	
	
}
