package tp.pr2.inst.conditionals;

import tp.pr2.elements.LexicalParser;;

public class ConditionalParser {
	private final static Condition[] conditions = {
			new Less( )
			,new LessEq()
			,new Equal()
			,new NotEqual()
	};
	
	public static Condition parse(String t1,String op,String t2,LexicalParser parser) {
		 Condition condParseada = null;
		 for (Condition condit:conditions) {
		condParseada = condit.parse(t1, op, t2, parser);
		if (condParseada!=null) return condParseada;
		}
		 return null;
		}
}
