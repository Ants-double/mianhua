# mianhua
cotton 

- 添加方式

  ``` xml
  <!-- https://mvnrepository.com/artifact/com.github.ants-double/mainhua -->
  <dependency>
      <groupId>com.github.ants-double</groupId>
      <artifactId>mainhua</artifactId>
      <version>1.03-RELEASE</version>
  </dependency>
  
  ```

  

### 使用

``` wiki
可以参考测试工程中相关代码
```





- Array相关

  ``` java
   Double testDouble=new Double(9);
          System.out.println(testDouble.intValue());
          System.out.println(testDouble.floatValue());
          System.out.println(testDouble.doubleValue());
          List<Double> doubleList = new ArrayList<Double>() {
              {
                  add(1.0);
                  add(2.0);
                  add(3.0);
              }
          };
          System.out.println(Arrays.toString(doubleList.toArray()));
          Double[] doubles = new Double[]{4.0, 5.0, 6.0};
          List<Double> doubleArrayList = AntsArrayUtil.arrayConvertList(doubles);
          System.out.println(Arrays.toString(doubleArrayList.toArray()));
  
          List<Double[]> listArray = new ArrayList<>();
          listArray.add(doubles);
          listArray.add(AntsArrayUtil.convertArray(doubleList, Double.class));
          Double[] sumArray = AntsArrayUtil.addListArrays(listArray, Double.class, doubles.length);
          System.out.println(Arrays.toString(sumArray));
          Double[] doubleAverage = AntsArrayUtil.averageListArrays(listArray, Double.class, doubles.length);
          System.out.println(Arrays.toString(doubleAverage));
  
  
          System.out.println(Arrays.toString(AntsArrayUtil.convertArray(doubleList, Double.class)));
  ```

- 