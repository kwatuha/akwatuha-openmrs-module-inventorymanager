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
import org.springframework.util.StringUtils;
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
        List<String> records=new ArrayList<String>();
        String [] linedata=null;
        String first=null;
        String second=null;
        String third=null;
        String fourth=null;
        try{
            FileInputStream fstream = new FileInputStream(history);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = br.readLine()) != null)   {
                linedata=line.split(",");
                first=linedata[0];
                second=linedata[1];
                third=linedata[2];
                fourth=linedata[3];

                records.add(first+"     "+second+"      "+third+"       "+fourth);
            }
            map.addAttribute("records",records);
            fstream.close();
            in.close();
            br.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

}
