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
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Datenbankklasse für einen Benutzer.
 */
@Entity
@Table(name = "RENAMEPLS_USER")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "USERNAME", length = 64)
    @Size(min = 5, max = 64, message = "Der Benutzername muss zwischen fünf und 64 Zeichen lang sein.")
    @NotNull(message = "Der Benutzername darf nicht leer sein.")
    private String username;
    
    public class Password {
        @Size(min = 6, max = 64, message = "Das Passwort muss zwischen sechs und 64 Zeichen lang sein.")
        public String password = "";
    }
    
    @Column(name = "RENAMELASTNAME", length = 64)
    @Size(min = 5, max = 64, message = "Eingabe muss zwischen 5 und 64 liegen!")
    @NotNull(message = "hier einen eigenen Text eingeben ")
    public String renamelastname;
    
    @Column(name = "RENAMEFIRSTNAME", length = 64)
    @Size(min = 5, max = 64, message = "Eingabe muss zwischen 5 und 64 liegen!")
    @NotNull(message = "hier einen eigenen Text eingeben ")
    public String renamefirstname;
    
    @Column(name = "RENAMEPHONENUMBER", length = 64)
    @Size(min = 5, max = 64, message = "Eingabe muss zwischen 5 und 64 liegen!")
    @NotNull(message = "hier einen eigenen Text eingeben ")
    public String renamephonenumber;
    
    @Column(name = "RENAMEEMAIL", length = 64)
    @Size(min = 5, max = 64, message = "Eingabe muss zwischen 5 und 64 liegen!")
    @NotNull(message = "hier einen eigenen Text eingeben ")
    @Pattern(regexp = "^\\w+@\\w+\\..{2,3}(.{2,3})?$")
    public String renameemail;
    
    @Column(name = "RENAMEADRESS", length = 64)
    @Size(min = 5, max = 64, message = "Eingabe muss zwischen 5 und 64 liegen!")
    @NotNull(message = "hier einen eigenen Text eingeben ")
    public String renameadress;
    
    @Column(name = "RENAMEPOSTCODE", length = 64)
    @Size(min = 5, max = 64, message = "Eingabe muss zwischen 5 und 64 liegen!")
    @NotNull(message = "hier einen eigenen Text eingeben ")
    public String renamepsotcode;
    
    @Column(name = "RENAMECITY", length = 64)
    @Size(min = 5, max = 64, message = "Eingabe muss zwischen 5 und 64 liegen!")
    @NotNull(message = "hier einen eigenen Text eingeben ")
    public String renamecity;
        
    @Transient
    private final Password password = new Password();

    @Column(name = "PASSWORD_HASH", length = 64)
    @NotNull(message = "Das Passwort darf nicht leer sein.")
    private String passwordHash;

    @ElementCollection
    @CollectionTable(
            name = "RENAMEPLS_USER_GROUP",
            joinColumns = @JoinColumn(name = "USERNAME")
    )
    @Column(name = "GROUPNAME")
    List<String> groups = new ArrayList<>();

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    List<Task> tasks = new ArrayList<>();

    //<editor-fold defaultstate="collapsed" desc="Konstruktoren">
    public User() {
    }
    
    public User(String username, String password, 
        String renamefirstname, String renamelastname, String renamephonenumber,
        String renameemail, String renameadress, String renamepostcode, String renamecity) {
        
        this.username = username;
        this.password.password = password;
        this.passwordHash = this.hashPassword(password);
        this.renamelastname = renamelastname;
        this.renamefirstname = renamefirstname;
        this.renameemail = renameemail;
        this.renamephonenumber = renamephonenumber;
        this.renamepsotcode = renamepostcode;
        this.renamecity = renamecity;
        this.renameadress = renameadress;
        
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Setter und Getter">
    public String getUsername() {
        return username;
    }

    public void setUsername(String id) {
        this.username = id;
    }

    public String getRenamelastname() {
        return renamelastname;
    }

    public void setRenamelastname(String renamelastname) {
        this.renamelastname = renamelastname;
    }

    public String getRenamefirstname() {
        return renamefirstname;
    }

    public void setRenamefirstname(String renamefirstname) {
        this.renamefirstname = renamefirstname;
    }

    public String getRenamephonenumber() {
        return renamephonenumber;
    }

    public void setRenamephonenumber(String renamephonenumber) {
        this.renamephonenumber = renamephonenumber;
    }

    public String getRenameemail() {
        return renameemail;
    }

    public void setRenameemail(String renameemail) {
        this.renameemail = renameemail;
    }

    public String getRenameadress() {
        return renameadress;
    }

    public void setRenameadress(String renameadress) {
        this.renameadress = renameadress;
    }

    public String getRenamepsotcode() {
        return renamepsotcode;
    }

    public void setRenamepsotcode(String renamepsotcode) {
        this.renamepsotcode = renamepsotcode;
    }

    public String getRenamecity() {
        return renamecity;
    }

    public void setRenamecity(String renamecity) {
        this.renamecity = renamecity;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Passwort setzen und prüfen">
    /**
     * Berechnet der Hash-Wert zu einem Passwort.
     *
     * @param password Passwort
     * @return Hash-Wert
     */
    private String hashPassword(String password) {
        byte[] hash;

        if (password == null) {
            password = "";
        }
        
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException ex) {
            hash = "!".getBytes(StandardCharsets.UTF_8);
        }

        BigInteger bigInt = new BigInteger(1, hash);
        return bigInt.toString(16);
    }

    /**
     * Berechnet einen Hashwert aus dem übergebenen Passwort und legt ihn im
     * Feld passwordHash ab. Somit wird das Passwort niemals als Klartext
     * gespeichert.
     * 
     * Gleichzeitig wird das Passwort im nicht gespeicherten Feld password
     * abgelegt, um durch die Bean Validation Annotationen überprüft werden
     * zu können.
     *
     * @param password Neues Passwort
     */
    public void setPassword(String password) {
        this.password.password = password;
        this.passwordHash = this.hashPassword(password);
    }

    /**
     * Nur für die Validierung bei einer Passwortänderung!
     * @return Neues, beim Speichern gesetztes Passwort
     */
    public Password getPassword() {
        return this.password;
    }
    
    /**
     * Prüft, ob das übergebene Passwort korrekt ist.
     *
     * @param password Zu prüfendes Passwort
     * @return true wenn das Passwort stimmt sonst false
     */
    public boolean checkPassword(String password) {
        return this.passwordHash.equals(this.hashPassword(password));
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Zuordnung zu Benutzergruppen">
    /**
     * @return Eine unveränderliche Liste aller Benutzergruppen
     */
    public List<String> getGroups() {
        List<String> groupsCopy = new ArrayList<>();

        this.groups.forEach((groupname) -> {
            groupsCopy.add(groupname);
        });

        return groupsCopy;
    }

    /**
     * Fügt den Benutzer einer weiteren Benutzergruppe hinzu.
     *
     * @param groupname Name der Benutzergruppe
     */
    public void addToGroup(String groupname) {
        if (!this.groups.contains(groupname)) {
            this.groups.add(groupname);
        }
    }

    /**
     * Entfernt den Benutzer aus der übergebenen Benutzergruppe.
     *
     * @param groupname Name der Benutzergruppe
     */
    public void removeFromGroup(String groupname) {
        this.groups.remove(groupname);
    }
    //</editor-fold>

}
