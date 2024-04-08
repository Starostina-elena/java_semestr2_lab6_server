package org.lia.commands;

import org.lia.managers.CollectionManager;

public class ShowCommand implements Command {

    private CollectionManager collectionManager;

    public ShowCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public String description() {
        return "shows elements in collection";
    }

    public void execute(String[] arguments) {
        collectionManager.show();
    }

}
