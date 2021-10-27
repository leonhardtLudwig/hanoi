
public class Plate {
    private int val;
    private Plate prev;

    public Plate(boolean base) {
        if (base)
            val = -2;
        else
            val = -1;

    }

    public Plate(int val){
        this.val = val;
    }

    public Plate(int val, Plate prev) {
        this.val = val;
        this.prev = prev;
    };

    public Plate(Plate p) {
        this.val = p.val;
        this.prev = p.prev;
    }

    public boolean setVal(int v) {
        this.val = v;
        return true;
    }

    public int getVal() {
        return val;
    }

    public boolean setPrev(Plate p) {
        this.prev = p;
        return true;
    }

    public Plate getPrev() {
        return prev;
    }
}
