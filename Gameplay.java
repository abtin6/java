public class Gameplay {
    private int m_direction;
    Snake m_snake = new Snake();
    Target m_target = new Target();

  //  public Gameplay() {

//   }

    public void run() {
        while (true) {

            StdDraw.clear();
            m_snake.grow();
            if (m_snake.isEat(m_target)){
                m_snake.grow();
                m_target.setRadius(0);

            }
            if(m_target.getRadius()==0){
                m_target.generateNewTarget();
            }
            m_snake.draw();

            m_target.draw();
            if (m_snake.isHit() && m_snake.isInside(m_target)) {
                break;
            }
            if (m_snake.isEat(m_target)){
                m_snake.grow();
                m_target.setRadius(0);

            }
            m_snake.move();
            if(m_direction != 2 && StdDraw.isKeyPressed(38)){                // up
                m_direction = 0 ;
            }
            else if(m_direction != 3 && StdDraw.isKeyPressed(39)){          // right
                m_direction = 1 ;
            }
            else if(m_direction != 0 && StdDraw.isKeyPressed(40)){          // down
                m_direction = 2 ;
            }
            else if(m_direction != 1 && StdDraw.isKeyPressed(37)){          // left
                m_direction = 3 ;
            }
            m_snake.setDirection(m_direction);
            StdDraw.pause(100);
        }

    }
}
