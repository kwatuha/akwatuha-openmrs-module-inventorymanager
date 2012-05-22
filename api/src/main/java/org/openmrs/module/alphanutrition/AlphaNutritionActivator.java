
package org.openmrs.module.alphanutrition;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.module.Activator;


/**
 * 
 */

/**
 * @author Ampath Developers
 *
 */
public class AlphaNutritionActivator implements Activator {
	private static Log log = LogFactory.getLog( AlphaNutritionActivator.class);
	/* (non-Javadoc)
	 * @see org.openmrs.module.Activator#startup()
	 */
	@Override
        public void startup() {
		log.info("Starting AlphaNutrition module");
	}
	
	/* (non-Javadoc)
	 * @see org.openmrs.module.Activator#shutdown()
	 */
        @Override
	public void shutdown() {
		log.info("Stopping AlphaNutrition module");
	}
	
}

