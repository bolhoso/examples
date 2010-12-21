package io.s4.example.speech01;

import io.s4.dispatcher.Dispatcher;
import io.s4.processor.AbstractPE;

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
	
    public void processEvent(Sentence sentence) {
    	System.out.printf ("Id: %d\nSpeechid: %d\nText:%s\nTime: %d\nLocaltion: %s\n",
    			sentence.getId(), sentence.getSpeechId(), sentence.getText(),
    			sentence.getTime(), sentence.getLocation());
    	
        dispatcher.dispatchEvent(outputStreamName, sentence);

    }
    
    @Override
    public void output() {
        // not called in this example
    }

    @Override
    public String getId() {
        return this.getClass().getName();
    }

}
