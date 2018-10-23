package tp.pr2.bc;
import tp.pr2.elements.CPU;




public class Halt implements ByteCode  {

	@Override
	/**
	 * llama  next y finish de cpu
	 */
	public boolean execute(CPU cpu) {
		cpu.next();
		return cpu.finish();
	}

	@Override
	/**
	 * parsea el string s, si es longitud 1 y es halt, crea el bytecode
	 */
	public ByteCode parse(String[] s) {
		if (s.length !=1){
		return null;
		}
		else{
			if (s[0].equalsIgnoreCase("HALT")){
				return new Halt();
			}
			else{
				return null;
			}
		}
	}
public String toString (){
	return "Halt";
}
}

