<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="container list">
    <div class="d-flex justify-content-between align-items-center">
        <h1 class="text-center list__title">DANH SÁCH PHƯƠNG THỨC THANH TOÁN</h1>
        <a href="<c:url value="/admin/payment-terms/add"/>" class="list__icon-add">
            <i class='bx bxs-plus-circle'></i>
        </a>
    </div>
</div>

<div class="container mt-4">
    <table id="table" class="table table-striped display nowrap">
        <thead>
        <tr>
            <th>ID</th>
            <th>Số ngày hưởng chiết khấu</th>
            <th>Phần trăm chiết khấu</th>
            <th>Loại</th>
            <th>Nhà cung cấp</th>
            <th>Ngày tạo</th>
            <th>Ngày cập nhập</th>
            <th>Chức năng</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="paymentTerms" items="${paymentTerms}">
            <tr id="item${paymentTerms.id}">
                <td>${paymentTerms.id}</td>
                <td>${paymentTerms.discountDays} ngày</td>
                <td>${paymentTerms.discountPercentage * 100}%</td>
                <td>${paymentTerms.type.getDisplayName()}</td>
                <td>${paymentTerms.supplier.name}</td>
                <td>
                    <fmt:parseDate value="${ paymentTerms.createdAt }" pattern="yyyy-MM-dd HH:mm:ss" var="parsedDateTime" type="both"/>
                    <fmt:formatDate pattern="dd-MM-yyyy" value="${ parsedDateTime }"/>
                </td>
                <td>
                    <c:if test="${ paymentTerms.updatedAt != null }">
                        <fmt:parseDate value="${ paymentTerms.updatedAt }" pattern="yyyy-MM-dd HH:mm:ss" var="parsedUpdatedDateTime" type="both"/>
                        <fmt:formatDate pattern="dd-MM-yyyy" value="${ parsedUpdatedDateTime }"/>
                    </c:if>
                    <c:if test="${ paymentTerms.updatedAt == null }">
                        Chưa cập nhập
                    </c:if>
                </td>
                <td>
                    <a class="btn btn-success btn-sm" href="<c:url value="/admin/payment-terms/edit/${paymentTerms.id}"/>">
                        <i class='bx'>Sửa</i>
                    </a>

                    <c:url value="/admin/payment-terms/delete/${paymentTerms.id}" var="deleteProduct"/>
                    <button class="btn btn-danger btn-sm" onclick="deleteItem('${deleteProduct}', ${paymentTerms.id})">
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
            columns: [null, {searchable: false}, {searchable: false}, null, null, {searchable: false}, {searchable: false}, {searchable: false}],
            language: {
                url: "https://cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Vietnamese.json"
            },
        });
    });
</script>