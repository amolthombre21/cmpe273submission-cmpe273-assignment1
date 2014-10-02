package hello;

import java.util.Collection;

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
public class WebLoginController
{
	final AtomicLong login_id=new AtomicLong();
	Map<String,WebLogin> webloginmap=new HashMap();
	@RequestMapping(value="/api/v1/users/{user_id}/weblogins", method=RequestMethod.POST, consumes="application/json")
	
	public ResponseEntity<WebLogin> create_weblogin(@RequestBody WebLogin weblogin, BindingResult bindingResult)
	{

		if (bindingResult.hasErrors()) 
		{
    			return new ResponseEntity<WebLogin>(weblogin, HttpStatus.BAD_REQUEST);
        	} 
		else 
		{
		Long newlogin_id = login_id.incrementAndGet();
    		String newloginidstr = "i-" + newlogin_id;
		//idcard.setCard_id(newcardidstr);
		//idcard.setCard_name(idcard.getCard_name());
		//idcard.setCard_nuber(idcard.getCard_number());
		WebLogin newweblogin=new WebLogin(newloginidstr,weblogin.getUrl(),weblogin.getLogin(),weblogin.getPassword());
		webloginmap.put(newloginidstr,newweblogin);
		return new ResponseEntity<WebLogin>(newweblogin, HttpStatus.CREATED);
		}
	}

	@RequestMapping(value="/api/v1/users/{user_id}/weblogins", method=RequestMethod.GET, produces="application/json")
	public Collection viewall_weblogins()
	{
		return webloginmap.values();
	}
	@RequestMapping(value="/api/v1/users/{user_id}/weblogins/{login_id}",method=RequestMethod.DELETE)
	public void delete_weblogin(@PathVariable String user_id,@PathVariable String login_id)
	{
		webloginmap.remove(login_id);
	}
}
