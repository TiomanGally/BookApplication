#!/bin/bash
docker run --name=postgresForBookit -d --rm -e POSTGRES_USER=bookitUser -e POSTGRES_PASSWORD=bookitPwd -e POSTGRES_DB=bookitdb postgres:11
