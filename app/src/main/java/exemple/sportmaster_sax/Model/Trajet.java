package exemple.sportmaster_sax.Model;

import java.util.ArrayList;

public class Trajet{
    private ArrayList<Point> points;

    public Trajet(){
        points = new ArrayList();
    }
    public void ajouter(Point point){
        points.add(point);
    }
    public Point getPoint(int i){
        return points.get(i);
    }
    public void clear(){ points.clear();}
    public int taille(){
        return points.size();
    }
}

