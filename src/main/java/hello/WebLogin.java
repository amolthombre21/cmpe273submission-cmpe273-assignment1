package hello;

public class WebLogin
{
	private String login_id;
	private String url;
	private String login;
	private String password;

	public WebLogin()
	{
	}
	
	public WebLogin(String login_id,String url,String login,String password)
	{
		this.login_id=login_id;
		this.url=url;
		this.login=login;
		this.password=password;
	}
	public String getLogin_id()
	{
		return this.login_id;
	}
	public String getUrl()
	{
		return this.url;
	}
	public String getLogin()
	{
		return this.login;
	}
	public String getPassword()
	{
		return this.password;
	}
	public void setLogin_id(String login_id)
	{
		this.login_id=login_id;
	}
	public void setUrl(String url)
	{
		this.url=url;
	}
	public void setLogin(String login)
	{
		this.login=login;
	}
	public void setPassword(String password)
	{
		this.password=password;
	}
	

}
