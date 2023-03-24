public class Element {
    private int cle;
    private float contenu;

    public Element(){
        contenu = 0;
        cle = 0;
    }
    public Element(float ent, int key){
        cle = key;
        contenu = ent;
    }
    public Element(int key){
        cle = key;
        contenu = (float) Math.random();
    }
    public int getCle(){
        return cle;
    }
    public float getContenu(){
        return contenu;
    }
    public String toString(){
        return "Clé : " + cle + " | Contenu : " + contenu;
    }
    public void setContenu(int cont){
        contenu=cont;
    }

    public void setCle(int cle) {
        this.cle = cle;
    }
    public static void main(String[] args){
        Element e1 = new Element(1,2);
        System.out.println(e1.toString());
    }
}