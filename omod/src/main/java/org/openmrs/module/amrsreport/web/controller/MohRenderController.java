package org.openmrs.module.amrsreport.web.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Location;
import org.openmrs.api.context.Context;
import org.openmrs.module.reporting.cohort.definition.CohortDefinition;
import org.openmrs.module.reporting.report.definition.ReportDefinition;
import org.openmrs.module.reporting.report.definition.service.ReportDefinitionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.openmrs.module.reporting.cohort.definition.service.CohortDefinitionService;
import  org.openmrs.reporting.data.DatasetDefinition;

import java.util.ArrayList;
import java.util.List;


/**
 * Created with IntelliJ IDEA.
 * User: Nicholas Ingosi Magaja
 * Date: 5/30/12
 * Time: 11:51 AM
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class MohRenderController {

    private static final Log log = LogFactory.getLog(MohRenderController.class);

    List<String> cohortdfnList=null;

    @RequestMapping(method=RequestMethod.GET, value="module/amrsreport/mohRender.form")
    public void preparePage(ModelMap map,@RequestParam(required=false, value="includeRetired") Boolean includeRetired) {

        CohortDefinitionService cohortDefinitionService = Context.getService(CohortDefinitionService.class);

        // Get list of existing reports
        boolean includeRet = (includeRetired == Boolean.TRUE);
        List<ReportDefinition> reportDefinitions = Context.getService(ReportDefinitionService.class).getAllDefinitions(includeRet);

        map.addAttribute("reportDefinitions", reportDefinitions);


        List<CohortDefinition> listOfCohorts= cohortDefinitionService.getAllDefinitions(false);

        //get a list of all the locations
        List<Location> locationList=Context.getLocationService().getAllLocations();

        //log.info("all cohorts listed "+listOfCohorts.size()+"All definition "+reportDefinitions.size());
        map.addAttribute("cohortdefinitions", listOfCohorts);
        map.addAttribute("location",locationList);

    }

    @RequestMapping(method=RequestMethod.POST, value="module/amrsreport/mohRender.form")
    public void processForm(ModelMap map){

    }
}
