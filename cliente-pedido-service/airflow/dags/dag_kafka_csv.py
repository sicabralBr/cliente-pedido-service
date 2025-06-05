from airflow import DAG
from airflow.operators.python import PythonOperator
from datetime import datetime
import requests
import csv
import json
from kafka import KafkaProducer

URL = "https://jsonplaceholder.typicode.com/posts"
CSV_FILE_PATH = "/tmp/dados.csv"
KAFKA_TOPIC = "dados-airflow"
KAFKA_SERVER = "kafka:9092"

default_args = {
    'owner': 'airflow',
    'start_date': datetime(2024, 1, 1),
}

def baixar_dados():
    response = requests.get(URL)
    response.raise_for_status()
    with open(CSV_FILE_PATH.replace('.csv', '.json'), 'w') as f:
        json.dump(response.json(), f)

def converter_para_csv():
    with open(CSV_FILE_PATH.replace('.csv', '.json')) as json_file:
        dados = json.load(json_file)

    with open(CSV_FILE_PATH, 'w', newline='') as csv_file:
        writer = csv.DictWriter(csv_file, fieldnames=["userId", "id", "title", "body"])
        writer.writeheader()
        for item in dados:
            writer.writerow(item)

def publicar_em_kafka():
    with open(CSV_FILE_PATH, 'r') as f:
        producer = KafkaProducer(
            bootstrap_servers=KAFKA_SERVER,
            value_serializer=lambda v: json.dumps(v).encode('utf-8')
        )
        next(f)  # Pular o cabeçalho
        for linha in f:
            dados = linha.strip().split(',')
            message = {
                "userId": int(dados[0]),
                "id": int(dados[1]),
                "title": dados[2],
                "body": dados[3] if len(dados) > 3 else ""
            }
            producer.send(KAFKA_TOPIC, message)
        producer.flush()

with DAG("dag_airflow_to_kafka",
         default_args=default_args,
         schedule_interval="@daily",
         catchup=False) as dag:

    t1 = PythonOperator(
        task_id="baixar_dados",
        python_callable=baixar_dados
    )

    t2 = PythonOperator(
        task_id="converter_para_csv",
        python_callable=converter_para_csv
    )

    t3 = PythonOperator(
        task_id="publicar_em_kafka",
        python_callable=publicar_em_kafka
    )

    t1 >> t2 >> t3
