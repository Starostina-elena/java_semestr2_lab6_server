package org.lia.commands;

import org.lia.managers.CollectionManager;
import org.lia.managers.CommandManager;
import org.lia.managers.FileManager;
import org.lia.models.Product;
import org.lia.tools.Response;

public class CountByPartNumberCommand implements Command {
    private static final long serialVersionUID = 1785464768755190753L;

    private CollectionManager collectionManager;
    private FileManager fileManager;
    private CommandManager commandManager;
    private String partNumber;

    public CountByPartNumberCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public String description() {
        return "shows number of element with specified part number. Pattern: count_by_part_number (String)partNumber";
    }

    public Response execute() {
        Response response = new Response();
        try {
            int cnt = 0;
            for (Product c : collectionManager.getProductCollection()) {
                if (c.getPartNumber().equals(partNumber)) {
                    cnt++;
                }
            }
            response.addAnswer("There are " + cnt +  " products with this partNumber");
        } catch (ArrayIndexOutOfBoundsException e) {
            response.addAnswer("Incorrect number of arguments for count_by_part_number command. Please try again");
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
