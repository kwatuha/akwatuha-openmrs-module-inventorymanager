<%@ include file="/WEB-INF/template/include.jsp"%>

<%@ include file="/WEB-INF/template/header.jsp"%>
<%@ include file="localHeader.jsp"%>

<openmrs:htmlInclude file="/scripts/jquery-ui/js/jquery-ui.custom.min.js" />
<openmrs:htmlInclude file="/scripts/jquery/dataTables/js/jquery.dataTables.min.js" />
<openmrs:htmlInclude file="/scripts/jquery/highlight/jquery.highlight-3.js" />
<openmrs:htmlInclude file="/scripts/jquery/dataTables/js/jquery.dataTables.filteringDelay.js" />
<openmrs:htmlInclude file="/scripts/jquery/dataTables/css/dataTables_jui.css" />
<openmrs:htmlInclude file="/scripts/jquery/dataTables/css/dataTables.css" />
<link href="<openmrs:contextPath/>/scripts/jquery-ui/css/<spring:theme code='jqueryui.theme.name' />/jquery-ui.custom.css" type="text/css" rel="stylesheet" />

<script type="text/javascript">
    $j(document).ready(function(){
        $j('#records').dataTable();
    });
    function moreDetails(){
        $j(function() {
            $j( "#dialogDisplayMore" ).dialog({
                modal: true,
                show: 'slide',
                height: 'auto',
                hide: 'slide',
                width: 750,
                position: 'center'

            });
        });
    }
</script>

<b class="boxHeader">Amrs Reports Settings</b>
<div class="box">
<form method="POST" name="amrsreportrenderer" action="mohRender.form">
 <table>
     <tr>
        <td><b>Reports:</b></td>
         <td><select name="definition">
                <c:forEach var="rptdefinition" items="${reportDefinitions}">
                    <option  value="${rptdefinition.uuid}" >${rptdefinition.name}</option>
                </c:forEach>
            </select>
        </td>
        <td><b>Cohorts:</b></td>
         <td><select name="cohortdef">
             <c:forEach var="cohortdefinition" items="${cohortdefinitions}">
                 <option  value="${cohortdefinition.uuid}" >${cohortdefinition.name}</option>
             </c:forEach>
         </select>
         </td>
         <td><b>Location:</b></td>
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
<div class="box">
    <table border="0" id="records">
        <thead>
            <tr>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="record" items="${records}">
                <tr>
                    <td><input type="button" value="View More" id="details" onclick="moreDetails()"> </td>
                    <c:forEach var="rec" items="${record}">
                        <td>
                            ${rec}
                        </td>
                    </c:forEach>

                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
<div id="dialogDisplayMore" title="More Patient Information Summary" style="display:none">


</div>

<%@ include file="/WEB-INF/template/footer.jsp"%>
