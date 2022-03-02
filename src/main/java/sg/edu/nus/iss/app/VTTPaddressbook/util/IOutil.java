package sg.edu.nus.iss.app.VTTPaddressbook.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class IOutil {
    private static final Logger logger = Logger.getLogger(IOutil. class.getName());
    public static void createDir(String path){
        logger.log(Level.INFO, "Create directory"); 
        //logger is static cos main method is static. static methods cannot call non static methods (java rules)
        File dir = new File(path);
        dir.mkdir();
        String osName = System.getProperty("os.name");
        if(!osName.contains("Windows")){  //for non-windows OS, windows user dont need to set permissions
            try{
                String perm = "rwxrwx----"; //linux notation: read write executable
                Set<PosixFilePermission> permission = PosixFilePermissions.fromString(perm);
                Files.setPosixFilePermissions(dir.toPath(), permission);
            }catch(IOException e){
                logger.log(Level.SEVERE, "Error creating directory");
            }
        }
    }
}
