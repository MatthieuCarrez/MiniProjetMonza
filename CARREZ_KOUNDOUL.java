// CARREZ Matthieu et Abdourakhmane KOUNDOUL TP H2
public class CARREZ_KOUNDOUL
{   // Type agrégé des case du plateau
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
    }/////////////////////
    // Type agrégé des dés
    static class De
    {   // resDé1 = dé 1, resDé2 = dé 2, resDé3 = dé 3
        String resDe1, resDe2, resDe3;
    }///////////////////////
    // Type agrégé du bolide
    static class Bolide
    {
        String modele; // Modele du bolide AKA XOOX
        Case c;
    }//////////////////////////////////
    // Sous-algo initialisant les cases
    static Case initCase( String couleur, boolean estO, boolean estD )
    {
        Case c = new Case();
        c.couleur = couleur;
        c.estOccupe = estO;
        c.estDerniere = estD;
        return c;

    }//////////////////////////////////
    // Sous-algo initialisant le bolide
    static Bolide initBolide(String bModele)
    {
        Bolide b = new Bolide();
        b.modele = bModele;
        return b;
    }
    // Sous-algo initialisant les dés
    static De initDe( String de1, String de2, String de3 )
    {
        De d = new De();
        d.resDe1 = de1;
        d.resDe2 = de2;
        d.resDe3 = de3;
        return d;
    }/////////////////////////////////////////////////
    // Sous-algo permettant de créer la première ligne
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
    }////////////////////////////////////////////////
    // Sous-algo permettant de créer la seconde ligne
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
    }////////////////////////////////////////////////////////////////////////////////////////
    // Sous-algo qui permet d'afficher les règles et de choisir lesquelles afficher à l'écran
    static void affichageRegles()
    {   int choix = 0;
        do
        {
            Ecran.afficherln("Veuiller choisir les règles à afficher ");
            Ecran.afficherln("1 - Règles Monza avec avancement d'une case à chaque lancer des trois dés");
            Ecran.afficherln("2 - Règles Monza avec avancement d'autant de cases que possible à chaque lancer des trois dés");
            Ecran.afficherln("Si vous voulez jouer, taper 0");
            choix = Clavier.saisirInt();
            // Si l'utilisateur entre autre chose qu'1, 2 ou 0, affiche un message d'erreur et permet à l'utilisateur d'entrer une valeur correcte
            while( choix != 1 && choix != 2 && choix != 0)
            {
                Ecran.afficherln("Erreur de saisie, veuiller recommencer");
                choix = Clavier.saisirInt();
            }
            // Si l'utilisateur entre 1
            if( choix == 1 )
            {
                Ecran.afficherln("Vous avez choisi les règles Monza avec avancement d'une case à chaque lancer des trois dés.");
                Ecran.afficherln("Le but du jeu est d'atteindre la dernière case du plateau.");
                Ecran.afficherln("Avec ces règles, il est possible d'avancer que d'une case par lancer, peu importe s'il était possible d'avancer plus.");
                Ecran.afficherln("Retour au choix des règles");
            }
            else
            {   // Si l'utilisateur entre 2
                if( choix == 2 )
                {
                    Ecran.afficherln("Vous avez choisi les règles avec avancement d'autant de cases que possible à chaque lancer des trois dés.");
                    Ecran.afficherln("Le but du jeu est d'atteindre la dernière case du plateau. Avec ces règles, il est possible d'avancer d'autant de cases que possible.");
                    Ecran.afficherln("Par exemple si la prochaine case est BLEU et que celle d'après est ROUG et que lors du lancer des dés vous obtenez BLEU, ROUG et VERT, il vous sera possible d'avancer jusque la case ROUG.");
                    Ecran.afficherln("Retour au choix des règles");
                }
            }
            Ecran.sautDeLigne();
        }while( choix != 0); // // Si l'utilisateur entre 0
    }
    ////////////////////////////////////////////
    // Sous-algo ui permet de choisir les règles
    // Entrée choixMode = 1 ou 2 selon le choix de l'utilisateur. On le récupère dans affichagePlateau pour jouer selon les règles choisies
    static int choixRegles(int choixMode)
    {
        Ecran.afficherln("Il est temps de choisir le mode de jeu");
        Ecran.afficherln("1 - Monza avec avancement d'une case à chaque lancer des trois dés");
        Ecran.afficherln("2 - Monza avec avancement d'autant de cases que possible à chaque lancer des trois dés");
        choixMode = Clavier.saisirInt();

        while( choixMode != 1 && choixMode != 2 )
        {
            Ecran.afficherln("Erreur de saisie, veuiller recommencer.");
            choixMode = Clavier.saisirInt();
        }
        return choixMode;
    }//////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Sous-algo permettant de choisir les règles permettant d'avancer d'autant de cases que possible AKA l'étape 3
    // Entrée cLigne = première ligne , Entrée cLigne2 = deuxième ligne
    static Case avanceAutantCasePoss(Ligne cLigne, Ligne cLigne2)
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
        // On remplace le String des dés déja utilisés par autre chose dans le but qu'ils ne soient pas réutilisés
        if( ( cLigne.c1.suivante.couleur == resDe.resDe1 || cLigne2.c1.suivante.couleur == resDe.resDe1 ) && ( cLigne.c1.estOccupe || cLigne2.c1.estOccupe ) )
        {
            resDe.resDe1 = "Ce message n'est pas censé s'afficher";
        }
        else
        {
            if( ( cLigne.c1.suivante.couleur == resDe.resDe2 || cLigne2.c1.suivante.couleur == resDe.resDe2 ) && ( cLigne.c1.estOccupe || cLigne2.c1.estOccupe ) )
            {
                resDe.resDe2 = "Vraiment, ce message n'est pas censé s'afficher";
            }
            else
            {
                if( ( cLigne.c1.suivante.couleur == resDe.resDe3 || cLigne2.c1.suivante.couleur == resDe.resDe3 ) && ( cLigne.c1.estOccupe || cLigne2.c1.estOccupe ) )
                {
                    resDe.resDe3 = "J'insiste, ce message n'est VRAIMENT pas censé s'afficher";
                }
            }
        }
        ///////////////////////////////////////////////////////////////////////////////////////////////////////
        // Deuxième case | Si le bolide a avancer sur la case d'avant alors le bolide peut avancer sur celle-ci
        if( cLigne.c1.suivante.couleur == b.modele || cLigne2.c1.suivante.couleur == b.modele && ( cLigne.c1.suivante.estOccupe || cLigne2.c1.suivante.estOccupe ) )
        {
            // Dans le cas où seulement l'une des deux couleurs des cases suivantes est égale à l'un des résultats des dés
            if( ( cLigne.c1.suivante.suivante.couleur == resDe.resDe1 || cLigne.c1.suivante.suivante.couleur == resDe.resDe2 || cLigne.c1.suivante.suivante.couleur == resDe.resDe3 || cLigne2.c1.suivante.suivante.couleur == resDe.resDe1 || cLigne2.c1.suivante.suivante.couleur == resDe.resDe2 || cLigne2.c1.suivante.suivante.couleur == resDe.resDe3 ) && ( cLigne.c1.suivante.estOccupe || cLigne2.c1.suivante.estOccupe ) )
            {   
                /*Ecran.afficherln("ROUG et JAUN");*/ // Debug pour tester les conditions
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
                        // Les cases sont maintenant occupées
                        cLigne.c1.suivante.suivante.estOccupe = true;
                        cLigne2.c1.suivante.suivante.estOccupe = true;
                        // Debug
                        /*Ecran.afficherln(cLigne2.c1.suivante.suivante.couleur);*/
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
        }
        // On remplace le String des dés déja utilisés par autre chose dans le but qu'ils ne soient pas réutilisés
        if( ( cLigne.c1.suivante.suivante.couleur == resDe.resDe1 || cLigne2.c1.suivante.suivante.couleur == resDe.resDe1 ) && ( cLigne.c1.suivante.estOccupe || cLigne2.c1.suivante.estOccupe ) )
        {   // Dé 1
            resDe.resDe1 = "Ce message n'est pas censé s'afficher";
        }
        else
        {
            if( ( cLigne.c1.suivante.suivante.couleur == resDe.resDe2 || cLigne2.c1.suivante.suivante.couleur == resDe.resDe2 ) && ( cLigne.c1.suivante.estOccupe || cLigne2.c1.suivante.estOccupe ) )
            {   // Dé 2
                resDe.resDe2 = "Vraiment, ce message n'est pas censé s'afficher";
            }
            else
            {   // Dé 3
                if( ( cLigne.c1.suivante.suivante.couleur == resDe.resDe3 || cLigne2.c1.suivante.suivante.couleur == resDe.resDe3 ) && ( cLigne.c1.suivante.estOccupe || cLigne2.c1.suivante.estOccupe ) )
                {
                    resDe.resDe3 = "J'insiste, ce message n'est VRAIMENT pas censé s'afficher";
                }
            }
        }
        ////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Troisième case | Si le bolide a avancer sur la case d'avant alors le bolide peut avancer sur celle-ci
        if( cLigne.c1.suivante.suivante.couleur == b.modele || cLigne2.c1.suivante.suivante.couleur == b.modele && ( cLigne.c1.suivante.suivante.estOccupe && cLigne2.c1.suivante.suivante.estOccupe ) )
        {
            // Dans le cas où seulement l'une des deux couleurs des cases suivantes est égale à l'un des résultats des dés
            if( ( cLigne.c1.suivante.suivante.suivante.couleur == resDe.resDe1 || cLigne.c1.suivante.suivante.suivante.couleur == resDe.resDe2 || cLigne.c1.suivante.suivante.suivante.couleur == resDe.resDe3 || cLigne2.c1.suivante.suivante.suivante.couleur == resDe.resDe1 || cLigne2.c1.suivante.suivante.suivante.couleur == resDe.resDe2 || cLigne2.c1.suivante.suivante.suivante.couleur == resDe.resDe3 ) && ( cLigne.c1.suivante.suivante.estOccupe && cLigne2.c1.suivante.suivante.estOccupe ) )
            {   
                /*Ecran.afficherln("VERT ET BLANC");*/ // Debug pour tester les conditions
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
        }
        // On remplace la donnée des dés déja utiliser par autre chose dans le but qu'il ne soit pas réutilisés
        if( ( cLigne.c1.suivante.suivante.suivante.couleur == resDe.resDe1 || cLigne2.c1.suivante.suivante.suivante.couleur == resDe.resDe1 ) && ( cLigne.c1.suivante.suivante.estOccupe && cLigne2.c1.suivante.suivante.estOccupe ) )
        {
            resDe.resDe1 = "Ce message n'est pas censé s'afficher";
        }
        else
        {
            if( ( cLigne.c1.suivante.suivante.suivante.couleur == resDe.resDe2 || cLigne2.c1.suivante.suivante.suivante.couleur == resDe.resDe2 ) && ( cLigne.c1.suivante.suivante.estOccupe && cLigne2.c1.suivante.suivante.estOccupe ) )
            {
                resDe.resDe2 = "Vraiment, ce message n'est pas censé s'afficher";
            }
            else
            {
                if( ( cLigne.c1.suivante.suivante.suivante.couleur == resDe.resDe3 || cLigne2.c1.suivante.suivante.suivante.couleur == resDe.resDe3 ) && ( cLigne.c1.suivante.suivante.estOccupe && cLigne2.c1.suivante.suivante.estOccupe ) )
                {
                    resDe.resDe3 = "J'insiste, ce message n'est VRAIMENT pas censé s'afficher";
                }
            }
        }
        ////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Quatrième case | Si le bolide a avancer sur la case d'avant alors le bolide peut avancer sur celle-ci
        if( cLigne.c1.suivante.suivante.suivante.couleur == b.modele || cLigne2.c1.suivante.suivante.suivante.couleur == b.modele && ( cLigne.c1.suivante.suivante.suivante.estOccupe && cLigne2.c1.suivante.suivante.suivante.estOccupe ) )
        {
            // Dans le cas où seulement l'une des deux couleurs des cases suivantes est égale à l'un des résultats des dés  
            if( ( cLigne.c1.suivante.suivante.suivante.suivante.couleur == resDe.resDe1 || cLigne.c1.suivante.suivante.suivante.suivante.couleur == resDe.resDe2 || cLigne.c1.suivante.suivante.suivante.suivante.couleur == resDe.resDe3 || cLigne2.c1.suivante.suivante.suivante.suivante.couleur == resDe.resDe1 || cLigne2.c1.suivante.suivante.suivante.suivante.couleur == resDe.resDe2 || cLigne2.c1.suivante.suivante.suivante.suivante.couleur == resDe.resDe3 ) && ( cLigne.c1.suivante.suivante.suivante.estOccupe && cLigne2.c1.suivante.suivante.suivante.estOccupe ) )
            {   
                /*Ecran.afficherln("VERT ET BLANC");*/ // Debug pour tester les conditions
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
        }
        // On remplace la donnée des dés déja utiliser par autre chose dans le but qu'il ne soit pas réutilisés
        if( ( cLigne.c1.suivante.suivante.suivante.suivante.couleur == resDe.resDe1 || cLigne2.c1.suivante.suivante.suivante.suivante.couleur == resDe.resDe1 ) && ( cLigne.c1.suivante.suivante.suivante.estOccupe && cLigne2.c1.suivante.suivante.suivante.estOccupe ) )
        {
            resDe.resDe1 = "Ce message n'est pas censé s'afficher";
        }
        else
        {
            if( ( cLigne.c1.suivante.suivante.suivante.suivante.couleur == resDe.resDe2 || cLigne2.c1.suivante.suivante.suivante.suivante.couleur == resDe.resDe2 ) && ( cLigne.c1.suivante.suivante.suivante.estOccupe && cLigne2.c1.suivante.suivante.suivante.estOccupe ) )
            {
                resDe.resDe2 = "Vraiment, ce message n'est pas censé s'afficher";
            }
            else
            {
                if( ( cLigne.c1.suivante.suivante.suivante.suivante.couleur == resDe.resDe3 || cLigne2.c1.suivante.suivante.suivante.suivante.couleur == resDe.resDe3 ) && ( cLigne.c1.suivante.suivante.suivante.estOccupe && cLigne2.c1.suivante.suivante.suivante.estOccupe ) )
                {
                    resDe.resDe3 = "J'insiste, ce message n'est VRAIMENT pas censé s'afficher";
                }
            }
        }
        ////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Cinquième case | Si le bolide a avancer sur la case d'avant alors le bolide peut avancer sur celle-ci
        if( cLigne.c1.suivante.suivante.suivante.suivante.couleur == b.modele || cLigne2.c1.suivante.suivante.suivante.suivante.couleur == b.modele && ( cLigne.c1.suivante.suivante.suivante.suivante.estOccupe && cLigne2.c1.suivante.suivante.suivante.suivante.estOccupe ) )
        {
            // Dans le cas où seulement l'une des deux couleurs des cases suivantes est égale à l'un des résultats des dés 
            if( ( cLigne.c1.suivante.suivante.suivante.suivante.suivante.couleur == resDe.resDe1 || cLigne.c1.suivante.suivante.suivante.suivante.suivante.couleur == resDe.resDe2 || cLigne.c1.suivante.suivante.suivante.suivante.suivante.couleur == resDe.resDe3 || cLigne2.c1.suivante.suivante.suivante.suivante.suivante.couleur == resDe.resDe1 || cLigne2.c1.suivante.suivante.suivante.suivante.suivante.couleur == resDe.resDe2 || cLigne2.c1.suivante.suivante.suivante.suivante.suivante.couleur == resDe.resDe3 ) && ( cLigne.c1.suivante.suivante.suivante.suivante.estOccupe && cLigne2.c1.suivante.suivante.suivante.suivante.estOccupe ) )
            {   
                /*Ecran.afficherln("VERT ET BLANC");*/ // Debug pour tester les conditions
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
        }return cLigne.c1.suivante;
    }   
    // Sous-algo permettant de choisir les règles permettant d'avancer d'une seule case par lancer AKA l'étape 2
    // Entrée cLigne = première ligne , Entrée cLigne2 = deuxième ligne
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
            {  
                /*Ecran.afficherln("ROUG et JAUN");*/ // Debug pour tester les conditions
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
                {   
                    /*Ecran.afficherln("VERT ET BLANC");*/ // Debug pour tester les conditions
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
                    {   
                        /*Ecran.afficherln("VERT ET BLANC");*/ // Debug pour tester les conditions
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
                            /*Ecran.afficherln("VERT ET BLANC");*/ // Debug pour tester les conditions
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
    // Sous-algo permetant de lancer les dés
    // J'ai fais le choix de demander à l'utilisateur d'appuyer sur une touche pour lancer les dés pour que ce soit plus facile à lire lors de l'utilisation du programme
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
    // Sous-algo permmetant l'affichage du plateau
    static void affichagePlateau()
    {
        Ligne l = creerLigne1();
        Ligne l2 = creerLigne2();
        
        if( choixRegles(0) == 1 )
        {   // Règles avec un seul avancement à la fois
            // Premier affichage de la première ligne
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
            // Premier affichage de la deuxième ligne
            Ecran.afficher(" ");
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

            do
            {   // Plateau avec les valeurs misent à jour
                avanceUneCase(l, l2);
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
                Ecran.afficher(" ");
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
            Ecran.afficherln("Vous avez atteint l'arrivée, bravo vous avez gagné !");
        }
        else//////////////////////////////////////////////
        {   //Règles avec autant d'avancement que possible
            // Premier affichage de la première ligne
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
            // Premier affichage de la deuxième ligne
            Ecran.afficher(" ");
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
            do
            {   // Plateau avec les valeurs misent à jour
                avanceAutantCasePoss(l, l2);
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
                Ecran.afficher(" ");
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
            Ecran.afficherln("Vous avez atteint l'arrivée, bravo vous avez gagné !");
        }
    }
    // Main
    public static void main(String [] args)
    {
        affichageRegles();
        affichagePlateau();
    }
    // Du QoL, expliquer les entrées et sorties des sous-algo
}