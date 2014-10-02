/**
 * 
 */
package hello;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Varun
 *
 */
public class AllUsersInfo {
	private Map<String, User> userMap = new HashMap<String, User>();
    
    public AllUsersInfo(){
    	
    }

	public Map<String, User> getUserMap() {
		return userMap;
	}

	public void setUserMap(Map<String, User> userMap) {
		this.userMap = userMap;
	}
}
