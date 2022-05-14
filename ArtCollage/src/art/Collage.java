package art;

import java.awt.Color;

/*
 * This class contains methods to create and perform operations on a collage of images.
 * 
 * @author Ana Paula Centeno
 */

public class Collage {

    // The orginal picture
    private Picture originalPicture;

    // The collage picture is made up of tiles.
    // Each tile consists of tileDimension X tileDimension pixels
    // The collage picture has collageDimension X collageDimension tiles
    private Picture collagePicture;

    // The collagePicture is made up of collageDimension X collageDimension tiles
    // Imagine a collagePicture as a 2D array of tiles
    private int collageDimension;

    // A tile consists of tileDimension X tileDimension pixels
    // Imagine a tile as a 2D array of pixels
    // A pixel has three components (red, green, and blue) that define the color
    // of the pixel on the screen.
    private int tileDimension;

    /*
     * One-argument Constructor
     * 1. set default values of collageDimension to 4 and tileDimension to 150
     * 2. initializes originalPicture with the filename image
     * 3. initializes collagePicture as a Picture of tileDimension*collageDimension
     * x tileDimension*collageDimension,
     * where each pixel is black (see constructors for the Picture class).
     * 4. update collagePicture to be a scaled version of original (see scaling
     * filter on Week 9 slides)
     *
     * @param filename the image filename
     */
    public Collage(String filename) {
        originalPicture = new Picture(filename);
        collageDimension = 4;
        tileDimension = 150;
        collagePicture = new Picture(tileDimension * collageDimension, tileDimension * collageDimension);
        scale(originalPicture, collagePicture);
    }

    /*
     * Three-arguments Constructor
     * 1. set default values of collageDimension to cd and tileDimension to td
     * 2. initializes originalPicture with the filename image
     * 3. initializes collagePicture as a Picture of tileDimension*collageDimension
     * x tileDimension*collageDimension,
     * where each pixel is black (see all constructors for the Picture class).
     * 4. update collagePicture to be a scaled version of original (see scaling
     * filter on Week 9 slides)
     *
     * @param filename the image filename
     */
    public Collage(String filename, int td, int cd) {

        originalPicture = new Picture(filename);
        collageDimension = cd;
        tileDimension = td;
        collagePicture = new Picture(tileDimension * collageDimension, tileDimension * collageDimension);
        scale(originalPicture, collagePicture);
    }

    /*
     * Scales the Picture @source into Picture @target size.
     * In another words it changes the size of @source to make it fit into
     * 
     * @target. Do not update @source.
     * 
     * @param source is the image to be scaled.
     * 
     * @param target is the
     */
    public static void scale(Picture source, Picture target) {

        Picture picsource = new Picture(source);
        for (int tcol = 0; tcol < target.width(); tcol++) {
            for (int trow = 0; trow < target.height(); trow++) {
                int scol = tcol * picsource.width() / target.width();
                int srow = trow * picsource.height() / target.height();
                Color color = source.get(scol, srow);
                target.set(tcol, trow, color);
            }
        }
    }

    /*
     * Returns the collageDimension instance variable
     *
     * @return collageDimension
     */
    public int getCollageDimension() {
        return collageDimension;
    }

    /*
     * Returns the tileDimension instance variable
     *
     * @return tileDimension
     */
    public int getTileDimension() {
        return tileDimension;
    }

    /*
     * Returns original instance variable
     *
     * @return original
     */

    public Picture getOriginalPicture() {
        return originalPicture;
    }

    /*
     * Returns collage instance variable
     *
     * @return collage
     */

    public Picture getCollagePicture() {
        return collagePicture;
    }

    /*
     * Display the original image
     * Assumes that original has been initialized
     */
    public void showOriginalPicture() {
        originalPicture.show();
    }

    /*
     * Display the collage image
     * Assumes that collage has been initialized
     */
    public void showCollagePicture() {
        collagePicture.show();
    }

