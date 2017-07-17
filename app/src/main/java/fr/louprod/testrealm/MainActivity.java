package fr.louprod.testrealm;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import fr.louprod.testrealm.databinding.ActivityMainBinding;
import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends Activity {

    Realm realm;
    Context leContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        realm = Realm.getDefaultInstance();
        leContext = this;

        RealmResults<Perso> allPersos = realm.where(Perso.class).findAll();
        String listePersos = "";
        for(int i=0; i<allPersos.size(); i++){
            listePersos = listePersos+allPersos.get(i).seDecrire();
            listePersos = listePersos+"\n\n";

        }

        binding.vMainListe.setText(listePersos);

        binding.bMainAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(leContext, EditPersoActivity.class));
            }
        });

    }
}
