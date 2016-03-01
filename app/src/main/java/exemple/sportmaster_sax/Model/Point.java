package exemple.sportmaster_sax.Model;

import java.util.Date;


public class Point {
    private double lat ;
    private double lon ;
    private Date time ;
    private double speed ;
    private double distance_nextPoint ;
    private double distance_prevPoint ;
    private int duree_nextPoint ;
    private int duree_prevPoint ;

    public Point(double lat, double lon,Date time, double speed , double distance_prevPoint , double distance_nextPoint , int duree_prevPoint , int duree_nextPoint){
        this.lat = lat ;
        this.lon = lon ;
        this.time = time ;
        this.speed = speed;
        this.distance_prevPoint = distance_prevPoint;
        this.distance_nextPoint = distance_nextPoint ;
        this.duree_nextPoint = duree_nextPoint ;
        this.duree_prevPoint = duree_prevPoint ;

    }
    public double get_lat(){return this.lat;}
    public double get_lon(){
        return this.lon;
    }
    public Date get_time(){
        return this.time;
    }
    public double get_speed(){
        return this.speed;
    }
    public double get_distance_nextPoint(){return this.distance_nextPoint;}
    public double get_distance_prevPoint(){return this.distance_prevPoint ;}
    public int get_duree_nextPoint(){return this.duree_nextPoint ;}
    public int getDuree_prevPoint(){return this.duree_prevPoint ;}
}
