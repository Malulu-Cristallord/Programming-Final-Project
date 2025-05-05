import java.util.ArrayList;
public class User {
	private ArrayList<String> username;
	private ArrayList<String> password;
	
	public User() {
	username = new ArrayList<String>();
	password = new ArrayList<String>();
	}
	
	public void add(String name, String pw) throws PasswordError, UserError{
	if (name.length() == 0) throw new UserError("Username can't be empty");
	if (pw.length() != 8) throw new PasswordError("Password should be 8 letter");
	username.add(name);
	password.add(pw);
	return;
	}
	
	public void checkUserExist(String name) throws UserError {
	if(username.contains(name)) return;
	throw new UserError("Can't find the user");
	}
	
	public void checkPassword(String name, String PW) throws PasswordError {
	int id = username.indexOf(name);
	if(password.get(id).equals(PW)) return;
	throw new PasswordError("Password is wrong");
	}
	
}

class UserError extends Exception {
	public UserError(String Error) {
		super(Error);
	}
}

class PasswordError extends Exception {
	public PasswordError(String Error) {
		super(Error);
	}
}
