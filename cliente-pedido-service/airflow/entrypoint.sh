#!/bin/bash

echo "⏳ Inicializando Airflow DB..."
airflow db init

echo "👤 Criando usuário admin..."
airflow users create \
  --username admin \
  --firstname Admin \
  --lastname Admin \
  --role Admin \
  --email admin@example.com \
  --password admin

echo "🚀 Iniciando Airflow Webserver..."
exec airflow webserver
