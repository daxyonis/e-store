package com.emaciejko.util;

import java.util.Locale;
import java.util.ResourceBundle;

import org.springframework.context.i18n.LocaleContextHolder;

public final class I18n {
    
    private I18n() {
    }

    private static ResourceBundle bundle;

    public static String getMessage(String key) {
        if(bundle == null) {
            bundle = ResourceBundle.getBundle("messages", I18n.getEffectiveLocale());
        }
        return bundle.getString(key);
    }    
    
    public static Locale getEffectiveLocale(){
	switch(LocaleContextHolder.getLocale().getLanguage()){
	case "fr":	
	    return Locale.FRENCH;	    
	default:
	    return Locale.ENGLISH;
	}
    }

}
