import java.util.LinkedList;

public class testListeChaine
{
    static class Case
    {   
        String couleur; // La couleur de la case
        boolean estOccupe, estDerniere; // estOccupé permet de savoir où est le bolide ; estDerniere n'est VRAi que pour la dernière case, permettant d'arrêter le jeu
        Case suivante; // Permet de créer une liste chaînée
    }///////////////////////////////////
    // Type agrégé des lignes du plateau
    static class Ligne
    {   // Me permet de de savoir quelle case est la suivante
        Case c1;
    }
    static Ligne creerLigne1()
    {
        Ligne l = new Ligne();
        l.c1 = initCase( "DEPA", true, false );
        l.c1.suivante = initCase("BLEU", false, false);
        l.c1.suivante.suivante = initCase("ROUG", false, false);
        l.c1.suivante.suivante.suivante = initCase("VERT", false, false);
        l.c1.suivante.suivante.suivante.suivante = initCase("BLEU", false, false);
        l.c1.suivante.suivante.suivante.suivante.suivante = initCase("JAUN", false, true);
        return l;
    }
    static Case initCase( String couleur, boolean estO, boolean estD )
    {
        Case c = new Case();
        c.couleur = couleur;
        c.estOccupe = estO;
        c.estDerniere = estD;
        return c;

    }
    static Ligne initialisationListe()
    {
        LinkedList<Ligne>
    }
    public static void main(String[] args)
    {
        

    }
}
