<%@ include file="/WEB-INF/template/include.jsp"%>

<%@ include file="/WEB-INF/template/header.jsp"%>
<openmrs:require privilege="Manage AMRSReports" otherwise="/login.htm"
                 redirect="/module/amrsreport/mohHistory.form" />
<%@ include file="localHeader.jsp"%>
<openmrs:htmlInclude file="/dwr/util.js"/>
<openmrs:htmlInclude file="/scripts/jquery-ui/js/jquery-ui.custom.min.js" />
<openmrs:htmlInclude file="/scripts/jquery/dataTables/js/jquery.dataTables.min.js" />
<openmrs:htmlInclude file="/scripts/jquery/highlight/jquery.highlight-3.js" />
<openmrs:htmlInclude file="/scripts/jquery/dataTables/js/jquery.dataTables.filteringDelay.js" />
<openmrs:htmlInclude file="/scripts/jquery/dataTables/css/dataTables_jui.css" />
<openmrs:htmlInclude file="/scripts/jquery/dataTables/css/dataTables.css" />
<openmrs:htmlInclude file="/dwr/interface/DWRAmrsReportService.js"/>
<link href="<openmrs:contextPath/>/scripts/jquery-ui/css/<spring:theme code='jqueryui.theme.name' />/jquery-ui.custom.css" type="text/css" rel="stylesheet" />

<script type="text/javascript">
    $j(document).ready(function(){
        var ti = $j('#tablehistory').dataTable({
            "bAutoWidth": true,
            "bLengthChange": true,
            "sPaginationType": "full_numbers"
        });

        $j('#tablehistory').delegate('tbody tr','click', function() {
            var aData2 = ti.fnGetData(this);
            var amrsid=aData2[1].trim();
            var  filename=dwr.util.getValue("history");
            DWRAmrsReportService.viewMoreDetails(filename,amrsid,callback);

        });
        $j("#dlgData" ).dialog({
            autoOpen:false,
            modal: true,
            show: 'slide',
            height: 'auto',
            hide: 'slide',
            width:600,
            cache: false,
            position: 'top',
            buttons: {
                "Exit": function () { $j(this).dialog("close"); }
            }

        });
        function callback(data){
            $j("#dlgData").empty();
            var listSplit=data.split(",");
            var listWithin;
            for(var i=0; i<listSplit.length; i++) {
                var value = listSplit[i]+"\n";
                var value2;

                if(value.indexOf('#') !=0){
                    listWithin=value.split("#");
                      for(var f=0;f<listWithin.length;f++){
                          value2= listWithin[f]+"\n";
                          $j("<div>"+value2+"</div>").appendTo("#dlgData");
                      }

                }
                else{

                $j("<div>"+value+"</div>").appendTo("#dlgData");
                }
            }

            $j("#dlgData").dialog("open");


        }
        function clearDataTable(){
            dwr.util.removeAllRows("tbodydata");
        }

    });



</script>
<b class="boxHeader">Amrs Reports History</b>
<div class="box">
 <form action="mohHistory.form" method="POST">
    <table>
        <tr>
            <td>Select file For location</td>
            <td>
                <select name="history" id="history" onchange="clearDataTable()">
                    <c:forEach var="rpthistory" items="${reportHistory}">
                        <option  value="${rpthistory}" >${rpthistory}</option>
                    </c:forEach>
                </select>
            </td>
            <td>
                <input type="submit" value="Run">
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
                    <th>View</th>
                    <th>Amrs Identifier</th>
                    <th>Names</th>
                    <th>Unique Identifier</th>
                    <th>Age at Enrollment</th>
                </tr>
          </thead>
          <tbody id="tbodydata">
          <c:forEach var="record" items="${records}">
              <tr>
                  <td><img src="${pageContext.request.contextPath}/moduleResources/amrsreport/images/format-indent-more.png" /></td>
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
<div id="dlgData" title="Patients More Information">

</div>
