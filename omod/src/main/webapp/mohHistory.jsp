<%@ include file="/WEB-INF/template/include.jsp"%>

<%@ include file="/WEB-INF/template/header.jsp"%>
<openmrs:require privilege="Manage AMRSReports" otherwise="/login.htm"
                 redirect="/module/amrsreport/mohHistory.form" />

<openmrs:htmlInclude file="/dwr/util.js"/>
<openmrs:htmlInclude file="/moduleResources/amrsreport/jquery.dataTables.min.js" />
<openmrs:htmlInclude file="/moduleResources/amrsreport/jquery.tools.min.js" />
<openmrs:htmlInclude file="/moduleResources/amrsreport/TableTools/js/TableTools.min.js" />
<openmrs:htmlInclude file="/moduleResources/amrsreport/TableTools/js/ZeroClipboard.js" />
<openmrs:htmlInclude file="/scripts/jquery/dataTables/css/dataTables.css" />
<openmrs:htmlInclude file="/moduleResources/amrsreport/css/smoothness/jquery-ui-1.8.16.custom.css" />
<openmrs:htmlInclude file="/moduleResources/amrsreport/css/dataTables_jui.css" />
<openmrs:htmlInclude file="/moduleResources/amrsreport/TableTools/css/TableTools.css" />
<openmrs:htmlInclude file="/moduleResources/amrsreport/TableTools/css/TableTools_JUI.css" />
<openmrs:htmlInclude file="/dwr/interface/DWRAmrsReportService.js"/>

<script type="text/javascript">
    $j(document).ready(function(){

        var ti = $j('#tablehistory').dataTable({
            "bJQueryUI":false,
            "sPaginationType": "full_numbers",
            "sDom": 'T<"clear">lfrtip',
            "oTableTools": {
                "sSwfPath": "${pageContext.request.contextPath}/moduleResources/amrsreport/media/swf/copy_csv_xls_pdf.swf",
                "sRowSelect": "single",
                "aButtons": [
                     "copy",
                     "print",
                     "csv",
                    {
                        sExtends: "xls",
                        sPdfOrientation: "landscape",
                        sPdfMessage: "Developed by Ampath"
                    },
                    {
                        sExtends: "pdf",
                        sPdfOrientation: "landscape",
                        sPdfMessage: "Developed by Ampath"
                    }
                ]

            }
        });


        $j('#tablehistory').delegate('tbody td #img','click', function() {

           var trow=this.parentNode.parentNode;
            var aData2 = ti.fnGetData(trow);
            var amrsNumber=aData2[1].trim();
            DWRAmrsReportService.viewMoreDetails("${historyURL}", amrsNumber,callback);
            return false;

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
            //alert(data)
            var listSplit=data.split(",");
            var listWithin;
            for(var i=0; i<listSplit.length; i++) {
                var value = listSplit[i]+"\n";
                var value2;

                if(value.indexOf('#') !=0){
                    listWithin=value.split("#");
                      for(var f=0;f<listWithin.length;f++){
                          value2= listWithin[f]+"\n";
                          $j("<table id='tbldata'><thead></t><tbody><tr><td>"+value2+"</td></tr></table>").appendTo("#dlgData");
                      }

                }
                else{

                $j("<div>"+value+"</div>").appendTo("#dlgData");
                }
            }

            $j("#dlgData").dialog("open");


        }


    });
    function clearDataTable(){
        //alert("on change has to take effect");
        dwr.util.removeAllRows("tbodydata");
    }



</script>
<c:if test="${not empty loci}">
<table align="right">
    <tr>
        <td><b>History Report for:</b></td>
        <td><u>${loci}</u></td>
        <td><b>As at:</b></td>
        <td><u>${time}</u></td>
    </tr>
</table>
</c:if>

<%@ include file="localHeader.jsp"%>
<b class="boxHeader">Amrs Reports History</b>
<div class="box" style=" width:99%; height:auto;  overflow-x: auto;">
 <form action="mohHistory.form" method="POST">
    <table>
        <tr>
            <td>Select file For location</td>
            <td>
                <select name="history" id="history"  onchange="clearDataTable()" >
                    <c:forEach var="rpthistory" items="${reportHistory}">
                        <option  value="${rpthistory}">${rpthistory}</option>
                    </c:forEach>
                </select>
            </td>
            <td>
                <input type="submit" value="View" id="collectFile">
            </td>
        </tr>
    </table>
 </form>
</div>
<c:if test="${not empty records}">
<b class="boxHeader">Amrs Reports Details</b>
<div  class="box" style=" width:99%; height:auto;  overflow-x: auto;">

      <table id="tablehistory">
          <thead>
                <tr>
                    <th>View</th>
                    <c:forEach var="col" items="${columnHeaders}">
                        <th>${col}</th>
                    </c:forEach>
                </tr>
          </thead>
          <tbody id="tbodydata">
          <c:forEach var="record" items="${records}">
              <tr>
                  <td><img src="${pageContext.request.contextPath}/moduleResources/amrsreport/images/format-indent-more.png"  id="img" /></td>
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
</c:if>
<div id="dlgData" title="Patients More Information">

</div>


