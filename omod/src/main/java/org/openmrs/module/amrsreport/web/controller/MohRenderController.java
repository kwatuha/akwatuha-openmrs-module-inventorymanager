package org.openmrs.module.amrsreport.web.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Cohort;
import org.openmrs.Location;
import org.openmrs.api.AdministrationService;
import org.openmrs.api.context.Context;
import org.openmrs.module.reporting.cohort.definition.CodedObsCohortDefinition;
import org.openmrs.module.reporting.cohort.definition.CohortDefinition;
import org.openmrs.module.reporting.cohort.definition.EncounterCohortDefinition;
import org.openmrs.module.reporting.dataset.DataSetUtil;
import org.openmrs.module.reporting.evaluation.EvaluationContext;
import org.openmrs.module.reporting.evaluation.EvaluationException;
import org.openmrs.module.reporting.report.ReportData;
import org.openmrs.module.reporting.report.definition.ReportDefinition;
import org.openmrs.module.reporting.report.definition.service.ReportDefinitionService;
import org.openmrs.module.reporting.report.renderer.CsvReportRenderer;
import org.openmrs.module.reporting.web.renderers.WebReportRenderer;
import org.openmrs.util.OpenmrsUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.openmrs.module.reporting.cohort.definition.service.CohortDefinitionService;
import  org.openmrs.reporting.data.DatasetDefinition;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.*;


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
    public void processForm(ModelMap map,
                            @RequestParam(required=true, value="definition") String definitionuuid,
                            @RequestParam(required=true, value="cohortdef") String cohortdefuuid,
                            @RequestParam(required=true, value="location") Integer location

                            ) {
        Location loc=Context.getLocationService().getLocation(location);
        ReportDefinition reportDefinition=Context.getService(ReportDefinitionService.class).getDefinitionByUuid(definitionuuid);
        CohortDefinition cohortDefinition= Context.getService(CohortDefinitionService.class).getDefinitionByUuid(cohortdefuuid);
       //here to allow the reloading of the screen
        List<CohortDefinition> listOfCohorts=  Context.getService(CohortDefinitionService.class).getAllDefinitions(false);
        List<ReportDefinition> reportDefinitions = Context.getService(ReportDefinitionService.class).getAllDefinitions(true);
        List<Location> locationList=Context.getLocationService().getAllLocations();
        //create a flat file here for storing our report data
        map.addAttribute("reportDefinitions", reportDefinitions);
        map.addAttribute("cohortdefinitions", listOfCohorts) ;
        map.addAttribute("location",locationList);

        try {
            EvaluationContext evaluationContext = new EvaluationContext();

            //add loctation to be displayed here
            evaluationContext.addParameterValue("locationList", Arrays.asList(loc));
            CohortDefinitionService cohortDefinitionService = Context.getService(CohortDefinitionService.class);

            //evaluation
            Cohort cohort = cohortDefinitionService.evaluate(cohortDefinition, evaluationContext);

            evaluationContext.setBaseCohort(cohort);

            Date d= Calendar.getInstance().getTime();
            String TIME;

            Format formatter=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");

            TIME=formatter.format(d);

            AdministrationService as = Context.getAdministrationService();
            String folderName=as.getGlobalProperty("amrsreport.file_dir");

            File loaddir = OpenmrsUtil.getDirectoryInApplicationDataDirectory(folderName);

            ReportDefinitionService reportDefinitionService = Context.getService(ReportDefinitionService.class);
            ReportData reportData = reportDefinitionService.evaluate(reportDefinition, evaluationContext);

            CsvReportRenderer csvReportRenderer= new CsvReportRenderer();

            File amrsreport = new File(loaddir, loc.getName() +TIME+ "-MOH-Register-361A.csv");
            BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(amrsreport));

            csvReportRenderer.render(reportData,"Report information ", outputStream);

            //normal file operations to follow here


            BufferedReader input =  new BufferedReader(new FileReader(amrsreport));
           // DataInputStream dis =new DataInputStream(input);

            // dis.available() returns 0 if the file does not have more lines.
            String line="";
            List<List<String>> records=new ArrayList<List<String>>();
            String [] linedata=null;
            String first=null;
            String second=null;
            String third=null;
            String fourth=null;

            while (( line = input.readLine()) != null){
                List<String> intlist=new ArrayList<String>();
                linedata=line.split(",");
                intlist.add(StringUtils.defaultString(stripLeadingAndTrailingQuotes(linedata[0])));
                intlist.add(StringUtils.defaultString(stripLeadingAndTrailingQuotes(linedata[1])));
                intlist.add(StringUtils.defaultString(stripLeadingAndTrailingQuotes(linedata[2])));
                intlist.add(StringUtils.defaultString(stripLeadingAndTrailingQuotes(linedata[3])));

                records.add(intlist) ;

            }
            map.addAttribute("records",records);
            input.close();
            outputStream.close();


        } catch (EvaluationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }
    static String stripLeadingAndTrailingQuotes(String str)
    {
        if (str.startsWith("\""))
        {
            str = str.substring(1, str.length());
        }
        if (str.endsWith("\""))
        {
            str = str.substring(0, str.length() - 1);
        }
        return str;
    }
}
