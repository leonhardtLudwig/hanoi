public class Tower implements Stack {

    private Plate top;
    private int size;
    private static int MAX;

    Tower(){}

    public static boolean setMAX(int n){
        MAX = n;
        return true;
    }

    public Tower(boolean empty) {
        
        top = new Plate(true);
        if (empty) {
            for (int i = 0; i < MAX; i++) {
                push(new Plate(false));
            }
        }else{
            for (int i = 0; i < MAX; i++) {
                push(new Plate(i));
            } 
        }
    }

    public void test() {
        for (int i = 0; i < MAX; i++) {
            try {
                System.out.println(pop().getVal());
            } catch (EmptyStackException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean push(Plate p) {
        Plate newPlate = p;
        newPlate.setPrev(top);
        top = newPlate;
        size++;
        return true;
    }

    protected void increaseSize(){
        size++;
    }

    protected Plate getTop(){
        return top;
    }

    protected void setTop(Plate p){
        top = p;
    }

    public Plate pop() throws EmptyStackException {
        if (size == 0)
            throw new EmptyStackException();
        Plate p = top;
        top = top.getPrev();
        size--;
        return p;
    }

    public Plate top() throws EmptyStackException {
        if (size == 0)
            throw new EmptyStackException();
        return top;
    }

    public int size() {
        return size;
    }

    public Plate search() {// this method is useless in this project
        return null;
    }
}
