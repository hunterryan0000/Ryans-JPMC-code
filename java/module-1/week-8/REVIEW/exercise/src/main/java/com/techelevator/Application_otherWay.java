package com.techelevator;

import com.techelevator.util.SystemInOutConsole;

public class Application_otherWay {

    public static void main(String[] args) {
        SystemInOutConsole systemInOutConsole = new SystemInOutConsole();
        WordSearchController_otherway controller = new WordSearchController_otherway(systemInOutConsole);
        controller.run();
    }
}
