/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parallelhyflex.problems.fdcsp.problem;

import parallelhyflex.parsing.tokenizing.TokenAnnotation;
import parallelhyflex.parsing.tokenizing.TokenGeneratorBase;

/**
 *
 * @author kommusoft
 */
@TokenAnnotation(token = "[A-Z][A-Za-z]*")
public class VariableTokenGenerator extends TokenGeneratorBase<Variable> {
    
    private VariableStore variableStore = new VariableStore();

    @Override
    public Variable generate(String variable) {
        return this.getVariableStore().generate(variable);
    }

    /**
     * @return the variableStore
     */
    public VariableStore getVariableStore() {
        return variableStore;
    }

    /**
     * @param variableStore the variableStore to set
     */
    public void setVariableStore(VariableStore variableStore) {
        this.variableStore = variableStore;
    }
    
    
    
}
