public class HanoiTower extends Tower {
    //private Tower t;
    HanoiTower t;
    public HanoiTower(boolean empty){
        t = (HanoiTower) new Tower(empty);
    }

    /*public boolean push(Plate p) {
        Plate newPlate = p;
        newPlate.setPrev(t.getTop());
        t.setTop(newPlate);
        t.increaseSize();
        return true;
    }*/
}
