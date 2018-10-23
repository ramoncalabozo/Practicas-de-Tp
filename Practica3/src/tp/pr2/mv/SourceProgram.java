package tp.pr2.mv;

import tp.pr2.exceptions.ArrayException;

public class SourceProgram {
		
	private  String[] sProgram;
	private int contador;
	private final int SIZE = 1000;
		
		public SourceProgram(){
			this.contador = 0;
			this.sProgram = new String [SIZE];
		}
	
	public void addInsr(String ins)throws ArrayException {
		if (contador < SIZE){
		this.sProgram[this.contador] = ins;
		this.contador++;
			}
		else{
		throw new ArrayException ("Programa fuente está lleno"); 
			}
		}

	public String getInstruction(int k) {
		return this.sProgram[k];
	}

	public int getSize() {
		return this.contador;
	}

	public void reset() {
		this.contador = 0;
	}

	public String toString() {
		String s;
		s = "";
		if (this.contador > 0){
		s = "Programa almacenado:"+System.lineSeparator();;
		for (int i = 0; i < this.contador; i++) {
			s = s + i + ": ";
			s = s + this.sProgram[i].toString();
			s = s + System.lineSeparator();
		}
		}
		
		return s;
	}
}