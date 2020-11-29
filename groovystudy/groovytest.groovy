
print("hello word")
println "22222"
1.upto(2) {println("number $it")}
3.times {print "$it"}
println "java -version".execute().text
println "cmd /C dir".execute().text
String name=null
println name?.toLowerCase()
name="liuweihong"
println name?.toLowerCase()
//可选形参
def testArgs(name,age=10){
    println "name:$name,age:$age"
}
def testArgs(name,String[] atrr){
    println "name:$name,attr:$atrr"
}
testArgs("liuweihong")
testArgs("liuweihong","10","20")
//使用多赋值
def splitName(fullName){
    fullName.split(' ')
}
def (firstName,lastName)=splitName("liu weihong")
println "firstname:$firstName,lastName:$lastName"
/*
交换变量的值
多余的变量设置为null，多余的值抛弃
* */
def name1="3333"
def name2="4444"
println "name1:$name1,name2:$name2"
(name1,name2)=[name2,name1]
println "name1:$name1,name2:$name2"
/*
实现接口
* */
Thread test = new Thread({println "run thread"} as Runnable)
test.start();
runmethod = {println "run thread 2"}
test2 = new Thread(runmethod as Runnable)
test2.start()
/*
布尔求值
java要求必须是一个布尔表达式
* */
println "end"

//groovy每行不需要分号
//方法调用的括号可以省略
//实现循环的方式
0.upto(3) {println it}
//范围从0开始，可以使用times方法
3.times {println it }
//跳过某些值
0.step(10,2) {println it }
//java.lang.Process的简单实现
println "ipconfig".execute().text
//安全导航操作符（?.），只有引用不为null时才调用对应的方法,否则返回null
def foo(str) {
    str?.reverse()
}
println foo('liu weihong')
println foo(null)
//异常处理跟java的差异，不要求捕获异常，自动向上抛出
//Java Bean，不需要写get、set方法，后台自动生成，访问时不需要使用get、set方法，直接字段名称
//groovy不能把字段标记为私有
//灵活初始化和具名参数
//可选形参，可以把方法和构造器的形参设为可选的，通过给参数设置值就变为可选的，末尾的数组形参也是作为可选的
//使用多赋值，返回一个数组，赋值给圆括号括起来的一组变量
def splitName2(fullname) {
    fullname.split(' ')
}
def (firstname,lastname)=splitName2('liu weihong')
println "firstnmae:$firstname,lastname:$lastname"
//实现接口的差异 可以把一个映射和代码块转化为接口,接口有多个方法时，采用映射
thread =new  Thread({println "11111"} as Runnable)
thread.start();
//布尔求值的差异 groovy会自动把表达式计算为布尔值,java要求if语句部分必须是一个布尔表达式
//有一套转换规则，在类中也可以通过编写asBoolen方法来编写转换规则
str='hello'
if(str) {println "true"}
//操作符重载




