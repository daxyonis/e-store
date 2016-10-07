package com.emaciejko.domain;

import java.util.LinkedHashMap;
import java.util.Map;

import com.emaciejko.util.I18n;

public enum Province {
    BC, AB, SK, MB, ON, QC, NB, NS, PE, NL; 
    
    private static Map<Province, String> mappedNames = new LinkedHashMap<>();
    
    static
    {
	for(Province p : Province.values()){
	    mappedNames.put(p, I18n.getMessage("province." + p.toString().toLowerCase()));
	    }
    }
    
    public static Map<Province, String> getKeyedNames() {
	return mappedNames;
	}
    
    public  String getName(){
	return mappedNames.get(this);
    }
}