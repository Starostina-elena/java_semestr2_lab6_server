package org.lia.commands;

import org.lia.managers.CollectionManager;
import org.lia.managers.CommandManager;
import org.lia.managers.FileManager;

import java.util.ArrayDeque;

public class ClearCommand implements Command {
    private static final long serialVersionUID = 1785464768755190753L;


    private CollectionManager collectionManager;
    private FileManager fileManager;
    private CommandManager commandManager;

    public ClearCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public String description() {
        return "clears collection";
    }

    public void execute() {
        collectionManager.setProductCollection(new ArrayDeque<>());
        System.out.println("Collection was successfully cleared");
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
