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
#### 4、ToastUtil
Toast操作工具类，提供非阻塞式显示Toast，防止出现连续点击Toast时的显示问题，目前支持功能如下：<br>
1、Toast显示<br>
2、Toast取消<br>
注：如果想要处理由于连续点击，导致Toast重复显示相同内容的问题或者退出当前页面后，Toast还会显示的问题，可以参考我之前的博客文章的解决方案：[完美解决Android中Toast重复显示相同内容的问题](https://blog.csdn.net/okg0111/article/details/79920375)
#### 5、LogUtil
日志Log操作工具类，支持日记打印以及输出到文件中进行存储，便于Log记录与持久化存储：<br>
1、Log打印到控制台<br>
2、Log输出到文件保存进行持久化存储<br>
使用注意事项：<br>
1)在使用LogUtil前需要调用init方法进行初始化（建议在Application中进行初始化），LogUtil.init(Context context, boolean isShowLog, boolean isWriteToFile) , 提供了两个初始化开关参数：<br>
a、isShowLog，是否打印日志，默认关闭<br>
b、isWriteToFile，日志是否写到文件，默认关闭<br>
2)由于Log需要记录到文件中进行持久储存，需要在Manifest中声明文件写权限android.permission.WRITE_EXTERNAL_STORAGE<br>
#### 6、TimeUtil
时间Time操作工具类TimeUtil，目前支持功能如下：<br>
1、获取当前系统时间（格式为yyyy-MM-dd HH:mm:ss的字符串）<br>
2、判断两个日期是否为同一天（yyyy-MM-dd HH:mm:ss格式的日期）<br>
3、判断两个时间之间的相隔的秒数、分钟数、小时数以及天数<br>
#### 7、SoftInputUtil
软键盘操作工具类SoftInputUtil，支持功能如下：<br>
1、软键盘显示<br>
2、软键盘隐藏（收起）<br>
#### 8、DimenUtil
Dimen尺寸操作工具类DimenUtil，支持功能如下：<br>
1、支持sp与px、dp与px之间的互相换算
