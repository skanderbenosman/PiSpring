package tn.esprit.spring.service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;



import tn.esprit.spring.entity.Advertisement;
import tn.esprit.spring.entity.Image;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.repository.AdvertisementRepository;

@Controller
public class GraphController {
	@Autowired	
	AdvertisementRepository AdRepository;
	
	@GetMapping("/displayBarGraph")
	public String barGraph(Model model) {
		Map<String, Integer> surveyMap = new LinkedHashMap<>();
		surveyMap.put("Java", 40);
		surveyMap.put("Dev oops", 25);
		surveyMap.put("Python", 20);
		surveyMap.put(".Net", 15);
		model.addAttribute("surveyMap", surveyMap);
		return "barGraph";
	}

	@GetMapping("/displayPieChart")
	public String pieChart(Model model) {
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
		for(Advertisement a: List){
			
			}
		model.addAttribute("pass", 30);
		model.addAttribute("fail", 70);
		return "pieChart";
	}
	
	
	/*@PostMapping("/uploadFile")
	 @ResponseBody
    public ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile[] file,@RequestParam("title") String title,@RequestParam("address") String address,@RequestParam("description") String description,@RequestParam("swimmingPool") boolean swimmingPool
    		,@RequestParam("garage") boolean garage,@RequestParam("garden") boolean garden,@RequestParam("price") float price) throws IOException, URISyntaxException {
		Advertisement ad=new Advertisement();
		Date date = new Date();
		ad.setAddDate(date);
		long a=1;
		User u1=new User(a,"Mohamed skander","Ben osman","skanderbenosman6@gmail.com");
		ad.setUser(u1);
		ad.setState(Advertisement.State.Pending);
		ad.setRemoved(false);
		ad.setTitle(title);
	  	ad.setAddress(address);
	  	ad.setDescription(description);
	  	ad.setSwimmingPool(swimmingPool);
	  	ad.setGarage(garage);
	  	ad.setGarden(garden);
	  	ad.setPrice(price);
	  	Set<Image> images= new HashSet<Image>();;
		for(MultipartFile f: file){
	    	
	    	String fileName = StringUtils.cleanPath(f.getOriginalFilename());
	    	System.out.println("sss=="+fileName);
	    	
	        Image img =new Image();
	        img.setName(fileName);
	        images.add(img);
	        
	       
	        String uploadDir = "user-photos/" + u1.getId();
	 
	        FileUploadUtil.saveFile(uploadDir, fileName, f);
	 
	    	}
		ad.setImage(images);
		 Advertisement sads=AdRepository.save(ad);
		 URI uri = new URI("http://localhost:8083/SpringMVC");
		    HttpHeaders httpHeaders = new HttpHeaders();
		    httpHeaders.setLocation(uri);
		    return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);

		 
		
		
    }*/
	
	
	

}
