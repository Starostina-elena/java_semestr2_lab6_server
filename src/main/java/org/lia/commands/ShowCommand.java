package org.lia.commands;

import org.lia.managers.CollectionManager;
import org.lia.managers.CommandManager;
import org.lia.managers.FileManager;
import org.lia.models.Product;
import org.lia.tools.Response;

public class ShowCommand implements Command {
    private static final long serialVersionUID = 1785464768755190753L;

    private CollectionManager collectionManager;
    private FileManager fileManager;
    private CommandManager commandManager;

    public ShowCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public String description() {
        return "shows elements in collection";
    }

    public Response execute() {
        Response response = new Response();
        long cnt = 0;
        for (Product c : collectionManager.getProductCollection()) {
            if (cnt > 200) {
                response.addAnswer(collectionManager.getNumberOfElements() - cnt + " more elements");
                break;
            }
            response.addAnswer(c.toString());
            cnt++;
        }
        return response;
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
