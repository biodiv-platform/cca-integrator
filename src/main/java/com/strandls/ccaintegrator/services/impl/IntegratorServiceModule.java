/**
 * 
 */
package com.strandls.ccaintegrator.services.impl;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.strandls.ccaintegrator.services.IntegratorServices;

/**
 * 
 * @author vilay
 *
 */
public class IntegratorServiceModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(IntegratorServices.class).to(IntegratorServicesImpl.class).in(Scopes.SINGLETON);
	}
}
