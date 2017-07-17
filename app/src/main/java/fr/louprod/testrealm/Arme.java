package fr.louprod.testrealm;

import io.realm.RealmObject;

/**
 * Created by mario on 15/07/2017.
 */

public class Arme extends RealmObject {

    private String nom;
    private int puissance;

    public Arme() {
    }

    public Arme(String nom, int puissance) {
        this.nom = nom;
        this.puissance = puissance;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getPuissance() {
        return puissance;
    }

    public void setPuissance(int puissance) {
        this.puissance = puissance;
    }

}
