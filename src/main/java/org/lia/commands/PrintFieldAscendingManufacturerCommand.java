package org.lia.commands;

import org.lia.managers.CollectionManager;
import org.lia.managers.CommandManager;
import org.lia.managers.FileManager;
import org.lia.models.Organization;
import org.lia.models.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PrintFieldAscendingManufacturerCommand implements Command {
    private static final long serialVersionUID = 1785464768755190753L;

    private CollectionManager collectionManager;
    private FileManager fileManager;
    private CommandManager commandManager;

    public PrintFieldAscendingManufacturerCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public String description() {
        return "shows all manufacturers in ascending orders";
    }

    public void execute() {
        List<Organization> orgList = new ArrayList<>();
        for (Product c : collectionManager.getProductCollection()) {
            orgList.add(c.getManufacturer());
        }
        Collections.sort(orgList);
        for (Organization c : orgList) {
            System.out.println(c);
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
