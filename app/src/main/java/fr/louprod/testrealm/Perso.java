package fr.louprod.testrealm;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by mario on 15/07/2017.
 */

public class Perso extends RealmObject {

    private String mName;
    private int mVie;
    private RealmList<Arme> mArmes;

    public Perso() {
    }

    public Perso(String mName, int mVie, RealmList<Arme> mArmes) {

        this.mName = mName;
        this.mVie = mVie;
        this.mArmes = mArmes;
    }


    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public int getmVie() {
        return mVie;
    }

    public void setmVie(int mVie) {
        this.mVie = mVie;
    }

    public RealmList<Arme> getmArmes() {
        return mArmes;
    }

    public void setmArmes(RealmList<Arme> mArmes) {
        this.mArmes = mArmes;
    }

    public void addArme(Arme arme){
        mArmes.add(arme);
    }

    //renvoie une string : nomDuPerso + (nombreDePointsDeVie) + listeDesArmesDuPerso
    public String seDecrire(){
        String listeArmes = "";
        for(int i=0; i< mArmes.size(); i++){
            listeArmes = listeArmes + mArmes.get(i).getNom();
            if(i != mArmes.size() -1){
                listeArmes = listeArmes +" - ";
            }
        }

        String vie = " ("+mVie+" pts de vie) ";

        String nom = mName;
        return nom+vie+" : "+listeArmes;
    }

}
