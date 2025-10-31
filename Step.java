class Step {
    Station station;// Current station
    String lineUsed; // Line used to get to this point
    int numChanges;  // line changes so far
    double totalTime; // Total time so far
    Step previousStep;// previous step for backtracking

    public Step(Station s, String line, int changes, double time, Step previous) {// to keep track of the route
        station = s;
        lineUsed = line;
        numChanges = changes;
        totalTime = time;
        previousStep = previous;
    }
}
