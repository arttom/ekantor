<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

Transaction status: ${transactionStatus}

<c:if test="${transactionStatus == SOLD}">
    You sold ${units} units of  ${currencyCode}
</c:if>