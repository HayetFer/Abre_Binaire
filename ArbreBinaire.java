public class ArbreBinaire
{
    public Noeud racine;
    public ArbreBinaire(Noeud r){
        racine = r;
    }
    public void ajout(Noeud a){
        ajout(a,racine);
    }
    public void ajout(Noeud a , Noeud courrant) {
            if (a.getCle() < courrant.getCle())
                if (courrant.GetGauche() != null) {
                    ajout(a, courrant.GetGauche());
                }
                else{
                    courrant.setGauche(a);
                }
            else if (a.getCle() > courrant.getCle()) {
                if (courrant.GetDroite() != null) {
                        ajout(a, courrant.GetDroite());
                    }
                else{
                        courrant.setDroite(a);
                    }
                }
        }
    //}
    public String affichage() {
        return " " + affichage(racine);
    }

    public String affichage(Noeud courrant) {
        if (courrant == null) {
            return "";
        }

        if (courrant.isFeuille()) {
            return (" " +courrant.getCle() + " ");
        } else if (affichage(courrant.GetGauche()).isEmpty()) {
            return courrant.getCle() + " , " + affichage(courrant.GetDroite());
        } else if (affichage(courrant.GetDroite()).isEmpty()) {
            return affichage(courrant.GetGauche()) + " , " + courrant.getCle();
        } else {
            return affichage(courrant.GetGauche()) + " , " + courrant.getCle() + " , " + affichage(courrant.GetDroite());
        }
    }
    public Element recherche(int cle){
        return recherche(cle,racine);
    }
    public Element recherche(int cle, Noeud courrant) {
        Element e1 = new Element(1, -1);
        if (courrant.getCle() == cle) {
            return courrant.getContenu();
        } else if (courrant.getCle() > cle) {
            if (courrant.GetGauche() != null) {
                return recherche(cle, courrant.GetGauche());
            } else {
                return e1;
            }
        } else {
            if (courrant.GetDroite() != null) {
                return recherche(cle, courrant.GetDroite());
            } else {
                return e1;
            }
        }
    }
    public static void main(String[] args) {
        Element e = new Element(10,15);
        Noeud n = new Noeud(e);
        Element e1 = new Element(20,18);
        Noeud n1 = new Noeud(e1);
        Element e2 = new Element(20,16);
        Noeud n2 = new Noeud(e2);
        Element e3 = new Element(20,1);
        Noeud n3 = new Noeud(e3);
        ArbreBinaire test = new ArbreBinaire(n);
        test.ajout(n1);
        test.ajout(n2);
        test.ajout(n3);
        System.out.println(test.affichage());
        System.out.println(test.recherche(19));

    }

}