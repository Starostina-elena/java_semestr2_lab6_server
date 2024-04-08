package org.lia.commands;

import org.lia.managers.CollectionManager;
import org.lia.managers.CommandManager;
import org.lia.managers.FileManager;

public class SaveCommand implements Command {

    private FileManager fileManager;
    private CollectionManager collectionManager;
    private CommandManager commandManager;

    public SaveCommand(FileManager fileManager, CollectionManager collectionManager) {
        this.fileManager = fileManager;
        this.collectionManager = collectionManager;
    }

    public String description() {
        return "saves collection to file";
    }

    public void execute() {
        fileManager.writeCollection(collectionManager.getProductCollection());
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
