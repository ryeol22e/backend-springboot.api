package backend.api.project;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest()
class ApplicationTests {
	
	@Test
	void contextLoads() {
	}
	
	@Test
	void jasypt() {
		String url = new String("");
		String key = new String("");
		String username = new String("");
		String password = new String("");
		
//		System.out.println(jasyptEncoding(url));
		System.out.println("key = " + jasyptEncoding(key));
		System.out.println("username = " + jasyptEncoding(username));
		System.out.println("password = " + jasyptEncoding(password));
	}
	
	private String jasyptEncoding(String value) {
        StandardPBEStringEncryptor pbeEnc = new StandardPBEStringEncryptor();
        pbeEnc.setAlgorithm("PBEWithMD5AndDES");
        pbeEnc.setPassword("projectSecretKey");
        
        return pbeEnc.encrypt(value);
	}

}
