# 使用Java17镜像作为基础镜像
FROM openjdk:17-jdk-alpine
FROM maven:3.8.3-openjdk-17

# 设置工作目录
WORKDIR /usr/src/app

COPY . .

# 打包
RUN mvn clean install -DskipTests --settings=settings.xml

RUN mkdir -p /usr/app

# 将Maven构建后的JAR文件复制到工作目录
RUN cp target/blog*.jar /usr/app/blog.jar

WORKDIR /usr/app

RUN rm /usr/src -rf

# 暴露应用的端口
EXPOSE 8080

# 设置JVM参数
ENV JAVA_OPTS="-Xmx512m -Xms256m"

# 设置容器启动时执行的命令
ENTRYPOINT [ "sh", "-c", "java ${JAVA_OPTS} -jar /usr/app/blog.jar" ]
