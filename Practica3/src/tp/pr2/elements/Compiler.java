package tp.pr2.elements;

import tp.pr2.bc.ByteCode;
import tp.pr2.exceptions.ArrayException;
import tp.pr2.inst.Instruction;
import tp.pr2.mv.ByteCodeProgram;
import tp.pr2.mv.ParsedProgram;



public class Compiler {
	private ByteCodeProgram bcProgram;
	private String[] varTable;
	private int numVariables;

	public Compiler(ByteCodeProgram bcProgram) {
		this.bcProgram = bcProgram ;// Programa de ByteCodes
		this.numVariables = 0; // Número de variables introducidas
		this.varTable = new String [1000]; //Array de variables
	}

	public void compile(ParsedProgram pProgram)throws ArrayException {
		int i = 0;
		while (i < pProgram.getSize()) {
			Instruction inst = pProgram.getInstruction(i);
			inst.compile(this);
			i++;
		}
	}

	
	public boolean addByteCode(ByteCode bc)throws ArrayException {
		return this.bcProgram.añadirenpos(this.bcProgram.getContador(), bc);
	}

	public void addVariable(String VarName) {
		this.varTable[this.numVariables] = VarName;
		this.numVariables++;
	}

	public int getSizeBcProgram() {
		return this.bcProgram.getContador();
	}

	public int indexOf(String varName) {
		boolean encontrada= false;
		int contador = 0;
		String comparador;
		while (contador < this.numVariables && !encontrada){
			comparador = this.varTable[contador];
			if (comparador.equalsIgnoreCase(varName)){
				encontrada = true;
			}
			else {
				contador++;
			}
		}
			if (encontrada){
				return contador;
		}
			else {
				return -1;
			}
	}

	void initialize(ByteCodeProgram bcProgram) {
		this.numVariables = 0;
		this.bcProgram.inicializar();
	}

	public String toString() {
		return null;
	}
}