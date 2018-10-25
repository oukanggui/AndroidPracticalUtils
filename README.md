# AndroidPracticalUtils
## 一、初衷
整理Android日常开发中常用的实用工具Utils，所有工具类均放在utilslib这个module里面
## 二、支持工具类以及功能说明--支持更新中
### 1、JsonUtil：
Json操作工具类，利用Gson通过泛型实现任意对象或列表对象与Json数据的转换，支持功能如下：<br>
1、将任意对象或列表转为Json字符串<br>
2、将Json字符串转换为任意对象或列表(通过泛型)<br>
**支持具体功能方法使用说明如下：**<br>

|方法名|功能说明|
|:---|:---|
|String toJson(Object object)|将对象转化后的Json字符串|
|T parseJson(String json, Class\<T\> tClass)| Json字符串转换为泛型对象T|
|List\<T\> parseJson(String json, Type listType)| Json字符串转换为参数为泛型对象T的List列表|
  
当将Json字符串转化为List列表时，需要获取List列表具体的Type对象(TypeToken)，可以通过如下方式获取：<br>
**new TypeToken\<List\<T\>\>(){}.getType()**<br>
其中T为泛型对象，在实际使用时替换为具体的实体类即可
### 2、TextUtil:
Text文本操作工具类TextUtil，目前支持功能如下：<br>
1、字符串是否为空判断<br>
2、字符串是否全为空格判断<br>
3、邮箱格式判断<br>
4、手机号码判断<br>
**支持具体功能方法使用说明如下：**<br>

|方法名|功能说明|
|:---|:---|
|boolean isEmpty(String str)|判断字符串是否为空|
|boolean isBlank(final CharSequence s)|判断字符串是否全为空格|
|boolean isEmail(String strEmail)|判断一个字符串是否是邮箱格式，目前只是简单的通过字符串中是否含有@来判断|
|boolean isPhoneNumber(String strPhone)|判断一个字符串是否是11位手机号格式（正则表达式）|

### 3、SharedPreferencesUtil
SharedPreferences操作工具类SharedPreferencesUtil，与原生保持功能一致，目前支持功能如下：<br>
1、save（put）与get：与原生保持一致，支持保存和获取int、float、long、boolean、String、Set\<String\><br>
2、clear：清空SharedPreferences内容<br>
3、remove：支持删除remove相应key对应的值<br>
**支持具体功能方法使用说明如下：**<br>

|方法名|功能说明|
|:---|:---|
|void saveInt(Context context, String key, int value)|保存int整型数值|
|void saveFloat(Context context, String key, float value)|保存Float浮点型数值|
|void saveBoolean(Context context, String key, boolean value)|保存Boolean数值|
|void saveLong(Context context, String key, long value)|保存Long数值|
|void saveString(Context context, String key, String value)|保存String数值|
|void saveStringSet(Context context, String key, Set\<String\> value)|保存Set\<String\>数值|
|int getInt(Context context, String key, int defaultValue)|获取相应key的整型数值|
|float getFloat(Context context, String key, float defaultValue)|获取相应key的Float浮点型数值|
|boolean getBoolean(Context context, String key, boolean defaultValue)|获取相应key的Boolean数值|
|long getLong(Context context, String key, long defaultValue)|获取相应key的Long数值|
|String getString(Context context, String key, String defaultValue)|获取相应key的String数值|
|Set\<String\> getStringSet(Context context, String key, Set\<String\> defaultValue)|获取相应key的Set\<String\>数值|
|void clear(Context context)|清空sp文件保存的内容|
|void remove(Context context, String key)|移除key对应的内容|

### 4、ToastUtil
Toast操作工具类，提供**非阻塞式**显示Toast，防止出现连续点击Toast时的显示问题，目前支持功能如下：<br>
1、Toast显示<br>
2、Toast取消<br>
**支持具体功能方法使用说明如下：**<br>

|方法名|功能说明|
|:---|:---|
|void showToast(final Context context, final CharSequence text, final int duration)|显示Toast|
|void showToast(Context context, CharSequence text)|显示Toast，默认显示时长duration为Toast.LENGTH_SHORT|
|void cancelToast()|取消Toast的显示|

