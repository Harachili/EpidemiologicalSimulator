public class MyVirus implements Virus {

    // Virus data
    static int currentDay = 1;
    final int duration;
    final double infectivity;
    final double symptomSeverity;
    final double fatalityRate;

    public MyVirus(double infectivity, double symptomSeverity, double fatalityRate, int duration) {

        this.duration = duration;
        this.infectivity = infectivity;
        this.fatalityRate = fatalityRate;
        this.symptomSeverity = symptomSeverity;
    }

    // New infections are the moving yellows multiplied by R0 and by a variable double "infectable" that ranges from 0 to 1 and changes based on the number of greens
    public long newInfections(double R0, double infectable) {
        return Math.min((long) Math.ceil(R0 * (MyGovernment.Population.yellows - MyGovernment.yellowIsolations) * infectable), MyGovernment.Population.greens - MyGovernment.greenIsolations);
    }

    // New symptomatic cases are the yellows multiplied by the symptom severity in percentage! x: symptom severity = yellows: 100
    public long newSymptomaticCases() {
        return (long) ((symptomSeverity / 100) * MyGovernment.Population.yellows);
    }

    // New deaths are the reds multiplied by the fatality rate in percentage! x: fatality rate = reds: 100
    public long newDeaths() {
        return (long) ((fatalityRate / 100) * MyGovernment.Population.reds);
    }

    // New recovered yellows are all current yellows minus the new symptomatic cases
    public long newRecoveredYellows(long newSymptomaticCases) {
        return (currentDay > duration / 6) ? MyGovernment.Population.yellows - newSymptomaticCases : 0;
    }

    // New recovered reds are all current reds minus the new deaths
    public long newRecoveredReds(long newDeaths) {
        return (currentDay > duration / 6) ? MyGovernment.Population.reds - newDeaths : 0;
    }

    // This method shows the virus progression day by day
    @Override
    public void progression(MyGovernment.Population p) {

        if (currentDay > duration / 6) {
            // People in motion are the greens plus the yellows, minus the isolations of both
            MyGovernment.Population.peopleInMotion = MyGovernment.Population.greens + MyGovernment.Population.yellows - MyGovernment.greenIsolations - MyGovernment.yellowIsolations;

            // Dynamic speed = speed : population = x : people in motion
            double dynamicSpeed = (double) (MyGovernment.Population.speed * MyGovernment.Population.peopleInMotion) / MyGovernment.Population.numberOfIndividuals;

            // Contagiousness factor
            double R0 = dynamicSpeed * (infectivity / 100);  // Not multiplying by the duration to get daily contagion rate

            // Infectable --> population : 100 = p.greens : x
            double infectable = (double) (MyGovernment.Population.greens - MyGovernment.greenIsolations) / MyGovernment.Population.numberOfIndividuals;
            // An index that ranges from 1 (when greens are equal to the entire population) to 0 (when greens are = 0)

            long newInfections = newInfections(R0, infectable);
            long newSymptomaticCases = newSymptomaticCases();
            long newDeaths = newDeaths();
            long newRecoveredYellows = newRecoveredYellows(newSymptomaticCases);
            long newRecoveredReds = newRecoveredReds(newDeaths);

            // Update healthy individuals
            MyGovernment.Population.greens = MyGovernment.Population.greens - newInfections;

            // Update asymptomatic individuals
            MyGovernment.Population.yellows = MyGovernment.Population.yellows + newInfections - newSymptomaticCases - newRecoveredYellows;

            // Update symptomatic individuals
            MyGovernment.Population.reds = MyGovernment.Population.reds + newSymptomaticCases - newDeaths - newRecoveredReds;

            // Update deceased individuals
            MyGovernment.Population.blacks = MyGovernment.Population.blacks + newDeaths;

            // Update recovered individuals
            MyGovernment.Population.blues = MyGovernment.Population.blues + newRecoveredReds + newRecoveredYellows;

            // Since yellows decrease by becoming red or blue over time, the yellow isolations must also decrease (they cannot be greater than yellows)
            MyGovernment.yellowIsolations = Math.min(MyGovernment.Population.yellows, MyGovernment.yellowIsolations);
            // Update the total population
            MyGovernment.Population.numberOfIndividuals -= newDeaths;
            // Activate the boolean that allows testing and isolation
            if (MyGovernment.Population.reds > 0) MyGovernment.isolationStarted = true;

            if (currentDay > duration && MyGovernment.Population.yellows == 1) {
                MyGovernment.Population.yellows--;
                MyGovernment.Population.blues++;
            }

            currentDay += 3;
        } else currentDay += 3;

        MyGovernment.resources -= (MyGovernment.greenIsolations + MyGovernment.yellowIsolations + MyGovernment.Population.reds * 3 * MyGovernment.cost);

    }
}

