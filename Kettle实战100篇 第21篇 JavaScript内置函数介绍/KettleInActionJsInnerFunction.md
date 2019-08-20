# Kettle实战100篇 第21篇 JavaScript内置函数说明

我们在使用JavaScript组件的时候,在左侧核心树对象栏中可以看到Kettle为我们提供了很多简洁强大的内置函数,帮助我们在写脚本的时候对数据、参数变量等能很轻松的做处理,体验编码的感觉.本篇将详细介绍JavaScript组件中的函数功能

脚本组件包含的函数主要包括六大类,分别是：

- 字符串类型的函数(String Functions)
- 浮点型的函数(Numeric Functions)
- 日期类型函数(Date Functions)
- 逻辑判断型函数(Logic Functions)
- 特殊的函数(Special Functions)
- 文件处理类函数(File Functions)

## 字符串类型函数(String Functions)

顾名思义,字符串类型的函数肯定是针对字符串类型的参数、变量进行处理操作的函数

### 日期转字符串(`date2str`)

日期转字符串函数`date2str`主要有4个方法,分别是：

- `date2str(date)`:传入日期实例,转换成字符串类型
- `date2str(date,format)`:传入日期和格式化参数,进行格式化转换
- `date2str(date,format,iso)`:传入日期和参数格式化及ISO代码进行转换,(DE = German, EN = English, FR = France, ...)
- `date2str(date,format,iso,zone)`:传入时区进行格式化,例如北京时区(GMT+8)

日期格式化参数format参数类型供参考：

```javascript
yy / yyyy - 06 / 2006
MM / MMM / MMMMM - 11 / Nov / November
d / dd  - 1 / 01
E / EEEE - Tue / Tuesday
hh / HH - 11 / 23
m / mm - 5 / 05
s / ss - 8 / 08
```

代码示例:

```javascript
var dValue = new Date();
writeToLog(date2str(dValue,"dd.MM.yyyy"));
writeToLog(date2str(dValue,"dd.MM.yyyy HH:mm:ss"));
writeToLog(date2str(dValue,"E.MMM.yyyy","DE"));
writeToLog(date2str(dValue,"dd.MM.yyyy HH:mm:ss","EN"));
writeToLog(date2str(dValue,"dd.MM.yyyy HH:mm:ss","ZH", "GMT+8"));
writeToLog(date2str(dValue,"yyyy-MM-dd HH:mm:ss","ZH", "GMT+8"));
```

以上代码在控制台将会输出如下：

```properties
2019/08/19 10:12:56 - JavaScript代码.0 - 19.08.2019
2019/08/19 10:12:56 - JavaScript代码.0 - 19.08.2019 10:12:56
2019/08/19 10:12:56 - JavaScript代码.0 - Mo.Aug.2019
2019/08/19 10:12:56 - JavaScript代码.0 - 19.08.2019 10:12:56
2019/08/19 10:12:56 - JavaScript代码.0 - 19.08.2019 10:12:56
2019/08/19 10:12:56 - JavaScript代码.0 - 2019-08-19 10:12:56
```

### 转义HTML`escapeHtml(html)`

代码如下：

```javascript
var html="<h1>我是H2标题</h2>";

writeToLog(escapeHtml(html))
```

最终输出

```properties
2019/08/19 10:16:13 - JavaScript代码.0 - &lt;h1&gt;&#25105;&#26159;H2&#26631;&#39064;&lt;/h2&gt;
```

### 转义SQL(`escapeSQL(var)`)

```javascript
var str1 = "SELECT * FROM CUSTOMER WHERE NAME='" + escapeSQL("McHale's Navy") + "'";  
writeToLog(str1)
```

该函数会把单引号转成双引号,输出结果如下：

```java
2019/08/19 10:18:59 - JavaScript代码.0 - SELECT * FROM CUSTOMER WHERE NAME='McHale''s Navy'
```

### 构造定长字符串(`fillString(char,length)`)

代码示例如下：

```javascript
writeToLog(fillString("x",10));
writeToLog(fillString("A",3));
```

最终会输出10个X和3个`A`,输出结果如下：

```properties
2019/08/19 10:24:08 - JavaScript代码.0 - xxxxxxxxxx
2019/08/19 10:24:08 - JavaScript代码.0 - AAA
```

需要注意的是第一个是一个char类型的单字符,不能是字符串

