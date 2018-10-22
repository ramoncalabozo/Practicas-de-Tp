package tp.pr1.mv;

public enum ENUM_BYTECODE {
	PUSH(1), STORE(1), LOAD(1), ADD, SUB, MUL, DIV, OUT, HALT;
	private int param;

	ENUM_BYTECODE(int param) {
		this.param = param;
	}

	ENUM_BYTECODE() {
		this.param = 0;
	}

	public int getparam() {
		return this.param;
	}
}
