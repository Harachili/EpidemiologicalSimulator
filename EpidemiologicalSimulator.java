import java.util.Scanner;

public class EpidemiologicalSimulator { // Main Function
    // Kormack-McKendrick epidemiological model from 1927
    // SIR model -> Susceptible, Infectious, or Recovered

    public static void main(String[] args) {
        petuniaPot();
    }

    public static void petuniaPot() {

        // Declaration of input parameters
        long populationSize = 0;
        long resources = 0;
        int testCost = 0;
        int speed = 0;
        double infectivity = 0;
        double symptomSeverity = 0;
        double fatalityRate = 0;
        int duration = 0;

        Scanner sc = new Scanner(System.in);

        System.out.println("\nWelcome to our epidemiological simulator!\n");
        System.out.println("Would you like to use demographic data from a known country or create a customized one?\n1 ==> Default\n2 ==> Custom");

        int countryChoice = sc.nextInt();

        // Choosing a default country among Italy, UK, USA, China
        while (true) {
            if (countryChoice == 1) {
                System.out.println("\nChoose a country:\n1 ==> Italy\n2 ==> UK\n3 ==> USA\n4 ==> China");
                int countrySelection = sc.nextInt();

                if (countrySelection == 1) {
                    System.out.println("\nThe demographic data for Italy is:");
                    populationSize = 60359546;
                    resources = 1_800_000_000L;
                    testCost = 30;
                    speed = 10;
                    System.out.println("Population size: " + populationSize + "\nResources: " + resources + "€" + "\nCost of a test: " + testCost + "€");
                    break;
                }
                if (countrySelection == 2) {
                    System.out.println("\nThe demographic data for the UK is:");
                    populationSize = 67545757;
                    resources = 2_000_000_000L;
                    testCost = 250;  // telegraph.co.uk reports the cost in pounds to be around £250
                    speed = 10;
                    System.out.println("Population size: " + populationSize + "\nResources: " + resources + "£" + "\nCost of a test: " + testCost + "£");
                    break;
                }
                if (countrySelection == 3) {
                    System.out.println("\nThe demographic data for the USA is:");
                    populationSize = 329_311_764L;
                    resources = 6_500_000_000L;
                    testCost = 1000;  // usatoday.com reports the cost in dollars to be around $1000
                    speed = 10;
                    System.out.println("Population size: " + populationSize + "\nResources: " + resources + "$" + "\nCost of a test: " + testCost + "$");
                    break;
                }
                if (countrySelection == 4) {
                    System.out.println("\nThe demographic data for China is:");
                    populationSize = 1_433_783_686;
                    resources = 28_600_000_000L;
                    testCost = 180;  // ANSA.it reports the cost in Yuan to be around ¥180
                    speed = 10;
                    System.out.println("Population size: " + populationSize + "\nResources: " + resources + " ¥" + "\nCost of a test: " + testCost + " ¥");
                    break;
                } else {
                    System.out.println("A number between 1 and 4, inclusive, is required!");
                }
            }

            // User-entered State parameters in case they don't choose the default
            else {

                // GENERAL ASPECTS
                System.out.println("Population size: ");
                populationSize = sc.nextLong();             // Initial population

                System.out.println("Resources: ");
                resources = sc.nextLong();                     // Initial resources

                System.out.println("Test cost: ");
                testCost = sc.nextInt();                        // Cost of a single test

                System.out.println("Speed: ");
                speed = sc.nextInt();                     // Average number of encounters per person
            }
        }

        System.out.println("\nWould you like to use data from a known virus or create a customized one?\n1 ==> Default\n2 ==> Custom");
        int virusChoice = sc.nextInt();

        // Choosing a default virus among COVID, plague, HIV, Ebola
        while (true) {
            if (virusChoice == 1) {
                System.out.println("\nChoose a virus:\n1 ==> Coronavirus\n2 ==> Plague\n3 ==> HIV\n4 ==> Ebola");
                int i2 = sc.nextInt();
                if (i2 == 1) { // Coronavirus
                    System.out.println("\nThe epidemiological data for COVID-19 is:");
                    infectivity = 60;
                    symptomSeverity = 55; // laleggepertutti.it
                    fatalityRate = 5;
                    duration = 21;
                    System.out.println("Infectivity: " + (int) infectivity + "%" + "\nSymptom Severity: " + (int) symptomSeverity + "%" + "\nFatality Rate: " + (int) fatalityRate + "%" + "\nDuration: " + duration + " days");
                    break;
                }
                if (i2 == 2) { // Plague
                    System.out.println("\nThe epidemiological data for the plague is:");
                    infectivity = 65;
                    symptomSeverity = 80;
                    fatalityRate = 50;     // wikipedia.it
                    duration = 20;
                    System.out.println("Infectivity: " + (int) infectivity + "%" + "\nSymptom Severity: " + (int) symptomSeverity + "%" + "\nFatality Rate: " + (int) fatalityRate + "%" + "\nDuration: " + duration + " days");
                    break;
                }
                if (i2 == 3) { // HIV
                    System.out.println("\nThe epidemiological data for HIV is:");
                    infectivity = 10;
                    symptomSeverity = 90;
                    fatalityRate = 50;
                    duration = 20;
                    System.out.println("Infectivity: " + (int) infectivity + "%" + "\nSymptom Severity: " + (int) symptomSeverity + "%" + "\nFatality Rate: " + (int) fatalityRate + "%" + "\nDuration: " + duration + " days");
                    break;
                }
                if (i2 == 4) { // Ebola
                    System.out.println("\nThe epidemiological data for Ebola is:");
                    infectivity = 20;
                    symptomSeverity = 90;
                    fatalityRate = 64;     // wikipedia.it
                    duration = 20;
                    System.out.println("Infectivity: " + (int) infectivity + "%" + "\nSymptom Severity: " + (int) symptomSeverity + "%" + "\nFatality Rate: " + (int) fatalityRate + "%" + "\nDuration: " + duration + " days");
                    break;
                } else {
                    System.out.println("A number between 1 and 4, inclusive, is required!");
                }
            }

            // User-entered virus parameters in case they don't choose the default
            else {

                System.out.println("Creating a custom virus\n");

                // HEALTH ASPECTS
                System.out.println("Infectivity: ");
                infectivity = sc.nextDouble();   // Probability that a healthy individual becomes infected

                System.out.println("Symptom Severity: ");
                symptomSeverity = sc.nextDouble(); // Probability that an infected individual develops symptoms

                System.out.println("Fatality Rate: ");
                fatalityRate = sc.nextDouble();      // Probability that a symptomatic patient dies

                System.out.println("Duration: ");
                duration = sc.nextInt();           // Number of days between the time of infection and recovery
            }
        }

        Virus V = new MyVirus(infectivity, symptomSeverity, fatalityRate, duration);
        MyGovernment Government = new MyGovernment(resources, testCost);
        MyGovernment.Population citizens = new MyGovernment.Population(populationSize, speed);

        while (true) { // Start of simulation
            // User's choice to advance to the next day, isolate or release individuals from isolation, or perform a test on healthy individuals
            System.out.println("\nChoose what to do: \n1 ==> Advance to the next day\n2 ==> Isolate/release a number of individuals\n3 ==> Perform a test");

            int choice = sc.nextInt();

            if (choice == 1) {
                V.progression(citizens);
                System.out.println("_________________________________________________");
                System.out.println("| Day " + (MyVirus.currentDay - 1));
                System.out.println("| Resources: " + MyGovernment.resources);
                System.out.println("| Healthy: " + MyGovernment.Population.greens + ", including " + MyGovernment.greenIsolations + " isolated");
                System.out.println("| Asymptomatic Infected: " + MyGovernment.Population.yellows + ", including " + MyGovernment.yellowIsolations + " isolated");
                System.out.println("| Symptomatic Infected: " + MyGovernment.Population.reds);
                System.out.println("| Recovered: " + MyGovernment.Population.blues);
                System.out.println("| Deaths: " + MyGovernment.Population.blacks);
                System.out.println("_________________________________________________");
            }

            else if (choice == 2) {
                System.out.println("You chose 'isolation'");
                System.out.println("Do you want to start or end isolation?\n1 ==> Start isolation\n2 ==> End isolation");
                int s1 = sc.nextInt();
                if (s1 == 1) {
                    System.out.println("\nEnter the number of healthy people to isolate: ");
                    long s2 = sc.nextLong();
                    Government.isolation(true, s2, 0);
                } else if (s1 == 2) {
                    System.out.println("Enter the number of healthy people to release from isolation: ");
                    long s2 = sc.nextLong();
                    Government.isolation(false, s2, 0);
                }
            } else if (choice == 3) {
                System.out.println("You chose 'Test'");
                System.out.println("Enter the number of people to subject to testing");
                long s1 = sc.nextLong();
                Government.test(s1);
                System.out.println("Healthy: " + MyGovernment.Population.greens + ", including " + MyGovernment.greenIsolations + " isolated");
                System.out.println("Asymptomatic Infected: " + MyGovernment.Population.yellows + ", including " + MyGovernment.yellowIsolations + " isolated");
                System.out.println("Symptomatic Infected: " + MyGovernment.Population.reds);
                System.out.println("Recovered: " + MyGovernment.Population.blues);
                System.out.println("Deaths: " + MyGovernment.Population.blacks + "\n");
            }
            // Bankruptcy condition
            if (MyGovernment.resources <= 0) {
                System.out.println("The state has gone bankrupt");
                break;
            }
            // Human extinction condition
            else if (MyGovernment.Population.blacks == populationSize) {
                System.out.println("The epidemic has won, humanity has become extinct. Press F to pay respect: ");
                String F = sc.next();
                if (F.equals("F")) {
                    System.out.println("You paid respect for humanity! <3");
                } else {
                    System.out.println("Nay");
                }
                break;
            }
            // Simulation victory condition
            else if (MyGovernment.Population.yellows + MyGovernment.Population.reds == 0 && MyGovernment.Population.blues >= 1) {
                System.out.println("Congratulations! The epidemic has been eradicated!");
                break;
            }
        }

        System.out.println("\nWould you like to simulate a new epidemic?\n1 ==> Yes\n2 ==> No");
        int restart = sc.nextInt();
        if (restart == 1) {
            MyGovernment.greenIsolations = 0;
            MyGovernment.yellowIsolations = 0;
            MyVirus.currentDay = 1;
            petuniaPot();
        } else
            System.out.println("\nSee you soon!");
    }
}

