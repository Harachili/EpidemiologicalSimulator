public interface Government {

    public void isolation(boolean isolate, long numberOfGreenIsolations, long numberOfYellowIsolations);                                
    // Tool 1: Isolation reduces the number of contacts for each person
    // It decreases the "speed" parameter
    // Modifies the "peopleInMotion" parameter, initially equal to the population and changes over time

    public void test(long tests);                   
    // Tool 2: Testing reduces resources based on the cost (C * people)
    // Finds and outputs additional "x" infected individuals
}

