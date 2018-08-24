FROM quay.io/ukhomeofficedigital/java8-mvn

VOLUME      ["/usr/src/app"]

WORKDIR /app

RUN mkdir -p /app

COPY . /app

ENTRYPOINT /app/bin/demo.sh