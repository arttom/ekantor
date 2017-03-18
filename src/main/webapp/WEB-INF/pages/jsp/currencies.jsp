<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

JEBANIUTKIE WALUTY <br/>
<table>
    <tr>
        <th width="30%">Currency name</th>
        <th width="30%">Currency code</th>
        <th width="20%">Currency buy value</th>
        <th width="20%">Currency update date</th>
    </tr>
    <c:forEach items="${currenciesRates}" var="currency">
        <tr>
            <td>${currency.name}</td>
            <td>${currency.code}</td>
            <td>${currency.purchasePrice}</td>
            <td>${currency.updateDate}</td>
        </tr>
    </c:forEach>
</table>
<script>

</script>