### 统计字符串出现频次(`getOcuranceString(str,searchStr)`)

第一个参数是要搜索的完整字符串,第二个参数是要搜索统计的字符串

代码示例：

```javascript
var sef='2007-09-11';

writeToLog(getOcuranceString(sef,'0'))
writeToLog(getOcuranceString(sef,'00'))
```

我们分别统计字符串0和00最终出现的次数,此时,日志最终打印的次数是3和1：

```pro
2019/08/19 10:28:45 - JavaScript代码.0 - 3
2019/08/19 10:28:45 - JavaScript代码.0 - 1
```

### 获取字符串下标索引(`indexOf`)

获取下标索引主要有2个重构函数,分别是：

- `indexOf(string,subString)`:获取出现字符串的索引开始位置
- `indexOf(string,subString,fromIndex)`；指定开始位置,获取字符串索引开始位置

代码示例：

```javascript
var str1= "Hello Pentaho!";
var str2= indexOf(str1, "Pentaho");
var str3= indexOf(str1, "o", 7);
writeToLog("Input : " + str1);
writeToLog("Index of 'Pentaho' : " + str2);
writeToLog("index of 'o', search from position 7 : " + str3);
```

最终控制台输出：

```java
2019/08/19 10:34:16 - JavaScript代码.0 - Input : Hello Pentaho!
2019/08/19 10:34:16 - JavaScript代码.0 - Index of 'Pentaho' : 6
2019/08/19 10:34:16 - JavaScript代码.0 - index of 'o', search from position 7 : 12
```

### 首字母大写(`initCap`)

对指定字符串首字母大写处理,来看代码示例：

```javascript
var str1 = "my home";      
writeToLog(initCap(str1));
writeToLog(initCap('test a aaa cw'));
writeToLog(initCap('myhome'));
```

此时,最终控制台输出如下：

```java
2019/08/19 10:41:27 - JavaScript代码.0 - My Home
2019/08/19 10:41:27 - JavaScript代码.0 - Test A Aaa Cw
2019/08/19 10:41:27 - JavaScript代码.0 - Myhome
```

### 字符串转小写(`lower`)

将传入字符串全部转小写

代码如下：

```javascript
var str1= "Hello World!";
var str2= lower(str1);
writeToLog("Input:" + str1);
writeToLog("Converted to LowerCase:" + str2);
writeToLog(lower('DDDHelloSWxss'))
```

响应内容

```javascript
2019/08/19 10:43:09 - JavaScript代码.0 - Input:Hello World!
2019/08/19 10:43:09 - JavaScript代码.0 - Converted to LowerCase:hello world!
2019/08/19 10:43:09 - JavaScript代码.0 - dddhelloswxss
```

### 字符串填充左侧(`lpad(string,char,length)`)

用指定长度的给定字符将字符串填充到左侧

参数定义：

- 1：传入字符串
- 2：填充单字符
- 3：填充单字符长度

如果length长度超过给定字符串的长度,将对填充字符串做减法，例如：

```javascript
var str1= "Hello World!"; 
writeToLog("Lpad:" + lpad(str1, "x",20));
```

此时,最终输出结果为：

```java
2019/08/19 10:46:38 - JavaScript代码.0 - Lpad:xxxxxxxxHello World!
```

最终的完成长度是20个字符长度,因此填充的单字符`x`并没有填充20次

如果length长度小于给定字符串的长度,则默认返回原字符串,不做填充,代码示例：

```javascript
var str1= "Hello World!"; 
writeToLog("Lpad:" + lpad(str1, "x",5));
```

此时最终的输出结果为：

```java
2019/08/19 10:46:38 - JavaScript代码.0 - Lpad:Hello World!
```

### 去空字符(`ltrim`)

从左侧开始去除空字符串

### 数字转字符串(`num2str`)

给定数字,转换为字符串,主要有3个构造函数：

- `num2str(num)`:转换num数字为字符串
- `num2str(num,format)`:格式化数字为指定字符串
- `num2str(num,format,iso)`:按照本地ISO编码进行格式化

代码示例如下：

```javascript
var d1 = 123.40;
var d2 = -123.40;
writeToLog(num2str(d1));
writeToLog(num2str(d1, "0.00"));
writeToLog(num2str(d1, "0.00", "EN"));
writeToLog(num2str(d2, "0.00;(0.00)", "EN"));
```

最终控制台输出：

