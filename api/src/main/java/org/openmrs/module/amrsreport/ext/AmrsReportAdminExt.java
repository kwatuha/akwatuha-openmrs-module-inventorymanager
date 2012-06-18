package org.openmrs.module.amrsreport.ext;

import org.openmrs.module.Extension;
import org.openmrs.module.web.extension.AdministrationSectionExt;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: oliver
 * Date: 5/30/12
 * Time: 11:57 AM
 * To change this template use File | Settings | File Templates.
 */
public class AmrsReportAdminExt extends AdministrationSectionExt {
    /** Defines the privilege required to the see the Administration section for the module */
    public String getRequiredPrivilege() {
        return "";
    }
    public Extension.MEDIA_TYPE getMediaType(){
        return Extension.MEDIA_TYPE.html;
    }
    /* (non-Javadoc)
      * @see org.openmrs.module.web.extension.AdministrationSectionExt#getLinks()
      */
    @Override
    public Map<String, String> getLinks() {
        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("module/amrsreport/mohRender.form", "Pre-ART 361A Register");
        map.put("module/amrsreport/mohHistory.form", "Reports History");
        /*map.put("module/amrsreport/mohtest.form", "Reports test");*/

        return map;
    }

    /* (non-Javadoc)
      * @see org.openmrs.module.web.extension.AdministrationSectionExt#getTitle()
      */
    @Override
    public String getTitle() {
        return "AMRS Report Module";
    }

}
