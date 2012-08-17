package org.openmrs.module.amrsreport.web.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

    @RequestMapping(method=RequestMethod.GET, value="module/amrsreport/mohRender.form")
    public void preparePage(ModelMap map) {

    }

    @RequestMapping(method=RequestMethod.POST, value="module/amrsreport/mohRender.form")
    public void processForm(ModelMap map){

    }
}
