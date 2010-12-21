package io.s4.example.speech01;

import org.apache.log4j.Logger;

import io.s4.dispatcher.Dispatcher;
import io.s4.processor.AbstractPE;

/**
 * Log a sentence that entered and dispatch it to the Speech Processing Element
 */
public class SentenceReceiverPE extends AbstractPE {

    private Dispatcher dispatcher;
	private String outputStreamName;
	
    public Dispatcher getDispatcher() {
        return dispatcher;
    }

    public void setDispatcher(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    public String getOutputStreamName() {
        return outputStreamName;
    }

    public void setOutputStreamName(String outputStreamName) {
        this.outputStreamName = outputStreamName;
    }
	
    /**
     * Log the sentence into the standard output and then emit an event to the SpeechPE
     * 
     * @param sentence The sentence entering the system
     */
    public void processEvent(Sentence sentence) {
    	System.out.printf ("Id: %d\nSpeechid: %d\nText:%s\nTime: %d\nLocaltion: %s\n\n",
    			sentence.getId(), sentence.getSpeechId(), sentence.getText(),
    			sentence.getTime(), sentence.getLocation());
    	
    	Logger.getLogger("s4").info("Dispatching event for speech " + sentence.getSpeechId());
    	
        dispatcher.dispatchEvent(outputStreamName, sentence);
    }
    
    @Override
    public void output() {
    }

    @Override
    public String getId() {
        return this.getClass().getName();
    }

}
