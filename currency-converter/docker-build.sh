#!/bin/bash

# Get .sh script directiory
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"

$DIR/../gradlew :currency-converter:build -x test
docker build . -t currency-converter.app:latest