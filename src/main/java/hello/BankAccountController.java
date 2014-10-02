
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
public class BankAccountController
{
	final AtomicLong ba_id=new AtomicLong();
	Map<String,BankAccount> bankaccountmap=new HashMap();
	@RequestMapping(value="/api/v1/users/{user_id}/bankaccounts", method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<BankAccount> create_bankaccount(@RequestBody @Valid BankAccount bankaccount,BindingResult bindingResult)
	{

		if (bindingResult.hasErrors()) 
		{
    			return new ResponseEntity<BankAccount>(bankaccount, HttpStatus.BAD_REQUEST);
        	} 
		else 
		{		
	
		Long newba_id = ba_id.incrementAndGet();
    		String newbastr = "b-" + newba_id;
		//idcard.setCard_id(newcardidstr);
		//idcard.setCard_name(idcard.getCard_name());
		//idcard.setCard_nuber(idcard.getCard_number());
		BankAccount newbankaccount=new BankAccount(newbastr,bankaccount.getAccount_name(),bankaccount.getRouting_number(),bankaccount.getAccount_number());
		bankaccountmap.put(newbastr,newbankaccount);
		return new ResponseEntity<BankAccount>(newbankaccount, HttpStatus.CREATED);
		}
	}

	@RequestMapping(value="/api/v1/users/{user_id}/bankaccounts", method=RequestMethod.GET, produces="application/json")
	public Collection viewall_bankaccounts()
	{
		return bankaccountmap.values();
	}
	@RequestMapping(value="/api/v1/users/{user_id}/bankaccounts/{ba_id}",method=RequestMethod.DELETE)
	public void delete_bankaccount(@PathVariable String user_id,@PathVariable String ba_id)
	{
		bankaccountmap.remove(ba_id);
	}
}
