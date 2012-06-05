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

<b class="boxHeader">Amrs Reports History</b>
<div class="box">
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
        </tr>
    </table>
</div>
<b class="boxHeader">Amrs Reports Details</b>
<div  class="box">
      <table id="tablehistory">
          <thead>
                <tr>
                    <th></th>
                </tr>
          </thead>
          <tbody>
          <c:forEach var="record" items="${records}">
              <tr>
                  <td>
                 <a href="#"> ${fn:substring(record,0,10)}</a>
                      ${record}
                  </td>
              </tr>
          </c:forEach>

          </tbody>
      </table>
</div>