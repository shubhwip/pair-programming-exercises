#!/bin/bash

usage() {
  echo "
Usage: bash wordgame.sh [OPTIONS]
wordgame.sh simulates the word game.
OPTIONS:
  -s                    String Name
"
  exit 0
}

args="$@"
OPTIND=1
optspec="h?s:"
while getopts "${optspec}" opt; do
  case "${opt}" in
  s)
    inputstring=$OPTARG
    ;;
  h | *)
    usage
    ;;
  esac
done
shift "$((OPTIND - 1))"
if [[ -z $inputstring ]]; then
  echo "Please provide input string, Exiting"
  usage
  exit 0
fi

// Simulating Word Game
java -jar target/wordgame-1.0-jar-with-dependencies.jar  $inputstring