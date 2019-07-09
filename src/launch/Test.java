package launch;

import logic.And;
import logic.Biconditional;
import logic.Conditional;
import logic.Expression;
import logic.Not;
import logic.Or;
import logic.Variable;

public class Test {
    
    public static void main(String[] args) {
                
        
        Expression e = new Not(new Conditional(new Or(new Variable("x"), new Variable("y"), new Variable("z")),
                new Biconditional(new And(new Variable("a"), new Or(new Variable("b"), new Variable("c"))), new Variable("d"))));
        
        System.out.println(e);
        System.out.println(e.toCNF());
    }
}