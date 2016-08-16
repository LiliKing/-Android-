# -Android-
杭州市民卡Android基础开发框架




Android App
框架说明文档
【PRD】


	
一、	整个项目文件夹下面分为以下几块

1、	activity

所有的activity类。

所有的activity类继承于BaseActivity类，BaseActivity中主要是写一些通用的方法。

例如：

```java
@Override
protected void onDestroy() {
    dismissProcessDialog();
    showDlgObj.dismiss();
    //在销毁页面的时候同时也取消请求
 VolleyUtil.getInstance(this).cancelPendingRequests(mRequestTag);
    super.onDestroy();
}
```
在这个onDestroy方法里面关闭了加载框，所有的弹出框，和所有的网络请求。所有的activity都需要做这些，所以可以再BaseActivity中写上这个方法。

2、	fragment

所有的fragment类。

所有的fragment类继承于BaseFragment类，BaseFragment类写一些基础fragment通用方法。

比如:

```java
@Override
public void onResume() {
    super.onResume();
    MobclickAgent.onPageStart(getClassName());  // umeng required,统计页面
}
```
在这个onResume里面，进行了友盟统计，所有fragment都需要统计，那么可以再BaseFragment中写上这个方法。

3、	adapter

所有适配器类

主要用于写一些列表适配器


4、	config

网络接口访问url或者是一些常量配置

比如：

```java
public static final String sBaseServer = "http://192.168.1.101:8082/test/test";
```
这是一个接口访问服务器地址的前缀。

或者是：

配置的一些Key，方便统一管理和使用


5、	constant

App的一些常量
如：Activity跳转时候的requestCode，需要统一管理，防止值一样，并且方便修改和查看

6、	net

网络接口请求类

里面包括网络请求发送接收等基础类

Request：网络接口请求参数

Response：网路接口返回参数


7、	utils

一些实用类，如StringUtil是一些对String的常用处理

比如：
```java
public static boolean isNotBlank(String str) {
    return str != null && !str.trim().equals(EMPTY_STRING);
}
```

是对字符串是否为空的判断，字符串为空有好几种，用这个方法可以判断所有的情况

8、	view

自定义视图，自定义控件

9、	database

数据存储和数据库

一些基本数据的存储：

如：用户名存储可以使用shareprefs中的BaseSaveData，里面定义了String、Int、Boolean类型的数据保存

数据库：

DataBase接口用于添加一些增删改查询的方法，然后在DataBaseHelper里面进行实现。

