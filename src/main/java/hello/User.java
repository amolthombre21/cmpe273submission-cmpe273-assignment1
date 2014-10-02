package hello;

import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;



public class User {
	
    private String user_id;

    @NotEmpty @NotNull @Email
    private String email;

    @NotEmpty @NotNull @Size(min=2)	
    private String password;

    @NotEmpty @NotNull @Size(min=2,max=30)	
    private String name;
    private String created_at;
    private String updated_at;
    private List<IdCard> idCardList;
    
    public User(){
    	
    }
    
    public User(String user_id, String email, String password, String name,String created_at) {
        this.user_id = user_id;
        this.email = email;
        this.password = password;
        this.created_at = created_at;
	this.name=name;
    }
    
    /*public User(String user_id, String email, String password, String created_at, String updated_at) {
        this.user_id = user_id;
        this.email = email;
        this.password = password;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }*/

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}

	public List<IdCard> getIdCardList() {
		return idCardList;
	}

	public void setIdCardList(List<IdCard> idCardList) {
		this.idCardList = idCardList;
	}

}
