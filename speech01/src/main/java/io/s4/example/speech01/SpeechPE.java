package io.s4.example.speech01;

import io.s4.persist.Persister;
import io.s4.processor.AbstractPE;

import org.apache.log4j.Logger;

/**
 * This class is responsible for aggregating the a speech using 
 * speechId and then persist it when it's ready.
 */
public class SpeechPE extends AbstractPE {
	private Persister persister;

	private long speechKey;
	private String speechCompleteText = "";
	
	@Override
	public String getId() {
		return Long.toString(this.speechKey);
	}

	@Override
	public void output() {
		try {
			this.persister.set(Long.toString (this.speechKey), this.getSpeechSentence(), 1000);
		} catch (InterruptedException e) {
			Logger.getLogger("s4").error("Interrupted exception while writing " + Long.toString (this.speechKey));
			e.printStackTrace();
		}
	}

	public void setPersister(Persister persister) {
		this.persister = persister;
	}

	public Persister getPersister() {
		return persister;
	}
	
	/**
	 * Put the data of the whole speech together according to idSpeech
	 * 
	 * @param sentence The sentence said
	 */
	// TODO: Can I have another processEvent to Speech, so a PE can receive 
	// TODO: events from two different sources?
	public void processEvent (Sentence sentence) {
	    System.out.println("Arrived sentence at SpeechPE! " + sentence.getSpeechId() + " -> " + sentence.getId());
		this.speechKey = sentence.getSpeechId();
		
		this.addSpeechSentence (sentence.getText());
		Logger.getLogger("s4").info ("Processed sentence " + sentence.getId() + 
				" from speech " + this.speechKey);
	}
	
	/**
	 * Adds a sentence to the speech being processed
	 * 
	 * @param sentence The sentence stated in the speech
	 */
	// TODO: Do I protect speechCompleteText against concurrent access this way?
	public synchronized void addSpeechSentence (String sentence) {
		synchronized (this.speechCompleteText) {
			this.speechCompleteText += sentence;			
		}
	}
	
	/**
	 * Get the complete speech text read until now.
	 * 
	 * @return String containing the full speech text.
	 */
	public String getSpeechSentence() {
		synchronized (this.speechCompleteText) {
			return this.speechCompleteText;
		}
	}
}
 