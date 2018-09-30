package in.mindbug.eventbus_demo;

import java.io.Serializable;

public class LoginSuccessEvent implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String user;

	public LoginSuccessEvent(String user) {
		this.user = user;
	}

	public String getUser() {
		return user;
	}

}
