// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        Gameplay game = new Gameplay();
        game.m_snake = new Snake();
        game.run();
        StdDraw.setPenColor(StdDraw.BOOK_RED);
        StdDraw.text(0.5 ,0.5 ,"GAME OVER!!!");
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.text(0.5,0.45,"Score: " + game.m_snake.getLength());


    }
}