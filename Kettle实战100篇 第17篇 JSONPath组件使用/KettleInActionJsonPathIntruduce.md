# Kettle实战100篇 第17篇 JSONPath组件介绍说明

在我们使用JSON input组件的时候,设置字段映射时,由于Kettle使用的是JSONPath组件来进行解析的,因此我们就需要了解他的相关语法

JSONPath是一个用于读取JSON的Java DSL操作库

GitHub:https://github.com/json-path/JsonPath

在线调试:http://jsonpath.herokuapp.com/

## 表示法

JSONPath表达式用于指定JSON结构元素(或一组元素)的路径.路径的表示法可以使用点表示,如下：

```javascript
$.store.book[0].title
```

或者括号表示:

```java
$['store']['book'][0]['title']
```

**`$`**所代表的是JSON根路径,在使用时可以忽略.例如`$.foobar.name`和`foobar.name`所表达的意思是一样的,同理`$[0].status`和`[0].status`也一样

其他语法元素如下表:

| 表达式                    | 说明                                                         |
| ------------------------- | ------------------------------------------------------------ |
| `$`                       | 根对象或者数组                                               |
| `.property`               | 选择父对象中指定的属性                                       |
| `[property]`              | 选择父对象中的指定属性。务必在属性名称周围加上单引号。<br />如果属性名称包含空格等特殊字符，或者以A..Za..z_以外的字符开头，请使用此表示法 |
| `[n]`                     | 从数组中选择第n个元素。索引从0开始。                         |
| `[index1,index2,…]`       | 选择具有指定索引的数组元素。返回一个集合列表。               |
| `..property`              | 递归查找：递归搜索指定的属性名称，并返回具有此属性名称的所有值的数组。即使只找到一个属性，也始终返回一个列表。 |
| `*`                       | 通配符选择对象或数组中的所有元素，无论其名称或索引如何。例如，`address.*`。*表示地址对象的所有属性，`book [*]`表示书籍数组的所有项目 |
| `[start:end] or [start:]` | 从起始索引中选择数组元素，最多但不包括结束索引。如果省略end，则从开始到数组结束选择所有元素。返回一个列表。 |
| `[:n]`                    | 选择数组的前n个元素。返回一个列表。                          |
| `[-n:]`                   | 选择数组的最后n个元素。返回一个列表。                        |
| `[?expression]`           | 过滤表达式。选择对象或数组中与指定过滤器匹配的所有元素。返回一个列表。 |
| `[(expression)]`          | 可以使用脚本表达式代替显式属性名称或索引。一个例子是`[(@.length-1)]`，它选择数组中的最后一项。这里，length指的是当前数组的长度，而不是名为length的JSON字段。 |
| `@`                       | 在过滤器表达式中用于引用正在处理的当前节点。                 |

**注意：**

- JSONPath表达式(包括属性名称和值)区分大小写。
- 与XPath不同，JSONPath没有用于从给定节点访问父节点或兄弟节点的操作。

## 过滤器

过滤器是用于过滤数组的逻辑表达式。带有过滤器的JSONPath表达式的示例

```json
$.store.book[?(@.price < 10)]
```

其中`@`表示当前正在处理的数组项或对象。过滤器也可以使用`$`来引用当前对象之外的属性

```javascript
$.store.book[?(@.price < $.expensive)]
```

只指定属性名称的表达式如`[?(@.isbn)]`将匹配具有此属性的所有项目,无论值如何

此外,过滤器支持一下运算符

| 操作符 | 说明                                                         |
| ------ | ------------------------------------------------------------ |
| `==`   | 等于,1和`'1'`被认为是相等的,字符串值必须用单引号括起来(不是双引号)：例如`[?(@.color=='red')]` |
| `!=`   | 不等于。字符串值必须用单引号括起来。                         |
| `>`    | 大于                                                         |
| `>=`   | 大于等于                                                     |
| `<`    | 小于                                                         |
| `<=`   | 小于等于                                                     |
| `=~`   | 匹配JavaScript正则表达式,例如`[?(@.description=~ /cat.*/i)]`匹配描述以cat开头的项（不区分大小写） |
| `!`    | 用于否定,例如`[?(!@.isbn)]`匹配没有isbn属性的项目            |
| `&&`   | 逻辑AND，用于组合多个过滤器表达式,例如：`[?(@.category=='fiction' && @.price < 10 )]` |
| `||`   | 逻辑OR，用于组合多个过滤器表达式,例如：`[?(@.category=='fiction' || @.price < 10 )]` |

