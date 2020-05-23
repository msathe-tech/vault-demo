#!/usr/bin/env bash

SCRIPT="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

[[ -z "$CLEAN" ]] || (cd $SCRIPT; mvn clean)

ARTIFACT="vault-demo-0.0.1-SNAPSHOT.jar"

[[ -f $SCRIPT/target/$ARTIFACT ]] || (cd $SCRIPT; mvn package)
# shellcheck disable=SC2068
java -jar $SCRIPT/target/$ARTIFACT $@