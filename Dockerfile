FROM quay.io/ukhomeofficedigital/java8-mvn

VOLUME      ["/usr/src/app"]

WORKDIR /app

RUN mkdir -p /app

COPY . /app

COPY maven/settings.xml /app/.m2/settings.xml

ENTRYPOINT /app/bin/demo.sh