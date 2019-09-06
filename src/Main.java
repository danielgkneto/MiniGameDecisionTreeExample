import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static Scanner kb = new Scanner(System.in);
    public static String answer;
    public static boolean isGameOver = false;
    public static Node currentNode;
    public static ArrayList<Node> nodes = new ArrayList<Node>();

    public static void main(String[] args){

        nodes.add(new Node(0, "start", "\nWELCOME TO DANIEL'S TINY ADVENTURE!\n\nYou are in a creepy house!  Where would you like to go? ", -1, 0, false));

        nodes.add(new Node(1, "kitchen", "\nYou are in the kitchen. Do you want to open the refrigerator or open the cabinet? ", 0, 1, false));
        nodes.add(new Node(2, "upstairs", "\nYou are upstairs. Where do you want to go now? ", 0, 1, false));
        nodes.add(new Node(3, "patio", "\nYou are on the patio. Go to the pool or open the shed? ", 0, 1, false));
        nodes.add(new Node(4, "livingroom", "\nDo you want to sit on the couch or turn on the TV? ", 0, 1, false));
        nodes.add(new Node(5, "basement", "\nYou are at the basement. It's too dark to see anything. Keep walking on the dark or turn on the lights? ", 0, 1, false));

        nodes.add(new Node(6, "refrigerator", "\nDo you prefer strange milk or leftovers? ", 1, 2, false));
        nodes.add(new Node(7, "cabinet", "\nInside the cabinet there is a can of beans and a plate? ", 1, 2, false));
        nodes.add(new Node(8, "bedroom", "\nDo you want to lay on the bed or open the drawer? ", 2, 2, false));
        nodes.add(new Node(9, "bathroom", "\nLook yourself in the mirror or use the toilet? ", 2, 2, false));
        nodes.add(new Node(10, "pool", "\nDo you prefer to clean it or swim a little bit? ", 3, 2, false));
        nodes.add(new Node(11, "shed", "\nYou are inside the shed. Do you want to take an ax or a hammer? ", 3, 2, false));
        nodes.add(new Node(12, "tv", "\nChange the channel or stay on the first channel? ", 4, 2, false));
        nodes.add(new Node(13, "couch", "\nDo you prefer to look at the window or eat something? ", 4, 2, false));
        nodes.add(new Node(14, "lights", "\nYou see a big angry dog. Do you turn the laundry on to scare him away or turn off the lights? ", 5, 2, false));
        nodes.add(new Node(15, "dark", "\nYou feel a chest and a closet in your way. Which one do you want to open? ", 5, 2, false));

        nodes.add(new Node(16, "milk", "\nSpoiled milk. You died.", 6, 3, true));
        nodes.add(new Node(17, "leftovers", "\nGood. You are not hungry anymore.", 6, 3, true));
        nodes.add(new Node(18, "beans", "\nYou're full now.", 7, 3, true));
        nodes.add(new Node(19, "plate", "\nThere's a big spider under the plate. It bites you and you die.", 7, 3, true));
        nodes.add(new Node(20, "bed", "\nNice dreams!", 8, 3, true));
        nodes.add(new Node(21, "drawer", "\nIt's a trap. Boom!.", 8, 3, true));
        nodes.add(new Node(22, "mirror", "\nYou break the mirror!", 9, 3, true));
        nodes.add(new Node(23, "toilet", "\nWhat a relief...", 9, 3, true));
        nodes.add(new Node(24, "clean", "\nGood job.", 10, 3, true));
        nodes.add(new Node(25, "swim", "\nA crocodile jumps on you. You died.", 10, 3, true));
        nodes.add(new Node(26, "ax", "\nDo you chop a tree or throw the ax? ", 11, 3, false));
        nodes.add(new Node(27, "hammer", "\nSo, hammer a nail or break the hammer? ", 11, 3, false));
        nodes.add(new Node(28, "change", "\nGreat! A soccer game! Increase or decrease the volume? ", 12, 3, false));
        nodes.add(new Node(29, "stay", "\nNothing but static. You heard something coming from the cabinet door. Open it or ignore it? ", 12, 3, false));
        nodes.add(new Node(30, "window", "\nIt's so desert outside.", 13, 3, true));
        nodes.add(new Node(31, "eat", "\nYou have nothing to eat.", 13, 3, true));
        nodes.add(new Node(32, "laundry", "\nGood job. You scared the dog away", 14, 3, true));
        nodes.add(new Node(33, "lights", "\nYou know dogs don't need lights to smell you, right? You die.", 14, 3, true));
        nodes.add(new Node(34, "chest", "\nA poisonous snake jumps and bite you. You die.", 15, 3, true));
        nodes.add(new Node(35, "closet", "\nIt's a trap. Boom!.", 15, 3, true));

        nodes.add(new Node(36, "chop", "\nThe ax is too old and rusty to chop anything.", 26, 4, true));
        nodes.add(new Node(37, "throw", "\nGood choice. You don't need that rusty ax", 26, 4, true));
        nodes.add(new Node(38, "nail", "\nYou hammer your finger instead!!", 27, 4, true));
        nodes.add(new Node(39, "break", "\nYou are not strong enough for that", 27, 4, true));
        nodes.add(new Node(40, "increase", "\nThe TV explode. You die...", 28, 4, true));
        nodes.add(new Node(41, "decrease", "\nYou heard strange noises comes from the door.", 28, 4, true));
        nodes.add(new Node(42, "open", "\nYou save a little kitten. What a hero!!", 29, 4, true));
        nodes.add(new Node(43, "ignore", "\nAre you too scared to go check it? Coward!", 29, 4, true));


        currentNode = nodes.get(0);

        while (!isGameOver) currentNode = checkAnswer(currentNode);

        System.out.println("\nGAME OVER\n\nThanks for playing!\n");
    }

    private static Node checkAnswer(Node currentNode){
        boolean isValidAnswer = false;
        String options = "";
        if (!currentNode.getIsLeaf()){
            options = "( ";
            for (Node node : nodes){
                if (node.getFatherNode() == currentNode.getId()){
                    options = options.concat(node.getName() + " / ");
                }
            }
            options = options.substring(0, (options.length() - 2)).concat(" )");
        }

        System.out.println(currentNode.getMessage() + options);

        if (currentNode.getIsLeaf()){
            isGameOver = true;
            return null;
        }

        while (!isValidAnswer){
            answer = kb.nextLine();

            for (Node node : nodes){
                if (node.getFatherNode() == currentNode.getId()){
                    if (answer.equals(node.getName())){
                        return node;
                    }
                }
            }
        }
        return currentNode;
    }
}