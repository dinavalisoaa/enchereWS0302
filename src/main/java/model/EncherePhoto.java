package model;

import BddObject.Connexion;
import BddObject.InfoDAO;
import BddObject.ObjectBDD;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import javax.imageio.ImageIO;
import org.apache.commons.codec.binary.Base64;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import utils.UFunction;

/**
 *
 * @author dina
 */
@InfoDAO(table = "EncherePhoto")
public class EncherePhoto extends ObjectBDD {

    private int enchereId = -1;
    private String path;
    private String photo;

    public int getEnchereId() {
        return this.enchereId;
    }

    /**
     *
     * @param enchereId
     */
    public void setEnchereId(int enchereId) {
        this.enchereId = enchereId;
    }

    private static String encodeFileToBase64Binary(String fileName)
            throws IOException {

        File file = new File(fileName);
        byte[] bytes = loadFile(file);
        byte[] encoded = Base64.encodeBase64(bytes);
        String encodedString = new String(encoded, StandardCharsets.US_ASCII);
        encodedString = new String(Base64.encodeBase64(bytes), "UTF-8");

        return encodedString;
    }

    private static byte[] loadFile(File file) throws IOException {
        InputStream is = new FileInputStream(file);

        long length = file.length();
        if (length > Integer.MAX_VALUE) {
            // File is too large
        }
        byte[] bytes = new byte[(int) length];

        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length
                && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
            offset += numRead;
        }

        if (offset < bytes.length) {
            throw new IOException("Could not completely read file " + file.getName());
        }

        is.close();
        return bytes;
    }
public static String convert(String file) {
        String base64 = null;
        final ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            //Can be any support image format
            final String formatName = file.split("\\.")[1];

            //Convert image data to Base64 and write date to the output stream 
//    final BufferedImage image = ImageIO.read(new File("D:\\cap.png"));
            final BufferedImage image = ImageIO.read(new File(file));

            ImageIO.write(image, formatName, java.util.Base64.getEncoder().wrap(os));

            //Create Base64 string
            base64 = os.toString(StandardCharsets.UTF_8.name());
//    System.out.println("-"+base64/);
        } catch (final IOException ioe) {

            throw new UncheckedIOException(ioe);
        }
        return base64;
    }
    public void setPIC(String image) throws Exception {
        try {
            String files_ = "data:image/png;base64, " +convert(image);
            EncherePhoto ph = new EncherePhoto();
            ph.setPhoto(files_);
            ph.setEnchereId(this.enchereId);
            ph.insert(null);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public String getPath() {
        return this.path;
    }

    /**
     *
     * @param path
     */
    public void setPath(String path) {
        this.path = path;
    }

    public String getPhoto() {
        return this.photo;
    }

    /**
     *
     * @param photo
     */
    public void setPhoto(String photo) {
        this.photo = photo;
    }

}
