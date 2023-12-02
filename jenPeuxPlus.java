public class jenPeuxPlus
{
    static class Case
    {
        String couleur;
        boolean occupe, derniere;
        Case suivante;
    }
    static class Bolide
    {
        String modele;
        Case laCase;
    }

    static Case initiationCase( String s, boolean b1, boolean b2 )
    {
        Case newCase = new Case();
    }
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

    static void main( String [] args )
    {

    }
}
