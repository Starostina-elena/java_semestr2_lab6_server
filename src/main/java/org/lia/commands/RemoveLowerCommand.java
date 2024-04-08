package org.lia.commands;

import org.lia.managers.CollectionManager;
import org.lia.managers.CommandManager;
import org.lia.managers.FileManager;
import org.lia.models.Product;
import org.lia.tools.Response;

public class RemoveLowerCommand implements Command {
    private static final long serialVersionUID = 1785464768755190753L;

    private CollectionManager collectionManager;
    private FileManager fileManager;
    private CommandManager commandManager;
    private long id;

    public RemoveLowerCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public String description() {
        return "delete from collection all elements lower that selected. Pattern: remove_lower (long)id";
    }

    public Response execute() {
        Response response = new Response();
        try {
            Product product = collectionManager.getById(id);
            int counter = 0;
            for (Product c : collectionManager.getProductCollection()) {
                if (product.compareTo(c) > 0) {
                    counter++;
                    collectionManager.removeFromCollection(c);
                }
            }
            response.addAnswer(counter + " products were successfully deleted");
        } catch (ArrayIndexOutOfBoundsException e) {
            response.addAnswer("Incorrect number of arguments for remove_lower command. Please try again");
        } catch (NumberFormatException e) {
            response.addAnswer("id is not integer. Please try again");
        } catch (IllegalArgumentException e) {
            response.addAnswer(e + ". Please try again");
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
