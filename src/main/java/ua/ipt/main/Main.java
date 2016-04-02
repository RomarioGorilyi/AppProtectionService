package ua.ipt.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Roman Horilyi on 01.04.2016.
 */
public class Main {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        System.out.println("Enter path to install the project: ");
        String path = reader.readLine();
        Installer.install(path);
    }
}
