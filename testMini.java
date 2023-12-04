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
        Case c;
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
    static Case avanceCase( Ligne cLigne, String resDe )
    {
        Case lTemp = new Case();
    
        
        Bolide b = initBolide("XOOX");
        
            
            if( resDe == cLigne.c1.suivante.couleur && cLigne.c1.estOccupe )
            {
                cLigne.c1.suivante.couleur = b.modele;

            
                
                Ecran.afficherln(lTemp.couleur);

            }
        return cLigne.c1.suivante;
        
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
        int tirage = (int) ( Math.random() * 4 ) + 1;
        switch( tirage )
        {
            case 1:
                res = "JAUN";
                Ecran.afficherln( "La couleur tirée est ", res, " !");
                break;
            case 2:
                res = "BLEU";
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

    static void affichagePlateau()
    {   
        
        Ligne l = creerLigne();
        // A modifier possiblement
        String s = lancerDeDes();

        /*do
        {*/
        avanceCase(l, s);
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
            Ecran.afficher("-");
        }
        Ecran.sautDeLigne();
        /*}while( l.c1.suivante.suivante.suivante.suivante.suivante.estOccupe != true );*/
    }
    public static void main(String[] args)
    {
        affichagePlateau();
    }    
}
