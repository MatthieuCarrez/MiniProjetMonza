// CARREZ Matthieu et ... - TP H2
public class miniProjetMonzaCarrez
{
    static class Case
    {
        String couleur;
        int position;
        boolean caseSuivante;
        boolean occupe;
        boolean derniere;
    }
    static class Bolide
    {
        int position;
        String modele;
    }

    static Case initialisationCase(String couleur,int position, boolean cSuiv, boolean occupe, boolean derniere)
    {
        Case c = new Case();
        c.couleur = couleur;
        c.position = position;
        c.caseSuivante = cSuiv;
        c.occupe = occupe;
        c.derniere = derniere;


        return c;
    }
    static Bolide initialisationBolide(int pos, String mod)
    {
        Bolide bolide = new Bolide();
        bolide.position = pos;
        bolide.modele = mod;

        return bolide;
    }
    // Entrée: pos1 et pos2, deux variables de type Case
    static boolean estCaseSuivante( Case cOccupe, Case cSuiv )
    {   // Retourne true si le bolide se trouve sur cOccupe et que cSuiv.caseSuivante est vrai
        boolean estSuiv = false;
        if( cOccupe.occupe )
        {
            if( cSuiv.caseSuivante )
            {
                estSuiv = true;
            }
        }
        return estSuiv;
    }
    
    static void affichagePlateauDepart(Case departCase, Case c2, Case c3, Case c4, Case c5, Case c6)
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

    static void affichagePlateauJeu(Case departCase, Case c2, Case c3, Case c4, Case c5, Case c6)
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

    static String lancerDeDes()
    {
        String res = "DEPA";
        int tirage = (int) ( Math.random() * 4 ) + 1;
        switch( tirage )
        {
            case 1:
                res = "BLEU";
                break;
            case 2:
                res = "JAUN";
                break;
            case 3:
                res = "ROUG";
                break;
            case 4:
                res = "VERT";
                break;
        }
        return res;
    }
    
    public static void main(String [] args)
    {
        String couleurCaseBolide;
        Bolide b1 = new Bolide();
        Case c1, c2, c3, c4, c5, c6 = new Case();
        // On initialise les différentes cases avec dans l'ordre: la couleur la position, si la case est occupée ou non et la dernière case
       c1 = initialisationCase("DEPA", 1, false, true, false);
       c2 = initialisationCase("BLEU", 4, true, false, false);
       c3 = initialisationCase("ROUG", 6, false, false, false);
       c4 = initialisationCase("VERT", 8, false, false, false);
       c5 = initialisationCase("BLEU", 10,  false, false, false);
       c6 = initialisationCase("JAUN", 6, false, false, true);
       // Initialisation du bolide
       b1 = initialisationBolide(1, "XOOX");

       affichagePlateauDepart( c1, c2, c3, c4, c5, c6 );
       Ecran.afficherln( lancerDeDes() );
       Ecran.afficherln( estCaseSuivante( c1, c2 ) );
       // Impossible tant que je ne trouve pas de solutions sur comment trouver la prochaine case :/
       while ( c6.occupe != false ) 
       {
        if( lancerDeDes() != c2.couleur )
        {
            if( estCaseSuivante( c1, c2 ) )
            {
                c1.occupe = false;
                c2.occupe = true;
                c2.caseSuivante = false;
                couleurCaseBolide = c2.couleur;
                c2.couleur = b1.modele;
            }
       affichagePlateauJeu( c1, c2, c3, c4, c5, c6 ) ;

       Ecran.afficherln( lancerDeDes() );
       Ecran.afficherln( estCaseSuivante( c1, c2 ) );
       if( estCaseSuivante( c1, c2 ) )
       {
            c1.occupe = false;
            c2.occupe = true;
            c2.caseSuivante = false;
            couleurCaseBolide = c2.couleur;
            c2.couleur = b1.modele;
       }
       affichagePlateauJeu( c1, c2, c3, c4, c5, c6 ) ;
    }

}
