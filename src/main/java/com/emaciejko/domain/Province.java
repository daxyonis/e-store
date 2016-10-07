package com.emaciejko.domain;

import java.util.Arrays;
import java.util.List;
import static java.util.stream.Collectors.toList;

import com.emaciejko.util.I18n;

public enum Province {
    BC, AB, SK, MB, ON, QC, NB, NS, PE, NL;     
    
    public static List<String> getNames() {	    
	    return Arrays.stream(Province.values())
		    	 .map(v -> I18n.getMessage("province." + v.toString().toLowerCase())) 
		    	 .collect(toList());
	}
}
