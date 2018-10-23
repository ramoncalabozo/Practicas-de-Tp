package tp.pr2.inst.assignments;

import tp.pr2.bc.ByteCode;
import tp.pr2.bc.Push;

public class Number implements Term {
		private int numero;
			public Number (int numero){
				this.numero = numero;
			}

	@Override
	public Term parse(String term) {
		int auxiliar;
		try{
			auxiliar = Integer.valueOf(term);
			return new Number(Integer.valueOf(auxiliar));
			}
			catch (NumberFormatException e){
			throw new NumberFormatException (term + " no es un número");
			}
	}

	@Override
	public ByteCode compile(tp.pr2.elements.Compiler compiler) {
		return new Push (this.numero);
	}
}