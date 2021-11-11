spring.components bug POC
==============================================================
jar META-INF/spring.components file will make scanBasePackages not work by now.
ie: 
```
@SpringBootApplication(scanBasePackages = { "com.example", "com.other" })
```

## fix
1. add jvm -Dspring.index.ignore=true or add spring.index.ignore=true to spring.properties, then META-INF/spring.components will not load.

2. fix ClassPathScanningCandidateComponentProvider: if the basePackage not defined in META-INF/spring.component, then using old `scanCandidateComponents(basePackage)`

3. add Config class to META-INF/spring.components

## ref
* https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/context/index/CandidateComponentsIndex.html
* CandidateComponentsIndexLoader
