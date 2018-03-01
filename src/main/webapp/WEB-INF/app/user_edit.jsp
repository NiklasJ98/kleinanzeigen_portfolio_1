<%-- 
    Document   : user_edit
    Created on : 01.03.2018, 10:38:46
    Author     : thoma
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags/templates" prefix="template"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<template:base>
    <jsp:attribute name="content">
        <div class="container">
            <form method="post" class="stacked">
                <div class="column">
                    <%-- CSRF-Token --%>
                    <input type="hidden" name="csrf_token" value="${csrf_token}">

                    <%-- Eingabefelder --%>
                    <label for="login_username">
                        Benutzername:
                        <span class="required">*</span>
                    </label>
                    <div class="side-by-side">
                        <input type="text" name="login_username" value="${renameuseduser.username}">
                    </div>
                    
                    <label for="renamefirstname">
                        Vorname:
                        <span class="required">*</span>
                    </label>
                    <div class="side-by-side">
                        <input type="text" name="login_renamefirstname" value="${renameuseduser.renamefirstname}">
                    </div>
                    
                    <label for="renamelastname">
                        Nachname:
                        <span class="required">*</span>
                    </label>
                    <div class="side-by-side">
                        <input type="text" name="login_renamelastname" value="${renameuseduser.renamelastname}">
                    </div>
                    
                    <label for="renameemail">
                        E-Mail-Adresse:
                        <span class="required">*</span>
                    </label>
                    <div class="side-by-side">
                        <input type="text" name="login_renameemail" value="${renameuseduser.renameemail}">
                    </div>
                    
                    <label for="renamephonenumber">
                        Telefonnummer:
                        <span class="required">*</span>
                    </label>
                    <div class="side-by-side">
                        <input type="text" name="login_renamephonenumber" value="${renameuseduser.renamephonenumber}">
                    </div>

                    <label for="renameadress">
                        Adresse:
                        <span class="required">*</span>
                    </label>
                    <div class="side-by-side">
                        <input type="text" name="login_renameadress" value="${renameuseduser.renameadress}">
                    </div>
                    
                    <label for="renamecity">
                        Stadt:
                        <span class="required">*</span>
                    </label>
                    <div class="side-by-side">
                        <input type="text" name="login_renamecity" value="${renameuseduser.renamecity}">
                    </div>
                    
                    <label for="renamepostcode">
                        PLZ:
                        <span class="required">*</span>
                    </label>
                    <div class="side-by-side">
                        <input type="text" name="login_renamepostcode" value="${renameuseduser.renamepsotcode}">
                    </div>
                    
                    <%-- Button zum Abschicken --%>
                    <div class="side-by-side">
                        <button class="icon-pencil" type="submit">
                            Speichern
                        </button>
                    </div>
                </div>

                <%-- Fehlermeldungen --%>
                <c:if test="${!empty login_form.errors}">
                    <ul class="errors">
                        <c:forEach items="${login_form.errors}" var="error">
                            <li>${error}</li>
                            </c:forEach>
                    </ul>
                </c:if>
            </form>
        </div>
    </jsp:attribute>
</template:base>

