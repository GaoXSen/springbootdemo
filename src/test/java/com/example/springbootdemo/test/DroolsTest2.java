package com.example.springbootdemo.test;

import com.example.springbootdemo.dao.Person;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieScanner;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.io.File;

/**
 * @author gaosen
 * @since 2024/3/1 16:13
 */

public class DroolsTest2 {
    public static void main(String[] args) throws InterruptedException {
        KieServices kieServices = KieServices.Factory.get();
        KieFileSystem kfs = kieServices.newKieFileSystem();

        // 将规则文件添加到KieFileSystem中
        File drlFile = new File("src/main/resources/rules/email.drl");
        kfs.write(kieServices.getResources().newFileSystemResource(drlFile));

        KieBuilder kieBuilder = kieServices.newKieBuilder(kfs).buildAll();

//        KieContainer kieContainer = kieServices.newKieContainer(kieServices.getRepository().getDefaultReleaseId());
        KieContainer kieContainer = kieServices.newKieContainer(kieServices.newReleaseId("com.example","game","0.0.1-SNAPSHOT"));

        // 创建初始的KieSession
        KieSession kSession = kieContainer.newKieSession();

        // 创建KieScanner并启动扫描任务
        KieScanner kieScanner = kieServices.newKieScanner(kieContainer);
        kieScanner.start(5000L); // 5秒钟扫描一次

        kieScanner.scanNow();

        for(int i = 0;i<10;i++){

            System.out.println("Waiting for changes in DRL file...");

            Person person1 = new Person();
            person1.setEmail("john.doe@example.com");
            Person person2 = new Person();
            person2.setEmail("invalid_email_address");
            Person person3 = new Person();
            person3.setEmail("mary.smith@example.com");

            kSession.insert(person1);
            kSession.insert(person2);
            kSession.insert(person3);

            kSession.fireAllRules();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        // 执行规则
//        kSession.fireAllRules();
        kSession.dispose();

        // 关闭KieScanner
        kieScanner.stop();
    }
}
