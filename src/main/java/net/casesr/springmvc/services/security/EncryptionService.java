package net.casesr.springmvc.services.security;

public interface EncryptionService {
	
	String encryptString(String input);
	
	boolean checkPassword(String plainPassword, String encryptedPassword);

}
