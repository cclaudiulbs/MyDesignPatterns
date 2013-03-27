package com.cc.java.designpatterns;

/**
 * @author cclaudiu
 * 
 *         The Bridge pattern should be used when the class and its responsabilities vary a lot It is similar to the
 *         Adapter Pattern, except that the interface defining the implementation is hidden from the end-api-client; In
 *         the original GoF the Bridge Design pattern says:
 *         "Decouple an abstraction from its implementation, so that the two can vary independently"
 * 
 *         The structure is something like:
 *         Abstraction<-----has a-------- Implementation Interface
 *              ^                                   ^
 *              |                                   |
 *              |                                   |
 *          Specific Abstraction            +-------+------+
 *                                        Concrete 1| Concrete 2
 * 
 *         The Abstraction defines the abstraction and maintains a reference alive to the Implementation Interface
 *         Spefic Abstraction defines an extension to the abstraction; The client uses the specific abstraction, so that
 *         the implementation is hidden away from him;
 * 
 *         We'll take the TV implementation and RemoteControl abstraction example
 */

interface TV {
	void on();
	void off();
	void tuneChannel(int channel);
    int getChannel();
}

final class Sony implements TV {
    private int channel;

	@Override
	public void on() {
        System.out.println("Turning TV on!");
    }

	@Override
	public void off() {
        System.out.println("Turning TV off!");
	}

	@Override
	public void tuneChannel(int channel) {
        System.out.println("Changing channel to=" + channel);
        this.channel = channel;
    }

    @Override
    public int getChannel() {
        return channel;
    }
}

// ----- Now provide the abstraction of the TV, which stands for the RemoteControl Abstraction
// ----- has a reference to the TV implementation
abstract class RemoteControl {

	private final TV television;

	protected RemoteControl(TV television) {
		this.television = television;
	}

	protected void turnOn() {
		television.on();
	}

	protected void turnOff() {
		television.off();
	}

	protected void changeChannel(int channel) {
		television.tuneChannel(channel);
	}
}

// ----- Now provide a more Specific Implementation for the RemoteControl Abstraction
final class SonyRemoteControl extends RemoteControl {

    private final TV television;
    private int channel;

	public SonyRemoteControl(TV television) {
		super(television);

        this.television = television;
    }

	public void nextChannel() {
        this.channel = television.getChannel();
        changeChannel(++channel);
	}

    public void previousChannel() {
        this.channel = television.getChannel();
        changeChannel(--channel);
    }
}

public class BridgeDesignPatternDemoClient {
    public static void main(String[] args) {
        TV sonyTV = new Sony();

        SonyRemoteControl sonyRemote = new SonyRemoteControl(sonyTV);

        sonyRemote.turnOn();
        sonyRemote.changeChannel(2);
        sonyRemote.nextChannel();
        sonyRemote.previousChannel();
        sonyRemote.turnOff();
    }
}

//Turning TV on!
//Changing channel to=2
//Changing channel to=3
//Changing channel to=2
//Turning TV off!