```java
2019/08/19 11:00:17 - JavaScript代码.0 - 123.4
2019/08/19 11:00:17 - JavaScript代码.0 - 123.40
2019/08/19 11:00:17 - JavaScript代码.0 - 123.40
2019/08/19 11:00:17 - JavaScript代码.0 - (123.40)
```

### XML保护标签函数转换(`protectXMLCDATA`)

传入给定字符串,添加标准保护,代码示例

```javascript
var str1 = "my home";      
writeToLog(protectXMLCDATA(str1));
```

此时,将会给变量`str1`加上保护标签

```java
2019/08/19 11:02:09 - JavaScript代码.0 - <![CDATA[my home]]>
```

### 移除字符串中CRLF字符(`removeCRLF(str)`)

给定字符串中删除CR END LF的字符串

### 替换字符串(`replace`)

替换字符串主要包括两个构造函数：

- `replace(str,searchStr,replaceStr)`:从指定字符串中查询，然后替换
- `replace(str,firstSearch,firstReplace,secondSearch,SecondReplace...)`：无限查询替换

代码示例如下：

```javascript
var str1 = "Hello World, this is a nice function";      
var str2 = replace(str1,"World", "Folk");
writeToLog(str2);
var str2 = replace(str1,"World", "Folk", "nice","beautifull");
writeToLog(str2);
```

最终输出:

```java
2019/08/19 11:10:21 - JavaScript代码.0 - Hello Folk, this is a nice function
2019/08/19 11:10:21 - JavaScript代码.0 - Hello Folk, this is a beautifull function
```

### 字符串右侧填充(`rpad(string,char,length`))

使用方法同`lpad`,只是一个是左侧，一个是右侧

### 去除空字符(右侧)(`rtrim`)

### 正则切分(`str2RegExp`)

出入一个正则表达式,对string字符串进行Split操作.代码如下：

```javascript

var strToMatch = "info@proconis.de";
var strReg = "^(\\w+)@([a-zA-Z_]+?)\\.([a-zA-Z]{2,3})$";
var xArr =  str2RegExp(strToMatch, strReg);
if ( xArr != null ) {
    for(i=0;i<xArr.length;i++) {
	    writeToLog(xArr[i]);
	}
}
else {
    writeToLog("no match");
}
```

最终控制台输出：

```java
2019/08/19 13:21:19 - JavaScript代码.0 - info
2019/08/19 13:21:19 - JavaScript代码.0 - proconis
2019/08/19 13:21:19 - JavaScript代码.0 - de
```

### 字符串截取(`substr`)

通过制定索引开始对字符串进行截取操作,主要有两个重构参数：

- `substr(string,from)`:指定from索引开始截取字符串
- `substr(string,from,to)`:指定开始和截止索引进行截取

代码示例：

```javascript
var str1= "Hello Pentaho!";
var str2= substr(str1, 6);
var str3= substr(str1, 6, 7);
writeToLog("Input : " + str1);
writeToLog("From position 6: " + str2);
writeToLog("From position 6 for 7 long : " + str3);
```

控制台输出如下：

```java
2019/08/19 13:31:20 - JavaScript代码.0 - Input : Hello Pentaho!
2019/08/19 13:31:20 - JavaScript代码.0 - From position 6: Pentaho!
2019/08/19 13:31:20 - JavaScript代码.0 - From position 6 for 7 long : Pentaho
```

### 去除左右空格(`trim`)

### 不转义HTML(`unEscapeHtml(html)`)

针对以转义的HTML字符进行解密,代码如下：

```javascript
var w='<h2>我是H2标题</h2>';

var esW=escapeHtml(w);
var unesw=unEscapeHtml(esW);

writeToLog(esW);
writeToLog(unesw);
```

最终控制台输出：

```java
2019/08/19 13:38:16 - JavaScript代码.0 - &lt;h2&gt;&#25105;&#26159;H2&#26631;&#39064;&lt;/h2&gt;
2019/08/19 13:38:16 - JavaScript代码.0 - <h2>我是H2标题</h2>
```

###  解码转义XML(`unEscapeXml `)



### 字符串转大写(`upper`)

将传入字符串全部转大写.例如:

```java
var str="Hello World";
writeToLog(upper(str));
```

## 浮点型的函数(Numeric Functions)

### 计算绝对值(`abs(num)`)

计算一个数值的绝对值,代码示例：

