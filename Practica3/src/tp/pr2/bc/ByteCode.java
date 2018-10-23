package tp.pr2.bc;
import tp.pr2.elements.CPU;
import tp.pr2.exceptions.DivByZeroException;
import tp.pr2.exceptions.StackException;




/**
 * Interfaz
 * @author salva
 *
 */
	public interface ByteCode{
	boolean execute(CPU cpu)throws DivByZeroException,StackException;// Ten�a un bool pero en el javadoc del profe pon�a un void y lo he cambiado
	ByteCode parse(String[] s);
}
