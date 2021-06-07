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
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import tn.esprit.spring.entity.Advertisement;
import tn.esprit.spring.entity.Image;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.repository.AdvertisementRepository;




@Controller
@RequestMapping("/Advertisement")
public class AdvertisementServiceImp implements IAdvertisementService {

	@Autowired	
	AdvertisementRepository AdRepository;
	
	@Autowired
    public JavaMailSender emailSender;
	
	@CrossOrigin
	@PostMapping("/AddAdvertisement")
	@ResponseBody
	public String addAdvertisement(@RequestBody Advertisement ad) {
		Date date = new Date();
		ad.setAddDate(date);
		long a=1;
		User u1=new User(a,"Mohamed skander","Ben osman","skanderbenosman6@gmail.com");
		ad.setUser(u1);
		ad.setState(Advertisement.State.Pending);
		ad.setRemoved(false);
		
		AdRepository.save(ad);
		 
		 
		return "Advertisement added with id"+ad.getId();
	}


	
	@CrossOrigin
	@GetMapping("/Advertisements")
	@ResponseBody
	public List<Advertisement>  retrieveAllAds()  {
		List<Advertisement> List= new ArrayList<Advertisement>();
		Iterable<Advertisement> ads =AdRepository.findAll();
		 System.out.println("skander");
		for(Advertisement a: ads){
			if(a.isRemoved()==false && a.getState()!=Advertisement.State.Blocked){
			List.add(a);
			System.out.println("skander"+a.getImage().size());}
		
		}
		
		return List ;
	}
	@CrossOrigin
	@GetMapping("/Advertisements2")
	@ResponseBody
	public List<Advertisement>  retrieveAllAds2()  {
		List<Advertisement> List= new ArrayList<Advertisement>();
		Iterable<Advertisement> ads =AdRepository.findAll();
		 System.out.println("skander");
		for(Advertisement a: ads){
			if(a.isRemoved()==false && a.getState()==Advertisement.State.Available){
			List.add(a);
			System.out.println("skander"+a.getImage().size());}
		
		}
		
		return List ;
	}
	
	
	@CrossOrigin
	@DeleteMapping("/DeleteAd/{id}")
	@ResponseBody
	public String delete(@PathVariable Long id) {
		Optional<Advertisement> ad=AdRepository.findById(id);
		if (ad.isPresent()){
			Advertisement objet = ad.get();
			objet.setRemoved(true);
			AdRepository.save(objet);}
		return "Ad deleted with id : "+id;		
	}

	@CrossOrigin
	@PostMapping("/updateAdvertisement/{id}")
	@ResponseBody
	public String updateAdvertisement(@PathVariable Long id,@RequestBody Advertisement ad) {
		
		Optional<Advertisement> ads = AdRepository.findById(id);
		if (ads.isPresent()){
			Advertisement objet = ads.get();
			objet.setAddress(ad.getAddress());
			objet.setArea(ad.getArea());
			objet.setGarage(ad.isGarage());
			objet.setGarden(ad.isGarden());
			objet.setPrice(ad.getPrice());
		
	}
		


return "aa";
	
}
	
	/*@PostMapping("/aa")
	@ResponseBody
    public String saveUser(
            @RequestParam("image") MultipartFile multipartFile) throws IOException {
         
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        Long a=(long) 1;
        Optional<Advertisement> ads = AdRepository.findById(a);
        Advertisement aa=ads.get();
        aa.setPhotos(fileName);
        Advertisement sads=AdRepository.save(aa);
        String uploadDir = "user-photos/" + sads.getId();
 
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
         
        return fileName;
    }*/
	@PostMapping("/uploadFiles")
	@ResponseBody
	public Advertisement uploadFiles(@RequestParam("image") MultipartFile[] files) throws IOException {
		Long t=(long) 10;
		Optional<Advertisement> ads=AdRepository.findById(t);
		Advertisement ad=ads.get();
	    for(MultipartFile f: files){
	    	String fileName = StringUtils.cleanPath(f.getOriginalFilename());
	    	System.out.println("testtt"+fileName);
	        /*Long a=(long) 1;
	        Optional<Advertisement> ads = AdRepository.findById(a);
	        Advertisement ad=ads.get();*/
	        Set<Image> images=ad.getImage();
	        Image img =new Image();
	        img.setName(fileName);
	        images.add(img);
	        ad.setImage(images);
	        Advertisement sads=AdRepository.save(ad);
	        String uploadDir = "user-photos/" + sads.getId();
	 
	        FileUploadUtil.saveFile(uploadDir, fileName, f);
	    }
	 
	    
	 
	    return ad;
	}

