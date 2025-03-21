// src/server/FileServer.java
package server;

import FileTransferApp.*;
import org.omg.CosNaming.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;

public class FileServer {
    
    public static void main(String[] args) {
        try {
            // Create and initialize the ORB first
            ORB orb = ORB.init(args, null);
            
            // Extract the directory path (should be the last argument)
            String fileDirectory = ".";
            boolean isORBArg = false;
            for (int i = 0; i < args.length; i++) {
                // Skip ORB arguments and their values
                if (args[i].startsWith("-ORB")) {
                    isORBArg = true;
                    continue;
                }
                if (isORBArg) {
                    isORBArg = false;
                    continue;
                }
                // Non-ORB argument is assumed to be the directory
                fileDirectory = args[i];
            }
            
            // Get reference to rootpoa & activate the POAManager
            POA rootpoa = POAHelper.narrow(
                orb.resolve_initial_references("RootPOA"));
            rootpoa.the_POAManager().activate();
            
            // Create servant and register it with the ORB
            FileServantImpl fileServant = new FileServantImpl(fileDirectory);
            
            // Get object reference from the servant
            org.omg.CORBA.Object ref = rootpoa.servant_to_reference(fileServant);
            FileTransfer fileTransferRef = FileTransferHelper.narrow(ref);
            
            // Get the root naming context
            org.omg.CORBA.Object objRef = 
                orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
            
            // Bind the object reference in naming
            String name = "FileTransfer";
            NameComponent path[] = ncRef.to_name(name);
            ncRef.rebind(path, fileTransferRef);
            
            System.out.println("FileServer ready and waiting...");
            System.out.println("Serving files from directory: " + fileDirectory);
            
            // Wait for invocations from clients
            orb.run();
        } catch (Exception e) {
            System.err.println("ERROR: " + e);
            e.printStackTrace(System.out);
        }
    }
}
