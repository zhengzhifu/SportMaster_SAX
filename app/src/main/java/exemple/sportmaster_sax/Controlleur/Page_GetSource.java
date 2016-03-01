package exemple.sportmaster_sax.Controlleur;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import exemple.sportmaster_sax.Model.Calculer;
import exemple.sportmaster_sax.Model.Point;
import exemple.sportmaster_sax.Model.Trajet;
import exemple.sportmaster_sax.R;

public class Page_GetSource extends AppCompatActivity {

    String lat,lon,time,speed,lat_prev,lat_next,lon_prev,lon_next,time_prev,time_next;

    public static ArrayList<Trajet> _LISTE_TRAJET;
    Calculer calculer ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page__get_source);

        calculer = new Calculer();
        this._LISTE_TRAJET = new ArrayList();
        Trajet trajet = new Trajet();

        if (Page_Fichier.multiSelectSpinner != null) {
            List<String> stringList = Page_Fichier.multiSelectSpinner.getSelectedStrings();

            this._LISTE_TRAJET.clear();
            for (int i = 0; i < stringList.size(); i++) {
                String path= Environment.getExternalStorageDirectory().toString()+"/temps/"+stringList.get(i).toString();
                InputStream inputStream = null;
                try {
                    inputStream = new FileInputStream(new File(path)) {
                    };
                    List<HashMap<String, String>> list= exemple.sportmaster_sax.Model.SaxService.readXML(inputStream, "trkpt");
                    /*
                        Parce que on dois calculer distance_nextPoint et distance_prevPoint ,
                        alors on dois utiliser xxx.get(i)-xxx.get(i-1)  et  xxx.get(i+1)-xxx.get(i)
                        Donc on ne peut pas sélectionner 1er élément ni dernière élement
                     */
                    trajet.clear();
                    for(int index = 1 ; index < list.size()-1 ; index ++)
                    {
                        // initialiser les attribut
                        lat = "";
                        lat_prev = "";
                        lat_next = "" ;
                        lon = "";
                        lon_prev = "" ;
                        lon_next = "";
                        speed = "" ;
                        time = "";
                        time_prev = "" ;
                        time_next = "" ;


                        lat_prev = list.get(index-1).get("lat");
                        double lat_prev_double = Double.parseDouble(lat_prev);
                        lat = list.get(index).get("lat");
                        double lat_double = Double.parseDouble(lat);
                        lat_next = list.get(index+1).get("lat");
                        double lat_next_double = Double.parseDouble(lat_next);

                        lon_prev = list.get(index-1).get("lon");
                        double lon_prev_double = Double.parseDouble(lon_prev);
                        lon = list.get(index).get("lon");
                        double lon_double = Double.parseDouble(lon);
                        lon_next = list.get(index+1).get("lon");
                        double lon_next_double = Double.parseDouble(lon_next);

                        speed = list.get(index).get("speed");
                        double speed_double = Double.parseDouble(speed);

                        time_prev = list.get(index-1).get("time");
                        DateFormat iso8600 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
                        Date time_prev_date = iso8600.parse(time_prev);

                        time = list.get(index).get("time");
                        DateFormat iso8601 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
                        Date time_date = iso8601.parse(time);

                        time_next = list.get(index+1).get("time");
                        DateFormat iso8602 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
                        Date time_next_date = iso8602.parse(time_next);

                        double distance_prev = calculer.Distance(lat_prev_double, lon_prev_double, lat_double, lon_double);
                        double distance_next = calculer.Distance(lat_double, lon_double, lat_next_double, lon_next_double);
                        int duree_prev = (int)calculer.Duree(time_prev_date.getTime(),time_date.getTime());
                        int duree_next = (int)calculer.Duree(time_date.getTime(),time_next_date.getTime());
                        Point point = new Point(lat_double,lon_double,time_date,speed_double,distance_prev,distance_next,duree_prev,duree_next);
                        trajet.ajouter(point);
                        Log.d("gpx", time);

                    }

                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                this._LISTE_TRAJET.add(trajet);

            }
        }
    }

    public void toFonction(View view){
        Intent intent_getSource_to_Fonction = new Intent(this,Page_Fonction.class);
        startActivity(intent_getSource_to_Fonction);
    }
}