```javascript
var d1 = -1234.01;
var d2 = 1234.01;
writeToLog(abs(d1));
writeToLog(abs(d2));
```

最终控制台输出为：

```java
2019/08/19 13:51:00 - JavaScript代码.0 - 1234.01
2019/08/19 13:51:00 - JavaScript代码.0 - 1234.01
```

### 最小双精度值(`ceil(num)`)

返回最小的双精度值。该值将被四舍五入。代码示例：

```java
var d1 = -1234.01;
var d2 = 1234.01;
writeToLog(ceil(d1));
writeToLog(ceil(d2));
```

最终控制台输出：

```java
2019/08/19 13:52:40 - JavaScript代码.0 - -1234
2019/08/19 13:52:40 - JavaScript代码.0 - 1235
```

### 最大数值(`floor(num)`)

返回最大数值,该值将被四舍五入,代码示例：

```javascript
var d1 = -1234.01;
var d2 = 1234.01;
writeToLog(floor(d1));
writeToLog(floor(d2));
```

运行结果如下：

```java
2019/08/19 13:55:13 - JavaScript代码.0 - -1235
2019/08/19 13:55:13 - JavaScript代码.0 - 1234
```

### 字符串转数值(`str2num(var)`)

字符串转数值主要包含两个构造函数,分别是

- `str2num(str)`:传入数值字符串,进行格式化转换
- `str2num(str,format)`:通过指定格式进行数值转换

代码示例如下：

```javascript
var str1 = "1.234,56";
var str2 = "12";
writeToLog((str2num(str1,"#,##0.00")));
writeToLog((str2num(str2)));
```

最终控制台输出：

```java
2019/08/19 14:02:19 - JavaScript代码.0 - 1.234
2019/08/19 14:02:19 - JavaScript代码.0 - 12
```

### 截取数值(`trunc`)

```javascript
trunc(1234.9); // 返回 1234
```

## 日期类型函数(Date Functions)

### 日期相加(`dateAdd`)

针对日期变量进行相应的添加时间,添加频率包括年、月、日、时、分、秒 等等

函数定义:`dateAdd(date,format,plusNum)`

- date:日期对象
- format:要加的类型
- plusNum:加的数值

相加类型主要包括：

- y:年
- m：月
- d:日
- w:周
- wd:工作日
- hh:小时
- mi:分钟
- ss:秒

代码示例如下：

```javascript
var d1 = new Date();

var fmt='yyyy-MM-dd HH:mm:ss';
writeToLog("当前时间:"+date2str(d1,fmt));
var py=dateAdd(d1,'y',1);
var fy=date2str(py,fmt);
writeToLog("加1年："+fy);
```

最终控制台输出：

```java
2019/08/19 14:17:41 - JavaScript代码.0 - 当前时间:2019-08-19 14:17:41
2019/08/19 14:17:41 - JavaScript代码.0 - 加1年：2020-08-19 14:17:41
```

### 日期比较(`dateDiff`)

两个日期相互比较

函数定义:`dateDiff(startDate,endDate,type)`

- startDate:开始日期
- endDate:截止日期
- type：返回相差数值类型

类型主要包括：

- y:年
- m：月
- d:日
- w:周
- wd:工作日
- hh:小时
- mi:分钟
- ss:秒

### 获取指定日期数值(`getDayNumber`)

根据类型获取指定日期的数值

函数定义：`getDayNumber(date,type)`

- date:当前日期实例
- type:类别

类别主要分四类

- y:获取当年的天数
- m:获取当月的天数
- w:获取本周的天数
- wm:获取当月中本周的天数

代码示例：

```java
var d1 = new Date();
writeToLog(getDayNumber(d1, "y"));
writeToLog(getDayNumber(d1, "m"));
writeToLog(getDayNumber(d1, "w"));
writeToLog(getDayNumber(d1, "wm"));
```

### getFiscalDate

```javascript
// Returns the fiscal Date from the date value,
// based on a given offset.
//
// Usage:
// getFiscalDate(var);
// 1: Date - The Variable with the Date.
// 2: String - The Date/Month which represents
// the fiscal Start Offset. Format allways "dd.MM.".
//
// 2006-11-15
//
var d1 = new Date();
var str1 = "01.07.";
var str2 = "10.12.";
Alert(getFiscalDate(d1, str1));
Alert(getFiscalDate(d1, str2));
```

