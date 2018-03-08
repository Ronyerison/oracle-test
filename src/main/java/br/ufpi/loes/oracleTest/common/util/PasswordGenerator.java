/**
 * 
 */
package br.ufpi.loes.oracleTest.common.util;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * @author Rony
 *
 */
public class PasswordGenerator {
	
	public static String generateApplicationCode() {
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		String pwd = RandomStringUtils.random( 8, characters );
		return pwd;
	}
}
