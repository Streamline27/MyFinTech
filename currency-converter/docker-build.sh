#!/bin/bash

./gradlew build -x test
docker build . -t currency-converter.app:latest