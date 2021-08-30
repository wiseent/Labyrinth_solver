import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Maze {

    private int[][] mazeArray;
    private int height;
    private  int width;
    private Point start;
    private Point end;
    private Set<Point> checked;
    List<Point> mainRoute;

    public Maze(BufferedImage bufferedImage) {
        height = bufferedImage.getHeight();
        width = bufferedImage.getWidth();
        mazeArray = new int [bufferedImage.getWidth()] [bufferedImage.getHeight()];
        for (int y = 0; y < bufferedImage.getHeight(); y++) {
            for (int x = 0; x < bufferedImage.getWidth(); x++) {
                if (bufferedImage.getRGB(x,y) == Color.black.getRGB()) {
                    mazeArray[x][y] = 0;
                }
                else if (bufferedImage.getRGB(x,y) == Color.white.getRGB()) {
                    mazeArray[x][y] = 1;
                }
                else {
                    System.out.println("Expected Pixel Color Black or White. Pixel Coordinates: X = " + x + " | Y = " + y);
                }
            }
        }
    }

    public void print() {
        for (int y = 0; y < height; y++) {
            System.out.print(" " + y +  "\t| ");
            for (int x = 0; x < width; x++) {
                System.out.print(" " + mazeArray[x][y] + " ");
            }
            System.out.println();
        }
    }

    public void solve() {
        //search first line for start
        start = new Point();
        for (int i = 0; i < width; i++) {
            //debug loop   System.out.println( "loop " + i + " | " + mazeArray[i][0]);
                if (mazeArray[i][0] == 1 ) {
                    start.x = i;
                    start.y = 0;
                }
            }
        System.out.println(start);

        //search last line for end
        end = new Point();
        for (int i = 0; i < width; i++) {
                if (mazeArray[i][height-1] == 1 ) {
                    end.x = i;
                    end.y = height-1;
                }
            }
        System.out.println(end);
        // Hashset for checked paths
        checked = new HashSet<Point>();
        checked.add(new Point(start.x, start.y));
        //Start of mainRoute
        mainRoute = new ArrayList<Point>();
        mainRoute.add( new Point (start.x, start.y) );
        checkRoute();
        System.out.println("mainRoute: " +mainRoute);
        }

    private void checkRoute() {
        Point lastPath = new Point(mainRoute.get(mainRoute.size() - 1));
        int paths = 0;
            //check right path
/*
        if (mazeArray[lastPath.x + 1 ][lastPath.y] == 1 && !(mainRoute.contains(new Point( lastPath.x + 1,lastPath.y )))) {
            paths++;
        }
        if (mazeArray[lastPath.x][lastPath.y + 1] == 1 && !(mainRoute.contains(new Point( lastPath.x,lastPath.y + 1)))) {
            paths ++;
        }
        if (mazeArray[lastPath.x - 1][lastPath.y] == 1 && !(mainRoute.contains(new Point( lastPath.x - 1,lastPath.y)))) {
            paths ++;
        }
        if (mazeArray[lastPath.x][lastPath.y - 1] == 1 && !(mainRoute.contains(new Point( lastPath.x,lastPath.y  -1)))) {
            paths++;
        }
*/

        if (lastPath.x != width-1 && mazeArray[lastPath.x + 1 ][lastPath.y] == 1 && !(mainRoute.contains(new Point( lastPath.x + 1,lastPath.y )))) {

            paths--;
            mainRoute.add(new Point( lastPath.x + 1,lastPath.y ));
            checkRoute();

            if ( end.equals(mainRoute.get(mainRoute.size() - 1))) {
                return;
            }
            else {
                mainRoute.remove(mainRoute.size() - 1);
            }
        }
        if (lastPath.y != height-1 &&  mazeArray[lastPath.x][lastPath.y + 1] == 1 && !(mainRoute.contains(new Point( lastPath.x,lastPath.y + 1)))) {

            paths--;
            mainRoute.add(new Point( lastPath.x,lastPath.y +1 ));
            checkRoute();

            if (end.equals( mainRoute.get(mainRoute.size() - 1))) {
                return;
            }
            else {
                mainRoute.remove(mainRoute.size() - 1);
            }

        }
        if (lastPath.x != 0 && mazeArray[lastPath.x - 1][lastPath.y] == 1 && !(mainRoute.contains(new Point( lastPath.x - 1,lastPath.y)))) {

            paths--;
            mainRoute.add(new Point( lastPath.x - 1,lastPath.y ));
            checkRoute();

            if (end.equals(mainRoute.get(mainRoute.size() - 1))) {
                return;
            }
            else {
                mainRoute.remove(mainRoute.size() - 1);
            }
        }
        if (lastPath.y != 0 && mazeArray[lastPath.x][lastPath.y - 1] == 1 && !(mainRoute.contains(new Point( lastPath.x,lastPath.y  -1)))) {

            paths--;
            mainRoute.add(new Point( lastPath.x,lastPath.y -1));
            checkRoute();

            if (end.equals(mainRoute.get(mainRoute.size() - 1))) {
                return;
            }
            else {
                mainRoute.remove(mainRoute.size() - 1);
            }
        }
    }
}
