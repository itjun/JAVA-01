# Redis 主从复制/集群/哨兵

[http://download.redis.io/redis-stable/redis.conf](http://download.redis.io/redis-stable/redis.conf)

[http://download.redis.io/redis-stable/sentinel.conf](http://download.redis.io/redis-stable/sentinel.conf)

# 主从复制

创建多个Redis进程 然后在 `redis-cli` 输入设置

开启主从: `SLAVEOF 192.168.100.6 6300`

关闭主从: `SLAVEOF NO ONE`

# 集群搭建(Docker情况下)

集群中至少应该有奇数个节点，所以至少有三个节点，每个节点至少有一个备份节点.所以一共有6个节点

- reids-00
- redis-01
- redis-02
- redis-03
- redis-04
- redis-05

### 下载Docker Redis镜像

`docker pull redis`

### 创建映射目录

```bash
├── DockerRun.txt
├── redis-cluster.dockerfile
├── redis00
│   ├── config
│   └── data
├── redis01
│   ├── config
│   └── data
├── redis02
│   ├── config
│   └── data
├── redis03
│   ├── config
│   └── data
├── redis04
│   ├── config
│   └── data
└── redis05
    ├── config
    └── data
```

### 修改配置文件

1. 修改bind `bind 127.0.0.1 ::1` → `bind 0.0.0.0` 不进行IP绑定
2. 修改dir `/usr/local/var/db/redis/` → `/data/` 原先指定的路径不存在
3. 修改 cluster-enabled `cluster-enabled no` → `cluster-enabled yes`

[redis.conf](Redis%20%E4%B8%BB%E4%BB%8E%E5%A4%8D%E5%88%B6%20%E9%9B%86%E7%BE%A4%20%E5%93%A8%E5%85%B5%20e6a78394c99547d3bca914eb6d9a8fab/redis.conf)

把 `redis.conf` 复制到对应文件夹下

```bash
├── DockerRun.txt
├── redis-cluster.dockerfile
├── redis00
│   ├── config
│   │   └── redis.conf
│   └── data
├── redis01
│   ├── config
│   │   └── redis.conf
│   └── data
├── redis02
│   ├── config
│   │   └── redis.conf
│   └── data
├── redis03
│   ├── config
│   │   └── redis.conf
│   └── data
├── redis04
│   ├── config
│   │   └── redis.conf
│   └── data
└── redis05
    ├── config
    │   └── redis.conf
    └── data
```

启动容器

```shell
docker run -idt -v <实际路径>/redis/redis00/config/:/usr/local/redis/ -v <实际路径>/redis/redis00/data/:/data/ -p 6300:6379 --name redis-00 redis /usr/local/redis/redis.conf
docker run -idt -v <实际路径>/redis/redis01/config/:/usr/local/redis/ -v <实际路径>/redis/redis01/data/:/data/ -p 6301:6379 --name redis-01 redis /usr/local/redis/redis.conf
docker run -idt -v <实际路径>/redis/redis02/config/:/usr/local/redis/ -v <实际路径>/redis/redis02/data/:/data/ -p 6302:6379 --name redis-02 redis /usr/local/redis/redis.conf
docker run -idt -v <实际路径>/redis/redis03/config/:/usr/local/redis/ -v <实际路径>/redis/redis03/data/:/data/ -p 6303:6379 --name redis-03 redis /usr/local/redis/redis.conf
docker run -idt -v <实际路径>/redis/redis04/config/:/usr/local/redis/ -v <实际路径>/redis/redis04/data/:/data/ -p 6304:6379 --name redis-04 redis /usr/local/redis/redis.conf
docker run -idt -v <实际路径>/redis/redis05/config/:/usr/local/redis/ -v <实际路径>/redis/redis05/data/:/data/ -p 6305:6379 --name redis-05 redis /usr/local/redis/redis.conf
```

查看集群状态

`cluster info`

```docker
cluster_state:fail
cluster_slots_assigned:0
cluster_slots_ok:0
cluster_slots_pfail:0
cluster_slots_fail:0
cluster_known_nodes:1
cluster_size:0
cluster_current_epoch:0
cluster_my_epoch:0
cluster_stats_messages_sent:0
cluster_stats_messages_received:0
```

现在还是错误的,在Redis可以用redis-cli 进行协助搭建(命令如下) 在容器中IP需要设置为容器内的IP而不是宿主机的IP

```sh
redis-cli --cluster create  --cluster-replicas 1 172.17.0.2:6379 172.17.0.3:6379 172.17.0.4:6379 172.17.0.5:6379 172.17.0.6:6379 172.17.0.7:6379
```

查看集群状态

`cluster info`

```docker
127.0.0.1:6300> cluster info
cluster_state:ok
cluster_slots_assigned:16384
cluster_slots_ok:16384
cluster_slots_pfail:0
cluster_slots_fail:0
cluster_known_nodes:6
cluster_size:3
cluster_current_epoch:6
cluster_my_epoch:1
cluster_stats_messages_ping_sent:965
cluster_stats_messages_pong_sent:1028
cluster_stats_messages_sent:1993
cluster_stats_messages_ping_received:1023
cluster_stats_messages_pong_received:965
cluster_stats_messages_meet_received:5
cluster_stats_messages_received:1993
```

已经正常启动

现在奇怪的是在容器内进行redis-cli访问没有问题,但是在外部进行访问连接就会出现问题

`redis-cli -c -p 6379`

设置参数

```shell
127.0.0.1:6379> set abc 123
-> Redirected to slot [7638] located at 172.17.0.3:6379
OK
172.17.0.3:6379> set bbc 123
-> Redirected to slot [1158] located at 172.17.0.2:6379
OK
172.17.0.2:6379> get abc
-> Redirected to slot [7638] located at 172.17.0.3:6379
"123"
```

# 哨兵

在集群的目录结构上搭建重新下载Redis配置文件.

## 修改配置文件

### 主节点

1. 关闭保护模式 `protected-mode yes` → `protected-mode no`

### 从节点

1. 关闭保护模式 `protected-mode yes` → `protected-mode no`
2. 设置主节点 `replicaof` ->`replicaof 127.17.0.2 6379`

### 哨兵

在配置文件 `redis.conf` 添加  `sentinel monitor mymaster 35.236.172.131 6379 2`

ip指向master,最后的2是认为几个节点认为服务下线后进行切换

### 启动容器

因为之前搭建了6个,这次我们使用一个主节点四个从节点 redis05 改为哨兵,

注意看redis05的执行语句不太一样

```shell
docker run -idt -v /Users/itjun/Desktop/redis/redis00/config/:/usr/local/redis/ -v /Users/itjun/Desktop/redis/redis00/data/:/data/ -p 6300:6379 --name redis-00 redis /usr/local/redis/redis.conf
docker run -idt -v /Users/itjun/Desktop/redis/redis01/config/:/usr/local/redis/ -v /Users/itjun/Desktop/redis/redis01/data/:/data/ -p 6301:6379 --name redis-01 redis /usr/local/redis/redis.conf
docker run -idt -v /Users/itjun/Desktop/redis/redis02/config/:/usr/local/redis/ -v /Users/itjun/Desktop/redis/redis02/data/:/data/ -p 6302:6379 --name redis-02 redis /usr/local/redis/redis.conf
docker run -idt -v /Users/itjun/Desktop/redis/redis03/config/:/usr/local/redis/ -v /Users/itjun/Desktop/redis/redis03/data/:/data/ -p 6303:6379 --name redis-03 redis /usr/local/redis/redis.conf
docker run -idt -v /Users/itjun/Desktop/redis/redis04/config/:/usr/local/redis/ -v /Users/itjun/Desktop/redis/redis04/data/:/data/ -p 6304:6379 --name redis-04 redis /usr/local/redis/redis.conf
docker run -idt -v /Users/itjun/Desktop/redis/redis05/config/:/usr/local/redis/ -v /Users/itjun/Desktop/redis/redis05/data/:/data/ -p 6305:6379 --name redis-05 redis /usr/local/redis/redis.conf --sentinel
```

