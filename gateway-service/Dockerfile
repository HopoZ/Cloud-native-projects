FROM openjdk:8-jre-alpine

# 维护者信息
LABEL maintainer=me

# 设置环境变量
ENV JAVA_ARGS="-Dfile.encoding=UTF8 -Duser.timezone=GMT+08"
ENV JAR_FILE="gateway-service.jar"

# 将 JAR 文件复制到容器中
COPY ./${JAR_FILE} ./

# 暴露端口
EXPOSE 8888

# 启动命令
CMD java -jar ${JAVA_ARGS} ${JAVA_OPTS} /${JAR_FILE}

