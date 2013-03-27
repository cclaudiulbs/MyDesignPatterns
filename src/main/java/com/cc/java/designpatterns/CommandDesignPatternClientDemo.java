package com.cc.java.designpatterns;

import java.util.Arrays;
import java.util.List;

/**
 * @author cclaudiu
 * <br/>
 * <br/>
 * ICommand Design Pattern in Action with a very simple and concise example
 */

// -- define the ICommand entry point for the ICommand Design Pattern
interface ICommand {
    void execute();
}

// -- define the commands to be used
class ShutdownCommand implements ICommand {
    private final Devices device;

    // -- the command knows only about the possible receiver which should define how this command gets executed
    public ShutdownCommand(Devices device) {
        this.device = device;
    }

    @Override public void execute() {
        device.onStop();
    }
}

class StartupCommand implements ICommand {
    private final Devices device;

    public StartupCommand(Devices device) {
        this.device = device;
    }

    @Override public void execute() {
        device.onStart();
    }
}

// -- the actual devices/receivers of the ICommand, for simplicity only two commands are defined
interface Devices {
    void onStart();
    void onStop();
}

// -- the devices which know operate on received commands
class RadioDevice implements Devices {
    @Override public void onStart() {
        System.out.println("Radio is started");
    }

    @Override public void onStop() {
        System.out.println("Radio is stopped");
    }
}

// -- the devices which know operate on received commands
class TVDevice implements Devices {
    @Override public void onStart() {
        System.out.println("TV is started");
    }

    @Override public void onStop() {
        System.out.println("TV is stopped");
    }
}

// -- this is not mandatory, gets the actual active device which is one of the two defined devices
final class GetActiveDeviceFactory {
    private GetActiveDeviceFactory() { }

    // some complex business logic to get the active and most prior device for which the command is fired
    public static Devices getActiveDevice(List<Devices> possibleActiveDevices) {
        return possibleActiveDevices.isEmpty() ? new RadioDevice() : possibleActiveDevices.get(0);
    }
}

// -- the actual invoker of the command, the invoker "knows" only to "invoke"(has) a ICommand in its aggregation
class CommandTriggerButton {
    private final ICommand command;

    public CommandTriggerButton(ICommand command) {

        this.command = command;
    }

    public void click() {
        command.execute();
    }
}

public class CommandDesignPatternClientDemo {

    public static void main(String[] args) {
        Devices currentActiveDevice = GetActiveDeviceFactory
                .getActiveDevice(Arrays.asList(new RadioDevice(), new TVDevice()));

        ICommand startCommand = new StartupCommand(currentActiveDevice);
        ICommand stopCommand = new ShutdownCommand(currentActiveDevice);

        CommandTriggerButton startButton = new CommandTriggerButton(startCommand);
        CommandTriggerButton stopButton = new CommandTriggerButton(stopCommand);

        startButton.click();
        stopButton.click();
    }
}