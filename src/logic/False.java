package logic;

public class False extends Expression {

    @Override
    public And toCNF() {
        return new And(this);
    }
    
    @Override
    public String toString() { return "FALSE"; }

}