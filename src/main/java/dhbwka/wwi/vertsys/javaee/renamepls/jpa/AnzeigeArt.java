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

/**
 * Statuswerte einer Aufgabe.
 */
public enum AnzeigeArt {
    TASK, SEEKER;

    /**
     * Bezeichnung ermitteln
     *
     * @return Bezeichnung
     */
    public String getLabel() {
        switch (this) {
            case TASK:
                return "Bieter";
            case SEEKER:
                return "Sucher";
            default:
                return this.toString();
        }
    }
}
