# spring-boot-https-SSL



## Generate a JKS Certificate
~~~
keytool -genkeypair -keystore user-management.jks -validity 365 -keysize 2048 -dname "CN=User Management, OU=IT, O=realspeed, L=bangalore, ST=Karnataka, C=IN" -keypass changeit -storepass changeit -keyalg RSA -alias user-management -ext "SAN=dns:user-management.com"
~~~

## Convert .cer file from .jks file
~~~
keytool -v -export -file user-management.cer -keystore user-management.jks -alias user-management -storepass changeit
~~~


## Import a CER to key store in consumer system
~~~
keytool -import -trustcacerts -keystore "C:\Program Files\Java\jdk\lib\security\cacerts" -storepass changeit -noprompt -alias user-management2 -file "C:\Users\DJ\Desktop\demo\src\main\resources\user-management.cer"
~~~

## Edit host entry:
~~~
C:\Windows\System32\drivers\etc\hosts

127.0.0.1 user-management.com
~~~


## Below configuration required for Spring Webflux
~~~
@Configuration
public class NettyWebServerFactorySslCustomizer
        implements WebServerFactoryCustomizer<NettyReactiveWebServerFactory> {
    @Override
    public void customize(NettyReactiveWebServerFactory serverFactory) {
        Ssl ssl = new Ssl();
        ssl.setEnabled(true);
        ssl.setKeyStore("classpath:user-management.jks");
        ssl.setKeyAlias("user-management");
        ssl.setKeyPassword("changeit");
        ssl.setKeyStorePassword("changeit");
        Http2 http2 = new Http2();
        http2.setEnabled(false);
        serverFactory.setSsl(ssl);
        serverFactory.setHttp2(http2);
        serverFactory.setPort(8443);
    }
}

~~~
