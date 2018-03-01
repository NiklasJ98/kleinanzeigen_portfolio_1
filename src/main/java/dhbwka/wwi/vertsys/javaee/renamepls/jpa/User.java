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
@Table(name = "kleinanzeigen_USER")
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
    
    @Column(name = "kleinanziegen_user_LASTNAME", length = 64)
    @Size(min = 5, max = 64, message = "Eingabe muss zwischen 5 und 64 liegen!")
    @NotNull(message = "hier einen eigenen Text eingeben ")
    public String user_lastname;
    
    @Column(name = "kleinanzeigen_user_FIRSTNAME", length = 64)
    @Size(min = 5, max = 64, message = "Eingabe muss zwischen 5 und 64 liegen!")
    @NotNull(message = "hier einen eigenen Text eingeben ")
    public String user_firstname;
    
    @Column(name = "kleinanziegen_user_PHONENUMBER", length = 64)
    @Size(min = 5, max = 64, message = "Eingabe muss zwischen 5 und 64 liegen!")
    @NotNull(message = "hier einen eigenen Text eingeben ")
    public String user_phonenumber;
    
    @Column(name = "kleinanziegen_user_EMAIL", length = 64)
    @Size(min = 5, max = 64, message = "Eingabe muss zwischen 5 und 64 liegen!")
    @NotNull(message = "hier einen eigenen Text eingeben ")
    @Pattern(regexp = "^\\w+@\\w+\\..{2,3}(.{2,3})?$")
    public String user_email;
    
    @Column(name = "kleinanziegen_user_ADRESS", length = 64)
    @Size(min = 5, max = 64, message = "Eingabe muss zwischen 5 und 64 liegen!")
    @NotNull(message = "hier einen eigenen Text eingeben ")
    public String user_adress;
    
    @Column(name = "kleinanziegen_user_POSTCODE", length = 64)
    @Size(min = 5, max = 64, message = "Eingabe muss zwischen 5 und 64 liegen!")
    @NotNull(message = "hier einen eigenen Text eingeben ")
    public String user_postcode;
    
    @Column(name = "kleinanziegen_user_CITY", length = 64)
    @Size(min = 5, max = 64, message = "Eingabe muss zwischen 5 und 64 liegen!")
    @NotNull(message = "hier einen eigenen Text eingeben ")
    public String user_city;
        
    @Transient
    private final Password password = new Password();

    @Column(name = "PASSWORD_HASH", length = 64)
    @NotNull(message = "Das Passwort darf nicht leer sein.")
    private String passwordHash;

    @ElementCollection
    @CollectionTable(
            name = "kleinanzeigen_user_GROUP",
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
        String user_firstname, String user_lastname, String user_phonenumber,
        String user_email, String user_adress, String user_postcode, String user_city) {
        
        this.username = username;
        this.password.password = password;
        this.passwordHash = this.hashPassword(password);
        this.user_lastname = user_lastname;
        this.user_firstname = user_firstname;
        this.user_email = user_email;
        this.user_phonenumber = user_phonenumber;
        this.user_postcode = user_postcode;
        this.user_city = user_city;
        this.user_adress = user_adress;
        
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
        return user_lastname;
    }

    public void setRenamelastname(String user_lastname) {
        this.user_lastname = user_lastname;
    }

    public String getRenamefirstname() {
        return user_firstname;
    }

    public void setRenamefirstname(String user_firstname) {
        this.user_firstname = user_firstname;
    }

    public String getRenamephonenumber() {
        return user_phonenumber;
    }

    public void setRenamephonenumber(String user_phonenumber) {
        this.user_phonenumber = user_phonenumber;
    }

    public String getRenameemail() {
        return user_email;
    }

    public void setRenameemail(String user_email) {
        this.user_email = user_email;
    }

    public String getRenameadress() {
        return user_adress;
    }

    public void setRenameadress(String user_adress) {
        this.user_adress = user_adress;
    }

    public String getRenamepsotcode() {
        return user_postcode;
    }

    public void setRenamepsotcode(String user_psotcode) {
        this.user_postcode = user_psotcode;
    }

    public String getRenamecity() {
        return user_city;
    }

    public void setRenamecity(String user_city) {
        this.user_city = user_city;
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
