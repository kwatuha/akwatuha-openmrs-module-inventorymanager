package org.openmrs.module.amrsreport.web.controller;

import org.apache.commons.lang.StringUtils;
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

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Nicholas Ingosi Magaja
 * Date: 6/5/12
 * Time: 8:44 AM
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class MohHistoryController {
    private static final Log log = LogFactory.getLog(MohHistoryController.class);

    @RequestMapping(method=RequestMethod.GET, value="module/amrsreport/mohHistory.form")
    public void preparePage(ModelMap map)  {
        String filename="";
        List<String> filenames=new ArrayList<String>();

        AdministrationService as = Context.getAdministrationService();
        String folderName=as.getGlobalProperty("amrsreport.file_dir");

        File fileDir = OpenmrsUtil.getDirectoryInApplicationDataDirectory(folderName);

        String[] children = fileDir.list();
        if (children == null) {
            // Either dir does not exist or is not a directory
        } else {
            for (int i=0; i<children.length; i++) {
                // Get filename of file or directory
                filename = children[i];
                filenames.add(filename);
            }
        }

        map.addAttribute("reportHistory",filenames);

    }

    @RequestMapping(method=RequestMethod.POST, value="module/amrsreport/mohHistory.form")
    public void processForm(ModelMap map,
                            @RequestParam(required=true, value="history") String history

    ) {

        ////////to be used for populating the interface
        String filename="";
        List<String> filenames=new ArrayList<String>();

        AdministrationService as = Context.getAdministrationService();
        String folderName=as.getGlobalProperty("amrsreport.file_dir");

        File fileDir = OpenmrsUtil.getDirectoryInApplicationDataDirectory(folderName);

        String[] children = fileDir.list();
        if (children == null) {
            // Either dir does not exist or is not a directory
        } else {
            for (int i=0; i<children.length; i++) {
                // Get filename of file or directory
                filename = children[i];
                filenames.add(filename);
            }
        }

        map.addAttribute("reportHistory",filenames);

        ///end of interface population after submitting
        List<List<String>> records=new ArrayList<List<String>>();
        String [] linedata=null;
        String first=null;
        String second=null;
        String third=null;
        String fourth=null;
        try{
            File amrsFile=new File(fileDir,history);
            FileInputStream fstream = new FileInputStream(amrsFile);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = br.readLine()) != null)   {

                List<String> intlist=new ArrayList<String>();
                linedata=line.split(",");
                intlist.add(StringUtils.defaultString(stripLeadingAndTrailingQuotes(linedata[0])));
                intlist.add(StringUtils.defaultString(stripLeadingAndTrailingQuotes(linedata[1])));
                intlist.add(StringUtils.defaultString(stripLeadingAndTrailingQuotes(linedata[2])));
                intlist.add(StringUtils.defaultString(stripLeadingAndTrailingQuotes(linedata[3])));


                records.add(intlist) ;
            }
            records.remove(0);
            map.addAttribute("records",records);
            fstream.close();
            in.close();
            br.close();
        }
        catch (Exception e){
            e.printStackTrace();
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
