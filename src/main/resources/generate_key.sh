#!/bin/bash

keytool -genkeypair -noprompt -alias tomcat -storepass password -keypass password -keyalg RSA -keysize 2048 -storetype PKCS12 -keystore keystore.p12 -validity 3650 -dname "CN=truststore.service.com, OU=security, O=TRUST, L=dummy, S=dummy, C=ES"
