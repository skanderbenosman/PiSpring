package tn.esprit.spring.control;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.FilesUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.primefaces.util.LangUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;




import tn.esprit.spring.entity.Advertisement;
import tn.esprit.spring.entity.Image;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.entity.Advertisement.State;
import tn.esprit.spring.repository.AdvertisementRepository;
import tn.esprit.spring.service.AdvertisementServiceImp;



@Controller(value = "AdsController") // Name of the bean in Spring IoC
@ELBeanName(value = "AdsController") // Name of the bean used by JSF
@Join(path = "/", to = "/Ads.jsf")
@MultipartConfig
@RequestScoped
public class AdsControllerImpl   {
	@Autowired
	AdvertisementServiceImp advs;
	@Autowired	
	AdvertisementRepository AdRepository;
	 
	public Set<Image> Image2;
	private Long id;
	private List<Advertisement> test=new ArrayList<Advertisement>();
	private String address;
    private String description;
    private String title; 
    private boolean swimmingPool;
    private boolean garden;
    private float price;
    private float area;
    private int nbRoom;
    private boolean garage;
    private Set<Image> Image;
    private String Name;
    private State state;
    private String tes;
    private List<Advertisement> filteredCustomers1;

  
    
    
    private UploadedFile file;
    private Advertisement selectedProduct;
    
    
    
   




	





	public List<Advertisement> getFilteredCustomers1() {
		return filteredCustomers1;
	}





	public void setFilteredCustomers1(List<Advertisement> filteredCustomers1) {
		this.filteredCustomers1 = filteredCustomers1;
	}





	public Set<Image> getImage2() {
		return Image2;
	}





	public void setImage2(Set<Image> image2) {
		Image2 = image2;
	}





	public Advertisement getSelectedProduct() {
		return selectedProduct;
	}





	public void setSelectedProduct(Advertisement selectedProduct) {
		this.selectedProduct = selectedProduct;
	}
	 public void openNew() {
	        this.selectedProduct = new Advertisement();
	    }




	public State getState() {
		return state;
	}





	public void setState(State state) {
		this.state = state;
	}





	public String getTes() {
		return tes;
	}





	public void setTes(String tes) {
		this.tes = tes;
	}





	public String getName() {
		return Name;
	}





	public void setName(String name) {
		Name = name;
	}





	public Set<Image> getImage() {
		return Image;
	}





	public void setImage(Set<Image> image) {
		Image = image;
	}
	 public UploadedFile getFile() {
	        return file;
	    }

	    public void setFile(UploadedFile file) {
	        this.file = file;
	    }





	public List<Advertisement> getEmployes()  {
		 test = advs.retrieveAllAds();
		// System.out.println("skandercaaaaaaaaaa");
		 for(Advertisement z:test){
			 //System.out.println("aa123"+z.getImage().size());
			 Image=z.getImage();
			 for(Image t:Image){
				 System.out.println("aa"+t.getName());
				 z.setTes(t.getName());
			 }
			 
		 }
		 //System.out.println("aa"+test.get(0).getImage().size());
		 
		 return test;
		
		 }
	public  List<Advertisement> getEmployes2()  {
		test = advs.retrieveAllAds2();
		// System.out.println("skandercaaaaaaaaaa");
		 for(Advertisement z:test){
			 //System.out.println("aa123"+z.getImage().size());
			 Image=z.getImage();
			 for(Image t:Image){
				 System.out.println("aa"+t.getName());
				 z.setTes(t.getName());
			 }
			 
		 }
		 //System.out.println("aa"+test.get(0).getImage().size());
		 
		 return test;
		
		 }
	
	
	

	

	public float getPrice() {
		return price;
	}





	public void setPrice(float price) {
		this.price = price;
	}





	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	public List<Advertisement> getTest() {
		return test;
	}





	public void setTest(List<Advertisement> test) {
		this.test = test;
	}





