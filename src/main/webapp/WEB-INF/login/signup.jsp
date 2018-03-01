<%-- 
    Copyright © 2018 Dennis Schulmeister-Zimolong

    E-Mail: dhbw@windows3.de
    Webseite: https://www.wpvs.de/

    Dieser Quellcode ist lizenziert unter einer
    Creative Commons Namensnennung 4.0 International Lizenz.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags/templates" prefix="template"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:set var="base_url" value="<%=request.getContextPath()%>" />

<template:base>
    <jsp:attribute name="title">
        Registrierung
    </jsp:attribute>

    <jsp:attribute name="head">
        <link rel="stylesheet" href="<c:url value="/css/login.css"/>" />
    </jsp:attribute>

    <jsp:attribute name="menu">
        <div class="menuitem">
            <a href="<c:url value="/logout/"/>">Einloggen</a>
        </div>
    </jsp:attribute>

    <jsp:attribute name="content">
        <div class="container">
            <form method="post" class="stacked">
                <div class="column">
                    <%-- CSRF-Token --%>
                    <input type="hidden" name="csrf_token" value="${csrf_token}">

                    <%-- Eingabefelder --%>
                    <label for="signup_username">
                        Benutzername:
                        <span class="required">*</span>
                    </label>
                    <div class="side-by-side">
                        <input type="text" name="signup_username" value="${signup_form.values["signup_username"][0]}">
                    </div>

                    <label for="signup_password1">
                        Passwort:
                        <span class="required">*</span>
                    </label>
                    <div class="side-by-side">
                        <input type="password" name="signup_password1" value="${signup_form.values["signup_password1"][0]}">
                    </div>

                    <label for="signup_password2">
                        Passwort (wdh.):
                        <span class="required">*</span>
                    </label>
                    <div class="side-by-side">
                        <input type="password" name="signup_password2" value="${signup_form.values["signup_password2"][0]}">
                    </div>
                    
                    <label for="renamefirstname">
                        Vorname:
                        <span class="required">*</span>
                    </label>
                    <div class="side-by-side">
                        <input type="text" name="signup_renamefirstname" value="${signup_form.values["signup_renamefirstname"][0]}">
                    </div>
                    
                    <label for="renamelastname">
                        Nachname:
                        <span class="required">*</span>
                    </label>
                    <div class="side-by-side">
                        <input type="text" name="signup_renamelastname" value="${signup_form.values["signup_renamelastname"][0]}">
                    </div>
                    
                    <label for="renameemail">
                        E-Mail-Adresse:
                        <span class="required">*</span>
                    </label>
                    <div class="side-by-side">
                        <input type="text" name="signup_renameemail" value="${signup_form.values["signup_renameemail"][0]}">
                    </div>
                    
                    <label for="renamephonenumber">
                        Telefonnummer:
                        <span class="required">*</span>
                    </label>
                    <div class="side-by-side">
                        <input type="text" name="signup_renamephonenumber" value="${signup_form.values["signup_renamephonenumber"][0]}">
                    </div>

                    <label for="renameadress">
                        Adresse:
                        <span class="required">*</span>
                    </label>
                    <div class="side-by-side">
                        <input type="text" name="signup_renameadress" value="${signup_form.values["signup_renameadress"][0]}">
                    </div>
                    
                    <label for="renamecity">
                        Stadt:
                        <span class="required">*</span>
                    </label>
                    <div class="side-by-side">
                        <input type="text" name="signup_renamecity" value="${signup_form.values["signup_renamecity"][0]}">
                    </div>
                    
                    <label for="renamepostcode">
                        PLZ:
                        <span class="required">*</span>
                    </label>
                    <div class="side-by-side">
                        <input type="text" name="signup_renamepostcode" value="${signup_form.values["signup_renamepostcode"][0]}">
                    </div>
                    
                    <%-- Button zum Abschicken --%>
                    <div class="side-by-side">
                        <button class="icon-pencil" type="submit">
                            Registrieren
                        </button>
                    </div>
                </div>

                <%-- Fehlermeldungen --%>
                <c:if test="${!empty signup_form.errors}">
                    <ul class="errors">
                        <c:forEach items="${signup_form.errors}" var="error">
                            <li>${error}</li>
                            </c:forEach>
                    </ul>
                </c:if>
            </form>
        </div>
    </jsp:attribute>
</template:base>