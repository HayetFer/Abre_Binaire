import java.lang.*;
public class ArbreBinaire
{
    public Noeud racine;

    public ArbreBinaire(){
        racine = null;
    }
    public boolean isVide(){
        return racine == null;
    }
    public ArbreBinaire(Element e){
        Noeud r = new Noeud(e);
        racine = r;
    }
    public boolean ajout(Element e){
        if(isVide()){
            racine = new Noeud(e);
        }
        Noeud a = new Noeud(e);
        return ajout(a,racine);
    }
    public boolean ajout(Noeud a , Noeud courrant) {
        if (a.getCle() != courrant.getCle()) {
            if (a.getCle() < courrant.getCle())
                if (courrant.GetGauche() != null) {
                    return ajout(a, courrant.GetGauche());
                } else {
                    courrant.setGauche(a);
                    return true;
                }
            else if (a.getCle() > courrant.getCle()) {
                if (courrant.GetDroite() != null) {
                    return ajout(a, courrant.GetDroite());
                } else {
                    courrant.setDroite(a);
                    return true;
                }
            }
        }
        return false;
    }
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
        if (isVide()){
            return null;
        }
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
        if (isVide()){
            return null;
        }
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

    public boolean suppression(Element e){
            if(isVide()){
                return false;
            }
            if(rechercheNoeud(e.getCle())==null){
                return false;
            }
            if(rechercheNoeud(e.getCle()).isFeuille() && rechercheNoeud(e.getCle())!=racine){
                if(rechercheParent(e.getCle()).getCle()<e.getCle()){
                    rechercheParent(e.getCle()).setDroite(null);
                    return true;
                }
                else{
                    rechercheParent(e.getCle()).setGauche(null);
                    return true;
                }
            } else if (rechercheNoeud(e.getCle()).GetGauche()==null) {
                if(rechercheParent(e.getCle()).getCle()<e.getCle()){
                    rechercheParent(e.getCle()).setDroite(rechercheNoeud(e.getCle()).GetDroite());
                    return true;
                }
                else{
                    rechercheParent(e.getCle()).setGauche(rechercheNoeud(e.getCle()).GetDroite());
                    return true;
                }
            } else if (rechercheNoeud(e.getCle()).GetDroite()==null) {
                if(rechercheParent(e.getCle()).getCle()>e.getCle()){
                    rechercheParent(e.getCle()).setGauche(rechercheNoeud(e.getCle()).GetGauche());
                    return true;
                }
                else{
                    rechercheParent(e.getCle()).setDroite(rechercheNoeud(e.getCle()).GetGauche());
                    return true;
                }
            }
            else{
                Noeud parent = rechercheParent(e.getCle());
                if(parent.getCle()<e.getCle()){
                    parent.setDroite(rechercheMin(parent.GetDroite()));
                    return true;
                }
                else{
                    parent.setGauche(rechercheMin(parent.GetDroite()));
                    return true;
                }
            }

        }



    private Noeud rechercheParent(int cle){
        if(isVide()){
            return null;
        }
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

    public int calculHauteur(){
        return calculHauteur(racine);
    }
    private int calculHauteur(Noeud courrant){
        if(isVide()){
            return 0;
        }
        if(courrant.isFeuille()){
            return 1;
        } else if (courrant.GetGauche()==null) {
            return 1+calculHauteur(courrant.GetDroite());
        }
        else if (courrant.GetDroite()==null) {
            return 1+calculHauteur(courrant.GetGauche());
        }
        else{
            return 1+Math.max(calculHauteur(courrant.GetGauche()), calculHauteur(courrant.GetDroite()));
        }
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
        Element e7 = new Element(20,12);
        Noeud n3 = new Noeud(e3);
        ArbreBinaire test = new ArbreBinaire(e);
        test.ajout(e1);
        test.ajout(e2);
        test.ajout(e3);
        test.ajout(e4);
        test.ajout(e5);
        System.out.println(test.ajout(e6));
        System.out.println(test.ajout(e7));
        System.out.println(test.suppression(32));
        //System.out.println(test.calculHauteur());
        System.out.println(test.affichage());
    }

}