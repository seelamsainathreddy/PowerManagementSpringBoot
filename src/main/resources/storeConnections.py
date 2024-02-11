import requests
import csv
from decimal import Decimal
# Function to read data from CSV file
def read_csv(file_path):
    data = []
    with open(file_path, 'r') as file:
        csv_reader = csv.DictReader(file)
        for row in csv_reader:
            data.append(row)
    return data

# Function to add entries hitting the API
def add_entries(data):
    url = "http://localhost:8080/api/v1/connections"
    headers = {"Content-Type": "application/json"}
    
    for entry in data:
        response = requests.post(url, json=entry, headers=headers)
        if response.status_code == 201:
            print(f"Entry added successfully: {entry}")
        else:
            print(f"Failed to add entry: {entry}. Status code: {response.status_code}")

# Path to your CSV file
file_path = '/Users/siddhuseelam/Downloads/PowerConnectionManagement/src/main/resources/data.csv'

# Read data from CSV file
data = read_csv(file_path)

# Add entries hitting the API
add_entries(data)
