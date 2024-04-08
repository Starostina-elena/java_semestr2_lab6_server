package org.lia.commands;

import org.lia.managers.CollectionManager;
import org.lia.managers.CommandManager;
import org.lia.managers.FileManager;
import org.lia.tools.Response;

import java.io.Serializable;

public interface Command extends Serializable {
    Response execute();
    void setCollectionManager(CollectionManager collectionManager);
    void setFileManager(FileManager fileManager);
    void setCommandManager(CommandManager commandManager);
    String description();
}
