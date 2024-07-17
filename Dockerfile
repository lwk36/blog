# 使用Java17镜像作为基础镜像
FROM  openjdk:17-jdk

# 设置工作目录
WORKDIR /usr/app

# 将Maven构建后的JAR文件复制到工作目录
COPY target/blog.jar app.jar

# 暴露应用的端口
EXPOSE 8080

# 设置JVM参数
ENV JAVA_OPTS="-Xmx512m -Xms256m"

# 设置容器启动时执行的命令
ENTRYPOINT [ "sh", "-c", "java ${JAVA_OPTS} -Dspring.profiles.active=docker -jar /usr/app/app.jar" ]