注：如果想要处理由于连续点击，导致Toast重复显示相同内容的问题或者退出当前页面后，Toast还会显示的问题，可以参考我之前的博客文章的解决方案：[完美解决Android中Toast重复显示相同内容的问题](https://blog.csdn.net/okg0111/article/details/79920375)
### 5、LogUtil
日志Log操作工具类，支持日记打印以及输出到文件中进行存储，便于Log记录与持久化存储：<br>
1、Log打印到控制台<br>
2、Log输出到文件保存进行持久化存储<br>
使用注意事项：<br>
1)在使用LogUtil前需要调用init方法进行初始化（建议在Application中进行初始化），LogUtil.init(Context context, boolean isShowLog, boolean isWriteToFile) , 提供了两个初始化开关参数：<br>
a、isShowLog，是否打印日志，默认关闭<br>
b、isWriteToFile，日志是否写到文件，默认关闭<br>
2)由于Log需要记录到文件中进行持久储存，需要在Manifest中声明文件写权限android.permission.WRITE_EXTERNAL_STORAGE<br>
**支持具体功能方法使用说明如下：**<br>

|方法名|功能说明|
|:---|:---|
|void i(String tag, String msg)|打印Info level log|
|void d(String tag, String msg)|打印Debug level log|
|void v(String tag, String msg)|打印Verbose level log|
|void w(String tag, String msg)|打印Warn level log|
|void e(String tag, String msg)|打印Error level log|

### 6、TimeUtil
时间Time操作工具类TimeUtil，目前支持功能如下：<br>
1、获取当前系统时间（格式为yyyy-MM-dd HH:mm:ss的字符串）<br>
2、判断两个日期是否为同一天（yyyy-MM-dd HH:mm:ss格式的日期）<br>
3、判断两个时间之间的相隔的秒数、分钟数、小时数以及天数<br>
**支持具体功能方法使用说明如下：**<br>

|方法名|功能说明|
|:---|:---|
|String getSystemCurrentTime()|获取当前系统时间（格式为yyyy-MM-dd HH:mm:ss的字符串）|
|boolean isSameDay(String lastTime, String currentTime)|判断两个日期是否为同一天（yyyy-MM-dd HH:mm:ss格式的日期）|
|long twoDateGapSeconds(String startTime, String endTime)|计算两个日期的时间相差多少秒|
|long twoDateGapMinutes(String startTime, String endTime)|计算两个日期的时间相差多少分钟|
|long twoDateGapHours(String startTime, String endTime)|计算两个日期的时间相差多少小时|
|long twoDateGapDays(String startTime, String endTime)|计算两个日期的时间相差多少天|

### 7、SoftInputUtil
软键盘操作工具类SoftInputUtil，支持功能如下：<br>
1、软键盘显示<br>
2、软键盘隐藏（收起）<br>
**支持具体功能方法使用说明如下：**<br>

|方法名|功能说明|
|:---|:---|
|void showSoftInput(Context context, View view)|显示系统软键盘，其中View为接收输入内容的View|
|void hideSoftInput(Context context, View view)|隐藏系统软键盘，其中View为接收输入内容的View|
|void hideSoftInput(Activity activity)|隐藏系统软键盘|

### 8、DimenUtil
Dimen尺寸操作工具类DimenUtil，支持功能如下：<br>
1、支持sp与px、dp与px之间的互相换算<br>
**支持具体功能方法使用说明如下：**<br>

|方法名|功能说明|
|:---|:---|
|int dp2px(Context context, int dipValue)|转换dp为px|
|int px2dp(Context context, int pxValue)|转换px为dp|
|int sp2px(Context context, float spValue)|转换sp为px|
|int px2sp(Context context, float pxValue)|转换px为sp|

### 9、AppUtil
App相关工具操作类，支持功能如下：<br>
1、获取自身以及第三方应用版本名称以及版本号<br>
2、检测某应用是否已安装<br>
3、获取某应用在手机的状态（更新、已安装、未安装等）<br>
4、启动（打开）第三方APP<br>
**支持具体功能方法使用说明如下：**<br>

