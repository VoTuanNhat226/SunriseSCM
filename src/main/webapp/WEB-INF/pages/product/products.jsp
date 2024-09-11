<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="container list">
    <div class="d-flex justify-content-between align-items-center">
        <h1 class="text-center list__title">DANH SÁCH SẢN PHẨM</h1>
        <a href="<c:url value="/admin/products/add"/>" class="list__icon-add">
            <i class='bx bxs-plus-circle'></i>
        </a>
    </div>
</div>

<div class="container mt-4">
    <table id="table" class="table table-striped display nowrap">
        <thead>
        <tr>
            <th>ID</th>
            <th>Tên</th>
            <th>Giá</th>
            <th>Đơn vị</th>
            <th>Danh mục</th>
            <th>Ngày hết hạn</th>
            <th>Ngày tạo</th>
            <th>Ngày cập nhập</th>
            <th>Chức năng</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="product" items="${products}">
            <tr id="item${product.id}">
                <td>${product.id}</td>
                <td>${product.name}</td>
                <td>${String.format("%,.3f", product.price)} VNĐ</td>
                <td>${product.unit.name}</td>
                <td>${product.category.name}</td>
                <td>
                    <fmt:parseDate value="${ product.expiryDate }" pattern="yyyy-MM-dd" var="parsedDateTime" type="date"/>
                    <fmt:formatDate pattern="dd-MM-yyyy" value="${ parsedDateTime }"/>
                </td>
                <td>
                    <fmt:parseDate value="${ product.createdAt }" pattern="yyyy-MM-dd HH:mm:ss" var="parsedDateTime" type="both"/>
                    <fmt:formatDate pattern="dd-MM-yyyy" value="${ parsedDateTime }"/>
                </td>
                <td>
                    <c:if test="${ product.updatedAt != null }">
                        <fmt:parseDate value="${ product.updatedAt }" pattern="yyyy-MM-dd HH:mm:ss" var="parsedUpdatedDateTime" type="both"/>
                        <fmt:formatDate pattern="dd-MM-yyyy" value="${ parsedUpdatedDateTime }"/>
                    </c:if>
                    <c:if test="${ product.updatedAt == null }">
                        Chưa cập nhập
                    </c:if>
                </td>
                <td>
                    <a class="btn btn-success btn-sm" href="<c:url value="/admin/products/edit/${product.id}"/>">
                        <i class='bx'>Sửa</i>
                    </a>

                    <c:url value="/admin/products/delete/${product.id}" var="deleteProduct"/>
                    <button class="btn btn-danger btn-sm" onclick="deleteItem('${deleteProduct}', ${product.id})">
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
            columns: [null, null, {searchable: false}, null, null, {searchable: false}, {searchable: false}, {searchable: false}, {searchable: false}],
            language: {
                url: "https://cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Vietnamese.json"
            },
        });
    });
</script>