### 获取下一个工作日日期(`getNextWorkingDay`)

传入当前日期,获取该日期后面一个工作日日期

函数定义`getNextWorkingDay(date)`

代码示例如下：

```javascript
var d1 = new Date();

// 周1
var d2=str2date('2019-08-19 16:36:00',fmt);
//周 6
var d3=str2date('2019-08-17 16:36:00',fmt);

writeToLog(date2str(getNextWorkingDay(d1),fmt));
writeToLog(date2str(getNextWorkingDay(d2),fmt));
writeToLog(date2str(getNextWorkingDay(d3),fmt));
```

我们这d2和d3变量中定义了不同的日期实例,分别是周1和周6,最终通过`getNextWorkingDay`能获取得到下一个工作日日期，控制台输出如下：

```java
2019/08/19 16:37:38 - JavaScript代码.0 - 2019-08-20 16:37:38
2019/08/19 16:37:38 - JavaScript代码.0 - 2019-08-20 16:36:00
2019/08/19 16:37:38 - JavaScript代码.0 - 2019-08-19 16:36:00
```

### 获取当前月份数值(`month(date)`)

获取当前日期的月份数值,需要注意的是,该值的月份是从0开始的,因此我们最终得到的结果应该+1才是我们的真实月份数值，代码示例：

```javascript
var d1 = new Date();//2019/08/19
writeToLog(month(d1)); //最终输出为7
```

### 获取当前时间的季度值(`quarter(date)`)

根据指定日期获取当前季度数值

```javascript
var d1 = new Date();//2019/08/19
writeToLog(quarter(d1));//最终输出为3(代表第三季度)
```

### 字符串转日期(`str2date`)

字符串转日期和日期转字符串有点类似,只不过主体对换了一下,但是传入的格式参数都是一样的，主要有4个重载函数:

- `str2date(str)`:默认转换
- `str2date(str,format)`:传入format格式化参数
- `str2date(str,format,iso)`:根据iso编码及格式化参数进行转换
- `str2date(str,format,iso,timezone)`:根据不同时区的iso编码进行格式化转换

代码示例如下：

```javascript
writeToLog(str2date("01.12.2006","dd.MM.yyyy"));
writeToLog(str2date("01.12.2006 23:23:01","dd.MM.yyyy HH:mm:ss"));
writeToLog(str2date("Tue.May.2006","E.MMM.yyyy","EN"));
writeToLog(str2date("22.02.2008 23:23:01","dd.MM.yyyy HH:mm:ss","DE"));
writeToLog(str2date("22.02.2008 23:23:01","dd.MM.yyyy HH:mm:ss","DE", "EST"));
```

### 截取日期(`truncDate(date,type)`)

指定截取不同的日期部分,函数定义truncDate(date,type)

- date:当前日期实例
- type:截取类型

类型主要有6中,分别是整型，从0-5：

- 5：截取月份
- 4：截取天数
- 3:截取小时
- 2：截取分钟
- 1：截取秒
- 0:截取毫秒

代码示例 如下：

```javascript
var dateTime = new Date();
var date0 = truncDate(dateTime, 0); // gives back today at yyyy/MM/dd HH:mm:ss.000
var date1 = truncDate(dateTime, 1); // gives back today at yyyy/MM/dd HH:mm:00.000
var date2 = truncDate(dateTime, 2); // gives back today at yyyy/MM/dd HH:00:00.000
var date3 = truncDate(dateTime, 3); // gives back today at yyyy/MM/dd 00:00:00.000
var date4 = truncDate(dateTime, 4); // gives back today at yyyy/MM/01 00:00:00.000
var date5 = truncDate(dateTime, 5); // gives back today at yyyy/01/01 00:00:00.000
```

### 获取当年的周数(`week`)

获取指定日期的周数,代码示例：

```javascript
var d1 = new Date(); //2019/08/19
 
writeToLog(week(d1));// 返回34
```

### 获取年份(`year`)

获取传入日期的年份,代码示例：

```javascript
var d1 = new Date(); //2019/08/19
 
writeToLog(year(d1));// 返回2019
```

## 逻辑判断型函数(Logic Functions)

### isCodepage

判断字符串的codepage项,代码示例：

```javascript
var xStr = "RÃ©al";
writeToLog(isCodepage(xStr, "UTF-8"));// true
writeToLog(isCodepage(xStr, "windows-1250"));// true
```

