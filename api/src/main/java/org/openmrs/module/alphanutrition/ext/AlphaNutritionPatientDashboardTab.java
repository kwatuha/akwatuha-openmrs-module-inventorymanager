
/**
 * 
 */
package org.openmrs.module.alphanutrition.ext;

import org.openmrs.module.web.extension.PatientDashboardTabExt;

/**
 * @author Ampath Developers
 *
 */
public class AlphaNutritionPatientDashboardTab extends PatientDashboardTabExt {

	/* (non-Javadoc)
	 * @see org.openmrs.module.web.extension.PatientDashboardTabExt#getPortletUrl()
	 */
	@Override
	public String getPortletUrl() {
		// TODO Auto-generated method stub
		return "patientAlphaNutrition";
	}

	/* (non-Javadoc)
	 * @see org.openmrs.module.web.extension.PatientDashboardTabExt#getRequiredPrivilege()
	 */
	@Override
	public String getRequiredPrivilege() {
		// TODO Auto-generated method stub
		return "Manage AlphaNutrition";
	}

	/* (non-Javadoc)
	 * @see org.openmrs.module.web.extension.PatientDashboardTabExt#getTabId()
	 */
	@Override
	public String getTabId() {
		// TODO Auto-generated method stub
		return "PatientAlphaNutrition";
	}

	/* (non-Javadoc)
	 * @see org.openmrs.module.web.extension.PatientDashboardTabExt#getTabName()
	 */
	@Override
	public String getTabName() {
		// TODO Auto-generated method stub
		return "AlphaNutrition";
	}

}
