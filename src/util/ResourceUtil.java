package util;

import util.constants.KeyConstants.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class ResourceUtil {

    public static BufferedImage getResource(String resourceLocation) throws IOException {
        return ImageIO.read(Objects.requireNonNull(ResourceUtil.class.getResourceAsStream(resourceLocation)));
    }

    public BufferedImage scaleImage(BufferedImage original, int width, int height){
        BufferedImage scaledImage = new BufferedImage(width, height, original.getType());
        Graphics2D g2 = scaledImage.createGraphics();
        g2.drawImage(original, 0, 0, width, height, null);
        g2.dispose();

        return scaledImage;
    }

    public static String getBasePath(
            String base_template, // spr_base_walking
            String base_action, // spr_base_walk
            Direction base_direction, // down
            String image_path, // base_walk
            Direction image_direction, // down
            int frame // 1
            ){

        return String.format("/spr_bases/%s/%s_%s/%s_%s%d.png",
                base_template,
                base_action,
                base_direction,
                image_path,
                image_direction,
                frame
                );
    }

    public static String getFacePath(
            String character, // player
            String base_face, // spr_lemon_head_face
            Direction base_direction, // down
            String image_path, // lemon_head_face
            Direction image_direction, // down
            int frame // 1
    ){

        return String.format("/%s/spr_faces/%s_%s/%s_%s%d.png",
                character,
                base_face,
                base_direction,
                image_path,
                image_direction,
                frame
        );
    }

    public static String getArmorPath(
            String armor_template, // armor_walking
            String armor_action, // spr_armor_walking
            Direction base_direction, // down
            String image_path, // armor_leather_walking
            Direction image_direction, // down
            int frame // 1
    ){

        return String.format("/spr_armor/%s/%s_%s/%s_%s%d.png",
                armor_template,
                armor_action,
                base_direction,
                image_path,
                image_direction,
                frame
        );
    }

    public static String getMonsterPath(
            String monster_type,
            Direction direction,
            int frame
    ){
        return String.format("/enemies/%s_%s_%d.png",
                monster_type,
                direction,
                frame);
    }

}
