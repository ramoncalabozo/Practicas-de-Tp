package tp.pr2.mv;

import tp.pr2.exceptions.ArrayException;
import tp.pr2.inst.Instruction;

public class ParsedProgram {
			
		private  Instruction[] sProgram;
		private int contador;
		private final int SIZE = 1000;
			
			public ParsedProgram(){
				this.contador = 0;
				this.sProgram = new Instruction [SIZE];
			}
		
			public boolean addInsr(Instruction ins) throws ArrayException{
			boolean añadir = false;
			if (contador < SIZE){
			this.sProgram[this.contador] = ins;
			this.contador++;
			añadir = true;
				}
			else{
				throw new ArrayException ("ParsedProgram está lleno");
			}
			return añadir;
			}

		public Instruction getInstruction(int k) {
			return this.sProgram[k];
		}

		public int getSize() {
			return this.contador;
		}

		public void reset() {
			this.contador = 0;
		}

		public String toString() {
			return null;
		}
	}