### 是否日期(`isDate(str)`)

判断当前字符串是否日期

```javascript
var d1 = "Hello World";      
var d2 = new Date();
writeToLog(isDate(d1));//false
writeToLog(isDate(d2));//true
```

### 是否为空(`isEmpty(str)`)

判断字符串是否为空

```javascript
var d = "Hello World";      
Alert(isEmpty(d));//false
```

### 判断字符串是否为邮箱标准格式(`isMailValid(str)`)

判断一个字符串是否是邮箱

### 判断是否是数值(`isNum(str)`)

判断一个字符串是否是数值

```javascript
var str1 = "Hello World";      
var str2 = 123456;
Alert(isNum(str1));//false
Alert(isNum(str2));//true
```

### 是否正则匹配(`isRegExp`)

判断给定的正则表达式是否匹配当前的字符串，主要有2个函数定义：

- isRegExp(str,reg):给定正则判断字符串是否匹配
- isRegExp(str,reg1,reg2,reg3...)；可以递归判断正则匹配

最终返回的是匹配的次数数值,如果不匹配,返回-1

代码示例如下：

```javascript
var email1 ="info@proconis.de";
var email2= "support@proconis.co.uk";
var email3= "HelloWorld@x";

var reg1="^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$";
var reg2="^[\\w-\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

writeToLog(isRegExp(email1, reg1,reg2) + " Matches"); //1
writeToLog(isRegExp(email2, reg1,reg2) + " Matches");  //2
writeToLog(isRegExp(email3, reg1,reg2) + " Matches");// 1
```

### 是否工作日(`isWorkingDay(date)`)

判断某日期是否是工作日,代码示例：

```javascript
var d1 = new Date();//周1
var d2=str2date('2019-08-17','yyyy-MM-dd') //周六
writeToLog(isWorkingDay(d1));//true
writeToLog(isWorkingDay(d2));//false
```

## 特殊的函数(Special Functions)

### 弹框信息(`Alert(msg)`)

在屏幕前弹出一个信息框

### 加载JavaScript文件(`LoadScriptFile`)

将一个javascript文件加载到实际的运行上下文中。应该从定义的StartScript调用此函数，否则，每次处理都会加载javascript文件行。

代码示例如下：

```javascript
var xPfad = "F:/bak/Hello.js";
LoadScriptFile(xPfad);
```

此时,我们的外部JS文件仅仅是包含一句简单的输出，如下：

```javascript
writeToLog("Hello LoadScriptFile,outSide JS File ");
```

最终运行时,控制台会打印出我们在外部JS中的输出行

### 从当前Tab栏加载JS并运行(`LoadScriptFromTab`)

如果我们在当前的JavaScript组件中通过模块化的方式编写了很多脚本代码,我们可以通过`LoadScriptFromTab`函数进行相互调用,这对于开发抽象来说是很好的,代码示例如下：

```javascript
writeToLog("外部Tab加载JS-------------------------")
LoadScriptFromTab('Item_1');
```

### 有效卡号判断(`LuhnCheck`)

如果给定的是一个有效的卡号,则返回true

```javascript
var str1 = "4444333322221111";      
writeToLog(str1 + ": " + LuhnCheck(str1)); //true

var str2 = "4444333322221110";      
writeToLog(str2 + ": " + LuhnCheck(str2));//false
      
```

### 向文件中追加数据(`appendToFile`)

向指定文件中追加数据,如果文件不存在则创建文件

```javascript

var file = "F:/bak/log.txt";

for(var i=0;i<100;i++){
	appendToFile(file,'TEST'+i+"\r\n");
}

```

此时,该代码会向`log.txt`文件输出100条数据行

### decode函数

decode函数有点类似于`IF-THEN-ELSE`语句，即表示通过给定查询的字符串是否存在，如果存在，即替换,否则返回默认值

代码示例：

```javascript
var str1 = "Hallo";
writeToLog(decode(str1, "Hallo", "Hello"));
writeToLog(decode(str1, "1", "Mr", "2", "Mrs", "N/A"));
writeToLog(decode(str1, "1", "Mr", "2", "Mrs"));
str1 = "Mrs";
writeToLog(decode(str1, "1", "Mr", "2", "Mrs"));
```

控制台输出：

