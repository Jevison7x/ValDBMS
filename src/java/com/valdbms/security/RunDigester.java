/*
 * ==========================================================================================
 * @(#)RunDigester.java
 * Document type: Java Application Launcher Class
 * Company: Xyneex Technologies
 * Author: Jevison Archibong
 * Date/Time Created: September 05, 2010. 08:16 PM
 * Version: 1.1
 * Compiler: JDK 1.6.0 SE
 * Operating System: Windows Vista Home Basic
 * Computer System: Becky-PC
 * ==========================================================================================
 */
package com.valdbms.security;

/**
 * <p>
 * This Application is used to test the encryption done by the <code>Digester</code> <i>Class</i> by passing in a <code>String</code> of characters into an input prompt and then retrieving the
 * resulting digest through the command line interface. The <code>Digester.getPassword</code> method is called in the main method of this <i>Class</i>
 * </p>
 *
 * @author Jevison Archibong
 *
 * @since 05 September 2010, 20:16:41
 *
 * @version 1.1
 *
 * @see Digester
 *
 * @deprecated  <font color="red">This <i>Class</i> would be replaced with a more advanced <i>Class.</i></font>
 */
@SuppressWarnings("dep-ann")
public class RunDigester
{
    /**
     * The Application launcher main method
     *
     * @param args The console based array argument.
     * @deprecated      <font color="red">This method may be removed in the future when a more convinient version of it's <i>Class</i> is developed.</font>
     */
    public static void main(String[] args)
    {
        Digester digester = new Digester();
        String password = Digester.getPassword();
        String digested = digester.doDigest(password);
        System.out.println("Password      : " + digester.PASSWORD);
        System.out.println("Salt          : " + digester.SALT);
        System.out.println("Digest Length : " + digested.length());
        System.out.println("Digest        : " + digested);
        System.exit(0);
    }
}
