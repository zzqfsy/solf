# solf
is the business solution, also shows our logical framework 

是一个业务解决方案，关于展示我们的业务逻辑框架

## Design background
设计背景

![image](https://zzqfsy.github.io/image/solf/solf-origin.drawio.png)

## Project description
工程描述
> demo/solf-bundle-demo
> 业务扩展包示例
> demo/solf-service-demo
> 服务示例
> solf-core
> 核心实现
> solf-model
> 核心模型
> wiki
> 介绍
    

## Core design
核心设计

### Register
#### Business identity register
业务身份注册
![image](https://zzqfsy.github.io/image/solf/Solf%20Degin%20Biz%20Identity%20Register.jpg)

#### Domain ability register
领域能力注册
![image](https://zzqfsy.github.io/image/solf/Solf%20Degin%20Doamin%20Ability%20Register.png)

#### Flow node register
流程节点注册
![image](https://zzqfsy.github.io/image/solf/Solf%20Degin%20Flow%20Register.png)

### Processor
#### Domain ability processor
领域能力处理
![image](https://zzqfsy.github.io/image/solf/Solf%20Design%20Domain%20handler.png)

#### Flow chain processor
流程链处理
![image](https://zzqfsy.github.io/image/solf/Solf%20Design%20Flow%20handler.png)

### Class diagram
类图
![image](https://zzqfsy.github.io/image/solf/solf-class-graph.drawio.png)

## How to use
使用方式

### import dependency
引入依赖包
引入solf-core依赖
```xml
<dependency>
    <groupId>com.zzqfsy.io</groupId>
    <artifactId>solf-core</artifactId>
</dependency>
```

### scan to load bean
扫描加载
```java
@ComponentScan(basePackages = {
        "com.zzqfsy.solf.core"
})
```
### Register custom business identity
注册自定义业务身份
```java
@Component
@BizIdentitySpec(code = "xxxBizId", name = "xxx")
public class XxxBizIdentityConfig {

    /**
     * 获取xxx的身份
     *
     * @return
     */
    public BizIdentity getBizIdentity(T t) {
        ...
    }
}
```

### Register custom domain ability
注册自定义领域能力
```java
@DomainSpec(name = "xxx", domain = "xxxDomain", description = "xxx")
public class XxxEventHandler {
    
    @AbilityOrderSpec(300)
    @AbilitySpec(name = "xxx", 
            scenario = "xxx", 
            description = "", 
            bizIdentityCode = "xxxBizId")
    public <E> E handler(T t) {
        ...
    }
```

### Register custom flow node chain
注册自定义流程节点链
```java
@Component
@FlowChainSpec(name = "xxx", code = "xxxFlow")
public class XxxFlowChain {
    
    @FlowNodeOrderSpec(1)
    @FlowNodeSpec(name = "xxx", code = "xxxFlowNode",
        domainName = "xxxDomain", abilityName = "xxxAbility")
    public void valid(T t){
        ...
    }
```

### Run it to understand
运行来了解
```java
debug com.zzqfsy.solf.service.demo.ServiceApplicaiton.main

then browser request http://127.0.0.1:8081/stockIn

console println execute handler:
PurchaseOrderStockInValidEventHandler handler
PurchaseOrderStockInAssemblyEventHandler handler
PurchaseOrderStockInCreateEventHandler handler
PurchaseOrderStockInCreateEventHandler wms handler
```

# View
视图

## flowChainView
流程链视图
请求示例
```java
debug com.zzqfsy.solf.service.demo.ServiceApplicaiton.main

then browser request http://127.0.0.1:8081/flowChain/config
```
输出示例
```json
[{
	"chainName": "入库通知",
	"chainCode": "stockInNoticeFlow",
	"flowNodeList": [{
		"name": "入库通知校验",
		"code": "StockInNotice.valid",
		"domainName": "stockInNoticeDomain",
		"abilityName": "valid",
		"order": 1
	}, {
		"name": "入库通知组装",
		"code": "StockInNotice.assembly",
		"domainName": "stockInNoticeDomain",
		"abilityName": "assembly",
		"order": 2
	}, {
		"name": "入库通知创建",
		"code": "StockInNotice.create",
		"domainName": "stockInNoticeDomain",
		"abilityName": "create",
		"order": 3
	}]
}]
```

## bizIdentityDomainAbilityView
业务身份能力视图
请求示例
```java
debug com.zzqfsy.solf.service.demo.ServiceApplicaiton.main

then browser request http://127.0.0.1:8081/domain/ability/config
```
输出示例
```json
[{
	"bizIdentityName": "采购单",
	"bizIdentityCode": "purchaseOrderBizId",
	"bizIdentityMethodObject": {
		"className": "com.zzqfsy.solf.service.demo.config.bizId.stock.in.PurchaseOrderBizIdentityConfig",
		"methodName": "getBizIdentity",
		"parameterType": "com.zzqfsy.solf.service.demo.model.StockInDo",
		"returnType": "com.zzqfsy.solf.model.identity.BizIdentity"
	},
	"domainCode": "stockInNoticeDomain",
	"domainName": "入库领域",
	"abilityScenario": "valid",
	"abilityName": "校验",
	"abilityOrder": 100,
	"abilityMethodObject": {
		"beanClass": "com.zzqfsy.solf.service.demo.domain.event.handler.stock.in.notice.valid.PurchaseOrderStockInValidEventHandler",
		"methodName": "handler",
		"parameterType": "com.zzqfsy.solf.service.demo.model.StockInDo",
		"returnType": "java.lang.Integer",
		"order": 100
	}
}]
```

## request object match flow and ability view
请求对象匹配流程和能力视图

todo 