package control;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
public class StringValidator{
 
	  private Pattern patternUsername;
	  private Pattern patternPassword;
	  private Pattern patternEmail;

	  
	  private Matcher matcher;
 
	  private static final String USERNAME_PATTERN = "^[a-z0-9_-]{3,15}$";
	  private static final String PASSWORD_PATTERN = 
              "^([a-zA-Z0-9@*#]{4,15})$";
	  private static final String EMAIL_PATTERN = 
				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	  
	  public StringValidator(){
		  patternUsername = Pattern.compile(USERNAME_PATTERN);
		  patternPassword = Pattern.compile(PASSWORD_PATTERN);
		  patternEmail = Pattern.compile(EMAIL_PATTERN);
	  }
 

	  public boolean validateUsername(final String username){
 
		  if(username == null)
			  return false;
		  
		  matcher = patternUsername.matcher(username);
		  return matcher.matches();
 
	  }
	  public boolean validatePassword(final String password){
		  
		  if(password == null)
			  return false;
		  
		  matcher = patternPassword.matcher(password);
		  return matcher.matches();
 
	  }
	  public boolean validateEmail(final String email){
		  
		  if(email == null)
			  return false;
		  
		  matcher = patternEmail.matcher(email);
		  return matcher.matches();
 
	  }


}

