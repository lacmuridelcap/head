package org.mifos.framework.security.util;

import java.util.List;
import java.util.Set;

import org.mifos.application.rolesandpermission.business.RoleBO;
import org.mifos.framework.MifosTestCase;

public class SecurityHelperTest extends MifosTestCase {

	public void testGetUserRoles() throws Exception{
		Set<RoleBO> userRoles= SecurityHelper.getUserRoles((short)1);
		assertEquals(1,userRoles.size());
	}
	
	public void testGetPersonnelOffices() throws Exception{
		List<OfficeSearch> officeSearchList = SecurityHelper.getPersonnelOffices(Short.valueOf("1"));
		assertEquals(3,officeSearchList.size());
	}
	
	public void testGetOffices() throws Exception{
		List<OfficeSearch> officeSearchList = SecurityHelper.getOffices();
		assertEquals(3,officeSearchList.size());
	}
		
}
