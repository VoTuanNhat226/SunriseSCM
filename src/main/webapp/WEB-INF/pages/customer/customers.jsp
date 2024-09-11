<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="container list">
    <div class="d-flex justify-content-between align-items-center">
        <h1 class="text-center list__title">DANH SÁCH KHÁCH HÀNG</h1>
        <a href="<c:url value="/admin/customers/add"/>" class="list__icon-add">
            <i class='bx bxs-plus-circle'></i>
        </a>
    </div>
</div>

<div class="container mt-4">
    <table id="table" class="table table-striped display nowrap">
        <thead>
        <tr>
            <th>ID</th>
            <th>Họ và tên</th>
            <th>Giới tinh</th>
            <th>Địa chỉ</th>
            <th>Số điện thoại</th>
            <th>Ngày tạo</th>
            <th>Ngày cập nhập</th>
            <th>Chức năng</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${customers}">
            <tr id="item${user.id}">
                <td>${user.id}</td>
                <td>${user.getFullName()}</td>
                <td>
                    <c:if test="${user.gender == true}">
                        Nữ
                    </c:if>
                    <c:if test="${user.gender == false}">
                        Nam
                    </c:if>
                </td>
                <td>${user.address}</td>
                <td>${user.phone}</td>
                <td>
                    <fmt:parseDate value="${ user.createdAt }" pattern="yyyy-MM-dd HH:mm:ss" var="parsedDateTime" type="both"/>
                    <fmt:formatDate pattern="dd-MM-yyyy" value="${ parsedDateTime }"/>
                </td>
                <td>
                    <c:if test="${ user.updatedAt != null }">
                        <fmt:parseDate value="${ user.updatedAt }" pattern="yyyy-MM-dd HH:mm:ss" var="parsedUpdatedDateTime" type="both"/>
                        <fmt:formatDate pattern="dd-MM-yyyy" value="${ parsedUpdatedDateTime }"/>
                    </c:if>
                    <c:if test="${ user.updatedAt == null }">
                        Chưa cập nhập
                    </c:if>
                </td>
                <td>
                    <a class="btn btn-success btn-sm" href="<c:url value="/admin/customers/edit/${user.id}"/>">
                        <i class='bx'>Sửa</i>
                    </a>

                    <c:url value="/admin/customers/delete/${user.id}" var="deleteCustomer"/>
                    <button class="btn btn-danger btn-sm" onclick="deleteItem('${deleteCustomer}', ${user.id})">
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
            columns: [null, null, {searchable: false}, {searchable: false}, {searchable: false}, {searchable: false}, {searchable: false}, {searchable: false}],
            language: {
                url: "https://cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Vietnamese.json"
            },
        });
    });
</script>