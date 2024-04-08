package org.lia.commands;

import org.lia.managers.CollectionManager;
import org.lia.managers.CommandManager;
import org.lia.managers.FileManager;

public class MinByIdCommand implements Command {
    private static final long serialVersionUID = 1785464768755190753L;

    private CollectionManager collectionManager;
    private FileManager fileManager;
    private CommandManager commandManager;

    public MinByIdCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public String description() {
        return "shows element with min id";
    }

    public void execute() {
        System.out.println(collectionManager.getProductCollection().getFirst());
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
