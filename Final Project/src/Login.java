import java.io.FileNotFoundException;

public interface Login {

	private void fileCheck(String fileName);
	private void createLayout();
    private void initializeScanner();
	private void loginSuccess(String name);
	private void enroll(String userName, String password);
	private void checkU() throws NullUsernameError, RepeatUsernameError, FileNotFoundException;
	private void checkP() throws PasswordLengthError;
	private void error(int errorCode);
}
