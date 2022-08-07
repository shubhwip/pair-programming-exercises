#!/bin/bash

usage() {
  echo "
Usage: bash cookie-analyzer.sh [OPTIONS]
cookie-analyzer.sh analyzes your cookies data.
OPTIONS:
  -d                    Parameter for date
  -f                    FileName
  -h                    Shows help (for K2Agents)
"
  exit 0
}

args="$@"
OPTIND=1
optspec="h?d:f:"
while getopts "${optspec}" opt; do
  case "${opt}" in
  d)
    date=$OPTARG
    ;;
  f)
    filename=$OPTARG
    ;;
  h | *)
    usage
    ;;
  esac
done
shift "$((OPTIND - 1))"
if [[ -z $filename ]]; then
  echo "Please provide filename, Exiting"
  usage
  exit 0
fi

if [ ! -z "$date" ]; then
  java -jar target/cookieanalyzer-1.0-jar-with-dependencies.jar  $date $filename
else
  java -jar target/cookieanalyzer-1.0-jar-with-dependencies.jar $filename
fi