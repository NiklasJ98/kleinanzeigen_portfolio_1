/*
 * Copyright © 2018 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.javaee.renamepls.jpa;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Eine zu erledigende Aufgabe.
 */
@Entity
public class Task implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "task_ids")
    @TableGenerator(name = "task_ids", initialValue = 0, allocationSize = 50)
    private long id;

    @ManyToOne
    @NotNull(message = "Die Anzeige muss einem User zugeordnet werden.")
    private User owner;

    @ManyToOne
    private Category category;

    @Column(length = 50)
    @NotNull(message = "Die Bezeichnung darf nicht leer sein.")
    @Size(min = 1, max = 50, message = "Die Bezeichnung muss zwischen ein und 50 Zeichen lang sein.")
    private String shortText;

    @Lob
    @NotNull
    private String longText;

    @NotNull(message = "Das Datum darf nicht leer sein.")
    private Date anzeigecreateDate;

    @NotNull(message = "Die Uhrzeit darf nicht leer sein.")
    private Time anzeigecreateTime;
    
    @NotNull(message = "hier eigene Nachricht erfinden")
    private String anzeigeprice;

    @Enumerated(EnumType.STRING)
    @NotNull
    private TaskStatus status = TaskStatus.TASK;
    
    @Enumerated(EnumType.STRING)
    @NotNull
    private TaskPriceType anzeigepricetype = TaskPriceType.FIXED;
    

    //<editor-fold defaultstate="collapsed" desc="Konstruktoren">
    public Task() {
    }

    public Task(User owner, Category category, String shortText, String longText, 
            Date createDate, Time anzeigecreateTime, String anzeigeprice) {
        
        this.owner = owner;
        this.category = category;
        this.shortText = shortText;
        this.longText = longText;
        this.anzeigecreateDate = anzeigecreateDate;
        this.anzeigecreateTime = anzeigecreateTime;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Setter und Getter">
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getShortText() {
        return shortText;
    }

    public void setShortText(String shortText) {
        this.shortText = shortText;
    }

    public String getLongText() {
        return longText;
    }

    public void setLongText(String longText) {
        this.longText = longText;
    }

    public Date getRenamecreateDate() {
        return anzeigecreateDate;
    }

    public void setRenamecreateDate(Date anzeigecreateDate) {
        this.anzeigecreateDate = anzeigecreateDate;
    }

    public Time getRenamecreateTime() {
        return anzeigecreateTime;
    }

    public void setRenamecreateTime(Time anzeigecreateTime) {
        this.anzeigecreateTime = anzeigecreateTime;
    }

    public String getRenameprice() {
        return anzeigeprice;
    }

    public void setRenameprice(String anzeigeprice) {
        this.anzeigeprice = anzeigeprice;
    }

    public TaskPriceType getRenamepricetype() {
        return anzeigepricetype;
    }

    public void setRenamepricetype(TaskPriceType anzeigepricetype) {
        this.anzeigepricetype = anzeigepricetype;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }
    //</editor-fold>

}
