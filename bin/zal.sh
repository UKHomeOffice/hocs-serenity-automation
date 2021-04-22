#!/bin/sh

docker run --rm -t --name zalenium -p 4444:4444 \
      -e PULL_SELENIUM_IMAGE=true \
      -v /var/run/docker.sock:/var/run/docker.sock \
      -v /tmp/videos:/home/seluser/videos \
      --privileged dosel/zalenium start --desiredContainers 4 --maxTestSessions 4 --videoRecordingEnabled false