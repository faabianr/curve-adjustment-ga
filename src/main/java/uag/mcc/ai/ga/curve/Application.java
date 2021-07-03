package uag.mcc.ai.ga.curve;

import uag.mcc.ai.ga.curve.service.ChartService;
import uag.mcc.ai.ga.curve.service.GAService;

public class Application {

    public static void main(String[] args) {
        GAService gaService = new GAService(new ChartService());
        gaService.startSimulation();
    }

}
