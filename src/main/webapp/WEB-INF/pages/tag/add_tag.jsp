<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:url value="/admin/tags/add" var="addTag"/>

<div class="container list">
    <div class="d-flex justify-content-between align-items-center">
        <h1 class="text-center list__title">THÊM NHÃN SẢN PHẨM</h1>
    </div>
</div>

<c:if test="${errors != null}">
    <c:forEach var="error" items="${errors}">
        <div class="alert alert-danger">
                ${error.message}
        </div>
    </c:forEach>
</c:if>

<form:form id="addTagForm" method="post" modelAttribute="tag" action="${addTag}">
    <div class="form-group">
        <form:label path="name" cssClass="form-label">Tên nhãn</form:label>
        <form:input type="text" name="name" path="name" placeholder="Nhập tên nhãn" cssClass="form-control"/><br/>
    </div>

    <div class="form-group">
        <form:label path="description" cssClass="form-label">Mô tả</form:label>
        <form:input type="text" name="description" path="description" placeholder="Mô tả" cssClass="form-control"/><br/>
    </div>

    <input type="submit" value="Thêm"/>
</form:form>