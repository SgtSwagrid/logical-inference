package logic;

public class Conditional extends Expression {
    
    private Expression antecedent, consequent;
    
    public Conditional(Expression antecedent, Expression consequent) {
        this.antecedent = antecedent;
        this.consequent = consequent;
    }
    
    public Expression getAntecedent() { return antecedent; }
    
    public Expression getConsequent() { return consequent; }

    @Override
    public And toCNF() {
        
        return new Or(
            new Not(antecedent),
            consequent)
                .toCNF();
    }
    
    @Override
    public String toString() {
        return "(" + antecedent + " -> " + consequent + ")";
    }
}