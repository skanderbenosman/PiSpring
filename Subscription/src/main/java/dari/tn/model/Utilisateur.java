package dari.tn.model;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public  class Utilisateur implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 537136081490133106L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long utilisateurId;

    @NotBlank(message = "LastName is required")
    private String lastName;
    @NotBlank(message = "FirstName is required")
    private String firstName;
    @NotBlank(message = "Password is required")
    private String password;
    private String phone;
    private Region Region;
    @Email
    @NotEmpty(message = "Email is required")
    private String email;
    private Instant created;
    private boolean subscribed;
    public SubType SubscriptionType;

}

