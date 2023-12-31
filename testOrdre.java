public class testOrdre
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
    static class De
    {
        String resDe1, resDe2, resDe3;
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
    static De initDe( String de1, String de2, String de3 )
    {
        De d = new De();
        d.resDe1 = de1;
        d.resDe2 = de2;
        d.resDe3 = de3;
        return d;
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
    static Ligne creerLigne2()
    {
        Ligne l = new Ligne();
        l.c1 = initCase( "DEPA", true, false );
        l.c1.suivante = initCase("ROSE", false, false);
        l.c1.suivante.suivante = initCase("JAUN", false, false);
        l.c1.suivante.suivante.suivante = initCase("BLAN", false, false);
        l.c1.suivante.suivante.suivante.suivante = initCase("ROUG", false, false);
        l.c1.suivante.suivante.suivante.suivante.suivante = initCase("VERT", false, true);
        return l;
    }
    // Sous-algo permettant de choisir les règles permettant d'avancer d'autant de cases que possible. Pas encore fonctonniel
    static Case avanceAutantCasePoss(Ligne cLigne, Ligne cLigne2)
    {
        int cpt = 0;
        Ligne l = cLigne;
        Ligne l2 = cLigne2;
        Case caseBolide = initCase("XOOX", true, false);
        Bolide b = initBolide("XOOX");
        De resDe = lancerDeDes();

        // Première case | Dans le cas où seulement l'une des deux couleurs des cases suivantes est égale à l'un des résultats des dés
        if( ( cLigne.c1.suivante.couleur == resDe.resDe1 || cLigne.c1.suivante.couleur == resDe.resDe2 || cLigne.c1.suivante.couleur == resDe.resDe3 || cLigne2.c1.suivante.couleur == resDe.resDe1 || cLigne2.c1.suivante.couleur == resDe.resDe2 || cLigne2.c1.suivante.couleur == resDe.resDe3 ) && ( cLigne.c1.estOccupe || cLigne2.c1.estOccupe ) )
        {   // Dans le cas où la couleur de la case suivante haute et la couleur de la case suivante basse sont égales à l'un des résultats des dés
            if( ( ( cLigne.c1.suivante.couleur == resDe.resDe1 || cLigne.c1.suivante.couleur == resDe.resDe2 || cLigne.c1.suivante.couleur == resDe.resDe3 ) && ( cLigne2.c1.suivante.couleur == resDe.resDe1 || cLigne2.c1.suivante.couleur == resDe.resDe2 || cLigne2.c1.suivante.couleur == resDe.resDe3 ) ) && ( cLigne.c1.estOccupe || cLigne2.c1.estOccupe ) )
            {   // On demande de choisir entre la case haute ou basse
                Ecran.afficherln("Choisissez entre la case du haut et la case du bas");
                Ecran.afficherln("1 pour haut et 2 pour bas");
                int choix = Clavier.saisirInt();
                // 1 = haute, 2 = basse
                while( choix != 1 && choix != 2 )
                {
                    Ecran.afficherln("Erreur, veuiller réessayer");
                    choix = Clavier.saisirInt();
                }

                if( ( choix == 1  && cLigne.c1.estOccupe ) || ( choix == 1 && cLigne2.c1.estOccupe ) )
                {   // Dans le cas de 1 donc BLEU
                    Ecran.afficherln("Le bolide avance"); 
                    cLigne.c1.suivante.couleur = b.modele;
                    // Les cases départ ne sont plus occupées
                    cLigne.c1.estOccupe = false;
                    cLigne2.c1.estOccupe = false;
                    // Les cases sont maintenant occupées
                    cLigne.c1.suivante.estOccupe = true;
                    cLigne2.c1.suivante.estOccupe = true;
                    Ecran.afficherln(cLigne.c1.suivante.couleur);   
                }
                else
                {   // Dans le cas de 2 donc ROSE
                    Ecran.afficherln("Le bolide avance"); 
                    cLigne2.c1.suivante.couleur = b.modele;
                    // Les cases départ ne sont plus occupées
                    cLigne.c1.estOccupe = false;
                    cLigne2.c1.estOccupe = false;
                    // Les cases sont maintenant occupées
                    cLigne.c1.suivante.estOccupe = true;
                    cLigne2.c1.suivante.estOccupe = true;
                    Ecran.afficherln(cLigne2.c1.suivante.couleur);
                    
                }
            }
            // Vérifie que les résultats des dés ne sont pas déjà utilisés, si tel est le cas, on change leur valeur  
            if( ( cLigne.c1.suivante.couleur == resDe.resDe1 || cLigne2.c1.suivante.couleur == resDe.resDe1 ) )
            {
            resDe.resDe1 = "Si vous voyez ceci c'est que je me suis foiré";
            }
            else
            {
                if( ( cLigne.c1.suivante.couleur == resDe.resDe2 || cLigne2.c1.suivante.couleur == resDe.resDe2 ) )
                {
                    resDe.resDe2 = "Si vous voyez ceci c'est que je me suis foiré";
                }
                else
                { 
                    if( ( cLigne.c1.suivante.couleur == resDe.resDe3 || cLigne2.c1.suivante.couleur == resDe.resDe3 ) )
                    {
                        resDe.resDe3 = "Si vous voyez ceci c'est que je me suis foiré";
                    }
                }
            }
        }
        
        // Deuxième case | Dans le cas où seulement l'une des deux couleurs des cases suivantes est égale à l'un des résultats des dés
        if( ( cLigne.c1.suivante.suivante.couleur == resDe.resDe1 || cLigne.c1.suivante.suivante.couleur == resDe.resDe2 || cLigne.c1.suivante.suivante.couleur == resDe.resDe3 || cLigne2.c1.suivante.suivante.couleur == resDe.resDe1 || cLigne2.c1.suivante.suivante.couleur == resDe.resDe2 || cLigne2.c1.suivante.suivante.couleur == resDe.resDe3 ) && ( cLigne.c1.suivante.estOccupe || cLigne2.c1.suivante.estOccupe ) )
        {   Ecran.afficherln("ROUG et JAUN");
            // Dans le cas où la couleur de la case suivante haute et la couleur de la case suivante basse sont égales à l'un des résultats des dés
            if( ( ( cLigne.c1.suivante.suivante.couleur == resDe.resDe1 || cLigne.c1.suivante.suivante.couleur == resDe.resDe2 || cLigne.c1.suivante.suivante.couleur == resDe.resDe3 ) && ( cLigne2.c1.suivante.suivante.couleur == resDe.resDe1 || cLigne2.c1.suivante.suivante.couleur == resDe.resDe2 || cLigne2.c1.suivante.suivante.couleur == resDe.resDe3 ) ) && ( cLigne.c1.suivante.estOccupe || cLigne2.c1.suivante.estOccupe ) )
            {   // On demande de choisir entre la case haute ou basse
                Ecran.afficherln("Choisissez entre la case du haut et la case du bas");
                Ecran.afficherln("1 pour haut et 2 pour bas");
                int choix = Clavier.saisirInt();
                // 1 = haute, 2 = basse
                while( choix != 1 && choix != 2 )
                {
                    Ecran.afficherln("Erreur, veuiller réessayer");
                    choix = Clavier.saisirInt();
                }   // Dans le cas de 1 donc ROUG
                if( choix == 1  && ( cLigne.c1.suivante.estOccupe && cLigne2.c1.suivante.estOccupe) )
                {
                    Ecran.afficherln("Le bolide avance");
                    cLigne.c1.suivante.couleur = "BLEU";
                    cLigne2.c1.suivante.couleur = "ROSE"; 
                    cLigne.c1.suivante.suivante.couleur = b.modele;
                    // Les cases départ ne sont plus occupées
                    cLigne.c1.suivante.estOccupe = false;
                    cLigne2.c1.suivante.estOccupe = false;
                    // Les cases sont maintenant occupées
                    cLigne.c1.suivante.suivante.estOccupe = true;
                    cLigne2.c1.suivante.suivante.estOccupe = true;
                    Ecran.afficherln(cLigne.c1.suivante.suivante.couleur);
                }
                else
                {   // Dans le cas de 2 donc JAUN
                    Ecran.afficherln("Le bolide avance"); 
                    cLigne.c1.suivante.couleur = "BLEU";
                    cLigne2.c1.suivante.couleur = "ROSE";
                    cLigne2.c1.suivante.suivante.couleur = b.modele;
                    // Les cases départ ne sont plus occupées
                    cLigne.c1.suivante.estOccupe = false;
                    cLigne2.c1.suivante.estOccupe = false;

                    cLigne.c1.suivante.suivante.estOccupe = true;
                    cLigne2.c1.suivante.suivante.estOccupe = true;
                    Ecran.afficherln(cLigne2.c1.suivante.suivante.couleur);
                }
            }
            else
            {
                if( cLigne.c1.suivante.suivante.couleur == resDe.resDe1 || cLigne.c1.suivante.suivante.couleur == resDe.resDe2 || cLigne.c1.suivante.suivante.couleur == resDe.resDe3 && cLigne.c1.suivante.estOccupe ) 
                {
                    // Dans le cas de ROUG
                    cLigne.c1.suivante.couleur = "BLEU";
                    cLigne2.c1.suivante.couleur = "ROSE";
                    // Les cases précédentes ne sont plus occupées
                    cLigne.c1.suivante.estOccupe = false;
                    cLigne2.c1.suivante.estOccupe = false;
                    cLigne.c1.suivante.suivante.couleur = b.modele;
                    // Les cases sont maintenant occupées
                    cLigne.c1.suivante.suivante.estOccupe = true;
                    cLigne2.c1.suivante.suivante.estOccupe = true;   
                }
                else
                {   // Dans le cas de JAUN
                    cLigne.c1.suivante.couleur = "BLEU";
                    cLigne2.c1.suivante.couleur = "ROSE";
                    // Les cases précédentes ne sont plus occupées
                    cLigne.c1.suivante.estOccupe = false;
                    cLigne2.c1.suivante.estOccupe = false;
                    cLigne2.c1.suivante.suivante.couleur = b.modele;
                    // Les cases sont maintenant occupées
                    cLigne.c1.suivante.suivante.estOccupe = true;
                    cLigne2.c1.suivante.suivante.estOccupe = true;
                }     
            }
        }   // Troisième case | Dans le cas où seulement l'une des deux couleurs des cases suivantes est égale à l'un des résultats des dés
        if( ( cLigne.c1.suivante.suivante.suivante.couleur == resDe.resDe1 || cLigne.c1.suivante.suivante.suivante.couleur == resDe.resDe2 || cLigne.c1.suivante.suivante.suivante.couleur == resDe.resDe3 || cLigne2.c1.suivante.suivante.suivante.couleur == resDe.resDe1 || cLigne2.c1.suivante.suivante.suivante.couleur == resDe.resDe2 || cLigne2.c1.suivante.suivante.suivante.couleur == resDe.resDe3 ) && ( cLigne.c1.suivante.suivante.estOccupe && cLigne2.c1.suivante.suivante.estOccupe ) )
        {   
            Ecran.afficherln("VERT ET BLANC");
            // Dans le cas où la couleur de la case suivante haute et la couleur de la case suivante basse sont égales à l'un des résultats des dés
            if( ( ( cLigne.c1.suivante.suivante.suivante.couleur == resDe.resDe1 || cLigne.c1.suivante.suivante.suivante.couleur == resDe.resDe2 || cLigne.c1.suivante.suivante.suivante.couleur == resDe.resDe3 ) && ( cLigne2.c1.suivante.suivante.suivante.couleur == resDe.resDe1 || cLigne2.c1.suivante.suivante.suivante.couleur == resDe.resDe2 || cLigne2.c1.suivante.suivante.suivante.couleur == resDe.resDe3 ) ) && ( cLigne.c1.suivante.suivante.estOccupe && cLigne2.c1.suivante.suivante.estOccupe ) )
            {   // On demande de choisir entre la case haute ou basse
                Ecran.afficherln("Choisissez entre la case du haut et la case du bas");
                Ecran.afficherln("1 pour haut et 2 pour bas");
                int choix = Clavier.saisirInt();
                // 1 = haute, 2 = basse
                while( choix != 1 && choix != 2 )
                {
                    Ecran.afficherln("Erreur, veuiller réessayer");
                    choix = Clavier.saisirInt();
                }   // Dans le cas de 1 donc VERT
                if( choix == 1  && ( cLigne.c1.suivante.suivante.estOccupe && cLigne2.c1.suivante.suivante.estOccupe) )
                {
                    Ecran.afficherln("Le bolide avance");
                    cLigne.c1.suivante.suivante.couleur = "ROUG";
                    cLigne2.c1.suivante.suivante.couleur = "JAUN"; 
                    cLigne.c1.suivante.suivante.couleur = b.modele;
                    // Les cases départ ne sont plus occupées
                    cLigne.c1.suivante.suivante.estOccupe = false;
                    cLigne2.c1.suivante.suivante.estOccupe = false;
                    // Les cases sont maintenant occupées
                    cLigne.c1.suivante.suivante.suivante.estOccupe = true;
                    cLigne2.c1.suivante.suivante.suivante.estOccupe = true;
                    Ecran.afficherln(cLigne.c1.suivante.suivante.couleur);
                }
                else
                {   // Dans le cas de 2 donc BLAN
                    Ecran.afficherln("Le bolide avance"); 
                    cLigne.c1.suivante.suivante.couleur = "ROUG";
                    cLigne2.c1.suivante.suivante.couleur = "JAUN";
                    cLigne2.c1.suivante.suivante.suivante.couleur = b.modele;
                    // Les cases départ ne sont plus occupées
                    cLigne.c1.suivante.suivante.estOccupe = false;
                    cLigne2.c1.suivante.suivante.estOccupe = false;
                    // Les cases sont maintenant occupées
                    cLigne.c1.suivante.suivante.suivante.estOccupe = true;
                    cLigne2.c1.suivante.suivante.suivante.estOccupe = true;
                    Ecran.afficherln(cLigne2.c1.suivante.suivante.suivante.couleur);
                }
            }
            else
            {
                if( cLigne.c1.suivante.suivante.suivante.couleur == resDe.resDe1 || cLigne.c1.suivante.suivante.suivante.couleur == resDe.resDe2 || cLigne.c1.suivante.suivante.suivante.couleur == resDe.resDe3 && cLigne.c1.suivante.suivante.estOccupe ) 
                {
                    // Dans le cas de VERT
                    cLigne.c1.suivante.suivante.couleur = "ROUG";
                    cLigne2.c1.suivante.suivante.couleur = "JAUN";
                    // Les cases précédentes ne sont plus occupées
                    cLigne.c1.suivante.suivante.estOccupe = false;
                    cLigne2.c1.suivante.suivante.estOccupe = false;
                    cLigne.c1.suivante.suivante.suivante.couleur = b.modele;
                    // Les cases sont maintenant occupées
                    cLigne.c1.suivante.suivante.suivante.estOccupe = true;
                    cLigne2.c1.suivante.suivante.suivante.estOccupe = true;
                }
                else
                {   // Dans le cas de BLANC   
                    cLigne.c1.suivante.suivante.couleur = "ROUG";
                    cLigne2.c1.suivante.suivante.couleur = "JAUN";
                    // Les cases précédentes ne sont plus occupées
                    cLigne.c1.suivante.suivante.estOccupe = false;
                    cLigne2.c1.suivante.suivante.estOccupe = false;
                    cLigne2.c1.suivante.suivante.suivante.couleur = b.modele;
                    // Les cases sont maintenant occupées
                    cLigne.c1.suivante.suivante.suivante.estOccupe = true;
                    cLigne2.c1.suivante.suivante.suivante.estOccupe = true;
                }     
            }
        }   // Quatrième case | Dans le cas où seulement l'une des deux couleurs des cases suivantes est égale à l'un des résultats des dés
        if( ( cLigne.c1.suivante.suivante.suivante.suivante.couleur == resDe.resDe1 || cLigne.c1.suivante.suivante.suivante.suivante.couleur == resDe.resDe2 || cLigne.c1.suivante.suivante.suivante.suivante.couleur == resDe.resDe3 || cLigne2.c1.suivante.suivante.suivante.suivante.couleur == resDe.resDe1 || cLigne2.c1.suivante.suivante.suivante.suivante.couleur == resDe.resDe2 || cLigne2.c1.suivante.suivante.suivante.suivante.couleur == resDe.resDe3 ) && ( cLigne.c1.suivante.suivante.suivante.estOccupe && cLigne2.c1.suivante.suivante.suivante.estOccupe ) )
        {   Ecran.afficherln("VERT ET BLANC");
            // Dans le cas où la couleur de la case suivante haute et la couleur de la case suivante basse sont égales à l'un des résultats des dés
            if( ( ( cLigne.c1.suivante.suivante.suivante.suivante.couleur == resDe.resDe1 || cLigne.c1.suivante.suivante.suivante.suivante.couleur == resDe.resDe2 || cLigne.c1.suivante.suivante.suivante.suivante.couleur == resDe.resDe3 ) && ( cLigne2.c1.suivante.suivante.suivante.suivante.couleur == resDe.resDe1 || cLigne2.c1.suivante.suivante.suivante.suivante.couleur == resDe.resDe2 || cLigne2.c1.suivante.suivante.suivante.suivante.couleur == resDe.resDe3 ) ) && ( cLigne.c1.suivante.suivante.suivante.estOccupe && cLigne2.c1.suivante.suivante.suivante.estOccupe ) )
            {   // On demande de choisir entre la case haute ou basse
                Ecran.afficherln("Choisissez entre la case du haut et la case du bas");
                Ecran.afficherln("1 pour haut et 2 pour bas");
                int choix = Clavier.saisirInt();
                // 1 = haute, 2 = basse
                while( choix != 1 && choix != 2 )
                {
                    Ecran.afficherln("Erreur, veuiller réessayer");
                    choix = Clavier.saisirInt();
                }   // Dans le cas de 1 donc BLEU
                if( choix == 1  && ( cLigne.c1.suivante.suivante.suivante.estOccupe && cLigne2.c1.suivante.suivante.suivante.estOccupe) )
                {
                    Ecran.afficherln("Le bolide avance");
                    cLigne.c1.suivante.suivante.suivante.couleur = "VERT";
                    cLigne2.c1.suivante.suivante.suivante.couleur = "BLAN"; 
                    cLigne.c1.suivante.suivante.suivante.couleur = b.modele;
                    // Les cases départ ne sont plus occupées
                    cLigne.c1.suivante.suivante.suivante.estOccupe = false;
                    cLigne2.c1.suivante.suivante.suivante.estOccupe = false;
                    // Les cases sont maintenant occupées
                    cLigne.c1.suivante.suivante.suivante.suivante.estOccupe = true;
                    cLigne2.c1.suivante.suivante.suivante.suivante.estOccupe = true;
                    Ecran.afficherln(cLigne.c1.suivante.suivante.suivante.couleur);
                }
                else
                {   // Dans le cas de 2 donc ROUG
                    Ecran.afficherln("Le bolide avance"); 
                    cLigne.c1.suivante.suivante.suivante.couleur = "VERT";
                    cLigne2.c1.suivante.suivante.suivante.couleur = "BLAN";
                    cLigne2.c1.suivante.suivante.suivante.suivante.couleur = b.modele;
                    // Les cases départ ne sont plus occupées
                    cLigne.c1.suivante.suivante.suivante.estOccupe = false;
                    cLigne2.c1.suivante.suivante.suivante.estOccupe = false;
                    // Les cases sont maintenant occupées
                    cLigne.c1.suivante.suivante.suivante.suivante.estOccupe = true;
                    cLigne2.c1.suivante.suivante.suivante.suivante.estOccupe = true;
                    Ecran.afficherln(cLigne2.c1.suivante.suivante.suivante.suivante.couleur);
                }
            }
            else
            {
                if( cLigne.c1.suivante.suivante.suivante.suivante.couleur == resDe.resDe1 || cLigne.c1.suivante.suivante.suivante.suivante.couleur == resDe.resDe2 || cLigne.c1.suivante.suivante.suivante.suivante.couleur == resDe.resDe3 && cLigne.c1.suivante.suivante.suivante.estOccupe ) 
                {
                    // Dans le cas de BLEU
                    cLigne.c1.suivante.suivante.suivante.couleur = "VERT";
                    cLigne2.c1.suivante.suivante.suivante.couleur = "BLAN";
                    // Les cases précédentes ne sont plus occupées
                    cLigne.c1.suivante.suivante.suivante.estOccupe = false;
                    cLigne2.c1.suivante.suivante.suivante.estOccupe = false;
                    cLigne.c1.suivante.suivante.suivante.suivante.couleur = b.modele;
                    // Les cases sont maintenant occupées
                    cLigne.c1.suivante.suivante.suivante.suivante.estOccupe = true;
                    cLigne2.c1.suivante.suivante.suivante.suivante.estOccupe = true;    
                }
                else
                {   // Dans le cas de ROUG   
                    cLigne.c1.suivante.suivante.suivante.couleur = "VERT";
                    cLigne2.c1.suivante.suivante.suivante.couleur = "BLAN";
                    // Les cases précédentes ne sont plus occupées
                    cLigne.c1.suivante.suivante.suivante.estOccupe = false;
                    cLigne2.c1.suivante.suivante.suivante.estOccupe = false;
                    cLigne2.c1.suivante.suivante.suivante.suivante.couleur = b.modele;
                    // Les cases sont maintenant occupées
                    cLigne.c1.suivante.suivante.suivante.suivante.estOccupe = true;
                    cLigne2.c1.suivante.suivante.suivante.suivante.estOccupe = true;
                }     
            }
        }
        if( ( cLigne.c1.suivante.suivante.suivante.suivante.suivante.couleur == resDe.resDe1 || cLigne.c1.suivante.suivante.suivante.suivante.suivante.couleur == resDe.resDe2 || cLigne.c1.suivante.suivante.suivante.suivante.suivante.couleur == resDe.resDe3 || cLigne2.c1.suivante.suivante.suivante.suivante.suivante.couleur == resDe.resDe1 || cLigne2.c1.suivante.suivante.suivante.suivante.suivante.couleur == resDe.resDe2 || cLigne2.c1.suivante.suivante.suivante.suivante.suivante.couleur == resDe.resDe3 ) && ( cLigne.c1.suivante.suivante.suivante.suivante.estOccupe && cLigne2.c1.suivante.suivante.suivante.suivante.estOccupe ) )
        {   
            Ecran.afficherln("VERT ET BLANC");
            // Dans le cas où la couleur de la case suivante haute et la couleur de la case suivante basse sont égales à l'un des résultats des dés
            if( ( ( cLigne.c1.suivante.suivante.suivante.suivante.suivante.couleur == resDe.resDe1 || cLigne.c1.suivante.suivante.suivante.suivante.suivante.couleur == resDe.resDe2 || cLigne.c1.suivante.suivante.suivante.suivante.suivante.couleur == resDe.resDe3 ) && ( cLigne2.c1.suivante.suivante.suivante.suivante.suivante.couleur == resDe.resDe1 || cLigne2.c1.suivante.suivante.suivante.suivante.suivante.couleur == resDe.resDe2 || cLigne2.c1.suivante.suivante.suivante.suivante.suivante.couleur == resDe.resDe3 ) ) && ( cLigne.c1.suivante.suivante.suivante.suivante.estOccupe && cLigne2.c1.suivante.suivante.suivante.suivante.estOccupe ) )
            {   // On demande de choisir entre la case haute ou basse
                Ecran.afficherln("Choisissez entre la case du haut et la case du bas");
                Ecran.afficherln("1 pour haut et 2 pour bas");
                int choix = Clavier.saisirInt();
                // 1 = haute, 2 = basse
                while( choix != 1 && choix != 2 )
                {
                    Ecran.afficherln("Erreur, veuiller réessayer");
                    choix = Clavier.saisirInt();
                }   // Dans le cas de 1 donc JAUN
                if( choix == 1  && ( cLigne.c1.suivante.suivante.suivante.suivante.estOccupe && cLigne2.c1.suivante.suivante.suivante.suivante.estOccupe) )
                {
                    Ecran.afficherln("Le bolide avance");
                    cLigne.c1.suivante.suivante.suivante.suivante.couleur = "BLEU";
                    cLigne2.c1.suivante.suivante.suivante.suivante.couleur = "ROUG"; 
                    cLigne.c1.suivante.suivante.suivante.suivante.couleur = b.modele;
                    // Les cases départ ne sont plus occupées
                    cLigne.c1.suivante.suivante.suivante.suivante.estOccupe = false;
                    cLigne2.c1.suivante.suivante.suivante.suivante.estOccupe = false;
                    // Les cases sont maintenant occupées
                    cLigne.c1.suivante.suivante.suivante.suivante.suivante.estOccupe = true;
                    cLigne2.c1.suivante.suivante.suivante.suivante.suivante.estOccupe = true;
                    Ecran.afficherln(cLigne.c1.suivante.suivante.suivante.suivante.couleur);
                }
                else
                {   // Dans le cas de 2 donc VERT
                    Ecran.afficherln("Le bolide avance"); 
                    cLigne.c1.suivante.suivante.suivante.suivante.couleur = "BLEU";
                    cLigne2.c1.suivante.suivante.suivante.suivante.couleur = "ROUG";
                    cLigne2.c1.suivante.suivante.suivante.suivante.suivante.couleur = b.modele;
                    // Les cases départ ne sont plus occupées
                    cLigne.c1.suivante.suivante.suivante.suivante.estOccupe = false;
                    cLigne2.c1.suivante.suivante.suivante.suivante.estOccupe = false;
                    // Les cases sont maintenant occupées
                    cLigne.c1.suivante.suivante.suivante.suivante.suivante.estOccupe = true;
                    cLigne2.c1.suivante.suivante.suivante.suivante.suivante.estOccupe = true;
                    Ecran.afficherln(cLigne2.c1.suivante.suivante.suivante.suivante.suivante.couleur);
                }
            }
            else
            {
                if( cLigne.c1.suivante.suivante.suivante.suivante.suivante.couleur == resDe.resDe1 || cLigne.c1.suivante.suivante.suivante.suivante.suivante.couleur == resDe.resDe2 || cLigne.c1.suivante.suivante.suivante.suivante.suivante.couleur == resDe.resDe3 && cLigne.c1.suivante.suivante.suivante.suivante.estOccupe ) 
                {
                    // Dans le cas de JAUN
                    cLigne.c1.suivante.suivante.suivante.suivante.couleur = "BLEU";
                    cLigne2.c1.suivante.suivante.suivante.suivante.couleur = "ROUG";
                    // Les cases précédentes ne sont plus occupées
                    cLigne.c1.suivante.suivante.suivante.suivante.estOccupe = false;
                    cLigne2.c1.suivante.suivante.suivante.suivante.estOccupe = false;
                    cLigne.c1.suivante.suivante.suivante.suivante.suivante.couleur = b.modele;
                    // Les cases sont maintenant occupées
                    cLigne.c1.suivante.suivante.suivante.suivante.suivante.estOccupe = true;
                    cLigne2.c1.suivante.suivante.suivante.suivante.suivante.estOccupe = true;    
                }
                else
                {   // Dans le cas de VERT
                    cLigne.c1.suivante.suivante.suivante.suivante.couleur = "BLEU";
                    cLigne2.c1.suivante.suivante.suivante.suivante.couleur = "ROUG";
                    // Les cases précédentes ne sont plus occupées
                    cLigne.c1.suivante.suivante.suivante.suivante.estOccupe = false;
                    cLigne2.c1.suivante.suivante.suivante.suivante.estOccupe = false;
                    cLigne2.c1.suivante.suivante.suivante.suivante.suivante.couleur = b.modele;
                    // Les cases sont maintenant occupées
                    cLigne.c1.suivante.suivante.suivante.suivante.suivante.estOccupe = true;
                    cLigne2.c1.suivante.suivante.suivante.suivante.suivante.estOccupe = true;
                }     
            }
        }
        return cLigne.c1.suivante;
    }
    // Sous-algo permettant de choisir les règles permettant d'avancer d'une seule case par lancer
    static Case avanceUneCase(Ligne cLigne, Ligne cLigne2)
    {
        Ligne l = cLigne;
        Ligne l2 = cLigne2;
        Bolide b = initBolide("XOOX");
        De resDe = lancerDeDes();
        
        // Première case | Dans le cas où seulement l'une des deux couleurs des cases suivantes est égale à l'un des résultats des dés
        if( ( cLigne.c1.suivante.couleur == resDe.resDe1 || cLigne.c1.suivante.couleur == resDe.resDe2 || cLigne.c1.suivante.couleur == resDe.resDe3 || cLigne2.c1.suivante.couleur == resDe.resDe1 || cLigne2.c1.suivante.couleur == resDe.resDe2 || cLigne2.c1.suivante.couleur == resDe.resDe3 ) && ( cLigne.c1.estOccupe || cLigne2.c1.estOccupe ) )
        {   // Dans le cas où la couleur de la case suivante haute et la couleur de la case suivante basse sont égales à l'un des résultats des dés
            if( ( ( cLigne.c1.suivante.couleur == resDe.resDe1 || cLigne.c1.suivante.couleur == resDe.resDe2 || cLigne.c1.suivante.couleur == resDe.resDe3 ) && ( cLigne2.c1.suivante.couleur == resDe.resDe1 || cLigne2.c1.suivante.couleur == resDe.resDe2 || cLigne2.c1.suivante.couleur == resDe.resDe3 ) ) && ( cLigne.c1.estOccupe || cLigne2.c1.estOccupe ) )
            {   // On demande de choisir entre la case haute ou basse
                Ecran.afficherln("Choisissez entre la case du haut et la case du bas");
                Ecran.afficherln("1 pour haut et 2 pour bas");
                int choix = Clavier.saisirInt();
                // 1 = haute, 2 = basse
                while( choix != 1 && choix != 2 )
                {
                    Ecran.afficherln("Erreur, veuiller réessayer");
                    choix = Clavier.saisirInt();
                }

                if( ( choix == 1  && cLigne.c1.estOccupe ) || ( choix == 1 && cLigne2.c1.estOccupe ) )
                {   // Dans le cas de 1 donc BLEU
                    Ecran.afficherln("Le bolide avance"); 
                    cLigne.c1.suivante.couleur = b.modele;
                    // Les cases départ ne sont plus occupées
                    cLigne.c1.estOccupe = false;
                    cLigne2.c1.estOccupe = false;
                    // Les cases sont maintenant occupées
                    cLigne.c1.suivante.estOccupe = true;
                    cLigne2.c1.suivante.estOccupe = true;
                    Ecran.afficherln(cLigne.c1.suivante.couleur);  
                }
                else
                {   // Dans le cas de 2 donc ROSE
                    Ecran.afficherln("Le bolide avance"); 
                    cLigne2.c1.suivante.couleur = b.modele;
                    // Les cases départ ne sont plus occupées
                    cLigne.c1.estOccupe = false;
                    cLigne2.c1.estOccupe = false;
                    // Les cases sont maintenant occupées
                    cLigne.c1.suivante.estOccupe = true;
                    cLigne2.c1.suivante.estOccupe = true;
                    Ecran.afficherln(cLigne2.c1.suivante.couleur);
                    
                }
            }
            else
            {   // Dans le cas où seulement l'une des deux couleurs des cases suivantes est égale à l'un des résultats des dés
                if ( ( cLigne.c1.suivante.couleur == resDe.resDe1 || cLigne.c1.suivante.couleur == resDe.resDe2 || cLigne.c1.suivante.couleur == resDe.resDe3 ) && ( cLigne.c1.estOccupe || cLigne2.c1.estOccupe ) )
                {   // Dans le cas de BLEU
                    cLigne.c1.suivante.couleur = b.modele;
                    // Les cases départ ne sont plus occupées
                    cLigne.c1.estOccupe = false;
                    cLigne2.c1.estOccupe = false;
                    
                    cLigne.c1.suivante.estOccupe = true;
                    cLigne2.c1.suivante.estOccupe = true;
                }
                else
                {   // Dans le cas de ROSE
                    cLigne2.c1.suivante.couleur = b.modele;
                    // Les cases départ ne sont plus occupées
                    cLigne.c1.estOccupe = false;
                    cLigne2.c1.estOccupe = false;
                    
                    // Les cases sont maintenant occupées
                    cLigne.c1.suivante.estOccupe = true;
                    cLigne2.c1.suivante.estOccupe = true;
                }
            }
        }
        else /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        {   // Deuxième case | Dans le cas où seulement l'une des deux couleurs des cases suivantes est égale à l'un des résultats des dés
            if( ( cLigne.c1.suivante.suivante.couleur == resDe.resDe1 || cLigne.c1.suivante.suivante.couleur == resDe.resDe2 || cLigne.c1.suivante.suivante.couleur == resDe.resDe3 || cLigne2.c1.suivante.suivante.couleur == resDe.resDe1 || cLigne2.c1.suivante.suivante.couleur == resDe.resDe2 || cLigne2.c1.suivante.suivante.couleur == resDe.resDe3 ) && ( cLigne.c1.suivante.estOccupe || cLigne2.c1.suivante.estOccupe ) )
            {  Ecran.afficherln("ROUG et JAUN");
                // Dans le cas où la couleur de la case suivante haute et la couleur de la case suivante basse sont égales à l'un des résultats des dés
                if( ( ( cLigne.c1.suivante.suivante.couleur == resDe.resDe1 || cLigne.c1.suivante.suivante.couleur == resDe.resDe2 || cLigne.c1.suivante.suivante.couleur == resDe.resDe3 ) && ( cLigne2.c1.suivante.suivante.couleur == resDe.resDe1 || cLigne2.c1.suivante.suivante.couleur == resDe.resDe2 || cLigne2.c1.suivante.suivante.couleur == resDe.resDe3 ) ) && ( cLigne.c1.suivante.estOccupe || cLigne2.c1.suivante.estOccupe ) )
                {   // On demande de choisir entre la case haute ou basse
                    Ecran.afficherln("Choisissez entre la case du haut et la case du bas");
                    Ecran.afficherln("1 pour haut et 2 pour bas");
                    int choix = Clavier.saisirInt();
                    // 1 = haute, 2 = basse
                    while( choix != 1 && choix != 2 )
                    {
                        Ecran.afficherln("Erreur, veuiller réessayer");
                        choix = Clavier.saisirInt();
                    }   // Dans le cas de 1 donc ROUG
                    if( choix == 1  && ( cLigne.c1.suivante.estOccupe && cLigne2.c1.suivante.estOccupe) )
                    {
                        Ecran.afficherln("Le bolide avance");
                        cLigne.c1.suivante.couleur = "BLEU";
                        cLigne2.c1.suivante.couleur = "ROSE"; 
                        cLigne.c1.suivante.suivante.couleur = b.modele;
                        // Les cases départ ne sont plus occupées
                        cLigne.c1.suivante.estOccupe = false;
                        cLigne2.c1.suivante.estOccupe = false;
                        // Les cases sont maintenant occupées
                        cLigne.c1.suivante.suivante.estOccupe = true;
                        cLigne2.c1.suivante.suivante.estOccupe = true;
                        Ecran.afficherln(cLigne.c1.suivante.suivante.couleur);
                    }
                    else
                    {   // Dans le cas de 2 donc JAUN
                        Ecran.afficherln("Le bolide avance"); 
                        cLigne.c1.suivante.couleur = "BLEU";
                        cLigne2.c1.suivante.couleur = "ROSE";
                        cLigne2.c1.suivante.suivante.couleur = b.modele;
                        // Les cases départ ne sont plus occupées
                        cLigne.c1.suivante.estOccupe = false;
                        cLigne2.c1.suivante.estOccupe = false;

                        cLigne.c1.suivante.suivante.estOccupe = true;
                        cLigne2.c1.suivante.suivante.estOccupe = true;
                        Ecran.afficherln(cLigne2.c1.suivante.suivante.couleur);
                    }
                }
                else
                {
                    if( cLigne.c1.suivante.suivante.couleur == resDe.resDe1 || cLigne.c1.suivante.suivante.couleur == resDe.resDe2 || cLigne.c1.suivante.suivante.couleur == resDe.resDe3 && cLigne.c1.suivante.estOccupe ) 
                    {
                        // Dans le cas de ROUG
                        cLigne.c1.suivante.couleur = "BLEU";
                        cLigne2.c1.suivante.couleur = "ROSE";
                        // Les cases précédentes ne sont plus occupées
                        cLigne.c1.suivante.estOccupe = false;
                        cLigne2.c1.suivante.estOccupe = false;
                        cLigne.c1.suivante.suivante.couleur = b.modele;
                        // Les cases sont maintenant occupées
                        cLigne.c1.suivante.suivante.estOccupe = true;
                        cLigne2.c1.suivante.suivante.estOccupe = true;
                        
                    }
                    else
                    {   // Dans le cas de JAUN
                        cLigne.c1.suivante.couleur = "BLEU";
                        cLigne2.c1.suivante.couleur = "ROSE";
                        // Les cases précédentes ne sont plus occupées
                        cLigne.c1.suivante.estOccupe = false;
                        cLigne2.c1.suivante.estOccupe = false;
                        cLigne2.c1.suivante.suivante.couleur = b.modele;
                        // Les cases sont maintenant occupées
                        cLigne.c1.suivante.suivante.estOccupe = true;
                        cLigne2.c1.suivante.suivante.estOccupe = true;
                    }     
                }
            }
            else //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            {   // Troisième case | Dans le cas où seulement l'une des deux couleurs des cases suivantes est égale à l'un des résultats des dés
                if( ( cLigne.c1.suivante.suivante.suivante.couleur == resDe.resDe1 || cLigne.c1.suivante.suivante.suivante.couleur == resDe.resDe2 || cLigne.c1.suivante.suivante.suivante.couleur == resDe.resDe3 || cLigne2.c1.suivante.suivante.suivante.couleur == resDe.resDe1 || cLigne2.c1.suivante.suivante.suivante.couleur == resDe.resDe2 || cLigne2.c1.suivante.suivante.suivante.couleur == resDe.resDe3 ) && ( cLigne.c1.suivante.suivante.estOccupe && cLigne2.c1.suivante.suivante.estOccupe ) )
                {   Ecran.afficherln("VERT ET BLANC");
                    // Dans le cas où la couleur de la case suivante haute et la couleur de la case suivante basse sont égales à l'un des résultats des dés
                    if( ( ( cLigne.c1.suivante.suivante.suivante.couleur == resDe.resDe1 || cLigne.c1.suivante.suivante.suivante.couleur == resDe.resDe2 || cLigne.c1.suivante.suivante.suivante.couleur == resDe.resDe3 ) && ( cLigne2.c1.suivante.suivante.suivante.couleur == resDe.resDe1 || cLigne2.c1.suivante.suivante.suivante.couleur == resDe.resDe2 || cLigne2.c1.suivante.suivante.suivante.couleur == resDe.resDe3 ) ) && ( cLigne.c1.suivante.suivante.estOccupe && cLigne2.c1.suivante.suivante.estOccupe ) )
                    {   // On demande de choisir entre la case haute ou basse
                        Ecran.afficherln("Choisissez entre la case du haut et la case du bas");
                        Ecran.afficherln("1 pour haut et 2 pour bas");
                        int choix = Clavier.saisirInt();
                        // 1 = haute, 2 = basse
                        while( choix != 1 && choix != 2 )
                        {
                            Ecran.afficherln("Erreur, veuiller réessayer");
                            choix = Clavier.saisirInt();
                        }   // Dans le cas de 1 donc VERT
                        if( choix == 1  && ( cLigne.c1.suivante.suivante.estOccupe && cLigne2.c1.suivante.suivante.estOccupe) )
                        {
                            Ecran.afficherln("Le bolide avance");
                            cLigne.c1.suivante.suivante.couleur = "ROUG";
                            cLigne2.c1.suivante.suivante.couleur = "JAUN"; 
                            cLigne.c1.suivante.suivante.couleur = b.modele;
                            // Les cases départ ne sont plus occupées
                            cLigne.c1.suivante.suivante.estOccupe = false;
                            cLigne2.c1.suivante.suivante.estOccupe = false;
                            // Les cases sont maintenant occupées
                            cLigne.c1.suivante.suivante.suivante.estOccupe = true;
                            cLigne2.c1.suivante.suivante.suivante.estOccupe = true;
                            Ecran.afficherln(cLigne.c1.suivante.suivante.couleur);
                        }
                        else
                        {   // Dans le cas de 2 donc BLAN
                            Ecran.afficherln("Le bolide avance"); 
                            cLigne.c1.suivante.suivante.couleur = "ROUG";
                            cLigne2.c1.suivante.suivante.couleur = "JAUN";
                            cLigne2.c1.suivante.suivante.suivante.couleur = b.modele;
                            // Les cases départ ne sont plus occupées
                            cLigne.c1.suivante.suivante.estOccupe = false;
                            cLigne2.c1.suivante.suivante.estOccupe = false;
                            // Les cases sont maintenant occupées
                            cLigne.c1.suivante.suivante.suivante.estOccupe = true;
                            cLigne2.c1.suivante.suivante.suivante.estOccupe = true;
                            Ecran.afficherln(cLigne2.c1.suivante.suivante.suivante.couleur);
                        }
                    }
                    else
                    {
                        if( cLigne.c1.suivante.suivante.suivante.couleur == resDe.resDe1 || cLigne.c1.suivante.suivante.suivante.couleur == resDe.resDe2 || cLigne.c1.suivante.suivante.suivante.couleur == resDe.resDe3 && cLigne.c1.suivante.suivante.estOccupe ) 
                        {
                            // Dans le cas de VERT
                            cLigne.c1.suivante.suivante.couleur = "ROUG";
                            cLigne2.c1.suivante.suivante.couleur = "JAUN";
                            // Les cases précédentes ne sont plus occupées
                            cLigne.c1.suivante.suivante.estOccupe = false;
                            cLigne2.c1.suivante.suivante.estOccupe = false;
                            cLigne.c1.suivante.suivante.suivante.couleur = b.modele;
                            // Les cases sont maintenant occupées
                            cLigne.c1.suivante.suivante.suivante.estOccupe = true;
                            cLigne2.c1.suivante.suivante.suivante.estOccupe = true;
                            
                        }
                        else
                        {   // Dans le cas de BLANC   
                            cLigne.c1.suivante.suivante.couleur = "ROUG";
                            cLigne2.c1.suivante.suivante.couleur = "JAUN";
                            // Les cases précédentes ne sont plus occupées
                            cLigne.c1.suivante.suivante.estOccupe = false;
                            cLigne2.c1.suivante.suivante.estOccupe = false;
                            cLigne2.c1.suivante.suivante.suivante.couleur = b.modele;
                            // Les cases sont maintenant occupées
                            cLigne.c1.suivante.suivante.suivante.estOccupe = true;
                            cLigne2.c1.suivante.suivante.suivante.estOccupe = true;
                        }     
                    }
                }
                else //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                {   // Quatrième case | Dans le cas où seulement l'une des deux couleurs des cases suivantes est égale à l'un des résultats des dés  
                    if( ( cLigne.c1.suivante.suivante.suivante.suivante.couleur == resDe.resDe1 || cLigne.c1.suivante.suivante.suivante.suivante.couleur == resDe.resDe2 || cLigne.c1.suivante.suivante.suivante.suivante.couleur == resDe.resDe3 || cLigne2.c1.suivante.suivante.suivante.suivante.couleur == resDe.resDe1 || cLigne2.c1.suivante.suivante.suivante.suivante.couleur == resDe.resDe2 || cLigne2.c1.suivante.suivante.suivante.suivante.couleur == resDe.resDe3 ) && ( cLigne.c1.suivante.suivante.suivante.estOccupe && cLigne2.c1.suivante.suivante.suivante.estOccupe ) )
                    {   Ecran.afficherln("VERT ET BLANC");
                        // Dans le cas où la couleur de la case suivante haute et la couleur de la case suivante basse sont égales à l'un des résultats des dés
                        if( ( ( cLigne.c1.suivante.suivante.suivante.suivante.couleur == resDe.resDe1 || cLigne.c1.suivante.suivante.suivante.suivante.couleur == resDe.resDe2 || cLigne.c1.suivante.suivante.suivante.suivante.couleur == resDe.resDe3 ) && ( cLigne2.c1.suivante.suivante.suivante.suivante.couleur == resDe.resDe1 || cLigne2.c1.suivante.suivante.suivante.suivante.couleur == resDe.resDe2 || cLigne2.c1.suivante.suivante.suivante.suivante.couleur == resDe.resDe3 ) ) && ( cLigne.c1.suivante.suivante.suivante.estOccupe && cLigne2.c1.suivante.suivante.suivante.estOccupe ) )
                        {   // On demande de choisir entre la case haute ou basse
                            Ecran.afficherln("Choisissez entre la case du haut et la case du bas");
                            Ecran.afficherln("1 pour haut et 2 pour bas");
                            int choix = Clavier.saisirInt();
                            // 1 = haute, 2 = basse
                            while( choix != 1 && choix != 2 )
                            {
                                Ecran.afficherln("Erreur, veuiller réessayer");
                                choix = Clavier.saisirInt();
                            }   // Dans le cas de 1 donc BLEU
                            if( choix == 1  && ( cLigne.c1.suivante.suivante.suivante.estOccupe && cLigne2.c1.suivante.suivante.suivante.estOccupe) )
                            {
                                Ecran.afficherln("Le bolide avance");
                                cLigne.c1.suivante.suivante.suivante.couleur = "VERT";
                                cLigne2.c1.suivante.suivante.suivante.couleur = "BLAN"; 
                                cLigne.c1.suivante.suivante.suivante.couleur = b.modele;
                                // Les cases départ ne sont plus occupées
                                cLigne.c1.suivante.suivante.suivante.estOccupe = false;
                                cLigne2.c1.suivante.suivante.suivante.estOccupe = false;
                                // Les cases sont maintenant occupées
                                cLigne.c1.suivante.suivante.suivante.suivante.estOccupe = true;
                                cLigne2.c1.suivante.suivante.suivante.suivante.estOccupe = true;
                                Ecran.afficherln(cLigne.c1.suivante.suivante.suivante.couleur);
                            }
                            else
                            {   // Dans le cas de 2 donc ROUG
                                Ecran.afficherln("Le bolide avance"); 
                                cLigne.c1.suivante.suivante.suivante.couleur = "VERT";
                                cLigne2.c1.suivante.suivante.suivante.couleur = "BLAN";
                                cLigne2.c1.suivante.suivante.suivante.suivante.couleur = b.modele;
                                // Les cases départ ne sont plus occupées
                                cLigne.c1.suivante.suivante.suivante.estOccupe = false;
                                cLigne2.c1.suivante.suivante.suivante.estOccupe = false;
                                // Les cases sont maintenant occupées
                                cLigne.c1.suivante.suivante.suivante.suivante.estOccupe = true;
                                cLigne2.c1.suivante.suivante.suivante.suivante.estOccupe = true;
                                Ecran.afficherln(cLigne2.c1.suivante.suivante.suivante.suivante.couleur);
                            }
                        }
                        else
                        {
                            if( cLigne.c1.suivante.suivante.suivante.suivante.couleur == resDe.resDe1 || cLigne.c1.suivante.suivante.suivante.suivante.couleur == resDe.resDe2 || cLigne.c1.suivante.suivante.suivante.suivante.couleur == resDe.resDe3 && cLigne.c1.suivante.suivante.suivante.estOccupe ) 
                            {
                                // Dans le cas de BLEU
                                cLigne.c1.suivante.suivante.suivante.couleur = "VERT";
                                cLigne2.c1.suivante.suivante.suivante.couleur = "BLAN";
                                // Les cases précédentes ne sont plus occupées
                                cLigne.c1.suivante.suivante.suivante.estOccupe = false;
                                cLigne2.c1.suivante.suivante.suivante.estOccupe = false;
                                cLigne.c1.suivante.suivante.suivante.suivante.couleur = b.modele;
                                // Les cases sont maintenant occupées
                                cLigne.c1.suivante.suivante.suivante.suivante.estOccupe = true;
                                cLigne2.c1.suivante.suivante.suivante.suivante.estOccupe = true;
                                
                            }
                            else
                            {   // Dans le cas de ROUG   
                                cLigne.c1.suivante.suivante.suivante.couleur = "VERT";
                                cLigne2.c1.suivante.suivante.suivante.couleur = "BLAN";
                                // Les cases précédentes ne sont plus occupées
                                cLigne.c1.suivante.suivante.suivante.estOccupe = false;
                                cLigne2.c1.suivante.suivante.suivante.estOccupe = false;
                                cLigne2.c1.suivante.suivante.suivante.suivante.couleur = b.modele;
                                // Les cases sont maintenant occupées
                                cLigne.c1.suivante.suivante.suivante.suivante.estOccupe = true;
                                cLigne2.c1.suivante.suivante.suivante.suivante.estOccupe = true;
                            }     
                        }
                    }
                    else //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                    {   // Cinquième case | Dans le cas où seulement l'une des deux couleurs des cases suivantes est égale à l'un des résultats des dés 
                        if( ( cLigne.c1.suivante.suivante.suivante.suivante.suivante.couleur == resDe.resDe1 || cLigne.c1.suivante.suivante.suivante.suivante.suivante.couleur == resDe.resDe2 || cLigne.c1.suivante.suivante.suivante.suivante.suivante.couleur == resDe.resDe3 || cLigne2.c1.suivante.suivante.suivante.suivante.suivante.couleur == resDe.resDe1 || cLigne2.c1.suivante.suivante.suivante.suivante.suivante.couleur == resDe.resDe2 || cLigne2.c1.suivante.suivante.suivante.suivante.suivante.couleur == resDe.resDe3 ) && ( cLigne.c1.suivante.suivante.suivante.suivante.estOccupe && cLigne2.c1.suivante.suivante.suivante.suivante.estOccupe ) )
                        {   
                            Ecran.afficherln("VERT ET BLANC");
                            // Dans le cas où la couleur de la case suivante haute et la couleur de la case suivante basse sont égales à l'un des résultats des dés
                            if( ( ( cLigne.c1.suivante.suivante.suivante.suivante.suivante.couleur == resDe.resDe1 || cLigne.c1.suivante.suivante.suivante.suivante.suivante.couleur == resDe.resDe2 || cLigne.c1.suivante.suivante.suivante.suivante.suivante.couleur == resDe.resDe3 ) && ( cLigne2.c1.suivante.suivante.suivante.suivante.suivante.couleur == resDe.resDe1 || cLigne2.c1.suivante.suivante.suivante.suivante.suivante.couleur == resDe.resDe2 || cLigne2.c1.suivante.suivante.suivante.suivante.suivante.couleur == resDe.resDe3 ) ) && ( cLigne.c1.suivante.suivante.suivante.suivante.estOccupe && cLigne2.c1.suivante.suivante.suivante.suivante.estOccupe ) )
                            {   // On demande de choisir entre la case haute ou basse
                                Ecran.afficherln("Choisissez entre la case du haut et la case du bas");
                                Ecran.afficherln("1 pour haut et 2 pour bas");
                                int choix = Clavier.saisirInt();
                                // 1 = haute, 2 = basse
                                while( choix != 1 && choix != 2 )
                                {
                                    Ecran.afficherln("Erreur, veuiller réessayer");
                                    choix = Clavier.saisirInt();
                                }   // Dans le cas de 1 donc JAUN
                                if( choix == 1  && ( cLigne.c1.suivante.suivante.suivante.suivante.estOccupe && cLigne2.c1.suivante.suivante.suivante.suivante.estOccupe) )
                                {
                                    Ecran.afficherln("Le bolide avance");
                                    cLigne.c1.suivante.suivante.suivante.suivante.couleur = "BLEU";
                                    cLigne2.c1.suivante.suivante.suivante.suivante.couleur = "ROUG"; 
                                    cLigne.c1.suivante.suivante.suivante.suivante.couleur = b.modele;
                                    // Les cases départ ne sont plus occupées
                                    cLigne.c1.suivante.suivante.suivante.suivante.estOccupe = false;
                                    cLigne2.c1.suivante.suivante.suivante.suivante.estOccupe = false;
                                    // Les cases sont maintenant occupées
                                    cLigne.c1.suivante.suivante.suivante.suivante.suivante.estOccupe = true;
                                    cLigne2.c1.suivante.suivante.suivante.suivante.suivante.estOccupe = true;
                                    Ecran.afficherln(cLigne.c1.suivante.suivante.suivante.suivante.couleur);
                                }
                                else
                                {   // Dans le cas de 2 donc VERT
                                    Ecran.afficherln("Le bolide avance"); 
                                    cLigne.c1.suivante.suivante.suivante.suivante.couleur = "BLEU";
                                    cLigne2.c1.suivante.suivante.suivante.suivante.couleur = "ROUG";
                                    cLigne2.c1.suivante.suivante.suivante.suivante.suivante.couleur = b.modele;
                                    // Les cases départ ne sont plus occupées
                                    cLigne.c1.suivante.suivante.suivante.suivante.estOccupe = false;
                                    cLigne2.c1.suivante.suivante.suivante.suivante.estOccupe = false;
                                    // Les cases sont maintenant occupées
                                    cLigne.c1.suivante.suivante.suivante.suivante.suivante.estOccupe = true;
                                    cLigne2.c1.suivante.suivante.suivante.suivante.suivante.estOccupe = true;
                                    Ecran.afficherln(cLigne2.c1.suivante.suivante.suivante.suivante.suivante.couleur);
                                }
                            }
                            else
                            {
                                if( cLigne.c1.suivante.suivante.suivante.suivante.suivante.couleur == resDe.resDe1 || cLigne.c1.suivante.suivante.suivante.suivante.suivante.couleur == resDe.resDe2 || cLigne.c1.suivante.suivante.suivante.suivante.suivante.couleur == resDe.resDe3 && cLigne.c1.suivante.suivante.suivante.suivante.estOccupe ) 
                                {
                                    // Dans le cas de JAUN
                                    cLigne.c1.suivante.suivante.suivante.suivante.couleur = "BLEU";
                                    cLigne2.c1.suivante.suivante.suivante.suivante.couleur = "ROUG";
                                    // Les cases précédentes ne sont plus occupées
                                    cLigne.c1.suivante.suivante.suivante.suivante.estOccupe = false;
                                    cLigne2.c1.suivante.suivante.suivante.suivante.estOccupe = false;
                                    cLigne.c1.suivante.suivante.suivante.suivante.suivante.couleur = b.modele;
                                    // Les cases sont maintenant occupées
                                    cLigne.c1.suivante.suivante.suivante.suivante.suivante.estOccupe = true;
                                    cLigne2.c1.suivante.suivante.suivante.suivante.suivante.estOccupe = true;
                                    
                                }
                                else
                                {   // Dans le cas de VERT
                                    cLigne.c1.suivante.suivante.suivante.suivante.couleur = "BLEU";
                                    cLigne2.c1.suivante.suivante.suivante.suivante.couleur = "ROUG";
                                    // Les cases précédentes ne sont plus occupées
                                    cLigne.c1.suivante.suivante.suivante.suivante.estOccupe = false;
                                    cLigne2.c1.suivante.suivante.suivante.suivante.estOccupe = false;
                                    cLigne2.c1.suivante.suivante.suivante.suivante.suivante.couleur = b.modele;
                                    // Les cases sont maintenant occupées
                                    cLigne.c1.suivante.suivante.suivante.suivante.suivante.estOccupe = true;
                                    cLigne2.c1.suivante.suivante.suivante.suivante.suivante.estOccupe = true;
                                }     
                            }
                        }
                    }   
                }
            }
        }
        return cLigne.c1.suivante;
    }
    // A enlever si problème
    static De lancerDeDes()
    {
        De r = initDe("DEPA", "DEPA", "DEPA");
        char roll;
        Ecran.afficherln("Premier lancer !");
        Ecran.afficherln("Appuyer sur 'r' pour lancer le dé");
        
        roll = Clavier.saisirChar();
        while (roll != 'r')
        {
            Ecran.afficherln("Erreur, veuiller réessayer");
            roll = Clavier.saisirChar();    
        }
        int tirage = (int) ( Math.random() * 6 ) + 1;
        switch( tirage )
        {
            case 1:
                r.resDe1 = "BLEU";
                Ecran.afficherln( "La couleur tirée est ", r.resDe1, " !" );
                break;
            case 2:
                r.resDe1 = "JAUN";
                Ecran.afficherln( "La couleur tirée est ", r.resDe1, " !" );
                break;
            case 3:
                r.resDe1 = "ROUG";
                Ecran.afficherln( "La couleur tirée est ", r.resDe1, " !" );
                break;
            case 4:
                r.resDe1 = "VERT";
                Ecran.afficherln( "La couleur tirée est ", r.resDe1, " !" );
                break;
            case 5:
                r.resDe1 = "ROSE";
                Ecran.afficherln( "La couleur tirée est ", r.resDe1, " !" );
                break;
            case 6:
                r.resDe1 = "BLAN";
                Ecran.afficherln( "La couleur tirée est ", r.resDe1, " !" );
                break;
        }
        // Lancer du deuxième dé
        Ecran.afficherln("Deuxième lancer !");
        Ecran.afficherln("Appuyer sur 'r' pour lancer le dé");

        roll = Clavier.saisirChar();
        while (roll != 'r')
        {
            Ecran.afficherln("Erreur, veuiller réessayer");
            roll = Clavier.saisirChar();    
        }
        int tirage2 = (int) ( Math.random() * 6) + 1;
        switch( tirage2 )
        {
            case 1:
                r.resDe2 = "BLEU";
                Ecran.afficherln( "La couleur tirée est ", r.resDe2, " !");
                break;
            case 2:
                r.resDe2 = "JAUN";
                Ecran.afficherln( "La couleur tirée est ", r.resDe2, " !");
                break;
            case 3:
                r.resDe2 = "ROUG";
                Ecran.afficherln( "La couleur tirée est ", r.resDe2, " !");
                break;
            case 4:
                r.resDe2 = "VERT";
                Ecran.afficherln( "La couleur tirée est ", r.resDe2, " !");
                break;
            case 5:
                r.resDe2 = "ROSE";
                Ecran.afficherln( "La couleur tirée est ", r.resDe2, " !" );
                break;
            case 6:
                r.resDe2 = "BLAN";
                Ecran.afficherln( "La couleur tirée est ", r.resDe2, " !" );
                break;
        }
        // Lancer du troisième dé
        Ecran.afficherln("Troisième lancer !");
        Ecran.afficherln("Appuyer sur 'r' pour lancer le dé");

        roll = Clavier.saisirChar();
        while (roll != 'r')
        {
            Ecran.afficherln("Erreur, veuiller réessayer");
            roll = Clavier.saisirChar();    
        }
        int tirage3 = (int) ( Math.random() * 6 ) + 1;
        switch( tirage3 )
        {
            case 1:
                r.resDe3 = "BLEU";
                Ecran.afficherln( "La couleur tirée est ", r.resDe3, " !");
                break;
            case 2:
                r.resDe3 = "JAUN";
                Ecran.afficherln( "La couleur tirée est ", r.resDe3, " !");
                break;
            case 3:
                r.resDe3 = "ROUG";
                Ecran.afficherln( "La couleur tirée est ", r.resDe3, " !");
                break;
            case 4:
                r.resDe3 = "VERT";
                Ecran.afficherln( "La couleur tirée est ", r.resDe3, " !");
                break;
            case 5:
                r.resDe3 = "ROSE";
                Ecran.afficherln( "La couleur tirée est ", r.resDe3, " !" );
                break;
            case 6:
                r.resDe3 = "BLAN";
                Ecran.afficherln( "La couleur tirée est ", r.resDe3, " !" );
                break;
        }
        //Debug
        Ecran.afficherln(r.resDe1, " ", r.resDe2, " ", r.resDe3);
        return r;
    }

    static void affichagePlateau()
    {   
        
        Ligne l = creerLigne1();
        Ligne l2 = creerLigne2();
        do{
        avanceAutantCasePoss(l, l2);
        /*avanceUneCase(l, l2);*/
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
        for( int i = 0 ; i < 41 ; i++ )
        {
            Ecran.afficher("_");
        }
        Ecran.sautDeLigne();
        Ecran.afficherln("| ", l2.c1.couleur, " | ", l2.c1.suivante.couleur, " | ", l2.c1.suivante.suivante.couleur, " | ", l2.c1.suivante.suivante.suivante.couleur, " | ", l2.c1.suivante.suivante.suivante.suivante.couleur, " | ", l2.c1.suivante.suivante.suivante.suivante.suivante.couleur, " |");
        Ecran.afficher(" ");
        for( int i = 0 ; i < 41 ; i++ )
        {
            Ecran.afficher("-");
        }
        Ecran.sautDeLigne();
        }while( l.c1.suivante.suivante.suivante.suivante.suivante.estOccupe != true || l2.c1.suivante.suivante.suivante.suivante.suivante.estOccupe != true );
    }
    public static void main(String [] args)
    {
        affichagePlateau();
    }    
}
