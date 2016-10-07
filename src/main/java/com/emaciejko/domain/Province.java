package com.emaciejko.domain;

import java.util.LinkedHashMap;
import java.util.Map;

import com.emaciejko.util.I18n;

public enum Province {
    BC, AB, SK, MB, ON, QC, NB, NS, PE, NL; 
    
    private static Map<Province, String> mappedNames  = new LinkedHashMap<>();
    
    /**
     * static initialization
     */
    static
    {	
	mapNames();
    }
    
    /**
     *  (Re)mapping of the province names
     */
    private static void mapNames() {
	mappedNames.clear();
	for(Province p : Province.values()){
	    mappedNames.put(p, I18n.getMessage("province." + p.toString().toLowerCase()));
	    }
    }
    
    /**
     * reset the province naming map
     * useful after programmatically changing the locale 
     */
    public static void reset(){
	I18n.reset();
	mapNames();
    }
    
    /**
     * Static map accessor
     * @return  the map of province enums with the translated province names as values
     * Depends on the locale as given by I18n
     */
    public static Map<Province, String> getKeyedNames() {
	return mappedNames;
	}
    
    /**
     * 
     * @return  this province's name (locale dependent)
     */
    public String getName(){	
	return mappedNames.get(this);
    }
}
