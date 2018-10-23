package tp.pr2.bc;



import tp.pr2.bc.Arithmetics.Add;
import tp.pr2.bc.Arithmetics.Div;
import tp.pr2.bc.Arithmetics.Mul;
import tp.pr2.bc.Arithmetics.Sub;
import tp.pr2.bc.Jumps.Goto;
import tp.pr2.bc.Jumps.conditionalJumps.IfEq;
import tp.pr2.bc.Jumps.conditionalJumps.IfLe;
import tp.pr2.bc.Jumps.conditionalJumps.IfLeq;
import tp.pr2.bc.Jumps.conditionalJumps.ifNeq;

/**
 * tiene como atributo un array de los bytecodes
 * @author salva
 *
 */
	public abstract class ByteCodeParser implements ByteCode {
		private final static ByteCode[] bytecodes =
			{new Add(), new Sub(),
			 new Mul(), new Div(),
			 new Push(0), new Store(0), new Load(0),
			 new Halt(), new Out(), new Goto(0),
			 new IfEq(0), new ifNeq(0),
			 new IfLe(0), new IfLeq(0)
			 };
		
		/**
		 * parsea la parte comun de los bytecodes, si tiene entre 1 y 2 de longitus, llama a su parseador particular
		 * @param line
		 * @return
		 */
		public static ByteCode parse(String line) {
			line = line.trim();
			String[] arrayLine = line.split(" +");
			 if (arrayLine.length < 1 || arrayLine.length > 2) return null;
			 ByteCode ins = null;
			 for (ByteCode bc:bytecodes) {
			ins=bc.parse(arrayLine);
			if (ins!=null) return ins;
			 }
			 return ins;
			}
		
}