    /*
     * Updates collagePicture to be a collage of tiles from original Picture.
     * collagePicture will have collageDimension x collageDimension tiles,
     * where each tile has tileDimension X tileDimension pixels.
     */
    public void makeCollage() {

        Picture tileT = new Picture(tileDimension, tileDimension);
        scale(collagePicture, tileT);

        for (int crow = 0; crow < collagePicture.height(); crow++) {
            for (int ccol = 0; ccol < collagePicture.height(); ccol++) {
                Color getter = tileT.get(ccol % tileDimension, crow % tileDimension);
                collagePicture.set(ccol, crow, getter);
            }
        }
    }

    /*
     * Colorizes the tile at (collageCol, collageRow) with component
     * (see Week 9 slides, the code for color separation is at the
     * book's website)
     *
     * @param component is either red, blue or green
     * 
     * @param collageCol tile column
     * 
     * @param collageRow tile row
     */
    public void colorizeTile(String component, int collageCol, int collageRow) {
        int x=collageRow*tileDimension;
        int y=collageCol*tileDimension;
        for(int i=x; i<x+tileDimension;i++){
            for(int j=y; j<y+tileDimension;j++){
                Color color = collagePicture.get(j, i);
        int r = 0;
        int g = 0;
        int b = 0;
        switch (component) {
            case "green":
                g = color.getGreen();
                collagePicture.set(j, i, new Color(0, g, 0));
                break;
            case "red":
                r = color.getRed();
                collagePicture.set(j, i, new Color(r, 0, 0));
                break;
            case "blue":
                b = color.getBlue();
                collagePicture.set(j, i, new Color(0, 0, b));
                break;
        }
    
        
    }
}
}

    /*
     * Replaces the tile at collageCol,collageRow with the image from filename
     * Tile (0,0) is the upper leftmost tile
     *
     * @param filename image to replace tile
     * 
     * @param collageCol tile column
     * 
     * @param collageRow tile row
     */
    public void replaceTile(String filename, int collageCol, int collageRow) {
        Picture tileT = new Picture(tileDimension, tileDimension);
        Picture File2 = new Picture(filename);
        scale(File2, tileT);
        int y=collageCol*tileDimension;
        int x=collageRow*tileDimension;
        for(int i=x; i<x+tileDimension;i++){
            for(int j=y; j<y+tileDimension;j++){
                Color getter = tileT.get(j % tileDimension, i % tileDimension);
                collagePicture.set(j, i, getter);
                }
            }
        
    }

    /*
     * Grayscale tile at (collageCol, collageRow)
     *
     * @param collageCol tile column
     * 
     * @param collageRow tile row
     */
    public void grayscaleTile(int collageCol, int collageRow) {
        int x=collageRow*tileDimension;
        int y=collageCol*tileDimension;
        for(int i=x; i<x+tileDimension;i++){
            for(int j=y; j<y+tileDimension;j++){
                Color color = collagePicture.get(j, i);
                Color newcolor= toGray(color);
                collagePicture.set(j,i,newcolor);
            }
        }
    }
    /**
     * Returns the monochrome luminance of the given color as an intensity
     * between 0.0 and 255.0 using the NTSC formula
     * Y = 0.299*r + 0.587*g + 0.114*b. If the given color is a shade of gray
     * (r = g = b), this method is guaranteed to return the exact grayscale
     * value (an integer with no floating-point roundoff error).
     *
     * @param color the color to convert
     * @return the monochrome luminance (between 0.0 and 255.0)
     */
    private static double intensity(Color color) {
        int r = color.getRed();
        int g = color.getGreen();
        int b = color.getBlue();
        if (r == g && r == b)
            return r; // to avoid floating-point issues
        return 0.299 * r + 0.587 * g + 0.114 * b;
    }

    /**
     * Returns a grayscale version of the given color as a {@code Color} object.
     *
     * @param color the {@code Color} object to convert to grayscale
     * @return a grayscale version of {@code color}
     */
    private static Color toGray(Color color) {
        int y = (int) (Math.round(intensity(color))); // round to nearest int
        Color gray = new Color(y, y, y);
        return gray;
    }

    /*
     * Closes the image windows
     */
    public void closeWindow() {
        if (originalPicture != null) {
            originalPicture.closeWindow();
        }
        if (collagePicture != null) {
            collagePicture.closeWindow();
        }
    }
}
