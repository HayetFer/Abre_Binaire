import java.util.*;

public class Test implements Iterator<Integer> {
    private List<Integer> cle = new ArrayList<>();
    public Test(int n){
        for(int i=0;i<n;i++){
            cle.add(i);
        }
        Collections.shuffle(cle);
    }
    public static ArbreBinaire unMillion(){
        Element e = new Element((int)Math.random());
        ArbreBinaire mil = new ArbreBinaire(e);
        Test random = new Test (1000000);
        while (random.hasNext()) {
            mil.ajout(new Element(random.next()));
        }

        return mil;
    }
    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return cle.remove(0);
    }

    @Override
    public boolean hasNext() {
        return !cle.isEmpty();
    }
    public static void main(String[] args) {
        unMillion();
    }
}
