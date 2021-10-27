public class Tower implements Stack {

    private Plate top;
    private int size;
    private final int MAX;

    public Tower(int n){
        MAX = n;
        top = new Plate(true);
        for (int i = 0; i < MAX; i++) {
            push(new Plate(false));
        }
    }

    public void test(){
        for (int i = 0; i < MAX; i++) {
            try {
                System.out.println(pop().getVal());
            } catch (EmptyStackException e) {
                e.printStackTrace();
            }
        }
    }



    public boolean isEmpty() {
        return size==0;
    }

    public boolean push(Plate p) {
        //if(size==MAX)throw new FullStackException();
        Plate newPlate = p;
        newPlate.setPrev(top);
        top = newPlate;
        size++;
        return true;
    }

    public Plate pop() throws EmptyStackException {
        if(size==0)throw new EmptyStackException();
        Plate p = top;
        top = top.getPrev();
        size--;
        return p;
    }

    public Plate top() throws EmptyStackException {
        if(size==0)throw new EmptyStackException();
        return top;
    }

    public int size() {
        return size;
    }

    public Plate search(){//this method is useless in this project
        return null;
    }
}
