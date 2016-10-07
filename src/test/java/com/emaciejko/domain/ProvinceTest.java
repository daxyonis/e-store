package com.emaciejko.domain;

import static org.junit.Assert.*;

import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.junit.Test;
import org.springframework.context.i18n.LocaleContextHolder;

import com.emaciejko.util.I18n;

public class ProvinceTest {
    
    protected void testKeyedNames(Locale locale){
	
	// Set the locale
	LocaleContextHolder.setLocale(locale);	
	// Get the right message bundle
	ResourceBundle bundle = ResourceBundle.getBundle("messages", I18n.getEffectiveLocale());
	
	// Reset Province to take new locale into account
	// This is needed in testing since we programmatically change the locale;
	// when running the application a locale is automatically set 
	// at some point; however this method must be invoked whenever a change of locale
	// happens in the application programmatically	
	Province.reset();			
	
	// Get the province names and compare them to the bundle result
	Map<Province, String> m = Province.getKeyedNames();
	m.forEach((p,n) -> assertEquals(bundle.getString("province." + p.toString().toLowerCase()),n));
    }
    
    @Test
    public void getKeyedNamesInCaFrenchTest(){
	testKeyedNames(new Locale("fr","CA"));	
    }      
    
    @Test
    public void getKeyedNamesInFrFrenchTest(){
	testKeyedNames(new Locale("fr","FR"));	
    }
    
    @Test
    public void getKeyedNamesInCaEnglishTest(){
	testKeyedNames(new Locale("en","CA"));	
    }       
    
    @Test
    public void getKeyedNamesInUsEnglishTest(){
	testKeyedNames(new Locale("en","US"));	
    }
      
}


