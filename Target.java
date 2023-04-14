public class Target {
    private Point m_center;
    private double m_radius;

    public Target () {
        m_center = new Point(Math.random(),Math.random());
        m_radius = ((float)Math.random() * 0.02f) + 0.01f;
    }
    public void draw() {
        StdDraw.setPenRadius(0.001);
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.filledCircle(m_center.getX(), m_center.getY(), m_radius);

    }
    public void generateNewTarget() {
        m_center = new Point(Math.random(), Math.random());
        m_radius = ((float)Math.random() * 0.02f) + 0.01f;
        draw();
    }
    public Point getCenter(){
        return m_center;

    }
    public double getRadius(){
        return m_radius;

    }
    public void setRadius(double r){
        m_radius = r;
    }

}
