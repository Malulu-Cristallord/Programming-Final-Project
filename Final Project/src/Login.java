import java.io.FileNotFoundException;

public interface Login {

	public void fileCheck(String fileName);
	public void createLayout();
    public void initializeScanner();
	public void loginSuccess(String name);
	public void enroll(String userName, String password);
	public void checkU() throws NullUsernameError, RepeatUsernameError, FileNotFoundException;
	public void checkP() throws PasswordLengthError;
	public void error(int errorCode);
}
