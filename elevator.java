package Assignments;
/**
 * @author Harnoor Singh
 * Program name: elevator.java
 * Due Date: Sept 18, 2017
 * Purpose of the program:
 * The program behaves like a real elevator with random values. It makes the elevator go up and then down. It makes
 * 8 stops while going up and 5 stops while going down. Total floors in the building are 12.
 * The elevator stops for 2 seconds at every floor it crosses, on the other hand for 3 seconds it stops.
 *
 * Solution for problem and algorithms used:
 * The solution for the program was mainly dividing the tasks into smaller fragments so that all of them
 * can be handled easily and in a best possible way. At the beginning of the program in the main method
 * random values are filled into up_List and down_List (Up ArrayList and Down Array List) from the total possible
 * floors that are 1-12. Total number of floors are stored in separate ArrayList as well. The up_List is sorted in
 * increasing order and the down_List is sorted in decreasing order. The elevator's movement up and down is handled
 * separately. The runLiftUp method is called to for the elevator's journey in up direction and code for elevator's
 * movement is handled in main method. Separate methods are used for timers at very floor and at every stop.
 *
 *
 * Data Structures used in this solution:
 * ArrayLists to store the floor numbers for both going up and down and also to store total number of floors. up_List
 * stores 8 random values while down_List stores 5 random values ranging from 1-12.
 *
 * Description of how to use the program and expected input/output:
 * In order to use this program, the user will need to run the program and watch it going up and then down. When the
 * elevator reaches back down, user is prompted whether he or she wants to run the elevator again; then he or she must
 * enter yes or no.
 *
 *
 * Purpose of this class:
 * There is only one class used in this program and this class is the main method class which contains all the
 * methods which are either called in the main method or within a method itself.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class elevator {
    private static ArrayList<Integer> listFloors;
    private static String direction;

    /**
     * The beginning of the program and main method where both up and down ArrayLists are filled with random numbers
     * by calling the fillList method. Then the runLiftUp method is called to make the elevator go up and then the
     * algorithm for the lift to go down is written in the main method itself. Two global variable are used in main
     * method that are the listFloors and the direction. The listFloors ArrayList contains the total number of floors
     * in the building and direction is used to keep track
     * elevator's direction up or down.
     *
     * @param args
     */
    public static void main(String[] args) {
        listFloors = totalFloors(12);
        System.out.println(listFloors.toString());
        while (true) {
            ArrayList<Integer> up_List = fillList(8, "up");
            ArrayList<Integer> down_List = fillList(5, "down");
            Collections.sort(up_List);
            Collections.sort(down_List);
            Collections.reverse(down_List);

            System.out.println("up_ArrayList: " + up_List.toString());
            System.out.println("down_ArrayList: " + down_List.toString());

            int stopFloor = Collections.max(up_List);
            runLiftUp(up_List);
            System.out.println("down_ArrayList: " + down_List.toString());
            if (stopFloor < down_List.get(0)) {
                direction = "up";
                System.out.println("Starting at floor: " + stopFloor);
                while (down_List.get(0) > stopFloor) {
                    System.out.print("\tGoing up: ");
                    timerAtFloors();
                    System.out.println("now at floor " + ++stopFloor);
                }
                down_List.remove(0);
                System.out.println("Stopping at floor: " + stopFloor + " for 3 sec");
                timerAtStop();
            } else if (stopFloor == down_List.get(0)) {
                down_List.remove(0);
//                System.out.println(down_List.toString());//Uncomment this if you want to keep track of the arrayList
            }
            int floor = stopFloor - 1;
            while (down_List.size() > 0) {
                direction = "down";
                System.out.println("Starting at floor: " + stopFloor);
                stopFloor = down_List.get(0);
                while (floor >= stopFloor) {
                    System.out.print("\tGoing down: ");
                    timerAtFloors();
                    System.out.println("now at floor " + floor);
                    floor -= 1;
                }
                stopFloor = down_List.remove(0);
                System.out.print("Stopping at floor: " + stopFloor + " for 3 sec");
                timerAtStop();
                System.out.println();
//                System.out.println(down_List.toString());//Uncomment this if you want to keep track of the arrayList
            }
            if (down_List.size() == 0) {
                System.out.println("Do you want to run the elevator again? “Y or y” continue. “N or n” stop.");
                Scanner input = new Scanner(System.in);
                String yesOrNo = input.next();
                if (yesOrNo.equalsIgnoreCase("y"))
                    continue;
                else break;
            }
        }
    }

    /**
     * @param up This method accepts a ArrayList as a parameter and it uses it in order to loop through it so that the elevator
     *           can stop at the every floor number it contains and just cross the floor for the floor number it doesn't.
     *           It sets global variable direction value to up as the elevator is going up in this method.
     */
    public static void runLiftUp(ArrayList<Integer> up) {
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

            System.out.print("Stopping at floor " + stopFloor + " for 3 sec");
            timerAtStop();
            System.out.println();
//            System.out.println(up.toString());Uncomment this if you want to keep track of the arrayList
        }
    }

    /**
     * This method is used to make sure that elevator takes exactly 2 second at every floor it crosses.
     */
    public static void timerAtFloors() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is used to make sure that elevator takes exactly 3 second at every floor it stops. This method is
     * called when elevator stops.
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
     * @param size      Accepts the size of the ArrayList which is used to fill the ArrayList with random numbers (2-12)
     *                  going up or down (12-1).
     * @param direction Accepts the direction of the elevator for the ArrayList associated with it, so that it can be
     *                  filled conveniently.
     * @return The method returns an ArrayList of the size specified containing unique random numbers.
     *
     * This method handled the ArrayList going up and down in a different way. If the arrayList is going up then the
     * range is 2-12 otherwise it is from 1-12.
     */
    public static ArrayList<Integer> fillList(int size, String direction) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        ArrayList<Integer> list1 = new ArrayList<>();
        if (direction.equalsIgnoreCase("up"))
            for (int i = 2; i < 13; i++) {
                list.add(i);
            }
        else if (direction.equalsIgnoreCase("down"))
            for (int i = 1; i < 13; i++) {
                list.add(i);
            }
        Collections.shuffle(list);
        for (int i = 0; i < size; i++) {
            list1.add(list.get(i));
        }
        return list1;
    }
}
