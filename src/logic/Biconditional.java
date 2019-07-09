package logic;

public class Biconditional extends Expression {
    
    private Expression consequent1, consequent2;
    
    public Biconditional(Expression consequent1, Expression consequent2) {
        this.consequent1 = consequent1;
        this.consequent2 = consequent2;
    }
    
    public Expression getConsequent1() { return consequent1; }
    
    public Expression getConsequent2() { return consequent2; }
    
    @Override
    public And toCNF() {
        
        return new And(
            new Conditional(consequent1, consequent2),
            new Conditional(consequent2, consequent1))
                .toCNF();
    }
    
    @Override
    public String toString() {
        return "(" + consequent1 + " <-> " + consequent2 + ")";
    }
}