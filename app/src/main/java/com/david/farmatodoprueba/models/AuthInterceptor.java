package com.david.farmatodoprueba.models;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthInterceptor implements Interceptor {
    private static final String TIMESTAMP_KEY = "ts";
    private static final String HASH_KEY = "hash";
    private static final String APIKEY_KEY = "apikey";

    private final String publicKey;
    private final String privateKey;
    private final AuthHashGenerator authHashGenerator = new AuthHashGenerator();

    public AuthInterceptor(String publicKey, String privateKey) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        String timestamp = String.valueOf(30 * 1000);
        String hash = null;
        try {
            hash = authHashGenerator.generateHash(timestamp, publicKey, privateKey);
        } catch (MarvelApiException e) {
            e.printStackTrace();
        }
        Request request = chain.request();
        HttpUrl url = request.url()
                .newBuilder()
                .addQueryParameter(TIMESTAMP_KEY, timestamp)
                .addQueryParameter(APIKEY_KEY, publicKey)
                .addQueryParameter(HASH_KEY, hash)
                .build();
        request = request.newBuilder().url(url).build();
        return chain.proceed(request);
    }


    class AuthHashGenerator {
        String generateHash(String timestamp, String publicKey, String privateKey)
                throws MarvelApiException {
            try {
                String value = timestamp + privateKey + publicKey;
                MessageDigest md5Encoder = MessageDigest.getInstance("MD5");
                byte[] md5Bytes = md5Encoder.digest(value.getBytes());

                StringBuilder md5 = new StringBuilder();
                for (int i = 0; i < md5Bytes.length; ++i) {
                    md5.append(Integer.toHexString((md5Bytes[i] & 0xFF) | 0x100).substring(1, 3));
                }
                return md5.toString();
            } catch (NoSuchAlgorithmException e) {
                throw new MarvelApiException("cannot generate the api key", e);
            }
        }
    }
}
