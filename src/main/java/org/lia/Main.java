package org.lia;

import org.lia.managers.CollectionManager;
import org.lia.managers.CommandManager;
import org.lia.managers.FileManager;

import java.io.ByteArrayInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.net.SocketAddress;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;

import static java.nio.channels.SelectionKey.OP_READ;
import static java.nio.channels.SelectionKey.OP_WRITE;

public class Main {
    public static void main(String[] args) {

        String fileName = System.getenv("collectionFileName");  //файл, где хранится начальное состояние коллекции
        if (fileName == null) {
            System.out.println("Please specify collectionFileName in environment variables");
            System.exit(0);
        }

        FileManager fileManager = new FileManager(fileName);
        CollectionManager collection;

        FileManager backup = new FileManager("urgentSaving.xml");
        if (backup.checkFileExists()) {
            System.out.println("backup was located. Do you want to restore your data? Y/n");
            System.out.print("> ");
            Scanner in = new Scanner(System.in);
            String answer = in.nextLine().toUpperCase();
            while (!answer.equals("Y") & !answer.equals("N")) {
                System.out.println("Wrong input, try again. Do you want to restore your data? Y/n");
                System.out.print("> ");
                answer = in.nextLine().toUpperCase();
            }
            if (answer.equals("Y")) {
                collection = backup.readCollection();
            } else {
                collection = fileManager.readCollection();
            }
            // удалить файл
            java.io.File file = new java.io.File("urgentSaving.xml");
            file.delete();
        } else {
            collection = fileManager.readCollection();
        }

        CommandManager commandManager = new CommandManager(collection, fileManager);
        System.out.println("program initialization: successful");

        commandManager.listen();

    }
}