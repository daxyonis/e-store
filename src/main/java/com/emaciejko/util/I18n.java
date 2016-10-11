package com.emaciejko.util;

import java.util.Locale;
import java.util.ResourceBundle;

import org.springframework.context.i18n.LocaleContextHolder;

public final class I18n {       
    
    private I18n() {
    }    
    
    private static ResourceBundle bundle;
    
    public static void reset(){
	bundle = ResourceBundle.getBundle("messages", getEffectiveLocale());
    }

    public static String getMessage(String key) {
        if(bundle == null) {
            reset();
        }   
        return bundle.getString(key);
    }    
    
    public static Locale getEffectiveLocale(){	
	String lang = LocaleContextHolder.getLocale().getLanguage();
	switch(lang){
	case "fr":
	case "fr_FR":
	case "fr_CA":
	    return Locale.FRANCE;	    
	default:
	    return Locale.US;
	}
    }

}