## 综合示例

目前我们有如下JSON结构:

```java
{
  "store": {
    "book": [
      {
        "category": "reference",
        "author": "Nigel Rees",
        "title": "Sayings of the Century",
        "price": 8.95
      },
      {
        "category": "fiction",
        "author": "Herman Melville",
        "title": "Moby Dick",
        "isbn": "0-553-21311-3",
        "price": 8.99
      },
      {
        "category": "fiction",
        "author": "J.R.R. Tolkien",
        "title": "The Lord of the Rings",
        "isbn": "0-395-19395-8",
        "price": 22.99
      }
    ],
    "bicycle": {
      "color": "red",
      "price": 19.95
    }
  },
  "expensive": 10
}
```

在下面的例子中,`$`符号是可选的,可以省略掉：

| 表达式                                                       | 说明                                                         |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| `$.store.*`                                                  | store对象下所有的属性(非递归)                                |
| `$.store.bicycle.color`                                      | 获取得到`color`属性的值,结果为`red`                          |
| `$.store..price` <br />`$..price`                            | 返回所有的price属性值集合,结果为`[8.95,8.99,22.99,19.95]`    |
| `$.store.book[*]`<br />`$..book[*]`                          | 所有的book集合                                               |
| `$..book[*].title`                                           | 返回book对象下的所有标题集合                                 |
| `$..book[0]`                                                 | 返回第一个book集合对象,结果为`[{"category":"reference","author":"Nigel Rees","title":"Sayings of the Century","price":8.95}]` |
| `$..book[0].title`                                           | 返回第一个book集合对象中的title属性,结果是`Sayings of the Century` |
| `$..book[0,1].title`<br />`$..book[:2].title`                | 返回前2个book集合对象的title属性,结果是`[Sayings of the Century, Moby Dick]` |
| `$..book[-1:].title`<br />`$..book[(@.length-1)].title`      | 返回最后一个book对象的title属性集合,结果是`[The Lord of the Rings]` |
| `$..book[?(@.author=='J.R.R. Tolkien')].title`               | 返回book集合中所有的作者等于`J.R.R. Tolkien`的title集合,结果是`[The Lord of the Rings]` |
| `$..book[?(@.isbn)]`                                         | 返回所有book对象属性中含有isbn的属性，其结果是books集合      |
| `$..book[?(!@.isbn)]`                                        | 返回book对象属性中不包含isbn的属性,结果是集合                |
| `$..book[?(@.price < 10)]`                                   | 返回所有book对象属性中`price`属性小于10的对象集合            |
| `$..book[?(@.price > $.expensive)]`                          | 返回所有book对象属性中`price`属性值大于`expensive`值的对象集合 |
| `$..book[?(@.author =~ /.*Tolkien/i)]`                       | 返回所有book对象属性中的author属性是以`Tolkien`结尾(不区分大小写)的属性对象集合 |
| `$..book[?(@.category == 'fiction' || @.category == 'reference')]` | 返回book对象属性中`category`等于`fiction`的或者等于`reference`的对象集合 |
| `$..*`                                                       | 根目录下的JSON结构的所有成员（子对象，单个属性值，数组项）组合成一个数组。 |



### 返回多个元素的JSONPath表达式的注意事项

JSONPath查询不仅可以返回单个元素，还可以返回匹配元素的列表。

例如如下JSON结构：

```json
{
  "name": "Rose Kolodny",
  "phoneNumbers": [
    {
      "type": "home",
      "number": "954-555-1234"
    },
    {
      "type": "work",
      "number": "754-555-5678"
    }
  ]
}
```

JSONPath表达式：

```js
phoneNumbers[*].number
```

该表达式将会返回一个集合列表,如下：

```js
[954-555-1234, 754-555-5678]
```

请注意，这不是JSON数组，它只是以逗号分隔的项列表，其中[]表示列表的开头和结尾。

对匹配列表使用“equals”断言时，请指定[]中包含的预期值列表，并用逗号和一个空格分隔

```js
[apples, 15, false, ["foo","bar"], {"status":"ok"}]
```

除非引号是值的一部分，否则独立字符串（如apples）不应包含引号

**示例**

给定下面一个JSON:

