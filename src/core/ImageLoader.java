package core; /**
 * Created by Devin on 12/25/2016.
 */
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageLoader {
    public static Image loadImage(String fileName){
        Image img = null;

        try {
            img = ImageIO.read(new File(".\\assets\\" + fileName));
        } catch (IOException e){
            e.printStackTrace();
        }

        return img;
    }
}
