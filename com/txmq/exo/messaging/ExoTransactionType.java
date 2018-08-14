package com.txmq.exo.messaging;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * An implementation of sn extensible Enum.  ExoTransactionType is the
 * base transaction type in an Exo application.  Applications should 
 * subclass ExoTransactionType and add the string keys identifying the 
 * transaction types their application supports in its constructor by
 * calling super.initialize(<my transactions>)
 */
public class ExoTransactionType implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3490353271014257619L;

	/**
	 * Identifier for the framework's Acknowledge message
	 */
	public static final int ACKNOWLEDGE = -101;

	/**
	 * Identifier for the transaction nodes issue when they start up
	 */
	public static final int ANNOUNCE_NODE = -102;
	
	/**
	 * Instructs the application to recover its state by re-applying 
	 * transactions from the block logger database.
	 */
	public static final int RECOVER_STATE = - 103;
	
	/**
	 * Instructs the Hashgraph to shut down in a graceful way, ensuring 
	 * blocks are logged to the chain DB before shutting down.
	 */
	public static final int SHUTDOWN = -104;
	
	/**
	 * The list of transaction type identifers supported by the application.  
	 * Think of this list as  list of nn enumerated type's values
	 */
	private static List<Integer> transactionTypes;
	
	/**
	 * Tracks whether this object has been initialized with its values list 
	 * by a superclass.  initialize() is invoked only once, the first time 
	 * an instance of your ExoTransactionType subclass is instantiated.
	 */
	private static Boolean initialized = false;
	
	/**
	 * Public accessor, returns true if the transaction type values have been initialized.
	 */
	public static Boolean getInitialized() {
		return initialized;
	}

	/**
	 * Initializes ExoTransactionType with the list of valid enum values 
	 */
	protected static void initialize(int[] transactionTypes) {
		ExoTransactionType.transactionTypes = new ArrayList<Integer>();
		ExoTransactionType.transactionTypes.add(ACKNOWLEDGE);
		ExoTransactionType.transactionTypes.add(ANNOUNCE_NODE);
		ExoTransactionType.transactionTypes.add(RECOVER_STATE);
		ExoTransactionType.transactionTypes.add(SHUTDOWN);
		
		for (int transactionType : transactionTypes) {
			ExoTransactionType.transactionTypes.add(transactionType);
		}
	}
		
	/**
	 * Returns a clone of the list of transaction types that are supported
	 */
	public ArrayList<Integer> getTransactionTypes() {
		return new ArrayList<>(ExoTransactionType.transactionTypes);
	}
	
	/**
	 * Value of this instance.
	 */
	private int value;

	/**
	 * Accessor for returning the enum value of the transaction type
	 */
	public int getValue() {
		return this.value;
	}
	
	/**
	 * Setter for setting the enum value of the transaction type
	 */
	public void setValue(int value) throws IllegalArgumentException {
		if (ExoTransactionType.transactionTypes.contains(value)) {
			this.value = value;
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * No-arg constructor
	 */
	public ExoTransactionType() {
		
	}
	
	/**
	 * Used to create a transaction type with a knopwn value
	 */
	public ExoTransactionType(int value) {
		this.setValue(value);
	}
	
	/**
	 * Method for Java to use to see if two ExoTransactionType oinstances have the same value.
	 */
	public boolean equals(ExoTransactionType type) {
		return this.value == type.value;
	}
}