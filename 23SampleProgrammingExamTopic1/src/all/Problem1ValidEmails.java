package all;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.*;

public class Problem1ValidEmails {

	private static int usernameValid(String username) {
		String regex = "^[A-Za-z_]+$";
		if (!username.matches(regex)) {
			return -1;
		}
		return 0;
	}

	private static int domainValid(String domain) {
		String regex = "^[a-z]+$";
		if (!domain.matches(regex)) {
			return -1;
		}
		if (domain.length() < 2 || domain.length() > 4) {
			return -1;
		}
		return 0;
	}

	private static int hostValid(String host) {
		String regex = "^[a-z]+$";
		if (!host.matches(regex)) {
			return -1;
		}
		return 0;
	}

	private static int validationOfEmail(String email) {
		String[] emailElements = email.split("[@.]");
		if (emailElements.length != 3) {
			return -1;
		}
		String username = emailElements[0];
		String host = emailElements[1];
		String domain = emailElements[2];
		if (usernameValid(username) != 0 || hostValid(host) != 0
				|| domainValid(domain) != 0) {
			return -1;
		}
		return 0;
	}

	private static int validationOfName(String name) {
		String regex = "[a-zA-Z]+\\.?";
		if (!name.matches(regex)) {
			return -1;
		}
		return 0;
	}

	public static void main(String[] args) {
        Scanner reader = null;
        PrintStream writer = null;

        try {
        	reader = new Scanner(new File("test.018.in.txt"), "windows-1251");
            writer = new PrintStream(new File("test.018.out.txt"), "windows-1251");
            String line = "";
            while (true) {
            	
            	if (reader.hasNextLine() == false) {
            		break;
                }
            	line = reader.nextLine();

                String[] elements = line.split(" ");
                if (elements.length < 3) {
                	System.out.println("Error in names");
                	continue;
                }
                String firstName = elements[0];
                String secondName = elements[1];
                String email = elements[2];
                if (validationOfName(firstName) != 0
                		|| validationOfName(secondName) != 0) {
                	System.out.println("Error in names");
                	continue;
                }
                try {
                	if (validationOfEmail(email) != 0) {
                		System.out.println("Error in email syntax");
                        continue;
                    }
                } catch (Exception e) {
                	System.out.println("Error");
                	continue;
                }     
                  
                writer.println(firstName + " " + secondName + " " + email);
            }
        } catch (FileNotFoundException fnfe) {
        	System.out.println("File mails.txt does not exist");
        	System.err.println(fnfe.getMessage());
        } catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} finally {
        	if (!(reader == null)) {
        		reader.close();
        	}
        	if (!(writer == null)) {
            	writer.close();
        	}
        }
    }
}
