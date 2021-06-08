package org.o7planning.sbmail;

import tn.esprit.spring.control.UserControllerImpl;
import tn.esprit.spring.service.UserService;

public class MyConstants {
	public static final String MY_EMAIL = "mohamedmselmi407@gmail.com";
	 
    // Replace password!!
    public static final String MY_PASSWORD = "50400131";
 
    // And receiver!
    public static final String FRIEND_EMAIL = UserControllerImpl.getT().getEmail();

}
