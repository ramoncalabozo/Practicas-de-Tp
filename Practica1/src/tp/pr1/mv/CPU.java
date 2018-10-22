package tp.pr1.mv;

public class CPU {
	private Memory memoria;
	private OperandStack pila;
	private boolean halt;

	public CPU() {
		this.halt = false;// no se ha ejecutado todav�a la instruccion Halt
		this.memoria = new Memory(1000);
		this.pila = new OperandStack();
	}

	public boolean execute(ByteCode instr) {
		boolean execute = true;
		ENUM_BYTECODE name;
		int param;
		param = instr.getparam();//coge el prametro de la instrucci�n
		name = instr.getname(); // coge el enumerado de la instrucci�n
		switch (name) {

		case PUSH:
			 this.pila.a�adirElementos(param);
			break;

		case LOAD:
			if (CPU.this.memoria.estavacia()) {
				execute = false;
			} else {
				if (param > this.memoria.getSize()){
					execute = false;
				}
				else {
				int numMemory = memoria.read(param);
				pila.a�adirElementos(numMemory);
				}
			}
			break;

		case STORE:
			if (CPU.this.pila.estavaciapila()) {
				execute = false;
			} else {
				int numPila = pila.getcima();
				memoria.write(param, numPila);
			}
			break;

		case ADD:
			int cimaAdd,
			subcimaAdd,
			resultadoAdd;
			if (CPU.this.pila.estavaciapila()) {
				execute = false;
			} else {
				cimaAdd = pila.getcima();
				if (CPU.this.pila.estavaciapila()) {
					execute = false;// Si no se puede realizar la operacion se
									// mete en pila otravez el elmento que se
									// acaba de sacar
					CPU.this.pila.a�adirElementos(cimaAdd);
				} else {
					subcimaAdd = pila.getcima();
					resultadoAdd = subcimaAdd + cimaAdd;
					execute = pila.a�adirElementos(resultadoAdd);
				}
			}
			break;

		case SUB:
			int cimaSub,
			subcimaSub,
			resultadoSub;
			if (CPU.this.pila.estavaciapila()) {
				execute = false;
			} else {
				cimaSub = pila.getcima();
				if (CPU.this.pila.estavaciapila()) {
					execute = false;// Si no se puede realizar la operacion se
					// mete en pila otravez el elmento que se
					// acaba de sacar
					CPU.this.pila.a�adirElementos(cimaSub);
				} else {
					subcimaSub = pila.getcima();
					resultadoSub = subcimaSub - cimaSub;
					execute = pila.a�adirElementos(resultadoSub);
				}
			}
			break;

		case MUL:
			int cimaMul,
			subcimaMul,
			resultadoMul;
			if (CPU.this.pila.estavaciapila()) {
				execute = false;
			} else {
				cimaMul = pila.getcima();
				if (CPU.this.pila.estavaciapila()) {
					execute = false;// Si no se puede realizar la operacion se
					// mete en pila otravez el elmento que se
					// acaba de sacar
					CPU.this.pila.a�adirElementos(cimaMul);
				} else {
					subcimaMul = pila.getcima();
					resultadoMul = cimaMul * subcimaMul;
					execute = pila.a�adirElementos(resultadoMul);
				}
			}

			break;

		case DIV:
			int cimaDiv,
			subcimaDiv,
			resultadoDiv;
			if (CPU.this.pila.estavaciapila()) {
				execute = false;
			} else {
				cimaDiv = pila.getcima();
				if (CPU.this.pila.estavaciapila() || cimaDiv == 0) {
					execute = false;// Si no se puede realizar la operacion se
					// mete en pila otravez el elmento que se
					// acaba de sacar
					CPU.this.pila.a�adirElementos(cimaDiv);
				} else {
					subcimaDiv = pila.getcima();
					resultadoDiv = subcimaDiv / cimaDiv;
					execute = pila.a�adirElementos(resultadoDiv);
				}
			}
			break;
		
		
		case OUT:
			// pasarlo a caracter y ense�arlo por pantalla
			if(!this.pila.estavaciapila()){
			char c = (char) this.pila.getcima();
			System.out.println("El caracter que hab�a en la cima de la pila es :" + c);
			}
			else execute = false;
			break;
		
		
		case HALT:
			this.halt = true;// para la m�quina virtual
			break;
		}
		return execute;
	}

	
	
	public boolean getHalt() {
		return this.halt;
	}//devuelve halt

	
	
	public String toString() {
		String s;
		s = "Estado de la CPU " + System.lineSeparator();;
		s = s + this.memoria.toString();
		s = s + this.pila.toString();
		return s;
	}
}