import java.awt.image.BufferedImage;

public final class ResizeImg {

    private ResizeImg() {}

    public static BufferedImage bufferedImage (BufferedImage img, int factor) {
        BufferedImage newImg = new BufferedImage(img.getWidth()*factor,img.getHeight()*factor,img.getType());
        // iterate through all rows and columns
        for (int y = 0; y < img.getHeight(); y++) {

            for (int x = 0; x < img.getWidth(); x++) {
                int color = img.getRGB(x,y);
/*  for debugging: outputs single pixel color in console and updates picture every line
                if (color == -16777216) {
                    System.out.println("x = " + x + " | y = " + y + " | color = black");
                }
                else if (color == -1) {
                    System.out.println("x = " + x + " | y = " + y + " | color = white");
                }
                else {
                    System.out.println("x = " + x + " | y = " + y + " | color = unknown");
                }
 */
                for (int k = x * factor; k < (factor + x * factor) ; k++) {
                    newImg.setRGB(k,y*factor,color);
                    for (int i = y * factor; i < (factor + y * factor); i++) {
                        newImg.setRGB(k,i,color);
                    }
                }
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Applet.setBufferedImage(newImg);
        }

        return newImg;
    }
}
