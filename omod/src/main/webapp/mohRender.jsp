<%@ include file="/WEB-INF/template/include.jsp"%>

<%@ include file="/WEB-INF/template/header.jsp"%>
<openmrs:require privilege="Manage AMRSReports" otherwise="/login.htm"
                 redirect="/module/amrsreport/mohRender.form" />


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
    //var $j = jQuery.noConflict();
    $j(document).ready(function(){

        var ty = $j('#tblMain').dataTable({
            "bJQueryUI":false,
            "sPaginationType": "full_numbers",
            "sDom": 'T<"clear">lfrtip',
            "oTableTools": {
                "sRowSelect": "single",
                "aButtons": [
                    "print"
                ]

            }
        } );

        //});

        $j('#tblMain').delegate('tbody td #imgrender','click', function() {
            var trow=this.parentNode.parentNode;
            var aData21 = ty.fnGetData(trow);
            var amrsid1=aData21[1].trim();
            DWRAmrsReportService.viewMoreDetailsRender("${fileToManipulate}",amrsid1,processThis);

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
        function processThis(data){
            $j("#dlgData").empty();
            var listSplit=data.split(",");
            var listWithin;
            for(var i=0; i<listSplit.length; i++) {
                var value = listSplit[i]+"\n";
                var value2;

                if(value.indexOf(';') !=0){
                    listWithin=value.split(";");
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



    });

    function clearDataTable(){
        //alert("on change has to take effect");
        var hidepic= document.getElementById("maindetails");
        var titleheader=document.getElementById("titleheader");
        hidepic.style.display='none';
        titleheader.style.display='none';

    }


</script>
<c:if test="${not empty loci}">
 <div id="titleheader">
    <table align="right">
        <tr>
            <td><b>History Report for:</b></td>
            <td><u>${loci}</u></td>
            <td><b>As at:</b></td>
            <td><u>${time}</u></td>
        </tr>
    </table>
 </div>
</c:if>
<%@ include file="localHeader.jsp"%>
<b class="boxHeader">Amrs Reports Settings</b>
<div class="box" style=" width:99%; height:auto;  overflow-x: auto;">
<form method="POST" name="amrsreportrenderer" action="mohRender.form">
 <table>
     <tr>
        <td><b>Reports:</b></td>
         <td><select name="definition" onchange="clearDataTable()">
                <c:forEach var="rptdefinition" items="${reportDefinitions}">
                    <option  value="${rptdefinition.uuid}" >${rptdefinition.name}</option>
                </c:forEach>
            </select>
        </td>
        <td><b>Cohorts:</b></td>
         <td><select name="cohortdef" onchange="clearDataTable()">
             <c:forEach var="cohortdefinition" items="${cohortdefinitions}">
                 <option  value="${cohortdefinition.uuid}" >${cohortdefinition.name}</option>
             </c:forEach>
         </select>
         </td>
         <td><b>Location:</b></td>
         <td><select name="location" onchange="clearDataTable()">
             <c:forEach var="location" items="${location}" >
                 <option  value="${location.locationId}" >${location.name}</option>
             </c:forEach>
         </select>
         </td>
         <td>&nbsp;</td>
         <td>
             <input type="submit" value="View" />
         </td>
     </tr>
 </table>
</form>
</div>
<c:if test="${not empty records}">
<b class="boxHeader">Report Details</b>
<div class="box" id="maindetails" style=" width:99%; height:auto;  overflow-x: auto;">
    <div id="printbuttons">
    <img src="${pageContext.request.contextPath}/moduleResources/amrsreport/images/pr_csv_file_document.png"  id="csvdownload" width="50" height="50" onclick="window.open('data:application/vnd.ms-excel,' + document.getElementById('tblMain').outerHTML.replace(/ /g, '%20'))"/>
    <img src="${pageContext.request.contextPath}/moduleResources/amrsreport/images/pdf.png"  id="pdfdownload" width="50" height="50" />
    </div>

    <table border="0" id="tblMain">
        <thead>
            <tr>
                <th>View</th>
                <c:forEach var="col" items="${columnHeaders}">
                        <th>${col}</th>
                </c:forEach>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="row" items="${records}">
                <tr>
                    <td><img src="${pageContext.request.contextPath}/moduleResources/amrsreport/images/format-indent-more.png" id="imgrender" /></td>
                    <c:forEach var="cell" items="${row}">
                        <td>${cell}</td>
                    </c:forEach>
                </tr>
            </c:forEach>
        </tbody>
    </table>

</div>
</c:if>
<div id="dlgData" title="Patients More Information">

</div>
<%--<input type="text" id="definitionname">--%>

<%@ include file="/WEB-INF/template/footer.jsp"%>
