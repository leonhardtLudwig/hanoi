import java.util.Scanner;

public class Main {

    private static final int N = 4;
    private static int[][] towers;

    public static void main(String args[]) {
        initTowers();
        System.out.println("Come on!!!");
        Scanner t = new Scanner(System.in);
        int begin;
        int end;
        do{
            printTowers();
            try {
                System.out.println("First tower");
                begin = t.nextInt();
                System.out.println("Second tower");
                end = t.nextInt();
                switchTower(begin, end);
                /*
                 * switchTower(0, 2); printTowers(); switchTower(0, 1); printTowers();
                 * switchTower(2, 1); printTowers(); switchTower(0, 2); printTowers();
                 * switchTower(1, 0); printTowers(); switchTower(1, 2); printTowers();
                 * switchTower(0, 2); printTowers(); switchTower(0, 1); printTowers();
                 * switchTower(2, 1); printTowers(); switchTower(2, 0); printTowers();
                 * switchTower(1, 0); printTowers(); switchTower(2, 1); printTowers();
                 * switchTower(0, 2); printTowers(); switchTower(0, 1); printTowers();
                 * switchTower(2, 1); printTowers();
                 */
            } catch (InvalidTowerException e) {
                System.out.println("Invalid tower input");
            } catch (InvalidSwitchException e) {
                System.out.println("Invalid switch");
            }
            
        }while(true);
        
    }

    private static void initTowers() {
        towers = new int[N][3];
        for (int i = 0; i < N; i++)
            towers[i][0] = i;
        for (int c = 1; c < 3; c++) {
            for (int r = 0; r < N; r++)
                towers[r][c] = -1;
        }
    }

    private static String toStringNSpazi(int n) {
        String s = "";
        for (int i = 0; i < n; i++)
            s += " ";
        return s;
    }

    private static String toStringDisco(int r, int c) {
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
        String s = "";
        for (int c = 0; c < 3; c++) {
            s += toStringDisco(r, c);
            s += " ";
        }
        return s;
    }

    private static String toStringTowers() {
        String s = "";
        for (int i = 0; i < N; i++) {
            System.out.println(toStringRiga(i));
        }
        return s;
    }

    private static void printTowers() {
        System.out.println(toStringTowers());
    }

    private static void switchTower(int begin, int end) throws InvalidTowerException, InvalidSwitchException {
        if ((begin < 0 || begin > 2) || (end < 0 || end > 2))
            throw new InvalidTowerException();
        int plateBeginId = scanTower(begin);
        int plateEndId = scanTower(end);
        /*
         * if(towers[plateEndId][end]<towers[plateBeginId][begin]){
         * towers[plateEndId][end] = towers[plateBeginId][begin];
         * towers[plateBeginId][begin] = -1; }else{ throw new InvalidSwitchException();
         * }
         */
        if (towers[plateBeginId][begin] < towers[plateEndId][end] || towers[plateBeginId][begin] > -1) {
            towers[plateEndId - (towers[plateEndId][end] != -1 ? 1 : 0)][end] = towers[plateBeginId][begin];
            towers[plateBeginId][begin] = -1;
        } else {
            throw new InvalidSwitchException();
        }

    }

    private static int scanTower(int towerId) {
        int lvl = 0;
        for (; lvl < N && towers[lvl][towerId] == -1; lvl++)
            ;
        if (lvl == N)
            lvl--;
        return lvl;
    }

}