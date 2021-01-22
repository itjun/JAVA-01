# 第二周 课程作业

【周三作业题目】

1、使用 GCLogAnalysis.java 自己演练一遍串行/并行/CMS/G1的案例。 

2、使用压测工具（wrk或sb），演练gateway-server-0.0.1-SNAPSHOT.jar示例。 

3、(选做)如果自己本地有可以运行的项目，可以按照2的方式进行演练。 

4、(必做)根据上述自己对于1和2的演示，写一段对于不同GC和堆内存的总结，提交到 github。

【周日作业题目】

**1.（选做）**运行课上的例子，以及 Netty 的例子，分析相关现象。

**2.（必做）**写一段代码，使用 HttpClient 或 OkHttp 访问 [http://localhost:8801 ](http://localhost:8801/)，代码提交到 Github。

## GCLogAnalysis

### GC参数运行分析

![GC运行分析](homework1/GC运行分析.png)

### GC运行对比

![GCLogAnalysis运行记录](homework1/GCLogAnalysis运行记录.png)

### GC运行结论

同一GC，内存增大：

1. 类生产数量 增加---临界值---减少 
2. GC数量变化 增加---临界值---减少
3. GC平均时间 增加---临界值---增加

不同GC，统一内存：

1. GC的次数   UseParallel > ConcMarkSweep > Serial > G1 

2. 类生产数量 G1 > UseParallel > ConcMarkSweep >=  Serial

## 压测

1. 由于时间太赶，作业采用了60s的压测时间
2. 在Mac上运行出现死机现象，改回使用 Win10 + Linux子系统压测
3. ![image-20210123012204654](homework1/wrk压测.png)

### **512m**

```bash
Running 1m test @ http://127.0.0.1:8088/api/hello
  100 threads and 200 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    25.59ms   66.44ms 947.44ms   91.58%
    Req/Sec   345.18    132.67     3.12k    71.35%
  1949819 requests in 1.00m, 232.79MB read
  Socket errors: connect 0, read 30, write 0, timeout 0
Requests/sec:  32441.20
Transfer/sec:      3.87MB
```

### **1g**

```bash
Running 1m test @ http://127.0.0.1:8088/api/hello
  100 threads and 200 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    30.20ms   73.84ms 979.19ms   90.93%
    Req/Sec   305.36    136.97     2.68k    65.04%
  1727653 requests in 1.00m, 206.26MB read
  Socket errors: connect 0, read 17, write 0, timeout 0
Requests/sec:  28745.98
Transfer/sec:      3.43MB
```

### **2g**

```bash
Running 1m test @ http://127.0.0.1:8088/api/hello
  100 threads and 200 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    29.50ms   72.75ms   1.18s    90.59%
    Req/Sec   347.25    149.83     1.28k    67.71%
  1944656 requests in 1.00m, 232.17MB read
  Socket errors: connect 0, read 156, write 0, timeout 0
Requests/sec:  32361.60
Transfer/sec:      3.86MB
```

### **4g**

```bash
Running 1m test @ http://127.0.0.1:8088/api/hello
  100 threads and 200 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    26.10ms   69.14ms 890.15ms   91.90%
    Req/Sec   341.35    134.31     2.75k    69.76%
  1923392 requests in 1.00m, 229.63MB read
  Socket errors: connect 0, read 63, write 0, timeout 0
Requests/sec:  32003.36
Transfer/sec:      3.82MB
```

### **8g**

```bash
Running 1m test @ http://127.0.0.1:8088/api/hello
  100 threads and 200 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    28.66ms   73.43ms   1.27s    91.28%
    Req/Sec   333.33    137.90     1.53k    66.94%
  1876686 requests in 1.00m, 224.06MB read
  Socket errors: connect 0, read 131, write 0, timeout 0
Requests/sec:  31227.82
Transfer/sec:      3.73MB
```

### Parallel

```bash
Running 1m test @ http://127.0.0.1:8088/api/hello
  100 threads and 200 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    37.76ms  106.87ms   1.18s    91.80%
    Req/Sec   364.37    143.46     2.96k    70.93%
  1990473 requests in 1.00m, 237.64MB read
  Socket errors: connect 0, read 39, write 1, timeout 0
Requests/sec:  33116.26
Transfer/sec:      3.95MB
```

### ConcMarkSweep

```bash
Running 1m test @ http://127.0.0.1:8088/api/hello
  100 threads and 200 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    24.91ms   65.63ms   1.17s    92.02%
    Req/Sec   333.63    130.55     2.93k    70.39%
  1893004 requests in 1.00m, 226.00MB read
  Socket errors: connect 0, read 144, write 0, timeout 0
Requests/sec:  31495.59
Transfer/sec:      3.76MB
```

### G1

1G

```bash
Running 5m test @ http://127.0.0.1:8088/api/hello
  100 threads and 200 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    25.60ms   69.79ms   1.23s    90.73%
    Req/Sec   406.35    146.58     1.57k    75.51%
  11247741 requests in 5.00m, 1.31GB read
  Socket errors: connect 0, read 31, write 1, timeout 0
Requests/sec:  37480.44
Transfer/sec:      4.47MB
```

8G

```bash
Running 5m test @ http://127.0.0.1:8088/api/hello
  100 threads and 200 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    29.74ms  105.55ms   1.98s    93.30%
    Req/Sec   396.53    141.84     1.58k    73.92%
  10965283 requests in 5.00m, 1.28GB read
  Socket errors: connect 0, read 83, write 0, timeout 0
Requests/sec:  36539.81
Transfer/sec:      4.36MB
```