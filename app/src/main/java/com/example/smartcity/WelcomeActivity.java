package com.example.smartcity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartcity.Utilitaries.Preferences;
import com.example.smartcity.View.Fragment.WelcomeFragment;

import static com.example.smartcity.Utilitaries.App.getContext;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!Preferences.getToken(getContext()).equals("")) {
            startActivity(new Intent(getContext(), MainActivity.class));
        }

        // Il est mis dans un else, celui-ci semble inutile puisque le démarrage de l'activité au desuss
        // S'il doit avoir lieu redirigera vers une autre activité. Cependant, si ce n'est pas assez rapide
        // on verra les champs de l'activity welcome durant quelques millisecondes
        else {
            setContentView(R.layout.activity_welcome);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new WelcomeFragment()).commit();
        }

    }


}
