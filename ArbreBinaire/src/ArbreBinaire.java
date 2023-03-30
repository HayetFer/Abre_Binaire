import java.lang.*;

public class ArbreBinaire {
    public Noeud racine;

    public ArbreBinaire() {
        racine = null;
    }

    public boolean isVide() {
        return racine == null;
    }

    public ArbreBinaire(Element e) {
        Noeud r = new Noeud(e);
        racine = r;
    }

    public boolean ajout(Element e) {
        if (isVide()) {
            racine = new Noeud(e);
        }
        Noeud a = new Noeud(e);
        return ajout(a, racine);
    }

    public boolean ajout(Noeud a, Noeud courrant) {
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
            return (" " + courrant.getCle() + " ");
        } else if (affichage(courrant.GetGauche()).isEmpty()) {
            return courrant.getCle() + " , " + affichage(courrant.GetDroite());
        } else if (affichage(courrant.GetDroite()).isEmpty()) {
            return affichage(courrant.GetGauche()) + " , " + courrant.getCle();
        } else {
            return affichage(courrant.GetGauche()) + " , " + courrant.getCle() + " , "
                    + affichage(courrant.GetDroite());
        }
    }

    public Element recherche(int cle) {
        if (isVide()) {
            return null;
        }
        return recherche(cle, racine);
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

    public Noeud rechercheNoeud(int cle) {
        if (isVide()) {
            return null;
        }
        return rechercheNoeud(cle, racine);
    }

    public Noeud rechercheNoeud(int cle, Noeud courrant) {
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

    public Noeud rechercheNoeudAvecElement(Element e) {
        if (isVide()) {
            return null;
        }
        return rechercheNoeudAvecElement(e, racine);
    }

    public Noeud rechercheNoeudAvecElement(Element e, Noeud courrant) {
        if (courrant.getContenu() == e) {
            return courrant;
        } else if (courrant.getCle() > e.getCle()) {
            if (courrant.GetGauche() != null) {
                return rechercheNoeudAvecElement(e, courrant.GetGauche());
            } else {
                return null;
            }
        } else {
            if (courrant.GetDroite() != null) {
                return rechercheNoeudAvecElement(e, courrant.GetDroite());
            } else {
                return null;
            }
        }
    }

    public boolean suppression(Element e) {
        Noeud aSupr = rechercheNoeudAvecElement(e);

        if (isVide()) {
            return false;
        }
        try {
            if (aSupr == racine) {
                if (racine.isFeuille()) {
                    racine = null;
                    return true;
                } else if (racine.GetGauche() == null) {
                    racine = racine.GetDroite();
                    return true;
                } else if (racine.GetDroite() == null) {
                    racine = racine.GetGauche();
                    return true;
                } else {
                    Noeud successeur = rechercheMin(racine.GetDroite());
                    suppression(successeur.getContenu());
                    successeur.setGauche(racine.GetGauche());
                    successeur.setDroite(racine.GetDroite());
                    racine = successeur;
                    return true;
                }
            }
            int parentCle = rechercheParent(aSupr.getCle()).getCle();
            Noeud parent = rechercheParent(aSupr.getCle());

            if (aSupr.isFeuille() && aSupr != racine) {
                if (parentCle < aSupr.getCle()) {
                    parent.setDroite(null);
                    return true;
                } else {
                    parent.setGauche(null);
                    return true;
                }
            } else if (rechercheNoeud(aSupr.getCle()).GetGauche() == null) {
                if (parentCle < aSupr.getCle()) {
                    parent.setDroite(aSupr.GetDroite());
                    return true;
                } else {
                    parent.setGauche(aSupr.GetDroite());
                    return true;
                }
            } else if (rechercheNoeud(aSupr.getCle()).GetDroite() == null) {
                if (parentCle > aSupr.getCle()) {
                    parent.setGauche(aSupr.GetGauche());
                    return true;
                } else {
                    parent.setDroite(aSupr.GetGauche());
                    return true;
                }
            } else {

                if (aSupr.getCle() < parent.getCle()) {

                    Noeud min = rechercheMin(aSupr.GetDroite());

                    if (aSupr.GetDroite() == min) {
                        min.setGauche(aSupr.GetGauche());
                        parent.setGauche(min);
                        return true;
                    } else {
                        System.out.println(min + " " + aSupr.GetGauche() + aSupr.GetDroite());
                        rechercheParent(min.getCle()).setGauche(null);
                        min.setGauche(aSupr.GetGauche());
                        min.setDroite(aSupr.GetDroite());
                        parent.setGauche(min);
                        return true;
                    }
                } else {
                    Noeud min = rechercheMin(aSupr.GetDroite());

                    if (aSupr.GetDroite() == min) {
                        min.setGauche(aSupr.GetGauche());
                        parent.setDroite(min);
                        return true;
                    } else {
                        rechercheParent(min.getCle()).setGauche(null);
                        min.setGauche(aSupr.GetGauche());
                        min.setDroite(aSupr.GetDroite());
                        parent.setDroite(min);
                        return true;
                    }
                }
            }

        } catch (

        NullPointerException exception) {
            return false;
        }

    }

    private Noeud rechercheParent(int cle) {
        if (isVide()) {
            return null;
        }
        if (cle == racine.getCle()) {
            return null;
        }
        return rechercheParent(cle, racine);
    }

    private Noeud rechercheParent(int cle, Noeud courrant) {

        if (courrant.GetGauche() != null && courrant.GetGauche().getCle() == cle) {
            return courrant;
        } else if (courrant.GetDroite() != null && courrant.GetDroite().getCle() == cle) {
            return courrant;
        }

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

    private Noeud rechercheMin(Noeud n) {
        Noeud min = n;
        while (min.GetGauche() != null) {
            min = min.GetGauche();
        }
        return min;
    }

    public int calculHauteur() {
        return calculHauteur(racine);
    }

    private int calculHauteur(Noeud courrant) {
        if (isVide()) {
            return 0;
        }
        if (courrant.isFeuille()) {
            return 1;
        } else if (courrant.GetGauche() == null) {
            return 1 + calculHauteur(courrant.GetDroite());
        } else if (courrant.GetDroite() == null) {
            return 1 + calculHauteur(courrant.GetGauche());
        } else {
            return 1 + Math.max(calculHauteur(courrant.GetGauche()), calculHauteur(courrant.GetDroite()));
        }
    }

    public static void main(String[] args) {
        Element e = new Element(10, 20);
        Noeud n = new Noeud(e);
        Element e1 = new Element(20, 13);
        Noeud n1 = new Noeud(e1);
        Element e2 = new Element(20, 25);
        Noeud n2 = new Noeud(e2);
        Element e3 = new Element(20, 10);
        Element e4 = new Element(20, 15);
        Element e5 = new Element(20, 9);
        Element e6 = new Element(20, 11);
        // Element e7 = new Element(20, 15);
        Element e8 = new Element(20, 14);
        Element e9 = new Element(20, 16);
        Element e10 = new Element(20, 21);
        Element e11 = new Element(20, 30);
        Noeud n3 = new Noeud(e3);
        ArbreBinaire test = new ArbreBinaire(e);
        test.ajout(e1);
        test.ajout(e2);
        test.ajout(e3);
        test.ajout(e4);
        test.ajout(e5);
        test.ajout(e6);
        // test.ajout(e7);
        test.ajout(e8);
        test.ajout(e9);
        test.ajout(e10);
        test.ajout(e11);

        // System.out.println(test.ajout(e6));
        // System.out.println(test.ajout(e7));
        System.out.println(test.affichage());
        // System.out.println(test.suppression(e3));
        System.out.println(test.suppression(e3));
        // System.out.println(test.calculHauteur());
        System.out.println(test.affichage());
        // System.out.println(test.rechercheNoeudAvecElement(e));
    }

}
