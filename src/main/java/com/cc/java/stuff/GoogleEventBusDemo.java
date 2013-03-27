package com.cc.java.stuff;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author cclaudiu
 *
 */

// -- The Listener is fully decoupled from the standard Java Interface Implementer approach
// -- must have an annotated with @Subscribe method, which declares to receive ONLY one parameter
// -- which is the exact Type of the Event( could be a String, an int, anything)
// -- MultiListeners functionality is possible in google guava EventBus, by declaring as many methods
// -- as we want, for different Argument Types, marked with @Subscribe annotation
// -- hence we can post many Event Types, and our Multi Listener will intercept them
// -- One limitation is the the EventBus is not accepting primitives as EventTypes
class MyListener {
    private String lastEventMessage;

    @Subscribe
    public void listen(MyEvent myEvent) {
        lastEventMessage = myEvent.getEventDescription();
    }

    public String getLastEventMessage() {
        return lastEventMessage;
    }
}

class MyEvent {
    private final String eventDescription;

    public MyEvent(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getEventDescription() {
        return eventDescription;
    }
}

class MyMultiListener {
    private String lastEventMessage;
    private Integer lastEventCode;

    @Subscribe public void interceptLastMessage(String eventMessage) {
        this.lastEventMessage = eventMessage;
    }

    @Subscribe public void interceptLastCode(Integer code) {
        this.lastEventCode = code;
    }

    public String getLastEventMessage() {
        return lastEventMessage;
    }

    public Integer getLastEventCode() {
        return lastEventCode;
    }
}

public class GoogleEventBusDemo {

    @Test public void myListener_should_receive_event_posted_from_eventbus() {
        final String eventMessage = "This should be received as in memory message by MyListener";

        final EventBus eventBus = new EventBus("Google Guava EventBus Demo");
        final MyEvent myEvent = new MyEvent(eventMessage);
        final MyListener myListener = new MyListener();

        eventBus.register(myListener);

        eventBus.post(myEvent);

        assertThat(myListener.getLastEventMessage(), is(eventMessage));
    }

    @Test public void myMultiListener_should_listen_to_as_many_event_types_as_we_want() {
        final String eventMessage = "This should be received as in memory message by MyListener";
        final Integer eventCode = 503;

        final EventBus eventBus = new EventBus("Google Guava EventBus Demo2");
        final MyMultiListener multiListener = new MyMultiListener();

        // -- subscribe the Listener
        eventBus.register(multiListener);

        // -- post the EventTypes, which the listener subscribed to receive
        eventBus.post(eventMessage);
        eventBus.post(eventCode);

        assertThat(multiListener.getLastEventMessage(), is(eventMessage));
        assertThat(multiListener.getLastEventCode(), is(eventCode));
    }
}