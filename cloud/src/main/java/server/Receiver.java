package server;

import dataelaboration.ClimateStore;
import dataelaboration.utility.FileManager;
import dataelaboration.utility.Log;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.net.JksOptions;
import io.vertx.ext.web.client.WebClient;
import server.controllers.*;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Base64;

public class Receiver extends AbstractVerticle {

    private static final String ENCRYPTION_PROTOCOL = "AES/ECB/PKCS5PADDING";
    private final int localPort;
    private final ClimateStore climateStore;
    private Routes routes;

    public Receiver(final int localPort, final ClimateStore climateStore) {
        this.localPort = localPort;
        this.climateStore = climateStore;
    }

    @Override
    public void start() {
        initialize();
        try {
            String password = this.decryptPassword();
            getVertx().createHttpServer(

                    new HttpServerOptions()
                            .setSsl(true)
                            .setKeyStoreOptions(new JksOptions()
                                    .setPath(FileManager.PATH_TO_RESOURCES + "/security/keystore.jks")
                                    .setPassword(password))
            ).requestHandler(this.routes.getRouter()).listen(localPort);
            System.out.println("Server online on port " + localPort);
        } catch (Exception e) {
            Log.info(e.getMessage());
        }
    }

    private void initialize() {
        WebClient client = WebClient.create(getVertx());
        SingleDTController singleDTController = new SingleDTController(client, climateStore);
        PeriodicDTController periodicDTController = new PeriodicDTController(climateStore);
        HistoryController historyController = new HistoryController(climateStore);
        DataController dataController = new DataController(client);
        SpatialController spatialController = new SpatialController(client, climateStore);


        this.routes = new Routes(vertx, singleDTController, periodicDTController, historyController, dataController, spatialController);
    }


    private String decryptPassword() throws Exception {
        BufferedReader keyReader = keyReader = new BufferedReader(
                new FileReader(FileManager.PATH_TO_RESOURCES + "/security/key.txt"));
        String key = keyReader.readLine();
        keyReader.close();
        BufferedReader passwordReader = new BufferedReader(
                new FileReader(FileManager.PATH_TO_RESOURCES + "/security/password.txt"));
        String encryptedPassword = passwordReader.readLine();
        passwordReader.close();
        Cipher cipher = Cipher.getInstance(ENCRYPTION_PROTOCOL);
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return new String(cipher.doFinal(Base64.getDecoder().decode(encryptedPassword)));

    }
}
