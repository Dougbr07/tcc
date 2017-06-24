package Dao;

import Model.GoogleModel;
import Util.Conexao;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;

public class GoogleDAO {
    private Connection connection;

    public GoogleDAO() {
        connection = Conexao.getConnection();
    }
    public boolean validateIdToken(GoogleModel object) throws SQLException, GeneralSecurityException, IOException {
        NetHttpTransport transport = new NetHttpTransport();
        GsonFactory jsonFactory = new GsonFactory();
        String ID_TOKEN_STRING = object.getIdToken();
//        if(ID_TOKEN_STRING.length() != 1170){
//            return false;
//        }
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
                .setAudience(Collections.singletonList("377306572429-fdovga51la59ekd7n1qjsqltl0sjskod.apps.googleusercontent.com"))
                // Or, if multiple clients access the backend:
                //.setAudience(Arrays.asList(CLIENT_ID_1, CLIENT_ID_2, CLIENT_ID_3))
                .build();

        // (Receive idTokenString by HTTPS POST)
        GoogleIdToken idToken = verifier.verify(ID_TOKEN_STRING);

        if (idToken != null) {

            Payload payload = idToken.getPayload();
            // Print user identifier
            String userId = payload.getSubject();
            // Get profile information from payload
            String email = payload.getEmail();
            boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
            String name = (String) payload.get("name");
            String pictureUrl = (String) payload.get("picture");
            String locale = (String) payload.get("locale");
            String familyName = (String) payload.get("family_name");
            String givenName = (String) payload.get("given_name");
            object.setNome(name);
            object.setEmail(email);
            object.setFotoUrl(pictureUrl);
            return true;
            // Use or store profile information
            // ...
        } else {
            return false;
        }
    }
}
