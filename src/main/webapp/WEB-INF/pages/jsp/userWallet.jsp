<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

JEBANIUTKI PORTFEL <br/>
Username: ${username} <br/>
<table>
    <tr>
        <th width="30%">Currency name</th>
        <th width="30%">Currency code</th>
        <th width="20%">Currency sell value</th>
        <th width="10%">Quantity</th>
        <th width="10%">Total Value</th>
    </tr>
    <c:forEach items="${userCurrencies}" var = "userCurrency">
        <tr>
            <td>${userCurrency.currency.name}</td>
            <td>${userCurrency.currency.code}</td>
            <td>${userCurrency.currency.sellPrice}</td>
            <td>${userCurrency.quantity}</td>
            <td>${userCurrency.currency.sellPrice}*${userCurrency.quantity}</td>
        </tr>

    </c:forEach>
</table>