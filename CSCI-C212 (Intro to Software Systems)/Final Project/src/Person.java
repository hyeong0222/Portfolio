///////////////////////////////////////////////////////////////////
/* C212 Final Project - group 6
 * 
 * Sang Hyeong Woo    sangwoo
 * Jean Chiu    weitchiu
 * Dana Mach    drmach
 * Caroline Cheng     
 * 
 * Last Modified: April 25th
 * 
 * Person Class
 */
////////////////////////////////////////////////////////////////////
import java.util.ArrayList;

public class Person {
	
	private String username;
	private String password;
	
	public Person(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public String getUsername()
	{
		return this.username;
	}
	
	public String getPassword()
	{
		return this.password;
	}
}
