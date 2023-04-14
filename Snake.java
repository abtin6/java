import java.util.ArrayList;
public class Snake {
    private ArrayList<Point> m_pos;
    private Point p = new Point(0.5,0.5);
    private Target target = new Target();
    private int m_direction;

    public Snake(){
        m_pos = new ArrayList<>();
        m_pos.add(p);
        m_direction = 0;
    }
    public int getLength(){
        return m_pos.size();
    }
    public void draw() {
        StdDraw.setPenRadius(0.04);
        StdDraw.setPenColor(StdDraw.GREEN);

        if (m_pos.size() == 1) {
            StdDraw.point(m_pos.get(0).getX(),m_pos.get(0).getY());
            return;
        }
        for (int i = 0; i < m_pos.size() - 1; i++) {
            StdDraw.line(m_pos.get(i).getX(), m_pos.get(i).getY(), m_pos.get(i+1).getX(), m_pos.get(i+1).getY());
        }

    }
    public void move(){
//        Point Po = new Point(double x, double y);
        Point Po = m_pos.get(0);

        for (int i=m_pos.size()-1; i>0; i--) {
            m_pos.set(i, m_pos.get(i - 1));
        }
        Point head = m_pos.get(0);


        switch (m_direction) {
            case 0: // up
                Po.setY(Po.getY() + 0.01);
                break;
            case 1: // right
                Po.setX(Po.getX() + 0.01);
                break;
            case 2: // down
                Po.setY(Po.getY() - 0.01);
                break;
            case 3: // left
                Po.setX(Po.getX() - 0.01);
                break;
        }
 /*       if (isEat(target)) {
            grow();
            target = new Target();
        }*/
        draw();

    }
    public void setDirection(int d){
        m_direction = d;
    }
    public boolean isEat(Target t){
        return Math.sqrt(Math.pow(m_pos.get(0).getX() - t.getCenter().getX() , 2) + Math.pow(m_pos.get(0).getY()- t.getCenter().getY(), 2)) < t.getRadius() + 0.02;
    }

    public void grow(){
      /*  if(isEat(target)){}*/
            m_pos.add(m_pos.get(m_pos.size()-1));

    }
    public boolean isHit(){
        double sx = m_pos.get(0).getX();
        double sy = m_pos.get(0).getY();
        if(sx <= 0 || sx >= 1 || sy <=0 || sy >=1){
            return true;
        }
        for (int i = 1; i < m_pos.size(); i++) {
            if (sx == m_pos.get(i).getX() && sy == m_pos.get(i).getY()) return true;
        }
        return false;
    }
    public boolean isInside(Target t){
        double distance = Math.sqrt(Math.pow(m_pos.get(0).getX() - t.getCenter().getX() , 2) + Math.pow(m_pos.get(0).getY() - t.getCenter().getY() , 2));
        boolean inside = m_pos.contains(t.getCenter());
        return inside;

    }


    }
