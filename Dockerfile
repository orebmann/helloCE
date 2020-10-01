FROM ibmjava:jre
RUN mkdir /opt/app
COPY HelloCOS.class /opt/app
COPY ibm-sql-query-jdbc-driver-2.3.2.jar /opt/app
WORKDIR /opt/app
CMD ["java", "-classpath", "ibm-sql-query-jdbc-driver-2.3.2.jar:.", "HelloCOS"]