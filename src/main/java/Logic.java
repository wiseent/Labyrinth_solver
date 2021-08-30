import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public final class Logic {

    private Logic() {};

    private static BufferedImage bufferedImage;

    public static void run() {

        double time = System.currentTimeMillis();

        System.out.println("__ Start __");
        try {
            bufferedImage = ImageIO.read(new File("test.bmp"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Applet.setBufferedImage(bufferedImage);

/*        Scanner scanner = new Scanner(System.in);
        System.out.println("type in factor: ");
        int factor = scanner.nextInt();
        if (factor > 1) {
            Applet.setBufferedImage( ResizeImg.bufferedImage(bufferedImage, factor));
        }
        */

        Maze maze1 = new Maze(bufferedImage);
        maze1.print();
        maze1.solve();

/* to get Information about color formats in java

        System.out.println("getRGB: " + bufferedImage.getRGB(0, 0));
        System.out.println("getRGBtoBinary: " + Integer.toBinaryString(bufferedImage.getRGB(0, 0)));
        System.out.println("Color black: " + Color.black.getRGB() );
        System.out.println("Color black to binary: " + Integer.toBinaryString( Color.black.getRGB() ) );
        System.out.println("Color white: " + Color.white.getRGB() );
        System.out.println("Color white to binary: " + Integer.toBinaryString( Color.white.getRGB() ) );

*/
        System.out.println("passed time: " + (System.currentTimeMillis() - time) / 1000 + "seconds" +
                "\n__ End __");
    }
}
