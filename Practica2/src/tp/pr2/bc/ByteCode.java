package tp.pr2.bc;
// Est� clase ya est� hecha
import tp.pr2.cpu.CPU;

/**
 * clase abstracta
 * @author salva
 *
 */
abstract public class ByteCode {
	abstract public boolean execute(CPU cpu);
	abstract public ByteCode parse(String[] s);
}
