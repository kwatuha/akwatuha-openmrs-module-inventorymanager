<%@ include file="/WEB-INF/template/include.jsp"%>

<%@ include file="/WEB-INF/template/header.jsp"%>

<openmrs:htmlInclude file="/scripts/jquery-ui/js/jquery-ui.custom.min.js" />
<openmrs:htmlInclude file="/scripts/jquery/dataTables/js/jquery.dataTables.min.js" />
<openmrs:htmlInclude file="/scripts/jquery/highlight/jquery.highlight-3.js" />
<openmrs:htmlInclude file="/scripts/jquery/dataTables/js/jquery.dataTables.filteringDelay.js" />
<openmrs:htmlInclude file="/scripts/jquery/dataTables/css/dataTables_jui.css" />
<openmrs:htmlInclude file="/scripts/jquery/dataTables/css/dataTables.css" />
<link href="<openmrs:contextPath/>/scripts/jquery-ui/css/<spring:theme code='jqueryui.theme.name' />/jquery-ui.custom.css" type="text/css" rel="stylesheet" />

<b class="boxHeader">Amrs Reports Settings</b>
<div class="box">
<form method="POST" name="amrsreportrenderer">
 <table>
     <tr>
        <td><b>Dataset Definition:</b></td>
         <td><select name="definition">
                <c:forEach var="rptdefinition" items="${reportDefinitions}">
                    <option  value="${rptdefinition.uuid}" >${rptdefinition.name}</option>
                </c:forEach>
            </select>
        </td>
        <td><b>Cohort Definition:</b></td>
         <td><select name="cohortdef">
             <c:forEach var="cohortdefinition" items="${cohortdefinitions}">
                 <option  value="${cohortdefinition.uuid}" >${cohortdefinition.name}</option>
             </c:forEach>
         </select>
         </td>
         <td><b>Location/Site:</b></td>
         <td><select name="location">
             <c:forEach var="location" items="${location}" >
                 <option  value="${location.locationId}" >${location.name}</option>
             </c:forEach>
         </select>
         </td>
         <td>&nbsp;</td>
         <td>
             <input type="submit" value="Generate" />
         </td>
     </tr>
 </table>
</form>
</div>
<b class="boxHeader">Report Details</b>
<div class="box"></div>

<%@ include file="/WEB-INF/template/footer.jsp"%>
