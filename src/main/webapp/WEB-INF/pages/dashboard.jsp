<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="row mt-4">
    <h2 class="mb-4 mt-4 text-center">
        <i class='bx' style="font-size: 3rem; margin-right: 12px"></i>
        Đơn hàng gần đây
    </h2>
    <hr>
    <table id="recentlyOrderTable" class="table table-striped display nowrap">
        <thead>
        <tr>
            <th>ID</th>
            <th>Mã đơn hàng</th>
            <th>Loại đơn hàng</th>
            <th>Ngày đặt</th>
            <th>Trạng thái</th>
            <th>Người đặt</th>
            <th>Chức năng</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${recentlyOrders}" var="order" varStatus="loop">
            <tr data-id="${order.id}" data-index="${loop.index}">
                <td>${order.id}</td>
                <td>${order.orderNumber}</td>
                <td>
                    <c:choose>
                        <c:when test="${ order.type == 'INBOUND' }">
                            <span>${order.type.getDisplayName()}</span>
                        </c:when>
                        <c:when test="${ order.type == 'OUTBOUND' }">
                            <span>${order.type.getDisplayName()}</span>
                        </c:when>
                    </c:choose>
                </td>
                <td>
                    <fmt:parseDate value="${ order.createdAt }" pattern="yyyy-MM-dd HH:mm:ss" var="parsedDateTime" type="both"/>
                    <fmt:formatDate pattern="dd-MM-yyyy HH:mm:ss" value="${ parsedDateTime }"/>
                </td>
                <td>
                    <c:choose>
                        <c:when test="${ order.status == 'PENDING' }">
                            <span>${order.status.getDisplayName()}</span>
                        </c:when>
                        <c:when test="${ order.status == 'CONFIRMED' }">
                            <span>${order.status.getDisplayName()}</span>
                        </c:when>
                        <c:when test="${ order.status == 'SHIPPED' }">
                            <span>${order.status.getDisplayName()}</span>
                        </c:when>
                        <c:when test="${ order.status == 'DELIVERED' }">
                            <span>${order.status.getDisplayName()}</span>
                        </c:when>
                        <c:when test="${ order.status == 'CANCELLED' }">
                            <span>${order.status.getDisplayName()}</span>
                        </c:when>
                        <c:when test="${ order.status == 'RETURNED' }">
                            <span>${order.status.getDisplayName()}</span>
                        </c:when>
                    </c:choose>
                </td>
                <td>${order.user.username}</td>
                <td>
                    <a href="<c:url value="/admin/orders/edit/${order.id}"/>" type="button" class="btn btn-outline-success">Xem chi tiết</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<script src="<c:url value="/js/dashboard.js"/>"></script>
