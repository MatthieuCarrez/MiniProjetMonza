public class testMini
{
    static class Case
    {
        String couleur;
        boolean estOccupe, estDerniere;
        Case suivante;
    }
    static class Ligne
    {
        Case c1;

    }
    static class Bolide
    {
        String modele;
    }
    static Case initCase( String couleur, boolean estO, boolean estD )
    {
        Case c = new Case();
        c.couleur = couleur;
        c.estOccupe = estO;
        c.estDerniere = estD;
        return c;

    }
    static Bolide initBolide(String bModele)
    {
        Bolide b = new Bolide();
        b.modele = bModele;
        return b;
    }
    static Ligne creerLigne()
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
    // A enlever si problème
    static String lancerDeDes()
    {
        String res = "DEPA";
        char roll;
        
        Ecran.afficherln("Appuyer sur 'r' pour lancer le dé");
        roll = Clavier.saisirChar();
        while (roll != 'r')
        {
            Ecran.afficherln("Erreur veuiller réessayer");
            roll = Clavier.saisirChar();    
        }
        int tirage = (int) ( Math.random() ) + 1;
        switch( tirage )
        {
            case 1:
                res = "BLEU";
                Ecran.afficherln( "La couleur tirée est ", res, " !");
                break;
            case 2:
                res = "JAUN";
                Ecran.afficherln( "La couleur tirée est ", res, " !");
                break;
            case 3:
                res = "ROUG";
                Ecran.afficherln( "La couleur tirée est ", res, " !");
                break;
            case 4:
                res = "VERT";
                Ecran.afficherln( "La couleur tirée est ", res, " !");
                break;
        }//Debug
        /*Ecran.afficherln(res)*/;
        return res;
    }

    static void bolideAvance(Ligne l)
    {
        Bolide b = new Bolide();
        Case c1 = new Case();
        Case l0 = l.c1;
        b = initBolide("XOOX");
        // ICI MATTHIEU, il faut au fur-et-à-mesure réaffecter les variables.
        if( lancerDeDes() == l.c1.suivante.couleur && l.c1.estOccupe == true )
        {
            l.c1.estOccupe = false;
            l0 = l.c1.suivante;
            Ecran.afficherln(l.c1.couleur);
            l.c1.suivante.estOccupe = true;
            l.c1.suivante.couleur = b.modele;
            Ecran.afficherln("2ème case: ", l.c1.suivante.couleur);
            

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
                        }
                    }
                }
            }   
        }



    }
    static void affichagePlateau( Ligne l )
    {       
        Ecran.afficher(" ");
        for( int i = 0 ; i < 41 ; i++ )
        {
            Ecran.afficher("_");
        }
        Ecran.sautDeLigne();
        Ecran.afficherln("| ", l.c1.couleur, " | ", l.c1.suivante.couleur, " | ", l.c1.suivante.suivante.couleur, " | ", l.c1.suivante.suivante.suivante.couleur, " | ", l.c1.suivante.suivante.suivante.suivante.couleur, " | ", l.c1.suivante.suivante.suivante.suivante.suivante.couleur, " |");
        Ecran.afficher(" ");
        for( int i = 0 ; i < 41 ; i++ )
        {
            Ecran.afficher("‾");
        }
        Ecran.sautDeLigne();
    }
    static void affichagePlateau1( Ligne l )
    {       
        Ecran.afficher(" ");
        for( int i = 0 ; i < 41 ; i++ )
        {
            Ecran.afficher("_");
        }
        Ecran.sautDeLigne();
        Ecran.afficherln("| ", l.c1.couleur, " | ", l.c1.suivante.couleur, " | ", l.c1.suivante.suivante.couleur, " | ", l.c1.suivante.suivante.suivante.couleur, " | ", l.c1.suivante.suivante.suivante.suivante.couleur, " | ", l.c1.suivante.suivante.suivante.suivante.suivante.couleur, " |");
        Ecran.afficher(" ");
        for( int i = 0 ; i < 41 ; i++ )
        {
            Ecran.afficher("‾");
        }
        Ecran.sautDeLigne();
    }
    public static void main(String[] args)
    {
        bolideAvance(creerLigne());
        affichagePlateau( creerLigne() );
    }    
}
