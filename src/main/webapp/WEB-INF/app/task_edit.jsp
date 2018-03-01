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

<template:base>
    <jsp:attribute name="title">
        <c:choose>
            <c:when test="${edit}">
                Aufgabe bearbeiten
            </c:when>
            <c:otherwise>
                Aufgabe anlegen
            </c:otherwise>
        </c:choose>
    </jsp:attribute>

    <jsp:attribute name="head">
        <link rel="stylesheet" href="<c:url value="/css/task_edit.css"/>" />
    </jsp:attribute>

    <jsp:attribute name="menu">
        <div class="menuitem">
            <a href="<c:url value="/app/tasks/"/>">Übersicht</a>
        </div>
    </jsp:attribute>

    <jsp:attribute name="content">
        <form method="post" class="stacked">
            <div class="column">
                <%-- CSRF-Token --%>
                <input type="hidden" name="csrf_token" value="${csrf_token}">

                <%-- Eingabefelder --%>
                <label for="task_owner">Eigentümer:</label>
                <div class="side-by-side">
                    <input type="text" name="task_owner" value="${task_form.values["task_owner"][0]}" readonly="readonly">
                </div>

                <div>
                    <ul>
                        <li>
                            <b> Vorname: </b> ${owner_list.renamefirstname}
                        </li>
                        <li>
                            <b> Nachname: </b> ${owner_list.renamelastname}
                        </li>
                        <li>
                            <b> Adresse: </b> ${owner_list.renameadress}
                        </li>
                        <li>
                            <b> Stadt: </b> ${owner_list.renamecity}
                        </li>
                        <li>
                            <b> PLZ: </b> ${owner_list.renamepsotcode}
                        </li>
                        <li>
                            <b> Telefonnummer: </b> ${owner_list.renamephonenumber}
                        </li>
                        <li>
                            <b> E-Mail: </b> ${owner_list.renameemail}
                        </li>
                    </ul>

                </div>


                <label for="task_category">Kategorie:</label>
                <div class="side-by-side">
                    <select name="task_category">
                        <option value="">Keine Kategorie</option>

                        <c:forEach items="${categories}" var="category">
                            <option value="${category.id}" ${task_form.values["task_category"][0] == category.id ? 'selected' : ''}>
                                <c:out value="${category.name}" />
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <label for="task_due_date">
                    Erstellt am:
                    <span class="required">*</span>
                </label>
                <div class="side-by-side">
                    <input type="text" name="task_due_date" value="${task_form.values["task_due_date"][0]}">
                    <input type="text" name="task_due_time" value="${task_form.values["task_due_time"][0]}">
                </div>

                <label for="task_status">
                    Anzeigenstatus:
                    <span class="required">*</span>
                </label>
                <div class="side-by-side margin">
                    <select name="task_status">
                        <c:forEach items="${statuses}" var="status">
                            <option value="${status}" ${task_form.values["task_status"][0] == status ? 'selected' : ''}>
                                <c:out value="${status.label}"/>
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <label for="task_renameprice">
                    Preis:
                    <span class="required">*</span>
                </label>
                <div class="side-by-side">
                    <input type="text" name="task_renameprice" value="${task_form.values["task_renameprice"][0]}">
                </div>

                <label for="task_renamepricetype">
                    Preisart:
                    <span class="required">*</span>
                </label>
                <div class="side-by-side margin">
                    <select name="task_renamepricetype">
                        <c:forEach items="${pricetypes}" var="pricetype">
                            <option value="${pricetype}" ${task_form.values["task_renamepricetype"][0] == pricetype ? 'selected' : ''}>
                                <c:out value="${pricetype.label}"/>
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <label for="task_short_text">
                    Bezeichnung:
                    <span class="required">*</span>
                </label>
                <div class="side-by-side">
                    <input type="text" name="task_short_text" value="${task_form.values["task_short_text"][0]}">
                </div>

                <label for="task_long_text">
                    Beschreibung:
                </label>
                <div class="side-by-side">
                    <textarea name="task_long_text"><c:out value="${task_form.values['task_long_text'][0]}"/></textarea>
                </div>

                <%-- Button zum Abschicken --%>
                <div class="side-by-side">
                    <button class="icon-pencil" type="submit" name="action" value="save">
                        Sichern
                    </button>

                    <c:if test="${edit}">
                        <button class="icon-trash" type="submit" name="action" value="delete">
                            Löschen
                        </button>
                    </c:if>
                </div>
            </div>

            <%-- Fehlermeldungen --%>
            <c:if test="${!empty task_form.errors}">
                <ul class="errors">
                    <c:forEach items="${task_form.errors}" var="error">
                        <li>${error}</li>
                        </c:forEach>
                </ul>
            </c:if>
        </form>
    </jsp:attribute>
</template:base>