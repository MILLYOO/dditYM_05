package kr.or.ddit.crypto;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import kr.or.ddit.util.CryptoUtils;

/**
 * encoding(부호화) : 데이터의 전송이나 저장을 목적으로 해당 매체가 이해할 수 있는 방식으로 데이터의 표현을 바꾸는 작업.
 * 				ex) URL encoding, Base64 encoding
 * encrypting(암호화) : 허가받지 않은 접근에 의해 데이터가 읽히거나 변환될 수 없도록 막기 위한 작업
 * 					  스니핑과 스푸핑을 막기 위함
 * 		1. 단방향 암호화(해시함수) : 한가지 작업. 암호화는 가능하나 평문 데이터로 복원 불가
 * 							  -> 복호화 불가능한 암호화 기법
 * 							ex) Sha-512, Sha-256
 * 					hash 함수란 ? 다양한 길이의 입력데이터를 처리하며, 일정 길이의 문자열을 만드는 함수(충돌의 위험 잔존)
 * 		2. 양방향 암호화 : 복호화를 통해 원래의 평문이 복원 가능한 기법
 * 			1) 비밀키(대칭키) 암호화 : 한개의 키를 통해 암호화, 복호화 : AES
 * 				e-book
 * 			2) 공개키(비대칭키) 암호화 : 한쌍의 키(공개키, 개인키)로 데이터를 암복호화. 암호화와 복호화에서 서로 반대키가 사용됨. : RSA
 * 				전자서명, 전자투표
 * 				=> 단문데이터에 사용됨. 장문에 사용 시 상당한 부하. 속도가 느림
 */
public class EncryptDesc {
	
	public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
		
		String plain = "java";
		// Member테이블의 Pass를 바꾸기위한 작업
		String encoded = CryptoUtils.sha512EncryptBase64(plain);
		System.out.println(encoded);
		
		// RSA
//		KeyPair keyPair = keyPairGenerateForRSA(2048);
//		PrivateKey privateKey = keyPair.getPrivate();
//		PublicKey publicKey = keyPair.getPublic();
//		
//		String encoded = rsaEncryptBase64(plain, privateKey);
//		String decryptedStr = rsaDecryptBase64(encoded, publicKey);
		
		// AES
//		String ivValue = "아무거나";
//		SecretKey key = CryptoUtils.generateAESKey(256);
//		String encoded = CryptoUtils.aesEncryptBase64(plain, ivValue, key);
//		String decryptedStr = CryptoUtils.aesDecryptBase64(encoded, ivValue, key);
		
	}
	
}
