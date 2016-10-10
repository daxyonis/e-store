package com.emaciejko.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

import com.emaciejko.domain.Province;

@Configuration
public class LocaleReset implements BeanPostProcessor, Ordered{       

    @Override
    public int getOrder() {
	return Ordered.LOWEST_PRECEDENCE;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
	if(beanName.equalsIgnoreCase("dispatcherServlet") || beanName.equalsIgnoreCase("localeResolver")){
	    Province.reset();
	}
	return bean;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {	
	return bean;
    }
}
