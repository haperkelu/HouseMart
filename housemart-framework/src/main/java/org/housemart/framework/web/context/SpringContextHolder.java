/**
 * 
 */
package org.housemart.framework.web.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author pai.li
 * 
 */
public class SpringContextHolder implements ApplicationContextAware {

	/** */
	private static ApplicationContext context;

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		SpringContextHolder.context = applicationContext;
	}
	
	public static ApplicationContext getApplicationContext(){
		return context;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String id) { 
		
		Object instance = context.getBean(id);
		if(instance != null) {return (T)instance;}
		
		String[] names = context.getAliases(id);
		if(names != null && names.length >= 1){
			return (T) context.getBean(names[0]);
		}
		return null;
    }  
	
}
