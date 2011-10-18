package org.openmrs.module.amrsreport.service.impl;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Cohort;
import org.openmrs.Concept;
import org.openmrs.Encounter;
import org.openmrs.Location;
import org.openmrs.Obs;
import org.openmrs.OpenmrsObject;
import org.openmrs.api.APIException;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.amrsreport.db.CoreDAO;
import org.openmrs.module.amrsreport.service.CoreService;
import org.openmrs.module.amrsreport.util.FetchRestriction;

/**
 * Actual implementation of the core service contract
 */
public class CoreServiceImpl extends BaseOpenmrsService implements CoreService {

	private static final Log log = LogFactory.getLog(CoreServiceImpl.class);

	private CoreDAO coreDAO;

	/**
	 * Setter for the DAO interface reference that will be called by Spring to inject the actual implementation of the DAO layer
	 *
	 * @param coreDAO
	 * 		the coreDAO to be injected
	 */
	public void setCoreDAO(final CoreDAO coreDAO) {
		if (log.isDebugEnabled())
			log.debug("Wiring up CoreDAO with CoreService ...");
		

		this.coreDAO = coreDAO;
	}

	/**
	 * @see CoreService#getDateCreatedCohort(org.openmrs.Location, java.util.Date, java.util.Date)
	 */
	public Cohort getDateCreatedCohort(final Location location, final Date startDate, final Date endDate) throws APIException {
		return coreDAO.getDateCreatedCohort(location, startDate, endDate);
	}

	/**
	 * @see CoreService#getReturnDateCohort(org.openmrs.Location, java.util.Date, java.util.Date)
	 */
	public Cohort getReturnDateCohort(final Location location, final Date startDate, final Date endDate) throws APIException {
		return coreDAO.getReturnDateCohort(location, startDate, endDate);
	}

	/**
	 * @see CoreService#getObservationCohort(java.util.List, java.util.Date, java.util.Date)
	 */
	public Cohort getObservationCohort(final List<Concept> concepts, final Date startDate, final Date endDate) throws APIException {
		return coreDAO.getObservationCohort(concepts, startDate, endDate);
	}

	/**
	 * @see CoreService#getPatientEncounters(Integer, java.util.Map, org.openmrs.module.amrsreport.util.FetchRestriction)
	 */
	@Override
	public List<Encounter> getPatientEncounters(final Integer patientId, final Map<String, Collection<OpenmrsObject>> restrictions,
	                                            final FetchRestriction fetchRestriction) throws APIException {
		return coreDAO.getPatientEncounters(patientId, restrictions, fetchRestriction);
	}
	/**
	 * @see CoreService#getPatientObservations(Integer, java.util.Map, org.openmrs.module.a.util.FetchRestriction)
	 */
	@Override
	public List<Obs> getPatientObservations(final Integer patientId, final Map<String, Collection<OpenmrsObject>> restrictions,
	                                        final FetchRestriction fetchRestriction) throws APIException {
		return coreDAO.getPatientObservations(patientId, restrictions, fetchRestriction);
	}

	@Override
	public Cohort getChildMOHRegisterCohortWithAge() {
		// TODO Auto-generated method stub
		return coreDAO.getChildMOHRegisterCohortBasedOnObservation();
	}

	@Override

	public Cohort getAdultMOHRegisterCohort() {
		// TODO Auto-generated method stub
		return coreDAO.getAdultMOHRegisterCohort();
	}

	@Override
	public Cohort getChildMOHRegisterCohortBasedOnObservation() {
		// TODO Auto-generated method stub
		return coreDAO.getChildMOHRegisterCohortBasedOnObservation();
	}

	@Override
	public Cohort getCohort(Location location, Date startDate, Date endDate)
			throws APIException {
		// TODO Auto-generated method stub
		return null;
	}
}
