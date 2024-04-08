package org.lia.managers;

import org.lia.commands.*;
import org.lia.tools.Response;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**CommandManager class. Provides operations with commands*/
public class CommandManager {

    private Map<String, Command> commandsManager = new HashMap<>();
    private CollectionManager collectionManager;
    private FileManager fileManager;

    DatagramChannel dc;
    ByteBuffer buf = ByteBuffer.allocate(1 << 16 - 1); // TODO: что за число и почему именно оно
    InetAddress host;
    int port = 6789;
    SocketAddress addr = new InetSocketAddress(port);

    /**Constructor. Loading of available commands*/
    public CommandManager(CollectionManager collectionManager, FileManager fileManager) {

        this.collectionManager = collectionManager;
        this.fileManager = fileManager;

        commandsManager.put("add", new AddCommand(this.collectionManager));
        commandsManager.put("info", new InfoCommand(this.collectionManager));
        commandsManager.put("show", new ShowCommand(this.collectionManager));
        commandsManager.put("update", new UpdateCommand(this.collectionManager));
        commandsManager.put("remove_by_id", new RemoveByIdCommand(this.collectionManager));
        commandsManager.put("clear", new ClearCommand(this.collectionManager));
        commandsManager.put("remove_head", new RemoveHeadCommand(this.collectionManager));
        commandsManager.put("add_if_max", new AddIfMaxCommand(this.collectionManager));
        commandsManager.put("remove_lower", new RemoveLowerCommand(this.collectionManager));
        commandsManager.put("min_by_id", new MinByIdCommand(this.collectionManager));
        commandsManager.put("count_by_part_number", new CountByPartNumberCommand(this.collectionManager));
        commandsManager.put("print_field_ascending_manufacturer", new PrintFieldAscendingManufacturerCommand(this.collectionManager));

    }

    public void listen() {
        try {
            dc = DatagramChannel.open();
            dc.bind(addr);
            dc.configureBlocking(false);
            System.out.println("connection created");
            while (true) {
                SocketAddress address = dc.receive(buf);
                if (address != null) {
                    buf.flip();
                    try {
                        ByteArrayInputStream in = new ByteArrayInputStream(buf.array());
                        ObjectInputStream is = new ObjectInputStream(in);
                        Command command = (Command) is.readObject();
                        command.setCollectionManager(collectionManager);
                        command.setCommandManager(this);
                        command.setFileManager(fileManager);
                        Response response = command.execute();
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        ObjectOutputStream oos = new ObjectOutputStream(baos);
                        oos.writeObject(response);
                        byte[] secondaryBuffer = baos.toByteArray(); //TODO: зачем...
                        ByteBuffer mainBuffer = ByteBuffer.wrap(secondaryBuffer);
                        dc.send(mainBuffer, address);
                    } catch (EOFException | ClassNotFoundException e) {
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

    public Map<String, Command> getCommandsList() {
        return commandsManager;
    }

    public CollectionManager getCollectionManager() {
        return collectionManager;
    }

    public FileManager getFileManager() {
        return fileManager;
    }

}