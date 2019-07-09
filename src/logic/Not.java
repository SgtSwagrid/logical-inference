package logic;

import static java.util.stream.Collectors.*;

public class Not extends Expression {
    
    private Expression expression;
    
    public Not(Expression expression) {
        this.expression = expression;
    }
    
    public Expression getExpression() { return expression; }
    
    @Override
    public And toCNF() {
        
        if(expression instanceof Variable) return new And(this);
        if(expression instanceof Not) return ((Not)expression).getExpression().toCNF();
        
        And cnf = expression.toCNF();
        
        if(cnf.getConjuncts().size() > 1) {
            
            return new Or(cnf.getConjuncts().stream()
                .map(Not::new)
                .collect(toList())).toCNF();
            
        } else if(cnf.getConjuncts().size() == 1) {
            
            if(cnf.getConjuncts().get(0) instanceof Or) {
                
                return new And(((Or)cnf.getConjuncts().get(0)).getDisjuncts().stream()
                    .map(Not::new)
                    .collect(toList())).toCNF();
                
            } else if(cnf.getConjuncts().get(0) instanceof True) {
                return new False().toCNF();
            } else if(cnf.getConjuncts().get(0) instanceof False) {
                return new True().toCNF();
            }
            
        } else {
            return new False().toCNF();
        }
        return null;
    }
    
    @Override
    public String toString() {
        return "!" + expression;
    }
}