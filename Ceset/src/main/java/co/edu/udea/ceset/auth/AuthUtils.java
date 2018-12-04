/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.ceset.auth;

import co.edu.udea.ceset.dto.User;
import co.edu.udea.ceset.dto.Usuario;
import co.edu.udea.ceset.excepciones.AuthenticationException;
import co.edu.udea.ceset.properties.PropiedadesCeset;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.ReadOnlyJWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import org.joda.time.DateTime;

/**
 * Clase de utilidades para JWT
 * @author edevargas
 */
public class AuthUtils {
    private static final JWSHeader JWT_HEADER = new JWSHeader(JWSAlgorithm.HS256);
    private static final String TOKEN_SECRET = PropiedadesCeset.getInstance().getPropiedadesAutorizacion().getString("TOKEN_SECRET");;
    public static final String AUTH_HEADER_KEY = "Authorization";
    
    private static final String EXPIRE_ERROR_MSG = "El token ha expirado",
            JWT_ERROR_MSG = "No se puede convertir a JWT",
            JWT_INVALID_MSG = "Token JWT inválido";

    public static String getSubject(String authHeader) throws ParseException, JOSEException {
        return decodeToken(authHeader).getSubject();
    }

    public static ReadOnlyJWTClaimsSet decodeToken(String authHeader) throws ParseException, JOSEException {
        SignedJWT signedJWT = SignedJWT.parse(getSerializedToken(authHeader));
        if (signedJWT.verify(new MACVerifier(TOKEN_SECRET))) {
            return signedJWT.getJWTClaimsSet();
        } else {
            throw new JOSEException("Verificación del Signature falló");
        }
    }

    public static Token createToken(String host, User user, int rolId) throws JOSEException {
        JWTClaimsSet claim = new JWTClaimsSet();
        claim.setSubject(Integer.toString(user.getIdUser()));
        claim.setIssuer(host);
        claim.setIssueTime(DateTime.now().toDate());
        claim.setExpirationTime(DateTime.now().plusDays(1).toDate());
        claim.setCustomClaim("usr", user.getNameUser());
      //  claim.setCustomClaim("nom", user.getNombreCompleto());
     //   claim.setCustomClaim("ide", user.getIdentificacion());
        //claim.setCustomClaim("rol", rolId);
     //   claim.setCustomClaim("rls", Arrays.toString(user.getRoles().toArray()));

        JWSSigner signer = new MACSigner(TOKEN_SECRET);
        SignedJWT jwt = new SignedJWT(JWT_HEADER, claim);
        jwt.sign(signer);

        return new Token(jwt.serialize());
    }

    public static String getSerializedToken(String authHeader) {
        return authHeader.split(" ")[1];
    }
    
    /**
     * Método provicional para verificar que el token enviado es válido
     * @param authHeader
     * @return
     * @throws IOException 
     */
    public static boolean verificarToken(String authHeader) throws IOException{

        // Si no posee Token o está incorrecto se meustra mensaje de error 
        if (authHeader == null || authHeader.isEmpty() || authHeader.split(" ").length != 2) {
            throw new AuthenticationException("Usted no cuenta con permisos para llamar el WebService.");
        } else {
            JWTClaimsSet claimSet;
            try {
                claimSet = (JWTClaimsSet) AuthUtils.decodeToken(authHeader);
            } catch (ParseException e) {
                throw new IOException(JWT_ERROR_MSG);
            } catch (JOSEException e) {
                throw new IOException(JWT_INVALID_MSG);
            }

            // Verificamos que el token no haya expirado
            if(claimSet.getExpirationTime() == null){
                 throw new AuthenticationException("El Token de autenticación no cuenta con Fecha de expiración.");
            }
            if (new DateTime(claimSet.getExpirationTime()).isBefore(DateTime.now())) {
                throw new IOException(EXPIRE_ERROR_MSG);
            } else {
                return true;

            }
        }
    }
    
}
