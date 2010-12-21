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
    	
    	Sentence sent2 = new Sentence();
    	sent2.setId(sentence.getId());
    	sent2.setText ("Look, ma, I can dispatch events: " + sentence.getText());
    	sent2.setTime (sentence.getTime());
    	
    	if (this.dispatcher != null)
    		System.out.println("Dispatcher is not null");
    	
    	System.out.println("Out stream name: " + this.outputStreamName);
    	
//        dispatcher.dispatchEvent(outputStreamName, sent2);
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
