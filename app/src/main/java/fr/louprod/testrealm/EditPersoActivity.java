package fr.louprod.testrealm;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import fr.louprod.testrealm.databinding.ActivityEditPersoBinding;
import io.realm.Realm;

/**
 * Created by mario on 16/07/2017.
 */

public class EditPersoActivity extends Activity implements AdapterView.OnItemSelectedListener {

    Realm realm;
    String classe_choisie;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityEditPersoBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_perso);

        realm = Realm.getDefaultInstance();

        Spinner spinner = binding.iEditPersoSpinnerClass;
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.class_spinner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        final Intent intent_retour = new Intent(this, MainActivity.class);
        binding.bEditPersoValid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nom_choisi = binding.iEditPersoName.getText().toString();

                if(classe_choisie != null && !nom_choisi.equals("")) {
                    realm.beginTransaction();
                    Perso new_perso = realm.createObject(Perso.class);
                    new_perso.setmName(nom_choisi);
                    new_perso.setmVie(getPtsDeVie(classe_choisie));
                    new_perso.addArme(getArme(classe_choisie));
                    realm.commitTransaction();
                    startActivity(intent_retour);
                }
            }
        });

        binding.bEditPersoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent_retour);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        classe_choisie = (String) parent.getItemAtPosition(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        classe_choisie = null;
    }


    //Fonction qui retourne "en dur" un nombre de points de vie selon la classe choisie par l'user. A changer bien sûr en vrai contexte
    int getPtsDeVie (String classe){
        switch (classe){
            case "Guerrier":
                return 100;
            case "Assassin":
                return 70;
            case "Magicien":
                return 50;
            case "Archer":
                return 70;
            case "Guerisseur":
                return 40;
            default:
                return 60;
        }
    }

    //Fonction qui retourne "en dur" une arme selon la classe choisie par l'user. A changer bien sûr en vrai contexte
    //l'arme retournée ne possède, de plus, par de "points de puissance"
    Arme getArme (String classe){
        Arme arme = new Arme();
        switch (classe){
            case "Guerrier":
                arme.setNom("épée large à deux main");
                break;
            case "Assassin":
                arme.setNom("double poignard incurvé");
                break;
            case "Magicien":
                arme.setNom("baton de puissance");
                break;
            case "Archer":
                arme.setNom("arc à poulie");
                break;
            case "Guerisseur":
                arme.setNom("herbes");
                break;
            default:
                arme.setNom("petite épée");
        }
        return arme;
    }
}
