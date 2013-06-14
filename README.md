MovieSite
=========

基于混合云存储系统的电影推荐引擎

推荐算法部分是Mahout下的Taste实现的，

数据集采用GroupLens 的数据集合，将这些数据集转换到mysql数据库中

其中Taste：http://mahout.apache.org/ 

GroupLens数据集：http://www.grouplens.org/node/12 

应用服务器后台数据库采用mysql

电影存储刚开始在数据中心（ceph搭建的分布式文件系统）

后来移植到实验室做的混合云存储系统上

采用java语言，javascript、jquery等

服务器是Apache Tomcat

电影是我从学校hudbt上下载的，数据量不大，有100部电影左右，

电影的图片信息是我从百度上收集的

电影图片信息和电影信息不严格匹配：
