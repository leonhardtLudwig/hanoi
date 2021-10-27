import java.util.Scanner;

public class Main {

    private static final int N = 4; //number of plates
    private static final int LEAST_MOVES = N * N - 1; //number of moves you could end the game with
    private static int[][] towers; //array containing the three towers

    public static void main(String args[]) {
        initTowers();
        System.out.println("Come on!!! Use numbers between 1 and 3");
        Scanner t = new Scanner(System.in);
        int begin; //tower which you want to move the plate from
        int end; //tower which you are moving the plate on
        int moves = 0;
        do {
            printTowers();
            try {
                System.out.println("First tower");
                begin = t.nextInt() - 1;
                System.out.println("Second tower");
                end = t.nextInt() - 1;
                switchTower(begin, end);
                moves++;
            } catch (InvalidTowerException e) { //exception created by me to simply handle the cases which the players writes the wrong inputs
                System.out.println("Invalid tower input");
            } catch (InvalidSwitchException e) {//exception created by me to simply handle the cases which the players makes an incorrect move
                System.out.println("Invalid switch");
            }

        } while (!hasWon());
        t.close();
        if (moves <= LEAST_MOVES) {
            System.out.println("YOU DID AN EXCELLENT WORK!!!");
            printTowers();
            System.out.println("YOU DID AN EXCELLENT WORK!!!");
        } else {
            System.out.println("YOU WON!!!");
            printTowers();
            System.out.println("YOU WON!!!");
            System.out.println("Moves you did: "+ moves);
            System.out.println("With "+ N+" plates you could finish the game with "+LEAST_MOVES);
            System.out.println("Maybe try to do better next time!");
        }
    }

    private static void initTowers() {
        //with this method you init the array
        //the first column will be filled with numbers from 0 to N-1
        //the other two columns are filled with -1
        towers = new int[N][3];
        for (int i = 0; i < N; i++)
            towers[i][0] = i;
        for (int c = 1; c < 3; c++) {
            for (int r = 0; r < N; r++)
                towers[r][c] = -1;
        }
    }

    private static String toStringNSpazi(int n) {
        //method which returns a string made of a precise (n) amount of blank spaces
        String s = "";
        for (int i = 0; i < n; i++)
            s += " ";
        return s;
    }

    private static String toStringDisco(int r, int c) {
        //method which returns a string made of numbers corrisponding a disk
        //if what you are analizing is a -1 value it will return a string with 
        //n spaces on the left, a '|' character in the middle and n spaces on the right
        String s = "";
        if (towers[r][c] == -1) {
            String spazi = toStringNSpazi(N - 1);
            s = s + spazi + "|" + spazi;

        } else {
            int l = towers[r][c] * 2 + 1;
            int nSpz = N - 1 - towers[r][c];
            String spazi = toStringNSpazi(nSpz);
            s += spazi;
            for (int i = 0; i < l; i++)
                s += towers[r][c];
            s += spazi;
        }
        return s;
    }

    private static String toStringRiga(int r) {
        //return as a string a row of plates
        String s = "";
        for (int c = 0; c < 3; c++) {
            s += toStringDisco(r, c);
            s += " ";
        }
        return s;
    }

    private static String toStringTowers() {
        //returns as a string all the towers
        String s = "";
        for (int i = 0; i < N; i++) {
            System.out.println(toStringRiga(i));
        }
        return s;
    }

    private static void printTowers() {
        //method which print all the towers
        System.out.println(toStringTowers());
    }

    private static void switchTower(int begin, int end) throws InvalidTowerException, InvalidSwitchException {
        //method which switch the plate
        if ((begin < 0 || begin > 2) || (end < 0 || end > 2) || (begin==end))
            throw new InvalidTowerException();
        //if the inputs are out of bounds or are the same it launches an Exception
        int plateBeginId = scanTower(begin);
        int plateEndId = scanTower(end);

        if (towers[plateEndId][end] == -1) {//if the tower is empty it simply put the plate
            towers[plateEndId][end] = towers[plateBeginId][begin];
            towers[plateBeginId][begin] = -1;
        } else if (towers[plateEndId][end] > towers[plateBeginId][begin]) {//if the tower is not empty and the plate we want to put on is not larger than the plate below then it puts the plate
            towers[plateEndId - 1][end] = towers[plateBeginId][begin];
            towers[plateBeginId][begin] = -1;
        } else { //if none of the previews conditions is satisfied it launches an Exception
            throw new InvalidSwitchException();
        }

    }

    private static int scanTower(int towerId) {
        //returns the id of the first plate that you encounter in a tower
        int lvl = 0;
        while ((lvl < N - 1 && towers[lvl][towerId] == -1)) {
            lvl++;
        }
        return lvl;
    }

    private static boolean hasWon() {
        //method which tells you if you won or not (you need to fullfill one of the other two columns)
        return towers[0][1] == 0 || towers[0][2] == 0;
    }

}