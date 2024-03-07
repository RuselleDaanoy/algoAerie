package com.example;

import javax.swing.*;
import java.awt.*;

//For Resizing Image Icon
public class Defaults {

    public static ImageIcon getResizeImage(String path, int width, int height){
        ImageIcon imageIcon = new ImageIcon(
            new ImageIcon(path)
                .getImage()
                .getScaledInstance(height, width, Image.SCALE_SMOOTH)
        );
        return imageIcon;
    }
}
