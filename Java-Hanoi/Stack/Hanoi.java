public class Hanoi {

    private static int MAX;

    private static HanoiTower[] tows;

    public static boolean setPlates(int n) {
        MAX = n;
        Tower.setMAX(n);
        return true;
    }

    private static boolean init(int n) {
        setPlates(n);
        tows = new HanoiTower[3];
        tows[0] = new HanoiTower(false);
        tows[1] = new HanoiTower(true);
        tows[2] = new HanoiTower(true);
        return true;
    }

    public static void play(int n) {
        init(n);
        printTowers();
    }

    private static boolean printTowers() {
        SupportTower[]sp=new SupportTower[3];
        
        sp[0]= new SupportTower(tows[0]);
        sp[1]= new SupportTower(tows[2]);
        sp[2]= new SupportTower(tows[3]);

        sp[0].test();
        sp[1].test();
        sp[2].test();
        return true;
    }

    private static class SupportTower extends Tower {
        private Tower tow;
        
        public SupportTower(HanoiTower t){
            tow = new Tower(true);
            for (int i = 0; i < MAX; i++) {
                try {
                    tow.push(t.pop());
                } catch (EmptyStackException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
