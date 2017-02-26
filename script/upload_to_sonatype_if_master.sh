#!/usr/bin/env bash

if [ "$TRAVIS_BRANCH" == "master" ]; then
    ./gradlew uploadArchives
fi