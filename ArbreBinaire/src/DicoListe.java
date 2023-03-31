public class DicoListe {
    private ListeChainee ListeDuDico;

    public DicoListe() {
        ListeDuDico = new ListeChainee();
    }

    public void ajouter(Element e) {
        ListeDuDico.ajouteTete(e);
    }
    public void ajouterQueue(Element e) {
        ListeDuDico.ajouteQueue(e);
    }
    public Element rechercher(int cle){
        Cellule actuelle = ListeDuDico.getTete();
        return rechercher(cle,actuelle);
    }
    public Element rechercher(int cle, Cellule current) {
        Object contenu = current.getContenu();
        if (contenu instanceof Element && ((Element) contenu).getCle() == cle) {
            return (Element) current.getContenu();
        } else if (current==ListeDuDico.getQueue()) {
            Element erreur = new Element(14,-1);
            return erreur;
        } else {
            current=current.getSuivant();
            return rechercher(cle, current);
        }
    }
    public void supprimer(Element e){
        if((Element) ListeDuDico.getTete().getContenu()==e){
            ListeDuDico.tete = ListeDuDico.getTete().getSuivant();
        }
        else if((Element) ListeDuDico.getQueue().getContenu()==e){
            ListeDuDico.retireQueue();
        }
        else{
            Cellule current = ListeDuDico.getTete();
            while(current.getSuivant()!=null && current.getSuivant().getContenu()!=e){
                current=current.getSuivant();
            }
            if(current.getSuivant()==null){
                throw new IllegalArgumentException("Element non trouvé");
            }
            else{
                current.setSuivant(current.getSuivant());
            }
        }
    }
    public String toString(){
        return ListeDuDico.toString();
    }
    public static void main(String[] args) {
        DicoListe Dico1 = new DicoListe();
        Element e1 = new Element(7, 5);
        Element e2 = new Element(7, 6);
        Element e3 = new Element(7, 7);
        Element e4 = new Element(7, 10);
        Element e5 = new Element(7, 4);
        Dico1.ajouter(e1);
        Dico1.ajouter(e2);
        Dico1.ajouter(e3);
        Dico1.ajouter(e4);
        Dico1.ajouter(e5);
        Dico1.supprimer(e5);
        System.out.println(Dico1);
        System.out.println(Dico1.rechercher(15).toString());
    }
}
