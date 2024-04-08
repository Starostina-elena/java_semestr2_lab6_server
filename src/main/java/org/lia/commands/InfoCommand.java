package org.lia.commands;

import org.lia.managers.CollectionManager;
import org.lia.managers.CommandManager;
import org.lia.managers.FileManager;

public class InfoCommand implements Command {
    private static final long serialVersionUID = 1785464768755190753L;


    private CollectionManager collectionManager;
    private FileManager fileManager;
    private CommandManager commandManager;

    public InfoCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public String description() {
        return "shows short information on collection";
    }

    public void execute() {
        System.out.println(collectionManager.shortInfo());
    }

    public void setCollectionManager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public void setFileManager(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    public void setCommandManager(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

}
