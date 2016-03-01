package exemple.sportmaster_sax.Controlleur;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import exemple.sportmaster_sax.R;

public class Page_Fonction extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page__fonction);
        textView=(TextView)findViewById(R.id.textView_fonction);
        textView.setMovementMethod(ScrollingMovementMethod.getInstance());

        String info = "" ;
        for(int i = 0 ; i < Page_GetSource._LISTE_TRAJET.size();i++){
            for(int j = 0 ; j < Page_GetSource._LISTE_TRAJET.get(i).taille();j++){
                info += "taille de trajet_"+i+" :"+Page_GetSource._LISTE_TRAJET.get(i).taille()+"\n"+Page_GetSource._LISTE_TRAJET.get(i).getPoint(j).get_time()+"\n" ;
            }
        }
        textView.setText(info);
    }
}
