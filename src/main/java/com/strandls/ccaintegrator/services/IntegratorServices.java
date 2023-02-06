/**
 * 
 */
package com.strandls.ccaintegrator.services;

import javax.servlet.http.HttpServletRequest;

import com.strandls.ccaintegrator.pojo.UserProfileData;
import com.strandls.user.ApiException;

/**
 * 
 * @author vilay
 *
 */
public interface IntegratorServices {
	
	public UserProfileData fetchUserProfileById(HttpServletRequest request, String userId) throws ApiException;
}
