package org.lia.commands;

import org.lia.managers.CollectionManager;
import org.lia.managers.CommandManager;
import org.lia.managers.FileManager;
import org.lia.models.Coordinates;
import org.lia.models.Organization;
import org.lia.models.Product;
import org.lia.models.UnitOfMeasure;
import org.lia.tools.Response;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UpdateCommand implements Command {
    private static final long serialVersionUID = 1785464768755190753L;

    private CollectionManager collectionManager;
    private FileManager fileManager;
    private CommandManager commandManager;
    private Product product;

    public UpdateCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public String description() {
        return "update product by it's id. Pattern: update (long)id";
    }

    public Response execute() {
        Response response = new Response();
        //try {
//
//            System.out.println(product);
//            System.out.println("Enter new name");
//            System.out.print("> ");
//            Scanner in = new Scanner(System.in);
//            while (true) {
//                try {
//                    String name = in.nextLine();
//                    product.setName(name);
//                    break;
//                } catch (IllegalArgumentException e) {
//                    System.out.println(e + ". Please try again");
//                    System.out.print("> ");
//                }
//            }
//            Coordinates coords;
//            long x;
//            double y;
//            while (true) {
//                try {
//                    System.out.println("Enter coordinates (long)x");
//                    System.out.print("> ");
//                    x = in.nextLong();
//                    System.out.println("Enter coordinates (double)y");
//                    System.out.print("> ");
//                    y = in.nextDouble();
//                    coords = new Coordinates(x, y);
//                    break;
//                } catch (InputMismatchException e) {
//                    System.out.println("Wrong coordinates, try again");
//                    in.nextLine();
//                }
//            }
//            product.setCoordinates(coords);
//            System.out.println("Enter new price (integer)");
//            while (true) {
//                try {
//                    System.out.print("> ");
//                    Integer price = in.nextInt();
//                    product.setPrice(price);
//                    break;
//                } catch (InputMismatchException e) {
//                    System.out.println("Price should be integer. Please try again");
//                    in.nextLine();
//                } catch (IllegalArgumentException e) {
//                    System.out.println(e + ". Please try again");
//                }
//            }
//            System.out.println("Enter new partNumber");
//            System.out.print("> ");
//            in.nextLine();
//            while (true) {
//                try {
//                    String partNumber = in.nextLine();
//                    product.setPartNumber(partNumber);
//                    break;
//                } catch (IllegalArgumentException e) {
//                    System.out.println(e + ". Please try again");
//                    System.out.print("> ");
//                }
//            }
//            System.out.println("Enter new manufactureCost (integer)");
//            while (true) {
//                try {
//                    System.out.print("> ");
//                    Integer manufactureCost = in.nextInt();
//                    product.setManufactureCost(manufactureCost);
//                    break;
//                } catch (InputMismatchException e) {
//                    System.out.println("manufactureCost should be integer. Please try again");
//                    in.nextLine();
//                } catch (IllegalArgumentException e) {
//                    System.out.println(e + ". Please try again");
//                }
//            }
//            ArrayList<String> unitOfMeasures = new ArrayList<>();
//            System.out.println("Enter one of unit of measure:");
//            for (UnitOfMeasure c : UnitOfMeasure.values()) {
//                System.out.println(c);
//                unitOfMeasures.add(c.name());
//            }
//            System.out.print("> ");
//            in.nextLine();
//            String unitOfMeasure = in.nextLine().toUpperCase();
//            while (!unitOfMeasures.contains(unitOfMeasure) & !unitOfMeasure.isBlank()) {
//                System.out.println("Wrong unit of measure, please try again:");
//                System.out.print("> ");
//                unitOfMeasure = in.nextLine().toUpperCase();
//            }
//            UnitOfMeasure resUnitOfMeasure;
//            if (unitOfMeasure.isBlank()) {
//                resUnitOfMeasure = null;
//            } else {
//                resUnitOfMeasure = UnitOfMeasure.valueOf(unitOfMeasure);
//            }
//            product.setUnitOfMeasure(resUnitOfMeasure);
//            Organization org;
//            while (true) {
//                try {
//                    System.out.println("Enter organization (String)name");
//                    System.out.print("> ");
//                    String name = in.nextLine();
//                    System.out.println("Enter organization (String)fullName. Press enter to leave this field empty");
//                    System.out.print("> ");
//                    String fullName = in.nextLine();
//                    if (fullName.isBlank()) {
//                        fullName = null;
//                    }
//                    Integer employeesCount;
//                    while (true) {
//                        try {
//                            System.out.println("Enter organization (Integer)employeesCount. Press enter to leave this field empty");
//                            System.out.print("> ");
//                            String inEmployeesCount = in.nextLine();
//                            if (inEmployeesCount.isBlank()) {
//                                employeesCount = null;
//                            } else {
//                                employeesCount = Integer.parseInt(inEmployeesCount);
//                            }
//                            break;
//                        } catch (NumberFormatException e) {
//                            System.out.println("Wrong employeesCount. Please try again:");
//                        }
//                    }
//                    org = new Organization(name, fullName, employeesCount);
//                    break;
//                } catch (IllegalArgumentException e) {
//                    System.out.println(e + ". Please try again");
//                }
//            }
//            product.setManufacturer(org);
//            System.out.println("Updated successfully");
//        } catch (ArrayIndexOutOfBoundsException e) {
//            System.out.println("Incorrect number of arguments for update command. Please try again");
//        } catch (NumberFormatException e) {
//            System.out.println("id is not integer. Please try again");
//        } catch (IllegalArgumentException e) {
//            System.out.println(e + ". Please try again");
//        }
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
