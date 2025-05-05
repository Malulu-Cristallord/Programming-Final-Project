
public interface Login {

	public void fileCheck(String fileName);
	public void createFw();
	public void createLayout();
	public void loginSuccess();
	public void enroll(String userName, String password);
	public void checkU();
	public void checkP();
	public void error(int errorCode);
}