|方法名|功能说明|
|:---|:---|
|String getAppVersionName(Context context)|获取应用本身的版本名|
|String getAppVersionName(Context context, String packageName)|获取第三方应用的版本名|
|int getAppVersionCode(Context context)|获取应用本身的版本号|
|int getAppVersionCode(Context context, String packageName)|获取第三方应用的版本号|
|boolean checkPackage(Context context, String packageName)|检测该包名所对应的应用是否已安装|
|int checkPackageStatus(Context context, String packageName, int versionCode)|判断APP在手机的安装状态（未安装、已安装、更新等）|
|boolean openApp(Context context, String packageName)|启动第三方APP|

### 10、 NetworkUtil
网络工具操作类NetworkUtil，目前支持功能如下：<br>
1、获取网络IP地址<br>
2、判断网络是否可用<br>
3、判断当前网络是否连接WiFi<br>
**支持具体功能方法使用说明如下：**<br>

|方法名|功能说明|
|:---|:---|
|String getIp()|获取网络IP地址|
|boolean isNetworkAvailable(Context context)|判断当前网络是否可用|
|boolean isWifiConnected(Context context)|判断当前网络是否连接WiFi|

### 11、DeviceUtil
设备操作工具类DeviceUtil，目前支持功能如下：<br>
1、获取设备屏幕宽度和高度大小<br>
2、获取设备IMEI号<br>
3、获取SIM卡IMSI号<br>
4、获取设备MAC地址<br>
**支持具体功能方法使用说明如下：**<br>

|方法名|功能说明|
|:---|:---|
|int getScreenWidth(Context context)|获取设备屏幕宽度（px）|
|int getScreenHeight(Context context)|获取屏幕的高度（px）|
|String getImei(Context context)|获取设备IMEI号|
|String getImsi(Context context)|获取SIM卡IMSI序列号|
|String getMacAddress(Context context)|获取设备Mac地址|

### 12、SystemUtil
System操作工具类SystemUtil,目前支持功能如下：<br>
1、判断SD卡是否可用<br>
2、获取SD卡路径<br>
3、获取系统型号<br>
4、获取系统版本号<br>
5、获取系统SDK版本号信息<br>
6、支持调用系统浏览器打开网页<br>
7、支持打开系统分享选择器<br>
**支持具体功能方法使用说明如下：**<br>

|方法名|功能说明|
|:---|:---|
|boolean isSDCardAvailable()|判断SD卡是否可用|
|String getSDCardPath()|获取SDCard路径|
|String getSysModel()|获取系统型号|
|String getSysVersion()|获取系统版本号|
|int getSDKVersion()|获取系统SDK版本号|
|void openSystemBrowser(Context context,String url)|调用系统浏览器打开网页|
|void openSystemShareChooser(Context context, String title, String text)|打开系统分享选择器|

### 13、RunnableUtil
Runnable操作工具类RunnableUtil，通过内置线程池，提高任务执行效率，支持如下功能：<br>
1、支持Runnable任务task立即以及排队执行<br>
**支持具体功能方法使用说明如下：**<br>

|方法名|功能说明|
|:---|:---|
|void runTask(Runnable task)|执行Runnable任务，默认为任务排队执行|
|runTask(Runnable task, boolean immediate)|执行Runnable任务，immediate值表明任务是否立即执行，true：任务立即执行，false：任务排队执行（默认值）|

### 14、ViewUtil
View操作相关工具类ViewUtil，目前支持功能如下：<br>
1、判断两次点击之间是否存在快速点击（支持重置）<br>
**支持具体功能方法使用说明如下：**<br>

|方法名|功能说明|
|:---|:---|
|boolean isFastDoubleClick()|判断两次点击之间是否存在快速点击,默认两次点击时间间隔阈值为800ms|
|boolean isFastDoubleClick(long interval)|判断两次点击之间是否存在快速点击，interval为两次点击时间间隔阈值|
|boolean isFastDoubleClick(boolean isReset)|判断两次点击之间是否存在快速点击,默认两次点击时间间隔阈值为800ms,isReset 需要是要重置（由于记录上次点击时间是采用static的形式，防止在两个不同控件之间快速点击存在误判）|
|boolean isFastDoubleClick(long interval, boolean isReset)|判断两次点击之间是否存在快速点击,isReset值含义如上|

### 持续更新中

