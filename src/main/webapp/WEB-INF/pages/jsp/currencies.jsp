<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<table>
    <tr>
        <th width="10%">Currency</th>
        <th width="10%">Unit</th>
        <th width="20%">Value</th>
        <th width="30%">Actions</th>
    </tr>
    <c:forEach items="${currenciesRates}" var="currency">
        <tr>
            <td>${currency.code}</td>
            <td>${currency.unit}</td>
            <td>${currency.purchasePrice}</td>
            <td><a href="${contextPath}/ekantor/buy/${currency.code}">
                <button>Buy</button>
            </a></td>
        </tr>
    </c:forEach>
</table>
<script>
function buy(currencyCode){

}
</script>