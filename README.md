# EpidemiologicalSimulator

The following Epidemiological Simulator has been written in Java in collaboration with [morebaconstrips](https://github.com/morebaconstrips).

It is a project that originated as a home exercise for the `Programming 2` exam. It provides basic viruses (Covid 19, Plague, HIV, and Ebola) and default states (Italy, UK, USA, China) in which to attempt virus management. However, you can also input custom state and virus data.

Each state has four values to input:

    Number of individuals
    Quantity of resources
    Cost of a test
    Daily contacts per person

Each virus also has four values to input:

    Infectivity (a value from 0 to 100 indicating the likelihood of a contact between an infected person and a healthy person generating an infection)
    Symptomaticity (a value from 0 to 100 indicating the probability that an infected person will develop symptoms)
    Lethality (a value from 0 to 100 indicating the probability that a symptomatic patient will die)
    Duration (the number of days between the moment of infection and recovery)

The simulator lacks a GUI but can be run from the terminal to observe the virus's progression. You can decide day by day whether to:

    Advance to the next day
    Isolate/remove a number of individuals from isolation
    Perform a test

Isolation and testing can only be executed once the first symptomatic case has been identified.

In general, isolation reduces the number of contacts for each person. You can decide how many people to put in isolation.

Testing, on the other hand, depletes the country's resources and identifies additional infected cases as output. The number of people to test must be manually entered.

If the number of infected and symptomatic cases reaches 0, and the population is not completely extinct, you win. Be cautious of the country going bankrupt and the extinction of humanity! Have fun!
