package com.emaciejko.util;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

@Component
public class PrintBeans {
    
    private ApplicationContext ac;

    @Autowired
    public PrintBeans(ApplicationContext applicationContext) {
	this.ac = applicationContext;
        //System.out.println(Arrays.asList(applicationContext.getBeanDefinitionNames()));
    }
    
    public List<String> getBeanNames() {
	return Arrays.asList(ac.getBeanDefinitionNames());		
    }
    
    public String checkLocaleResolver(){
	Object lr = ac.getBean("localeResolver");
	if(lr instanceof FixedLocaleResolver){
	    return ((FixedLocaleResolver)lr).toString();
	}
	else{
	    return "No instance of FixedLocaleResolver";
	}
    }
    
    
}
