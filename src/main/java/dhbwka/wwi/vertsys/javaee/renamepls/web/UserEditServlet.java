/*
 * Copyright © 2018 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.javaee.renamepls.web;

import dhbwka.wwi.vertsys.javaee.renamepls.ejb.UserBean;
import dhbwka.wwi.vertsys.javaee.renamepls.ejb.ValidationBean;
import dhbwka.wwi.vertsys.javaee.renamepls.jpa.Task;
import dhbwka.wwi.vertsys.javaee.renamepls.jpa.TaskPriceType;
import dhbwka.wwi.vertsys.javaee.renamepls.jpa.TaskStatus;
import dhbwka.wwi.vertsys.javaee.renamepls.jpa.User;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/app/useredit/"})
public class UserEditServlet extends HttpServlet {

    @EJB
    ValidationBean validationBean;

    @EJB
    UserBean userBean;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Verfügbare Kategorien und Stati für die Suchfelder ermitteln
        User renameuseduser = this.userBean.getCurrentUser();
        request.setAttribute("renameuseduser",renameuseduser);
        
        // Anfrage an dazugerhörige JSP weiterleiten
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/app/user_edit.jsp");
        dispatcher.forward(request, response);

        // Alte Formulardaten aus der Session entfernen
        HttpSession session = request.getSession();
        session.removeAttribute("login_form");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Formulareingaben auslesen
        request.setCharacterEncoding("utf-8");

        String username = request.getParameter("login_username");
        String renamefirstname = request.getParameter("login_renamefirstname");
        String renamelastname = request.getParameter("login_renamelastname");
        String renamephonenumber = request.getParameter("login_renamephonenumber");
        String renameadress = request.getParameter("login_renameadress");
        String renameemail = request.getParameter("login_renameemail");
        String renamecity = request.getParameter("login_renamecity");
        String renamepostcode = request.getParameter("login_renamepostcode");

        // Eingaben prüfen
        User renameuseduser = this.userBean.getCurrentUser();

        //sets
        renameuseduser.setRenamefirstname(renamefirstname);
        renameuseduser.setRenamelastname(renamelastname);
        renameuseduser.setRenamecity(renamecity);
        renameuseduser.setRenameemail(renameemail);
        renameuseduser.setRenamepsotcode(renamepostcode);
        renameuseduser.setRenamephonenumber(renamephonenumber);
        renameuseduser.setUsername(username);

        List<String> errors = this.validationBean.validate(renameuseduser);

        //wenn keine Fehler vorliegen wird der User upgedatet <--renamepls
        if (errors.isEmpty()) {
            this.userBean.update(renameuseduser);
            response.sendRedirect(WebUtils.appUrl(request, "/app/tasks/"));
        }else{
            // Fehler: Formuler erneut anzeigen
            FormValues formValues = new FormValues();
            formValues.setValues(request.getParameterMap());
            formValues.setErrors(errors);

            HttpSession session = request.getSession();
            session.setAttribute("login_form", formValues);

            response.sendRedirect(request.getRequestURI());
        }
    }
}
