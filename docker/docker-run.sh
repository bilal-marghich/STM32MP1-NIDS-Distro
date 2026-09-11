#!/usr/bin/env bash
# Lance le conteneur de build CANARY avec le projet monté et KAS_WORK_DIR fixé.
docker run -it \
  -v "/home/rog/Desktop/project/:/home/builder/project" \
  -e KAS_WORK_DIR=/home/builder/project/canary \
  canary-builder bash
