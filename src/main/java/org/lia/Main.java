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

        DatagramChannel dc;
        ByteBuffer buf = ByteBuffer.allocate(1 << 16 - 1); // TODO: что за число и почему именно оно
        InetAddress host;
        int port = 6789;
        SocketAddress addr;
        addr = new InetSocketAddress(port);

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

        try {
            dc = DatagramChannel.open();
            dc.bind(addr);
            dc.configureBlocking(false);
//            Selector selector = Selector.open();
//            dc.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("connection created");
            while (true) {
//                selector.select();
//                Set<SelectionKey> keys = selector.selectedKeys();
//                for (var iter = keys.iterator(); iter.hasNext();) {
//                    SelectionKey key = iter.next();
//                    iter.remove();
//                    if (key.isValid()) {
//                        System.out.println(key);
//                        if (key.isAcceptable()) {
//                            var ssc = (DatagramChannel) key.channel();
//                            var sc = ssc.socket();
//                            Object line = "123";
//                            key.attach(line);
//                            //sc.configureBlocking(false);
//                            ssc.register(key.selector(), OP_READ);
//                        }
//                        if (key.isReadable()) {
//                            var sc = (DatagramChannel) key.channel();
//                            Object line = (Object) key.attachment();
//                            sc.read((ByteBuffer) line);
//                            System.out.println(line);
//                            sc.register(key.selector(), OP_WRITE);
//                        }
//                        if (key.isWritable()) {
//                            var sc = (DatagramChannel) key.channel();
//                            Object line = (Object) key.attachment();
//                            sc.write((ByteBuffer) line);
//                        }
//                    }
//                }

                SocketAddress address = dc.receive(buf);
                //buf = ByteBuffer.wrap(line.getBytes());
                //System.out.println(buf);
                //addr = dc.receive(buf);
                if (address != null) {
                    //System.out.println(buf.array());
                    //System.out.println(address);
                    buf.flip();
                    //System.out.println(new ObjectInputStream(new ByteArrayInputStream(buf.array())));
                    try {
                        ByteArrayInputStream in = new ByteArrayInputStream(buf.array());
                        ObjectInputStream is = new ObjectInputStream(in);
                        System.out.println(is);
                    } catch (EOFException e) {
                        System.out.println(e);
                    }
//                    Request request = (Request) is.readObject();
//                    logger.info("receive request, deserialize request");
//                    String commandMessage = (String) request.getCommandName();
//                    if (commands.containsKey(commandMessage)) {
//                        AbstractCommand commandExe = commands.get(commandMessage);
//                        CommandResultDto commandResultDto = commandExe.execute(request, collectionManager,
//                                historyManagerImpl);
//                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                        ObjectOutputStream oos = new ObjectOutputStream(baos);
//                        oos.writeObject(commandResultDto);
//                        logger.info("serialize result");
//                        byte[] secondaryBuffer = baos.toByteArray();
//                        ByteBuffer mainBuffer = ByteBuffer.wrap(secondaryBuffer);
//                        server.send(mainBuffer, address);
//                        logger.info("By server send data to client");
//                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }

    }
}