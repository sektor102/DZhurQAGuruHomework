package guru.qa.dZhurHomeWork.Less17_Owner_WebTest.config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:${env}.properties")
public interface WebConfig extends Config {

    // НИКАКИХ аннотаций @Key – Owner сам берёт имя метода как ключ

    String browser();

    String version();

    boolean remote();

    String remoteUrl();

    String baseUrl();

    String browserSize();

    String pageLoadStrategy();
}
