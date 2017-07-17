package fr.louprod.testrealm;

import android.app.Application;

import io.realm.DynamicRealm;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmMigration;
import io.realm.RealmSchema;

/**
 * Created by mario on 15/07/2017.
 */

public class TestRealmApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .migration(new CustomMigration())
                .build();
        Realm.setDefaultConfiguration(config);
    }

    private class CustomMigration implements RealmMigration {
        @Override
        public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
            RealmSchema schema = realm.getSchema();

            if (oldVersion == 0) {
                // Migrate from v0 to v1
                oldVersion++;
            }

            if (oldVersion == 1) {
                // Migrate from v1 to v2
                oldVersion++;
            }

            if (oldVersion < newVersion) {
                throw new IllegalStateException(String.format( "Migration missing from v%d to v%d", oldVersion, newVersion));
            }


        }
    }
}
