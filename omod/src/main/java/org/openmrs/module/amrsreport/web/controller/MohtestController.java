package org.openmrs.module.amrsreport.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created with IntelliJ IDEA.
 * User: oliver
 * Date: 6/15/12
 * Time: 9:28 AM
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class MohtestController {
    @RequestMapping(method= RequestMethod.GET, value="module/amrsreport/mohtest.form")
    public void preparePage(ModelMap map,@RequestParam(required=false, value="includeRetired") Boolean includeRetired) {

    }
}
