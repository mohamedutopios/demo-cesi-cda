keytool -genkeypair -alias toto -keyalg RSA -keysize 2048 -storetype PKCS12 -keystore keystore1.p12 -validity 365

curl -v -k https://localhost:8443/hello


