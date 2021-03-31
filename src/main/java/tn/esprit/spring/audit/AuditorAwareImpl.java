package tn.esprit.spring.audit;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import tn.esprit.spring.PiedevDariApplication;

public class AuditorAwareImpl implements AuditorAware<String> {
	
	public static final Logger L=LogManager.getLogger(AuditorAwareImpl.class);
	@Override
	public Optional<String> getCurrentAuditor() {
		
		String name="System";
		
		if(SecurityContextHolder.getContext().getAuthentication().isAuthenticated()){
			
			name=SecurityContextHolder.getContext().getAuthentication().getName();
			
			
			L.info("audiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiit !!! "+name);
		}
		
		return Optional.ofNullable(name);
	}

}
