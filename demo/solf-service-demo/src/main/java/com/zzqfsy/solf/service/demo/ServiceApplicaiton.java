package com.zzqfsy.solf.service.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author zzqfsy
 * @email zzqfsy@gmail.com
 * Created on 2022/10/18
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.zzqfsy.solf.service.demo","com.zzqfsy.solf.core"})
@EnableAspectJAutoProxy
public class ServiceApplicaiton {

    public static void main(String[] args) {
        SpringApplication.run(ServiceApplicaiton.class, args);

//        Set<String> dict = new HashSet<>();
//        dict.add("a");
//        dict.add("app");
//        dict.add("apple");
//        dict.add("desk");
//        dict.add("top");
//        dict.add("led");
//
//        System.out.println(split("appledesktop", dict, new Stack<>()));
//        System.out.println(split("appledappleatop", dict, new Stack<>()));
    }

//    public static List<String> split(String text, Set<String> dict, Stack<String> result){
//        if (StringUtils.isEmpty(text)){
//            return result;
//        }
//
//        Boolean anyMatch = false;
//        for (String matchS : dict) {
//            if (text.startsWith(matchS)){
//                String sub = text.substring(matchS.length());
//                result.push(matchS);
//
//                split(sub, dict, result);
//                if (result.isEmpty()){
//                    continue;
//                }
//
//                anyMatch = true;
//            }
//        }
//        if (!anyMatch){
//            result.pop();
//        }
//        return result;
//    }
}
