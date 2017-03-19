<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
    <c:when test="${not empty userCurrencies}">
        <table>
            <tr>
                <th width="10%">Currency</th>
                <th width="20%">Unit price</th>
                <th width="20%">Amount</th>
                <th width="20%">Value</th>
                <th width="30%">Action</th>
            </tr>

                <c:forEach items="${userCurrencies}" var = "userCurrency">
                    <tr>
                        <td>${userCurrency.currency.code}</td>
                        <td>${userCurrency.currency.sellPrice}</td>
                        <td>${userCurrency.quantity*userCurrency.currency.unit}</td>
                        <td>${userCurrency.currency.sellPrice*userCurrency.quantity}</td>
                        <td><a href="${contextPath}/ekantor/sell/${userCurrency.currency.code}">
                            <button>Sell</button>
                        </a></td>
                    </tr>
                </c:forEach>
        </table>
    </c:when>
    <c:otherwise>
        No currencies on your wallet <br/>
    </c:otherwise>
</c:choose>
Available PLN: ${userMoney}