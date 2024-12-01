# 策略模式笔记

### 关于泛型
方法中的优先级比类上的优先级高
如果方法中的泛型用的是类上的T，那么此方法无法设置为static，见Cycler

```java
// 这两个其实是一个对象
Cycler<Integer> instanceInteger = Cycler.getInstance();
Cycler<Cat> instanceCat = Cycler.getInstance();
```

### Comparator<T>
比较器接口，定义了一个方法int compare(T t1 , T t2)，在SorterByComparator中，通过传入不同类型的比较器，可以实现不同类型的排序对比方式。

可以使用以下几种方式实现
* 匿名内部类形式
* lambda格式
* 传入类格式

### Comparable<T>
比较接口，在比较类中，定义方法void sort(Comparable[] comparables)，传参为Comparable类型，比较类需要去实现Comparable接口。
