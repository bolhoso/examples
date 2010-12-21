package io.s4.example.speech01;

import org.apache.log4j.Logger;

import io.s4.persist.Persister;
import io.s4.processor.AbstractPE;

/**
 * This class is responsible for printing the sentence in a different form and
 * for persisting it in a file.
 */
public class LookMaSentenceReceiverPE extends AbstractPE {
	private Persister persister;

    public Persister getPersister() {
		return persister;
	}

	public void setPersister(Persister persister) {
		this.persister = persister;
	}

	public void processEvent(Sentence sentence) {
    	System.out.printf ("\n====\nLook ma, I've dispatched an event: %s\n====\n\n", sentence.getText());
    	
    	try {
    		persister.set(Long.toString (sentence.getSpeechId()), sentence, 1000);
    	} catch (InterruptedException e) {
    		Logger.getLogger("s4").error("Interrupted exception received when persisting: " + e.toString());
    	}
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
