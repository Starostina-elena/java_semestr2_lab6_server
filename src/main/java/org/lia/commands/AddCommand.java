package org.lia.commands;

import org.lia.managers.CollectionManager;
import org.lia.managers.CommandManager;
import org.lia.managers.FileManager;
import org.lia.models.Coordinates;
import org.lia.models.Organization;
import org.lia.models.Product;
import org.lia.models.UnitOfMeasure;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AddCommand implements Command {
    private static final long serialVersionUID = 1785464768755190753L;


    private CollectionManager collectionManager;
    private FileManager fileManager;
    private CommandManager commandManager;
    private Product product;

    public AddCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public String description() {
        return "adds an element to collection. " +
                "Pattern: add (String)name (Integer)price(may be null, use empty string) (String)partNumber (Integer)manufactureCost";
    }

    public void execute() {
        try {
            product.setup();
            collectionManager.addToCollection(product);
            System.out.println("object was successfully added");
        } catch (IllegalArgumentException e) {
            System.out.println(e + ". Please try again");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Incorrect number of arguments for add command. Please try again");
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
