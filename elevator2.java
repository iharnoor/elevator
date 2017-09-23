package Assignments;
/**
 * @author Harnoor Singh
 * Program name: elevator2.java
 * Due Date: Sept 18, 2017
 * Purpose of the program:
 * The program behaves like a real elevator2 with random values. It makes the elevator2 go up and then down. It makes
 * 8 stops while going up and 5 stops while going down. Total floors in the building are 12.
 * The elevator2 stops for 2 seconds at every floor it crosses, on the other hand for 3 seconds it stops.
 * The elevator2 also prompts the user to enter the direction and floor number at every floor it stops and drops
 * the user to that floor accordingly. If the direction of the elevator2 is opposite of the direction the elevator2
 * is going or the floor number is less than the floor it is going, the program ignores the user's input.
 * <p>
 * Solution for problem and algorithms used:
 * The solution for the program was mainly dividing the tasks into smaller fragments so that all of them
 * can be handled easily and in a best possible way. At the beginning of the program in the main method
 * random values are filled into up_List and down_List (Up ArrayList and Down Array List) from the total possible
 * floors that are 1-12. Total number of floors are stored in separate ArrayList as well. The up_List is sorted in
 * increasing order and the down_List is sorted in decreasing order. The elevator2's movement up and down is handled
 * separately. The runLiftUp method is called to for the elevator2's journey in up direction and code for elevator2's
 * movement is handled in main method. Separate methods are used for timers at very floor and at every stop.
 * Furthermore, even the user Input is handled in separate method so that it can be called at every floor the
 * elevator2 stops.
 * <p>
 * Data Structures used in this solution:
 * ArrayLists to store the floor numbers for both going up and down and also to store total number of floors.
 * <p>
 * Description of how to use the program and expected input/output:
 * In order to use this program, the user will need to run the program and watch at what floor it stops and
 * then enter the direction and floor number where they want to go. User will also be prompted if they want to run
 * the elevator2 again; then he or she must enter yes or no.
 * <p>
 * Purpose of this class:
 * There is only one class used in this program and this class is the main method class which contains all the
 * methods which are either called in the main method or within a method itself.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

public class elevator2 {
    /**
     * The beginning of the program and main method where both up and down ArrayLists are filled with random numbers
     * by calling the fillList method and then the runLiftUp method is called and then the algorithm for the lift to go
     * down is used. Two global variable are used in main method that are the listFloors and the direction. The
     * listFloors ArrayList contains the total number of floors in the building and direction is used to keep track
     * elevator2's direction up or down.
     *
     * @param args
     */
    private static ArrayList<Integer> listFloors;
    private static String direction;
    private static int MaxUp;

    public static void main(String[] args) {
        listFloors = totalFloors(12);
        System.out.println(listFloors.toString());
        while (true) {
            ArrayList<Integer> up_List = fillList(8);
            ArrayList<Integer> down_List = fillList(5);
            Collections.sort(up_List);
            Collections.sort(down_List);
            Collections.reverse(down_List);

            System.out.println("up_ArrayList: " + up_List.toString());
            System.out.println("down_ArrayList: " + down_List.toString());
            MaxUp = Collections.max(up_List);
//            Update the stop floor somehow to max after the userInput
            runLiftUp(up_List, down_List);

            if (MaxUp < down_List.get(0)) {
                direction = "up";
                System.out.println("Starting at floor: " + MaxUp);
                while (down_List.get(0) > MaxUp) {
                    System.out.print("\tGoing up: ");
                    timerAtFloors();
                    System.out.println("now at floor " + ++MaxUp);
                }
                down_List.remove(0);
                userInputUp(up_List);// Comment the userInput if you don't want to prompt the user
                System.out.println("Stopping at floor: " + MaxUp + " for 3 sec");
                timerAtStop();
            } else if (MaxUp == down_List.get(0)) {
                down_List.remove(0);
                System.out.println(down_List.toString());
            }
            int floor = MaxUp - 1;
            while (down_List.size() > 0) {
                direction = "down";
                System.out.println("Starting at floor: " + MaxUp);
                MaxUp = down_List.get(0);
                while (floor >= MaxUp) {
                    System.out.print("\tGoing down: ");
                    timerAtFloors();
                    System.out.println("now at floor " + floor);
                    floor -= 1;
                }
                int max = Collections.max(down_List);
                MaxUp = down_List.remove(0);
                System.out.print("Stopping at floor: " + MaxUp + " for 3 sec");
                timerAtStop();
                if (down_List.size() > 0)
                    userInputDown(down_List);// Comment the userInput if you don't want to prompt the user
                System.out.println();
                System.out.println(down_List.toString());
            }
            if (down_List.size() == 0) {
                System.out.println("Do you want to continue? Enter y or n");
                Scanner input = new Scanner(System.in);
                String yesOrNo = input.next();
                if (yesOrNo.equalsIgnoreCase("y"))
                    continue;
                else break;
            }
        }
    }

    /**
     * @param up This method accepts a ArrayList as a parameter and it uses it in order to loop through it so that the elevator2
     *           can stop at the every floor number it contains and just cross the floor for the floor number it doesn't.
     *           It sets global variable direction value to up as the elevator2 is going up in this method.
     *           It also sets the value of maxUp to maximum value of the up_List so that it can be used when the elevator
     *           is going down.
     *
     * @param down It also accepts the second ArrayList that is down_List so that it can be used while taking user input.
     *             And also when the elevator is going down till the max element of the down_List.
     */
    public static void runLiftUp(ArrayList<Integer> up, ArrayList<Integer> down) {
        direction = "up";
        int floor = 2;
        int stopFloor = 1;
        while (up.size() > 0) {
            System.out.println("Starting at floor: " + stopFloor);
            stopFloor = up.get(0);
            while (floor <= stopFloor) {
                System.out.print("\tGoing up: ");
                timerAtFloors();
                System.out.println("now at floor " + floor);
                floor += 1;
            }

            stopFloor = up.remove(0);
            if (stopFloor > MaxUp) {
                MaxUp = stopFloor;
            }

            System.out.print("Stopping at floor " + stopFloor + " for 3 sec");
            timerAtStop();
            if (stopFloor == 12) {
                direction = "down";
                userInputDown(down);// Comment the userInput if you don't want to prompt the user
            } else userInputUp(up);
            System.out.println();
            System.out.println(up.toString());
        }
    }

    /**
     * @param list Accepts an ArrayList and uses it in order to add the floor number where a user wants to go while going up. It
     *             will add the floor number to the ArrayList only if the floor number is greater that or equal to 2 or less than
     *             or equal to 12. Furthermore, It will only add if it the upList doesn't already contain the floor number.
     *             It uses the global variable direction to verify the direction of user's input.
     *
     * The method assumes the that the user will input a string character for the elevator's direction and a integer for
     *             the floor number
     */
    public static void userInputUp(ArrayList<Integer> list) {
        Scanner input = new Scanner(System.in);
        System.out.println("\nWhere do you want to go up or down? Enter u or d\n \t Also enter the floor number separated by space");
        String dir;
        int floor;
        try {
            dir = input.next();
            floor = input.nextInt();
        } catch (Exception e) {
            System.out.println("Enter the direction and the floor number again");
            input.nextLine();
            dir = input.next();
            floor = input.nextInt();

        }
        if (floor > 12 || floor < 2 || floor < Collections.min(list) || !dir.equalsIgnoreCase("u")) {
            System.out.println("Ignored");

        } else if (list.size() >= 0)
            if (dir.equalsIgnoreCase("u") && direction.equals("up") && !list.contains(floor)) {
                list.add(floor);
                Collections.sort(list);
            }
    }

    /**
     * @param list Accepts an ArrayList and uses it in order to add the floor number where a user wants to go while going down. It
     *             will add the floor number to the ArrayList only if the floor number is greater that or equal to 2 or less than
     *             or equal to 12. Furthermore, It will only add if it the upList doesn't already contain the floor number.
     *             It uses the global variable direction to verify the direction of user's input.
     *
     * The method assumes the that the user will input a string character for the elevator's direction and a integer for
     *             the floor number
     */
    public static void userInputDown(ArrayList<Integer> list) {
        Scanner input = new Scanner(System.in);
        System.out.println("\nWhere do you want to go up or down? Enter u or d\n \t Also enter the floor number separated by space");
        String dir;
        int floor;
        try {

            dir = input.next();
            floor = input.nextInt();
        } catch (Exception e) {
            System.out.println("Enter the direction and the floor number again");
            dir = input.next();
            floor = input.nextInt();

        }

        if (floor > 12 || floor < 1  || !dir.equalsIgnoreCase("d")) {
            System.out.println("Ignored");
        } else if (list.size() > 0)
            if (dir.equalsIgnoreCase("d") && direction.equals("down") && !list.contains(floor)) {
                list.add(floor);
                Collections.sort(list);
                Collections.reverse(list);
            }
    }

    /**
     * This method is used to make sure that elevator2 takes exactly 2 second at every floor it crosses.
     */
    public static void timerAtFloors() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is used to make sure that elevator2 takes exactly 3 second at every floor it stops.
     */
    public static void timerAtStop() {
        for (int i = 3; i >= 1; i--) {
            System.out.print(" " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @param Floors Accepts an ArrayList and uses it in order to add the total floors in the building that are 1-12.
     * @return The method returns the list after sorting it, containing the total number of floors.
     */
    public static ArrayList<Integer> totalFloors(int Floors) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 1; i <= Floors; i++) {
            list.add(i);
        }
        Collections.sort(list);
        return list;
    }

    /**
     * @param size Accepts the size of the ArrayList which is used to fill the ArrayList with random numbers (1-12) going up or
     *             down.
     * @return The method returns an ArrayList of the size specified containing unique random numbers 1-12.
     */
    public static ArrayList<Integer> fillList(int size) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        ArrayList<Integer> list1 = new ArrayList<>();
        for (int i = 2; i < 13; i++) {
            list.add(i);
        }
        Collections.shuffle(list);
        for (int i = 0; i < size; i++) {
            list1.add(list.get(i));
        }
        return list1;
    }
}
