FROM ubuntu

WORKDIR /

RUN apt update

RUN apt install -y wget tar vim openjdk-17-jre supervisor
RUN wget https://dlcdn.apache.org/kafka/3.5.0/kafka_2.13-3.5.0.tgz
RUN tar -xzf kafka_2.13-3.5.0.tgz
RUN wget https://dlcdn.apache.org/zookeeper/zookeeper-3.9.1/apache-zookeeper-3.9.1-bin.tar.gz
RUN tar -xzf apache-zookeeper-3.9.1-bin.tar.gz
RUN mv /kafka_2.13-3.5.0 /kafka
RUN mv /apache-zookeeper-3.9.1-bin /zookeeper

WORKDIR /kafka

COPY ./server.properties /kafka/config/server.properties
COPY ./zoo.cfg /zookeeper/conf/zoo.cfg
COPY ./supervisord.conf /etc/supervisor/conf.d/supervisord.conf

EXPOSE 9092

ENTRYPOINT [ "/usr/bin/supervisord" ]