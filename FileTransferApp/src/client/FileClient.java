// src/client/FileClient.java
package client;

import FileTransferApp.*;
import org.omg.CosNaming.*;
import org.omg.CORBA.*;
import java.io.*;

public class FileClient {
    
    public static void main(String[] args) {
        try {
            // Validate arguments
            if (args.length < 1) {
                System.out.println("Usage: java client.FileClient <fileName> [outputDirectory]");
                return;
            }
            
            String fileName = args[0];
            String outputDir = ".";
            if (args.length > 1) {
                outputDir = args[1];
            }
            
            // Create and initialize the ORB
            ORB orb = ORB.init(args, null);
            
            // Get the root naming context
            org.omg.CORBA.Object objRef = 
                orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
            
            // Resolve the object reference in naming
            String name = "FileTransfer";
            FileTransfer fileTransferRef = FileTransferHelper.narrow(
                ncRef.resolve_str(name));
            
            System.out.println("Requesting file: " + fileName);
            
            // Call the server method
            byte[] fileData = fileTransferRef.downloadFile(fileName);
            
            if (fileData.length == 0) {
                System.out.println("File not found or empty");
                return;
            }
            
            // Save the file
            File outputFile = new File(outputDir + File.separator + fileName);
            BufferedOutputStream output = new BufferedOutputStream(
                new FileOutputStream(outputFile));
            
            output.write(fileData, 0, fileData.length);
            output.flush();
            output.close();
            
            System.out.println("File downloaded successfully: " + 
                outputFile.getAbsolutePath() + " (" + fileData.length + " bytes)");
            
        } catch (Exception e) {
            System.out.println("ERROR: " + e);
            e.printStackTrace(System.out);
        }
    }
}
