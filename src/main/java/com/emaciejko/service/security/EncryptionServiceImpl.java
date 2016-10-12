package com.emaciejko.service.security;

import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EncryptionServiceImpl implements EncryptionService {

    private StrongPasswordEncryptor strongEncryptor;
    
    @Autowired
    public void setStrongPasswordEncryptor(StrongPasswordEncryptor se){
	this.strongEncryptor = se;
    }
    
    @Override
    public String encryptString(String input) {
	return strongEncryptor.encryptPassword(input);
    }

    @Override
    public boolean checkPassword(String plainPassword, String encryptedPassword) {
	return strongEncryptor.checkPassword(plainPassword, encryptedPassword);
    }

}