```java
2019/08/19 17:39:01 - JavaScript代码.0 - Hello
2019/08/19 17:39:01 - JavaScript代码.0 - N/A
2019/08/19 17:39:01 - JavaScript代码.0 - Hallo
2019/08/19 17:39:01 - JavaScript代码.0 - Mrs
```

### 执行命令(`execProcess`)

代码如下：

```javascript
var t=execProcess('ping www.baidu.com');
writeToLog(t)
```

调用命令行,ping百度的网址，最终输出返回数据

### 执行SQL语句(`fireToDB`)

通过获取数据库连接名称，传递SQL语句,以返回SQL查询的值,函数定义：

- fireToDB(connectionName,SQL)；第一个参数为数据库连接名称，我们在JNDI中定义的名称，第二个参数为SQL语句

```javascript
var strConn = "MY Connection";
var strSQL = "SELECT COUNT(*) FROM ...";
var xArr = fireToDB(strConn, strSQL);
```

### 仅仅获取数值(`getDigitsOnly`)

在给定的字符串中仅仅筛选过滤得到数值，代码如下：

```javascript
var str1 = "abc123cde";      
writeToLog(getDigitsOnly(str1));//返回123
```

### 获取Kettle环境变量的值(`getEnvironmentVar`)

获取在Kettle中的环境变量的值

```javascript
writeToLog(getEnvironmentVar("user.dir"));
writeToLog(getEnvironmentVar("user.name"));
```

### 获取当前进程的受影响行数(`getProcessCount(type)`)

根据类型获取当前进程的受影响行数，类型如下：

- u:更新行数
- i:插入行数
- w:写入行数
- r:读取行数
- o:输出行数

```javascript
writeToLog(getProcessCount("u"));
writeToLog(getProcessCount("r"));
```

### 获取当前转换名称(`getTransformationName`)

获取当前的转换名称

```javascript
var xTranName = getTransformationName();
writeToLog(xTranName);
```

### 获取Kettle环境中的变量值(`getVariable`)

从当前的Kettle环境中获取指定的变量值,目前函数有2个重载：

- getVariable(varName)；根据变量名称获取变量值
- getVariable(varName,defaultValue):根据变量名获取值,如果不存在则使用默认值

```javascript
var strVarName="getVariableTest";
var strVarValue="123456";
Alert(getVariable(strVarName, ""));
setVariable(strVarName,strVarValue, "r");
Alert(getVariable(strVarName, ""));
strVarValue="654321";
setVariable(strVarName,strVarValue, "r");
Alert(getVariable(strVarName, ""));
```

### 控制台打印(`println`)

```javascript
var str = "Hello World!";
print(str);
      
```

### 移除数值(`removeDigits`)

移除给定字符串中的数值，代码示例：

```javascript
var str1 = "abc123cde"; 

writeToLog(removeDigits(str1));//返回abccde
```

### 发送邮件

###  设置环境变量(`setEnvironmentVar`)

通过在Script脚本组件中调用函数重新设置Kettle的环境变量

```javascript
var strVarName="setEnvTest";
var strVarValue="123456";
Alert(getEnvironmentVar(strVarName));
setEnvironmentVar(strVarName,strVarValue);
Alert(getEnvironmentVar(strVarName));
strVarValue="654321";
setEnvironmentVar(strVarName,strVarValue);
Alert(getEnvironmentVar(strVarName));
```

### 设置变量(`setVariable`)

通过`setVariable`函数设置环境变量,该用途可以用于重新赋值Kettle环境中已经存在的变量值或者重新生成变量值

函数定义`setVariable(key,value,level)`

- key:变量名称
- value:变量值
- level:级别,主要包括s(system)、r(root)、p(parent)、g(grandparent)四种类别

代码示例如下：

```javascript
var strVarName="setEnvTest";
var strVarValue="123456";
Alert(getVariable(strVarName, ""));
setVariable(strVarName,strVarValue, "r");
Alert(getVariable(strVarName, ""));
strVarValue="654321";
setVariable(strVarName,strVarValue, "r");
Alert(getVariable(strVarName, ""));
```

### 写入日志(`writeToLog`)

打印并写入日志信息,该函数可能是我们用到的最多的函数,可以帮助我们调试信息,主要有两个重载：

- writeToLog(msg):写入msg日志信息
- writeToLog(level,msg)：根据level基本写入msg信息

关于日志的级别,这里主要是简写的方式,主要如下：

