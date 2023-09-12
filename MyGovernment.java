import java.util.Scanner;

public class MyGovernment implements Government {

    // Data on Italian resources available for investing in the coronavirus emergency
    // https://www.agi.it/economia/news/2020-06-03/quanti-soldi-italia-per-emergenza-coronavirus-8800736/
    static long resources;
    // Resources are deducted in case of:
    // 1. Lack of production by idle individuals;
    // 2. Use of tests;
    // 3. Hospitalization of the sick;

    static int cost;  // cost of a test

    static long greenIsolations;
    static long yellowIsolations;
    static boolean isolationStarted = false;


    public MyGovernment(long resources, int cost) {

        // The government's goal is to eradicate the virus, thus trying to reduce
        // the R0 below 1.
        // Its tools are isolation and testing; through these, it is possible to build strategies
        // by combining the two tools over time.

        MyGovernment.resources = resources;
        MyGovernment.cost = cost;

    }

    public static class Population {

        static long greens, yellows, reds, blues, blacks;
        static int speed;
        static long numberOfIndividuals;
        static long peopleInMotion;


        public Population(long numberOfIndividuals, int speed) {

            Population.speed = speed;
            Population.numberOfIndividuals = numberOfIndividuals;

            // At the beginning, the situation is as follows:
            greens = numberOfIndividuals - 1; // All are virus-negative except one person
            yellows = 1;                       // Patient 0 who starts the epidemic
            reds = 0;
            blues = 0;
            blacks = 0;

            peopleInMotion = greens + yellows - greenIsolations - yellowIsolations;

        }
    }


    @Override
    public void isolation(boolean isolated, long numberOfGreenIsolations, long numberOfYellowIsolations) {

        // If "isolated" is true, then the number of isolations (healthy or asymptomatic) is subtracted from the number of people in motion;
        // if "isolated" is false, the input isolations are subtracted from isolation
        // and added to the number of people in motion

        try {
            // If at least one red has appeared
            if (isolationStarted) {
                // Condition for isolating
                if (isolated) {
                    // Check if an attempt is made to isolate a number of available people
                    if (numberOfGreenIsolations <= Population.greens - greenIsolations) {
                        Population.peopleInMotion -= numberOfGreenIsolations;
                        greenIsolations += numberOfGreenIsolations;
                    } else {
                        throw new TooManyLockedDownException();
                    }
                    Population.peopleInMotion -= numberOfYellowIsolations;
                    yellowIsolations += numberOfYellowIsolations;
                } else { // Condition for removing from isolation
                    if (numberOfGreenIsolations <= greenIsolations) {
                        Population.peopleInMotion += numberOfGreenIsolations;
                        greenIsolations -= numberOfGreenIsolations;
                    } else {
                        throw new TooManyOutOfIsolationException();
                    }

                    Population.peopleInMotion += numberOfYellowIsolations;
                    yellowIsolations -= numberOfYellowIsolations;
                }
            } else throw new TooEarlyException();
        } catch (TooEarlyException e) {
            System.err.println("\nNothing can be done until the first symptomatic person appears on the scene to sound the alarm\n");
        } catch (TooManyLockedDownException e) {
            System.err.println("\nIt is not possible to isolate more healthy individuals than there currently are. Currently, there are " + greenIsolations + " healthy individuals in isolation.\n");
        } catch (TooManyOutOfIsolationException e) {
            System.err.println("\nIt is not possible to release more healthy individuals from isolation than have been placed in it. Currently, there are " + greenIsolations + " healthy individuals in isolation.\n");
        }
    }


    @Override
    public void test(long tests) {

        try {
            // If at least one red has appeared
            if (isolationStarted) {

                Scanner sc = new Scanner(System.in);
                long yellowFound = 0;
                long t = tests;

                if (tests <= Population.peopleInMotion) {

                    // Random proportion between greens and yellows, their total must be equal to the number of tests

                    while (t-- > 0) {
                        long r = (long) (Math.random() * Population.peopleInMotion);
                        if (r >= Population.greens - greenIsolations && yellowFound <= Population.yellows) yellowFound += 1;
                    }
                    System.out.println("Out of " + tests + " tests performed, " + yellowFound + " asymptomatic individuals were found");

                    resources -= (tests * cost);
                    // The user chooses whether to isolate the asymptomatic individuals found or not
                    System.out.println("Do you want to isolate the asymptomatic individuals found? [y][n]");
                    if (sc.next().equals("y")) isolation(true, 0, yellowFound);
                } else {
                    System.err.println("\nThe number of tests requested is greater than the number of people who can be tested\n");
                }

            } else throw new TooEarlyException();
        } catch (TooEarlyException e) {
            System.err.println("\nNothing can be done until the first symptomatic person appears on the scene to sound the alarm\n");
        }
    }
}

