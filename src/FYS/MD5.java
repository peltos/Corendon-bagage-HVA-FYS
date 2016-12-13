package FYS;

import java.security.MessageDigest;

public class MD5 {
        public String MD5(String[] args) throws Exception {

		if (args.length != 1) {
			System.err.println("String to MD5 digest should be first and only parameter");
			return args[0];
		}
		String original = args[0];
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(original.getBytes());
		byte[] digest = md.digest();
		StringBuffer sb = new StringBuffer();
		for (byte b : digest) {
			sb.append(String.format("%02x", b & 0xff));
		}

                
                return sb.toString();
	}
        

}