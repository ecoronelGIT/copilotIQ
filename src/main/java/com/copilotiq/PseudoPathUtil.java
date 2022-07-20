package com.copilotiq;

import java.util.List;

/**
 * <h3>PseudoPathUtil</h3>
 * <p>
 * Resolves if a pseudoPath given belongs to a list of pseudoPaths
 * <pre>Given a set of 'pseudo-paths' such as
 * /opt/util/labs,
 * /opt/util/labs/java,
 * /home/users/bob,
 * /home/users/joe, etc.
 * and a pseudoPath like /home/users
 * </pre>
 * </p>
 * the following code will satisfy the assertion:
 * <pre> assertTrue(PseudoPathUtil.pseudoPathBelongToPaths(
 * Arrays.asList("/opt/util/labs", "/home/users/bob", "/home/users/joe"),
 * "/home/users"
 * ));
 * </pre>
 */
public class PseudoPathUtil {

    /**
     * Evaluate if pseudoPath belong to the list of pseudoPath
     * @param pseudoPaths list of current paths
     * @param pseudoPath path to validate
     * @return true if belong to the list
     */
    public boolean pseudoPathBelongToPaths(List<String> pseudoPaths, String pseudoPath) {
        //TODO: implement
        //I will have only one public method and some private methods to do the validation
        return false;
    }

}
