// src/server/FileServantImpl.java
package server;

import FileTransferApp.*;
import java.io.*;

public class FileServantImpl extends FileTransferPOA {
    
    private String serverDirectory;
    
    public FileServantImpl(String directory) {
        this.serverDirectory = directory;
    }
    
    public byte[] downloadFile(String fileName) {
        try {
            File file = new File(serverDirectory + File.separator + fileName);
            
            if (!file.exists()) {
                System.out.println("File not found: " + fileName);
                return new byte[0];
            }
            
            byte[] buffer = new byte[(int)file.length()];
            BufferedInputStream input = new BufferedInputStream(
                new FileInputStream(file));
            
            input.read(buffer, 0, buffer.length);
            input.close();
            
            System.out.println("Sending file: " + fileName + " (" + buffer.length + " bytes)");
            return buffer;
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
            e.printStackTrace();
            return new byte[0];
        }
    }
}
