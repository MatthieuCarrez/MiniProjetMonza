import java.util.*;

class Joueur {
    int position;
    String nom;

    public Joueur(String nom) {
        this.nom = nom;
        this.position = 0;
    }

    public void avancer(int avance) {
        position += avance;
        System.out.println(nom + " avance de " + avance + " cases.");
    }
}

public class JeuMonza {
    private static final List<String> couleurs = Arrays.asList("Rouge", "Bleu", "Vert", "Jaune");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        Map<Integer, String> cases = new HashMap<>();
        for (int i = 1; i <= 40; i++) {
            cases.put(i, couleurs.get(random.nextInt(couleurs.size())));
        }

        Joueur joueur1 = new Joueur("Joueur 1");
        Joueur joueur2 = new Joueur("Joueur 2");

        System.out.println("Bienvenue au jeu de Monza !");
        System.out.println("Le premier joueur à atteindre la ligne d'arrivée gagne.");

        while (joueur1.position < 40 && joueur2.position < 40) {
            afficherPlateau(joueur1, joueur2, cases);
            jouerTour(joueur1, scanner, cases);
            if (joueur1.position >= 40) {
                afficherPlateau(joueur1, joueur2, cases);
                afficherResultat(joueur1);
                break;
            }

            afficherPlateau(joueur1, joueur2, cases);
            jouerTour(joueur2, scanner, cases);
            if (joueur2.position >= 40) {
                afficherPlateau(joueur1, joueur2, cases);
                afficherResultat(joueur2);
                break;
            }
        }

        if (joueur1.position >= 40 && joueur2.position >= 40) {
            System.out.println("C'est un match nul !");
        }
    }

    public static void afficherPlateau(Joueur joueur1, Joueur joueur2, Map<Integer, String> cases) {
        System.out.println("Plateau de jeu :");
        for (int i = 1; i <= 40; i++) {
            String caseColor = cases.get(i);
            if (joueur1.position == i) {
                System.out.print("1 ");
            } else if (joueur2.position == i) {
                System.out.print("2 ");
            } else {
                System.out.print("- ");
            }
        }
        System.out.println();
        for (int i = 1; i <= 40; i++) {
            String caseColor = cases.get(i);
            System.out.print(caseColor.substring(0, 1) + " ");
        }
        System.out.println();
    }

    public static void jouerTour(Joueur joueur, Scanner scanner, Map<Integer, String> cases) {
        System.out.println(joueur.nom + ", appuyez sur Entrée pour lancer le dé.");
        scanner.nextLine();

        Random random = new Random();
        String couleurObtenue = couleurs.get(random.nextInt(couleurs.size()));

        int prochaineCase = joueur.position + 1;
        if (prochaineCase <= 40 && couleurObtenue.equals(cases.get(prochaineCase))) {
            joueur.avancer(1);
            System.out.println("Case couleur : " + couleurObtenue);
        } else {
            System.out.println("Impossible d'avancer, la couleur ne correspond pas !");
        }
    }

    public static void afficherResultat(Joueur joueur) {
        System.out.println(joueur.nom + " a gagné !");
    }
}
