// CARREZ Matthieu et ... - TP H2
public class miniProjetMonzaCarrez
{
    static class Case
    {
        String couleur;
        int position;
        boolean occupe;   
    }
    static class Bolide
    {
        int position;
    }

    static Case initialisationCase(String couleur, int pos, boolean occupe)
    {
        Case c = new Case();
        c.couleur = couleur;
        c.position = pos;
        c.occupe = occupe;

        return c;
    }
    // Entrée: pos1 et pos2, deux variables de type Case
    static boolean estStrictementInferieur( Case pos1, Case pos2 )
    {   // On récupère, du type agrégé Case, la position des deux variables
        return ( pos1.position < pos2.position );
    }

    static void affichagePlateau(Case departCase, Case c2, Case c3, Case c4, Case c5, Case c6)
    {   
        Ecran.afficher(" ");
        for( int i = 0 ; i < 41 ; i++ )
        {
            Ecran.afficher("_");
        }
        Ecran.sautDeLigne();
        Ecran.afficherln("| ", departCase.couleur, " | ", c2.couleur, " | ", c3.couleur, " | ", c4.couleur, " | ", c5.couleur, " | ", c6.couleur, " |");
        Ecran.afficher(" ");
        for( int i = 0 ; i < 41 ; i++ )
        {
            Ecran.afficher("‾");
        }
    }
    public static void main(String [] args)
    {
        Case c1, c2, c3, c4, c5, c6 = new Case();
        // On initialise les différentes cases avec dans l'ordre: la couleur la position et si la case est occupée ou non
       c1 = initialisationCase("DEPA", 1, true);
       c2 = initialisationCase("BLEU", 2, false);
       c3 = initialisationCase("ROUG", 3, false);
       c4 = initialisationCase("VERT", 4, false);
       c5 = initialisationCase("BLEU", 5, false);
       c6 = initialisationCase("JAUN", 6, false);
       estStrictementInferieur( c1, c2 );
       Ecran.afficherln( estStrictementInferieur( c1, c2 ) );
       affichagePlateau( c1, c2, c3, c4, c5, c6 );
    }

}
