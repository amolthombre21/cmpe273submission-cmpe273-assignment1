package hello;

import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;


public class BankAccount
{
	private String ba_id;
	
	@NotEmpty @NotNull 
	private String account_name;
	private String routing_number;
	
	@NotEmpty @NotNull 
	private String account_number;

	public BankAccount()
	{
	}
	
	public BankAccount(String ba_id,String account_name,String routing_number,String account_number)
	{
		this.ba_id=ba_id;
		this.account_name=account_name;
		this.routing_number=routing_number;
		this.account_number=account_number;
	}
	public String getBa_id()
	{
		return this.ba_id;
	}
	public String getAccount_name()
	{
		return this.account_name;
	}
	public String getRouting_number()
	{
		return this.routing_number;
	}
	public String getAccount_number()
	{
		return this.account_number;
	}
	public void setBa_id(String ba_id)
	{
		this.ba_id=ba_id;
	}
	public void setAccount_name(String account_name)
	{
		this.account_name=account_name;
	}
	public void setRouting_number(String routing_number)
	{
		this.routing_number=routing_number;
	}
	public void setAccount_number(String account_number)
	{
		this.account_number=account_number;
	}
}
