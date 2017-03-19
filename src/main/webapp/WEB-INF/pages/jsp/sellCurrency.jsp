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
<div id = "userCurrency">

</div>
Units amount: <input id ="units" type="number" placeholder="Units amount" value="1"/><label>*${currencyUnit}</label> <br/>

<button onclick="sellCurrency()">Sell</button>

<br/>
<a href="${contextPath}/ekantor">
    <button>Back</button>
</a> <br/>
<div id="buyResult">

</div>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script>
    function sellCurrency(){
        var currencyCode="${currencyCode}";
        var units=$("#units").val();
        $.ajax({
            url : "${contextPath}/sellCurrency",
            type : "POST",
            data : {currencyCode: currencyCode, units: units},
            success : function(response) {
                $('#buyResult').html(response);
            },
            error : function() {
                alert("opps.....");
            }
        });
    }
</script>

<script>
    function refreshCurrency(){
        $.get("${contextPath}/getUserCurrencyInfo/${currencyCode}",function(data){
            $('#userCurrency').html(data);
            setTimeout(refreshCurrency,5000);
        });
    }
    window.onload = function(){
        refreshCurrency();
    }
</script>
</html>