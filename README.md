# AndroidPracticalUtils
### 一、初衷
整理Android日常开发中常用的实用工具Utils，所有工具类均放在utilslib这个module里面
### 二、支持工具类以及功能说明--支持更新中
#### 1、JsonUtil：
Json操作工具类，利用Gson通过泛型实现任意对象或列表对象与Json数据的转换，支持功能如下：<br>
1、将任意对象或列表转为Json字符串<br>
2、将Json字符串转换为任意对象或列表(通过泛型)<br>
#### 2、TextUtil:
Text文本操作工具类TextUtil，目前支持功能如下--持续更新中：<br>
1、字符串是否为空判断<br>
2、字符串是否全为空格判断<br>
3、邮箱格式判断<br>
4、手机号码判断<br>
#### 3、SharedPreferencesUtil
SharedPreferences操作工具类SharedPreferencesUtil，与原生保持功能一致，目前支持功能如下：<br>
1、save（put）与get：与原生保持一致，支持保存和获取int、float、long、boolean、String、Set<String><br>
2、clear：清空SharedPreferences内容<br>
3、remove：支持删除remove相应key对应的值<br>
---持续更新中
