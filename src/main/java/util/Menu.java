package util;

import java.util.Scanner;

public class Menu {


    public static int getChoice(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("1:Car Details " +
                "2:Car Entry " +
                "3:exit Car " +
                "4:Get PlateNumber" +
                "5:Get SlotNumber" +
                "10:Exit");
        // Input Validation
        int choice = 0;
        boolean loop = true;
        while (loop) {
            System.out.print("Enter choice number: ");
            if (!scanner.hasNextInt() || !scanner.hasNext()) {
                System.out.println("try again...");
                scanner.nextLine();
            } else {
                choice = scanner.nextInt();
                if (choice <= 10 && choice >= 1)
                    loop = false;
                else {
                    System.out.println("try again... ");
                    scanner.nextLine();
                }
            }
        }
        return choice;
    }




}
