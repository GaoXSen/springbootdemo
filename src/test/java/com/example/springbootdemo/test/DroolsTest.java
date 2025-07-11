package com.example.springbootdemo.test;

import com.example.springbootdemo.dao.IndividualInfo;
import com.example.springbootdemo.dao.Person;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieScanner;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import com.example.springbootdemo.dao.Order;

import java.io.File;

/**
 * @author gaosen
 * @since 2024/3/1 10:34
 */


public class DroolsTest {

    @Test
    public void test() {

        KieServices kieServices = KieServices.Factory.get();

        KieFileSystem kfs = kieServices.newKieFileSystem();

        // 将规则文件添加到KieFileSystem中
        File drlFile = new File("src/main/resources/rules/individualInfo.drl");
        kfs.write("src/main/resources/rules/individualInfo.drl", kieServices.getResources().newFileSystemResource(drlFile));

        //获得Kie容器对象
        //默认自动加载 META-INF/kmodule.xml
        //从KieServices中获得KieContainer实例，其会加载kmodule.xml文件并load规则文件
        KieBuilder kieBuilder = kieServices.newKieBuilder(kfs).buildAll();
        KieContainer kieContainer = kieServices.newKieContainer(kieServices.getRepository().getDefaultReleaseId());

        // 从Kie容器对象中获取会话对象（默认session对象
        KieSession kieSession = kieContainer.newKieSession();

        // 创建KieScanner并启动扫描任务
        KieScanner kieScanner = kieServices.newKieScanner(kieContainer);
        kieScanner.start(1000L); // 5秒钟扫描一次


        System.out.println("Waiting for changes in DRL file...");

        IndividualInfo individualInfo = new IndividualInfo();
        individualInfo.setIndividualName("$");
//            Person person2 = new Person();
//            person2.setEmail("invalid_email_address");
//            Person person3 = new Person();
//            person3.setEmail("mary.smith@example.com");

        kieSession.insert(individualInfo);

        kieSession.fireAllRules();

        kieSession.dispose();
        // 关闭KieScanner
        kieScanner.stop();


    }

//    @Test
//    public void test() {
//        KieServices kieServices = KieServices.Factory.get();
//        //获得Kie容器对象
//        //默认自动加载 META-INF/kmodule.xml
//        //从KieServices中获得KieContainer实例，其会加载kmodule.xml文件并load规则文件
//        KieContainer kieContainer = kieServices.getKieClasspathContainer();
//        // 从Kie容器对象中获取会话对象（默认session对象
//        KieSession kieSession = kieContainer.newKieSession();
//
//        Order order = new Order();
//        order.setOriginalPrice(160d);
//
//        // 将order对象插入工作内存
//        kieSession.insert(order);
//
//        System.out.println("匹配规则前优惠后价格：" + order.getRealPrice());
//
//        // 匹配对象
//        // 激活规则，由drools框架自动进行规则匹配。若匹配成功，则执行
//        kieSession.fireAllRules();
//
//        // 关闭会话
//        kieSession.dispose();
//
//        System.out.println("优惠前价格：" + order.getOriginalPrice() + "\n优惠后价格：" + order.getRealPrice());
//    }


}