```json
{ "words": ["apples", "\"oranges\""] }
```

`$ .words [*]`返回所有数组项的列表，因此预期值为`[apples，“oranges”]`。

注意与`$ .words`的区别，它返回JSON中显示的数组本身，因此，在这种情况下，值将是`[“apples”，“\”oranges \“”]`

作为JSON数组和对象的值保留内部引号，但是在它们的项之间缩小而没有空格：`[“foo”，“bar”]`，而不是`[ “foo” ， “bar” ]`。

## Java使用示例

因为我是一名Java工程师,看到这里,既然JSONPath是一个用Java语言开发的组件,那自然是要学习一下的(非Java语言的同学可以忽略~~~)

### 简单使用

首先在Maven项目中加入JSONPath的引用

```java
<!-- https://mvnrepository.com/artifact/com.jayway.jsonpath/json-path -->
<dependency>
   <groupId>com.jayway.jsonpath</groupId>
   <artifactId>json-path</artifactId>
   <version>2.4.0</version>
</dependency>
```

假设有如下JSON结构

```json
{
  "name": "Rose Kolodny",
  "phoneNumbers": [
    {
      "type": "home",
      "number": "954-555-1234"
    },
    {
      "type": "work",
      "number": "754-555-5678"
    }
  ]
}
```

我们想获取得到number的数组集合,应该如何做呢

```java
String json="...";
System.out.println("JSON:"+json);

List<String> numbers= JsonPath.read(json,"$..number");
for (String num:numbers){
    System.out.println(num);
}
String name=JsonPath.read(json,"$.name");
System.out.println("name:"+name);
```

最终控制台输出：

```java
954-555-1234
754-555-5678
Rose Kolodny
```

上面这种方式很麻烦,因为你没读取一个JSON的字段属性,都需要将源JSON整体传入到方法中,在对于程序性能来说是一种消耗,因为都需要JSONPath组件重新解析一次JSON的结构

如果你只想JSONPath只初始化一次就可以了,应该使用如下方式：

```java
private static void once(String json){
    System.out.println("初始化一次");
    //初始化创建Document对象
    Object document= Configuration.defaultConfiguration().jsonProvider().parse(json);
    List<String> numbers= JsonPath.read(document,"$..number");
    for (String num:numbers){
      System.out.println(num);
    }
    String name=JsonPath.read(document,"$.name");
    System.out.println("name:"+name);
}
```

此外,JSONPath还提供了流式API,方便开发者使用

```java
String json = "...";

ReadContext ctx = JsonPath.parse(json);

List<String> authorsOfBooksWithISBN = ctx.read("$.store.book[?(@.isbn)].author");


List<Map<String, Object>> expensiveBooks = JsonPath
                            .using(configuration)
                            .parse(json)
                            .read("$.store.book[?(@.price > 10)]", List.class);
```

### 返回预期

在使用JSONPath组件时,了解结果中的预期类型非常重要,比如在上个json中,我们查询name属性时,使用JSONPath的表达式`$.name`和`$..name`就区别很大,一个是返回String类型的字符串,一个是返回数组,因此,根据JSONPath预判返回结果类型显得尤为关键。

```java
//将会抛出java.lang.ClassCastException异常,因为这是返回一个String字符串的JSONPath表达式
List<String> list = JsonPath.parse(json).read("$.store.book[0].author")

//正确执行
String author = JsonPath.parse(json).read("$.store.book[0].author")
```

在评估预判JSONPath的路径时,您需要了解路径何时确定的概念。如果路径包含，则路径是不确定的

- `..`:一个递归扫描的表示法
- `?(<expression>)`：一个JSONPath表达式
- `[<number>,<number>(,<number>)]`:多个数组索引

对于不确定的路径JSONPath始终返回一个数组列表

默认情况下，MappingProvider SPI提供了一个简单的对象映射器。这允许您指定所需的返回类型，MappingProvider将尝试执行映射。在下面的示例中，演示了Long和Date之间的映射

```java
String json = "{\"date_as_long\" : 1411455611975}";

Date date = JsonPath.parse(json).read("$['date_as_long']", Date.class);
```

如果将JSONPath配置为使用`JacksonMappingProvider`或者`GsonMappingProvider`，您甚至可以将JSONPath输出直接映射到POJO上

```java
Book book = JsonPath.parse(json).read("$.store.book[0]", Book.class);
```

