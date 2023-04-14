package com.tabrizu;
import java.util.ArrayList;

public class App 
{
    static void drawSnake ( ArrayList<Double> x , ArrayList<Double> y ) {

        StdDraw.setPenColor(StdDraw.GREEN);
        StdDraw.setPenRadius(0.03);
 
        if(x.size() == 1){
            StdDraw.point( x.get(0) , y.get(0) );
            return;
        }

        for(int i = 0 ; i < x.size() - 1 ; i++)
        {
            StdDraw.line( x.get(i) , y.get(i) , x.get(i+1) , y.get(i+1) );
        }
    }

    static void drawTarget ( float x , float y , float r , int time ) {

        if(time < 5) {
            StdDraw.setPenRadius(0.03);
            StdDraw.setPenColor(StdDraw.PINK);
            StdDraw.filledCircle(x , y , r);
        }

        else if (time == 5) {
            StdDraw.setPenRadius(0.07);
            StdDraw.setPenColor(StdDraw.ORANGE);
            StdDraw.filledCircle(x , y , 3 * r);
        }

    }

    static boolean snakeEat ( double snakeHeadX , double snakeHeadY , double tx , double ty , double tr ) {
        return Math.sqrt( Math.pow(snakeHeadX-tx,2) + Math.pow(snakeHeadY-ty,2) ) < tr + 0.02 ;
    }

    static  void move ( ArrayList<Double> x , ArrayList<Double> y , int d ) { 
        for(int i = x.size()-1 ; i>0 ; i--){

            x.set( i , x.get(i-1) );
            y.set( i , y.get(i-1) );
        }

        switch (d) {
            case 0 :   //up
                y.set( 0 , y.get(0)+0.01 );
                break ;

            case 2 :   //down
                y.set( 0 , y.get(0)-0.01 );
                break ;

            case 1 :   //right
                x.set( 0 , x.get(0)+0.01 );
                break ;

            case 3 :   //left
                x.set( 0 , x.get(0)-0.01 );
                break ;

        }

        if ( x.get(0) < 0.0 )
           x.set(0, 1.0);
        if ( x.get(0) > 1.0 )       
            x.set(0, 0.0);
        if ( y.get(0) < 0.0 )
           y.set(0, 1.0);
        if ( y.get(0) > 1.0 )
           y.set(0, 0.0);
        
    }

    static  boolean hit ( ArrayList<Double> x , ArrayList<Double> y ) {

        double sx = x.get(0);
        double sy = y.get(0);

        for(int i = 1 ; i < x.size() ; i++ ) {
            if ( ( Math.abs(sx-x.get(i)) )<0.000001 && ( Math.abs(sy-y.get(i)) )<0.000001 )
                return true ;
        }
        return  false ;
    }


    public static void main(String[] args) {

        ArrayList<Double> snakeX = new ArrayList<Double> ();
        ArrayList<Double> snakeY = new ArrayList<Double> ();

        int direction = 0 ;      
        int time = 0 ;
        float tx = 0 , ty = 0 , tr = 0 ;

        snakeX.add(0.5);
        snakeY.add(0.5);

        while (true) {

            StdDraw.clear();

            if(tr == 0){
                tx = (float)Math.random();
                ty = (float)Math.random();
                tr = ((float)Math.random() * 0.02f) + 0.01f ;
            }
            
            drawSnake(snakeX , snakeY) ;
            drawTarget(tx , ty , tr , time) ;

            if(hit(snakeX , snakeY)){
                break ;
            }

            if(time == 5){
                if (tr < 0.001){      
                    tr = 0 ;
                    time = 0 ;
                }
                else{
                    tr -= 0.00009 ;
                }

            }
 
            if(snakeEat( snakeX.get(0) , snakeY.get(0) , tx, ty, tr )){

                if(time == 5){

                    snakeX.add (2*(snakeX.get(snakeX.size()-1)));
                    snakeY.add (2*(snakeY.get(snakeY.size()-1)));

                    tr = 0 ;
                    time = 0 ;

                }

                else{

                    snakeX.add(snakeX.get(snakeX.size()-1));
                    snakeY.add(snakeY.get(snakeY.size()-1));

                    tr = 0 ;
                    time++ ;

                }

            }

            move (snakeX , snakeY , direction);

            if(direction != 2 && StdDraw.isKeyPressed(38)){                // up
                direction = 0 ;
            }
            else if(direction != 3 && StdDraw.isKeyPressed(39)){          // right
                direction = 1 ;
            }
            else if(direction != 0 && StdDraw.isKeyPressed(40)){          // down
                direction = 2 ;
            }
            else if(direction != 1 && StdDraw.isKeyPressed(37)){          // left
                direction = 3 ;
            }

            StdDraw.pause(100);

        }

        StdDraw.setPenColor(StdDraw.BOOK_RED);
        StdDraw.text(0.5 ,0.5 ,"GAME OVER!!!");
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.text(0.5,0.45,"Score: " + snakeX.size());


    }
}
