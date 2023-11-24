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
    static class Incrementation
    {
        String incrCouleur;
        int incrPosition;
        boolean incrCaseSuivante;
        boolean incrOccupe;
        boolean increDerniere;
        
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
        Ecran.sautDeLigne();
    }

    static String lancerDeDes()
    {
        String res = "DEPA";
        char roll;
        int cpt = 0;
        Ecran.afficherln("Appuyer sur 'r' pour lancer le dé");
        roll = Clavier.saisirChar();
        while (roll != 'r')
        {
            Ecran.afficherln("Erreur veuiller réessayer");
            roll = Clavier.saisirChar();    
        }
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
        cpt++;
        return res;
    }
    static Case incrementationCaseSuivante(Case c3, Case c4, Case c5, Case c6, int cpt )
    {
        Case c0 = c3;
        if( cpt == 1 )
        {
            c0.couleur = c3.couleur;
            c0.position = c3.position;
            c0.caseSuivante = c3.caseSuivante;
            c0.occupe = c3.occupe;
            c0.derniere = c3.derniere;
        }
        else
        {
            if ( cpt == 2 )
            {
                c0.couleur = c4.couleur;
                c0.position = c4.position;
                c0.caseSuivante = c4.caseSuivante;
                c0.occupe = c4.occupe;
                c0.derniere = c4.derniere;
            }
            else
            {
                if ( cpt == 3 )
                {
                    c0.couleur = c5.couleur;
                    c0.position = c5.position;
                    c0.caseSuivante = c5.caseSuivante;
                    c0.occupe = c5.occupe;
                    c0.derniere = c5.derniere;
                }
                else
                {
                    if( cpt == 4 );
                    {
                        c0.couleur = c6.couleur;
                        c0.position = c6.position;
                        c0.caseSuivante = c6.caseSuivante;
                        c0.occupe = c6.occupe;
                        c0.derniere = c6.derniere;                       }
                }
            }
        }
        return c0;
    }

    static void bolideAvance( Bolide bolide, Case caseActuelle, Case caseSuivante, String des, boolean estCaseSuiv, Case c0)
    {
        String lancer = des;
        String couleurCaseBolide;
        
        Ecran.afficherln( "La couleur tirée est ", des, " !");

        if( ( lancer == caseSuivante.couleur ) && ( estCaseSuiv ) )
        {
            Ecran.afficherln("Le bolide avance !");
            caseActuelle.occupe = false;
            caseSuivante.occupe = true;
            caseSuivante.caseSuivante = false;
            couleurCaseBolide = caseSuivante.couleur;
            caseSuivante.couleur = bolide.modele;
            caseActuelle = caseSuivante;
            caseSuivante = c0;
        }
        else
        {
            Ecran.afficherln("La couleur ne correspond pas, relancer le dé");
        }
    }
    
    public static void main(String [] args)
    {
        String resDe;
        int cpt = 1;
        resDe = lancerDeDes();
        Bolide b1 = new Bolide();
        Case c1, c2, c3, c4, c5, c6 = new Case();
        // On initialise les différentes cases avec dans l'ordre: la couleur la position, si la case est occupée ou non et la dernière case
       c1 = initialisationCase("DEPA", 1, false, true, false);
       c2 = initialisationCase("BLEU", 2, true, false, false);
       c3 = initialisationCase("ROUG", 3, false, false, false);
       c4 = initialisationCase("VERT", 4, false, false, false);
       c5 = initialisationCase("BLEU", 5,  false, false, false);
       c6 = initialisationCase("JAUN", 6, false, false, true);
       // Initialisation du bolide
       b1 = initialisationBolide(1, "XOOX");

       affichagePlateau( c1, c2, c3, c4, c5, c6 );
       /*Ecran.afficherln( estCaseSuivante( c1, c2 ) );*/
       cpt++;
       bolideAvance(b1, c1, c2, resDe, estCaseSuivante(c1, c2), incrementationCaseSuivante( c3, c4, c5, c6, cpt ) );
       affichagePlateau( c1, c2, c3, c4, c5, c6 );
       
    }

}
