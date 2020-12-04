package com.company;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    /**
     * Change the program to allow the players to type full words, or phrases, then move to the
     * correct location based upon their input.
     * The player should be able to tpe commands such as 'Go West', 'run South', or just 'East'
     * and the program will move to the appropriate location if there is one. As at present, an
     * an attempt to move in an invalid direction should print a message and remain in the same place.
     *
     * Single letter commands (N, W, S, E, Q) should still be available.
     */

    private static Map<Integer, Location> locations = new HashMap<>(); //initializing

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        locations.put(0, new Location(0, "You are sitting in front of a computer learning Java"));
        locations.put(1, new Location(1, "You are standing at the end of the road before a small brick building"));
        locations.put(2, new Location(2, "You are at the top of a hill"));
        locations.put(3, new Location(3, "You are inside a building, a well house for a small spring"));
        locations.put(4, new Location(4, "You are in a valley beside a stream"));
        locations.put(5, new Location(5, "You are in the forest"));

        // MAPPING OUT THE EXITS FOR EACH LOCATION
        locations.get(1).addExit("W", 2);
        locations.get(1).addExit("E", 3);
        locations.get(1).addExit("S", 4);
        locations.get(1).addExit("N", 5);

        locations.get(2).addExit("N", 5);

        locations.get(3).addExit("W", 1);

        locations.get(4).addExit("N", 1);
        locations.get(4).addExit("W", 2);

        locations.get(5).addExit("S", 1);
        locations.get(5).addExit("W", 2);

        // EXTRA WORDS WE ARE NOW GOING TO SUPPORT FOR THE CHALLENGE
        Map<String, String> vocabulary = new HashMap<>();
        vocabulary.put("QUIT", "Q");
        vocabulary.put("NORTH", "N");
        vocabulary.put("SOUTH", "S");
        vocabulary.put("WEST", "W");
        vocabulary.put("EAST", "E");



        int loc = 1;
        while (true) {
            System.out.println(locations.get(loc).getDescription());
            if (loc == 0){
                break;
            }

            Map<String, Integer> exits = locations.get(loc).getExits();
            System.out.print("Available exits are: ");
            for (String exit: exits.keySet()) {
                //use a for loop to go through all the keys which indicate the values which are valid for the particular location
                System.out.print(exit + ", ");
            }
            System.out.println();

            String direction = scanner.nextLine().toUpperCase();
            if (direction.length() > 1) { //if greater than 1 then its not a single character command
                //so we do extra processing
                String [] words = direction.split(" "); //so we can get the individual words into our string array
                for (String word: words) {
                    if (vocabulary.containsKey(word)) { //checking if the vocabulary map has the key (word)
                        direction = vocabulary.get(word); //using the get to retrieve the value from the map
                        // based on the key value
                        break;
                    }
                }
            }

            if (exits.containsKey(direction)) {
                loc = exits.get(direction);
            } else {
                System.out.println("You cannot go in that direction");
            }
        }


    }
}














