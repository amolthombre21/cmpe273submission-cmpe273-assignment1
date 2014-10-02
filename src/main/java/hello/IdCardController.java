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
public class IdCardController
{
	final AtomicLong card_id=new AtomicLong();
	Map<String,IdCard> idcardmap=new HashMap();
	SimpleDateFormat sdf = null;
	Date date = null;
	@RequestMapping(value="/api/v1/users/{user_id}/idcards", method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<IdCard> create_idcard(@RequestBody @Valid IdCard idcard,BindingResult bindingResult)
	{
		if (bindingResult.hasErrors()) 
		{
    			return new ResponseEntity<IdCard>(idcard, HttpStatus.BAD_REQUEST);
        	} 
		else 
		{		
		
		Long newcard_id = card_id.incrementAndGet();
    		String newcardidstr = "c-" + newcard_id;
		//idcard.setCard_id(newcardidstr);
		//idcard.setCard_name(idcard.getCard_name());
		//idcard.setCard_nuber(idcard.getCard_number());
		date = new Date();
    		sdf = new SimpleDateFormat("MM/DD/YYYY");
    		idcard.setExpiration_date(sdf.format(date));
		IdCard newidcard=new IdCard(newcardidstr,idcard.getCard_name(),idcard.getCard_number(),idcard.getExpiration_date());
		idcardmap.put(newcardidstr,newidcard);
		return new ResponseEntity<IdCard>(newidcard, HttpStatus.CREATED);
		}
	}

	@RequestMapping(value="/api/v1/users/{user_id}/idcards", method=RequestMethod.GET, produces="application/json")
	public Collection viewall_idcards()
	{
		return idcardmap.values();
	}
	@RequestMapping(value="/api/v1/users/{user_id}/idcards/{card_id}",method=RequestMethod.DELETE)
	public void delete_idcard(@PathVariable String user_id,@PathVariable String card_id)
	{
		idcardmap.remove(card_id);
	}
}
