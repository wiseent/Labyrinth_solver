import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.awt.image.BufferedImage;


public class Applet extends Application {

    private static ImageView img = new ImageView();
    private static StackPane root;
    private final int SCALE_IMG = 28;
    private static WritableImage writableImage;

    @Override
    public void start(Stage primaryStage) {

        StackPane root = new StackPane();
        // Scene scene = new Scene(root);

        root.getChildren().add(img);
        primaryStage.setScene(new Scene(root, 1080, 1080));
        primaryStage.show();
        Thread logicThread = new Thread( () -> Logic.run() );
        logicThread.start();
    }

    public static void mainRight(String[] args) { launch(args); }

    public static void setBufferedImage(BufferedImage bufferedImage) {
        writableImage = SwingFXUtils.toFXImage(bufferedImage, null);
        img.setImage(writableImage);
    }

    public static void setPixel(int x, int y, Color c) {
        writableImage.getPixelWriter().setColor(x,y,c);
    }
}

