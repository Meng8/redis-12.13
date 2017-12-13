package cn.com.taiji.entity;

import java.io.Serializable;
/**
 * 
 * 类名称：User   
 * 类描述：   
 * 创建人：Meng   
 * 创建时间：2017年12月13日 下午3:42:23 
 * @version
 */
public class User  implements Serializable{
	/**
	 * Description:
	 * @author Meng
	 */
	private static final long serialVersionUID = 3963298612669499734L;
	private String username;
	private String email;
	private String id;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", email=" + email + ", id=" + id + "]";
	}
	public User(String username, String email, String id) {
		super();
		this.username = username;
		this.email = email;
		this.id = id;
	}
	public User() {
		super();
	}
	
	
	

}
