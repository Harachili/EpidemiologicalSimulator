public interface Virus {
    /*
    Population:

    - Green (Susceptible)
    S(n+1) = S(n) - γI(n)*S(n) // Green minus new infections

    - Yellow (Asymptomatic Infected)
    I(n+1) = I(n) + γI(n)*S(n) - qI(n) - S*I(n) // Previous yellows plus new yellows minus (yellows becoming blue and red)

    - Red (Symptomatic Infected)
    M(n+1) = M(n) + S*I(n) - L*M(n) - q*M(n) // Previous reds plus new reds minus deaths and recoveries

    - Blue (Recovered)
    B(n+1) = B(n) + q*I(n) + q*M(n) // Previous blues plus new blues

    - Black (Deceased)
    R(n+1) = R(n) + L*M(n) // Previous deaths plus new deaths

    In the Virus interface, we initialize an advancement method that allows us
    to apply the formulas above to see how the population parameters change
    over days based on different strategies.
    If the user continues without modifying any parameters, the virus will keep
    spreading day by day without encountering obstacles.
    */
    public void progression(MyGovernment.Population p);
}

