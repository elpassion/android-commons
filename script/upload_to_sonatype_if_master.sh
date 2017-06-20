#!/usr/bin/env bash

if [ "master" = `git rev-parse --abbrev-ref HEAD` ]; then
    ./gradlew uploadArchives
fi