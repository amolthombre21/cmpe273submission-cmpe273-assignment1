package hello;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

import org.hibernate.validator.*;



@RestController
public class UserController extends WebMvcConfigurerAdapter {
	final AtomicLong userId = new AtomicLong();

	Map<String, User> userMap = new HashMap<String, User>();
	SimpleDateFormat sdf = null;
	Date date = null;

    @RequestMapping(value="/api/v1/users", method=RequestMethod.POST, consumes="application/json")
    public ResponseEntity<User> createUser(@RequestBody @Valid User user, BindingResult bindingResult) 
{
    	
    	if (bindingResult.hasErrors()) 
	{
    		return new ResponseEntity<User>(user, HttpStatus.BAD_REQUEST);
        } 
	else 
	{


    	Long newUserId = userId.incrementAndGet();
    	String newUserIdStr = "u-" + newUserId;
    	date = new Date();
    	sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
    	user.setCreated_at(sdf.format(date));
    	User newUser = new User(newUserIdStr, user.getEmail(), user.getPassword(),user.getName(), user.getCreated_at());
    	userMap.put(newUserIdStr, newUser);

	return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
        }
    }
    
    @RequestMapping(value="/api/v1/users/{user_id}", method=RequestMethod.GET, produces="application/json")
    public User viewUser(@PathVariable String user_id) {
    	return userMap.get(user_id);
    }
    
    @RequestMapping(value="/api/v1/users/{user_id}", method=RequestMethod.PUT)
    public ResponseEntity<User> updateUser(@RequestBody @Valid User user, @PathVariable String user_id,BindingResult bindingResult) 
{
	
	if (bindingResult.hasErrors()) 
	{
    		return new ResponseEntity<User>(user, HttpStatus.BAD_REQUEST);
        }
	else
	{	 
    	date = new Date();
    	sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
    	User getExistingUser = userMap.get(user_id);
    	getExistingUser.setEmail(user.getEmail());
    	getExistingUser.setPassword(user.getPassword());
    	getExistingUser.setUpdated_at(sdf.format(date));

    	return new ResponseEntity<User>(getExistingUser, HttpStatus.CREATED);
	}
    }
    
}
