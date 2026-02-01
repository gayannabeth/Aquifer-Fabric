package gay.mountainspring.aquifer.util;

public enum BooleanPair {
	FALSEFALSE(false, false),
	FALSETRUE(false, true),
	TRUEFALSE(true, false),
	TRUETRUE(true, true);
	
	private final boolean first, second;
	
	private BooleanPair(boolean first, boolean second) {
		this.first = first;
		this.second = second;
	}
	
	public boolean first() {
		return this.first;
	}
	
	public boolean second() {
		return this.second;
	}
	
	@Override
	public String toString() {
		return this.first + ", " + this.second;
	}
	
	public static BooleanPair of(boolean first, boolean second) {
		return first ? second ? TRUETRUE : TRUEFALSE : second ? FALSETRUE : FALSEFALSE;
	}
}