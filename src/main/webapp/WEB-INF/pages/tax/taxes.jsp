<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="container list">
    <div class="d-flex justify-content-between align-items-center">
        <h1 class="text-center list__title">DANH SÁCH THUẾ</h1>
        <a href="<c:url value="/admin/taxes/add"/>" class="list__icon-add">
            <i class='bx bxs-plus-circle'></i>
        </a>
    </div>
</div>

<div class="container mt-4">
    <table id="table" class="table table-striped display nowrap">
        <thead>
        <tr>
            <th>ID</th>
            <th>Rate</th>
            <th>Vùng</th>
            <th>Ngày tạo</th>
            <th>Ngày cập nhập</th>
            <th>Chức năng</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="tax" items="${taxes}">
            <tr id="item${tax.id}">
                <td>${tax.id}</td>
                <td>${tax.rate}</td>
                <td>${tax.region}</td>
                <td>
                    <fmt:parseDate value="${ tax.createdAt }" pattern="yyyy-MM-dd HH:mm:ss" var="parsedDateTime" type="both"/>
                    <fmt:formatDate pattern="dd-MM-yyyy" value="${ parsedDateTime }"/>
                </td>
                <td>
                    <c:if test="${ tax.updatedAt != null }">
                        <fmt:parseDate value="${ tax.updatedAt }" pattern="yyyy-MM-dd HH:mm:ss" var="parsedUpdatedDateTime" type="both"/>
                        <fmt:formatDate pattern="dd-MM-yyyy" value="${ parsedUpdatedDateTime }"/>
                    </c:if>
                    <c:if test="${ tax.updatedAt == null }">
                        Chưa cập nhập
                    </c:if>
                </td>
                <td>
                    <a class="btn btn-success btn-sm" href="<c:url value="/admin/taxes/edit/${tax.id}"/>">
                        <i class='bx'>Sửa</i>
                    </a>

                    <c:url value="/admin/taxes/delete/${tax.id}" var="deleteTax"/>
                    <button class="btn btn-danger btn-sm" onclick="deleteItem('${deleteTax}', ${tax.id})">
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
            columns: [null, {searchable: false}, null, {searchable: false}, {searchable: false}, {searchable: false}],
            language: {
                url: "https://cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Vietnamese.json"
            },
        });
    });
</script>