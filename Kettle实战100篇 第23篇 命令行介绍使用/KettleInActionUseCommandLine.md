# Kettle实战100篇 第23篇 命令行介绍使用

我们在前面介绍的实战篇章中,基本都是在Spoon的图形化界面中点击运行按钮时来运行我们的作业或者转换的,但是Kettle也为我们提供了基于命令行的调用方式,基于命令行的方式可以方便我们通过Shell脚本或者Windows的Bat脚本来对作业&转换进行调用,这方便我们配置作业&转换的任务调度

我们可以在类似Unix平台上使用crontab服务来调用我们的Kettle作业&转换,在Windows平台也可以编写bat脚本添加任务计划进行有规律的调用,这对ETL的自动化处理是非常方便的.

本章就详细的介绍Kettle为我们提供的命令行工具

## 命令行工具介绍

在我们下载的Kettle目录下,我们可以看到存在几个bat或者sh文件,主要包含：

- **Pan**:执行转换的命令行工具
- **Kitchen**:执行作业的命令行工具
- **Encr**：加密工具
- **import**:
- **Spoon**：Kettle的Spoon图形化启动命令
- **runSample**：执行Kettle为我们提供的转换示例
- **Carte**：Kettle提供的基于Jetty的简单作业服务器,主要用于Kettle集群，不同于`Kitchen`命令的是,该服务是后台一直运行的

## 转换执行工具(Pan)

在Windows平台执行`Pan.bat`文件,如果是在类似Unix环境下需要执行`pan.sh`文件

关于Pan工具我们如果不知道输入参数的话,可以直接运行,此时Kettle会为我们打印出来相关的参数介绍信息

```shell
Options:
/rep            : 资源库名称
/user           : 资源库用户名
/trustuser      : 资源库用户名
/pass           : 资源库密码
/trans          : 要启动的转换名称
/dir            : 目录(不要忘了前缀 /)
/file           : 要启动的文件名(转换所在的 XML 文件)
/level          : 日志等级 (Basic,Detailed,Debug,Rowlevel,Error,Minimal,Nothing)
/logfile        : 要写入的日志文件
/listdir        : 列出资源库里的目录
/listtrans      : 列出指定目录下的转换
/listrep        : 列出可用资源库
/exprep         : 将资源库里的所有对象导出到 XML 文件中
/norep          : 不要将日志写到资源库中
/safemode       : 安全模式下运行: 有额外的检查
/version        : 显示版本,校订和构建日期
/param          : Set a named parameter <NAME>=<VALUE>. For example -param:FOO=bar
/listparam      : List information concerning the defined named parameters in the specified transformation.
/metrics        : Gather metrics during execution
/maxloglines    : The maximum number of log lines that are kept internally by Kettle. Set to 0 to keep all rows (default)
/maxlogtimeout  : The maximum age (in minutes) of a log line while being kept internally by Kettle. Set to 0 to keep all rows indefinitely (default)

```

如果我们的转换是另存为XML文件存在在本地磁盘上的,我们可以通过pan这样来调用(Windows平台)

```shell
Pan.bat -file=test.ktr -logfile=test.log -level=RowLevel
```

## 作业执行工具(Kitchen)

作业执行工具和转换差不多,可以直接运行Kitchen.bat来查看输入参数,参数如下：

```shell
Options:
  /rep            : 资源库名称
  /user           : 资源库用户名
  /trustuser      : 资源库用户名
  /pass           : 资源库密码
  /job            : 资源库中的作业名称
  /dir            : 资源库中的作业目录
  /file           : 本地作业XML文件路径
  /level          : 日志级别(Basic,Detailed,Debug,Rowlevel,Error,Minimal,Nothing)
  /logfile        : 日志文件
  /listdir        : 列出当前资源库中的所有目录
  /listjobs       : 列出指定目录下的所有子夜
  /listrep        : 列出可用的资源库
  /norep          : 不要将日志写到资源库中
  /version        : 显示版本号
  /param          : Set a named parameter <NAME>=<VALUE>. For example -param:FILE=customers.csv
  /listparam      : List information concerning the defined parameters in the specified job.
  /export         : Exports all linked resources of the specified job. The argument is the name of a ZIP file.
  /custom         : Set a custom plugin specific option as a String value in the job using <NAME>=<Value>, for example: -custom:COLOR=Red
  /maxloglines    : The maximum number of log lines that are kept internally by Kettle. Set to 0 to keep all rows (default)
  /maxlogtimeout  : The maximum age (in minutes) of a log line while being kept internally by Kettle. Set to 0 to keep all rows indefinitely (default)

```

如果我们的作业文件是存在磁盘则可以这样调用(Windows平台)

```shell
Kitchen.bat -file=F:\公共技术\kettle\任务调度系统同步ES日志作业.kjb -logfile=F:\公共技术\kettle\mySqlToES.log -level=Rowlevel
```

## 加密工具(Encr)

加密工具,我们在生产服务器部署时可以用到此工具,针对数据库密码等敏感信息进行加密处理

定义：

```shell
  encr <-kettle|-carte> <password>
  Options:
    -kettle: generate an obfuscated password to include in Kettle XML files
    -carte : generate an obfuscated password to include in the carte password file 'pwd/kettle.pwd'

```

主要分为kettle和carte两种不同类型的密码处理

kettle是我们在XML文件中使用的

carte主要用于作用于作业服务器中

使用方法

```shell
Encr.bat -kettle 123456
```

最终生成字符串`Encrypted 2be98afc86aa7f2e4cb79ff228dc6fa8c`