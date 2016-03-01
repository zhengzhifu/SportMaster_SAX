package exemple.sportmaster_sax.Model;

public class Calculer {
    double Resultat_angle;
    double Resultat_Distance;
    long Resultat_time;

    public Calculer(){ }
    public double Distance(double lat_1 , double lon_1 , double lat_2 , double lon_2){
        // point_2 - point_1
        Resultat_angle = 0;
        Resultat_Distance = 0 ;
        Resultat_angle = Math.sin(lat_1)*Math.sin(lat_2)+Math.cos(lat_1)* Math.cos(lat_2)* Math.cos(lon_1 - lon_2) ;
        Resultat_Distance = 6371.004*Math.acos(Resultat_angle)*Math.PI/180;

        return Resultat_Distance*1000;
    }

    public long Duree(long time_A,long time_B){
        // point_2 - point_1
        Resultat_time = 0;

            Resultat_time = time_B-time_A ;

        return Resultat_time;
    }




}