	public boolean isGarage() {
		return garage;
	}
	public void setGarage(boolean garage) {
		this.garage = garage;
	}
	public boolean isGarden() {
		return garden;
	}
	public void setGarden(boolean garden) {
		this.garden = garden;
	}
	
	public float getArea() {
		return area;
	}
	public void setArea(float area) {
		this.area = area;
	}
	public int getNbRoom() {
		return nbRoom;
	}
	public void setNbRoom(int nbRoom) {
		this.nbRoom = nbRoom;
	}
	public boolean isSwimmingPool() {
		return swimmingPool;
	}
	public void setSwimmingPool(boolean swimmingPool) {
		this.swimmingPool = swimmingPool;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String addAdv()  {
		
		Advertisement ad=new Advertisement();
		ad.setTitle(title);
		ad.setAddress(address);
		ad.setDescription(description);
		ad.setSwimmingPool(swimmingPool);
		ad.setGarage(garage);
		ad.setGarden(garden);
		ad.setPrice(price);
		//System.out.println("testt"+price);
				//save();
		//ad.setPrice(price);
		if (file != null) {
	           
			System.out.println(file.getFileName()); 
        }
if(ad.getTitle()!=null && ad.getAddress()!=null ){	
	System.out.println("testt222");

	advs.addAdvertisement(ad);
	
}
		
title=null;
address=null;
description=null;
price=0;
swimmingPool=false;
garage=false;
garden=false;
String navigateTo = "/Ads.xhtml?faces-redirect=true";
return navigateTo;
	}
	
	 
	 
	 
	 
	
	 public String removeAds(Long id)
	 {System.out.println("skandercaaaaaaaaaa"+id);
	 advs.delete(id);
	 getEmployes();
	 return "/Ads.xhtml?faces-redirect=true";
	 }
	 public String deleteProduct() {
		 advs.updateVNBAdvertisement(selectedProduct.getId());
		Image2=selectedProduct.getImage();
		
		
		 System.out.println("downs"+selectedProduct.getTitle());
		
		 
		
		 return "/aa.xhtml?faces-redirect=true";
	 }
	 public Set<Image> deleteProduct2() {
			Image2=selectedProduct.getImage();
			
			
			 System.out.println("downs"+selectedProduct.getTitle());
			
			 
			
			 return Image2;
		 }
	/* public String deleteProduct() throws URISyntaxException {
		 System.out.println("downs");
		 System.out.println("downs"+selectedProduct.getTitle());
		 advs.delete(selectedProduct.getId()); 
		
		 return "/aa.xhtml?faces-redirect=true";
	 }*/
	 public String ajout()
	 {String navigateTo = "/AddAds.xhtml?faces-redirect=true";
	 return navigateTo;
	 }
		
		public String barGraph(Model model) {
			List<Advertisement> List= new ArrayList<Advertisement>();
			long b=1;
			User u1=new User(b,"Mohamed skander","Ben osman","skanderbenosman6@gmail.com");
			Iterable<Advertisement> ads =AdRepository.findByUser(u1);
			 System.out.println("skander");
			for(Advertisement a: ads){
				if(a.isRemoved()==false && a.getState()!=Advertisement.State.Blocked){
				List.add(a);
				System.out.println(a.toString());}
			
			}
			Map<String, Integer> surveyMap = new LinkedHashMap<>();
			for(Advertisement a: List){
			surveyMap.put(a.getTitle(), a.getVnb());
			}
			model.addAttribute("surveyMap", surveyMap);
			return "barGraph";
		}
		public String updateStateAds(Long id)
		 {System.out.println("skandercaaaaaaaaaa"+id);
		 advs.updateStateAdvertisement(id);
		 getEmployes();
		 return "";
		 }
		

		public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
	        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
	        if (LangUtils.isValueBlank(filterText)) {
	            return true;
	        }
	        

	        Advertisement customer = (Advertisement) value;
	        return customer.getTitle().toLowerCase().contains(filterText);
	               
	    }
	   
	       
	    
	}


