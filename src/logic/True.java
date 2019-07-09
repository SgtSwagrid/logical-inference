package logic;

public class True extends Expression {

    @Override
    public And toCNF() {
        return new And(this);
    }
    
    @Override
    public String toString() { return "TRUE"; }

}