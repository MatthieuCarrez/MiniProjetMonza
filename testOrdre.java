public class testOrdre
{
    static class Bolide
    {
        String modele;
    }
    static class Case
    {
        int position;
        String couleur;
        boolean estSuivante, estDerniere, estOccupe;
    }

    static Case initCase( int position, String couleur, boolean estOccupe, boolean estSuiv, boolean estDerniere )
    {
        Case cInit = new Case();
        cInit.position = position;
        cInit.couleur = couleur;
        cInit.estSuivante = estSuiv;
        cInit.estDerniere = estDerniere;
        cInit.estOccupe = estOccupe;
        return cInit;
    }
    static Bolide initBolide( String modele )
    {
        Bolide bInit = new Bolide();
        bInit.modele = modele;
        return bInit;
    }

    static void affichagePlateau(Bolide bolide, Case c0, Case c1, Case c2, Case c3, Case c4, Case c5)
    {   
        Ecran.afficher(" ");
        for( int i = 0 ; i < 41 ; i++ )
        {
            Ecran.afficher("_");
        }
        Ecran.sautDeLigne();
        Ecran.afficherln("| ", c0.couleur, " | ", c1.couleur, " | ", c2.couleur, " | ", c3.couleur, " | ", c4.couleur, " | ", c5.couleur, " |");
        Ecran.afficher(" ");
        for( int i = 0 ; i < 41 ; i++ )
        {
            Ecran.afficher("-");
        }
        Ecran.sautDeLigne();
    }
    //
    static String couleurTiree()
    {
        char roll = 'r';
        Ecran.afficherln("Une couleur va être tirée au sort");
        Ecran.afficherln("Appuyer sur 'r' pour lancer le dé");
        do
        {
            roll = Clavier.saisirChar();
            if ( roll != 'r')
            {
                Ecran.afficherln( "Erreur de saisie, recommencer !" );
            }
        }while( roll != 'r' );

        int de = /*(int) ( Math.random() * 4 ) +*/ 1;
        String couleurTiree = "DEFAULT";

        switch ( de )
        {
            case 1:
                couleurTiree = "BLEU";
                break;
            case 2:
                couleurTiree = "JAUN";
                break;
            case 3:
                couleurTiree = "VERT";
                break;
            case 4:
                couleurTiree = "ROUG";
            default:
                break;
        }
        Ecran.afficherln( "La couleur tirée est ", couleurTiree );
        return couleurTiree;
    }
    static String couleurTireeD()
    {
        char roll = 'r';
        Ecran.afficherln("Une couleur va être tirée au sort");
        Ecran.afficherln("Appuyer sur 'r' pour lancer le dé");
        do
        {
            roll = Clavier.saisirChar();
            if ( roll != 'r')
            {
                Ecran.afficherln( "Erreur de saisie, recommencer !" );
            }
        }while( roll != 'r' );

        int de = /*(int) ( Math.random() * 4 ) +*/ 4;
        String couleurTiree = "DEFAULT";

        switch ( de )
        {
            case 1:
                couleurTiree = "BLEU";
                break;
            case 2:
                couleurTiree = "JAUN";
                break;
            case 3:
                couleurTiree = "VERT";
                break;
            case 4:
                couleurTiree = "ROUG";
            default:
                break;
        }
        Ecran.afficherln( "La couleur tirée est ", couleurTiree );
        return couleurTiree;
    }
    

    static boolean peutAvancer( Case cActu, Case cSuiv )
    {
        return (cActu.estOccupe == cSuiv.estSuivante);
    }

    static void bolideAvance( boolean resPeutAvancer, String resCouleurTiree, Bolide bModele, Case cActuelle, Case cSuivante, Case cNewSuivante, Case cPrecedente )
    {
        
        String couleurCaseActuelle, couleurCasePrecedente;
        if( resCouleurTiree == cSuivante.couleur )
        {
            if( resPeutAvancer )
            {
                Ecran.afficherln("Le bolide avance ! ");
                cActuelle.estOccupe = false;
                cSuivante.estOccupe = true;
                cSuivante.estSuivante = false;
                cNewSuivante.estSuivante = true;


                couleurCasePrecedente = cActuelle.couleur;
                couleurCaseActuelle = cSuivante.couleur;
                cPrecedente = cActuelle;
                cActuelle = cSuivante;
                cActuelle.couleur = bModele.modele;
                cSuivante = cNewSuivante;
                Ecran.afficherln("A effacer. couleur case précédente: ", cPrecedente.couleur);
                Ecran.afficherln("A effacer. couleur case actuelle: ", cActuelle.couleur);
                Ecran.afficherln("A effacer. couleur case suivante: ", cSuivante.couleur);
            }
        }
    }
    /*static Case newCaseSuivante(Case c2, Case c3, Case c4, Case c5, int bigCpt)
    {
        int cpt = bigCpt;
        Case cNew = c3;
        if( cpt == 1 )
        {
            cNew.couleur = c2.couleur;
            cNew.estOccupe = c2.estOccupe;
            cNew.estSuivante = c2.estSuivante;
            cNew.estDerniere = c2.estDerniere;
            cpt++;
        }
        else
        {
            if ( cpt == 2 )
            {
                cNew.couleur = c3.couleur;
                cNew.estOccupe = c3.estOccupe;
                cNew.estSuivante = c3.estSuivante;
                cNew.estDerniere = c3.estDerniere;
                cpt++; 
            }
            else
            {
                if ( cpt == 3 )
                {
                    cNew.couleur = c4.couleur;
                    cNew.estOccupe = c4.estOccupe;
                    cNew.estSuivante = c4.estSuivante;
                    cNew.estDerniere = c4.estDerniere;   
                }
                else
                {
                    if ( cpt == 4 )
                    {
                        cNew.couleur = c5.couleur;
                        cNew.estOccupe = c5.estOccupe;
                        cNew.estSuivante = c5.estSuivante;
                        cNew.estDerniere = c5.estDerniere;
                    }
                }
            }
        }
        return cNew;
    }*/
    static void affichagePlateauJeu(Case newCaseSuivante, Bolide bolide, Case c0, Case c1, Case c2, Case c3, Case c4, Case c5)
    {   
        Ecran.afficher(" ");
        for( int i = 0 ; i < 41 ; i++ )
        {
            Ecran.afficher("_");
        }
        Ecran.sautDeLigne();
        Ecran.afficherln("| ", bolide.modele, " | ", c1.couleur, " | ", c2.couleur, " | ", c3.couleur, " | ", c4.couleur, " | ", c5.couleur, " |");
        Ecran.afficher(" ");
        for( int i = 0 ; i < 41 ; i++ )
        {
            Ecran.afficher("-");
        }
        Ecran.sautDeLigne();
    }
    public static void main(String [] args)
    {
        Case c0 = initCase( 1, "DEPA", true, false, false);
        Case c1 = initCase( 2, "BLEU", false, true, false);
        Case c2 = initCase( 3, "ROUG", false, false, false);
        Case c3 = initCase( 4, "VERT", false, false, false);
        Case c4 = initCase( 5, "BLEU", false, false, false);
        Case c5 = initCase( 6, "JAUN", false, false, true);

        Bolide b1 = initBolide( "XOOX" );

        affichagePlateau(b1, c0, c1, c2, c3, c4, c5);
        bolideAvance( peutAvancer(c0, c1), couleurTiree(), b1, c0, c1, c2, c0 );
        affichagePlateau( b1, c0, c1, c2, c3, c4, c5 );
        bolideAvance( peutAvancer(c0, c1), couleurTireeD(), b1, c1, c2, c3, c1 );
        affichagePlateau( b1, c0, c1, c2, c3, c4, c5 );
    }

}
