public class ArbreBinaire
{
    public Noeud racine;
    public ArbreBinaire(Element e){
        Noeud r = new Noeud(e);
        racine = r;
    }
    public void ajout(Element e){
        Noeud a = new Noeud(e);
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
    public Noeud rechercheNoeud(int cle){
        return rechercheNoeud(cle, racine);
    }
    public Noeud rechercheNoeud(int cle, Noeud courrant){
        if (courrant.getCle() == cle) {
            return courrant;
        } else if (courrant.getCle() > cle) {
            if (courrant.GetGauche() != null) {
                return rechercheNoeud(cle, courrant.GetGauche());
            } else {
                return null;
            }
        } else {
            if (courrant.GetDroite() != null) {
                return rechercheNoeud(cle, courrant.GetDroite());
            } else {
                return null;
            }
        }
    }

    public void suppression(int cle){
        //A CHANGER
        if(rechercheNoeud(cle).isFeuille() && rechercheNoeud(cle)!=racine){
            if(rechercheParent(cle).getCle()<cle){
                rechercheParent(cle).setDroite(null);
            }
            else{
                rechercheParent(cle).setGauche(null);
            }
        } else if (rechercheNoeud(cle).GetGauche()==null) {
            if(rechercheParent(cle).getCle()<cle){
                rechercheParent(cle).setDroite(rechercheNoeud(cle).GetDroite());
            }
            else{
                rechercheParent(cle).setGauche(rechercheNoeud(cle).GetDroite());
            }
        } else if (rechercheNoeud(cle).GetDroite()==null) {
            if(rechercheParent(cle).getCle()>cle){
                rechercheParent(cle).setGauche(rechercheNoeud(cle).GetGauche());
            }
            else{
                rechercheParent(cle).setDroite(rechercheNoeud(cle).GetGauche());
            }
        }
        else{
            Noeud parent = rechercheParent(cle);
            if(parent.getCle()<cle){
                parent.setDroite(rechercheMin(parent.GetDroite()));
            }
            else{
                parent.setGauche(rechercheMin(parent.GetDroite()));
            }
        }
    }
    private void suppression(int cle, Noeud courrant){

    }
    private Noeud rechercheParent(int cle){
        return rechercheParent(cle,racine);
    }
    private Noeud rechercheParent(int cle,Noeud courrant){

        if (courrant.GetGauche()!=null && courrant.GetGauche().getCle()== cle) {
            return courrant;
        }
        else if (courrant.GetDroite()!=null && courrant.GetDroite().getCle()== cle) {
            return courrant; }

        else if (courrant.getCle() > cle) {
            if (courrant.GetGauche() != null) {
                return rechercheParent(cle, courrant.GetGauche());
            } else {
                return null;
            }
        } else {
            if (courrant.GetDroite() != null) {
                return rechercheParent(cle, courrant.GetDroite());
            } else {
                return null;
            }
        }
    }

    private Noeud rechercheMin(Noeud n){
        Noeud min = n;
        while(min.GetGauche()!=null){
            min=min.GetGauche();
        }
        return min;
    }
    public static void main(String[] args) {
        Element e = new Element(10,15);
        Noeud n = new Noeud(e);
        Element e1 = new Element(20,18);
        Noeud n1 = new Noeud(e1);
        Element e2 = new Element(20,13);
        Noeud n2 = new Noeud(e2);
        Element e3 = new Element(20,2);
        Element e4 = new Element(20,17);
        Element e5 = new Element(20,20);
        Element e6 = new Element(20,12);
        Noeud n3 = new Noeud(e3);
        ArbreBinaire test = new ArbreBinaire(e);
        test.ajout(e1);
        test.ajout(e2);
        test.ajout(e3);
        test.ajout(e4);
        test.ajout(e5);
        test.ajout(e6);
        test.suppression(13);
        System.out.println(test.affichage());
        //System.out.println(test.rechercheMin(n));
        //System.out.println(test.recherche(19));

    }

}
