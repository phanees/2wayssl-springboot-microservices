# 2wayssl-springboot-microservices
To demonstrate 2 Way SSL between two microservices developed in Spring Boot.

# Commands to generate the certificates
keytool -genkeypair -alias 2wayssl-gateway -keyalg RSA -keysize 2048 -storetype JKS -keystore 2wayssl-gateway.jks -validity 3650 -ext SAN=dns:localhost,ip:127.0.0.1 

keytool -export -alias 2wayssl-gateway -file 2wayssl-gateway.crt -keystore 2wayssl-gateway.jks

keytool -import -alias 2wayssl-gateway -file 2wayssl-gateway.crt -keystore 2wayssl-ms.jks

keytool -genkeypair -alias 2wayssl-ms -keyalg RSA -keysize 2048 -storetype JKS -keystore 2wayssl-ms.jks -validity 3650 -ext SAN=dns:localhost,ip:127.0.0.1

keytool -export -alias 2wayssl-ms -file 2wayssl-ms.crt -keystore 2wayssl-ms.jks

keytool -import -alias 2wayssl-ms -file 2wayssl-ms.crt -keystore 2wayssl-gateway.jks

# Step to create .p12 file to import the server (2wayssl-ms) certificate into the System
:::note
Because it’s 2 way SSL. When we access gateway url in browser, our browser becomes the client to our gateway application and so, our gateway web app will ask the browser to present a cert for authentication.

To overcome this, we will have to import a cert to our browser. But our browser can’t understand a .jks file. Instead, it understands PKCS12 format.
:::

keytool -importkeystore -srckeystore 2wayssl-ms.jks -destkeystore 2wayssl-ms.p12 -srcstoretype JKS -deststoretype PKCS12 -srcstorepass 2wayssl-service -deststorepass 2wayssl-service -srcalias 2wayssl-ms -destalias 2wayssl-ms -srckeypass 2wayssl-service -destkeypass 2wayssl-service -noprompt