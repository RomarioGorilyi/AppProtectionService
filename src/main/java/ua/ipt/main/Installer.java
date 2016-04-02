package ua.ipt.main;

import ua.ipt.signature.Signature;
import ua.ipt.signature.WinRegistry;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.UnknownHostException;
import java.nio.channels.FileChannel;

/**
 * Created by Roman Horilyi on 01.04.2016.
 */
public class Installer {
    private static final String LAB_1_JAR_PATH = "/src/main/resources/Lab1.jar";

    public static void writeSignature(){
        String username = System.getProperty("user.name");

        java.net.InetAddress localMachine = null;
        try {
            localMachine = java.net.InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        String machineHostname = localMachine != null ? localMachine.getHostName() : null;

        String osName = System.getProperty("os.name");
        int mouseButtonsNumber = java.awt.MouseInfo.getNumberOfButtons();
        double displayWidth = java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth();

        File[] roots = File.listRoots();
        long diskCapacity = roots[1].getTotalSpace();

        Signature signature = new Signature(username, machineHostname, osName, mouseButtonsNumber, displayWidth,
                diskCapacity);

        // IMPORTANT: after creating this new key comment the code below !!!
        try {
            WinRegistry.createKey(
                    WinRegistry.HKEY_CURRENT_USER,              // HKEY
                    "SOFTWARE\\JetBrains\\IntelliJ IDEA");      // Key
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        // till here

        try {
            WinRegistry.writeStringValue(
                    WinRegistry.HKEY_CURRENT_USER,
                    "SOFTWARE\\JetBrains\\IntelliJ IDEA",
                    "Roman_Horilyi",
                    String.valueOf(signature.hashCode()));
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static void install(String path) throws IOException {
        writeSignature();
        System.out.println("Signing completed");
        try (
                FileChannel source = new FileInputStream(new File(LAB_1_JAR_PATH)).getChannel();
                FileChannel dest = new FileOutputStream(new File(path + "/Lab1.jar")).getChannel()
        ) {
            source.transferTo(0, source.size(), dest);
        }
        System.out.println("Installation completed.");
    }
}
