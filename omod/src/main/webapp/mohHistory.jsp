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
        $j('#tablehistory').dataTable();
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
<b class="boxHeader">Amrs Reports History</b>
<div class="box">
 <form action="mohHistory.form" method="POST">
    <table>
        <tr>
            <td>Select file For location</td>
            <td>
                <select name="history">
                    <c:forEach var="rpthistory" items="${reportHistory}">
                        <option  value="${rpthistory}" >${rpthistory}</option>
                    </c:forEach>
                </select>
            </td>
            <td>
                <input type="submit" value="Generate Report">
            </td>
        </tr>
    </table>
 </form>
</div>
<b class="boxHeader">Amrs Reports Details</b>
<div  class="box">
      <table id="tablehistory">
          <thead>
                <tr>
                    <th>&nbsp;</th>
                    <th>&nbsp;</th>
                    <th>&nbsp;</th>
                    <th>&nbsp;</th>
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

    <div id="dialogDisplayMore" title="More Patient Information Summary" style="display:none">


    </div>
</div>
