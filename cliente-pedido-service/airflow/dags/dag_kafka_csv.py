import csv
import json
import requests
from airflow import DAG
from airflow.operators.python import PythonOperator
from datetime import datetime
from kafka import KafkaProducer

URL = "https://jsonplaceholder.typicode.com/posts"
CSV_FILE_PATH = "/opt/airflow/dados/dados.csv"
JSON_FILE_PATH = "/opt/airflow/dados/dados.json"
KAFKA_TOPIC = "dados-airflow"
KAFKA_SERVER = "kafka:9092"

default_args = {
    'owner': 'airflow',
    'start_date': datetime(2024, 1, 1),
}


def baixar_dados():
    response = requests.get(URL)
    response.raise_for_status()
    with open(JSON_FILE_PATH, 'w') as f:
        json.dump(response.json(), f)
    print(f"✅ JSON salvo em {JSON_FILE_PATH}")


def converter_para_csv():
    with open(JSON_FILE_PATH) as json_file:
        dados = json.load(json_file)

    with open(CSV_FILE_PATH, 'w', newline='') as csv_file:
        writer = csv.DictWriter(csv_file, fieldnames=["userId", "id", "title", "body"])
        writer.writeheader()
        for item in dados:
            writer.writerow(item)
    print(f"✅ CSV salvo em {CSV_FILE_PATH}")


def publicar_em_kafka():
    producer = KafkaProducer(
        bootstrap_servers=KAFKA_SERVER,
        value_serializer=lambda v: json.dumps(v).encode('utf-8')
    )

    with open(CSV_FILE_PATH, 'r') as f:
        next(f)  # Pula o cabeçalho
        for linha in f:
            dados = linha.strip().split(',')
            if len(dados) < 4:
                continue  # evita erro se linha estiver incompleta
            message = {
                "userId": int(dados[0]),
                "id": int(dados[1]),
                "title": dados[2],
                "body": ','.join(dados[3:])
            }
            print(f"📤 Enviando para Kafka: {message}")
            producer.send(KAFKA_TOPIC, message)

    producer.flush()
    print(f"✅ Todos os dados foram enviados para o tópico Kafka: {KAFKA_TOPIC}")


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
