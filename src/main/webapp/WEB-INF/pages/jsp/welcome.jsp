<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>

    <title>Exchange</title>

</head>
<body>
<div class="container">

    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

        <h2> EXCHANGE </h2> <br/>
        <h2> Logged in as ${pageContext.request.userPrincipal.name} | <a onclick="document.forms['logoutForm'].submit()">Logout</a> </h2>

    </c:if>

</div>

<table>
    <tr>
        <td width="50%">
        <div id="currencies" />
        </td>
        <td width="50%">
        <div id="userWallet"/>
        </td>
    </tr>
</table>
<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
<script>
    function refreshCurrencies(){
        $.get("${contextPath}/getCurrentExchangeRates",function(data){
            $('#currencies').html(data);
            setTimeout(refreshCurrencies,5000);
        });
    }
    function refreshUserWallet(){
        $.get("${contextPath}/getUserWallet",function(data){
            $('#userWallet').html(data);
        });
    }

    function startRefresh(){

    }
    window.onload = function(){
        refreshCurrencies();
        refreshUserWallet();
    }
</script>
</body>
</html>
