FROM quay.io/ukhomeofficedigital/java8-mvn

VOLUME      ["/usr/src/app"]
WORKDIR /usr/src/app

RUN yum install -y bzip2
RUN yum install -y fontconfig
COPY google-chrome.repo /etc/yum.repos.d/
RUN yum install -y gnu-free-sans-fonts
RUN yum install -y google-chrome-stable
RUN yum install -y xorg-x11-server-Xvfb
RUN Xvfb -ac :99 -screen 0 1680x1050x24 &
RUN export DISPLAY=:99

ENTRYPOINT /bin/demo.sh