public class Noeud {
    public Element contenu;
    public Noeud gauche;
    public Noeud droite;
    public Noeud(){
        Element contenu = new Element();
        gauche = null;
        droite = null;
    }
    public Noeud(Element e){
        contenu = e;
        gauche = null;
        droite = null;
    }
    /*
    A REFAIRE public Noeud (Element e, Noeud g, Noeud d){
        contenu = e;
        gauche = g;
        droite = d;
    }*/
    public boolean isFeuille(){
        return(gauche==null && droite ==null);
    }
    public void setGauche(Noeud g){
        gauche = g;
    }
    public void setDroite(Noeud d){
        droite = d;
    }
    public Noeud GetGauche(){
        return gauche;
    }
    public Noeud GetDroite(){
        return droite;
    }
    public Element getContenu(){
        return contenu;
    }
    public int getCle(){
        return contenu.getCle();
    }
    public String toString(){
        return contenu.toString();
    }
}