要获得完整的泛型类型信息，请使用TypeRef

```java
TypeRef<List<String>> typeRef = new TypeRef<List<String>>() {};

List<String> titles = JsonPath.parse(JSON_DOCUMENT).read("$.store.book[*].title", typeRef);
```

### 过滤

有三种方法来创建JSONPath组件的过滤器规则

#### 内联

```java
List<Map<String, Object>> books =  JsonPath.parse(json)
                                     .read("$.store.book[?(@.price < 10)]");
```

您可以使用`&&`和`||`组合多个条件，`[?(@.price < 10 && @.category == 'fiction')]`、`[?(@.category == 'reference' || @.price > 10)]`

您也可以使用`!`表示非条件`[?(!(@.price < 10 && @.category == 'fiction'))]`

#### 过滤

通过JSONPath提供的API来筛选

```java
import static com.jayway.jsonpath.JsonPath.parse;
import static com.jayway.jsonpath.Criteria.where;
import static com.jayway.jsonpath.Filter.filter;
...
...

Filter cheapFictionFilter = filter(
   where("category").is("fiction").and("price").lte(10D)
);

List<Map<String, Object>> books =  
   parse(json).read("$.store.book[?]", cheapFictionFilter);
```

注意占位符`?`对于路径中的过滤器。当提供多个过滤器时，它们将按顺序应用，其中占位符的数量必须与提供的过滤器数量相匹配。

您也可以使用`OR`和`AND`对接过进行筛选组合

```java
Filter fooOrBar = filter(
   where("foo").exists(true)).or(where("bar").exists(true)
);
   
Filter fooAndBar = filter(
   where("foo").exists(true)).and(where("bar").exists(true)
);
```

#### Roll Your Own

第三种是实现你自己的`Predicate`接口

```java
Predicate booksWithISBN = new Predicate() {
    @Override
    public boolean apply(PredicateContext ctx) {
        return ctx.item(Map.class).containsKey("isbn");
    }
};

List<Map<String, Object>> books = 
   reader.read("$.store.book[?].isbn", List.class, booksWithISBN);
```

### 值和路径

在Goessner实现中，JsonPath可以返回Path或Value。值是默认值，以及上面的所有示例返回的内容。如果您更喜欢我们的查询所遇到的元素的路径，则可以使用选项来实现。

```java
Configuration conf = Configuration.builder()
   .options(Option.AS_PATH_LIST).build();

List<String> pathList = using(conf).parse(json).read("$..author");

assertThat(pathList).containsExactly(
    "$['store']['book'][0]['author']",
    "$['store']['book'][1]['author']",
    "$['store']['book'][2]['author']",
    "$['store']['book'][3]['author']");
```

### 配置信息

#### 选项

创建配置时，有一些选项标志可以改变默认行为。

##### **DEFAULT_PATH_LEAF_TO_NULL** 

此选项使JsonPath为缺少的叶子返回null。考虑以下json

```json
[
   {
      "name" : "john",
      "gender" : "male"
   },
   {
      "name" : "ben"
   }
]
```

Java代码

```java
Configuration conf = Configuration.defaultConfiguration();

//正确运行
String gender0 = JsonPath.using(conf).parse(json).read("$[0]['gender']");
//PathNotFoundException thrown
String gender1 = JsonPath.using(conf).parse(json).read("$[1]['gender']");

Configuration conf2 = conf.addOptions(Option.DEFAULT_PATH_LEAF_TO_NULL);

//Works fine
String gender0 = JsonPath.using(conf2).parse(json).read("$[0]['gender']");
//返回null
String gender1 = JsonPath.using(conf2).parse(json).read("$[1]['gender']");
```

##### ALWAYS_RETURN_LIST

即使路径是确定的，此选项也会将JsonPath配置为返回列表

```java
Configuration conf = Configuration.defaultConfiguration();

//Works fine
List<String> genders0 = JsonPath.using(conf).parse(json).read("$[0]['gender']");
//PathNotFoundException thrown
List<String> genders1 = JsonPath.using(conf).parse(json).read("$[1]['gender']");
```

##### **SUPPRESS_EXCEPTIONS** 

此选项可确保不会从路径评估传播任何异常。它遵循以下简单规则

- 如果存在选项`ALWAYS_RETURN_LIST`，则将返回空列表
- 如果选项`ALWAYS_RETURN_LIST`不存在，则返回null

