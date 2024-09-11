<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="container list">
    <div class="d-flex justify-content-between align-items-center">
        <h1 class="text-center list__title">DANH SÁCH ĐƠN HÀNG</h1>
        <a href="<c:url value="/admin/orders/add"/>" class="list__icon-add">
            <i class='bx bxs-plus-circle'></i>
        </a>
    </div>
</div>

<div class="container mt-4">
    <table id="table" class="table table-striped display nowrap">
        <thead>
        <tr>
            <th>ID</th>
            <th>Loại đơn hàng</th>
            <th>Trạng thái</th>
            <th>Người đặt</th>
            <th>Ngày tạo</th>
            <th>Ngày cập nhập</th>
            <th>Chức năng</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${orders}" var="order">
            <tr id="item${order.id}">
                <td>${order.id}</td>
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
                    <fmt:parseDate value="${ order.createdAt }" pattern="yyyy-MM-dd HH:mm:ss" var="parsedDateTime" type="both"/>
                    <fmt:formatDate pattern="dd-MM-yyyy" value="${ parsedDateTime }"/>
                </td>
                <td>
                    <c:if test="${ order.updatedAt != null }">
                        <fmt:parseDate value="${ order.updatedAt }" pattern="yyyy-MM-dd HH:mm:ss" var="parsedUpdatedDateTime" type="both"/>
                        <fmt:formatDate pattern="dd-MM-yyyy" value="${ parsedUpdatedDateTime }"/>
                    </c:if>
                    <c:if test="${ order.updatedAt == null }">
                        Chưa cập nhập
                    </c:if>
                </td>
                <td>
                    <a class="btn btn-success btn-sm" href="<c:url value="/admin/orders/edit/${order.id}"/>">
                        <i class='bx'>Sửa</i>
                    </a>

                    <c:url value="/admin/orders/delete/${order.id}" var="deleteOrder"/>
                    <button class="btn btn-danger btn-sm" onclick="deleteItem('${deleteOrder}', ${order.id})">
                        <i class='bx'>Xóa</i>
                    </button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<script>
    $(document).ready(function () {
        $('#table').DataTable({
            columns: [null, null, null, null, {searchable: false}, {searchable: false}, {searchable: false}],
            language: {
                url: "https://cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Vietnamese.json"
            },
        });
    });
</script>