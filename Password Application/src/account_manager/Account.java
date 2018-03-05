package account_manager;

import java.util.Random;

public class Account {
	private String association, username, password;
	private static Random rand = new Random();
	
	public Account(String association) {
		this.association = association;
	}
	
	//Mutators
	public void setAssociation(String association) {
		this.association = association;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	//generates password, taking minimums for each category, length, and character omission
	public void generatePassword(int length, int upperCaseMin, int lowerCaseMin, 
			int symbolMin, int digitMin, char ... characterOmission) {
		String newPassword = "";
		
		if(length < (upperCaseMin + lowerCaseMin + symbolMin + digitMin)) {
			length = upperCaseMin + lowerCaseMin + symbolMin + digitMin;
			System.out.println("Increasing password length to meet minimums...");
		}
		
		do {
			if(upperCaseMin > 0) {
				newPassword += randomUpperCase();
				upperCaseMin--;
				length--;
			}
			if(lowerCaseMin > 0) {
				newPassword += randomLowerCase();
				lowerCaseMin--;
				length--;
			}
			if(symbolMin > 0) {
				newPassword += randomSymbol();
				symbolMin--;
				length--;
			}
			if(digitMin > 0) {
				newPassword += randomDigit();
				digitMin--;
				length--;
			}
			if((upperCaseMin == 0)&&(lowerCaseMin == 0)&&(symbolMin == 0)&&(digitMin == 0))
			{
				newPassword += randomChar();
				length--;
			}
		} while(length > 0);
		
		newPassword = scrambleString(newPassword);
		password = newPassword;
	}
	
	//overloaded; same except no character omission
	public void generatePassword(int length, int upperCaseMin, int lowerCaseMin, 
			int symbolMin, int digitMin) {
		String newPassword = "";
		
		if(length < (upperCaseMin + lowerCaseMin + symbolMin + digitMin)) {
			length = upperCaseMin + lowerCaseMin + symbolMin + digitMin;
			System.out.println("Increasing password length to meet minimums..."); 
		}
		
		do {
			if(upperCaseMin > 0) {
				newPassword += randomUpperCase();
				upperCaseMin--;
				length--;
			}
			if(lowerCaseMin > 0) {
				newPassword += randomLowerCase();
				lowerCaseMin--;
				length--;
			}
			if(symbolMin > 0) {
				newPassword += randomSymbol();
				symbolMin--;
				length--;
			}
			if(digitMin > 0) {
				newPassword += randomDigit();
				digitMin--;
				length--;
			}
			if((upperCaseMin == 0)&&(lowerCaseMin == 0)&&(symbolMin == 0)&&(digitMin == 0))
			{
				newPassword += randomChar();
				length--;
			}
		} while(length > 0);
		
		newPassword = scrambleString(newPassword);
		password = newPassword;
	}
	
	//random password generator with only length as a modifier
	public void generatePassword(int length) {
		String newPassword = "";
		do {
			newPassword += randomChar();
			length--;
		} while(length > 0);
		
		/* newPassword = scrambleString(newPassword); */
		password = newPassword;
	}
	
	//Accessors
	public String getAssociation() {
		return association;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	//toString
	public String toString() {
		String result = "";
		result += "Association: " + association 
				+ "\nUsername: " + username
				+ "\nPassword: " + password;
		return result;
	}
	
	//Support methods for password generator
	private static char randomUpperCase() {
		int unicodeRep;
		unicodeRep = rand.nextInt(90-65) + 65; //range of uppercase unicode integers
		return (char) unicodeRep;
	}
	
	private static char randomLowerCase() {
		int unicodeRep;
		unicodeRep = rand.nextInt(122-97) + 97; //range of lowercase unicode integers
		return (char) unicodeRep;
	}
	
	private static char randomSymbol() {
		int unicodeRep;
		do { //range of commonly used symbols
			unicodeRep = rand.nextInt(43-33) + 33;
		} while(unicodeRep == 34||(unicodeRep <= 42&&unicodeRep >= 39));
		return (char) unicodeRep;
	}
	
	private static char randomDigit() {
		int unicodeRep;
		unicodeRep = rand.nextInt(57-48) + 48;
		return (char) unicodeRep;
	}
	
	private static char randomChar() {
		int randomDecision = rand.nextInt(4-1) + 1;
		
		switch(randomDecision)
		{
		case 1:
			return randomUpperCase();
		case 2:
			return randomLowerCase();
		case 3:
			return randomSymbol();
		case 4:
			return randomDigit();
		default:
			return randomChar();
		}
	}
	
	private static String scrambleString(String word) {
		
		char[] characters = word.toCharArray();
		
		for(int i = rand.nextInt(25 - 10) + 10; i > 0; i--) {
			int index1 = rand.nextInt(word.length()-1), index2 = rand.nextInt(word.length()-1);
			
			while(index1 == index2)
				index1 = rand.nextInt(word.length()-1);
			
			char placeHolder;
			
			placeHolder = characters[index1];
			characters[index1] = characters[index2];
			characters[index2] = placeHolder;
			
			
		}
		
		return characters.toString();
	}
}