	@PostMapping("/uploadFiles2")
	@ResponseBody
	public Advertisement uploadFiles2(@RequestParam("image") MultipartFile[] files,@RequestBody Advertisement ad) throws IOException {
		Date date = new Date();
		ad.setAddDate(date);
		long a=1;
		User u1=new User(a,"Mohamed skander","Ben osman","skanderbenosman6@gmail.com");
		ad.setUser(u1);
		ad.setState(Advertisement.State.Available);
		ad.setRemoved(false);
	    for(MultipartFile f: files){
	    	
	    	String fileName = StringUtils.cleanPath(f.getOriginalFilename());
	    	System.out.println("testtt"+fileName);
	        /*Long a=(long) 1;
	        Optional<Advertisement> ads = AdRepository.findById(a);
	        Advertisement ad=ads.get();*/
	        Set<Image> images=ad.getImage();
	        Image img =new Image();
	        img.setName(fileName);
	        images.add(img);
	        ad.setImage(images);
	        Advertisement sads=AdRepository.save(ad);
	        String uploadDir = "user-photos/" + sads.getId();
	 
	        FileUploadUtil.saveFile(uploadDir, fileName, f);
	    }
	 
	    
	 
	    return ad;
	}

	@CrossOrigin
	@GetMapping("/UserAdvertisements")
	@ResponseBody
	public List<Advertisement>  retrieveUserAds() {
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
		return List ;
	}
	@GetMapping("/displayBarGraph")
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
	@CrossOrigin
	@PostMapping("/updateStateAdvertisement/{id}")
	@ResponseBody
	public String updateStateAdvertisement(@PathVariable Long id) {
		
		Optional<Advertisement> ads = AdRepository.findById(id);
		if (ads.isPresent()){
			Advertisement objet = ads.get();
			objet.setState(Advertisement.State.Available);
			String email=objet.getUser().getEmail();
			String titre=objet.getTitle();
			String msg="your advertisement"+titre+" was added successfully";
			AdRepository.save(objet);
			 SimpleMailMessage message = new SimpleMailMessage();
	         
		        message.setTo(email);
		        message.setSubject("Test Simple Email");
		        message.setText(msg);
		 
		        // Send Message!
		        this.emailSender.send(message);
			
		
	}
		


return "aa";
	
}
	@CrossOrigin
	@PostMapping("/updateVNBAdvertisement/{id}")
	@ResponseBody
	public void updateVNBAdvertisement(@PathVariable Long id) {
		
		Optional<Advertisement> ads = AdRepository.findById(id);
		if (ads.isPresent()){
			Advertisement objet = ads.get();
			int a=objet.getVnb();
			objet.setVnb(a+1);
			AdRepository.save(objet);
	}
		



	
}
	public List<Advertisement> Search (String word) {
		return (List<Advertisement>) AdRepository.searchEvent(word);
	}
	public List<Advertisement> Search2 (String word2) {
		return (List<Advertisement>) AdRepository.searchEvent2(word2);
	}

	@PostMapping("/uploadFile")
	 @ResponseBody
   public ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile[] file,@RequestParam("title") String title,@RequestParam("type") String type,@RequestParam("address") String address,@RequestParam("description") String description,@RequestParam("swimmingPool") String swimmingPool
   		,@RequestParam("garage") String garage,@RequestParam("garden") String garden,@RequestParam("price") float price) throws IOException, URISyntaxException {
		Advertisement ad=new Advertisement();
		Date date = new Date();
		ad.setAddDate(date);
		long a=1;
		if(Advertisement.Type.Exchange.toString().equals(type)){ad.setType(Advertisement.Type.Exchange);}
		if(Advertisement.Type.Sell.toString().equals(type)){ad.setType(Advertisement.Type.Sell);}
		if(Advertisement.Type.Rent.toString().equals(type)){ad.setType(Advertisement.Type.Rent);}
		User u1=new User(a,"Mohamed skander","Ben osman","skanderbenosman6@gmail.com");
		ad.setUser(u1);
		ad.setState(Advertisement.State.Pending);
		ad.setRemoved(false);
		ad.setTitle(title);
	  	ad.setAddress(address);
	  	ad.setDescription(description);
	  	if(swimmingPool.equals("TRUE")){ad.setSwimmingPool(true);}else{ad.setSwimmingPool(false);}
	  	if(garden.equals("TRUE")){ad.setGarden(true);}else{ad.setGarden(false);}
	  	if(garage.equals("TRUE")){ad.setGarage(true);}else{ad.setGarage(false);}
	  
	  	ad.setPrice(price);
	  	Set<Image> images= new HashSet<Image>();;
		for(MultipartFile f: file){
	    	
	    	String fileName = StringUtils.cleanPath(f.getOriginalFilename());
	    	System.out.println("sss=="+fileName);
	    	
	        Image img =new Image();
	        img.setName(fileName);
	        images.add(img);
	        
	       
	        String uploadDir = "src/main/webapp/images/" + u1.getId();
	 
	        FileUploadUtil.saveFile(uploadDir, fileName, f);
	 
	    	}
		ad.setImage(images);
		 Advertisement sads=AdRepository.save(ad);
		 URI uri = new URI("http://localhost:8083/SpringMVC/userAds.jsf?faces-redirect=true");
		    HttpHeaders httpHeaders = new HttpHeaders();
		    httpHeaders.setLocation(uri);
		    return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);

		 
		
		
   }
		}	
