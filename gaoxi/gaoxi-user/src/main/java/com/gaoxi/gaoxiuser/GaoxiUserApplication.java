package com.gaoxi.gaoxiuser;

        import org.mybatis.spring.annotation.MapperScan;
        import org.springframework.boot.SpringApplication;
        import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.gaoxi.gaoxicommonservicefacade.dao")
public class GaoxiUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(GaoxiUserApplication.class, args);
    }

}
