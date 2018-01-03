# Purchasing-Management-System
采用MVC+sqlserver模型开发
实例可以前往网址http://118.89.44.123:8080/Purchase/login.jsp
1．	实验目的：<br>
掌握数据库模式设计，依据实际要求设计表结构，建立表的关系；结合一定的开发工具实现数据库应用程序的开发。<br>
2．	实验环境：<br>
2.1 硬件环境：<br>
	惠普ProBook  6455b笔记本电脑<br>
2.2 软件环境（包括操作系统、应用服务器配置、开发环境配置等）<br>
系统：Winidows10<br>
服务器：Tomcat9.0<br>
数据库：SQL Server 2008 R2<br>
开发环境：Eclipse<br>
3．需求分析： <br>
	 <br>
	职员、供应商和商品的基本管理，以及订单管理。以订单为关系将供应商、商品和职员联系起来。上图为ER图，并将字段放入概念设计中。<br>
4．概念结构设计<br>
	概念设计分为4个实体：分别为订单，商品，供应商和职员。由于订单和商品为n：n关系，所以还有一个订单详细表。<br>
实体：<br>
	职员：编码，密码，职位，姓名，性别，出生日期，地址，电话。<br>
	主键：编码。<br>
	供应商：编码，姓名，代理人，电话，地址，邮编，银行，账号。<br>
主键：编码。<br>
订单：编码，订单日期，供应商编码，职员编码，提交日期，审核意见，审核日期，审核结果，审核人编码，审核名字。<br>
主键：编码：<br>
	商品：编码，名称，规格，类型，型号，供应商编码，进价，售价，vip价格，库存。<br>
	主键：编码。<br>
关系：<br>
	订单明细：订单编码，商品编码，库存。<br>
	商品分类：类型，大类，中类，小类，名称。<br>
5．逻辑结构设计<br>
	供应商和商品为n：1供应关系，一个供应商可以供应多个商品。订单与供应商为n：1归属关系，每个订单属于1个供应商，一个东营上有多个订单。订单与商品为n：n关系，每个订单包含多个商品，每个商品可以出现在多个订单中。职员与订单为n：1关系，一个订单可以由多个职员管理。<br>
6．数据库实现<br>
	建立6个表。4个实体分别对应一个实体表，如：order_table，goods，supplier，class，emp。而商品和订单由于n：n关系，可以抽离一个关系表，即order_detail。还设立了一个class商品分类表。<br>
Class：<br>
 ![image](https://github.com/ChineseAStar/Purchasing-Management-System/blob/master/images/class.png)<br>
Emp：<br>
 ![image](https://github.com/ChineseAStar/Purchasing-Management-System/blob/master/images/emp.png)<br>
Goods：<br>
 ![image](https://github.com/ChineseAStar/Purchasing-Management-System/blob/master/images/goods.png)<br>
 <br>
Order_detail:<br>
 ![image](https://github.com/ChineseAStar/Purchasing-Management-System/blob/master/images/order2.png)<br>
Order_table:<br>
 ![image](https://github.com/ChineseAStar/Purchasing-Management-System/blob/master/images/order1.png)<br>
Supplier：<br>
 ![image](https://github.com/ChineseAStar/Purchasing-Management-System/blob/master/images/supplier.png)<br>
数据库后台连接代码：数据库连接将其抽象形成一个类，使代码优化，每一次操作调用该类实现连接。<br>
 <br>
7．应用系统设计实现<br>
	设计登陆界面，实现从职员表中获取登录信息。登陆后进入欢迎界面；登陆后有四种管理界面，设计Filter过滤器过滤不合权限的操作，跳转到权限不足的界面。<br>
	对于职员和供应商的管理，实现四种基本操作。对于订单管理，实现基本操作外，增加自动生成订单编号的设置，以及多条商品自动分类生成订单。对于商品管理，除基本操作和自动生成编码外，设定分类，并按分类生成订单。<br>
实际界面如下：<br>
登录界面：更具账号密码进行登录。<br>
 <br>
欢迎界面：登陆后首先进入的页面，然后可以选择相应的操作模块。<br>
 <br>
列表界面（订单管理为例）：进入后可以进行增删改查的基本操作。<br>
 <br>
订单生成控制界面：拥有手动生成和自动生成两种。手动生成是选择供应商后进入订单修改界面，并自动生成订单号。自动生成将完全自动生成完毕，将所有选择的商品按照供应商分类，生成订单。<br>
 <br>
订单修改界面：订单修改可以实现对于商品条目的增、删查看和对数量的修改。<br>
 <br>
商品分类的简单管理界面：拥有对于商品分类的删除和增加功能。分类分为大类，中类和小类。凡类下有子类的不可删除。<br>
 <br>
订单审核界面：在这里实现的代码审核，选择审核结果，和填写审核界面，后台将自动获取审核时间和审核人信息。<br>
 <br>
权限不足的限制界面：经理和采购员存在功能限制，凡是试图进入无权限的模块，将自动跳转到该界面。<br>
 <br>
密码修改：每个人都可以对自己的密码进行修改。<br>











附录：<br>
后台：<br>
	包类：<br>
daos层:设定每个模块单独的daos操作类。全部为接口定义所有需要的操作目录。<br>
daosImp层:是daos层的实现，将对应模块的daos函数，在这里实现具体操作。<br>
Controller层：控制类，所有操作在这里分配到不同的servlet，实现具体操作。<br>
servlet层:实现对于不同模块的详细操作，是联系界面和daos层的业务处理类。<br>
common层：存放公共资源类，存放有数据库连接关闭的ResourceClose类，Page分页类，ConnectionFactory数据库来连接类。<br>
model层：全部的javabean文件。存放所用到的各种类型的信息类。<br>
filter层：过滤层，在这里实现所有的操作过滤功能，对于每次工作提前判断是否拥有身份切是否符合权限。没有身份的跳回登陆页面，不符合权限的跳转到权限不符的界面。<br>
Web.xml文件:<br>
	通过该文件实现对于所有的操作的第一次调度，实现先过滤再分配的内容。<br>
前台：<br>
	登陆界面：login.jsp实现登陆功能。<br>
	Emp文件夹：存放所有的职员管理的界面内容，以及欢迎界面、密码修改界面和权限限制界面。<br>
	Goods：文件夹；存放所有的商品管理的界面内容，以及商品分类管理。<br>
	Suppliers文件夹：存放所有的供应商界面内容。<br>
	Orders文件夹：存放所有的订单管理的界面内容。<br>
资源文件：<br>
	Css文件夹：存放所有的css文件，用于网页模型。<br>
	Js文件夹：存放所有的js文件，用于代码函数的响应。<br>
	Images和img文件夹：存放所有的资源图片。<br>
<br>
