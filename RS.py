import requests
import json
from datetime import datetime

now = datetime.now()
appointment_url = "http://google.com"
new_appoint_header = {'Content-Type': 'application/json'}
new_appoint_payload = {
        "title": "appointment",
        "description": "just a new appointment",
        "completionDate": "2025-01-18T14:00:00",
        "completed": False
    }


def send_req(now,appointment_url,new_appoint_header,new_appoint_payload):
    url = appointment_url
    headers = new_appoint_header
    payload = new_appoint_payload

    response = requests.post(url, data=json.dumps(payload), headers=headers)
    if response.status_code != 200:
        print(str(now) + " - Error: "+ str(response.status_code) + " Message: " + str(response.text))
    else:
        print(str(now) + " - Success: " + str(response.status_code) + " Message: " + str(response.text))



if __name__ == "__main__":
    send_req(now,appointment_url,new_appoint_header,new_appoint_payload)