- d(Debug):调试模式
- l(Detailed):详细
- e(Error):错误
- m(Minimal):最小日志
- r(RowLevel):行级日志

```javascript
writeToLog("Hello World!");
writeToLog("r", "Hello World!");
```

## 文件处理类函数(File Functions)

### 复制文件(`copyFile`)

复制一个文件到目标目录,函数定义如下：

`copyFile(sourceFile,targetFile,overwrite)`

- sourceFile:源文件
- targetFile:目标文件
- overWrite:是否覆盖,如果目标文件存在的话,布尔类型

```javascript
var file1 = "F:/bak/log.txt";

var targetFile="F:/bak/logTarget.txt";

copyFile(file1,targetFile,false)
```

### 创建文件夹(`createFolder`)

创建一个文件夹,代码示例如下：

```javascript
var strFolder = "F:/bak/createFolder";
createFolder(strFolder);
```

### 删除文件(`deleteFile`)

删除一个文件(不能删除文件夹)

```javascript
var targetFile="F:/bak/logTarget.txt";
 
deleteFile(targetFile);
```

### 判断文件是否存在(`fileExists()`)

判断文件是否存在

```javascript
var targetFile="F:/bak/logTarget.txt";
 
fileExists(targetFile);
```

### 获取文件扩展名(`getFileExtension`)

如果文件不存在,则返回null,代码示例

```javascript
var file1 = "F:/bak/log.txt";
 
var ext=getFileExtension(file1);
writeToLog("扩展名："+ext)
```

### 获取文件大小(`getFileSize`)

获取文件大小,结果是一个long类型的长整型数值

```javascript
var file1 = "F:/bak/log.txt";
 
var ext=getFileSize(file1);
writeToLog("大小："+ext)
```

### 获取文件最后修改日期(`getLastModifiedTime`)

获取文件最后修改日期,函数定义：

`getLastModifiedTime(filePath,format)`

- filePath:文件路径
- format：日期格式化

```javascript
var file1 = "F:/bak/log.txt";
 
var ext=getLastModifiedTime(file1,"yyyy-MM-dd HH:mm:ss");
```

### 获取文件的父文件夹名称(`getParentFoldername`)

获取文件的父文件夹名称

```javascript
var file1 = "F:/bak/log.txt";
var parentFolder=getParentFoldername(file1);
```

### 获取文件简称(`getShortName`)

获取文件简称

```javascript

var file1 = "F:/bak/log.txt";
var shortName=getShortFilename(file1);

writeToLog("简单名称:"+shortName)//返回log.txt
```

### 判断是否是一个文件(`isFile`)

判断是否是一个文件

```javascript
var file1 = "F:/bak/log.txt";
var flag=isFile(file1) //true
```

### 判断是否是一个文件夹(`isFolder`)

判断是否是一个文件夹

```javascript
var file1 = "F:/bak/log.txt";
var flag=isFolder(file1) //false
```

### 加载一个文件的内容(`loadFileContent`)

从指定文件中加载内容,主要有两个重载函数：

- loadFileContent(filePath):默认加载文件
- loadFileContent(filePath,encoding):指定编码加载文件内容

代码示例：

```javascript
var file1 = "F:/bak/log.txt";
var content=loadFileContent(file1);
var c1=loadFileContent(file1,"UTF-8")
writeToLog(content)
```

### 移动文件(`moveFile`)

移动指定文件，函数定义moveFile(source,target,overWrite)

- source:源文件
- target:目标文件
- overWrite；是否覆盖,如果目标文件存在,布尔类型值

```javascript
var file1 = "F:/bak/log.txt";
var targetFile="F:/bak/logTarget.txt";

moveFile(file1,targetFile,false)

```

### 创建一个空文件(`touch`)

创建一个空文件

```javascript
var strFile = "F:/bak/log.txt";
touch(strFile);
```

## 总结

以上就是Kettle 8.3版本中的内置函数方法,方法很多,写这篇博客也是很累,算是全部都学习了一遍,脑子里已经记忆了一遍,但是我们也不需要死记硬背,就和我们学些Linux命令一样,如果你知道`man`命令，对某个命令不是很了解的话直接通过man命令学习即可.

Kettle也是如此,对于某个函数不是很了解的话,右键点击该函数,会出现`sample`字样菜单,点击该菜单即可弹出该函数的介绍和使用信息,里面包含了该函数的调用示例和函数详细介绍,也是很人性化的.