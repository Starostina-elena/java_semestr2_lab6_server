package org.lia.commands;

import org.lia.managers.CollectionManager;
import org.lia.managers.CommandManager;
import org.lia.managers.FileManager;
import org.lia.models.Product;

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

    public void execute() {
        try {
            Product product = collectionManager.getById(id);
            int counter = 0;
            for (Product c : collectionManager.getProductCollection()) {
                if (product.compareTo(c) > 0) {
                    counter++;
                    collectionManager.removeFromCollection(c);
                }
            }
            System.out.println(counter + " products were successfully deleted");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Incorrect number of arguments for remove_lower command. Please try again");
        } catch (NumberFormatException e) {
            System.out.println("id is not integer. Please try again");
        } catch (IllegalArgumentException e) {
            System.out.println(e + ". Please try again");
        }
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
