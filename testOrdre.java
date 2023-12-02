public class testOrdre
{
    static class Bolide
    {
        String modele;
    }
    static class Case
    {
        String couleur;
        boolean estSuivante, estDerniere, estOccupe;
    }

    static Case initCase( String couleur, boolean estOccupe, boolean estSuiv, boolean estDerniere )
    {
        Case cInit = new Case();
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
                Ecran.afficherln("")


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
    static Case newCaseSuivante(Case c1, Case c2, Case c3, Case c4, Case c5, int bigCpt)
    {
        int cpt = bigCpt;
        Case cNew = c2;
        if( cpt == 1 )
        {
            cNew.couleur = c2.couleur;
            cNew.estOccupe = c2.estOccupe;
            cNew.estSuivante = c2.estSuivante;
            cNew.estDerniere = c2.estDerniere;
            Ecran.afficherln("cNew : cpt 1: ", cNew.couleur, " ", cNew.estOccupe, " ", cNew.estSuivante, " ", cNew.estDerniere, cpt );
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
                Ecran.afficherln("cNew : cpt 2: ", cNew.couleur, " ", cNew.estOccupe, " ", cNew.estSuivante, " ", cNew.estDerniere, cpt );
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
                    Ecran.afficherln("cNew : cpt 3: ", cNew.couleur, " ", cNew.estOccupe, " ", cNew.estSuivante, " ", cNew.estDerniere, cpt );
                    cpt++;
                }
                else
                {
                    if ( cpt == 4 )
                    {
                        cNew.couleur = c5.couleur;
                        cNew.estOccupe = c5.estOccupe;
                        cNew.estSuivante = c5.estSuivante;
                        cNew.estDerniere = c5.estDerniere;
                        Ecran.afficherln("cNew : cpt 4: ", cNew.couleur, " ", cNew.estOccupe, " ", cNew.estSuivante, " ", cNew.estDerniere, cpt );

                    }
                }
            }
        }
        return cNew;
    }
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
        Case c0 = initCase( "DEPA", true, false, false);
        Case c1 = initCase( "BLEU", false, true, false);
        Case c2 = initCase( "ROUG", false, false, false);
        Case c3 = initCase( "VERT", false, false, false);
        Case c4 = initCase( "BLEU", false, false, false);
        Case c5 = initCase( "JAUN", false, false, true);

        Bolide b1 = initBolide( "XOOX" );

        affichagePlateau(b1, c0, c1, c2, c3, c4, c5);
        bolideAvance( peutAvancer(c0, c1), couleurTiree(), b1, c0, c1, newCaseSuivante( c1,c2, c3, c4, c5, 1), c0 );
        affichagePlateau( b1, c0, c1, c2, c3, c4, c5 );
        bolideAvance( peutAvancer(c0, c1), couleurTireeD(), b1, c1, newCaseSuivante( c1, c2, c3, c4, c5, 1), c3, c1 );
        affichagePlateau( b1, c0, c1, c2, c3, c4, c5 );
    }

}
 static Case bolideAvance()
    {
        Ligne l = creerLigne();
        Bolide b = initBolide("XOOX");
        Case c1 = new Case();
        Case l0 = l.c1;
        Case l2 = new Case();
        // ICI MATTHIEU, il faut au fur-et-à-mesure réaffecter les variables.
        if( lancerDeDes() == l.c1.suivante.couleur && l.c1.estOccupe == true )
        {
            l.c1.estOccupe = false;
            l0 = l.c1.suivante;
            Ecran.afficherln(l.c1.couleur);
            l.c1.suivante.estOccupe = true;
            l.c1.suivante.couleur = b.modele;
            Ecran.afficherln("2ème case: ", l.c1.suivante.couleur);
            l2 = l.c1.suivante;
            Ecran.afficherln(l2.couleur);
        }
        else
        {
            if( lancerDeDes() == l.c1.suivante.suivante.couleur && l.c1.suivante.estOccupe == true)
            {
                l.c1.suivante = l0;
                
                Ecran.afficherln( l.c1.suivante.couleur);
                l0 = l.c1.suivante.suivante;
                Ecran.afficherln(l.c1.suivante.couleur);
                l.c1.suivante.suivante.estOccupe = true;
                l.c1.suivante.suivante.couleur = b.modele;
                Ecran.afficherln("3ème case: ", l.c1.suivante.suivante.couleur);
                l2 = l.c1.suivante.suivante;
            }
            else
            {
                if(lancerDeDes() == l.c1.suivante.suivante.suivante.couleur && l.c1.suivante.suivante.estOccupe == true)
                {
                    l.c1.suivante.suivante = l0;

                    Ecran.afficherln( l.c1.suivante.suivante.couleur );
                    l0 = l.c1.suivante.suivante.suivante;

                    l.c1.suivante.suivante.suivante.estOccupe = true;
                    l.c1.suivante.suivante.suivante.couleur = b.modele;
                    Ecran.afficherln("4ème case: ", l.c1.suivante.suivante.suivante.couleur);
                    l2 = l.c1.suivante.suivante.suivante;
                }
                else
                {
                    if(lancerDeDes() == l.c1.suivante.suivante.suivante.suivante.couleur && l.c1.suivante.suivante.suivante.estOccupe == true)
                    {
                        l.c1.suivante.suivante.suivante = l0;

                        Ecran.afficherln( l.c1.suivante.suivante.suivante.couleur );
                        l0 = l.c1.suivante.suivante.suivante.suivante;

                        l.c1.suivante.suivante.suivante.suivante.estOccupe = true;
                        l.c1.suivante.suivante.suivante.suivante.couleur = b.modele;
                        Ecran.afficherln("5ème case: ", l.c1.suivante.suivante.suivante.suivante.couleur);
                        l2 = l.c1.suivante.suivante.suivante.suivante;
                    }
                    else
                    {
                        if( lancerDeDes() == l.c1.suivante.suivante.suivante.suivante.suivante.couleur && l.c1.suivante.suivante.suivante.suivante.estOccupe == true )
                        {
                            l.c1.suivante.suivante.suivante.suivante = l0;
                            Ecran.afficherln( l.c1.suivante.suivante.suivante.suivante.couleur );
                            l0 = l.c1.suivante.suivante.suivante.suivante.suivante;

                            l.c1.suivante.suivante.suivante.suivante.suivante.estOccupe = true;
                            l.c1.suivante.suivante.suivante.suivante.suivante.couleur = b.modele;
                            Ecran.afficherln("6ème case: ", l.c1.suivante.suivante.suivante.suivante.suivante.couleur);
                            l2 = l.c1.suivante.suivante.suivante.suivante.suivante;
                        }
                    }
                }
            }  
           
        }
        return l2; 
    }
