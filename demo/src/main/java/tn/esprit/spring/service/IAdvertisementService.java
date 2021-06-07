package tn.esprit.spring.service;

import java.io.IOException;
import java.util.List;

import tn.esprit.spring.entity.Advertisement;


public interface IAdvertisementService {
	public List<Advertisement> retrieveAllAds() throws IOException;
	
	
}
