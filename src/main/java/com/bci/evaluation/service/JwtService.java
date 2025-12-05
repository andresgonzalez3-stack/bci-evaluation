package com.bci.evaluation.service;

import com.bci.evaluation.commons.RsaKeyProperties;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JOSEObjectType;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Instant;
import java.util.Date;

@Service
public class JwtService {

    private final RSAPrivateKey privateKey;

    @Autowired
    public JwtService(RsaKeyProperties rsaKeyProperties) {
        this.privateKey = rsaKeyProperties.getPrivateKey();
    }

    // Generate a signed JWT
    public String generateToken(String username) throws JOSEException {
        Instant now = Instant.now();

        JWTClaimsSet claims = new JWTClaimsSet.Builder()
                .subject(username)
                .issueTime(Date.from(now))
                .expirationTime(Date.from(now.plusSeconds(3600))) // 1 hour expiry
                .build();

        // Create signer with RSA private key
        JWSSigner signer = new RSASSASigner(privateKey);

        SignedJWT signedJWT = new SignedJWT(
                new JWSHeader.Builder(JWSAlgorithm.RS256)
                        .type(JOSEObjectType.JWT).build(), claims);

        // Sign the token
        signedJWT.sign(signer);

        return signedJWT.serialize();
    }

}
